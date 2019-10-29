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
 * @since 2019-10-17
 */
@TableName("p_order")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String orderNum;
	private BigDecimal orderPrice;
	private Date createTime;
	private Date endTime;
	private Date payTime;
    /**
     * 0---支付宝 1---微信 2---账户余额
     */
	private Integer payType;
    /**
     * 0- 进行中   1-已完成
     */
	private Integer state;
    /**
     * 0---未删除   1---已删除
     */
	private Integer isDel;
	private Integer userId;
	private Integer couponId;
	private BigDecimal realPay;
    /**
     * 停车场id
     */
	private Integer ccId;
    /**
     * 车位id
     */
	private Integer cwId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
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

	public BigDecimal getRealPay() {
		return realPay;
	}

	public void setRealPay(BigDecimal realPay) {
		this.realPay = realPay;
	}

	public Integer getCcId() {
		return ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}

	public Integer getCwId() {
		return cwId;
	}

	public void setCwId(Integer cwId) {
		this.cwId = cwId;
	}

	public static final String ID = "id";

	public static final String ORDER_NUM = "order_num";

	public static final String ORDER_PRICE = "order_price";

	public static final String CREATE_TIME = "create_time";

	public static final String END_TIME = "end_time";

	public static final String PAY_TIME = "pay_time";

	public static final String PAY_TYPE = "pay_type";

	public static final String STATE = "state";

	public static final String IS_DEL = "is_del";

	public static final String USER_ID = "user_id";

	public static final String COUPON_ID = "coupon_id";

	public static final String REAL_PAY = "real_pay";

	public static final String CC_ID = "cc_id";

	public static final String CW_ID = "cw_id";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Order{" +
			", id=" + id +
			", orderNum=" + orderNum +
			", orderPrice=" + orderPrice +
			", createTime=" + createTime +
			", endTime=" + endTime +
			", payTime=" + payTime +
			", payType=" + payType +
			", state=" + state +
			", isDel=" + isDel +
			", userId=" + userId +
			", couponId=" + couponId +
			", realPay=" + realPay +
			", ccId=" + ccId +
			", cwId=" + cwId +
			"}";
	}
}
