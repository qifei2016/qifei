package com.qifei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qifei.dao.DimDateTypeDAO;
import com.qifei.model.DimDateType;
import com.qifei.service.DimDateTypeService;

@Service
public class DimDateTypeServiceImpl implements DimDateTypeService {

	@Autowired
	DimDateTypeDAO dimDateTypeDAO;

	@Override
	public List<DimDateType> getAllDimDateType() {
		return dimDateTypeDAO.getAllDimDateType();
	}

	@Override
	@Transactional
	public void deleteDimDateType(List<DimDateType> dimDateTypes) {
		for (DimDateType dimDateType : dimDateTypes) {
			dimDateTypeDAO.deleteEntity(dimDateType);
		}
	}

	@Override
	@Transactional
	public void saveDimDateType(List<DimDateType> dimDateTypes) {
		for (DimDateType dimDateType : dimDateTypes) {
			dimDateTypeDAO.saveOrUpdateEntity(dimDateType);
		}
	}

}
