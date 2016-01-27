package com.qifei.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_collect_item")
public class CollectItem implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
	@Column(name="COLLECT_ITEM_ID", length=11)
	private Integer collectItemId;
	
	@Column(name="COLLECT_ITEM_DESC", length=300)
	private String collectItemDesc;
	
	@Column(name="COLLECT_SOURCE", length=11)
	private Integer collectSource;
	
	@Column(name="COLLECT_URL", length=300)
	private String collectURL;
	
	@Column(name="BASECLASS_ID", length=11)
	private Integer baseclassId;
	
	@Column(name="INDUSTRY_ID", length=11)
	private Integer industryId;
	
	@Column(name="UNIT_ID", length=11)
	private Integer unitId;
	
	@Column(name="DATATYPE_ID", length=11)
	private Integer dataTypeId;
	
	@Column(name="DATETYPE_ID", length=11)
	private Integer dateTypeID;
	
	@Column(name="REGION_ID", length=11)
	private Integer regionId;
	
	@Column(name="Status", length=300)
	private String status;

	@Column(name="CLASS2", length=300)
	private String class2;

	@Column(name="CLASS3", length=300)
	private String class3;

	@Column(name="CLASS4", length=300)
	private String class4;

	@Column(name="CLASS5", length=300)
	private String class5;

	@Column(name="XML_ID", length=11)
	private Integer xmlId;
	
	@Column(name="COLLECT_KEYWORDS", length=300)
	private String collectKeywords;

	@Column(name="IS_VALID", length=11)
	private Integer isValId;

	@Column(name="LAST_UPDATE_TIME", length=300)
	private Date lastUpdateTime;

	@Column(name="REMARK", length=300)
	private String remark;

	public Integer getCollectItemId() {
		return collectItemId;
	}

	public void setCollectItemId(Integer collectItemId) {
		this.collectItemId = collectItemId;
	}

	public String getCollectItemDesc() {
		return collectItemDesc;
	}

	public void setCollectItemDesc(String collectItemDesc) {
		this.collectItemDesc = collectItemDesc;
	}

	public Integer getCollectSource() {
		return collectSource;
	}

	public void setCollectSource(Integer collectSource) {
		this.collectSource = collectSource;
	}

	public String getCollectURL() {
		return collectURL;
	}

	public void setCollectURL(String collectURL) {
		this.collectURL = collectURL;
	}

	public Integer getBaseclassId() {
		return baseclassId;
	}

	public void setBaseclassId(Integer baseclassId) {
		this.baseclassId = baseclassId;
	}

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getDataTypeId() {
		return dataTypeId;
	}

	public void setDataTypeId(Integer dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	public Integer getDateTypeID() {
		return dateTypeID;
	}

	public void setDateTypeID(Integer dateTypeID) {
		this.dateTypeID = dateTypeID;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClass2() {
		return class2;
	}

	public void setClass2(String class2) {
		this.class2 = class2;
	}

	public String getClass3() {
		return class3;
	}

	public void setClass3(String class3) {
		this.class3 = class3;
	}

	public String getClass4() {
		return class4;
	}

	public void setClass4(String class4) {
		this.class4 = class4;
	}

	public String getClass5() {
		return class5;
	}

	public void setClass5(String class5) {
		this.class5 = class5;
	}

	public Integer getXmlId() {
		return xmlId;
	}

	public void setXmlId(Integer xmlId) {
		this.xmlId = xmlId;
	}

	public String getCollectKeywords() {
		return collectKeywords;
	}

	public void setCollectKeywords(String collectKeywords) {
		this.collectKeywords = collectKeywords;
	}

	public Integer getIsValId() {
		return isValId;
	}

	public void setIsValId(Integer isValId) {
		this.isValId = isValId;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
