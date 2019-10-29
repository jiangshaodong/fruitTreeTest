package com.part.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.part.entity.Notice;
import com.part.service.NoticeService;
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
 * @since 2019-10-17
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@ApiImplicitParams(value = {
            @ApiImplicitParam(name = "title", value = "公告标题",
                    required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "content", value = "公告内容",
                    required = true, paramType = "query", dataType = "string")
    })
	@ApiOperation("新增公告")
	@PostMapping(value="/addNotice")
	public Result addNotice(Notice notice){
		noticeService.insert(notice);
		return new Result().success();
	}
	
	@ApiOperation("删除公告")
	@PostMapping(value="/delNotice")
	public Result delNotice(Integer noticeId){
		Notice notice = noticeService.selectById(noticeId);
		notice.setCanUse(0);
		notice.updateById();
		return new Result().success();
	}
	
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "id", value = "公告id",
                    required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "title", value = "公告标题",
                    required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "content", value = "公告内容",
                    required = true, paramType = "query", dataType = "string")
    })
	@ApiOperation("编辑公告")
    @PostMapping(value="/editNotice")
	public Result editNotice(Notice notice){
		Notice noticeOne = noticeService.selectById(notice.getId());
		noticeOne.setTitle(notice.getTitle());
		noticeOne.setContent(notice.getContent());
		noticeOne.updateById();
		return new Result().success();
		
	}
	
}
