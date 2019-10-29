package com.part.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
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
 * @since 2019-10-18
 */
@TableName("p_parkinglot")
public class Parkinglot extends Model<Parkinglot> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String name;
    /**
     * 经度
     */
	private BigDecimal longitude;
    /**
     * 纬度
     */
	private BigDecimal latitude;
	private String img;
    /**
     * 总停车位
     */
	private Integer totalNumber;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public static final String ID = "id";

	public static final String NAME = "name";

	public static final String LONGITUDE = "longitude";

	public static final String LATITUDE = "latitude";

	public static final String IMG = "img";

	public static final String TOTAL_NUMBER = "total_number";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Parkinglot{" +
			", id=" + id +
			", name=" + name +
			", longitude=" + longitude +
			", latitude=" + latitude +
			", img=" + img +
			", totalNumber=" + totalNumber +
			"}";
	}
}
