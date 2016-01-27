package com.qifei.service;
import java.util.List;

import com.qifei.model.DimBaseclass;

public interface DimBaseclassService {
	
	public List<DimBaseclass> getAllDimBaseclass();
	
	public void deleteDimBaseclass(List<DimBaseclass> dimBaseclass);
	
	public void saveDimBaseclass(List<DimBaseclass> dimBaseclass);

}