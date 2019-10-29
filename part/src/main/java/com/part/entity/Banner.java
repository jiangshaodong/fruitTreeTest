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
@TableName("p_banner")
public class Banner extends Model<Banner> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String title;
	private String img;
	private String content;
    /**
     * 级别
     */
	private Integer rank;
	private Date publishTime;
    /**
     * 0---删除  1---未删除
     */
	private Integer isDel;
    /**
     * 停车场id
     */
	private Integer parkinglotId;
	private String url;


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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getParkinglotId() {
		return parkinglotId;
	}

	public void setParkinglotId(Integer parkinglotId) {
		this.parkinglotId = parkinglotId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static final String ID = "id";

	public static final String TITLE = "title";

	public static final String IMG = "img";

	public static final String CONTENT = "content";

	public static final String RANK = "rank";

	public static final String PUBLISH_TIME = "publish_time";

	public static final String IS_DEL = "is_del";

	public static final String PARKINGLOT_ID = "parkinglot_id";

	public static final String URL = "url";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Banner{" +
			", id=" + id +
			", title=" + title +
			", img=" + img +
			", content=" + content +
			", rank=" + rank +
			", publishTime=" + publishTime +
			", isDel=" + isDel +
			", parkinglotId=" + parkinglotId +
			", url=" + url +
			"}";
	}
}
