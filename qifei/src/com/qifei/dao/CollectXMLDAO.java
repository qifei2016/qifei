package com.qifei.dao;

import java.util.List;

import com.qifei.model.CollectXML;

public interface CollectXMLDAO extends BasicDAO {
	
	public List<CollectXML> getAllCollectXML();
	
}