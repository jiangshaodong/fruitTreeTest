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
@TableName("p_coupon")
public class Coupon extends Model<Coupon> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 满足价格
     */
	private BigDecimal fullPrice;
    /**
     * 优惠价格
     */
	private BigDecimal discount;
	private Date startTime;
	private Date endTime;
	private Integer num;
	private Integer isDel;
	private Date publishTime;
	private BigDecimal integral;
    /**
     * 优惠券名称
     */
	private String couponName;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getFullPrice() {
		return fullPrice;
	}

	public void setFullPrice(BigDecimal fullPrice) {
		this.fullPrice = fullPrice;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public static final String ID = "id";

	public static final String FULL_PRICE = "full_price";

	public static final String DISCOUNT = "discount";

	public static final String START_TIME = "start_time";

	public static final String END_TIME = "end_time";

	public static final String NUM = "num";

	public static final String IS_DEL = "is_del";

	public static final String PUBLISH_TIME = "publish_time";

	public static final String INTEGRAL = "integral";

	public static final String COUPON_NAME = "coupon_name";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Coupon{" +
			", id=" + id +
			", fullPrice=" + fullPrice +
			", discount=" + discount +
			", startTime=" + startTime +
			", endTime=" + endTime +
			", num=" + num +
			", isDel=" + isDel +
			", publishTime=" + publishTime +
			", integral=" + integral +
			", couponName=" + couponName +
			"}";
	}
}
