package com.qifei.service;

import java.util.List;

import com.qifei.model.DimOwnerShip;

public interface DimOwnerShipService {
	
	public List<DimOwnerShip> getAllDimOwnerShips();
	
	public void deleteDimOwnerShip(List<DimOwnerShip> dimOwnerShip);
	
	public void saveDimOwnerShip(List<DimOwnerShip> dimOwnerShip);

}