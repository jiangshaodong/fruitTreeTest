package com.part.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.part.entity.SysUse;
import com.part.entity.User;
import com.part.service.SysUseService;
import com.part.service.UserService;
import com.part.utils.MD5Util;
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
@RequestMapping("/sysUse")
public class SysUseController {
	 
	@Autowired
	private SysUseService sysUseService;
	@Autowired
	private UserService userService;
	
	@ApiOperation("后台管理员登录")
	@PostMapping(value="/sysLogin")
	public ModelAndView sysLogin(String username, String password, HttpSession session, ModelAndView mv, HttpServletResponse response){
		SysUse sysUse = sysUseService.selectOne(new MyEntityWrapper<SysUse>().eq("user_name", username));
		mv.setViewName("login");
		if(sysUse !=null){
			if(sysUse.getPassword().equals(MD5Util.getMD5(password))){
				if(sysUse.getCanUse()==0){
                    mv.addObject("msg","用户已被禁用");
                }
				else{
					session.setAttribute("user",sysUse);
                    session.setAttribute("adminId",sysUse.getId());
                    session.setAttribute("adminRole",sysUse.getRole());
                    session.setAttribute("adminUserName",sysUse.getUserName());
                    Cookie ck = new Cookie("userName",sysUse.getUserName());
                    Cookie ck3 = new Cookie("name",sysUse.getNickName());
                    Cookie ck1 = new Cookie("userRole",sysUse.getRole().toString());
                    response.addCookie(ck);
                    response.addCookie(ck1);
                    response.addCookie(ck3);
                    try {
						response.sendRedirect("index.html");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else {
                mv.addObject("msg","密码错误");
            }
		}
		else {
            mv.addObject("msg","没有找到该用户");
        }
		return mv;
	}
	
	@ApiOperation("注销")
	@GetMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "redirect:login.html";
    }
	
	@ApiOperation("后台查看所有用户")
	@GetMapping(value="/showAllUsers")
	private Result showAllUsers(){
		List<User> allUsers = userService.selectList(new MyEntityWrapper<>());
		return new Result().setData(allUsers);
	}
	
	@ApiOperation("后台屏蔽用户")
	@GetMapping(value="/delUser")
	private Result delUser(Integer userId){
		User user = userService.selectById(userId);
		user.setCanUse(0);
		user.updateById();
		return new Result().setMsg("屏蔽成功");
	}
	
	@ApiOperation("后台开启用户")
	@GetMapping(value="/openUser")
	private Result openUser(Integer userId){
		User user = userService.selectById(userId);
		user.setCanUse(1);
		user.updateById();
		return new Result().setMsg("开启成功");
	}
}
