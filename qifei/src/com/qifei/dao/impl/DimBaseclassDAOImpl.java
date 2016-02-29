package com.qifei.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qifei.dao.DimBaseclassDAO;
import com.qifei.model.DimBaseclass;

@Repository
public class DimBaseclassDAOImpl extends BasicHibernateDAOImpl implements
		DimBaseclassDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<DimBaseclass> getAllDimBaseclass() {
		String hql = "from DimBaseclass";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return DimBaseclass.class;
	}

	@Override
	public int getMaxBaseclassId() {
		int id = 0;
		String hql = "select max(baseclassId) from DimBaseclass";
		id = (Integer)sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
		return id+1;
	}

	@Override
	public DimBaseclass getBaseclassByName(String name) {
		// TODO Auto-generated method stub
		DimBaseclass base = new DimBaseclass();
		String hql = "from DimBaseclass where baseclassName = '" + name + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(query.list().size() > 0){
			base = (DimBaseclass) query.list().get(0);
		}
		return base;
	}
}
