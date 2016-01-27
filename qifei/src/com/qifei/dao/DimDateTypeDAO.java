package com.qifei.dao;

import java.util.List;

import com.qifei.model.DimDateType;

public interface DimDateTypeDAO extends BasicDAO {
	
	public List<DimDateType> getAllDimDateType();
	
}