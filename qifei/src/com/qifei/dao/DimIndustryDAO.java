package com.qifei.dao;

import java.util.List;

import com.qifei.model.DimIndustry;

public interface DimIndustryDAO extends BasicDAO {
	public List<DimIndustry> getAllDimIndustrys();
	public int getMaxDimIndustryId();
}