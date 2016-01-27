package com.qifei.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_collect_xml")
public class CollectXML implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
	@Column(name="xml_id", length=11)
	private Integer xmlId;
	
	@Column(name="col1", length=255)
	private String col1;
	
	@Column(name="col2", length=255)
	private String col2;
	
	@Column(name="col3", length=255)
	private String col3;
	
	@Column(name="col4", length=255)
	private String col4;
	
	@Column(name="is_valid", length=11)
	private Integer isValId;
	
	@Column(name="remark", length=300)
	private String remark;

	public Integer getXmlId() {
		return xmlId;
	}

	public void setXmlId(Integer xmlId) {
		this.xmlId = xmlId;
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
