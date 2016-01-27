package com.qifei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qifei.dao.DimDataTypeDAO;
import com.qifei.model.DimDataType;
import com.qifei.service.DimDataTypeService;

@Service
public class DimDataTypeServiceImpl implements DimDataTypeService {

	@Autowired
	DimDataTypeDAO dimDataTypeDAO;

	@Override
	public List<DimDataType> getAllDimDataType() {
		return dimDataTypeDAO.getAllDimDataType();
	}

	@Override
	@Transactional
	public void deleteDimDataType(List<DimDataType> dimDataTypes) {
		for (DimDataType dimDataType : dimDataTypes) {
			dimDataTypeDAO.deleteEntity(dimDataType);
		}
	}

	@Override
	@Transactional
	public void saveDimDataType(List<DimDataType> dimDataTypes) {
		for (DimDataType dimDataType : dimDataTypes) {
			dimDataTypeDAO.saveOrUpdateEntity(dimDataType);
		}
	}

}
