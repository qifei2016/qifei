package com.qifei.dao;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

public interface XMLDAO {
	
	/**
	 * 读取xml文件，返回document类型
	 * @param path
	 * @return
	 */
	public Document getDocFromXML(String path);
	
	/**
	 * 读取xml文件，返回String类型
	 * @param path
	 * @return
	 */
	public String getStrFromXML(String path);
	
	/**
	 * 符合xml格式的字符串转成document类型
	 * @param str
	 * @return
	 */
	public Document changeStr2Doc(String str);
	
	/**
	 * document转换String
	 * @param str
	 * @return
	 */
	public String changeDoc2Str(Document doc);
	
	/**
	 * 将document保存到xml文件中
	 * @param document
	 * @param path
	 * @param charset
	 * @throws Exception
	 */
	public void saveDoc2File(Document document, String path, String charset) throws Exception;
	
	/**
	 * 将符合xml格式的字符串保存到xml文件中
	 * @param document
	 * @param path
	 * @param charset
	 * @throws Exception
	 */
	public void saveXMLStr2File(String xmlstr, String path, String charset) throws Exception;
	
	/**
	 * 获取所有子节点
	 * @param node
	 * @return
	 */
	public List<Element> getChildNodes(Element node);
	
	/**
	 * 遍历当前节点元素下面的所有节点 (包括子节点的子节点)
	 * @param node
	 */
    public List<Element> getAllNodes(Element node, List<Element> allnodes);
    
    /**
     * 根据属性值获取子节点
     * @param node
     * @param attr
     * @param value
     * @return
     */
    public List<Element> getChildByAttribute(Element node, String attr, String value);
    
    public String getXMLPath(Class c);
    public String getXMLPath();
}
