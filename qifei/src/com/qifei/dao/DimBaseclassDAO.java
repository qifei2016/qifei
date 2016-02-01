package com.qifei.dao;

import java.util.List;

import com.qifei.model.DimBaseclass;

public interface DimBaseclassDAO extends BasicDAO {
	
	public List<DimBaseclass> getAllDimBaseclass();
	
	public int getMaxBaseclassId();
}