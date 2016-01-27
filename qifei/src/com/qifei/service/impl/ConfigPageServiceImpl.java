package com.qifei.service.impl;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qifei.crawl.CheckConfig;
import com.qifei.crawl.CrawlURL;
import com.qifei.crawl.crawlerConfig;
import com.qifei.dao.CollectDataDAO;
import com.qifei.dao.XMLDAO;
import com.qifei.model.CollectData;
import com.qifei.model.ConfigParam;
import com.qifei.model.CrawlURLResult;
import com.qifei.service.ConfigPageService;

@Service
public class ConfigPageServiceImpl implements ConfigPageService {

	@Autowired
	XMLDAO xmlDao;
	@Autowired
	CollectDataDAO collectDataDao;

	/**
	 * 根据id查找配置信息
	 * 
	 * @param itemid
	 * @return
	 */
	public ConfigParam getConfigById(String itemid) {
		ConfigParam param = new ConfigParam();
		String path = xmlDao.getXMLPath();
		Document doc = xmlDao.getDocFromXML(path);
		Element root = doc.getRootElement();
		List<Element> elelist = xmlDao.getChildByAttribute(root, "id", itemid);
		Element e = null;
		if (elelist.size() > 0) {
			e = elelist.get(0);
			Element crawlnode = e.element("deployInformation");
			Element checknode = e.element("testInformation");
			List<List> steplist = new ArrayList<List>();
			Element ele = null, subele = null;
			Iterator<Element> it = crawlnode.elementIterator("step");
			while (it.hasNext()) {
				ele = it.next();
				List<Map> substeplist = new ArrayList<Map>();
				Iterator<Element> subit = ele.elementIterator("substep");
				while (subit.hasNext()) {
					subele = subit.next();
					Map<String, String> stepmap = new HashMap<String, String>();
					stepmap.put("infotype", subele.elementText("infotype"));
					stepmap.put("crawltype", subele.elementText("crawltype"));
					stepmap.put("crawlrule", subele.elementText("crawlrule"));
					substeplist.add(stepmap);
				}
				steplist.add(substeplist);
			}

			param.setCharset(crawlnode.elementText("charset"));
			param.setMethod(Integer.parseInt(crawlnode.elementText("method")));
			param.setUrl(crawlnode.elementText("url"));
			param.setBaseurl(crawlnode.elementText("baseurl"));
			param.setSteplist(steplist);
			param.setDateFormat(checknode.elementText("dateFormat"));
			param.setDateRange(checknode.elementText("dateRange"));
			param.setValueFormat(checknode.elementText("valueFormat"));
			param.setValueRange(checknode.elementText("valueRange"));
		}
		return param;
	}

	/**
	 * 删除指定id的配置信息
	 */
	@Override
	public int deleteConfigById(String itemid) throws Exception {
		// TODO Auto-generated method stub
		String path = xmlDao.getXMLPath();
		Document doc = xmlDao.getDocFromXML(path);
		Element root = doc.getRootElement();
		List<Element> elelist = xmlDao.getChildByAttribute(root, "id", itemid);

		Iterator<Element> it = elelist.iterator();
		boolean flag = false;
		int num = 0;
		while (it.hasNext()) {
			flag = root.remove(it.next());
			if (flag) {
				num++;
			}
		}

		xmlDao.saveDoc2File(doc, path, "utf-8");

		return num;
	}

	/**
	 * 添加配置信息
	 */
	@Override
	public void addConfig(ConfigParam param, String itemid) throws Exception {
		// TODO Auto-generated method stub
		String path = xmlDao.getXMLPath();
		Document doc = xmlDao.getDocFromXML(path);
		Element root = doc.getRootElement();
		
		List<Element> list = xmlDao.getChildByAttribute(root, "id", itemid);
		for(int i=0; i<list.size(); i++){
			root.remove(list.get(i));
		}
		
		String charset = param.getCharset();
		int method = param.getMethod();
		String url = param.getUrl();
		String baseurl = param.getBaseurl();
		List<List> steplist = param.getSteplist();
		String dateFormat = param.getDateFormat();
		String dateRange = param.getDateRange();
		String valueFormat = param.getValueFormat();
		String valueRange = param.getValueRange();

		Element recipe = DocumentHelper.createElement("recipe");
		recipe.addAttribute("id", itemid);

		Element deploy = DocumentHelper.createElement("deployInformation");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		deploy.addAttribute("settime", sf.format(new Date()));

		Element echarset = DocumentHelper.createElement("charset");
		echarset.setText(charset);
		deploy.add(echarset);
		Element emethod = DocumentHelper.createElement("method");
		emethod.setText(method + "");
		deploy.add(emethod);
		Element eurl = DocumentHelper.createElement("url");
		eurl.setText(url);
		deploy.add(eurl);
		Element ebaseurl = DocumentHelper.createElement("baseurl");
		ebaseurl.setText(baseurl);
		deploy.add(ebaseurl);


		for (int i = 0; i < steplist.size(); i++) {
			Element estep = DocumentHelper.createElement("step");
			List<Map> substeplist = steplist.get(i);
			for (int j = 0; j < substeplist.size(); j++) {
				Element esubstep = DocumentHelper.createElement("substep");
				Map<String, String> substep = substeplist.get(j);
				Element einfotype = DocumentHelper.createElement("infotype");
				einfotype.setText(substep.get("infotype"));
				Element ecrawltype = DocumentHelper.createElement("crawltype");
				ecrawltype.setText(substep.get("crawltype"));
				Element ecrawlrule = DocumentHelper.createElement("crawlrule");
				ecrawlrule.setText(substep.get("crawlrule"));
				esubstep.add(einfotype);
				esubstep.add(ecrawltype);
				esubstep.add(ecrawlrule);
				estep.add(esubstep);
			}
			deploy.add(estep);
		}

		Element check = DocumentHelper.createElement("testInformation");
		Element edaterule = DocumentHelper.createElement("dateFormat");
		edaterule.setText(dateFormat);
		check.add(edaterule);
		Element edateregex = DocumentHelper.createElement("dateRange");
		edateregex.setText(dateRange);
		check.add(edateregex);
		Element evaluerule = DocumentHelper.createElement("valueFormat");
		evaluerule.setText(valueFormat);
		check.add(evaluerule);
		Element evalueregex = DocumentHelper.createElement("valueRange");
		evalueregex.setText(valueRange);
		check.add(evalueregex);

		recipe.add(deploy);
		recipe.add(check);
		root.add(recipe);

		xmlDao.saveDoc2File(doc, path, "utf-8");
	}

	@Override
	public List<CollectData> crawlData(ConfigParam param, String itemid) {
		// TODO Auto-generated method stub
		String url = param.getUrl();
		List<CollectData> collectlist = new ArrayList<CollectData>();
		
		if(url != null){
			// 定位最终url
			CrawlURL crawlurl = new CrawlURL();
			CrawlURLResult crawlresult = new CrawlURLResult();
			crawlresult = crawlurl.getAllURL(url, param);

			crawlerConfig crawlcon = new crawlerConfig();
			ArrayList<String> urlList = crawlresult.getUrllist();
			ArrayList<HashMap> resultmaplist = new ArrayList<HashMap>();
			HashMap<String, String> resultmap = new HashMap<String, String>();

			try {
				for (int i = 0; i < urlList.size(); i++) {
					if(resultmap.get("value") != null && resultmap.get("value").length()>0 ){
						CollectData collectdata = new CollectData();
						resultmap = crawlcon.getCell(urlList.get(i), param);
						resultmap.put("url", urlList.get(i));
						resultmap.put("urlDom", crawlresult.getUrlDomlist());
						resultmaplist.add(resultmap);
	
						collectdata.setCollectItemId(Integer.parseInt(itemid));
						
						String date = resultmap.get("date");
						if(date == null){
							collectdata.setCollectDate(new Date());
						}else if(date.matches("\\d{4}年\\d{1,2}月\\d{1,2}日*")){
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
							collectdata.setCollectDate(sdf.parse(resultmap.get("date")));
						}else if(date.matches("\\d{4}-\\d{1,2}-\\d{1,2}")){
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							collectdata.setCollectDate(sdf.parse(resultmap.get("date")));
						}else if(date.matches("\\d{4}\\d{1,2}\\d{1,2}")){
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
							collectdata.setCollectDate(sdf.parse(resultmap.get("date")));
						}
					
						collectdata.setCollectData(Float.parseFloat(resultmap
								.get("value")));
						collectdata.setLastUpdateTime(new Date());

						collectlist.add(collectdata);
					}
					
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		// return resultmaplist;
		return collectlist;
	}

	@Override
	@Transactional
	public void saveCollectData(CollectData data) {
		// TODO Auto-generated method stub
		// collectDataDao.saveCollectData(data);
		collectDataDao.saveOrUpdateEntity(data);
	}

	@Override
	public List checkData(List<HashMap> list, ConfigParam param) {
		// TODO Auto-generated method stub
		ArrayList<HashMap> checkmaplist = new ArrayList<HashMap>();
		HashMap<String, String> collectdata = new HashMap<String, String>();
		String dateFormat = "", dateRange = "", valueFormat = "", valueRange = "";
		if (param.getDateFormat() != null) {
			dateFormat = param.getDateFormat();
		}
		if (param.getDateRange() != null) {
			dateRange = param.getDateRange();
		}
		if (param.getValueFormat() != null) {
			valueFormat = param.getValueFormat();
		}
		if (param.getValueRange() != null) {
			valueRange = param.getValueRange();
		}

		CheckConfig check = new CheckConfig();
		String datestr = "", valuestr = "";
		boolean isRight = true;
		for (int i = 0; i < list.size(); i++) {
			collectdata = list.get(i);
			datestr = collectdata.get("date");
			valuestr = collectdata.get("value");

			if (!dateFormat.equals("") && !datestr.equals("")) {
				HashMap<String, String> checkMap = new HashMap<String, String>();
				isRight = check.checkData(datestr, dateFormat);
				checkMap.put("checkstring", datestr);
				checkMap.put("checkrule", dateFormat);
				checkMap.put("isRight", isRight + "");
				checkmaplist.add(checkMap);
			}
			if (dateRange.equals("1")) {
				HashMap<String, String> checkMap = new HashMap<String, String>();
				isRight = check.checkDateRange(datestr);
				checkMap.put("checkstring", datestr);
				checkMap.put("checkrule", dateRange);
				checkMap.put("isRight", isRight + "");
				checkmaplist.add(checkMap);
			}
			if (!valueFormat.equals("") && !valuestr.equals("")) {
				HashMap<String, String> checkMap = new HashMap<String, String>();
				isRight = check.checkData(valuestr, valueFormat);
				checkMap.put("checkstring", valuestr);
				checkMap.put("checkrule", valueFormat);
				checkMap.put("isRight", isRight + "");
				checkmaplist.add(checkMap);
			}
			if (!valueRange.equals("") && !valuestr.equals("")) {
				HashMap<String, String> checkMap = new HashMap<String, String>();
				isRight = check.checkRange(valuestr, valueRange);
				checkMap.put("checkstring", valuestr);
				checkMap.put("checkrule", valueRange);
				checkMap.put("isRight", isRight + "");
				checkmaplist.add(checkMap);
			}
		}
		return checkmaplist;
	}

	@Override
	public List<HashMap> crawlData(ConfigParam param) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		String url = param.getUrl();
		List<CollectData> collectlist = new ArrayList<CollectData>();
		// 定位最终url
		CrawlURL crawlurl = new CrawlURL();
		CrawlURLResult crawlresult = new CrawlURLResult();
		crawlresult = crawlurl.getAllURL(url, param);

		crawlerConfig crawlcon = new crawlerConfig();
		ArrayList<String> urlList = crawlresult.getUrllist();
		ArrayList<HashMap> resultmaplist = new ArrayList<HashMap>();
		HashMap<String, String> resultmap = new HashMap<String, String>();

		try {
			for (int i = 0; i < urlList.size(); i++) {
				resultmap = crawlcon.getCell(urlList.get(i), param);
				resultmap.put("url", urlList.get(i));
				resultmap.put("urlDom", crawlresult.getUrlDomlist());
				resultmaplist.add(resultmap);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultmaplist;
	}
}
