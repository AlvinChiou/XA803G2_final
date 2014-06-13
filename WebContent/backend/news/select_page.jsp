<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		
<title>IBM News: Home</title>

</head>
<%	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4008);
List<PowVO> listPower = (List<PowVO>)session.getAttribute("list"); %>

<body bgcolor='white'>
	<%@ include file="/menu1.jsp" %> 

	<c:if test="<%=listPower.contains(powVO)%>"> 
	
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><h3>iPETCares News: Home</h3></td>
		</tr>
	</table>
	<br>
	<h3>資料查詢:</h3>

	<ul>
		<li><a href='listAllNews.jsp'>所有消息列表</a></li>
	</ul>
	<br>
<!-- 	<h3>最新消息管理</h3> -->

	<ul>
		<li><a href='addNews.jsp'>新增最新消息</a></li>
	</ul>
	
	</c:if>
	
		<%@ include file="/menu2.jsp" %> 
	
</body>
	
</html>
