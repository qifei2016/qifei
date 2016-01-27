package com.qifei.crawl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TimeCommon{  
	
	
	public static void main(String []abc){
		int y,m,d,h,mi,s;    
		Calendar cal=Calendar.getInstance();    
		y=cal.get(Calendar.YEAR);    
		m=cal.get(Calendar.MONTH);    
		d=cal.get(Calendar.DATE);    
		h=cal.get(Calendar.HOUR_OF_DAY);    
		mi=cal.get(Calendar.MINUTE);    
		s=cal.get(Calendar.SECOND);    
		//System.out.println("现在时刻是"+y+"年"+m+"月"+d+"日"+h+"时"+mi+"分"+s+"秒"); 
		//System.out.println(getNowDate());
		
		/*
		 * 第一种：2015-12-02 12-12-12  
		 * 第二种：2015-12-02 12:12:12   
		 * 第三种：2015年12月02日
		 * 第四种：2015/12/02
		 * 第五种：2015/12/02dfdff
		 * 第六种：2015_12_02
		 * 
		 * 第七种：12_02_2015
		 * 第八种：2_2_2015ewewe
		 */
		getDate("02_02_2015ewewe");//1448985600000
	}


    public static java.sql.Date getDate(String dates){

    	dates = dates.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "").replaceAll("/", "-").replaceAll("_", "-").trim();
    	//dates = dates.substring(0, 10);
    	System.out.println(dates);
    	
    	SimpleDateFormat sdf = new SimpleDateFormat();
    	if(getDateRegex2(dates)){
    		sdf = new SimpleDateFormat("yyyy-MM-dd");
    	}else{
    		sdf = new SimpleDateFormat("MM-dd-yyyy");
    	}
    	
    	java.util.Date parse = new java.util.Date();
		try {
			parse = sdf.parse(dates);
			System.out.println(parse.getTime());
		} catch (ParseException e) {
			System.err.println("时间转换出现错误！！！");
			e.printStackTrace();
		}
    	return new java.sql.Date(parse.getTime());
    }
    
    
    //获得当天的时间
	public static String getNowDate(){
	    String temp_str="";   
	    Date dt = new Date();   
	    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
	    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	    temp_str=sdf.format(dt);   
	    return temp_str;   
	}  
	
	
	//格式12-02-2015
	public static boolean getDateRegex1(String date){
		String regex = "[0-9]{2}-[0-9]{2}-[0-9]{4}";  
		 Pattern pattern = Pattern.compile(regex);  
		 Matcher matcher = pattern.matcher("12-02-2015");  
		 if(matcher.matches()){  
			 return true;
        }else{  
        	return false;
        }  
		
	}
	
	//格式2015-12-02
	public static boolean getDateRegex2(String date){
		String regex = "^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}[^>]*";  
		 Pattern pattern = Pattern.compile(regex);  
		 Matcher matcher = pattern.matcher(date);  
		 if(matcher.matches()){  
			 return true;
        }else{  
        	return false;
        }  
		
	}

}