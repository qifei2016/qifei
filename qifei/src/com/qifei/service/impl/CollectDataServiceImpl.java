package com.qifei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qifei.dao.CollectDataDAO;
import com.qifei.model.CollectData;
import com.qifei.service.CollectDataService;

@Service
public class CollectDataServiceImpl implements CollectDataService {

	@Autowired
	CollectDataDAO collectDataDAO;

	@Override
	public List<CollectData> getAllCollectDatas(int startRecode, int maxRecode) {
		return collectDataDAO.getAllCollectDatas(startRecode, maxRecode);
	}

	@Override
	public List<CollectData> queryCollectDataByCollectItemId(
			String collectItemId) {
		return collectDataDAO.queryCollectDataByCollectItemId(collectItemId);
	}

	@Override
	@Transactional
	public void deleteCollectData(List<CollectData> collectDatas) {
		for (CollectData collectData : collectDatas) {
			collectDataDAO.deleteEntity(collectData);
		}
	}

	@Override
	@Transactional
	public void saveCollectData(List<CollectData> collectDatas) {
		for (CollectData collectData : collectDatas) {
			collectDataDAO.saveOrUpdateEntity(collectData);
		}
	}

}
