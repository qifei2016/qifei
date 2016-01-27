package com.qifei.dao;

import java.util.List;

import com.qifei.model.CollectData;

public interface CollectDataDAO extends BasicDAO {
	
	public CollectData getCollectData(String itemid);

	public void saveCollectData(CollectData data);
	
	public int getMaxCollectId();

	public List<CollectData> getAllCollectDatas(int startRecode, int maxRecode);

	public List<CollectData> queryCollectDataByCollectItemId(String collectItemId);
	
}