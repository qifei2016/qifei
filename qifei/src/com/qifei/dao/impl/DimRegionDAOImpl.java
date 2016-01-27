package com.qifei.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qifei.dao.DimRegionDAO;
import com.qifei.model.DimRegion;

@Repository
public class DimRegionDAOImpl extends BasicHibernateDAOImpl implements DimRegionDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<DimRegion> getAllDimRegions() {
		String hql = "from DimRegion";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return DimRegion.class;
	}

}
