<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.lost.model.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />

<%--
  List<LostVO> listLost_ByState = (List<LostVO>) request.getAttribute("listLost_ByState");
--%>
<%

PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4011);
List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>

<jsp:useBean id="listLost_ByState" scope="request" type="java.util.Set" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>失蹤協尋資料 - listLosts_ByState.jsp</title>
</head>
<body bgcolor='white'>
<%@ include file="/menu1.jsp" %> 

<c:if test="<%=listPower.contains(powVO)%>"> 

<b><font color="red"></font></b>
<table border='1' cellpadding='5' cellspacing='0' width='650'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>失蹤協尋資料</h3>
		<a href="<%=request.getContextPath()%>/backend/lost/select_page.jsp"><img src="<%=request.getContextPath()%>/backend/lost/images/back1.gif" width="100" height="32" border="0">回首頁</a>
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

<table border='1' bordercolor='#CCCCFF' width='650'>
	<tr>
<!-- 		<th nowrap>文章編號</th> -->
		<th nowrap>文章標題</th>
<!-- 		<th nowrap>照片</th> -->
<!-- 		<th nowrap>文章內容</th> -->
		<th nowrap>登載時間</th>
		<th nowrap>找尋狀態</th>
		<th nowrap>會員編號</th>
	</tr>
	<c:forEach var="lostVO" items="${listLost_ByState}" >
		<tr align='center' valign='middle' ${(lostVO.lostno == param.lostno)? 'bgcolor=#CCCCFF' : ''}><!--將修改的那一筆加入對比色而已-->
<%-- 		<td>${lostVO.lostno}</td> --%>
		<td><a href="<%= request.getContextPath()%>/lost/lost.do?action=getOne_For_Display&lostno=${lostVO.lostno}">${lostVO.losttitle}</a></td>
<%-- 		<td><c:if test="${lostVO.lostpic!=null}"><img src="DBGifReader3?lostno=${lostVO.lostno}"></c:if> --%>
<%-- 			<c:if test="${lostVO.lostpic==null}"><img src="images/nopic.jpg"></c:if></td> --%>
<%-- 		<td>${lostVO.lostcontent}</td> --%>
		<td>${lostVO.losttime}</td>
		<td><c:if test="${lostVO.loststate=='0'}">失蹤</c:if>
			<c:if test="${lostVO.loststate=='1'}">拾獲</c:if>
			<c:if test="${lostVO.loststate=='2'}">已找到</c:if>
			<c:if test="${lostVO.loststate=='3'}">已領回</c:if></td>
		<td>${lostVO.memno}</td>
		
<!-- 		<td> -->
<%-- 			<form method="post" action="<%=request.getContextPath()%>/lost/lost.do"> --%>
<!-- 			<input type="submit" value="修改"> -->
<%-- 			<input type="hidden" name="lostno" value="${lostVO.lostno}"> --%>
<%-- 			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<!-- 			<input type="hidden" name="action" value="getOne_For_Update"></form> -->
<!-- 		</td> -->
<!-- 		<td> -->
<%-- 			<form method="post" action="<%=request.getContextPath()%>/lost/lost.do"> --%>
<!-- 			<input type="submit" value="刪除"> -->
<%-- 			<input type="hidden" name="lostno" value="${lostVO.lostno}"> --%>
<%-- 			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<!-- 			<input type="hidden" name="action" value="delete"></form> -->
<!-- 		</td> -->
		
	</tr>
	</c:forEach>
</table>
</c:if>

<%@ include file="/menu2.jsp" %> 

</body>
</html>