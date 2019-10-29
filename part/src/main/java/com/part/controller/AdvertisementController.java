package com.part.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.part.entity.Advertisement;
import com.part.service.AdvertisementService;
import com.part.utils.MyEntityWrapper;
import com.part.utils.Result;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiangSD
 * @since 2019-10-23
 */
@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {
	@Autowired
	private AdvertisementService advertisementService;
	
	@ApiOperation("新增广告")
	@ApiImplicitParams(value = {
            @ApiImplicitParam(name = "img", value = "广告图片",
                    required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "url", value = "路径",
                    required = true, paramType = "query", dataType = "string")
    })
	@PostMapping(value="/addAdvertisment")
	private Result addAdvertisment(Advertisement advertisement){
		advertisementService.insert(advertisement);
		return new Result().setMsg("新增成功");
	}
	
	@ApiOperation("展示所有的广告")
	@GetMapping(value="/showAdvertisment")
	private Result showAdvertisment(){
		List<Advertisement> allAdvertisment = advertisementService.selectList(new MyEntityWrapper<Advertisement>().eq("flag", 1));
		return new Result().setData(allAdvertisment);
	}
	
	@ApiOperation("删除广告")
	@PostMapping(value="/delAdvertisment")
	private Result delAdvertisment(Integer id){
		Advertisement advertisement = advertisementService.selectById(id);
		advertisement.setFlag(0);
		advertisement.updateById();
		return new Result().setMsg("删除成功");
	}
	
	
}
