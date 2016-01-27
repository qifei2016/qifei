package com.qifei.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qifei.dao.CollectDataDAO;
import com.qifei.model.CollectData;

@Transactional
@Repository
public class CollectDataDAOImpl extends BasicHibernateDAOImpl implements
		CollectDataDAO {

	@Override
	public CollectData getCollectData(String itemid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCollectData(CollectData data) {
		this.saveOrUpdateEntity(data);
	}
	

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return CollectData.class;
	}

	@Override
	public int getMaxCollectId() {
		int id = 0;
		String hql = "select max(collectId) from CollectData";
		id = (Integer)sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
		return id+1;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CollectData> getAllCollectDatas(int startRecode, int maxRecode) {
		String hql = "from CollectData";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(startRecode);
		query.setMaxResults(maxRecode);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CollectData> queryCollectDataByCollectItemId(
			String collectItemId) {
		String hql = "from CollectData where collectItemId = "
				+ collectItemId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
}
