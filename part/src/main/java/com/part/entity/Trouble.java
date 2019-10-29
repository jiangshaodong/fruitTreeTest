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
 * @since 2019-10-21
 */
@TableName("p_trouble")
public class Trouble extends Model<Trouble> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer userId;
    /**
     * 停车场id
     */
	private Integer ccId;
    /**
     * 停车位id
     */
	private Integer cwId;
    /**
     * 故障图片
     */
	private String troubleImg;
	private String troubleDec;
    /**
     * 软删除
     */
	private Integer flag;
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

	public String getTroubleImg() {
		return troubleImg;
	}

	public void setTroubleImg(String troubleImg) {
		this.troubleImg = troubleImg;
	}

	public String getTroubleDec() {
		return troubleDec;
	}

	public void setTroubleDec(String troubleDec) {
		this.troubleDec = troubleDec;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public static final String ID = "id";

	public static final String USER_ID = "user_id";

	public static final String CC_ID = "cc_id";

	public static final String CW_ID = "cw_id";

	public static final String TROUBLE_IMG = "trouble_img";

	public static final String TROUBLE_DEC = "trouble_dec";

	public static final String FLAG = "flag";

	public static final String PUBLISH_TIME = "publish_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Trouble{" +
			", id=" + id +
			", userId=" + userId +
			", ccId=" + ccId +
			", cwId=" + cwId +
			", troubleImg=" + troubleImg +
			", troubleDec=" + troubleDec +
			", flag=" + flag +
			", publishTime=" + publishTime +
			"}";
	}
}
