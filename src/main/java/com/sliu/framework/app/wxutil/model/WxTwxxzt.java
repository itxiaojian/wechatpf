package com.sliu.framework.app.wxutil.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 图文消息主体
 * @author : zhangyi
 * @version 创建时间：2016年9月2日 下午3:53:43
 */
@Entity
@Table(name = "WX_TWXXZT")
public class WxTwxxzt  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键id
	 */
	private Long id;
	
    /**
     * 消息标题
     */
    private String xxbt;
    
    /**
     * 添加时间
     */
    private String tjsj;
    
    /**
     * 添加人编号
     */
    private String tjrbh;
    
    /**
     * 添加人姓名
     */
    private String tjrxm;
    
    /**
     * 消息说明
     */
    private String xxsm;
     
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

	@Column(name = "TJRBH", length = 32)
	public String getTjrbh() {
		return tjrbh;
	}

	public void setTjrbh(String tjrbh) {
		this.tjrbh = tjrbh;
	}

	@Column(name = "TJRXM", length = 32)
	public String getTjrxm() {
		return tjrxm;
	}

	public void setTjrxm(String tjrxm) {
		this.tjrxm = tjrxm;
	}

	@Column(name = "XXSM", length = 256)
	public String getXxsm() {
		return xxsm;
	}

	public void setXxsm(String xxsm) {
		this.xxsm = xxsm;
	}
    
}