<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<%@ page import="com.func.model.*"%>

<jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" />
<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmployeeService" />
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4013);
	List<PowVO> list = (List<PowVO>)session.getAttribute("list");
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>所有員工權限 </title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>

<script src="<%=request.getContextPath()%>/menu.js"></script>	

</head>

<body bgcolor='white'>

<%@ include file="/menu1.jsp" %>

<b><font color="red"></font></b>
<table border='1' cellpadding='5' cellspacing='0' width='1250'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有員工權限 - listAllPow.jsp</h3>
		<a href="<%= request.getContextPath()%>/index.jsp"><img src="<%= request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">回首頁</a>
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

<table border='1' bordercolor='#CCCCFF' width='1000'>
	<tr>
		<th nowrap>員工姓名</th>
		<c:forEach var="funcVO" items="${funcSvc.all}">
			<th nowrap>${funcVO.funcname}</th>
		</c:forEach>
	</tr>
	
<c:forEach var="empVO" items="${empSvc.all}">
	<tr align='center' valign='middle' >
		<td>
		${empVO.empName}
		</td>
		<c:forEach var="funcVO" items="${funcSvc.all}">
			<td style= "text-align : center">
				<c:forEach var="powVO" items="${powSvc.all}">
 					 <c:if test="${powVO.empno == empVO.empNo && powVO.funcno == funcVO.funcno}"><% out.println("V");%></c:if>
 				</c:forEach>
			</td>
		</c:forEach>
			<c:if test="<%=list.contains(powVO)%>">  
			<td>   
				<form method="post" action="<%=request.getContextPath()%>/pow/pow.do">
				<input type="submit" value="修改">
				<input type="hidden" name="empno" value="${empVO.empNo}">
				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
				<input type="hidden" name="action" value="getOne_For_Update"></form>
			</td>
			</c:if>
	</tr>
</c:forEach>


</table>
<%@ include file="/menu2.jsp" %>
</body>
</html>