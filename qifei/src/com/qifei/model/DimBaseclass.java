package com.qifei.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_dim_baseclass")
public class DimBaseclass implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id  
	@Column(name="BASECLASS_ID", length=11)
	private Integer baseclassId;
	
	@Column(name="BASECLASS_CODE", length=50)
	private String baseclassCode;
	
	@Column(name="BASECLASS_NAME", length=100)
	private String baseclassName;
	
	@Column(name="CATEGORY_ID", length=11)
	private Integer categoryId;
	
	@Column(name="IS_VALID", length=11)
	private Integer isValId;
	
	@Column(name="REMARK", length=300)
	private String remark;

	public Integer getBaseclassId() {
		return baseclassId;
	}

	public void setBaseclassId(Integer baseclassId) {
		this.baseclassId = baseclassId;
	}

	public String getBaseclassCode() {
		return baseclassCode;
	}

	public void setBaseclassCode(String baseclassCode) {
		this.baseclassCode = baseclassCode;
	}

	public String getBaseclassName() {
		return baseclassName;
	}

	public void setBaseclassName(String baseclassName) {
		this.baseclassName = baseclassName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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
