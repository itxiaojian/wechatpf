package com.sliu.framework.app.bx.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 报修申请
 * @author liujiansen
 * @since  2015-08-06
 */
@Entity
@Table(name="bx_bxsq")
public class BxBxsq implements Serializable{
	
	/**主键*/
	private Long id;
	
	/**报修编号*/
	private String bxbh;
	
	/**学号或工号*/
	private String xgh;
	
	/**联系号码*/
	private String lxhm;
	
	/**申请人姓名*/
	private String sqrxm;
	
	/**校区*/
	private String xq;
	
	/**楼宇*/
	private String ly;
	
	/**楼号*/
	private String lh;
	
	/**服务:1、教材设备；2、维修*/
	private String fw;
	
	/**预约时间*/
	private java.util.Date yysj;
	
	/**申报主题编号:电器、工具、设施等分类*/
	private String sbztbh;
	
	/**申报主题名称:电器、工具、设施等分类*/
	private String sbztmc;
	
	/**地址*/
	private String dz;
	
	/**内容*/
	private String nr;
	
	/**维修大类*/
	private String   wxdl;
	
	/**状态:1、申请中；2、受理中；3、处理中；4、结束*/
	private String zt;
	
	/**评价*/
	private String pj;
	
	/**满意度*/
	private String myd;
	
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
	
	@Column(name="BXBH", length=32, nullable=true)
	public String getBxbh() {
		return bxbh;
	}
	
	public void setBxbh(String bxbh) {
		this.bxbh = bxbh;
	}
	
	@Column(name="XGH", length=32, nullable=true)
	public String getXgh() {
		return xgh;
	}
	
	public void setXgh(String xgh) {
		this.xgh = xgh;
	}
	
	@Column(name="LXHM", length=32, nullable=true)
	public String getLxhm() {
		return lxhm;
	}
	
	public void setLxhm(String lxhm) {
		this.lxhm = lxhm;
	}
	
	@Column(name="SQRXM", length=32, nullable=true)
	public String getSqrxm() {
		return sqrxm;
	}
	
	public void setSqrxm(String sqrxm) {
		this.sqrxm = sqrxm;
	}
	
	@Column(name="XQ", length=32, nullable=true)
	public String getXq() {
		return xq;
	}
	
	public void setXq(String xq) {
		this.xq = xq;
	}
	
	@Column(name="LY", length=32, nullable=true)
	public String getLy() {
		return ly;
	}
	
	public void setLy(String ly) {
		this.ly = ly;
	}
	
	@Column(name="LH", length=32, nullable=true)
	public String getLh() {
		return lh;
	}
	
	public void setLh(String lh) {
		this.lh = lh;
	}
	
	@Column(name="FW", length=32, nullable=true)
	public String getFw() {
		return fw;
	}
	
	public void setFw(String fw) {
		this.fw = fw;
	}
	
	@Column(name="YYSJ", length=19, nullable=true)
	public java.util.Date getYysj() {
		return yysj;
	}
	
	public void setYysj(java.util.Date yysj) {
		this.yysj = yysj;
	}
	
	@Column(name="SBZTBH", length=32, nullable=true)
	public String getSbztbh() {
		return sbztbh;
	}
	
	public void setSbztbh(String sbztbh) {
		this.sbztbh = sbztbh;
	}
	
	@Column(name="SBZTMC", length=32, nullable=true)
	public String getSbztmc() {
		return sbztmc;
	}
	
	public void setSbztmc(String sbztmc) {
		this.sbztmc = sbztmc;
	}
	
	@Column(name="DZ", length=512, nullable=true)
	public String getDz() {
		return dz;
	}
	
	public void setDz(String dz) {
		this.dz = dz;
	}
	
	@Column(name="NR", length=256, nullable=true)
	public String getNr() {
		return nr;
	}
	
	public void setNr(String nr) {
		this.nr = nr;
	}
	
	@Column(name="WXDL", length=10, nullable=true)
	public String getWxdl() {
		return wxdl;
	}

	public void setWxdl(String wxdl) {
		this.wxdl = wxdl;
	}
	
	@Column(name="ZT", length=2, nullable=true)
	public String getZt() {
		return zt;
	}
	
	public void setZt(String zt) {
		this.zt = zt;
	}
	
	@Column(name="PJ", length=512, nullable=true)
	public String getPj() {
		return pj;
	}
	
	public void setPj(String pj) {
		this.pj = pj;
	}
	
	@Column(name="MYD", length=2, nullable=true)
	public String getMyd() {
		return myd;
	}

	public void setMyd(String myd) {
		this.myd = myd;
	}

	@Column(name="BZ", length=512, nullable=true)
	public String getBz() {
		return bz;
	}
	
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
