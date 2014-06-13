<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    PetService petSvc = new PetService();
    List<PetVO> list = petSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>前端-寵物資料 - 可查詢會員有哪些寵物,修改寵物資料</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>前端-寵物資料  - 可查詢會員有哪些寵物</h3>
		<a href="<%=request.getContextPath()%>/front/pet/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
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
		<th>寵物編號</th>
		<th>寵物名字</th>
		<th>寵物性別</th>
		<th>寵物類別</th>
		<th>寵物照片</th>
		<th>寵物年齡</th>
		<th>會員編號</th>
		
		
	</tr>
	
	<c:forEach var="petVO1" items="${list}" >
		<tr align='center' valign='middle' ${(petVO1.petNo==petVO.petNo)? 'bgcolor=#CCCCFF':'' }>
			<td>${petVO1.petNo}</td>
			<td>${petVO1.petName}</td>
			<td>${petVO1.petSex}</td>
			<td>${petVO1.petType}</td>
			<td><img src="<%=request.getContextPath()%>/pet/DBGifReader3?petNo=${petVO1.petNo}"></td>
			<td>${petVO1.petAge}</td>
			<td>${petVO1.memNo}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="petNo" value="${petVO1.petNo}">
			     <input type="hidden" name="requestURL" value="<%= request.getServletPath() %>">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do"> --%>
<!-- 			    <input type="submit" value="刪除"> -->
<%-- 			    <input type="hidden" name="petNo" value="${petVO1.petNo}"> --%>
<!-- 			    <input type="hidden" name="action"value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>


</body>
</html>
