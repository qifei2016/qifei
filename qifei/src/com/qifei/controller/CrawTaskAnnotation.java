package com.qifei.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qifei.model.CollectItem;
import com.qifei.service.CollectItemService;
import com.qifei.service.ConfigPageService;
import com.qifei.thread.ThreadCrawler;
import com.qifei.vo.CollectItemVO;

/**
 * 基于注解的定时器
 */
@Component
@Controller
@RequestMapping("/crawTaskAnnotation")
public class CrawTaskAnnotation {

//	Log logger = LogFactory.getLog(CrawTaskAnnotation.class.getClass());

	@Autowired
	CollectItemService collectItemService;

	@Autowired
	ConfigPageService configservice;

	/**
	 * 定时执行。每天凌晨 08:00 执行一次
	 * 
	 * @throws Exception
	 */
	@Scheduled(cron = "0 17 18 * * *")
	@RequestMapping("/crawOneDay")
	public void crawOneDay() throws Exception {
//		logger.info("*****************【crawOneDay】执行开始*******************");
		List<CollectItem> collectItemVOs = collectItemService
				.getAllCollectItems();
		for (CollectItem collectItemVO : collectItemVOs) {
			String status = collectItemVO.getStatus();
			if (null != status && status.equals("1")) {
				Integer collectItemId = collectItemVO.getCollectItemId();
				if (null != collectItemId) {
					String itemid = String.valueOf(collectItemId);
					new Thread(new ThreadCrawler(configservice, itemid))
							.start();
				}
			}
		}
//		logger.info("*****************【crawOneDay】执行结束*******************");
	}

	/**
	 * 心跳更新。启动时执行一次，之后每隔1000*30 秒执行一次
	 * 
	 * @throws Exception
	 */
	@Scheduled(fixedRate = 1000 * 60*60)
	@RequestMapping("/crawOneHour")
	public void crawOneHour() throws Exception {
//		logger.info("*****************【crawOneHour】执行开始*******************");
//		List<CollectItem> collectItemVOs = collectItemService
//				.getAllCollectItems();
		List<CollectItemVO> collectItemVOs = collectItemService
				.getAllEnableItems();
		for (CollectItemVO collectItemVO : collectItemVOs) {
//			String status = collectItemVO.getStatus();
//			if (null != status && status.equals("1")) {
				Integer collectItemId = collectItemVO.getCollectItemId();
				if (null != collectItemId) {
					String itemid = String.valueOf(collectItemId);
					new Thread(new ThreadCrawler(configservice, itemid))
							.start();
				}
//			}
		}
//		logger.info("*****************【crawOneHour】执行结束*******************");

	}
}