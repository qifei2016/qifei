package com.qifei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qifei.dao.CollectXMLDAO;
import com.qifei.model.CollectXML;
import com.qifei.service.CollectXMLService;

@Service
public class CollectXMLServiceImpl implements CollectXMLService {

	@Autowired
	CollectXMLDAO collectXMLDAO;

	@Override
	public List<CollectXML> getAllCollectXML() {
		return collectXMLDAO.getAllCollectXML();
	}

	@Override
	@Transactional
	public void deleteCollectXML(List<CollectXML> collectXMLs) {
		for (CollectXML collectXML : collectXMLs) {
			collectXMLDAO.deleteEntity(collectXML);		
		}
	}

	@Override
	@Transactional
	public void saveCollectXML(List<CollectXML> collectXMLs) {
		for (CollectXML collectXML : collectXMLs) {
			collectXMLDAO.saveOrUpdateEntity(collectXML);
		}		
	}

}
