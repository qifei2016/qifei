package com.qifei.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qifei.dao.DimUnitDAO;
import com.qifei.model.DimUnit;

@Repository
public class DimUnitDAOImpl extends BasicHibernateDAOImpl implements DimUnitDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<DimUnit> getAllDimUnits() {
		String hql = "from DimUnit";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return DimUnit.class;
	}

}
