package com.qifei.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_dim_category")
public class DimCategory implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
	@Column(name="category_id", length=11)
	private Integer categoryId;
	
	@Column(name="category_code", length=255)
	private String categoryCode;
	
	@Column(name="category_name", length=255)
	private String categoryName;
	
	@Column(name="is_valid", length=11)
	private Integer isValId;
	
	@Column(name="remark", length=255)
	private String remark;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
