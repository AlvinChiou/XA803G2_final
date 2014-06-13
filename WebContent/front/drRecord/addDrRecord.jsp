<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.drRecord.model.*"%>
<%
// 	DrRecordVO drRecVO = (DrRecordVO) request.getAttribute("drRecVO");
	DrRecordVO drRecVO = (DrRecordVO) request.getAttribute("drRecVO"); 
%>

<html>
<head>
<title>新增看診紀錄資料 - AddDrRecord.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>新增看診紀錄資料 - AddDrRecord.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>看診紀錄:</h3>
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

<FORM METHOD="post" ACTION="act.do" name="form1" enctype="multipart/form-data">
<table border="0">

	<tr>
		<td>寵物編號:</td>
		<td><input type="text" name="petNo" size="45"
			value="<%= (drRecVO==null)? "aaa" : drRecVO.getPetNo()%>" /></td>
	</tr>
	<tr>
		<%java.sql.Date drRecTime_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>看診日期:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="drRecTime" value="<%= (drRecVO==null)? drRecTime_SQL : drRecVO.getDrRecTime()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','drRecTime','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="看診日期"></a>
		</td>
	</tr>
	<tr>
		<td>看診處方:</td>
		<td><textarea name="drRecPres" rows="3" cols="35" wrap="off" value="<%= (drRecVO==null)? "請輸入處方" : drRecVO.getDrRecPres()%>" >
			</textarea>
		</td>
	</tr>
	<tr>
		<td>醫生:</td>
		<td><input type="text" name="drNo" size="45"
			value="<%= (drRecVO==null)? "aaa" : drRecVO.getDrNo()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<br>
<input type="submit" value="送出新增">

</FORM>
</body>

</html>
