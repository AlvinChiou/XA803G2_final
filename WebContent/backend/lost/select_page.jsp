<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.lost.model.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>

<% 
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4011);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
	
	%>
</head>
<body bgcolor='#EFF6FF'>
<%@ include file="/menu1.jsp" %> 
	<c:if test="<%=listPower.contains(powVO)%>"> 
	
<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>iPETCares Lost: Home</h3></td>
  </tr>
</table>

<h3>���ܨ�M�峹�d��</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<ul>
	<li><a href='<%= request.getContextPath()%>/backend/lost/listAllLost.jsp'>�Ҧ����ܨ�M�峹</a></li>
	<br>
	<li><a href='<%= request.getContextPath()%>/backend/lost/addLost.jsp'>�s�W���ܨ�M�峹</a></li>
	
	<li>
		<form method="post" action="<%= request.getContextPath()%>/lost/lost.do">
			<b>��J�峹�s��(�p5001):</b>
			<input type="text" name="lostno">
			<input type="submit" value="�e�X">
			<input type="hidden" name="action" value="getOne_For_Display">
		</form>
	</li>
	
	<li>
		<form method="post" action="<%= request.getContextPath()%>/lost/lost.do">
			<b>��J�|���s��(�p7001):</b>
			<input type="text" name="memno">
			<input type="submit" value="�e�X">
			<input type="hidden" name="action" value="listLost_ByMemno">
		</form>
	</li>
	
	<jsp:useBean id="lostSvc" scope="page" class="com.lost.model.LostService" />

	<li>
		<form method="post" action="<%= request.getContextPath()%>/lost/lost.do">
			<b><font color=orange>��ܤ峹���A:</font></b>
			<select size="1" name="loststate">
          		<option value="0">����
          		<option value="1">�B��
          		<option value="2">�w���
          		<option value="3">�w��^
			</select>
			<input type="submit" value="�e�X">
			<input type="hidden" name="action" value="listLost_ByState">
		</form>
	</li>
</ul>

<!-- <h3>���ܨ�M�峹�޲z</h3> -->

<!-- <ul> -->
<%-- 	<li><a href='<%= request.getContextPath()%>/backend/lost/addLost.jsp'>Add</a> a new Lost.</li> --%>
<!-- </ul> -->
	</c:if>
<%@ include file="/menu2.jsp" %> 

</body>
</html>