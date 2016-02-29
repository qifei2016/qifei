package com.qifei.service;

import java.util.List;

import com.qifei.model.CollectItem;
import com.qifei.vo.CollectItemVO;

public interface CollectItemService {

	public List<CollectItemVO> getAllCollectItemVOs();
	
	public List<CollectItem> getAllCollectItems();

	public List<CollectItemVO> queryCollectItems(String name,String collectKeywords, String datetype,
			String region, String industry, String category, String captureState, int startRecode,
			int maxRecode);
	
	public Integer getCollectItemsCount(String name, String collectKeywords,
			String unit, String region, String industry, String category, String captureState);
	
	public CollectItemVO queryCollectItemByCollectItemId(String collectItemId);
	
	public void deleteCollectItem(List<CollectItem> collectItem);
	
	public void saveCollectItem(List<CollectItem> collectItem);
	
	public CollectItem saveCollectItem(CollectItem collectItem);
	
	public CollectItem updateCollectItem(CollectItem collectItem);
	
	public void updateItemStateByItemId(String itemId, String statue);
	
	public List<CollectItemVO> getAllEnableItems();
	
	public boolean checkItemName(String itemName, String itemId);
}
