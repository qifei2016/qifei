package com.qifei.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qifei.model.CollectData;
import com.qifei.model.ConfigParam;
import com.qifei.service.ConfigPageService;

@Controller
public class ConfigPageController {

	@Autowired
	ConfigPageService configservice;

	@RequestMapping(value = "/readFromXML", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String readFromXML(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String itemid = request.getParameter("itemid");
		ConfigParam param = configservice.getConfigById(itemid);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(param);
		return jsonString;
	}

	@RequestMapping(value = "/removeFromXML", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String removeFromXML(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String itemid = request.getParameter("itemid");
		int num = configservice.deleteConfigById(itemid);
		return num + "";
	}

	@RequestMapping(value = "/saveToXML", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String saveToXML(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String itemid = request.getParameter("itemid");
		ObjectMapper objectMapper = new ObjectMapper();
		String paramstr = new String(request.getParameter("params").getBytes(
				"ISO-8859-1"), "UTF-8");
		ConfigParam param = objectMapper.readValue(paramstr, ConfigParam.class);
		configservice.addConfig(param, itemid);
		return "";
	}

	@RequestMapping(value = "/crawlData", produces = { "application/json;charset=UTF-8" })
	public void crawlData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String itemid = request.getParameter("itemid");
		ConfigParam param = configservice.getConfigById(itemid);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			List<CollectData> collectdatalist = configservice.crawlData(param,
					itemid);
			for (int i = 0; i < collectdatalist.size(); i++) {
				CollectData collectdata = collectdatalist.get(i);
				configservice.saveCollectData(collectdata);
			}
			out.print(collectdatalist);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/testCrawlData", produces = { "application/json;charset=UTF-8" })
	public void testCrawlData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String paramstr = new String(request.getParameter("params").getBytes(
				"ISO-8859-1"), "UTF-8");
		ConfigParam param = objectMapper.readValue(paramstr, ConfigParam.class);
		// ConfigParam param = configservice.getConfigById("10100");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			@SuppressWarnings("rawtypes")
			List<HashMap> resultlist = configservice.crawlData(param);
			for(int i=0; i<resultlist.size(); i++){
				out.println("urlDom:" + resultlist.get(i).get("urlDom"));
				out.println("url:" + resultlist.get(i).get("url"));
				out.println("dateDom:" + resultlist.get(i).get("dateDom"));
				out.println("date:" + resultlist.get(i).get("date"));
				out.println("valueDom:" + resultlist.get(i).get("valueDom"));
				out.println("value:" + resultlist.get(i).get("value"));
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/checkData", produces = { "application/json;charset=UTF-8" })
	public void checkData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String paramstr = new String(request.getParameter("params").getBytes(
				"ISO-8859-1"), "UTF-8");
		ConfigParam param = objectMapper.readValue(paramstr, ConfigParam.class);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			@SuppressWarnings({ "rawtypes" })
			List<HashMap> list = configservice.crawlData(param);
			@SuppressWarnings("rawtypes")
			List resultlist = configservice.checkData(list, param);
			out.print(resultlist);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}