package com.qifei.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Repository;

public class XMLOption{
	
	/**
	 * 读取xml文件，返回document类型
	 * @param path
	 * @return
	 */
	public Document getDocFromXML(String path){
		SAXReader reader = new SAXReader();               
	    Document document = null;
		try {
			document = reader.read(new File(path));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    return document;
	}
	
	/**
	 * 读取xml文件，返回String类型
	 * @param path
	 * @return
	 */
	public String getStrFromXML(String path){
		SAXReader reader = new SAXReader();               
	    Document document = null;
	    String xmlStr = "";
		try {
			document = reader.read(new File(path));
			xmlStr = document.asXML();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xmlStr;
	}
	
	/**
	 * 符合xml格式的字符串转成document类型
	 * @param str
	 * @return
	 */
	public Document changeStr2Doc(String str){
		Document document = null;
        try {
			document = DocumentHelper.parseText(str);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return document;
	}
	
	/**
	 * document转换String
	 * @param str
	 * @return
	 */
	public String changeDoc2Str(Document doc){
        return doc.asXML();
	}
	
	/**
	 * 将document保存到xml文件中
	 * @param document
	 * @param path
	 * @param charset
	 * @throws Exception
	 */
	public void saveDoc2File(Document document, String path, String charset) throws Exception {  
        // 紧凑的格式  
        // OutputFormat format = OutputFormat.createCompactFormat();  
        // 排版缩进的格式  
        OutputFormat format = OutputFormat.createPrettyPrint();  
        // 设置编码  
        format.setEncoding(charset);  
        // 创建XMLWriter对象,指定了写出文件及编码格式  
        // XMLWriter writer = new XMLWriter(new FileWriter(new  
        // File("src//a.xml")),format);  
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(  
                new FileOutputStream(new File(path)), charset), format);  
        // 写入  
        writer.write(document);  
        // 立即写入  
        writer.flush();  
        // 关闭操作  
        writer.close();  
    }
	
	/**
	 * 将符合xml格式的字符串保存到xml文件中
	 * @param document
	 * @param path
	 * @param charset
	 * @throws Exception
	 */
	public void saveXMLStr2File(String xmlstr, String path, String charset) throws Exception {  
        OutputFormat format = OutputFormat.createPrettyPrint();  
        format.setEncoding(charset);  
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(  
                new FileOutputStream(new File(path)), charset), format);  
        writer.write(xmlstr);  
        writer.flush();  
        writer.close();  
    }
	
	/**
	 * 获取所有子节点
	 * @param node
	 * @return
	 */
	public List<Element> getChildNodes(Element node){
		return node.elements();
	}
	
	/**
	 * 遍历当前节点元素下面的所有节点 (包括子节点的子节点)
	 * @param node
	 */
    public List<Element> getAllNodes(Element node, List<Element> allnodes) {
        // 获取当前节点的所有属性节点  
        List<Attribute> list = node.attributes();  
        // 遍历属性节点  
        for (Attribute attr : list) {
            System.out.println(attr.getText() + "-----" + attr.getName()  
                    + "---" + attr.getValue());  
        }  
  
        if (!(node.getTextTrim().equals(""))) {  
            System.out.println("文本内容：：：：" + node.getText());  
        }  
  
        // 当前节点下面子节点迭代器  
        Iterator<Element> it = node.elementIterator();  
        // 遍历  
        while (it.hasNext()) {  
            // 获取某个子节点对象  
            Element e = it.next();
            allnodes.add(e);
            // 对子节点进行遍历  
            getAllNodes(e,allnodes);  
        } 
        return allnodes;
    }
    
    /**
     * 根据属性值获取子节点
     * @param node
     * @param attr
     * @param value
     * @return
     */
    public List<Element> getChildByAttribute(Element node, String attr, String value){
    	List<Element> list = new ArrayList<Element>();
    	Iterator<Element> it = node.elementIterator();
    	while(it.hasNext()){
    		Element e = it.next();
    		if(e.attributeValue(attr).equals(value)){
    			list.add(e);
    		}
    	}
    	return list;
    }
    
    public String getXMLPath(Class c){
    	String path = c.getResource("/").getFile().toString();
    	if(path.indexOf("WEB-INF") > -1){
    		path = path.substring(0, path.indexOf("WEB-INF"));
        	path = path + "demo.xml";
    	}else{
    		path = "";
    	}
		return path;
	}
    
    public static void main(String[] args) throws UnsupportedEncodingException{
    	XMLOption xmldao = new XMLOption();
//    	String xmlstr = "<root><step id='1'><method>aaaaa</method></step><step name='2'><method>bbbbb</method></step></root>";
//    	Document doc = xmldao.changeStr2Doc(xmlstr);
//    	List<Element> allnodes = new ArrayList<Element>();
//    	allnodes = xmldao.getChildByAttribute(doc.getRootElement(), "name", "2");
    	String path = XMLOption.class.getClass().getResource("/").getFile().toString();
    	path = path.substring(0, path.indexOf("build"));
    	path = path + "WebContent/demo.xml";
    	String str = xmldao.getStrFromXML(path);
    	System.out.println(str);
    }
}
