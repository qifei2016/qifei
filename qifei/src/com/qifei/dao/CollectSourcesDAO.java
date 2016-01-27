package com.qifei.dao;

import java.util.List;

import com.qifei.model.CollectSources;

public interface CollectSourcesDAO extends BasicDAO {
	
	public List<CollectSources> getAllCollectSources();
	
}