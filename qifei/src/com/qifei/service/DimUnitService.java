package com.qifei.service;
import java.util.List;

import com.qifei.model.DimUnit;

public interface DimUnitService {
	
	public List<DimUnit> getAllDimUnits();
	
	public void deleteDimUnit(List<DimUnit> dimUnit);
	
	public void saveDimUnit(List<DimUnit> dimUnit);

}