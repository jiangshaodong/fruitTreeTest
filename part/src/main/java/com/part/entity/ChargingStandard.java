package com.part.entity;

import java.io.Serializable;

import java.math.BigDecimal;
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
@TableName("p_charging_standard")
public class ChargingStandard extends Model<ChargingStandard> {

    private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer time;
	private BigDecimal price;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public static final String ID = "id";

	public static final String TIME = "time";

	public static final String PRICE = "price";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ChargingStandard{" +
			", id=" + id +
			", time=" + time +
			", price=" + price +
			"}";
	}
}
