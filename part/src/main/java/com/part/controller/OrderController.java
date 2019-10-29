package com.part.controller;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import com.part.entity.ChargingStandard;
import com.part.entity.Order;
import com.part.entity.Partingset;
import com.part.entity.User;
import com.part.entity.vo.OrderVo;
import com.part.service.ChargingStandardService;
import com.part.service.CouponService;
import com.part.service.OrderService;
import com.part.service.PartingsetService;
import com.part.service.UserService;
import com.part.utils.DateTimeUtils;
import com.part.utils.MyEntityWrapper;
import com.part.utils.OrderNumUtil;
import com.part.utils.Result;
import com.part.utils.poi.FileUtil;
import com.part.utils.poi.OrderExcel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiangSD
 * @since 2019-10-17
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private PartingsetService partingsetService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private ChargingStandardService chargingStandardService;
	@Autowired
	private UserService userService;
	  
	@ApiOperation("创建订单")
	@ApiImplicitParams(value = {
            @ApiImplicitParam(name = "user_id", value = "用户id",
                    required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "cc_id", value = "停车场id",
                    required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "cw_id", value = "车库id",
                    required = true, paramType = "query", dataType = "string")
    })
	@PostMapping(value = "/createOrder")
    @Transactional
    public Result createOrder(Order order){
		//判断该用户是否有未完成的订单
		List<Order> orderList = orderService.selectList(new MyEntityWrapper<Order>().eq("user_id", order.getUserId()));
		for (int i = 0; i < orderList.size(); i++) {
			if(orderList.get(i).getState()==0){
				Map map=new HashMap<>();
				return new Result().erro("你还有未完成的订单，请你完成后在进行操作");
			}
		}
		order.setOrderNum(OrderNumUtil.getOrderIdByUUId());
		order.setCreateTime(new Date());
		order.setState(0);
		order.insert();
		//更改车位的状态，从空闲换成使用中
		Partingset partingset = partingsetService.selectOne(new MyEntityWrapper<Partingset>().eq("id", order.getCwId()).eq("cc_id", order.getCcId()));
		partingset.setState(2);
		partingset.updateById();
		return new Result().success();
	}
	
	@ApiOperation("车辆离场")
	@PostMapping(value = "/carLeave")
	public Result carLeave(Integer userId){
		List<Order> orderList = orderService.selectList(new MyEntityWrapper<Order>().eq("user_id", userId));
		//正在进行中的订单查询
		List<Order> order =new ArrayList<Order>();
		for (int i = 0; i < orderList.size(); i++) {
			if(orderList.get(i).getState()==0){
				order.add(orderList.get(i));
			}
		}
		order.get(0).setEndTime(new Date());
		order.get(0).updateById();
		//时间戳差
		long time=order.get(0).getEndTime().getTime()-order.get(0).getCreateTime().getTime();
		System.out.println("time:"+time);
		
		int hour=(int) Math.ceil(time/1000/60/60);
		System.out.println("hour:"+hour);
		List<ChargingStandard> feeList = chargingStandardService.selectList(new MyEntityWrapper<ChargingStandard>().eq("id", order.get(0).getCcId()));
		if(hour<=feeList.get(0).getTime()){
			BigDecimal sumprice=new BigDecimal(hour).multiply(feeList.get(0).getPrice());
			order.get(0).setOrderPrice(sumprice);
			order.get(0).updateById();
			System.out.println("order.get(0):"+order.get(0));
		}
		if(hour>feeList.get(0).getTime() && hour<=feeList.get(1).getTime()){
			BigDecimal sumprice=feeList.get(1).getPrice();
			order.get(0).setOrderPrice(sumprice);
			order.get(0).updateById();
		}
		if(hour>feeList.get(1).getTime() && hour<feeList.get(2).getTime() || hour>=feeList.get(2).getTime()){
			BigDecimal sumprice=feeList.get(2).getPrice();
			order.get(0).setOrderPrice(sumprice);
			order.get(0).updateById();
		}
		return new Result().setData(order.get(0));
	}
	
	@ApiOperation("删除订单")
	@PostMapping(value = "/delOrder")
	public Result delOrder(String orderNumber){
		Order order = orderService.selectById(new MyEntityWrapper<Order>().eq("order_num", orderNumber));
		order.setIsDel(1);
		order.updateById();
		return new Result().success();
		
	}
	
	
	@ApiOperation("展示我的订单")
	@GetMapping(value = "/myOrder")
	public Result myOrder(Integer userId){
		List<Order> myOrder = orderService.selectList(new MyEntityWrapper<Order>().eq("user_id", userId).eq("is_del", 0));
		return new Result().setData(myOrder);
	}
	
	@ApiOperation("通过订单状态--展示我的订单")
	@GetMapping(value = "/myOrderByState")
	public Result myOrderByState(Integer userId,Integer state){
		List<Order> selectList = orderService.selectList(new MyEntityWrapper<Order>().eq("user_id", userId).eq("state", state));
		return new Result().setData(selectList);
	}
	/*
	 * 后台首页获取相关数据
	 * */
	@ApiOperation("后台首页获取数据")
	@GetMapping(value="/getMainInfo")
	public Result getMainInfo(HttpSession session){
	//	int role = (int)session.getAttribute("adminRole");
		
		Map<String, Object> map = Maps.newHashMap();
        Map<String, Object> dayStartAndEnd = DateTimeUtils.getDayStartAndEnd();//获取今天起止日期
        Map<String, Object> lastDayStartAndEnd = DateTimeUtils.getLastDayStartAndEnd();//获取昨天天起止日期
        Map<String, Object> sevenStartAndEnd = DateTimeUtils.getSevenStartAndEnd();//获取七天起止日期
        Map<String, Object> mounthStartAndEnd = DateTimeUtils.getMounthStartAndEnd();//获取本月起止日期
        
       //今日订单总数
        int orderTotalNumDay=orderService.selectCount(new MyEntityWrapper<Order>().gt("create_time", dayStartAndEnd.get("startTime")).lt("create_time", dayStartAndEnd.get("endTime")));
	  //今日交易总额
        List<Order> ordersDay = orderService.selectList(new MyEntityWrapper<Order>().gt("create_time", dayStartAndEnd.get("startTime")).lt("create_time", dayStartAndEnd.get("endTime")).eq("state", 2));
        BigDecimal orderTotalMoneyDay = new BigDecimal(0);
        for (Order orderD : ordersDay) {
        	orderTotalMoneyDay = orderTotalMoneyDay.add(orderD.getOrderPrice());
		}
        
       //昨日交易总额
        System.out.println("111"+orderService);
        List<Order> ordersLast=orderService.selectList(new MyEntityWrapper<Order>().gt("create_time", lastDayStartAndEnd.get("startTime")).lt("create_time", lastDayStartAndEnd.get("endTime")).eq("state", 2));
        BigDecimal orderTotalMoneyLast = new BigDecimal(0);
        for(Order orderE :ordersLast){
        	orderTotalMoneyLast=orderTotalMoneyLast.add(orderE.getOrderPrice());
        }
        
       //7天交易总额
        List<Order> orderSeven=orderService.selectList(new MyEntityWrapper<Order>().gt("create_time", sevenStartAndEnd.get("startTime")).lt("create_time", sevenStartAndEnd.get("endTime")).eq("state", 2));
        BigDecimal orderTotalMoneySeven = new BigDecimal(0);
        for(Order orderF :orderSeven){
        	orderTotalMoneySeven=orderTotalMoneySeven.add(orderF.getOrderPrice());
        }
      //本月交易总额
        List<Order> ordersMonth = orderService.selectList(new MyEntityWrapper<Order>().gt("create_time", mounthStartAndEnd.get("startTime")).lt("create_time", mounthStartAndEnd.get("endTime")).eq("state", 2));
        BigDecimal orderTotalMoneyMonth = new BigDecimal(0);
        for(Order orderG: ordersMonth){
        	orderTotalMoneyMonth=orderTotalMoneyMonth.add(orderG.getOrderPrice());
        }
      //全平台交易额
        List<Order> orders = orderService.selectList(new MyEntityWrapper<Order>().eq("state", 2));
        BigDecimal orderTotalMoney = new BigDecimal(0);
        for (Order orderH : orders) {
            orderTotalMoney = orderTotalMoney.add(orderH.getOrderPrice());
            }
       // 今日新增会员
 		Integer userDay = userService.selectCount(new MyEntityWrapper<User>()
 				.gt("publish_time", dayStartAndEnd.get("startTime")).lt("publish_time", dayStartAndEnd.get("endTime")));
 		// 昨日新增会员
 		Integer userLast = userService
 				.selectCount(new MyEntityWrapper<User>().gt("publish_time", lastDayStartAndEnd.get("startTime"))
 						.lt("publish_time", lastDayStartAndEnd.get("endTime")));
 		// 本月新增
 		Integer userMonth = userService
 				.selectCount(new MyEntityWrapper<User>().gt("publish_time", mounthStartAndEnd.get("startTime"))
 						.lt("publish_time", mounthStartAndEnd.get("endTime")));
 		// 会员总数
 		Integer userTotal = userService.selectCount(null);
 		
 		map.put("orderTotalMoney", orderTotalMoney);
		map.put("orderTotalNumDay", orderTotalNumDay);
		map.put("orderTotalMoneyDay", orderTotalMoneyDay);
		map.put("orderTotalMoneyLast", orderTotalMoneyLast);
		map.put("orderTotalMoneySeven", orderTotalMoneySeven);
		map.put("orderTotalMoneyMonth", orderTotalMoneyMonth);
		
		map.put("userDay", userDay);
		map.put("userLast", userLast);
		map.put("userMonth", userMonth);
		map.put("userTotal", userTotal);
        
        return new Result().setData(map);
		
	}
	
	@ApiOperation("根据条件查询展示相应的订单")
	@GetMapping(value="/allOrders")
	public Result allOrders(Page page, @RequestParam(required = false) Integer state,
	@RequestParam(required = false) String orderNum, @RequestParam(required = false) Integer payType,
	@RequestParam(required = false) String ccName,
	@RequestParam(required = false) String userPhone, @RequestParam(required = false) Date startTime,
	@RequestParam(required = false) Date endTime, @RequestParam(required = false) Integer isDel) {
		MyEntityWrapper<Order> ew = new MyEntityWrapper<>();
		ew.eq("t1.state", state);
		ew.like("t1.order_num", orderNum);
		ew.like("t2.phone", userPhone);
		ew.like("t3.name", ccName);
		ew.eq("t1.is_del", isDel);
		ew.eq("t1.pay_type", payType);
		
		if (startTime != null && !"".equals(startTime)) {
			ew.gt("t1.create_time", startTime);
		}
		if (endTime != null && !"".equals(endTime)) {
			ew.lt("t1.create_time", endTime);
		}
		if (startTime != null && !"".equals(startTime) && endTime != null && !"".equals(endTime)) {
			ew.between("t1.create_time", startTime, endTime);
		}
    	ew.orderBy("t1.create_time", false);	
	
    	System.out.println("ew:" + ew);
    	
		Page<OrderVo> orderVoPage = orderService.findOrderVo(page, ew);
		List<OrderVo> list = orderVoPage.getRecords();    
		return new Result().setData(list);
	}
	
	@ApiOperation("订单详情")
	@GetMapping(value="/orderDetails")
	public Result orderDetails(String orderNum){
		Order orderOne = orderService.selectOne(new MyEntityWrapper<Order>().eq("order_num", orderNum));
		return new Result().setData(orderOne);
	}
	
	/**
	 * 导出Excel
	 * @throws Exception 
	 */
	@ApiOperation("excel导出订单")
	@GetMapping(value="/orderExport")
	public void orderExport(HttpServletResponse response,Page page, @RequestParam(required = false) Integer state,
		@RequestParam(required = false) String orderNum, @RequestParam(required = false) Integer payType,
		@RequestParam(required = false) String ccName,
		@RequestParam(required = false) String userPhone, @RequestParam(required = false) Date startTime,
		@RequestParam(required = false) Date endTime, @RequestParam(required = false) Integer isDel) throws Exception {
			MyEntityWrapper<Order> ew = new MyEntityWrapper<>();
			ew.eq("t1.state", state);
			ew.like("t1.order_num", orderNum);
			ew.like("t2.phone", userPhone);
			ew.like("t3.name", ccName);
			ew.eq("t1.is_del", isDel);
			ew.eq("t1.pay_type", payType);
			
			if (startTime != null && !"".equals(startTime)) {
				ew.gt("t1.create_time", startTime);
			}
			if (endTime != null && !"".equals(endTime)) {
				ew.lt("t1.create_time", endTime);
			}
			if (startTime != null && !"".equals(startTime) && endTime != null && !"".equals(endTime)) {
				ew.between("t1.create_time", startTime, endTime);
			}
	    	ew.orderBy("t1.create_time", false);	
		
	    	System.out.println("ew:" + ew);
	    	
			Page<OrderVo> orderVoPage = orderService.findOrderVo(page, ew);
			List<OrderVo> list = orderVoPage.getRecords();    
			
			List<OrderExcel> orderList = new ArrayList<>();
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
			for (OrderVo re :list) {
				String statusD = "";
			    switch (re.getState()) {
				case 0:
					statusD="进行中";
					break;
				case 1:
					statusD="已完成";
					break;
				case 2:
					statusD="已支付";
					break;	
				}
			    String payTypeD = "";
			    if (re.getPayType() != null) {
					if (re.getPayType() == 0) {
						payTypeD = "支付宝";
					} else if (re.getPayType() == 1) {
						payTypeD = "微信";
					} else {
						payTypeD = "余额";
					}
				}
			    String createTimeD = "";
				String endTimeD = "";
				String payTimeD = "";
			   
				createTimeD=format.format(re.getCreateTime());
			    if(re.getEndTime()!=null){
			    	endTimeD=format.format(re.getEndTime());
			    }
			    if(re.getPayTime()!=null){
			    	payTimeD=format.format(re.getPayTime());
			    }
			    
			    orderList.add(new OrderExcel(
			    		re.getUserId(),
			    		re.getUser().getCarNumber(),
			    		re.getUser().getPhone(),
			    		re.getCouponId(),
			    		re.getOrderNum(),
			    		re.getOrderPrice(),
			    		re.getRealPay(),
			    		createTimeD,
			    		endTimeD,
			    		payTypeD,
			    		payTimeD,
			    		statusD,
			    		re.getCcId(),
			    		re.getParkinglot().getName(),
			    		re.getCwId()));
			}
			String nowTime = format.format(new Date());
			FileUtil.exportExcel(orderList, "停车订单列表", "停车订单列表",OrderExcel.class, "停车订单列表__" + nowTime + ".xls", response);
		}
}
