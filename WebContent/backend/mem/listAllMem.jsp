<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.pow.model.*"%>
<%@ page import="com.doctor.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	MemService memSvc = new MemService();
	List<MemVO> list = memSvc.getAll();
	pageContext.setAttribute("list", list);
	
	
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4010);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>
<%--	
	MemVO memVO = new MemVO();
	memVO.setMemstate(1);
	memVO.setMemstate(2);
	memVO.setMemstate(3);
	{map[memVO.memstate]}
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>所有會員資料 </title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
</head>
<body bgcolor="white">
	<%@ include file="/menu1.jsp" %> 
	<c:if test="<%=listPower.contains(powVO)%>"> 
	
<b><font color="red"></font></b>
<table border='1' cellpadding='5' cellspacing='0' width='1100'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有會員資料 </h3>
		<a href="<%= request.getContextPath()%>/backend/mem/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
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

<table border='1' bordercolor='#CCCCFF' width='1100'>
	<tr>
		<th>會員編號</th>
		<th>會員帳號</th>
		<th>會員密碼</th>
		<th>會員姓名</th>
		<th>身分證字號</th>
		<th>會員信箱</th>
		<th>出生年月日</th>
		<th>住址</th>
		<th>性別</th>
		<th>電話</th>
		<th>狀態</th>
		<th> </th>
	</tr>  
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(memVO.memno == param.memno)? 'bgcolor=#CCCCFF' : ''}><!--將修改的那一筆加入對比色而已-->
			<td>${memVO.memno}</td>
			<td>${memVO.memid}</td>
			<td>${memVO.mempassword}</td>
			<td>${memVO.memname}</td>
			<td>${memVO.memidno}</td>
			<td>${memVO.mememail}</td>
			<td>${memVO.membirth}</td>
			<td>${memVO.memadd}</td>
			<td><c:if test="${memVO.memsex=='0'}">女</c:if>
				<c:if test="${memVO.memsex=='1'}">男</c:if></td>
			<td>${memVO.memtel}</td>
			<td><c:if test="${memVO.memstate=='0'}">未認證</c:if>
			    <c:if test="${memVO.memstate=='1'}">已認證</c:if>
			    <c:if test="${memVO.memstate=='2'}">已停權</c:if></td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/mem/mem.do">
				<input type="submit" value="修改">
				<input type="hidden" name="memno" value="${memVO.memno}">
				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
				<input type="hidden" name="action" value="getOne_For_Update"></form>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

</c:if>
    <%@ include file="/menu2.jsp" %> 

</body>
</html>