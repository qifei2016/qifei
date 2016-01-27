package com.qifei.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qifei.dao.CollectSourcesDAO;
import com.qifei.model.CollectSources;

@Repository
public class CollectSourcesDAOImpl extends BasicHibernateDAOImpl implements CollectSourcesDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<CollectSources> getAllCollectSources() {
		String hql = "from CollectSources";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return CollectSources.class;
	}

}
