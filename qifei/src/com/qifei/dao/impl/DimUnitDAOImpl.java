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

	@Override
	public int getMaxUnitId() {
		int id = 0;
		String hql = "select max(unitId) from DimUnit";
		id = (Integer)sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
		return id+1;
	}

	@Override
	public DimUnit getUnitByName(String name) {
		// TODO Auto-generated method stub
		DimUnit unit = new DimUnit();
		String hql = "from DimUnit where unitName = '" + name + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(query.list().size() > 0){
			unit = (DimUnit) query.list().get(0);
		}
		return unit;
	}

}
