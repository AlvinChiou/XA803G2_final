<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.drRecord.model.*"%>
<%
	DrRecordVO drRecVO = (DrRecordVO) request.getAttribute("drRecVO"); //EmpServlet.java (Concroller), �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<html>
<head>
<title>�N�E�����ק� - update_drRecord_input.jsp</title>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�N�E�����ק� - update_drRecord_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></td>
	</tr>
</table>

<h3>�N�E�����ק� :</h3>
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

<FORM METHOD="post" ACTION="act.do" name="form1">
<table border="0">
	<tr>
		<td>�ݶE�����s��:<font color=red><b>*</b></font></td>
		<td><%=drRecVO.getDrRecNo()%></td>
	</tr>
	<tr>
		<td>�d���W�r:</td>
		<td><input type="TEXT" name="petNo" size="45" value="<%=drRecVO.getPetNo()%>" /></td>
	</tr>
	<tr>
		<td>�ݶE�ɶ�:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="drRecTime" value="<%=drRecVO.getDrRecTime()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','drRecTime','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="�ݶE�ɶ�"></a>
		</td>
	</tr>
	<tr>
		<td>�ݶE�B��:</td>
		<td><input type="TEXT" name="drRecPres" size="45" value="<%=drRecVO.getDrRecPres()%>" /></td>
	</tr>
	<tr>
		<td>���:</td>
		<td><input type="TEXT" name="drNo" size="45" value="<%=drRecVO.getDrNo()%>" /></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="drRecNo" value="<%=drRecVO.getDrRecNo()%>">
<input type="submit" value="�e�X�ק�"></FORM>

</body>
</html>
