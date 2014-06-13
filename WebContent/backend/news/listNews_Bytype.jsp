<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>


<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />

<%--
    NewsService newsSvc = new NewsService();
    List<NewsVO> list = newsSvc.listNews_Bytype(newstype);
    pageContext.setAttribute("list",list);
--%>

<%
PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4008);
List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>
<jsp:useBean id="listNews_Bytype" scope="request" type="java.util.List"></jsp:useBean>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		
</head>
<body bgcolor='white'>
	<%@ include file="/menu1.jsp" %> 

<c:if test="<%=listPower.contains(powVO)%>"> 


<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有消息資料 </h3>
		<a href="<%=request.getContextPath()%>/backend/news/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
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
		<th>消息編號</th>
		<th>消息主題</th>
		<th>消息類別</th>
		<th>消息內容</th>
		<th>照片</th>
		<th>發布時間</th>
		<th>員工編號</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	
	<c:forEach var="newsVO" items="${listNews_Bytype}">
		<tr align='center' valign='middle' ${(newsVO.newsno==param.newsno) ? 'bgcolor=#CCCCFF':''}>
			<td>${newsVO.newsno}</td>
			<td>${newsVO.newstitle}</td>
			<td>${newsVO.newstype}</td>
			<td>${newsVO.newscontent}</td>
			<td><img src="DBGifReader?newsno=${newsVO.newsno}" ></td>
			<td>${newsVO.newspotime}</td>
			<td>${newsVO.empno}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/news/news.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="newsno" value="${newsVO.newsno}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/news/news.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="newsno" value="${newsVO.newsno}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</c:if>

<%@ include file="/menu2.jsp" %> 
</body>
</html>