package com.qifei.service;

import java.util.List;

import com.qifei.model.CollectSources;

public interface CollectSourcesService {
	
	public List<CollectSources> getAllCollectSources();
	
	public void deleteCollectSources(List<CollectSources> collectSources);
	
	public void saveCollectSources(List<CollectSources> collectSources);

}