<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.apt.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    AptDAO dao = new AptDAO();
    List<AptVO> list = dao.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>�Ҧ����u��� - listAllEmp1_byDAO.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ�������� - listAllApt_byDAO.jsp</h3>
		<a href="<%=request.getContextPath()%>/backend/apt-Knifeman.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='700'>
	<tr>
		<th>�����s��</th>
		<th>�������</th>
		<th>�����ɬq</th>
		<th>���X�P�s��</th>
		<th>����ɶ�</th>
		<th>�d���s��</th>
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
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</body>
</html>
