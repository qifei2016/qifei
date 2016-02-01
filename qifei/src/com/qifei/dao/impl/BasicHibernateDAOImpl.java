package com.qifei.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifei.dao.BasicDAO;

/**
 * 基于Hibernate持久层解决方案
 * 
 * @author long
 * 
 */
@Repository
public abstract class BasicHibernateDAOImpl implements BasicDAO {

	@Autowired
	public SessionFactory sessionFactory;

	@SuppressWarnings("rawtypes")
	public abstract Class getEntityClass();

	public void deleteEntity(Object obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@SuppressWarnings("rawtypes")
	public List getAllEntitys() {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from " + getEntityClass().getName());
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	public List getAllEntitys(int startRecode, int maxRecode) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from " + getEntityClass().getName());
		query.setFirstResult(startRecode);
		query.setMaxResults(maxRecode);
		return query.list();
	}

	public Object getEntityByKey(Serializable key) {
		return sessionFactory.getCurrentSession().load(getEntityClass(), key);
	}

	@SuppressWarnings("rawtypes")
	public int getEntitysCountRow() {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from " + getEntityClass().getName());
		List list = query.list();
		if (list != null) {
			return (Integer) list.get(0);
		}
		return 0;
	}

	public void saveEntity(Object obj) {
		sessionFactory.getCurrentSession().save(obj);
	}

	public Object saveOrUpdateEntity(Object obj) {
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
		sessionFactory.getCurrentSession().flush();
		return obj;
	}
	
	public Object updateEntity(Object obj) {
		sessionFactory.getCurrentSession().update(obj);
		return obj;
	}

}
