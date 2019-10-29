package com.part.entity;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
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
@TableName("p_user_account_log")
public class UserAccountLog extends Model<UserAccountLog> {

    private static final long serialVersionUID = 1L;

	private Integer id;
	private BigDecimal money;
	private String log;
    /**
     * 增OR减 0减，1增，
     */
	private Integer isAdd;
	private Date publishTime;
	private String payNum;
	private Date payTime;
	private Integer payType;
    /**
     *  1---已支付   0---未支付
     */
	private Integer isPay;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Integer getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(Integer isAdd) {
		this.isAdd = isAdd;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getPayNum() {
		return payNum;
	}

	public void setPayNum(String payNum) {
		this.payNum = payNum;
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

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	public static final String ID = "id";

	public static final String MONEY = "money";

	public static final String LOG = "log";

	public static final String IS_ADD = "is_add";

	public static final String PUBLISH_TIME = "publish_time";

	public static final String PAY_NUM = "pay_num";

	public static final String PAY_TIME = "pay_time";

	public static final String PAY_TYPE = "pay_type";

	public static final String IS_PAY = "is_pay";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserAccountLog{" +
			", id=" + id +
			", money=" + money +
			", log=" + log +
			", isAdd=" + isAdd +
			", publishTime=" + publishTime +
			", payNum=" + payNum +
			", payTime=" + payTime +
			", payType=" + payType +
			", isPay=" + isPay +
			"}";
	}
}
