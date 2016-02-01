package com.qifei.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_dim_unit")
public class DimUnit implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id  
	@Column(name="UNIT_ID", length=11)
	private Integer unitId;
	
	@Column(name="UNIT_NAME", length=100)
	private String unitName;
	
	@Column(name="CURRENCY", length=50)
	private String currency;
	
	@Column(name="IS_VALID", length=11)
	private Integer isValId;
	
	@Column(name="REMARK", length=300)
	private String remark;

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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
