package com.qifei.service;


import java.util.HashMap;
import java.util.List;

import com.qifei.model.CollectData;
import com.qifei.model.ConfigParam;

public interface ConfigPageService {
	
	public ConfigParam getConfigById(String itemid);
	
	public int deleteConfigById(String itemid) throws Exception;
	
	public void addConfig(ConfigParam param, String itemid) throws Exception;
	
	public List<CollectData> crawlData(ConfigParam param, String itemid);
	
	public List<HashMap> crawlData(ConfigParam param);
	
	public List checkData(List<HashMap> list, ConfigParam param);
	
	public void saveCollectData(CollectData data);
}
