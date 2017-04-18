package com.sliu.framework.app.wsh.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class ShWhbMb {

/**
*
*
@Author oufeng	
@Date 2015年7月6日 上午11:47:54
@Version 1.0
*/
	@Entity
	@Table(name="sh_whbmb")
	public class ShWshMb implements Serializable{
		

		/**id*/
		private Long id;
		
		/**模板主题*/
		private Long mbzt;
	
		/**模板正文*/
		private Long mbzw;
		
		/**模板图片*/
		private String mbtp;
		
		/**备注*/
		private String bz;
		
		/**创建时间*/
		private Date cjsj;
		
		/**是否发布  0:未发布  1:发布*/
		private Integer sffb;
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
	    @Column(name="ID", length=200, nullable=true)
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		@Column(name="MBZT", length=300, nullable=true)
		public Long getMbzt() {
			return mbzt;
		}

		public void setMbzt(Long mbzt) {
			this.mbzt = mbzt;
		}

		@Column(name="MBZW", length=300, nullable=true)
		public Long getMbzw() {
			return mbzw;
		}

		public void setMbzw(Long mbzw) {
			this.mbzw = mbzw;
		}
		@Column(name="MBTP", length=300, nullable=true)
		public String getMbtp() {
			return mbtp;
		}

		public void setMbtp(String mbtp) {
			this.mbtp = mbtp;
		}

		@Column(name="BZ", length=256, nullable=true)
		public String getBz() {
			return bz;
		}

		public void setBz(String bz) {
			this.bz = bz;
		}
		
		@Column(name="CJSJ", length=30, nullable=true)
		public Date getCjsj() {
			return cjsj;
		}

		public void setCjsj(Date cjsj) {
			this.cjsj = cjsj;
		}

		@Column(name="SFFB", length=2, nullable=true)
		public Integer getSffb() {
			return sffb;
		}

		public void setSffb(Integer sffb) {
			this.sffb = sffb;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}