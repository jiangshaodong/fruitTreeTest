package com.part.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.part.entity.ChargingStandard;
import com.part.entity.Order;
import com.part.entity.Parkinglot;
import com.part.entity.Partingset;
import com.part.service.ChargingStandardService;
import com.part.service.ParkinglotService;
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
@RequestMapping("/parkinglot")
public class ParkinglotController {
	@Autowired
	private ParkinglotService parkinglotService;
	@Autowired
	private ChargingStandardService chargingStandardService;
	@Autowired
	private PartingsetService partingsetService;
	
	
	@ApiOperation("展示所有停车场")
	@GetMapping(value = "/showAll")
	public Result showAll(){
		List<Parkinglot> allParkinglot = parkinglotService.selectList(new MyEntityWrapper<Parkinglot>());
		List allPatking =new ArrayList<>();
		for (int i = 0; i < allParkinglot.size(); i++) {
			Map parking =new HashMap<>();
			List<ChargingStandard> chargingStandard = chargingStandardService.selectList(new MyEntityWrapper<ChargingStandard>().eq("id", allParkinglot.get(i).getId()));
			parking.put("allParkinglot", allParkinglot.get(i));
			parking.put("ChargingStandard", chargingStandard);
			allPatking.add(parking);
		}
		return new Result().setData(allPatking);
	}
	
	@ApiOperation("单个停车场详情")
	@ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "停车场id",
                    required = true, paramType = "query", dataType = "int"),
    })
	@GetMapping(value = "/showOne")
	public Result showOne(Integer id){
		Map parking =new HashMap<>();
		Parkinglot parkinglot = parkinglotService.selectById(id);
		List<Partingset> partingsetList = partingsetService.selectList(new MyEntityWrapper<Partingset>().eq("cc_id", id));
		parking.put("parkinglot", parkinglot);
		parking.put("partingsetList", partingsetList);
		return new Result().setData(parking);
	}
	
}
