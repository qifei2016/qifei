package com.qifei.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfigParam {
	private int id;
	private String name;
	private String keyword;
	private int baseclassId;
	private int industryId;
	private int unitId;
	private int sourceId;
	private int regionId;
	private int dateTypeID;
	
	private String charset;
	private int method;
	private String url;
	private String baseurl;
	private List steplist;
	
	private String dateFormat;
	private String dateRange;
	private String valueFormat;
	private String valueRange;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getBaseclassId() {
		return baseclassId;
	}
	public void setBaseclassId(int baseclassId) {
		this.baseclassId = baseclassId;
	}
	public int getIndustryId() {
		return industryId;
	}
	public void setIndustryId(int industryId) {
		this.industryId = industryId;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public int getSourceId() {
		return sourceId;
	}
	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public int getDateTypeID() {
		return dateTypeID;
	}
	public void setDateTypeID(int dateTypeID) {
		this.dateTypeID = dateTypeID;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public int getMethod() {
		return method;
	}
	public void setMethod(int method) {
		this.method = method;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBaseurl() {
		return baseurl;
	}
	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}
	public List getSteplist() {
		return steplist;
	}
	public void setSteplist(List steplist) {
		this.steplist = steplist;
	}
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public String getDateRange() {
		return dateRange;
	}
	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}
	public String getValueFormat() {
		return valueFormat;
	}
	public void setValueFormat(String valueFormat) {
		this.valueFormat = valueFormat;
	}
	public String getValueRange() {
		return valueRange;
	}
	public void setValueRange(String valueRange) {
		this.valueRange = valueRange;
	}
}
