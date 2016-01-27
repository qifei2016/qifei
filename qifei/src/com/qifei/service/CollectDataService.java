package com.qifei.service;

import java.util.List;

import com.qifei.model.CollectData;

public interface CollectDataService {

	public List<CollectData> getAllCollectDatas(int startRecode, int maxRecode);

	public List<CollectData> queryCollectDataByCollectItemId(String collectItemId);
	
	public void deleteCollectData(List<CollectData> collectData);
	
	public void saveCollectData(List<CollectData> collectData);
	
}
