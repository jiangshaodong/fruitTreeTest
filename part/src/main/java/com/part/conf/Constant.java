package com.part.conf;

public class Constant {
    /**
     * 数据请求返回码
     */
    public static final long RESCODE_SUCCESS = 1000;				//成功(默认success)
    public static final long RESCODE_SUCCESS_MSG = 1001;	        //成功(msg)
    public static final long RESCODE_SUCCESS_DATA = 1002;	        //成功(默认success 自定义data)
    public static final long RESCODE_SUCCESS_MSG_DATA = 1003;	//成功(自定义msg data)
    public static final long RESCODE_EXCEPTION = 2000;			//请求抛出异常
    public static final long RESCODE_NOLOGIN = 2001;				//未登陆状态
    public static final long RESCODE_LOGIN_OVERDUE = 2002;		//登陆过期
    public static final long RESCODE_NOFOUND = 2003;		        //没有找到该用户
    public static final long RESCODE_NOAUTH = 2004;				//无操作权限
    public static final long RESCODE_NOEXIST = 2005;				//查询结果为空

    /**
     * jwt
     */
    public static final String JWT_ID = "jwt";
    public static final String JWT_SECRET = "5bc9a23bb5994ef8565ed1e657192fee";
    public static final String JWT_HEADER = "ACCESS_TOKEN";
    public static final int JWT_TTL = 60*60*1000;  //millisecond
    public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
    public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond
}
