package com.part.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.part.conf.Constant;
import com.part.entity.Coupon;
import com.part.entity.Order;
import com.part.entity.User;
import com.part.entity.UserAccountLog;
import com.part.entity.vo.OrderVo;
import com.part.service.CouponService;
import com.part.service.OrderService;
import com.part.service.UserAccountLogService;
import com.part.service.UserService;
import com.part.utils.MyEntityWrapper;
import com.part.utils.Result;
import com.part.utils.alipay.AliPayUtil;

import io.swagger.annotations.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author JiangSD
 * @date 2019-10-24 15:32
 */
@Api(tags = {"api_pay"})
@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private UserAccountLogService userAccountLogService;

    @ApiOperation("获取支付宝签名")
    @PostMapping("/alipay")
    @Transactional
    public Result alipay(@ApiParam(value = "订单id", required = true) @RequestParam String orderId,
    		@ApiParam(value = "订单真实价格", required = true) @RequestParam BigDecimal realPay){
    	String notifyUrl = "http://*******/pay/aliPayNotify";
    	Order order = orderService.selectById(orderId);
    	try {
			AlipayTradeAppPayResponse response = AliPayUtil.getPayResponse(order.getOrderNum(), realPay.toString(), notifyUrl);
			return new Result().success().setCode(Constant.RESCODE_SUCCESS_DATA).setData(response.getBody());
		} catch (AlipayApiException e) {
			return new Result().erro("获取签名失败");
		}
    }
    
    @RequestMapping("/aliPayNotify")
    public String aliPayNotify(HttpServletRequest request) {
    	System.out.println("支付回调执行了！" + new Date().toString());
    	Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        String orderNum = params.get("out_trade_no");//订单号
        MyEntityWrapper<Order> ew = new MyEntityWrapper<>();
        Order orderOne = orderService.selectOne(ew);
        if (!StringUtils.isEmpty(orderOne.getPayType())) {
            System.out.println("订单已支付");
            return null;
        }
        String trade_status = "";
        if (params.get("trade_status") == null) {
            trade_status = request.getParameter("trade_status");
        } else {
            trade_status = params.get("trade_status");
        }
        if (trade_status.equals("TRADE_FINISHED")) {
            System.out.println(trade_status);
            return "fail";
        }
        orderOne.setState(2);
        orderOne.setPayType(0);
        orderOne.setPayTime(new Date());
        orderOne.updateById();
        UserAccountLog uerAccountLog =new UserAccountLog();
        uerAccountLog.setMoney(orderOne.getRealPay());
        uerAccountLog.setLog("支付宝支付");
        uerAccountLog.setIsAdd(0);
        uerAccountLog.setPayTime(orderOne.getPayTime());
        uerAccountLog.setPayType(1);
        uerAccountLog.setIsPay(1);
        return "success";
    	
    }
    
    @ApiOperation("使用余额支付")
    @PostMapping("/payForAccount")
    @Transactional
    public Result payForAccount(@ApiParam(value = "订单id", required = true) @RequestParam Integer orderId,
                                @ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
                                @ApiParam(value = "订单真实价格", required = true) @RequestParam BigDecimal realPay) {
            Order order = orderService.selectById(orderId);
            Coupon coupon = couponService.selectById(order.getCouponId());
      //      BigDecimal realOrderPrice = order.getRealPay();//订单价格
           
            User user = userService.selectById(userId);
            if (realPay.compareTo(user.getAccount()) == 1) {
                return new Result().erro("余额不足，请充值或更换其他支付方式！");
            }
           //余额减去订单费用
            user.setAccount(user.getAccount().subtract(realPay));
            user.updateById();
            //订单添加支付时间，支付方式，改变订单状态
            order.setPayTime(new Date());
            order.setPayType(2);
            order.setState(2);
            order.updateById();
            
            UserAccountLog userAccountLog=new UserAccountLog();
            userAccountLog.setMoney(realPay);
            userAccountLog.setLog("余额支付");
            userAccountLog.setIsAdd(0);
            userAccountLog.setPayTime(new Date());
            userAccountLog.setIsPay(1);
            userAccountLogService.insert(userAccountLog);
            
           return new Result().setMsg("支付成功");
  }
} 

