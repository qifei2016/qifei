package com.qifei.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qifei.dao.DimDateTypeDAO;
import com.qifei.model.DimDateType;

@Repository
public class DimDateTypeDAOImpl extends BasicHibernateDAOImpl implements
		DimDateTypeDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<DimDateType> getAllDimDateType() {
		String hql = "from DimDateType";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return DimDateType.class;
	}
}
