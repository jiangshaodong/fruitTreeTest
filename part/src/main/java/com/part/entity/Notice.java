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
@TableName("p_notice")
public class Notice extends Model<Notice> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String title;
	private String content;
	private Integer canUse;
	private Date publishTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCanUse() {
		return canUse;
	}

	public void setCanUse(Integer canUse) {
		this.canUse = canUse;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public static final String ID = "id";

	public static final String TITLE = "title";

	public static final String CONTENT = "content";

	public static final String CAN_USE = "can_use";

	public static final String PUBLISH_TIME = "publish_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Notice{" +
			", id=" + id +
			", title=" + title +
			", content=" + content +
			", canUse=" + canUse +
			", publishTime=" + publishTime +
			"}";
	}
}
