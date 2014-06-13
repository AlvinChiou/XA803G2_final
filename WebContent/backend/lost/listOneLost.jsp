<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.lost.model.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%--
LostVO lostVO = (LostVO)request.getAttribute("lostVO");
--%>
<%

PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4011);
List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	

<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>���ܤ峹 - listOneLost.jsp</title>
</head>
<body bgcolor='white'>
<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>"> 

<table border='1' cellpadding='0' cellspacing='5' width='500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���ܤ峹</h3>
		<a href="<%= request.getContextPath()%>/backend/lost/select_page.jsp"><img src="<%= request.getContextPath()%>/backend/lost/images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='500'>
	<tr>
		<th nowrap>�峹�s��</th>
		<td>${lostVO.lostno}</td>
	</tr>
	<tr>	
		<th nowrap>�峹���D</th>
		<td>${lostVO.losttitle}</td>
	</tr>
	<tr>	
		<th nowrap>�Ӥ�</th>
		<td><c:if test="${lostVO.lostpic!=null}"><img src="DBGifReader3?lostno=${lostVO.lostno}"></c:if>
		    <c:if test="${lostVO.lostpic==null}"><img src="images/nopic.jpg"></c:if></td>
	</tr>
	<tr>	
		<th nowrap>�峹���e</th>
		<td>${lostVO.lostcontent}</td>
	</tr>
	<tr>	
		<th nowrap>��M���A</th>
		<td><c:if test="${lostVO.loststate=='0'}">����</c:if>
			<c:if test="${lostVO.loststate=='1'}">�B��</c:if>
			<c:if test="${lostVO.loststate=='2'}">�w���</c:if>
			<c:if test="${lostVO.loststate=='3'}">�w��^</c:if>
		</td>
	</tr>
	<tr>	
		<th nowrap>�n���ɶ�</th>
		<td>${lostVO.losttime}</td>
	</tr>
	<tr>		
		<th nowrap>�|���s��</th>
		<td>${lostVO.memno}</td>
	</tr>
	
</table>

<%-- 	<form method="post" action="<%=request.getContextPath()%>/lost/lost.do"> --%>
<!-- 		<input type="submit" value="�ק�"> -->
<%-- 		<input type="hidden" name="lostno" value="${lostVO.lostno}"> --%>
<%-- 		<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--> --%>
<!-- 		<input type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 	</form> -->
</c:if>
	<%@ include file="/menu2.jsp" %> 
	
</body>
</html>