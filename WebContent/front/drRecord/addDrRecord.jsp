<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.drRecord.model.*"%>
<%
// 	DrRecordVO drRecVO = (DrRecordVO) request.getAttribute("drRecVO");
	DrRecordVO drRecVO = (DrRecordVO) request.getAttribute("drRecVO"); 
%>

<html>
<head>
<title>�s�W�ݶE������� - AddDrRecord.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�s�W�ݶE������� - AddDrRecord.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">�^����</a>
	    </td>
	</tr>
</table>

<h3>�ݶE����:</h3>
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

<FORM METHOD="post" ACTION="act.do" name="form1" enctype="multipart/form-data">
<table border="0">

	<tr>
		<td>�d���s��:</td>
		<td><input type="text" name="petNo" size="45"
			value="<%= (drRecVO==null)? "aaa" : drRecVO.getPetNo()%>" /></td>
	</tr>
	<tr>
		<%java.sql.Date drRecTime_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>�ݶE���:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="drRecTime" value="<%= (drRecVO==null)? drRecTime_SQL : drRecVO.getDrRecTime()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','drRecTime','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="�ݶE���"></a>
		</td>
	</tr>
	<tr>
		<td>�ݶE�B��:</td>
		<td><textarea name="drRecPres" rows="3" cols="35" wrap="off" value="<%= (drRecVO==null)? "�п�J�B��" : drRecVO.getDrRecPres()%>" >
			</textarea>
		</td>
	</tr>
	<tr>
		<td>���:</td>
		<td><input type="text" name="drNo" size="45"
			value="<%= (drRecVO==null)? "aaa" : drRecVO.getDrNo()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<br>
<input type="submit" value="�e�X�s�W">

</FORM>
</body>

</html>
