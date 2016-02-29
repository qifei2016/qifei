package com.qifei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qifei.dao.DimBaseclassDAO;
import com.qifei.model.DimBaseclass;
import com.qifei.service.DimBaseclassService;

@Service
public class DimBaseclassServiceImpl implements DimBaseclassService {

	@Autowired
	DimBaseclassDAO dimBaseclassDAO;

	@Override
	public List<DimBaseclass> getAllDimBaseclass() {
		return dimBaseclassDAO.getAllDimBaseclass();
	}

	@Override
	@Transactional
	public void deleteDimBaseclass(List<DimBaseclass> dimBaseclass) {
		for (DimBaseclass dimBaseclass2 : dimBaseclass) {
			dimBaseclassDAO.deleteEntity(dimBaseclass2);
		}
	}

	@Override
	@Transactional
	public void saveDimBaseclass(List<DimBaseclass> dimBaseclass) {
		for (DimBaseclass dimBaseclass2 : dimBaseclass) {
			dimBaseclassDAO.saveOrUpdateEntity(dimBaseclass2);
		}
	}

	@Override
	public int getMaxBaseclassId() {
		return dimBaseclassDAO.getMaxBaseclassId();
	}

	@Override
	public DimBaseclass getBaseclassByName(String name) {
		// TODO Auto-generated method stub
		return dimBaseclassDAO.getBaseclassByName(name);
	}

}
