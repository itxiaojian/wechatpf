package com.sliu.framework.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 投诉建议
 * @author zhangdongdong
 *
 */
@Entity
@Table(name="WX_TSJY_INFO")
public class WxTsjyInfo  implements Serializable {


	/**TSJY_ID*/
	private Long tsjyId;
	
	/**用户的标识，对当前公众号唯一*/
	private String openid;
	
	/**1:投诉 2:建议*/
	private Integer tslx;
	
	/**TSLR*/
	private String tslr;
	
	/**LXFS*/
	private String lxfs;
	
	/**创建时间*/
	private java.util.Date createTime;
	
	/**0:未处理 ,默认未处理
1:已处理
  
由用户手动把数据状态为更新掉*/
	private Integer dealFlag;
	
	@Id
    @GeneratedValue(generator = "tableGenerator")     
    @GenericGenerator(name = "tableGenerator", strategy="com.sliu.framework.app.common.dao.key.SequenceGenerator")  
    @Column(name="TSJY_ID", length=19)
	public Long getTsjyId() {
		return tsjyId;
	}
	
	public void setTsjyId(Long tsjyId) {
		this.tsjyId = tsjyId;
	}
	
	@Column(name="OPENID", length=100)
	public String getOpenid() {
		return openid;
	}
	
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Column(name="TSLX", length=10)
	public Integer getTslx() {
		return tslx;
	}
	
	public void setTslx(Integer tslx) {
		this.tslx = tslx;
	}
	
	@Column(name="TSLR", length=1000)
	public String getTslr() {
		return tslr;
	}
	
	public void setTslr(String tslr) {
		this.tslr = tslr;
	}
	
	@Column(name="LXFS", length=50)
	public String getLxfs() {
		return lxfs;
	}
	
	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}
	
	@Column(name="CREATE_TIME", length=26)
	public java.util.Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="DEAL_FLAG", length=10)
	public Integer getDealFlag() {
		return dealFlag;
	}
	
	public void setDealFlag(Integer dealFlag) {
		this.dealFlag = dealFlag;
	}



}
