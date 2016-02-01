package com.qifei.service;
import java.util.List;

import com.qifei.model.DimIndustry;

public interface DimIndustryService {
	
	public List<DimIndustry> getAllDimIndustrys();
	
	public void deleteDimIndustry(List<DimIndustry> dimIndustry);
	
	public void saveDimIndustry(List<DimIndustry> dimIndustry);

	public int getMaxDimIndustryId();
	
}