package com.qifei.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qifei.dao.DimCategoryDAO;
import com.qifei.model.DimCategory;

@Repository
public class DimCategoryDAOImpl extends BasicHibernateDAOImpl implements
		DimCategoryDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<DimCategory> getAllDimCategory() {
		String hql = "from DimCategory";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return DimCategory.class;
	}
}
