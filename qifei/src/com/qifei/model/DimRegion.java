package com.qifei.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_dim_region")
public class DimRegion implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id  
	@Column(name="REGION_ID", length=11)
	private Integer regionId;
	
	@Column(name="REGION_CODE", length=50)
	private String regionCode;
	
	@Column(name="REGION_NAME", length=100)
	private String regionName;

	@Column(name="REGION_SHORTNAME", length=50)
	private String regionShortName;
	
	@Column(name="AREA", length=100)
	private String agea;
	
	@Column(name="PROVINCE", length=100)
	private String province;
	
	@Column(name="CITY", length=100)
	private String city;
	
	@Column(name="COUNTY", length=100)
	private String county;
	
	@Column(name="COL1", length=100)
	private String col1;
	
	@Column(name="COL2", length=100)
	private String col2;
	
	@Column(name="COL3", length=100)
	private String col3;
	
	@Column(name="COL4", length=100)
	private String col4;
	
	@Column(name="REGION_LEVEL", length=100)
	private Integer regionLevel;
	
	@Column(name="IS_VALID", length=11)
	private Integer isValId;
	
	@Column(name="REMARK", length=300)
	private String remark;

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionShortName() {
		return regionShortName;
	}

	public void setRegionShortName(String regionShortName) {
		this.regionShortName = regionShortName;
	}

	public String getAgea() {
		return agea;
	}

	public void setAgea(String agea) {
		this.agea = agea;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public String getCol3() {
		return col3;
	}

	public void setCol3(String col3) {
		this.col3 = col3;
	}

	public String getCol4() {
		return col4;
	}

	public void setCol4(String col4) {
		this.col4 = col4;
	}

	public Integer getRegionLevel() {
		return regionLevel;
	}

	public void setRegionLevel(Integer regionLevel) {
		this.regionLevel = regionLevel;
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
