<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actregister.model.*"%>
<%
ActRegisterVO actRegVO = (ActRegisterVO) request.getAttribute("actRegVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>活動報名資料修改 - update_actRegister_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>活動報名資料修改 - update_actRegister_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>活動報名資料修改:</h3>
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

<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/front/act/act.do" name="form1">
<table border="0">
	<tr>
		<td>活動報名紀錄編號:<font color=red><b>*</b></font></td>
		<td><%=actRegVO.getActRegNo()%></td>
	</tr>
	<tr>
		<td>報名活動名稱:</td>
		<td><input type="TEXT" name="actRegName" size="45" value="<%=actRegVO.getActRegName()%>" /></td>
	</tr>
	<tr>
		<td>報名日期:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			 size="20" readonly type="text" name="actRegDate" value="<%=actRegVO.getActRegDate()%>">
			
		</td>
	</tr>
	
	<tr>
		<td>付費狀態:</td>
		<td><input type="TEXT" name="actRegPayState" size="45" value="<%=actRegVO.getActRegPayState()%>" /></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="memNo" size="45" value="<%=actRegVO.getMemNo()%>" /></td>
	</tr>
	<tr>
		<td>活動編號:</td>
		<td><input type="TEXT" name="actNo" size="45" value="<%=actRegVO.getActNo()%>" /></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="actRegNo" value="<%=actRegVO.getActRegNo()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
