<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.emp.model.*"%>
<%
EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");
%>
<html>
<head>
<title>���u��� - listOneEmp.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>
</head>
<body bgcolor='white'>
	<%@ include file="/menu1.jsp" %> 

<table border='1' cellpadding='5' cellspacing='0' width='500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���u���</h3>
		<a href="<%=request.getContextPath()%>/backend/emp/select_page.jsp"><img src="<%=request.getContextPath()%>/backend/emp/images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='500'>
	<tr>
		<th nowrap>���u�s��</th><td><%=employeeVO.getEmpNo()%></td>
	</tr>
	<tr>
		<th nowrap>���u�m�W</th><td><%=employeeVO.getEmpName()%></td>
	</tr>
	<tr>
		<th nowrap>�X�ͦ~���</th><td><%=employeeVO.getEmpBirth()%></td>
	</tr>
	<tr>
		<th nowrap>�q��</th><td><%=employeeVO.getEmpTel()%></td>
	</tr>
	<tr>
		<th nowrap>�ʧO</th>
		<td><c:if test="${employeeVO.empSex == 'M'}">�ӭ�</c:if>
			<c:if test="${employeeVO.empSex == 'F'}">���k</c:if></td>
	</tr>
	<tr>
		<th nowrap>¾��</th><td><%=employeeVO.getEmpPos()%></td>
	</tr>
	<tr>
		<th nowrap>�~��</th><td><%=employeeVO.getEmpSalary()%></td>
	</tr>
	<tr>
		<th nowrap>��¾��</th><td><%=employeeVO.getEmpArrDate()%></td>
	</tr>
	<tr>
		<th nowrap>��¾��</th><td><%=employeeVO.getEmpOff()%></td>
	</tr>
	<tr>
		<th nowrap>�����Ҧr��</th><td><%=employeeVO.getEmpID()%></td>
	</tr>
	<tr>
		<th nowrap>��}</th><td><%=employeeVO.getEmpAdd()%></td>
	</tr>
	<tr>
		<th nowrap>�Ӥ�</th><td><img src="<%= request.getContextPath()%>/emp/DBGifReader3?empNo=${employeeVO.empNo}"></td>
	</tr>
	<tr>
		<th nowrap>�K�X</th><td>${employeeVO.empPassword}</td>
	</tr>
	<tr>
		<th nowrap>E-mail</th><td>${employeeVO.empEmail}</td>
	</tr>
</table>
	<%@ include file="/menu2.jsp" %> 

</body>
</html>
