package com.qifei.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.qifei.dao.DimIndustryDAO;
import com.qifei.model.DimIndustry;

@Repository
public class DimIndustryDAOImpl extends BasicHibernateDAOImpl implements DimIndustryDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<DimIndustry> getAllDimIndustrys() {
		String hql = "from DimIndustry";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return DimIndustry.class;
	}

	@Override
	public int getMaxDimIndustryId() {
		int id = 0;
		String hql = "select max(industryId) from DimIndustry";
		id = (Integer)sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
		return id+1;
	}

	@Override
	public DimIndustry getIndustryByName(String name) {
		// TODO Auto-generated method stub
		DimIndustry industry = new DimIndustry();
		String hql = "from DimIndustry where industryName = '" + name + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(query.list().size() > 0){
			industry = (DimIndustry) query.list().get(0);
		}
		return industry;
	}
}
