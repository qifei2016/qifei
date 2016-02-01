package com.qifei.service;
import java.util.List;

import com.qifei.model.DimRegion;

public interface DimRegionService {
	
	public List<DimRegion> getAllDimRegions();
	
	public void deleteDimRegion(List<DimRegion> dimRegion);
	
	public void saveDimRegion(List<DimRegion> dimRegion);
	
	public int getMaxRegionId();

}