package com.qifei.dao;

import java.util.List;

import com.qifei.model.DimUnit;

public interface DimUnitDAO extends BasicDAO {
	public List<DimUnit> getAllDimUnits();
	public int getMaxUnitId();
	public DimUnit getUnitByName(String name);
}