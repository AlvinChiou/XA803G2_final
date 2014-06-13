<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.doctor.model.*"%>
<%
	DoctorVO doctorVO = (DoctorVO) request.getAttribute("doctorVO");
%>

<html>
<head>
<title>��v��Ʒs�W - addDr.jsp</title>
</head>
<link rel="stylesheet" type="text/css" href="pages/calendar.css">
<script language="JavaScript" src="pages/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>��v��Ʒs�W - addDr.jsp</h3>
			</td>
			<td><a href="<%=request.getContextPath()%>/front/dr/select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">�^����</a>
			</td>
		</tr>
	</table>

	<h3>�����v:</h3>
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

	<FORM METHOD="post" ACTION="dr.do" name="form1" enctype="multipart/form-data">
		<table border="0">

			<tr>
				<td>��v�m�W:</td>
				<td><input type="TEXT" name="drName" size="45"
					value="<%=(doctorVO == null) ? "KING" : doctorVO.getDrName()%>" /></td>
			</tr>
			<tr>
				<td>�Ǹg��:</td>
				<td><input type="TEXT" name="drExp" size="45"
					value="<%=(doctorVO == null) ? "�����H��" : doctorVO.getDrExp()%>" /></td>
			</tr>
			<tr>
				<td>�ʧO:</td>
				<td><input type="TEXT" name="drSex" size="45"
					value="<%=(doctorVO == null) ? "M" : doctorVO.getDrSex()%>" /></td>
			</tr>
			<tr>
				<td>�Ӥ�:</td>
				<td><input type="file" name="drPic" size="45"
					value="<%=(doctorVO == null) ? "full" : doctorVO.getDrPic()%>" /></td>
			</tr>
			<tr>
				<%	java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
				<td>�X�ͦ~���:</td>
				<td bgcolor="#CCCCFF">
				    <input class="cal-TextBox"
					onFocus="this.blur()" size="9" readonly type="text" name="drBirth"
					value="<%=(doctorVO == null) ? date_SQL : doctorVO.getDrBirth()%>">
					<a class="so-BtnLink" href="javascript:calClick();return false;"
					onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
					onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
					onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','drBirth','BTN_date');return false;">
				    <img align="middle" border="0" name="BTN_date" src="images/btn_date_up.gif" width="22" height="17" alt="�}�l���"></a>
				</td>
			</tr>
			<tr>
				<td>��}:</td>
				<td><input type="TEXT" name="drAdd" size="45"
					value="<%=(doctorVO == null) ? "��鿤���c�����v��2�F���j��300��" : doctorVO.getDrAdd()%>" /></td>
			</tr>
			<tr>
				<td>�q��:</td>
				<td><input type="TEXT" name="drTel" size="45"
					value="<%=(doctorVO == null) ? "0932322331" : doctorVO.getDrTel()%>" /></td>
			</tr>
			


		 </table>
		<br> 
		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="�e�X�s�W">
	</FORM>
</body>

</html>
