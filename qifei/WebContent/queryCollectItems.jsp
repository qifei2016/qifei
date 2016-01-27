<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
<html>
<head>
    <title>Spring MVC Hello World</title>
</head>
<script type="text/javascript" src="./resources/bootstrap/js/jquery.min.js">
<script>

$(function(){
	$.ajax({
		type : "get",
		url : "/queryCollectItems.do?keywords=&datetype=&region=&industry=&category=",
		dataType : "json",
		success : function (data) {
		},
		error : function () {
		}
	});
});
	
</script>
<body>
    <h2>All industryName in System</h2>
 
    <table border="1">
        <tr>
            <th>industryName</th>
        </tr>
        <c:forEach items="${collectItems}" var="collectItem">
            <tr>
                <td>${collectItem.industryName}</td>
            </tr>
        </c:forEach>
    </table>
 
</body>
</html>