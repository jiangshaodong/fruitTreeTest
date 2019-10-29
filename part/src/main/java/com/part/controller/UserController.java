package com.part.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.part.conf.Constant;
import com.part.entity.Coupon;
import com.part.entity.Dictionary;
import com.part.entity.User;
import com.part.entity.UserCoupon;
import com.part.jwt.AccessToken;
import com.part.jwt.JwtParam;
import com.part.service.CouponService;
import com.part.service.DictionaryService;
import com.part.service.UserCouponService;
import com.part.service.UserService;
import com.part.utils.JwtHelper;
import com.part.utils.MD5Util;
import com.part.utils.MessageUtil;
import com.part.utils.MyEntityWrapper;
import com.part.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiangSD
 * @since 2019-10-16
 */
@Api(tags = {"api_user"})
@RestController
@RequestMapping("/pUser")
public class UserController {
	@Autowired
    private UserService userService;
	@Autowired
    private DictionaryService dictionaryService;
	@Autowired
    private JwtHelper jwtHelper;
	@Autowired
	private UserCouponService userCouponService;
	@Autowired
	private CouponService couponService;
	
	
	@ApiOperation("注册短信发送")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号",required = true,paramType ="query", dataType = "string"),
    })
    @GetMapping("/sendByRegister")
    public Result sendByRegister(@RequestParam String phone, HttpSession session){
        try {
            User user = userService.selectOne(new EntityWrapper<User>().eq(User.PHONE,phone));
            if(user != null){
                return new Result().erro("该用户已注册");
            }
            String code = (Math.random() + "").substring(2, 6);
            SendSmsResponse sendSmsResponse = MessageUtil.sendSms(phone, code );
            String code1 = sendSmsResponse.getCode();
            session.setAttribute(phone+"register0",code);
            if ("OK".equalsIgnoreCase(code1)){
                session.setMaxInactiveInterval(60*60*5*1000);
                return new Result().success("发送成功").setData(code);
            }else {
                return new Result().erro("发送失败");
            }
        } catch (ClientException e) {
            e.printStackTrace();
            return new Result().erro("发送失败");
        }
	}
	
	@ApiOperation("验证码登录发送短信")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号",required = true,paramType ="query", dataType = "string"),
    })
    @GetMapping("/sendByCodeLogin")
    public Result sendByCodeLogin(@RequestParam String phone, HttpSession session){
        try {
            User user = userService.selectOne(new EntityWrapper<User>().eq(User.PHONE,phone));
            if(user == null){
                return new Result().erro("该用户尚未注册");
            }
            String code = (Math.random() + "").substring(2, 6);
            SendSmsResponse sendSmsResponse = MessageUtil.sendSms(phone, code );
            String code1 = sendSmsResponse.getCode();
            if ("OK".equalsIgnoreCase(code1)){
                session.setAttribute(phone+"loginCode0",code);
                session.setMaxInactiveInterval(60*60*5*1000);
                return new Result().success("发送成功").setData(code);
            }else {
                return new Result().erro("发送失败");
            }
        } catch (ClientException e) {
            e.printStackTrace();
            return new Result().erro("发送失败");
        }
    }
	
	@ApiOperation("找回密码短信发送")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号",required = true,paramType ="query", dataType = "string"),
    })
    @GetMapping("/sendByFindPassword")
    public Result sendByFind(@RequestParam String phone, HttpSession session){
        try {
            User user = userService.selectOne(new EntityWrapper<User>().eq(User.PHONE,phone));
            if(user == null){
                return new Result().erro("该用户未注册");
            }
            String code = (Math.random() + "").substring(2, 6);
            SendSmsResponse sendSmsResponse = MessageUtil.sendSms(phone, code );
            String code1 = sendSmsResponse.getCode();
            System.out.println(code1);
            if ("OK".equalsIgnoreCase(code1)){
                session.setAttribute(phone+"find0",code);
                session.setMaxInactiveInterval(60*60*5*1000);
                return new Result().success("发送成功").setData(code);
            }else {
                return new Result().erro("发送失败");
            }
        } catch (ClientException e) {
            e.printStackTrace();
            return new Result().erro("发送失败");
        }
    } 
	
	@ApiOperation("用户注册")
	@ApiImplicitParams(value = {
            @ApiImplicitParam(name = "phone", value = "手机号",
                    required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码",
                    required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "code", value = "验证码",
                    required = true, paramType = "query", dataType = "string")
    })
	@GetMapping("/register")
	public Result register(@ModelAttribute User user,@RequestParam String code, HttpSession session) throws Exception {
		Result result = new Result();
		String realCode =(String) session.getAttribute(user.getPhone()+"register0");
		if(realCode !=null){
			if(code.equals(realCode)){
				String phone = user.getPhone();
				user.setPassword(MD5Util.getMD5(user.getPassword()));
				user.setCanUse(1);
				//获取默认头像
                Dictionary dictionaryImg = dictionaryService.selectOne(new EntityWrapper<Dictionary>().eq("code","user_default_img"));
                user.setHeadImg(dictionaryImg.getValue());
                //获取默认昵称
                Dictionary dictionaryName = dictionaryService.selectOne(new EntityWrapper<Dictionary>().eq("code","user_default_name"));
                user.setNickName(dictionaryName.getValue()+phone.substring(phone.length()- 4));
                userService.insert(user);
                JwtParam jwtParam = new JwtParam();
                jwtParam.setUserId(user.getId());
                jwtParam.setLoginTime(new Date());
                jwtParam.setLoginType(3);
                String subject = JwtHelper.generalSubject(jwtParam);
                String jwt = jwtHelper.createJWT(Constant.JWT_ID, subject);
                AccessToken accessToken = new AccessToken();
                accessToken.setAccess_id(user.getId());
                accessToken.setAccess_token(jwt);
                accessToken.setAccess_phone(user.getPhone());
                Map<String,Object> p = new HashMap<>();
                p.put("userId",user.getId());
                result.setData(p);
                result.success("注册成功",accessToken);
			}
		}
		return result;
	}
	
	@ApiOperation("用户账号密码登录")
	@PostMapping("/loginBypassword")
	public Result login(@RequestParam @ApiParam(value = "手机号", required = true) String phone, @RequestParam @ApiParam(value = "密码", required = true) String password, HttpServletRequest request) throws Exception{
		Result result = new Result();
		User user = userService.selectOne(new EntityWrapper<User>().eq("phone", phone));
		if(user!=null){
			if(user.getCanUse()==0){
				result.setCode((long)2002);
				result.setMsg("账号不可用，如有疑问，请联系工作人员");
			}
			else if(MD5Util.getMD5(password).equals(user.getPassword())){
				user.setLastLoginDate(new Date());
				user.updateById();
				Date loginTime = new Date();
                JwtParam jwtParam = new JwtParam();
                jwtParam.setUserId(user.getId());
                jwtParam.setLoginTime(loginTime);
                jwtParam.setLoginType(3);
                String subject = JwtHelper.generalSubject(jwtParam);
                String jwt = jwtHelper.createJWT(Constant.JWT_ID, subject);
                AccessToken accessToken = new AccessToken();
                accessToken.setAccess_id(user.getId());
                accessToken.setAccess_token(jwt);
                Map<String,Object> p = new HashMap<>();
                p.put("userId",user.getId());
                result.setData(p);
                result.success("登录成功",accessToken);
			}
			else {
                result.erro("密码错误");
            }
		}
		else {
            result.erro("该用户不存在");
        }
		return result;
	}
	
	@ApiOperation("用户验证码登录")
	@PostMapping("/loginByCode")
	public Result loginByCode(@RequestParam @ApiParam(value = "手机号", required = true) String phone, @RequestParam @ApiParam(value = "CODE", required = true) String code, HttpServletRequest request,HttpSession session) throws Exception {
		Result result = new Result();
		User user = userService.selectOne(new EntityWrapper<User>().eq("phone", phone));
		if(user!=null){
			String realCode =(String) session.getAttribute(user.getPhone() + "loginCode0");
			if(user.getCanUse()==0){
				result.setCode((long)2002);
				result.setMsg("账号不可用，如有疑问，请联系工作人员");
			}
			else if(realCode!=null){
				if(code.equals(realCode)){
				user.setLastLoginDate(new Date());
				user.updateById();
				Date loginTime = new Date();
                JwtParam jwtParam = new JwtParam();
                jwtParam.setUserId(user.getId());
                jwtParam.setLoginTime(loginTime);
                jwtParam.setLoginType(3);
                String subject = JwtHelper.generalSubject(jwtParam);
                String jwt = jwtHelper.createJWT(Constant.JWT_ID, subject);
                AccessToken accessToken = new AccessToken();
                accessToken.setAccess_id(user.getId());
                accessToken.setAccess_token(jwt);
                accessToken.setAccess_phone(user.getPhone());
                Map<String,Object> p = new HashMap<>();
                p.put("userId",user.getId());
                result.setData(p);
                result.success("登录成功",accessToken);
            }else {
                return new Result().erro("验证码错误");
            }
        }else {
            result.erro("验证码失效");
        }
    }else {
        result.erro("该用户不存在");
    }
    return result;
}
	@ApiOperation("用户信息修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carNumber", value = "车牌号",paramType ="query", dataType = "string"),
            @ApiImplicitParam(name = "headImg", value = "头像",paramType ="query", dataType = "string"),
            @ApiImplicitParam(name = "gender", value = "性别",paramType ="query", dataType = "int"),
            @ApiImplicitParam(name = "nickName", value = "昵称",paramType ="query", dataType = "string"),
    })
    @PostMapping("/edit")
    public Result edit(@ModelAttribute User user,Integer userId){
        if(userId!=null){
            user.setId(userId);      
        }
        user.updateById();
        return new Result().success();
    }
	
	@ApiOperation("查看用户资料")
    @GetMapping("/getInfo")
    public Result getInfo(Integer userId){
		Map userInfo =new HashMap<>();
        User user = userService.selectById(userId);
        List<UserCoupon> userCoupon = userCouponService.selectList(new MyEntityWrapper<UserCoupon>().eq("user_id", userId));
        List coupon =new ArrayList<>();
        for (int i = 0; i < userCoupon.size(); i++) {
        	Coupon couponOne = couponService.selectById(userCoupon.get(i).getCouponId());
        	coupon.add(couponOne);
		}
        userInfo.put("user", user);
        userInfo.put("userCoupon", userCoupon);
        userInfo.put("coupon", coupon);
        return new Result().success(userInfo);
    }
	
	@ApiOperation("根据手机验证码修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号",required = true,paramType ="query", dataType = "string"),
            @ApiImplicitParam(name = "code", value = "验证码",required = true,paramType ="query", dataType = "string"),
            @ApiImplicitParam(name = "password", value = "新密码",required = true,paramType ="query", dataType = "string"),
    })
    @PostMapping("/editPassword")
    public Result editPassword(@RequestParam String phone,@RequestParam String code,@RequestParam String password,HttpSession session){
        String realCode = (String) session.getAttribute(phone + "find0");
        if(realCode != null) {
            if (code.equals(realCode)) {
                User user = userService.selectOne(new EntityWrapper<User>().eq(User.PHONE, phone));
                user.setPassword(MD5Util.getMD5(password));
                user.updateById();
            }else {
                return new Result().erro("验证码失效");
            }
        }else {
            return new Result().erro("验证码过期");
        }
        return new Result().success();
    }
	
	@ApiOperation("根据旧密码修改新密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号",required = true,paramType ="query", dataType = "string"),
            @ApiImplicitParam(name = "password", value = "旧密码",required = true,paramType ="query", dataType = "string"),
            @ApiImplicitParam(name = "newPassword", value = "新密码",required = true,paramType ="query", dataType = "string"),
    })
    @PostMapping("/editPasswordByOld")
    public Result editPasswordByOld(@RequestParam String phone,@RequestParam String password,@RequestParam String newPassword){
        User user = userService.selectOne(new EntityWrapper<User>().eq(User.PHONE, phone));
        if(user != null){
            if(user.getPassword().equals(MD5Util.getMD5(password))){
                user.setPassword(MD5Util.getMD5(newPassword));
                user.updateById();
                return new Result().success();
            }else {
                return new Result().erro("旧密码错误");
            }
        }else {
            return new Result().erro("没有找到该用户");
        }
    }
	
	@ApiOperation("绑定账户")
    @PostMapping("/bindAccount")
	public Result bindAccount(@ApiParam("第三方唯一标识符")@RequestParam String token, @RequestParam Integer tokenType, @ApiParam("手机号")@RequestParam String phone , @ApiParam("密码")@RequestParam String password) throws Exception {
		//tokenType  1==微信  0==QQ
		System.out.println("绑定账户"+token+tokenType+phone+password);
		Result result = new Result();
		User user = userService.selectOne(new EntityWrapper<User>()
                .eq(User.PHONE, phone));
		if(user !=null){
            if(user.getPassword().equals(MD5Util.getMD5(password))){
                if(tokenType==1) {
                    if (StringUtils.isEmpty(user.getWxToken())) {
                        user.setWxToken(token);
                    }
                }else if(tokenType==0) {
                    if (StringUtils.isEmpty(user.getQqToken())) {
                        user.setQqToken(token);
                    }
                }
                user.updateById();
                Date loginTime = new Date();
                JwtParam jwtParam = new JwtParam();
                jwtParam.setUserId(user.getId());
                jwtParam.setLoginTime(loginTime);
                jwtParam.setLoginType(3);
                String subject = JwtHelper.generalSubject(jwtParam);
                String jwt = jwtHelper.createJWT(Constant.JWT_ID, subject);
                AccessToken accessToken = new AccessToken();
                accessToken.setAccess_id(user.getId());
                accessToken.setAccess_token(jwt);
                Map<String,Object> p = new HashMap<>();
                p.put("userId",user.getId());
                result.setData(p);
                result.success("登录成功",accessToken);
            }else {
                result.erro("密码错误");
            }
		}
		return null;
}
	
	@ApiOperation("使用第三方唯一标识符登陆,微信---QQ")
    @PostMapping("/loginByToken")
    public Result loginByToken(@ApiParam("第三方唯一标识符")@RequestParam String token, Integer tokenType, HttpServletRequest request)throws Exception {
       System.out.println("使用第三方唯一标识符登陆"+token+request);
    	User user = null;
        Result result = new Result();
        if(tokenType==0){
            user = userService.selectOne(new EntityWrapper<User>()
                    .eq("qq_token", token));
        }else if(tokenType==1){
            user = userService.selectOne(new EntityWrapper<User>()
                    .eq("wx_token", token));
        }
        if(user != null){
            Date loginTime = new Date();
            JwtParam jwtParam = new JwtParam();
            jwtParam.setUserId(user.getId());
            jwtParam.setLoginTime(loginTime);
            jwtParam.setLoginType(3);
            user.setLastLoginDate(loginTime);
            userService.updateById(user);
            String subject = JwtHelper.generalSubject(jwtParam);
            String jwt = jwtHelper.createJWT(Constant.JWT_ID, subject);
            AccessToken accessToken = new AccessToken();
            accessToken.setAccess_id(user.getId());
            accessToken.setAccess_token(jwt);
            Map<String,Object> p = new HashMap<>();
            p.put("userId",user.getId());
            result.setData(p);
            result.success("登录成功",accessToken);
        }else {
            result.erro("您还没有绑定第三方登陆");
        }
        return result;
    }
}
