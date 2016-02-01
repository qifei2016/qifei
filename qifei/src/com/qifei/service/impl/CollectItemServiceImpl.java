package com.qifei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qifei.dao.CollectItemDAO;
import com.qifei.model.CollectItem;
import com.qifei.service.CollectItemService;
import com.qifei.vo.CollectItemVO;

@Service
public class CollectItemServiceImpl implements CollectItemService {

	@Autowired
	CollectItemDAO collectItemDAO;

	@Override
	public List<CollectItemVO> getAllCollectItemVOs() {
		return collectItemDAO.getAllCollectItems();
	}

	@Override
	public List<CollectItemVO> queryCollectItems(String name,
			String collectKeywords, String unit, String region,
			String industry, String baseclass, int startRecode, int maxRecode) {

		return collectItemDAO.queryCollectItems(name, collectKeywords, unit,
				region, industry, baseclass, startRecode, maxRecode);
	}

	@Override
	public CollectItemVO queryCollectItemByCollectItemId(String collectItemId) {
		return collectItemDAO.queryCollectItemByCollectItemId(collectItemId);
	}

	@Override
	@Transactional
	public void deleteCollectItem(List<CollectItem> collectItems) {
		for (CollectItem collectItem : collectItems) {
			collectItemDAO.deleteCollectItem(collectItem.getCollectItemId());
		}
	}

	@Override
	@Transactional
	public void saveCollectItem(List<CollectItem> collectItems) {
		for (CollectItem collectItem : collectItems) {
			collectItemDAO.saveOrUpdateEntity(collectItem);
		}
	}

	@Transactional
	public CollectItem saveCollectItem(CollectItem collectItem) {
		collectItem = (CollectItem) collectItemDAO
				.saveOrUpdateEntity(collectItem);
		System.out.println("collectitem:"+collectItem.getCollectItemDesc());
		return collectItem;
	}

	@Override
	public void updateItemStateByItemId(String itemId, String statue) {
		collectItemDAO.updateItemStateByItemId(itemId, statue);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CollectItem> getAllCollectItems() {
		return collectItemDAO.getAllEntitys();
	}

	@Override
	public List<CollectItemVO> getAllEnableItems() {
		// TODO Auto-generated method stub
		return collectItemDAO.getAllEnableItems();
	}

	@Override
	public Integer getCollectItemsCount(String name, String collectKeywords,
			String unit, String region, String industry, String category) {
		return collectItemDAO.getCollectItemsCount(name, collectKeywords, unit,
				region, industry, category);
	}

	@Override
	@Transactional
	public CollectItem updateCollectItem(CollectItem collectItem) {
		return (CollectItem) collectItemDAO.updateEntity(collectItem);
	}

	@Override
	public boolean checkItemName(String itemName, String itemId) {
		return collectItemDAO.checkItemName(itemName, itemId);
	}

}
