package com.part.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.part.entity.Banner;
import com.part.service.BannerService;
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
 * @since 2019-10-21
 */
@RestController
@RequestMapping("/banner")
public class BannerController {
	
	@Autowired
	private BannerService bannerService;
	
	@ApiOperation("显示所有轮播图")
	@GetMapping(value="/allBanner")
	public Result allBanner(){
		List<Banner> allBanner = bannerService.selectList(new MyEntityWrapper<Banner>().eq("is_del", 1));
		return new Result().setData(allBanner);
	}
	
	@ApiOperation("删除轮播图")
	
	@GetMapping(value="/delBanner")
	public Result delBanner(Integer bannerId){
		Banner banner = bannerService.selectById(bannerId);
		banner.setIsDel(0);
		banner.updateById();
		return new Result().success();
	}
	
	@ApiOperation("新增轮播图")
	@PostMapping(value="/addBanner")
	public Result delBanner(Banner banner){
		bannerService.insert(banner);
		return new Result().success();
	}
	
	@ApiOperation("编辑轮播图级别")
	@ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "轮播图id",
                    required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rank", value = "级别",
                    required = true, paramType = "query", dataType = "int")
    })
	@PostMapping(value="/editBanner")
		public Result editBanner(Banner banner){
		Banner oneBanner = bannerService.selectById(banner.getId());
		oneBanner.setRank(banner.getRank());
		banner.updateById();
		return new Result().success();
		
}
}