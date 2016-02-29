package com.qifei.dao;

import java.util.List;

import com.qifei.model.CollectItem;
import com.qifei.vo.CollectItemVO;

public interface CollectItemDAO extends BasicDAO {

	public List<CollectItemVO> getAllCollectItems();

	public List<CollectItemVO> queryCollectItems(String name,
			String collectKeywords, String datetype, String region,
			String industry, String category, String captureState, int startRecode, int maxRecode);

	public Integer getCollectItemsCount(String name,String collectKeywords, String unit,
			String region, String industry, String category);
	
	public CollectItemVO queryCollectItemByCollectItemId(String collectItemId);

	public void updateItemStateByItemId(String itemId, String statue);

	public CollectItem saveCollectItem(CollectItem collectItem);
	
	public void deleteCollectItem(int collectitemid);
	
	public List<CollectItemVO> getAllEnableItems();
	
	public boolean checkItemName(String itemName, String itemId);
	
	public void updateItemCaptureStateByItemId(String itemId,
			String captureState);
}