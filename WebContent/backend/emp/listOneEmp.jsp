<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.emp.model.*"%>
<%
EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");
%>
<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>
</head>
<body bgcolor='white'>
	<%@ include file="/menu1.jsp" %> 

<table border='1' cellpadding='5' cellspacing='0' width='500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料</h3>
		<a href="<%=request.getContextPath()%>/backend/emp/select_page.jsp"><img src="<%=request.getContextPath()%>/backend/emp/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='500'>
	<tr>
		<th nowrap>員工編號</th><td><%=employeeVO.getEmpNo()%></td>
	</tr>
	<tr>
		<th nowrap>員工姓名</th><td><%=employeeVO.getEmpName()%></td>
	</tr>
	<tr>
		<th nowrap>出生年月日</th><td><%=employeeVO.getEmpBirth()%></td>
	</tr>
	<tr>
		<th nowrap>電話</th><td><%=employeeVO.getEmpTel()%></td>
	</tr>
	<tr>
		<th nowrap>性別</th>
		<td><c:if test="${employeeVO.empSex == 'M'}">帥哥</c:if>
			<c:if test="${employeeVO.empSex == 'F'}">美女</c:if></td>
	</tr>
	<tr>
		<th nowrap>職稱</th><td><%=employeeVO.getEmpPos()%></td>
	</tr>
	<tr>
		<th nowrap>薪資</th><td><%=employeeVO.getEmpSalary()%></td>
	</tr>
	<tr>
		<th nowrap>到職日</th><td><%=employeeVO.getEmpArrDate()%></td>
	</tr>
	<tr>
		<th nowrap>離職日</th><td><%=employeeVO.getEmpOff()%></td>
	</tr>
	<tr>
		<th nowrap>身分證字號</th><td><%=employeeVO.getEmpID()%></td>
	</tr>
	<tr>
		<th nowrap>住址</th><td><%=employeeVO.getEmpAdd()%></td>
	</tr>
	<tr>
		<th nowrap>照片</th><td><img src="<%= request.getContextPath()%>/emp/DBGifReader3?empNo=${employeeVO.empNo}"></td>
	</tr>
	<tr>
		<th nowrap>密碼</th><td>${employeeVO.empPassword}</td>
	</tr>
	<tr>
		<th nowrap>E-mail</th><td>${employeeVO.empEmail}</td>
	</tr>
</table>
	<%@ include file="/menu2.jsp" %> 

</body>
</html>
