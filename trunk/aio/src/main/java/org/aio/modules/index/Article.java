package org.aio.modules.index;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 
 * @author laobi18
 * @功能:文章实体类
 * @生成时间:2012-4-29
 */
@Entity
@Table(name = "ARTICLE", schema = "old")
@SequenceGenerator(name = "nnldbz_seq_article", sequenceName = "nnldbz_seq_article")
public class Article implements Serializable {

	private static final long serialVersionUID = -2624585271224781235L;
	private Integer id;			/*主键*/
	private String title;		/*标题*/
	private String content;		/*内容*/
	private Integer hits;		/*点击数*/
	private Integer status;		/*审核状态,0未审核,1通过审核,2,未通过审核*/
	private Date createdTime;	/*创建时间*/
	private Date updateTime;	/*更新时间*/
	private Integer isTop;		/*置顶标志位,0表示未置顶,1表示已置顶*/
	private Integer isDel;		/*删除标志位,0表示未删除,1表示已删除*/
	private Integer isHot;		/*热门标志位,0表示非热门文章,1表示为热门文章 */
	private String author;		/*作者名称 */
	private String editor;		/*编辑者名称*/
	private String partname;	/*所属栏目名称*/
	private Integer type;		/*文章类型,0文章 1 公告  ,2热点专题文章,3简介*/
	
	
	
	
	
	@Column(length=32)
	public String getAuthor() {
		return author;
	}
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(nullable = false)
	public String getContent() {
		return content;
	}
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getUpdateTime() {
		return updateTime;
	}
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getCreatedTime() {
		return createdTime;
	}

	@Column(length=32)
	public String getEditor() {
		return editor;
	}


	@Column(nullable = false)
	public Integer getHits() {
		return hits;
	}

	@Id
	@GeneratedValue(generator = "nnldbz_seq_article")
	public Integer getId() {
		return id;
	}
	
	@Column( nullable = false)
	public Integer getIsDel() {
		return isDel;
	}
	public Integer getIsHot() {
		return isHot;
	}
	public Integer getIsTop() {
		return isTop;
	}
	public String getPartname() {
		return partname;
	}


	@Column(nullable = false)
	public Integer getStatus() {
		return status;
	}

	@Column(nullable = false)
	public String getTitle() {
		return title;
	}
	public Integer getType() {
		return type;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	public void setPartname(String partname) {
		this.partname = partname;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}