package com.sliu.framework.app.wxutil.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 图文消息内容
 * @author : zhangyi
 * @version 创建时间：2016年9月2日 下午3:53:43
 */
@Entity
@Table(name = "WX_TWXXNR")
public class WxTwxxnr  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 主体ID
	 */
	private Long ztid;
	
    /**
     * 消息标题
     */
    private String xxbt;
    
    /**
     * 消息内容
     */
    private String xxnr;
    
    /**
     * 添加时间
     */
    private String tjsj;
    
    /**
     * 缩略图id
     */
    private Long sltid;
    
    /**
     * 排序
     */
    private Integer px;
    
     
	@Id
	@GeneratedValue(generator = "tableGenerator")
	@GenericGenerator(name = "tableGenerator", strategy = "com.sliu.framework.app.common.dao.key.SequenceGenerator")
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "XXBT", length = 64)
	public String getXxbt() {
		return xxbt;
	}

	public void setXxbt(String xxbt) {
		this.xxbt = xxbt;
	}

	@Column(name = "TJSJ", length = 32)
	public String getTjsj() {
		return tjsj;
	}

	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}

	@Column(name = "ZTID")
	public Long getZtid() {
		return ztid;
	}

	public void setZtid(Long ztid) {
		this.ztid = ztid;
	}

	@Column(name = "XXNR", length = 8000, nullable = false)
	public String getXxnr() {
		return xxnr;
	}

	public void setXxnr(String xxnr) {
		this.xxnr = xxnr;
	}

	@Column(name = "SLTID")
	public Long getSltid() {
		return sltid;
	}

	public void setSltid(Long sltid) {
		this.sltid = sltid;
	}

	@Column(name = "PX")
	public Integer getPx() {
		return px;
	}

	public void setPx(Integer px) {
		this.px = px;
	}

    
}