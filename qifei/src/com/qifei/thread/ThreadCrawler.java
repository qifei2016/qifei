package com.qifei.thread;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qifei.model.CollectData;
import com.qifei.model.ConfigParam;
import com.qifei.service.ConfigPageService;

public class ThreadCrawler implements Runnable {

	Log logger = LogFactory.getLog(ThreadCrawler.class.getClass());

	ConfigPageService configservice = null;

	String itemid = null;

	public ThreadCrawler(ConfigPageService configservice, String itemid) {
		this.configservice = configservice;
		this.itemid = itemid;
	}

	public void run() {
		logger.info("**************【itemid】:" + itemid + "抓取数据开始*************");
		ConfigParam param = configservice.getConfigById(itemid);
		List<CollectData> collectdatalist = configservice.crawlData(param,
				itemid);
		for (int i = 0; i < collectdatalist.size(); i++) {
			CollectData collectdata = collectdatalist.get(i);
			configservice.saveCollectData(collectdata);
		}
		logger.info("************【itemid】:" + itemid + "抓取数据结束*************");
	}

}
