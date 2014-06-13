<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.func.model.*"%>

<%
	FuncService funcSvc = new FuncService();
	List<FuncVO> list = funcSvc.getAll();
	pageContext.setAttribute("list", list);
	
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>所有權限功能 - listAllFunc.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>

<script src="<%=request.getContextPath()%>/menu.js"></script>	

</head>
<%@ include file="/menu1.jsp" %> 

<body bgcolor="white">
<b><font color="red"></font></b>
<!-- <table border='1' cellpadding='5' cellspacing='0' width='1100'> -->
<!-- 	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'> -->
<!-- 		<td> -->
<!-- 		<h3>所有權限功能  - listAllFunc.jsp</h3> -->
<%-- 		<a href="<%= request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- </table> -->

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='1100'>
	<tr>
		<th>功能編號</th>
		<th>功能名稱</th>
	</tr>
	<c:forEach var="funcVO" items="${list}">
		<tr align='center' valign='middle'>
			<td>${funcVO.funcno}</td>
			<td>${funcVO.funcname}</td>
		</tr>
	</c:forEach>

</table>
<%@ include file="/menu2.jsp" %> 

</body>
</html>