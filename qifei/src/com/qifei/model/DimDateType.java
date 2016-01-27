package com.qifei.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_dim_datetype")
public class DimDateType implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
	@Column(name="DATETYPE_ID", length=10)
	private Integer datetypeId;
	
	@Column(name="DATETYPE_DESC", length=20)
	private String datetypeDesc;
	
	@Column(name="IS_VALID", length=11)
	private Integer isValId;
	
	@Column(name="REMARK", length=300)
	private String remark;

	public Integer getDatetypeId() {
		return datetypeId;
	}

	public void setDatetypeId(Integer datetypeId) {
		this.datetypeId = datetypeId;
	}

	public String getDatetypeDesc() {
		return datetypeDesc;
	}

	public void setDatetypeDesc(String datetypeDesc) {
		this.datetypeDesc = datetypeDesc;
	}

	public Integer getIsValId() {
		return isValId;
	}

	public void setIsValId(Integer isValId) {
		this.isValId = isValId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
