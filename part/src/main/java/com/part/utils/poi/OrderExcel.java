package com.part.utils.poi;

import java.math.BigDecimal;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class OrderExcel {

	@Excel(name = "用户ID", orderNum = "1" ,width = 46)
    private Integer userId;

    @Excel(name = "用户车牌号", orderNum = "2" ,width = 46)
    private String carNumber;
    
    @Excel(name = "用户手机号",orderNum = "3", width = 46)
    private String phone;

    @Excel(name = "优惠券ID", orderNum = "4" ,width = 46)
    private Integer couponId;

    @Excel(name = "订单号", orderNum = "5" ,width = 46)
    private String orderNum;

    @Excel(name = "订单金额", orderNum = "6" ,width = 46)
    private BigDecimal orderPrice;

    @Excel(name = "实际支付金额", orderNum = "7" ,width = 46)
    private BigDecimal realPay;

    @Excel(name = "订单创建时间", orderNum = "8" ,width = 36)
    private String createTime;
    
    @Excel(name = "订单结束时间", orderNum = "9" ,width = 36)
    private String endTime;

    @Excel(name = "支付方式", orderNum = "10" ,width = 46)
    private String payType;

    @Excel(name = "支付时间", orderNum = "11" ,width = 46)
    private String payTime;
    
    @Excel(name = "订单状态", orderNum = "12" ,width = 46)
    private String status; 
    
    @Excel(name = "停车场ID", orderNum = "13" ,width = 46)
    private Integer ccId; 
    
    @Excel(name = "停车场名字", orderNum = "14" ,width = 46)
    private String name;
    
    @Excel(name = "车位ID", orderNum = "15" ,width = 46)
    private Integer cwId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
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

	public BigDecimal getRealPay() {
		return realPay;
	}

	public void setRealPay(BigDecimal realPay) {
		this.realPay = realPay;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCcId() {
		return ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCwId() {
		return cwId;
	}

	public void setCwId(Integer cwId) {
		this.cwId = cwId;
	}

	@Override
	public String toString() {
		return "OrderExcel [userId=" + userId + ", carNumber=" + carNumber + ", phone=" + phone + ", couponId="
				+ couponId + ", orderNum=" + orderNum + ", orderPrice=" + orderPrice + ", realPay=" + realPay
				+ ", createTime=" + createTime + ", endTime=" + endTime + ", payType=" + payType + ", payTime="
				+ payTime + ", status=" + status + ", ccId=" + ccId + ", name=" + name + ", cwId=" + cwId + "]";
	}

	public OrderExcel(Integer userId, String carNumber, String phone, Integer couponId, String orderNum,
			BigDecimal orderPrice, BigDecimal realPay, String createTime, String endTime, String payType,
			String payTime, String status, Integer ccId, String name, Integer cwId) {
		super();
		this.userId = userId;
		this.carNumber = carNumber;
		this.phone = phone;
		this.couponId = couponId;
		this.orderNum = orderNum;
		this.orderPrice = orderPrice;
		this.realPay = realPay;
		this.createTime = createTime;
		this.endTime = endTime;
		this.payType = payType;
		this.payTime = payTime;
		this.status = status;
		this.ccId = ccId;
		this.name = name;
		this.cwId = cwId;
	}

	public OrderExcel() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
    
}
