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
@Table(name="t_collect_data")
public class CollectData implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
	@Column(name="COLLECT_ID", length=11, nullable = true)
	private Integer collectId;
	
	@Column(name="COLLECT_DATE", nullable = true)
	private Date collectDate;
	
	@Column(name="COLLECT_ITEM_ID", length=11, nullable = true)
	private Integer collectItemId;
	
	@Column(name="COLLECT_DATA", length=20)
	private Float collectData;
	
	@Column(name="LAST_UPDATE_TIME")
	private Date lastUpdateTime;
	
	@Column(name="COL1", length=300)
	private String col1;
	
	@Column(name="COL2", length=300)
	private String col2;
	
	@Column(name="COL3", length=300)
	private String col3;
	
	@Column(name="COL4", length=300)
	private String col4;
	
	@Column(name="COL5", length=300)
	private String col5;
	
	@Column(name="REMARK", length=300)
	private String remark;

	public Integer getCollectId() {
		return collectId;
	}

	public void setCollectId(Integer collectId) {
		this.collectId = collectId;
	}

	public Date getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}

	public Integer getCollectItemId() {
		return collectItemId;
	}

	public void setCollectItemId(Integer collectItemId) {
		this.collectItemId = collectItemId;
	}

	public Float getCollectData() {
		return collectData;
	}

	public void setCollectData(Float collectData) {
		this.collectData = collectData;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
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

	public String getCol5() {
		return col5;
	}

	public void setCol5(String col5) {
		this.col5 = col5;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
