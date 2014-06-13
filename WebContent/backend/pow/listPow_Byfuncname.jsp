<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>

<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmployeeService" />
<jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" />
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>員工權限 - listPow_Byfuncname.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>

<script src="<%=request.getContextPath()%>/menu.js"></script>	
</head>
<body bgcolor='white'>


<b><font color="red"></font></b>
<table border='1' cellpadding='5' cellspacing='0' width='1100'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工權限 - listOnePow.jsp</h3>
		<a href="<%= request.getContextPath()%>/backend/pow&func-Yo.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='1100'>
	<tr>
		<th nowrap>員工姓名</th>
		<c:forEach var="funcVO" items="${funcSvc.all }">
			<th nowrap>${funcVO.funcname}</th>
		</c:forEach>
	</tr>

<c:forEach var="powVO" items="${list}">
	<tr align='center' valign='middle'>
		
		<td>
			<c:forEach var="empVO" items="${empSvc.all}">
				<c:if test="${empVO.empNo == powVO.empno}">${empVO.empName}</c:if>
			</c:forEach>
		</td>
		
		<c:forEach var="funcVO" items="${funcSvc.all}">
			<td>
				<c:forEach var="empVO" items="${empSvc.all}">
					<c:if test="${empVO.empNo == powVO.empno && funcVO.funcno == powVO.funcno}"><% out.println("V"); %></c:if>
				</c:forEach>
			</td>
		</c:forEach>

			<td>
				<form method="post" action="<%=request.getContextPath()%>/pow/pow.do">
				<input type="submit" value="修改">
				<input type="hidden" name="empno" value="${powVO.empno}">
				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
				<input type="hidden" name="action" value="getOne_For_Update"></form>
			</td>
	</tr>
</c:forEach>



</table>

</body>
</html>