<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>

<%pageContext.setAttribute("empno", request.getParameter("empno"));%>


<jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" />
<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmployeeService" />
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>修改員工權限 - update_pow_input.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>

<script src="<%=request.getContextPath()%>/menu.js"></script>	

<script>
function check_all(obj,cName)  {
	
	var checkboxs = document.getElementsByName(cName); 
    for(var i=0;i<checkboxs.length;i++){checkboxs[i].checked = obj.checked;}  
}
</script>

</head>
<body bgcolor='white'>



<%@ include file="/menu1.jsp" %>
<b><font color="red"></font></b>
<table border='1' cellpadding='5' cellspacing='0' width='1000'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有員工權限 - listAllPow.jsp</h3>
		<a href="<%= request.getContextPath()%>/backend/pow&func-Yo.jsp"><img src="<%= request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">回首頁</a>
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

<form method="post" action="pow.do" name="form1">
<table border='1' bordercolor="#CCCCFF" width='1000'>

	<tr>
		<th nowrap>員工姓名</th>
		<c:forEach var="funcVO" items="${funcSvc.all}">
			<th nowrap>${funcVO.funcname}</th>
		</c:forEach>
		<th></th>
		<th></th>
		
	</tr>

	<tr align="center" valign="middle">
	
		<td>
			<c:forEach var="empVO" items="${empSvc.all}">
 				<c:if test="${empVO.empNo == empno}">${empVO.empName}</c:if> 
			</c:forEach> 
		</td>
		
	<c:forEach var="funcVO" items="${funcSvc.all}">
		<td>
			<c:set var="checkStr" value=""></c:set>
			<c:forEach var="powVO" items="${list}">
				<c:if test="${powVO.funcno == funcVO.funcno}"><c:set var="checkStr" value="checked"></c:set></c:if>
			</c:forEach>
			<input type="checkbox" name="funcno" value="${funcVO.funcno}" ${checkStr}>
		</td>
	</c:forEach>
		<td nowrap>
			<input type="checkbox" name="all" onclick = "check_all(this,'funcno')">全選
		</td>
		<td>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="empno" value="${empno}">
			<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
			<input type="submit" value="修改">
		</td>
	</tr>
</table>
</form>
<%@ include file="/menu2.jsp" %>
</body>
</html>