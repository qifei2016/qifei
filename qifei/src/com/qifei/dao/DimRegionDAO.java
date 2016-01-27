package com.qifei.dao;

import java.util.List;

import com.qifei.model.DimRegion;

public interface DimRegionDAO extends BasicDAO {
	public List<DimRegion> getAllDimRegions();
}