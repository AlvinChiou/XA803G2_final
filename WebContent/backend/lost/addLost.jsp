<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.lost.model.*"%>
<%@ page import="com.pow.model.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
  LostVO lostVO = (LostVO) request.getAttribute("lostVO");

PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4011);
List<PowVO> list = (List<PowVO>)session.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>���ܤ峹�s�W - addLost.jsp</title></head>



<body bgcolor='white'>
<%@ include file="/menu1.jsp" %> 

<c:if test="<%=list.contains(powVO)%>"> 

<table  border='1' cellpadding='5' cellspacing='0' width='550'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���ܤ峹�s�W </h3>
		</td>
		<td>
			<a href="<%= request.getContextPath()%>/backend/lost/select_page.jsp">
			<img src="<%= request.getContextPath()%>/backend/lost/images/tomcat.gif" width="120" height="100" border="1">�^����</a>
		</td>
	</tr>
</table>

<h3>���ܤ峹���:</h3>
<%-- ���~��C  --%>
<c:if test="${not empty errorMsgs}">
<!-- 	<font color='red'>�Эץ��H�U���~: -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li>${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<!-- 	</font> -->
</c:if>

<form method="post" action="<%= request.getContextPath()%>/lost/lost.do" name="form1" enctype="multipart/form-data">
<table border="0">
	<tr>
		<td>�峹���D:</td>
		<td><input type="text" name="losttitle" size="60" value="<%= (lostVO==null)? "" : lostVO.getLosttitle()%>" /></td>
		<td><font color='red'>${errorMsgs.losttitle}</font></td>
	</tr>
	<tr>
		<td>�Ӥ�:</td>
		<td><input type="file" name="lostpic" size="60" /></td>
		<td><font color='red'>${errorMsgs.lostpic}</font></td>
	</tr>
	<tr>
		<td>�峹���e:</td>
		<td><textarea name="lostcontent" rows="30" cols="55" ><%= (lostVO==null)? "" : lostVO.getLostcontent() %></textarea></td>
		<td><font color='red'>${errorMsgs.lostcontent}</font></td>
	</tr>
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>�n���ɶ�:</td>
		<td>
		    <input type="hidden" id="datepicker3" name="losttime" value="<%= date_SQL%>" /><%= date_SQL%>
		</td>
		<td>${errorMsgs.losttime}</td>
	</tr>
	<tr>
		<td>��M���A:</td>
		<td><input type="radio" name="loststate" size="2" value="<%= (lostVO==null)? "0" : lostVO.getLoststate()%>" checked='' />����
			<input type="radio" name="loststate" size="2" value="<%= (lostVO==null)? "1" : lostVO.getLoststate()%>" />�B��</td>
	</tr>
	<tr>
		<td>�|���s��</td>
		<td><input type="hidden" name="memno" size="10" value="<%= (lostVO==null)? "7001" : lostVO.getMemno() %>"/>7001</td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" name="�e�X�s�W">
</form>

<%@ include file="/menu2.jsp" %> 

</c:if>
</body>
</html>