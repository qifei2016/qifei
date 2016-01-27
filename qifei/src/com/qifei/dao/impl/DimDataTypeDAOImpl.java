package com.qifei.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qifei.dao.DimDataTypeDAO;
import com.qifei.model.DimDataType;

@Repository
public class DimDataTypeDAOImpl extends BasicHibernateDAOImpl implements
		DimDataTypeDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<DimDataType> getAllDimDataType() {
		String hql = "from DimDataType";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return DimDataType.class;
	}
}
