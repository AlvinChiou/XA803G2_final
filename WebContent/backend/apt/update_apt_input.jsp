<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.apt.model.*"%>
<%
	AptVO aptVO = (AptVO) request.getAttribute("aptVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>員工資料修改 - update_apt_input.jsp</title>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<!-- 自己新增的 jquery DatePicker -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<div id="popupcalendar" class="text"></div>


<script>
		$(function() {
		
			$("#datepicker5").datepicker({
				showOn : "button",
				buttonImage : "images/calendar.gif",
				buttonImageOnly : true,
				minDate: 0,
				maxDate: "+19D",
				dateFormat: "yy-mm-dd"
			});
		});
</script>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料修改 - update_apt_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/backend/apt-Knifeman.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
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

<FORM METHOD="post" ACTION="apt.do" name="form1">
<table border="0">
	<tr>
		<td>掛號編號:</td>
		<td><%= (aptVO==null)? "0" : aptVO.getAptNo()%></td>
	</tr>
		
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>掛號日期:</td>
		<%-- <td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="aptDate" value="<%= (aptVO==null)? date_SQL : aptVO.getAptDate()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','hiredate','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="開始日期"></a>
		</td> --%>
		
		
			<td>
				<input type="text"  id="datepicker5"  name = "aptDate"
				value="<%= (aptVO==null)? date_SQL : aptVO.getAptDate()%>" />
			</td>
	
	</tr>
	<tr>
		<td>時段:</td>
		<td>
			<select name = "aptPeriod">
				<option value = "請選擇欲掛號時段">請選擇欲掛號時段</option>
				<option value = "0910">9:00~10:00</option>
				<option value = "1011">10:00~11:00</option>
				<option value = "1112">11:00~12:00</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<td>掛號號碼牌編號:</td>
		<td><%= (aptVO==null)? "0" : aptVO.getAptNoSlip()%></td>
	</tr>
	
	<tr>
		<td>寵物編號:</td>
		<td><input type="TEXT" name="petNo" size="45"
			value="<%= (aptVO==null)? "2" : aptVO.getPetNo()%>" /></td>
	</tr>
<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" />
	<tr>
		<td>部門:<font color=red><b>*</b></font></td>
		<td><select size="1" name="deptno">
			<c:forEach var="deptVO" items="${deptSvc.all}">
				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname}
			</c:forEach>
		</select></td>
	</tr> --%>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="aptNo" value="<%=aptVO.getAptNo()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
