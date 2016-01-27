package com.qifei.dao;

import java.util.List;

import com.qifei.model.DimCategory;

public interface DimCategoryDAO extends BasicDAO {
	
	public List<DimCategory> getAllDimCategory();
	
}