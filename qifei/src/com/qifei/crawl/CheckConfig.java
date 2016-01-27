package com.qifei.crawl;

import java.util.Date;

public class CheckConfig {
	
	public boolean checkData(String value, String rule){
		Crawler crawl = new Crawler();
		
		String res = crawl.getMatchHtml(value, rule);
		if(value.equals(res)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean checkRange(String value, String rule){
		String[] rules = rule.split(";");
		String currule = "", comparestr = "";
		double values = Double.parseDouble(value);
		double comparevalue = 0.000;
		boolean flag = true;
		for(int i=0; i<rules.length; i++){
			currule = rules[i].trim();
			if(currule.indexOf(">=")==0){
				comparestr = currule.substring(2);
				comparevalue = Double.parseDouble(comparestr);
				if(values < comparevalue){
					flag = false;
				}
			}else if(currule.indexOf("<=")==0){
				comparestr = currule.substring(2);
				comparevalue = Double.parseDouble(comparestr);
				if(values > comparevalue){
					flag = false;
				}
			}else if(currule.indexOf("!=")==0){
				comparestr = currule.substring(2);
				comparevalue = Double.parseDouble(comparestr);
				if(values == comparevalue){
					flag = false;
				}
			}else if(currule.indexOf(">")==0){
				comparestr = currule.substring(1);
				comparevalue = Double.parseDouble(comparestr);
				if(values <= comparevalue){
					flag = false;
				}
			}else if(currule.indexOf("<")==0){
				comparestr = currule.substring(1);
				comparevalue = Double.parseDouble(comparestr);
				if(values >= comparevalue){
					flag = false;
				}
			}else if(currule.indexOf("[")==0){
				currule = currule.replaceAll("\\[|\\]", "");
				String[] r = currule.split(",");
				Double rleft = Double.parseDouble(r[0]);
				Double rright = Double.parseDouble(r[1]);
				if(rleft < rright){
					if(values < rleft || values > rright){
						flag = false;
					}
				}else{
					if(values > rleft || values < rright){
						flag = false;
					}
				}
			}
		}
		return flag;
	}
	
	public boolean checkDateRange(String date){
		boolean flag = true;
		//不可超过当前日期
		Date valuedate = TimeCommon.getDate(date);
		if(valuedate.after(new Date())){
			flag = false;
		}
		return flag;
	}
}
