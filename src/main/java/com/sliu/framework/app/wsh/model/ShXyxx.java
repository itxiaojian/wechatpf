package com.sliu.framework.app.wsh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 校友信息
 * @author duanpeijun
 * @date  2015年7月7日
 */
@Entity
@Table(name = "wx_user_info")
public class ShXyxx implements Serializable{
	
	private Long user_id;//主键
	
	private String openid;//openid
	
	private String nickname;//用户昵称
	
	private Integer sex;//性别
	
	private String u_language;//用户的语言
	
	private String city;//用户所在城市
	
	private String province;//用户所在省份
	
	private String country;//用户所在国家
	
	private String headimgurl;//用户头像
	
	private String subscribe_time;//用户关注时间
	
	private String unionid;//unionid
	
	private String remark;//用户备注名
	
	private String memo;//备注

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	@Column(name = "openid", length = 30, nullable = true)
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name = "nickname", length = 30, nullable = true)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "sex", length = 30, nullable = false)
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "u_language", length = 30, nullable = true)
	public String getU_language() {
		return u_language;
	}

	public void setU_language(String u_language) {
		this.u_language = u_language;
	}

	@Column(name = "city", length = 30, nullable = true)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "province", length = 30, nullable = true)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "country", length = 30, nullable = true)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "headimgurl", length = 30, nullable = true)
	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	@Column(name = "subscribe_time", length = 30, nullable = true)
	public String getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	@Column(name = "unionid", length = 30, nullable = true)
	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	@Column(name = "remark", length = 30, nullable = true)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "memo", length = 30, nullable = true)
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
