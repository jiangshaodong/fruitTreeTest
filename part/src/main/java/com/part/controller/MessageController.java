package com.part.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.part.utils.Result;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api-message")
public class MessageController {

	 @ApiOperation("停车发送系统消息")
	 @PostMapping(value="/firstMessage")
	 public Result firstMessage(){
		 
		 return new Result().setMsg("您已经成功停车");
		 
	 }
	 
	 @ApiOperation("离开发送系统消息")
	 @PostMapping(value="/endMessage")
	 public Result endMessage(){
		 
		 return new Result().setMsg("您已经离开，请您及时支付订单费用");
		 
	 }
	 
}
