package com.qifei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qifei.dao.DimIndustryDAO;
import com.qifei.model.DimIndustry;
import com.qifei.service.DimIndustryService;

@Service
public class DimIndustryServiceImpl implements DimIndustryService {

	@Autowired
	DimIndustryDAO dimIndustryDAO;

	@Override
	public List<DimIndustry> getAllDimIndustrys() {
		return dimIndustryDAO.getAllDimIndustrys();
	}

	@Override
	@Transactional
	public void deleteDimIndustry(List<DimIndustry> dimIndustrys) {
		for (DimIndustry dimIndustry : dimIndustrys) {
			dimIndustryDAO.deleteEntity(dimIndustry);
		}
	}

	@Override
	@Transactional
	public void saveDimIndustry(List<DimIndustry> dimIndustrys) {
		for (DimIndustry dimIndustry : dimIndustrys) {
			dimIndustryDAO.saveOrUpdateEntity(dimIndustry);
		}
	}

	@Override
	public int getMaxDimIndustryId() {
		return dimIndustryDAO.getMaxDimIndustryId();
	}

}
