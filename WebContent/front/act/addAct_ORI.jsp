<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.act.model.*"%>
<%@ page import="java.util.*"%>
<%
	ActVO actVO = (ActVO) request.getAttribute("ActVO");
%>

<html>
<head>

<title>���ʸ�Ʒs�W - addAct.jsp</title>

</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>���ʸ�Ʒs�W - addAct.jsp</h3>
			</td>
			<td><a href="select_page.jsp"><img src="images/tomcat.gif"
					width="100" height="100" border="1">�^����</a></td>
		</tr>
	</table>

	<h3>���ʸ��:</h3>
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

	<FORM METHOD="post" ACTION="act.do" name="form1"
		enctype="multipart/form-data">
		<table border="0">

			<tr>
				<td>���ʦW��:</td>
				<td><input type="TEXT" name="actName" size="20"
					value="<%=(actVO == null) ? "" : actVO.getActName()%>" /></td>
			</tr>
			<tr>
				<td>���ʤ��e:</td>
				<td><textarea name="actContent" rows="3" cols="30"
						value="<%=(actVO == null) ? "" : actVO.getActContent()%>" /></textarea></td>
			</tr>
			<tr>
				<%
					java.sql.Timestamp startDate_SQL = new java.sql.Timestamp(
							System.currentTimeMillis());
				%>
				<td>�}�l���:</td>
				<td bgcolor="#CCCCFF"><input class="cal-TextBox"
					onFocus="this.blur()" size="9" readonly type="text"
					name="startDate"
					value="<%=(actVO == null) ? "" : actVO.getActStartTime()%>">
					<a class="so-BtnLink" href="javascript:calClick();return false;"
					onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
					onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
					onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','startDate','BTN_date');return false;">
						<img align="middle" border="0" name="BTN_date"
						src="images/btn_date_up.gif" width="22" height="17" alt="�}�l���">
				</a> <select name="startHour" class="cal-TextBox" onFocus="this.blur()" size="1" >

						<%
							if ((TreeSet<Integer>) request.getAttribute("occupied") != null) {
								for (int i = 10; i < 22; i++) {
									boolean isOccupied = false;

									for (Integer j : (TreeSet<Integer>) request.getAttribute("occupied")) {
										if (i == j.intValue()) {
											isOccupied = true;
										}
									}
									if (!isOccupied) {
						%>
						<option value=" <%=i%>+':00:00' "><%=i%>:00</option>
						<%
							}
								}
							} else {
						%>
						<%-- 		    	<c:if test=""> --%>
						<!-- 		    	<option value="09:00:00">09:00</option> -->
						<%-- 		    	</c:if> --%>
						<option value="10:00:00">10:00</option>
						<option value="11:00:00">11:00</option>
						<option value="12:00:00">12:00</option>
						<option value="13:00:00">13:00</option>
						<option value="14:00:00">14:00</option>
						<option value="15:00:00">15:00</option>
						<option value="16:00:00">16:00</option>
						<option value="17:00:00">17:00</option>
						<option value="18:00:00">18:00</option>
						<option value="19:00:00">19:00</option>
						<option value="20:00:00">20:00</option>
						<option value="21:00:00">21:00</option>
						<%
							}
						%>
				</select></td>
			</tr>
			<tr>
				<%
					java.sql.Timestamp endDate_SQL = new java.sql.Timestamp(
							System.currentTimeMillis());
				%>
				<td>�������:</td>
				<td bgcolor="#CCCCFF"><input class="cal-TextBox"
					onFocus="this.blur()" size="9" readonly type="text" name="endDate"
					value="<%=(actVO == null) ? "" : actVO.getActEndTime()%>"> <a
					class="so-BtnLink" href="javascript:calClick();return false;"
					onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
					onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
					onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','endDate','BTN_date');return false;">
						<img align="middle" border="0" name="BTN_date"
						src="images/btn_date_up.gif" width="22" height="17" alt="�������">
				</a> <select name="endHour" class="cal-TextBox" onFocus="this.blur()" size="1" readonly>
						
						
						<%
							if ((TreeSet<Integer>) request.getAttribute("occupied") != null) {
								for (int i = 10; i < 22; i++) {
									boolean isOccupied = false;

									for (Integer j : (TreeSet<Integer>) request.getAttribute("occupied")) {
										if (i == j.intValue()) {
											isOccupied = true;
										}
									}
									if (!isOccupied) {
						%>
						<option value=" <%=i%>+':00:00' "><%=i%>:00</option>
						<%
							}
								}
							} else {
						%>
						
						<option value="09:00:00">09:00</option>
						<option value="10:00:00">10:00</option>
						<option value="11:00:00">11:00</option>
						<option value="12:00:00">12:00</option>
						<option value="13:00:00">13:00</option>
						<option value="14:00:00">14:00</option>
						<option value="15:00:00">15:00</option>
						<option value="16:00:00">16:00</option>
						<option value="17:00:00">17:00</option>
						<option value="18:00:00">18:00</option>
						<option value="19:00:00">19:00</option>
						<option value="20:00:00">20:00</option>
						<option value="21:00:00">21:00</option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<td>���ʷӤ�:</td>
				<td><input type="file" name="actPic" size="20"
					value="<%=(actVO == null) ? "pic" : actVO.getActPic()%>" /></td>
			</tr>

			<tr>
				<td>���ɾ���:</td>
				<td><textarea name="actEquipment" rows="3" cols="30"
						value="<%=(actVO == null) ? "�L" : actVO.getActEquipment()%>" /></textarea></td>
			</tr>
			<tr>
				<td>����O��:</td>
				<td><input type="TEXT" name="actDeposit" size="20"
					value="<%=(actVO == null) ? "1500" : actVO.getActDeposit()%>"
					readonly /></td>
			</tr>
			<tr>
				<td>���ɳ��a�O��:</td>
				<td><input type="TEXT" name="actHostFee" size="20"
					value="<%=(actVO == null) ? "1000" : actVO.getActHostFee()%>"
					readonly /></td>
			</tr>
			<tr>
				<td>���ʳ��W�O��:</td>
				<td><input type="TEXT" name="actRegFee" size="20"
					value="<%=(actVO == null) ? "" : actVO.getActRegFee()%>" /></td>
			</tr>
			<tr>
				<td><input type="hidden" name="actStatus" size="10"
					value="<%=(actVO == null) ? "N" : actVO.getActStatus()%>" /></td>
			</tr>
			<tr>
				<td>�D���|���s��:</td>
				<td><input type="TEXT" name="memNo" size="10"
					value="<%=(actVO == null) ? "" : actVO.getMemNo()%>" /></td>
			</tr>
			<tr>
				<td>�f��¾���s��:</td>
				<td><input type="TEXT" name="empNo" size="10"
					value="<%=(actVO == null) ? "1003" : actVO.getEmpNo()%>" /></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <br>
		<input type="submit" value="�e�X�s�W">

	</FORM>

</body>

</html>
