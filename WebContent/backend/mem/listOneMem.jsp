<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	MemVO memVO = (MemVO)request.getAttribute("memVO");

	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4010);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�|����� - listOneMem.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
</head>
<body bgcolor='white'>
	
	<%@ include file="/menu1.jsp" %> 
			<c:if test="<%=listPower.contains(powVO)%>"> 
	
<table border='1' cellpadding='0' cellspacing='5' width='1100'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�|����� - ListOneMem.jsp</h3>
		<a href="<%=request.getContextPath()%>/backend/mem/select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='1100'>
	<tr>
		<th>�|���s��</th>
		<th>�|���b��</th>
		<th>�|���K�X</th>
		<th>�|���m�W</th>
		<th>�����Ҧr��</th>
		<th>�|���H�c</th>
		<th>�X�ͦ~���</th>
		<th>��}</th>
		<th>�ʧO</th>
		<th>�q��</th>
		<th>���A</th>
	</tr>
	<tr align='center' valign='middle'>
		<td>${memVO.memno}</td>
		<td>${memVO.memid}</td>
		<td>${memVO.mempassword}</td>
		<td>${memVO.memname}</td>
		<td>${memVO.memidno}</td>
		<td>${memVO.mememail}</td>
		<td>${memVO.membirth}</td>
		<td>${memVO.memadd}</td>
		<td><c:if test="${memVO.memsex=='0'}">�k</c:if>
			<c:if test="${memVO.memsex=='1'}">�k</c:if></td>
		<td>${memVO.memtel}</td>
		<td><c:if test="${memVO.memstate=='0'}">���{��</c:if>
			<c:if test="${memVO.memstate=='1'}">�w�{��</c:if>
			<c:if test="${memVO.memstate=='2'}">�w���v</c:if>
		</td>
 
		<td>
			<form method="post" action="<%=request.getContextPath()%>/mem/mem.do">
			<input type="submit" value="�ק�">
			<input type="hidden" name="memno" value="${memVO.memno}">
			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			<input type="hidden" name="action" value="getOne_For_Update"></form>
		</td>

	</tr>
</table>
	</c:if>
   <%@ include file="/menu2.jsp" %> 
</body>
</html>