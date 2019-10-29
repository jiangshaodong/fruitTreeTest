package com.part.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.part.entity.Partingset;
import com.part.service.PartingsetService;
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
 * @since 2019-10-18
 */
@RestController
@RequestMapping("/partingset")
public class PartingsetController {
	@Autowired
	private PartingsetService partingsetService;
	
	@ApiOperation("显示车位")
	@ApiImplicitParams(value = {
            @ApiImplicitParam(name = "ccId", value = "停车场id",
                    required = true, paramType = "query", dataType = "int")
    })
	@GetMapping("showAllPartingset")
	public Result showAllPartingset(Integer ccId){
		List<Partingset> allPartingset = partingsetService.selectList(new MyEntityWrapper<Partingset>().eq("cc_id", ccId));
		return new Result().setData(allPartingset);}
}
