package com.qifei.crawl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.qifei.model.ConfigParam;


public class crawlerConfig {
	static Crawler craw = new Crawler();
	
	public static void main(String args[]) throws MalformedURLException{}
	
	
	public HashMap<String, String> getCell(String url,ConfigParam p) throws MalformedURLException{
		HashMap<String, String> resultmap = new HashMap<String, String>();
		String pgaeHtml = "";
		//选择抓取方式
		if(p.getMethod()==1){
			pgaeHtml = craw.getHtml(url, p.getCharset());
		}else if(p.getMethod()==2){
			pgaeHtml = craw.clientGetHtml(url);
		}else if(p.getMethod()==3){
			pgaeHtml = craw.getAjaxHtml(url);
		}
		Document doc = Jsoup.parse(pgaeHtml);
		List steplist = p.getSteplist();
		List<HashMap> substeplist = (List<HashMap>) steplist.get(steplist.size()-1);
		HashMap substepmap = new HashMap();
		String infotype = "", crawltype = "", crawlrule = "";
		String datestr = "", valuestr = "";
		for(int i=0; i<substeplist.size(); i++){
			substepmap = substeplist.get(i);
			infotype = substepmap.get("infotype").toString();
			crawltype = substepmap.get("crawltype").toString();
			crawlrule = substepmap.get("crawlrule").toString();
			
			if(infotype.equals("date")){
				if(crawltype.equals("dom")){
					String[] daterules = crawlrule.split(">");
					int dateNum = getElementsNumber(daterules);//获得最后一个单元的数字
					if(getElements(doc, daterules).size()-1<dateNum){resultmap.put("dateDom", "日期dom错误！");return resultmap;}
					Element date = getElements(doc, daterules).get(dateNum);
					datestr = date.text();
					resultmap.put("dateDom", datestr);
				}
				if(crawltype.equals("regex")){
					datestr = craw.getMatchHtml(datestr, crawlrule);
				}
				resultmap.put("date", datestr);
			}
			
			if(infotype.equals("value")){
				if(crawltype.equals("dom")){
					String[] valuerules = crawlrule.split(">");
					int valueNum = getElementsNumber(valuerules);//获得最后一个单元的数字
					if(getElements(doc, valuerules).size()-1<valueNum){resultmap.put("value", "数值dom错误！");return resultmap; }
					Element valueele = getElements(doc, valuerules).get(valueNum);
					valuestr = valueele.text();
					resultmap.put("valueDom", valuestr);
				}
				if(crawltype.equals("regex")){
					valuestr = craw.getMatchHtml(valuestr, crawlrule);
					valuestr = craw.getMatchHtml(valuestr, "(-)*\\d+(\\.\\d+)*");
				}
				resultmap.put("value", valuestr);
			}
		}
		
		System.out.println("日期："+datestr+"  数值："+valuestr);
		return resultmap;
	}
	
	public static Elements getElements(Document doc, String[] locs){
		String[] loc = locs[0].split("=");
		String numstr = craw.getMatchHtml(loc[1], "\\[.*\\]");
		String attrvalue = loc[1].replace(numstr, "");
		numstr = numstr.replace("[", "").replace("]", "");
		int num = 0;
		if(!numstr.equals("")){
			num = Integer.parseInt(numstr);
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
			numstr = craw.getMatchHtml(loc[1], "\\[.*\\]");
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
				}else{
					num = 0;
				}
			}
		}
		//aeles = aeles.get(num).getElementsByTag(getLastAttribute(locs));
		
		return aeles;
	}
	
	public static String getLastAttribute(String[] locs){
		String[] loc = locs[locs.length-1].split("=");
		String numstr = craw.getMatchHtml(loc[1], "\\[.*\\]");
		return loc[1].replace(numstr, "");
	}
	public static int getElementsNumber(String[] valuerules){
		String[] lastloc = valuerules[valuerules.length-1].split("=");
		String numstr = craw.getMatchHtml(lastloc[1], "\\[.*\\]");
		numstr = numstr.replace("[", "").replace("]", "");
		int num = 0;
		if(!numstr.equals("")){
			num = Integer.parseInt(numstr);
		}
		return num;
	}
}