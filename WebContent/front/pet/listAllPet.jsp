<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    PetService petSvc = new PetService();
    List<PetVO> list = petSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>�e��-�d����� - �i�d�߷|���������d��,�ק��d�����</title>
</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�e��-�d�����  - �i�d�߷|���������d��</h3>
		<a href="<%=request.getContextPath()%>/front/pet/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
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
		<th>�d���s��</th>
		<th>�d���W�r</th>
		<th>�d���ʧO</th>
		<th>�d�����O</th>
		<th>�d���Ӥ�</th>
		<th>�d���~��</th>
		<th>�|���s��</th>
		
		
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
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="petNo" value="${petVO1.petNo}">
			     <input type="hidden" name="requestURL" value="<%= request.getServletPath() %>">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do"> --%>
<!-- 			    <input type="submit" value="�R��"> -->
<%-- 			    <input type="hidden" name="petNo" value="${petVO1.petNo}"> --%>
<!-- 			    <input type="hidden" name="action"value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>


</body>
</html>
