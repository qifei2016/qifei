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

	@Override
	public int getMaxRegionId() {
		int id = 0;
		String hql = "select max(regionId) from DimRegion";
		id = (Integer)sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
		return id+1;
	}

	@Override
	public DimRegion getRegionByName(String name) {
		// TODO Auto-generated method stub
		DimRegion region = new DimRegion();
		String hql = "from DimRegion where regionName = '" + name + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(query.list().size() > 0){
			region = (DimRegion) query.list().get(0);
		}
		return region;
	}

}
