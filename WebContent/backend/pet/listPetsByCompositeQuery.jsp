<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>
<%@ page import="com.mem.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listPets_ByCompositeQuery" scope="request" type="java.util.List" />

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" /> 

<html>
<head>
<title>後端-寵物複合查詢 - listPetsByCompositeQuery.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>

</head>
<body bgcolor='white'>

	<%@ include file="/menu1.jsp" %> 
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3><font color=red>後端-寵物複合查詢</font></h3>
		<a href="<%=request.getContextPath()%>/backend/pet/select_page.jsp"><img src="<%= request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>


<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>寵物編號</th>
		<th>寵物名字</th>
		<th>寵物性別</th>
		<th>寵物類別</th>
		<th>寵物照片</th>
		<th>寵物年齡</th>
		<th>會員編號</th>
	</tr>
<%-- 	<%@ include file="pages/page1_ByCompositeQuery.file" %> --%>
	<c:forEach var="petVO" items="${listPets_ByCompositeQuery}" >
		<tr align='center' valign='middle' >
					
			<td>${petVO.petNo}</td>
			<td>${petVO.petName}</td>
			<td>
				<c:if test="${petVO.petSex == 'boy'}">男生</c:if>
				<c:if test="${petVO.petSex == 'girl'}">女生</c:if>
			</td>
			<td>
				<c:if test="${petVO.petType == 'dog'}">狗狗</c:if>
				<c:if test="${petVO.petType == 'cat'}">貓咪</c:if>
			</td>
			<td><img src="<%=request.getContextPath()%>/pet/DBGifReader3?petNo=${petVO.petNo}"></td>
			<td>${petVO.petAge}</td>
			<td>
				<c:forEach var="memVO" items="${memSvc.all}">
					<c:if test="${petVO.memNo == memVO.memno}">
						${memVO.memname}(${petVO.memNo })
					</c:if>
				</c:forEach>
			</td>
			</tr>
	</c:forEach>
</table>
<%-- <%@ include file="pages/page2_ByCompositeQuery.file" %> --%>

<%@ include file="/menu2.jsp" %>    
</body>
</body>
</html>
