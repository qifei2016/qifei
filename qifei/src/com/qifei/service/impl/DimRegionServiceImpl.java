package com.qifei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qifei.dao.DimRegionDAO;
import com.qifei.model.DimRegion;
import com.qifei.service.DimRegionService;

@Service
public class DimRegionServiceImpl implements DimRegionService {

	@Autowired
	DimRegionDAO dimRegionDAO;

	@Override
	public List<DimRegion> getAllDimRegions() {
		return dimRegionDAO.getAllDimRegions();
	}

	@Override
	@Transactional
	public void deleteDimRegion(List<DimRegion> dimRegions) {
		for (DimRegion dimRegion : dimRegions) {
			dimRegionDAO.deleteEntity(dimRegion);
		}
	}

	@Override
	@Transactional
	public void saveDimRegion(List<DimRegion> dimRegions) {
		for (DimRegion dimRegion : dimRegions) {
			dimRegionDAO.saveOrUpdateEntity(dimRegion);
		}
	}

	@Override
	public int getMaxRegionId() {
		return dimRegionDAO.getMaxRegionId();
	}

	@Override
	public DimRegion getRegionByName(String name) {
		// TODO Auto-generated method stub
		return dimRegionDAO.getRegionByName(name);
	}

}
