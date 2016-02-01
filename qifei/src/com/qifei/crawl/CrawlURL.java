package com.qifei.crawl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.qifei.model.ConfigParam;
import com.qifei.model.CrawlURLResult;

public class CrawlURL {
	private static Crawler crawl = new Crawler();
	
	//定位最终url
	public CrawlURLResult getAllURL(String url, ConfigParam param){
		ArrayList<String> urllist = new ArrayList<String>();
		urllist.add(url);
		CrawlURLResult CrawlURLResult = new CrawlURLResult();
		CrawlURLResult.setUrllist(urllist);
		
		String code = param.getCharset();
		int method = param.getMethod();
		String baseurl = param.getBaseurl();
		List<List> stepslist = param.getSteplist();
		List<HashMap> substepslist = new ArrayList<HashMap>();
		HashMap stepmap = new HashMap();
		
		if(stepslist != null){
			for(int i=0; i<stepslist.size()-1; i++){
				substepslist = stepslist.get(i);
				
				urllist = CrawlURLResult.getUrllist();
				if(urllist.size() > 0){
					url = urllist.get(0);
				}
				stepmap.put("charset", code);
				stepmap.put("method", method);
				stepmap.put("baseurl", baseurl);
				stepmap.put("substepslist", substepslist);
				CrawlURLResult crawlres = new CrawlURLResult();
				CrawlURLResult = crawlPage(url, stepmap, crawlres);
			}
		}
		
		return CrawlURLResult;
	}
	
	
	
	//解析页面信息
	public CrawlURLResult crawlPage(String url, HashMap stepmap, CrawlURLResult crawlres){
		ArrayList<String> urllist = crawlres.getUrllist();
		String datestr = crawlres.getDate();
		String datedom = crawlres.getDateDom();
		String valuestr = crawlres.getValue();
		String valuedom = crawlres.getValueDom();
		String urlDomlist = crawlres.getUrlDomlist();
		
		int crawmethod = Integer.parseInt(stepmap.get("method").toString());
		String charset = stepmap.get("charset").toString();
		String baseurl = stepmap.get("baseurl").toString();
		List<HashMap> substepslist = (List<HashMap>) stepmap.get("substepslist");
		HashMap map = new HashMap();
		String pageHtml = "";
		
		//用不同的方法抓取页面信息
		if(crawmethod == 1){
			try {
				pageHtml = crawl.getHtml(url, charset);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(crawmethod == 2){
			pageHtml = crawl.clientGetHtml(url);
		}else if(crawmethod == 3){
			pageHtml = crawl.getAjaxHtml(url);
		}
		
		Elements aeles = new Elements();
		String href = "";
		
		//DOM
		Document doc = Jsoup.parse(pageHtml);
		String infotype = "", crawltype = "", crawlrule = "", nexturl = "";
		String[] nexts = null;
		for(int i=0; i<substepslist.size(); i++){
			map = substepslist.get(i);
			infotype = map.get("infotype").toString();
			crawltype = map.get("crawltype").toString();
			crawlrule = map.get("crawlrule").toString();
			
			if(infotype.equals("url")){
				if(crawltype.equals("dom")){
					String[] locs = crawlrule.split(">");
					Map urlmap = new HashMap();
					
					//根据规则解析dom
					if(locs.length > 0){
						urlmap = getElementsMap(doc, locs, aeles);
						aeles = (Elements) urlmap.get("alleles");
					}
				}
				if(crawltype.equals("condition")){
					String[] con = crawlrule.split("=");
					if(con[0].contains("attr")){
						con[0] = con[0].replace("attr", "").replace("(", "").replace(")", "");
						
						for(Element a : aeles){
							if(a.attr(con[0]).contains(con[1])){
									href = a.attr("href");
									urlDomlist = urlDomlist + href + ",";
									urllist.add(baseurl + href);
							}
						}
					}else if(con[0].equals("text") || con[0].equals("html")){
						for(Element a : aeles){
							if(a.text().contains(con[1])){
									href = a.attr("href");
									urlDomlist = urlDomlist + href + ",";
									urllist.add(baseurl + href);
									
							}
						}
					}
				}
			}
			//解析nexturl
			if(infotype.equals("nextPage")){
				if(crawltype.equals("dom")){
					nexts = crawlrule.split(">");
				}
				if(crawltype.equals("condition")){
					nexturl = getNextPage(doc, baseurl, nexts, crawlrule);
				}
				if(nexturl != "" && urllist.size()<5){
					crawlres = crawlPage(nexturl, stepmap, crawlres);
				}
			}
			
			if(infotype.equals("date")){
				if(crawltype.equals("dom")){
					String[] daterules = crawlrule.split(">");
					String dateDomstr = "";
					Map datemap = getElementsMap(doc, daterules, aeles);
					aeles = (Elements) datemap.get("alleles");
				}
				if(crawltype.equals("regex")){
					if(aeles.size() > 0){
						Element date = aeles.get(0);
						datedom = date.text();
						datestr += crawl.getMatchHtml(datedom, crawlrule);
					}else{
//						dateDom = datemap.get("Dom").toString();
					}
				}
				crawlres.setDateDom(datedom);
				crawlres.setDate(datestr);
			}
			
			if(infotype.equals("value")){
				Map valuemap = new HashMap();
				if(crawltype.equals("dom")){
					String[] daterules = crawlrule.split(">");
					String dateDomstr = "";
					valuemap = getElementsMap(doc, daterules, aeles);
					aeles = (Elements) valuemap.get("alleles");
				}
				if(crawltype.equals("regex")){
					if(aeles.size() > 0){
						Element valueele = aeles.get(0);
						valuedom = valueele.text();
						if(!crawlrule.equals("")){
							valuestr = crawl.getMatchHtml(valuedom, crawlrule);
							valuestr = crawl.getMatchHtml(valuedom, "(-)*\\d+(\\.\\d+)*");
						}
					}else{
						valuedom = valuemap.get("Dom").toString();
					}
				}
				
				crawlres.setValueDom(valuedom);
				crawlres.setValue(valuestr);
			
			}
			
			crawlres.setUrllist(urllist);
			crawlres.setUrlDomlist(urlDomlist);
		}
		
		//解析nexturl
		/*if(!nextdom.equals("") && urllist.size()<5){
			String[] nexts = nextdom.split(">");
			String nexturl = getNextPage(doc, baseurl, nexts, nextcondition);
			if(nexturl != ""){
				crawlres = crawlPage(nexturl, crawmethod, charset, crawlbean, crawlres);
			}
		}*/
		
		return crawlres;
	}
	
	
	/**
	 * 根据规则解析dom,返回节点信息
	 * @param doc
	 * @param locs
	 * @param alleles
	 * @return
	 */
	public Elements getElements(Document doc, String[] locs, Elements alleles){
		String[] loc = locs[0].split("=");
		if(loc.length < 2){
			System.out.println("dom解析输入格式不正确！");
			return alleles;
		}
		String numstr = crawl.getMatchHtml(loc[1], "\\[.*\\]");
		String attrvalue = loc[1].replace(numstr, "");
		numstr = numstr.replace("[", "").replace("]", "");
		int num = 0;
		if(!numstr.equals("")){
			try{
				num = Integer.parseInt(numstr);
			}catch(NumberFormatException e){
				System.out.println("dom解析输入数字格式不正确");
				return alleles;
			}
		}
		
		Elements aeles = new Elements();
		if(loc[0].equals("tag")){
			aeles = doc.getElementsByTag(attrvalue);
		}else{
			aeles = doc.getElementsByAttributeValue(loc[0], attrvalue);
		}
		int i=1;
		while(aeles.size() > 0 && i<locs.length){
			loc = locs[i].split("=");
			if(loc.length < 2){
				System.out.println("dom解析输入格式不正确！");
				return alleles;
			}
			numstr = crawl.getMatchHtml(loc[1], "\\[.*\\]");
			attrvalue = loc[1].replace(numstr, "");
			if(loc[0].equals("tag")){
				aeles = aeles.get(num).getElementsByTag(attrvalue);
			}else{
				aeles = aeles.get(num).getElementsByAttributeValue(loc[0], attrvalue);
			}
			i++;
			if(i<locs.length){
				numstr = numstr.replace("[", "").replace("]", "");
				if(!numstr.equals("")){
					try{
						num = Integer.parseInt(numstr);
					}catch(NumberFormatException e){
						System.out.println("dom解析输入数字格式不正确");
						return alleles;
					}
				}else{
					num = 0;
				}
			}
		}
		for(Element ele : aeles){
			alleles.add(ele);
		}
		
		return alleles;
	}
	
	/**
	 * 根据规则解析dom,返回元素和异常信息
	 * @param doc
	 * @param locs
	 * @param alleles
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map getElementsMap(Document doc, String[] locs, Elements alleles){
		Map resultmap = new HashMap();
		String[] loc = locs[0].split("=");
		if(loc.length < 2){
			resultmap.put("Dom", "dom解析输入格式不正确！");
			resultmap.put("alleles", alleles);
			return resultmap;
		}
		String numstr = crawl.getMatchHtml(loc[1], "\\[.*\\]");
		String attrvalue = loc[1].replace(numstr, "");
		numstr = numstr.replace("[", "").replace("]", "");
		int num = 0;
		if(!numstr.equals("")){
			try{
				num = Integer.parseInt(numstr);
			}catch(NumberFormatException e){
				resultmap.put("Dom", "dom解析输入数字格式不正确");
				resultmap.put("alleles", alleles);
				return resultmap;
			}
		}
		
		Elements aeles = new Elements();
		if(loc[0].equals("tag")){
			aeles = doc.getElementsByTag(attrvalue);
		}else{
			aeles = doc.getElementsByAttributeValue(loc[0], attrvalue);
		}
		int i=1;
		while(aeles.size() > 0 && i<locs.length){
			loc = locs[i].split("=");
			if(loc.length < 2){
				resultmap.put("Dom", "dom解析输入格式不正确！");
				resultmap.put("alleles", alleles);
				return resultmap;
			}
			numstr = crawl.getMatchHtml(loc[1], "\\[.*\\]");
			attrvalue = loc[1].replace(numstr, "");
			if(loc[0].equals("tag")){
				aeles = aeles.get(num).getElementsByTag(attrvalue);
			}else{
				aeles = aeles.get(num).getElementsByAttributeValue(loc[0], attrvalue);
			}
			i++;
			if(i<locs.length){
				numstr = numstr.replace("[", "").replace("]", "");
				if(!numstr.equals("")){
					try{
						num = Integer.parseInt(numstr);
					}catch(NumberFormatException e){
						resultmap.put("Dom", "dom解析输入数字格式不正确");
						resultmap.put("alleles", alleles);
						return resultmap;
					}
				}
			}
		}
		for(Element ele : aeles){
			alleles.add(ele);
		}
		resultmap.put("alleles", alleles);
		return resultmap;
	}
	
	
	//��ȡ��һҳurl
	public String getNextPage(Document doc, String baseurl, String[] locs, String condition){
		String[] loc = locs[0].split("=");
		String numstr = crawl.getMatchHtml(loc[1], "\\[.*\\]");
		String attrvalue = loc[1].replace(numstr, "");
		numstr = numstr.replace("[", "").replace("]", "");
		int num = 0;
		if(!numstr.equals("")){
			num = Integer.parseInt(numstr);
		}
		
		Elements aeles = doc.getElementsByAttributeValue(loc[0], attrvalue);
		int i=1;
		while(aeles.size() > 0 && i<locs.length){
			loc = locs[i].split("=");
			numstr = crawl.getMatchHtml(loc[1], "\\[.*\\]");
			attrvalue = loc[1].replace(numstr, "");
			if(loc[0].equals("tag")){
				aeles = aeles.get(num).getElementsByTag(attrvalue);
			}else{
				aeles = aeles.get(num).getElementsByAttributeValue(loc[0], attrvalue);
			}
			i++;
			if(i<locs.length){
				numstr = numstr.replace("[", "").replace("]", "");
				if(!numstr.equals("")){
					num = Integer.parseInt(numstr);
				}
			}
		}
		
		String href = "", nexturl = "";
		if(aeles.size() > 0){
			if(condition.equals("")){
				nexturl = baseurl + aeles.get(0).attr("href");
			}else{
				String[] con = condition.split("=");
				if(con[0].contains("attr")){
					con[0] = con[0].replace("attr", "").replace("(", "").replace(")", "");
					
					for(Element a : aeles){
						if(a.attr(con[0]).contains(con[1])){
								href = a.attr("href");
								nexturl = baseurl + href;
						}
					}
				}else if(con[0].equals("text") || con[0].equals("html")){
					for(Element a : aeles){
						if(a.text().contains(con[1])){
								href = a.attr("href");
								nexturl = baseurl + href;
								
						}
					}
				}
			}
		}
		return nexturl;
	}
	
	public static void main(String args[]){
	}
}
