package com.part.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.part.entity.Coupon;
import com.part.entity.UserCoupon;
import com.part.service.CouponService;
import com.part.service.UserCouponService;
import com.part.utils.MyEntityWrapper;
import com.part.utils.Result;

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
@RequestMapping("/userCoupon")
public class UserCouponController {
	 @Autowired
	 private UserCouponService userCouponService;
	 @Autowired
	 private CouponService couponService;
	
	@ApiOperation("我的优惠券")
	@GetMapping(value="/myCoupon")
	public Result myCoupon(Integer userId){
		List<UserCoupon> myCoupon = userCouponService.selectList(new MyEntityWrapper<UserCoupon>().eq("user_id", userId));
		List allCoupon =new ArrayList();
		for (int i = 0; i < myCoupon.size(); i++) {
			Map coupon =new HashMap<>();
			Coupon couponOne = couponService.selectById(myCoupon.get(i).getCouponId());
			coupon.put("coupon", couponOne);
			coupon.put("myCoupon", myCoupon.get(i));
			allCoupon.add(coupon);
		}
		return new Result().setData(allCoupon);
	}
}
