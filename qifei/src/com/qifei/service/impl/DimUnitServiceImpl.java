package com.qifei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qifei.dao.DimUnitDAO;
import com.qifei.model.DimUnit;
import com.qifei.service.DimUnitService;

@Service
public class DimUnitServiceImpl implements DimUnitService {

	@Autowired
	DimUnitDAO dimUnitDao;

	@Override
	public List<DimUnit> getAllDimUnits() {
		return dimUnitDao.getAllDimUnits();
	}

	@Override
	@Transactional
	public void deleteDimUnit(List<DimUnit> dimUnits) {
		for (DimUnit dimUnit : dimUnits) {
			dimUnitDao.deleteEntity(dimUnit);
		}
	}

	@Override
	@Transactional
	public void saveDimUnit(List<DimUnit> dimUnits) {
		for (DimUnit dimUnit : dimUnits) {
			dimUnitDao.saveOrUpdateEntity(dimUnit);
		}
	}

	@Override
	public int getMaxUnitId() {
		return dimUnitDao.getMaxUnitId();
	}

}
