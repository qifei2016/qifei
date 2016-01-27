package com.qifei.service;

import java.util.List;

import com.qifei.model.DimCategory;

public interface DimCategoryService {
	
	public List<DimCategory> getAllDimCategory();
	
	public void deleteDimCategory(List<DimCategory> dimCategory);
	
	public void saveDimCategory(List<DimCategory> dimCategory);

}