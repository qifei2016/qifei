package com.qifei.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qifei.dao.CollectXMLDAO;
import com.qifei.model.CollectXML;

@Repository
public class CollectXMLDAOImpl extends BasicHibernateDAOImpl implements CollectXMLDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<CollectXML> getAllCollectXML() {
		String hql = "from CollectXML";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return CollectXML.class;
	}

}
