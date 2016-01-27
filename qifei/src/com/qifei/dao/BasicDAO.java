package com.qifei.dao;

import java.io.Serializable;
import java.util.List;

public interface BasicDAO {

	public void saveEntity(Object obj);

	public Object updateEntity(Object obj);

	public void deleteEntity(Object obj);

	public Object saveOrUpdateEntity(Object obj);
	
	public Object getEntityByKey(Serializable key);
	
	@SuppressWarnings("rawtypes")
	public List getAllEntitys();
	
	@SuppressWarnings("rawtypes")
	public List getAllEntitys(int startRecode, int maxRecode);
	
	public int getEntitysCountRow();
}
