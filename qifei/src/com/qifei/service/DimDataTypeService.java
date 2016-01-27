package com.qifei.service;

import java.util.List;

import com.qifei.model.DimDataType;

public interface DimDataTypeService {
	
	public List<DimDataType> getAllDimDataType();
	
	public void deleteDimDataType(List<DimDataType> dimDataType);
	
	public void saveDimDataType(List<DimDataType> dimDataType);

}