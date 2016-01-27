package com.qifei.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qifei.dao.DimOwnerShipDAO;
import com.qifei.model.DimOwnerShip;

@Repository
public class DimOwnerShipDAOImpl extends BasicHibernateDAOImpl implements DimOwnerShipDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<DimOwnerShip> getAllDimOwnerShips() {
		String hql = "from DimOwnerShip";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return DimOwnerShip.class;
	}
}
