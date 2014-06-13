<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.apt.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    AptService aptSvc = new AptService();
    List<AptVO> list = aptSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有員工資料 - listAllApt.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有員工資料 - ListAllApt.jsp</h3>
		<a href="<%=request.getContextPath()%>/backend/apt-Knifeman.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回掛號管理首頁</a>
		</td>
	</tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>掛號編號</th>
		<th>掛號日期</th>
		<th>掛號時段</th>
		<th>號碼牌編號</th>
		<th>應到時間</th>
		<th>寵物編號</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="aptVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${aptVO.aptNo}</td>
			<td>${aptVO.aptDate}</td>
			<td>${aptVO.aptPeriod}</td>
			<td>${aptVO.aptNoSlip}</td>
			<td>${aptVO.aptRegTime}</td>
			<td>${aptVO.petNo}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apt/apt.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="aptNo" value="${aptVO.aptNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"> </FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apt/apt.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="aptNo" value="${aptVO.aptNo}"> 
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
