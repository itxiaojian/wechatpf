package com.sliu.framework.app.wsh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 微信抽奖结果
 * @author liujiansen
 * @since  2015-06-18
 */
@Entity
@Table(name="sh_wxcjjg")
public class ShWxcjjg implements Serializable{
	
	/**ID*/
	private Long id;
	
	/**抽奖编号*/
	private Long cjid;
	
	/**用户openId*/
	private String openid;
	
	/**抽中奖项*/
	private String czjx;
	
	/**备注*/
	private String bz;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", length=19, nullable=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="CJID", length=19, nullable=true)
	public Long getCjid() {
		return cjid;
	}
	
	public void setCjid(Long cjid) {
		this.cjid = cjid;
	}
	
	@Column(name="OPENID", length=200, nullable=true)
	public String getOpenid() {
		return openid;
	}
	
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Column(name="CZJX", length=200, nullable=true)
	public String getCzjx() {
		return czjx;
	}
	
	public void setCzjx(String czjx) {
		this.czjx = czjx;
	}
	
	@Column(name="BZ", length=500, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
