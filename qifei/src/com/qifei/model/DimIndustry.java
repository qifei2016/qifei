package com.qifei.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_dim_industry")
public class DimIndustry implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id  
	@Column(name="INDUSTRY_ID", length=11)
	private Integer industryId;
	
	@Column(name="INDUSTRY_NAME", length=200)
	private String industryName;
	
	@Column(name="INDUSTRY_SHORTNAME", length=100)
	private String industryShortName;

	@Column(name="INDUS_ID", length=11)
	private Integer indusId;
	
	@Column(name="CLASS1", length=100)
	private String class1;
	
	@Column(name="CLASS2", length=100)
	private String class2;
	
	@Column(name="IS_VALID", length=11)
	private Integer isValId;
	
	@Column(name="REMARK", length=300)
	private String remark;

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getIndustryShortName() {
		return industryShortName;
	}

	public void setIndustryShortName(String industryShortName) {
		this.industryShortName = industryShortName;
	}

	public Integer getIndusId() {
		return indusId;
	}

	public void setIndusId(Integer indusId) {
		this.indusId = indusId;
	}

	public String getClass1() {
		return class1;
	}

	public void setClass1(String class1) {
		this.class1 = class1;
	}

	public String getClass2() {
		return class2;
	}

	public void setClass2(String class2) {
		this.class2 = class2;
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
