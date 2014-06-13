<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.apt.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    AptService aptSvc = new AptService();
    List<AptVO> list = aptSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>�Ҧ����u��� - listAllApt.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ����u��� - ListAllApt.jsp</h3>
		<a href="<%=request.getContextPath()%>/backend/apt-Knifeman.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^�����޲z����</a>
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

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>�����s��</th>
		<th>�������</th>
		<th>�����ɬq</th>
		<th>���X�P�s��</th>
		<th>����ɶ�</th>
		<th>�d���s��</th>
		<th>�ק�</th>
		<th>�R��</th>
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
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apt/apt.do">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="aptNo" value="${aptVO.aptNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"> </FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apt/apt.do">
			    <input type="submit" value="�R��">
			    <input type="hidden" name="aptNo" value="${aptVO.aptNo}"> 
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
