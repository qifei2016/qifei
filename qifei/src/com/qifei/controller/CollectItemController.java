package com.qifei.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qifei.model.CollectData;
import com.qifei.model.CollectItem;
import com.qifei.model.ConfigParam;
import com.qifei.service.CollectDataService;
import com.qifei.service.CollectItemService;
import com.qifei.service.ConfigPageService;
import com.qifei.vo.CollectDataVO;
import com.qifei.vo.CollectItemVO;

@Controller
public class CollectItemController {

	@Autowired
	CollectItemService collectItemService;

	@Autowired
	CollectDataService collectDataService;

	@Autowired
	ConfigPageService configservice;

	@RequestMapping(value = "/queryCollectItems", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String queryCollectItems(HttpServletRequest request,
			HttpServletResponse response) throws JsonGenerationException,
			JsonMappingException, IOException {
		String name = new String(request.getParameter("name").getBytes(
				"ISO-8859-1"), "UTF-8");
		String unit = request.getParameter("unit");
		String region = request.getParameter("region");
		String industry = request.getParameter("industry");
		String baseclass = request.getParameter("baseclass");
		int currentPage = request.getParameter("offset") == null ? 1 : Integer
				.parseInt(request.getParameter("offset"));
		// 每页行数
		int showCount = request.getParameter("limit") == null ? 10 : Integer
				.parseInt(request.getParameter("limit"));

		// 获取items信息
		// TODO 前台分页还没做
		List<CollectItemVO> collectItems = collectItemService
				.queryCollectItems(name, null, unit, region, industry,
						baseclass, currentPage, showCount);// 0,100 返回前100条

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(collectItems);
		System.out.println(jsonString);
		int total = collectItemService.getCollectItemsCount(name, null, unit,
				region, industry, baseclass);
		String json = "{\"total\":" + total + ",\"rows\":" + jsonString + "}";
		return json;
	}

	@RequestMapping(value = "/deleteCollectItem", produces = { "application/json;charset=UTF-8" })
	public void deleteCollectItem(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String collectItemId = request.getParameter("collectItemId");
		Integer id = Integer.valueOf(collectItemId);
		// 删除data数据
		CollectData collectData = new CollectData();
		collectData.setCollectItemId(id);
		collectDataService.deleteCollectData(Arrays.asList(collectData));

		// 删除item表数据
		CollectItem collectItem = new CollectItem();
		collectItem.setCollectItemId(id);
		collectItemService.deleteCollectItem(Arrays.asList(collectItem));
		
		// 删除配置信息
		configservice.deleteConfigById(collectItemId);
	}

	@RequestMapping(value = "/queryCollectItemByCollectItemId", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String queryCollectItemByCollectItemId(HttpServletRequest request,
			HttpServletResponse response) throws JsonGenerationException,
			JsonMappingException, IOException {
		String collectItemId = request.getParameter("collectItemId");
		// 获取items信息
		CollectItemVO collectItem = collectItemService
				.queryCollectItemByCollectItemId(collectItemId);
		// TODO 获取配置数据
		ConfigParam param = configservice.getConfigById(collectItemId);
		param.setName(collectItem.getCollectItemDesc());
		param.setUrl(collectItem.getCollectURL());
		param.setKeyword(collectItem.getCollectKeywords());
		param.setBaseclassId(collectItem.getBaseclassId());
		param.setSourceId(collectItem.getCollectSource());
		param.setDateTypeID(collectItem.getDateTypeID());
		param.setIndustryId(collectItem.getIndustryId());
		param.setRegionId(collectItem.getRegionId());
		param.setUnitId(collectItem.getUnitId());
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(param);
		System.out.println(jsonString);
		return jsonString;
	}

	@RequestMapping(value = "/saveCollectItem", produces = { "application/json;charset=UTF-8" })
	public void saveCollectItem(HttpServletRequest request,
			HttpServletResponse response) {
		CollectItem collectItem = new CollectItem();
		try {
			// ConfigParam param = configservice.getConfigById("10100");
			ObjectMapper objectMapper = new ObjectMapper();
			// String paramstr = objectMapper.writeValueAsString(param);
			String paramstr = new String(request.getParameter("params").getBytes(
					"ISO-8859-1"), "UTF-8");
			ConfigParam pageParam = objectMapper.readValue(paramstr,
					ConfigParam.class);
			if(pageParam.getId() != 0){
				collectItem.setCollectItemId(pageParam.getId());
			}
			
			collectItem.setCollectItemDesc(pageParam.getName());
			collectItem.setCollectURL(pageParam.getUrl());
			collectItem.setCollectKeywords(pageParam.getKeyword());
			collectItem.setBaseclassId(pageParam.getBaseclassId());
			collectItem.setCollectSource(pageParam.getSourceId());
			collectItem.setDateTypeID(pageParam.getDateTypeID());
			collectItem.setIndustryId(pageParam.getIndustryId());
			collectItem.setRegionId(pageParam.getRegionId());
			collectItem.setUnitId(pageParam.getUnitId());
			collectItem.setLastUpdateTime(new Date());
			collectItem.setStatus("1");

			// 保存item表数据
			collectItem = collectItemService.saveCollectItem(collectItem);
			// 保存配置信息
			configservice.addConfig(pageParam, collectItem.getCollectItemId()
					+ "");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/queryCollectDataByItemId", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String queryCollectDataByItemId(HttpServletRequest request,
			HttpServletResponse response) throws JsonGenerationException,
			JsonMappingException, IOException {
		String collectItemId = request.getParameter("itemid");
		List<CollectData> collectDatas = collectDataService
				.queryCollectDataByCollectItemId(collectItemId);
		List<CollectDataVO> collectDataVOs = new ArrayList<CollectDataVO>();
		for (CollectData collectData : collectDatas) {
			if (collectData == null) {
				continue;
			}
			CollectDataVO vo = new CollectDataVO();
			vo.setCol1(collectData.getCol1());
			vo.setCol2(collectData.getCol2());
			vo.setCol3(collectData.getCol3());
			vo.setCollectData(collectData.getCollectData());
			vo.setCollectDate(collectData.getCollectDate().toString());
			vo.setCollectId(collectData.getCollectId());
			vo.setCollectItemId(collectData.getCollectItemId());
			collectDataVOs.add(vo);
		}
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(collectDataVOs);
		System.out.println(jsonString);
		return jsonString;
	}

	@RequestMapping(value = "/updateItemStateByItemId", produces = { "application/json;charset=UTF-8" })
	public void updateItemStateByItemId(HttpServletRequest request,
			HttpServletResponse response) {
		String itemId = request.getParameter("itemid");
		String statue = request.getParameter("statue");
		collectItemService.updateItemStateByItemId(itemId, statue);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveCollectData", produces = { "application/json;charset=UTF-8" })
	public void saveCollectData(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String paramstr = request.getParameter("params");
			JavaType javaType = objectMapper.getTypeFactory()
					.constructParametricType(ArrayList.class,
							CollectDataVO.class);
			List<CollectDataVO> collectDataVOs = (List<CollectDataVO>) objectMapper
					.readValue(paramstr, javaType);
			List<CollectData> collectDatas = new ArrayList<CollectData>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dateNow = new Date();
			String nowString = sdf.format(dateNow);
			for (CollectDataVO collectDataVO : collectDataVOs) {
				CollectData collectData = new CollectData();
				collectData.setCollectData(collectDataVO.getCollectData());
				collectData.setCollectId(collectDataVO.getCollectId());
				collectData.setCollectItemId(collectDataVO.getCollectItemId());
				collectData.setRemark(collectDataVO.getRemark());
				String date = collectDataVO.getCollectDate();
				if (!StringUtils.isEmpty(date)) {
					collectData.setCollectDate(sdf.parse(date));
				}
				collectData.setLastUpdateTime(sdf.parse(nowString));
				collectDatas.add(collectData);
			}
			collectDataService.saveCollectData(collectDatas);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
