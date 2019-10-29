package com.part.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiangSD
 * @since 2019-10-17
 */
@TableName("p_sys_use")
public class SysUse extends Model<SysUse> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String userName;
	private String password;
    /**
     * 1:总平台管理员   2：分管理员
     */
	private Integer role;
	private Integer canUse;
	private String headImg;
	private String nickName;
	private String rongyunToken;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getCanUse() {
		return canUse;
	}

	public void setCanUse(Integer canUse) {
		this.canUse = canUse;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRongyunToken() {
		return rongyunToken;
	}

	public void setRongyunToken(String rongyunToken) {
		this.rongyunToken = rongyunToken;
	}

	public static final String ID = "id";

	public static final String USER_NAME = "user_name";

	public static final String PASSWORD = "password";

	public static final String ROLE = "role";

	public static final String CAN_USE = "can_use";

	public static final String HEAD_IMG = "head_img";

	public static final String NICK_NAME = "nick_name";

	public static final String RONGYUN_TOKEN = "rongyun_token";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysUse{" +
			", id=" + id +
			", userName=" + userName +
			", password=" + password +
			", role=" + role +
			", canUse=" + canUse +
			", headImg=" + headImg +
			", nickName=" + nickName +
			", rongyunToken=" + rongyunToken +
			"}";
	}
}
