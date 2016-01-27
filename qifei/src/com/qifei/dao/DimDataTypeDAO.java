package com.qifei.dao;

import java.util.List;

import com.qifei.model.DimDataType;

public interface DimDataTypeDAO extends BasicDAO {
	
	public List<DimDataType> getAllDimDataType();
	
}