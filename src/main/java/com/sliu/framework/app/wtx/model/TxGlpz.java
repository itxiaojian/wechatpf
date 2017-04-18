package com.sliu.framework.app.wtx.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 提醒管理配置
 * @author liujiansen
 * @since  2015-07-29
 */
@Entity
@Table(name="tx_glpz")
public class TxGlpz implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**提醒数据归集时间*/
	private java.util.Date sjgjsj;
	
	/**提醒时间*/
	private java.util.Date txsj;
	
	/**提醒类型（0:一卡通大额消费1:一卡通消费记录2:还书3:学生上课4:学生连续未上网5:老师上课）*/
	private String txlx;
	
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
	
	@Column(name="SJGJSJ", length=19, nullable=true)
	public java.util.Date getSjgjsj() {
		return sjgjsj;
	}
	
	public void setSjgjsj(java.util.Date sjgjsj) {
		this.sjgjsj = sjgjsj;
	}
	
	@Column(name="TXSJ", length=19, nullable=true)
	public java.util.Date getTxsj() {
		return txsj;
	}
	
	public void setTxsj(java.util.Date txsj) {
		this.txsj = txsj;
	}
	
	@Column(name="TXLX", length=10, nullable=true)
	public String getTxlx() {
		return txlx;
	}
	
	public void setTxlx(String txlx) {
		this.txlx = txlx;
	}
	
	@Column(name="BZ", length=512, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
