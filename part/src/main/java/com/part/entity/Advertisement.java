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
 * @since 2019-10-23
 */
@TableName("p_advertisement")
public class Advertisement extends Model<Advertisement> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String img;
    /**
     * 排序 1  2  3  4 -----等级
     */
	private Integer rank;
	private String url;
    /**
     * 停车场id
     */
	private Integer ccId;
	private Integer flag;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getCcId() {
		return ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public static final String ID = "id";

	public static final String IMG = "img";

	public static final String RANK = "rank";

	public static final String URL = "url";

	public static final String CC_ID = "cc_id";

	public static final String FLAG = "flag";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Advertisement{" +
			", id=" + id +
			", img=" + img +
			", rank=" + rank +
			", url=" + url +
			", ccId=" + ccId +
			", flag=" + flag +
			"}";
	}
}
