<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
<html>
<head>
    <title>Spring MVC Hello World</title>
</head>
<script type="text/javascript" src="./resources/bootstrap/js/jquery.js" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	window.onload = function(){
        $.ajax({
          url:"getAllDimIndustrys.do",
          type:"get",
          dataType:"json",
          success:function(dimIndustrys){
            alert(responseText);
          },
          error:function(){
            alert("system error");
          }
        });
    };
</script> 
<body>
    <h2>All industryName in System</h2>
 
    <table border="1">
        <tr>
            <th>industryName</th>
        </tr>
        <c:forEach items="${dimIndustrys}" var="DimIndustry">
            <tr>
                <td>${DimIndustry.industryName}</td>
            </tr>
        </c:forEach>
    </table>
 
</body>
</html>