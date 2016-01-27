package com.qifei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qifei.dao.CollectSourcesDAO;
import com.qifei.model.CollectSources;
import com.qifei.service.CollectSourcesService;

@Service
public class CollectSourcesServiceImpl implements CollectSourcesService {

	@Autowired
	CollectSourcesDAO collectSourcesDAO;

	@Override
	public List<CollectSources> getAllCollectSources() {
		return collectSourcesDAO.getAllCollectSources();
	}

	@Override
	@Transactional
	public void deleteCollectSources(List<CollectSources> collectSources) {
		for (CollectSources collectSource : collectSources) {
			collectSourcesDAO.deleteEntity(collectSource);
		}
	}

	@Override
	@Transactional
	public void saveCollectSources(List<CollectSources> collectSources) {
		for (CollectSources collectSource : collectSources) {
			collectSourcesDAO.saveOrUpdateEntity(collectSource);
		}
	}

}
