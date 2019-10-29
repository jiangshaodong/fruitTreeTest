package com.part.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
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
 * @since 2019-10-17
 */
@TableName("p_user_coupon")
public class UserCoupon extends Model<UserCoupon> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer userId;
	private Integer couponId;
    /**
     * 0已使用 1未使用  2未开始  3已结束
     */
	private Integer state;
	private Date publishTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public static final String ID = "id";

	public static final String USER_ID = "user_id";

	public static final String COUPON_ID = "coupon_id";

	public static final String STATE = "state";

	public static final String PUBLISH_TIME = "publish_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserCoupon{" +
			", id=" + id +
			", userId=" + userId +
			", couponId=" + couponId +
			", state=" + state +
			", publishTime=" + publishTime +
			"}";
	}
}
