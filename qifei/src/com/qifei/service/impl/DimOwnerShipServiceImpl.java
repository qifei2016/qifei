package com.qifei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qifei.dao.DimOwnerShipDAO;
import com.qifei.model.DimOwnerShip;
import com.qifei.service.DimOwnerShipService;

@Service
public class DimOwnerShipServiceImpl implements DimOwnerShipService {

	@Autowired
	DimOwnerShipDAO dimOwnerShipDAO;

	@Override
	public List<DimOwnerShip> getAllDimOwnerShips() {
		return dimOwnerShipDAO.getAllDimOwnerShips();
	}

	@Override
	@Transactional
	public void deleteDimOwnerShip(List<DimOwnerShip> dimOwnerShips) {
		for (DimOwnerShip dimOwnerShip : dimOwnerShips) {
			dimOwnerShipDAO.deleteEntity(dimOwnerShip);
		}
	}

	@Override
	@Transactional
	public void saveDimOwnerShip(List<DimOwnerShip> dimOwnerShips) {
		for (DimOwnerShip dimOwnerShip : dimOwnerShips) {
			dimOwnerShipDAO.saveOrUpdateEntity(dimOwnerShip);
		}
	}

}
