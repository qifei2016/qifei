package com.qifei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qifei.dao.DimCategoryDAO;
import com.qifei.model.DimCategory;
import com.qifei.service.DimCategoryService;

@Service
public class DimCategoryServiceImpl implements DimCategoryService {

	@Autowired
	DimCategoryDAO dimCategoryDAO;

	@Override
	public List<DimCategory> getAllDimCategory() {
		return dimCategoryDAO.getAllDimCategory();
	}

	@Override
	@Transactional
	public void deleteDimCategory(List<DimCategory> dimCategorys) {
		for (DimCategory dimCategory : dimCategorys) {
			dimCategoryDAO.deleteEntity(dimCategory);
		}
	}

	@Override
	@Transactional
	public void saveDimCategory(List<DimCategory> dimCategorys) {
		for (DimCategory dimCategory : dimCategorys) {
			dimCategoryDAO.saveOrUpdateEntity(dimCategory);
		}
	}

}
