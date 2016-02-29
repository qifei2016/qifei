package com.qifei.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.qifei.dao.CollectItemDAO;
import com.qifei.model.CollectItem;
import com.qifei.vo.CollectItemVO;

@Repository
public class CollectItemDAOImpl extends BasicHibernateDAOImpl implements
		CollectItemDAO {

	@Override
	public List<CollectItemVO> getAllCollectItems() {
		String sql = "select t.COLLECT_ITEM_ID, t.COLLECT_ITEM_DESC, t.COLLECT_SOURCE, t.COLLECT_URL, t.BASECLASS_ID, t.INDUSTRY_ID, t.UNIT_ID, t.DATATYPE_ID, t.DATETYPE_ID, "
				+ " t.REGION_ID, t.XML_ID, t.COLLECT_KEYWORDS, t.IS_VALID, t.LAST_UPDATE_TIME, t.REMARK, t.Status, t3.BASECLASS_NAME, "
				+ " t6.UNIT_NAME, t7.REGION_NAME, t8.INDUSTRY_NAME "
				+ "from t_collect_item t "
				+ "join t_dim_baseclass t3 on t.BASECLASS_ID=t3.BASECLASS_ID "
				+ "join t_dim_unit t6 on t.UNIT_ID=t6.UNIT_ID "
				+ "join t_dim_region t7 on t.REGION_ID=t7.REGION_ID "
				+ "join t_dim_industry t8 on t.INDUSTRY_ID=t8.INDUSTRY_ID ";

//		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		Query query = sessionFactory.openSession().createSQLQuery(sql);

		@SuppressWarnings("unchecked")
		List<Object> list = query.list();
		List<CollectItemVO> collectItemVOs = conversionCollectItemVOs(list);

		return collectItemVOs;
	}

	@Override
	public List<CollectItemVO> queryCollectItems(String name, String collectKeywords, String unit,
			String region, String industry, String baseclass, String captureState, int startRecode,
			int maxRecode) {
		String sql = "select t.COLLECT_ITEM_ID, t.COLLECT_ITEM_DESC, t.COLLECT_SOURCE, t.COLLECT_URL, t.BASECLASS_ID, t.INDUSTRY_ID, t.UNIT_ID, t.DATATYPE_ID, t.DATETYPE_ID, "
				+ " t.REGION_ID, t.XML_ID, t.COLLECT_KEYWORDS, t.IS_VALID, t.LAST_UPDATE_TIME, t.REMARK, t.Status, t3.BASECLASS_NAME, "
				+ " t6.UNIT_NAME, t7.REGION_NAME, t8.INDUSTRY_NAME "
				+ "from t_collect_item t "
				+ "join t_dim_baseclass t3 on t.BASECLASS_ID=t3.BASECLASS_ID "
				+ "join t_dim_unit t6 on t.UNIT_ID=t6.UNIT_ID "
				+ "join t_dim_region t7 on t.REGION_ID=t7.REGION_ID "
				+ "join t_dim_industry t8 on t.INDUSTRY_ID=t8.INDUSTRY_ID ";

		String condition = "";

		if (!StringUtils.isEmpty(name)) {
//			condition = condition + "t.COLLECT_ITEM_DESC like '%" + name
//					+ "%' and ";
			condition = condition + " instr(t.COLLECT_ITEM_DESC,'"+name+"')<>0 and ";
		}
		if (!StringUtils.isEmpty(collectKeywords)) {
			condition = condition + "t.COLLECT_KEYWORDS like '%" + collectKeywords
					+ "%' and ";
		}
		if (!StringUtils.isEmpty(captureState) && !captureState.equals("0")) {
			condition = condition + "t.CLASS2 in (" + captureState + ") and ";
		}
		if (!StringUtils.isEmpty(unit)) {
			condition = condition + "t.UNIT_ID in (" + unit + ") and ";
		}
		if (!StringUtils.isEmpty(region)) {
			condition = condition + "t.REGION_ID in (" + region + ") and ";
		}
		if (!StringUtils.isEmpty(industry)) {
			condition = condition + "t.INDUSTRY_ID in (" + industry + ") and ";
		}
		if (!StringUtils.isEmpty(baseclass)) {
			condition = condition + "t3.BASECLASS_ID in (" + baseclass + ") and ";
		}
		if (!StringUtils.isEmpty(condition)) {
			sql = sql + " where "
					+ condition.substring(0, condition.length() - 4)
					+ "order by t.collect_item_id";
		}

		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setFirstResult(startRecode);
		query.setMaxResults(maxRecode);

		@SuppressWarnings("unchecked")
		List<Object> list = query.list();

		List<CollectItemVO> collectItemVOs = conversionCollectItemVOs(list);

		return collectItemVOs;
	}

	@Override
	public Integer getCollectItemsCount(String name, String collectKeywords,
			String unit, String region, String industry, String baseclass, String captureState) {
		String sql = "select count(t.COLLECT_ITEM_ID) from t_collect_item t "
				+ "join t_dim_baseclass t3 on t.BASECLASS_ID=t3.BASECLASS_ID "
				+ "join t_dim_unit t6 on t.UNIT_ID=t6.UNIT_ID "
				+ "join t_dim_region t7 on t.REGION_ID=t7.REGION_ID "
				+ "join t_dim_industry t8 on t.INDUSTRY_ID=t8.INDUSTRY_ID ";

		String condition = "";

		if (!StringUtils.isEmpty(name)) {
			condition = condition + " instr(t.COLLECT_ITEM_DESC,'" + name
					+ "')<>0 and ";
		}
		if (!StringUtils.isEmpty(collectKeywords)) {
			condition = condition + "t.COLLECT_KEYWORDS like '%"
					+ collectKeywords + "%' and ";
		}
		if (!StringUtils.isEmpty(captureState) && !captureState.equals("0")) {
			condition = condition + "t.CLASS2 in (" + captureState + ") and ";
		}
		if (!StringUtils.isEmpty(unit)) {
			condition = condition + "t.UNIT_ID in (" + unit + ") and ";
		}
		if (!StringUtils.isEmpty(region)) {
			condition = condition + "t.REGION_ID in (" + region + ") and ";
		}
		if (!StringUtils.isEmpty(industry)) {
			condition = condition + "t.INDUSTRY_ID in (" + industry + ") and ";
		}
		if (!StringUtils.isEmpty(baseclass)) {
			condition = condition + "t3.BASECLASS_ID in (" + baseclass
					+ ") and ";
		}
		if (!StringUtils.isEmpty(condition)) {
			sql = sql + " where "
					+ condition.substring(0, condition.length() - 4)
					+ "order by t.collect_item_id";
		}

		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List list = query.list();
		if (list != null) {
			BigInteger bigInteger = (BigInteger) list.get(0);
			return bigInteger.intValue();
		}
		return 0;
	}
	
	private List<CollectItemVO> conversionCollectItemVOs(List<Object> list) {
		List<CollectItemVO> collectItemVOs = new ArrayList<CollectItemVO>();
		for (Object object : list) {
			Object[] modle = (Object[]) object;
			CollectItemVO vo = new CollectItemVO();
			vo.setCollectItemId((Integer) modle[0]);
			vo.setCollectItemDesc((String) modle[1]);
			vo.setCollectSource((Integer) modle[2]);
			vo.setCollectURL((String) modle[3]);
			vo.setBaseclassId((Integer) modle[4]);
			vo.setIndustryId((Integer) modle[5]);
			vo.setUnitId((Integer) modle[6]);
			vo.setDataTypeId((Integer) modle[7]);
			vo.setDateTypeID((Integer) modle[8]);
			vo.setRegionId((Integer) modle[9]);
			vo.setXmlId((Integer) modle[10]);
			vo.setCollectKeywords((String) modle[11]);
			vo.setIsValId((Integer) modle[12]);
			Date date = (Date) modle[13];
			if(date != null){
				vo.setLastUpdateTime(date.toString());
			}
			vo.setRemark((String) modle[14]);
			String status = (String) modle[15];
			if (StringUtils.isEmpty(status) || status.equals("0")) {
				vo.setStatus("停用");
			} else {
				vo.setStatus("启用");
			}
			vo.setBassclassName((String) modle[16]);
			vo.setUnitName((String) modle[17]);
			vo.setRegionName((String) modle[18]);
			vo.setIndustryName((String) modle[19]);
			collectItemVOs.add(vo);
		}
		return collectItemVOs;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return CollectItem.class;
	}

	@Override
	public CollectItemVO queryCollectItemByCollectItemId(String collectItemId) {
		String sql = "select t.COLLECT_ITEM_ID, t.COLLECT_ITEM_DESC, t.COLLECT_SOURCE, t.COLLECT_URL, t.BASECLASS_ID, t.INDUSTRY_ID, t.UNIT_ID, t.DATATYPE_ID, t.DATETYPE_ID, "
				+ " t.REGION_ID, t.XML_ID, t.COLLECT_KEYWORDS, t.IS_VALID, t.LAST_UPDATE_TIME, t.REMARK, t.Status, t3.BASECLASS_NAME, "
				+ " t6.UNIT_NAME, t7.REGION_NAME, t8.INDUSTRY_NAME "
				+ "from t_collect_item t "
				+ "join t_dim_baseclass t3 on t.BASECLASS_ID=t3.BASECLASS_ID "
				+ "join t_dim_unit t6 on t.UNIT_ID=t6.UNIT_ID "
				+ "join t_dim_region t7 on t.REGION_ID=t7.REGION_ID "
				+ "join t_dim_industry t8 on t.INDUSTRY_ID=t8.INDUSTRY_ID ";

		if (!StringUtils.isEmpty(collectItemId)) {
			sql = sql + " where t.COLLECT_ITEM_ID = " + collectItemId;
		}

		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);

		@SuppressWarnings("unchecked")
		List<Object> list = query.list();

		if (!CollectionUtils.isEmpty(list)) {
			List<CollectItemVO> collectItemVOs = conversionCollectItemVOs(list);
			return collectItemVOs.get(0);

		}
		return null;
	}

	@Override
	public void updateItemStateByItemId(String itemId, String statue) {
		String sql = "UPDATE t_collect_item SET status = '" + statue
				+ "' WHERE collect_item_id = " + itemId;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.executeUpdate();
	}
	
	@Override
	public void updateItemCaptureStateByItemId(String itemId,
			String captureState) {
		String sql = "UPDATE t_collect_item SET CLASS2 = '" + captureState
				+ "' WHERE collect_item_id = " + itemId;
		Session session = sessionFactory.openSession();
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		session.close();
	}

	public int saveOrUpdateItem(CollectItem item) {
		int id = -1;
		sessionFactory.getCurrentSession().save(item);
		id = item.getCollectItemId();
		return id;
	}

	public static void main(String[] args) {
		CollectItem item = new CollectItem();
		item.setCollectItemDesc("aaaaa");
		item.setBaseclassId(1);
		item.setCollectSource(1);
		item.setIndustryId(1);
		item.setDataTypeId(6);
		item.setDateTypeID(3);
		item.setCollectURL("");
		item.setIsValId(1);
		item.setLastUpdateTime(new Date());
		item.setStatus("");
		CollectItemDAOImpl dao = new CollectItemDAOImpl();
		int id = dao.saveOrUpdateItem(item);
		System.out.println(id);
	}

	@Override
	public CollectItem saveCollectItem(CollectItem collectItem) {
		// TODO Auto-generated method stub
		if (collectItem.getCollectItemId() != null) {
			sessionFactory.getCurrentSession().update(collectItem);
		} else {
			sessionFactory.getCurrentSession().save(collectItem);
		}
		return null;
	}
	
	public void deleteCollectItem(int collectitemid){
		String sql = "delete from t_collect_item where collect_item_id="+collectitemid;
		sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		
	}

	@Override
	public List<CollectItemVO> getAllEnableItems() {
		String sql = "select t.COLLECT_ITEM_ID, t.COLLECT_ITEM_DESC, t.COLLECT_SOURCE, t.COLLECT_URL, t.BASECLASS_ID, "
				+ "t.INDUSTRY_ID, t.UNIT_ID, t.DATATYPE_ID, t.DATETYPE_ID, t.REGION_ID, t.XML_ID, t.COLLECT_KEYWORDS, "
				+ "t.IS_VALID, t.LAST_UPDATE_TIME, t.REMARK, t.Status, t3.BASECLASS_NAME, t6.UNIT_NAME, t7.REGION_NAME, "
				+ "t8.INDUSTRY_NAME from t_collect_item t left join t_dim_baseclass t3 on t.BASECLASS_ID=t3.BASECLASS_ID "
				+ "left join t_dim_unit t6 on t.UNIT_ID=t6.UNIT_ID left join t_dim_region t7 on t.REGION_ID=t7.REGION_ID "
				+ "left join t_dim_industry t8 on t.INDUSTRY_ID=t8.INDUSTRY_ID where t.status=1";
		Query query = sessionFactory.openSession().createSQLQuery(sql);

		@SuppressWarnings("unchecked")
		List<Object> list = query.list();
		List<CollectItemVO> collectItemVOs = conversionCollectItemVOs(list);

		return collectItemVOs;
	}

	@Override
	public boolean checkItemName(String itemName, String itemId) {
		if (StringUtils.isEmpty(itemName)) {
			return false;
		}
		String sql = "select * from t_collect_item where COLLECT_ITEM_DESC = '" + itemName + "' ";
		if (!StringUtils.isEmpty(itemId)) {
			sql = sql + " and collect_item_id !=" + itemId;
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		@SuppressWarnings("unchecked")
		List<Object> list = query.list();
		if (CollectionUtils.isEmpty(list)) {
			return true;
		}
		return false;
	}

}
