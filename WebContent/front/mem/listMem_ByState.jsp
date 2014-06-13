<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>

<%-- 
  List<MemVO> listMem_ByState = (List<MemVO>) request.getAttribute("listMem_ByState"); //MemServlet.java (Controller), �s�Jreq��memVO���� (�]�A�������X��memVO, �]�]�A��J��ƿ��~�ɪ�memVO����)
--%>

<jsp:useBean id="listMem_ByState" scope="request" type="java.util.Set" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�|����� - listMems_ByState.jsp</title>
</head>
<body bgcolor="white">
<b><font color="red"></font></b>
<table border='1' cellpadding='5' cellspacing='0' width='1100'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ��|����� - listMems_ByState.jsp</h3>
		<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
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
	<c:forEach var="memVO" items="${listMem_ByState}" >
		<tr align='center' valign='middle' ${(memVO.memno == param.memno)? 'bgcolor=#CCCCFF' : ''}><!--�N�ק諸���@���[�J����Ӥw-->
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
			    <c:if test="${memVO.memstate=='2'}">�w���v</c:if></td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/mem/mem.do">
				<input type="submit" value="�ק�">
				<input type="hidden" name="memno" value="${memVO.memno}">
				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
				<input type="hidden" name="action" value="getOne_For_Update"></form>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>