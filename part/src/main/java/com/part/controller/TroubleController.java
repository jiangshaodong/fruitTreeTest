package com.part.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.part.entity.Trouble;
import com.part.service.TroubleService;
import com.part.utils.Result;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiangSD
 * @since 2019-10-21
 */
@RestController
@RequestMapping("/trouble")
public class TroubleController {
	 
	@Autowired
	private TroubleService troubleService;
	
	@ApiOperation("故障申报")
	@PostMapping(value="/troubleApply")
	public Result troubleApply(Trouble trouble){
		troubleService.insert(trouble);
		return new Result().success();
	}
}
