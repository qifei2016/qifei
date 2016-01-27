package com.qifei.service;

import java.util.List;

import com.qifei.model.DimDateType;

public interface DimDateTypeService {
	
	public List<DimDateType> getAllDimDateType();
	
	public void deleteDimDateType(List<DimDateType> dimDateType);
	
	public void saveDimDateType(List<DimDateType> dimDateType);

}