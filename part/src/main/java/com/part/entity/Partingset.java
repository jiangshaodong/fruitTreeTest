package com.part.entity;

import java.io.Serializable;

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
@TableName("p_partingset")
public class Partingset extends Model<Partingset> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 停车场id
     */
	private Integer ccId;
    /**
     * 0---空闲，可使用    1---故障   2---使用中
     */
	private Integer state;
    /**
     *  1---删除
     */
	private Integer isDel;
	private String img;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCcId() {
		return ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public static final String ID = "id";

	public static final String CC_ID = "cc_id";

	public static final String STATE = "state";

	public static final String IS_DEL = "is_del";

	public static final String IMG = "img";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Partingset{" +
			", id=" + id +
			", ccId=" + ccId +
			", state=" + state +
			", isDel=" + isDel +
			", img=" + img +
			"}";
	}
}
