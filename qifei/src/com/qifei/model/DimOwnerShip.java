package com.qifei.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_dim_ownership")
public class DimOwnerShip implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
	@Column(name="ownership_id", length=11)
	private Integer ownershipId;
	
	@Column(name="ownership_name", length=255)
	private String ownershipName;
	
	@Column(name="is_valid", length=11)
	private Integer isValId;
	
	@Column(name="remark", length=255)
	private String remark;

	public Integer getOwnershipId() {
		return ownershipId;
	}

	public void setOwnershipId(Integer ownershipId) {
		this.ownershipId = ownershipId;
	}

	public String getOwnershipName() {
		return ownershipName;
	}

	public void setOwnershipName(String ownershipName) {
		this.ownershipName = ownershipName;
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
