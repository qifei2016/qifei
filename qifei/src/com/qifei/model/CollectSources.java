package com.qifei.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_collect_sources")
public class CollectSources implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
	@Column(name="SOURCE_ID", length=11)
	private Integer sourceId;
	
	@Column(name="SOURCE_NAME", length=100)
	private String sourceName;
	
	@Column(name="SOURCE_URL", length=100)
	private String sourceURL;
	
	@Column(name="CLASS", length=100)
	private String class1;
	
	@Column(name="ORGANIZATION", length=300)
	private String organization;

	@Column(name="OWNERSHIP_ID", length=11)
	private Integer ownershipId;

	@Column(name="IS_VALID", length=11)
	private Integer isValId;
	
	@Column(name="REMARK", length=300)
	private String remark;

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceURL() {
		return sourceURL;
	}

	public void setSourceURL(String sourceURL) {
		this.sourceURL = sourceURL;
	}

	public String getClass1() {
		return class1;
	}

	public void setClass1(String class1) {
		this.class1 = class1;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public Integer getOwnershipId() {
		return ownershipId;
	}

	public void setOwnershipId(Integer ownershipId) {
		this.ownershipId = ownershipId;
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
