package com.qifei.thread;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import com.qifei.model.CollectData;
import com.qifei.model.CollectItem;
import com.qifei.service.CollectDataService;
import com.qifei.service.CollectItemService;

public class ThreadCollectItemCrawler implements Runnable {

	Log logger = LogFactory.getLog(ThreadCollectItemCrawler.class.getClass());

	CollectItemService collectItemService = null;
	CollectDataService collectDataService = null;

	List<CollectItem> collectItems = null;
	List<CollectData> collectDatas = null;

	public ThreadCollectItemCrawler(CollectItemService collectItemService,
			CollectDataService collectDataService,
			List<CollectItem> collectItems, List<CollectData> collectDatas) {
		this.collectDatas = collectDatas;
		this.collectItems = collectItems;
	}

	public void run() {
		if (!CollectionUtils.isEmpty(collectItems)) {
			collectItemService.saveCollectItem(collectItems);
		}
		if (!CollectionUtils.isEmpty(collectDatas)) {
			collectDataService.saveCollectData(collectDatas);
		}

	}

}
