package com.part.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
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
 * @since 2019-10-21
 */
@TableName("p_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String phone;
	private String password;
    /**
     * 信誉分
     */
	private Integer reputationMark;
    /**
     * 车牌号
     */
	private String carNumber;
    /**
     * 头像
     */
	private String headImg;
	private String nickName;
    /**
     * 1---男   2---女
     */
	private Integer gender;
    /**
     * 推荐人id
     */
	private Integer refereeId;
	private String qqToken;
	private String wxToken;
    /**
     * 账户余额
     */
	private BigDecimal account;
    /**
     * 注册时间
     */
	private Date publishTime;
    /**
     * 最后登录时间
     */
	private Date lastLoginDate;
    /**
     * 1---可用  0---不可用
     */
	private Integer canUse;
	private String rongyunToken;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getReputationMark() {
		return reputationMark;
	}

	public void setReputationMark(Integer reputationMark) {
		this.reputationMark = reputationMark;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
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

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getRefereeId() {
		return refereeId;
	}

	public void setRefereeId(Integer refereeId) {
		this.refereeId = refereeId;
	}

	public String getQqToken() {
		return qqToken;
	}

	public void setQqToken(String qqToken) {
		this.qqToken = qqToken;
	}

	public String getWxToken() {
		return wxToken;
	}

	public void setWxToken(String wxToken) {
		this.wxToken = wxToken;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Integer getCanUse() {
		return canUse;
	}

	public void setCanUse(Integer canUse) {
		this.canUse = canUse;
	}

	public String getRongyunToken() {
		return rongyunToken;
	}

	public void setRongyunToken(String rongyunToken) {
		this.rongyunToken = rongyunToken;
	}

	public static final String ID = "id";

	public static final String PHONE = "phone";

	public static final String PASSWORD = "password";

	public static final String REPUTATION_MARK = "reputation_mark";

	public static final String CAR_NUMBER = "car_number";

	public static final String HEAD_IMG = "head_img";

	public static final String NICK_NAME = "nick_name";

	public static final String GENDER = "gender";

	public static final String REFEREE_ID = "referee_id";

	public static final String QQ_TOKEN = "qq_token";

	public static final String WX_TOKEN = "wx_token";

	public static final String ACCOUNT = "account";

	public static final String PUBLISH_TIME = "publish_time";

	public static final String LAST_LOGIN_DATE = "last_login_date";

	public static final String CAN_USE = "can_use";

	public static final String RONGYUN_TOKEN = "rongyun_token";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "User{" +
			", id=" + id +
			", phone=" + phone +
			", password=" + password +
			", reputationMark=" + reputationMark +
			", carNumber=" + carNumber +
			", headImg=" + headImg +
			", nickName=" + nickName +
			", gender=" + gender +
			", refereeId=" + refereeId +
			", qqToken=" + qqToken +
			", wxToken=" + wxToken +
			", account=" + account +
			", publishTime=" + publishTime +
			", lastLoginDate=" + lastLoginDate +
			", canUse=" + canUse +
			", rongyunToken=" + rongyunToken +
			"}";
	}
}
