package com.qifei.dao;

import java.util.List;

import com.qifei.model.DimOwnerShip;

public interface DimOwnerShipDAO extends BasicDAO {
	public List<DimOwnerShip> getAllDimOwnerShips();
}