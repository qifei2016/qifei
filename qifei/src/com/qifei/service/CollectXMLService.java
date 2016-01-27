package com.qifei.service;

import java.util.List;

import com.qifei.model.CollectXML;

public interface CollectXMLService {
	
	public List<CollectXML> getAllCollectXML();
	
	public void deleteCollectXML(List<CollectXML> collectXML);
	
	public void saveCollectXML(List<CollectXML> collectXML);

}