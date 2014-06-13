<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.act.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	ActVO actVO = (ActVO) request.getAttribute("ActVO");
PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4001);
List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<title>前端-活動新增 - addAct.jsp</title>

</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>
			<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>"> 

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>前端-活動資料新增 - addAct.jsp</h3>
			</td>
			<td><a href="select_page.jsp"><img src="images/tomcat.gif"
					width="100" height="100" border="1">回首頁</a></td>
		</tr>
	</table>

	<h3>活動資料:</h3>
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


	<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
	
	<FORM METHOD="post" ACTION="act.do" name="form2">
		<b>選擇活動日期:</b> <input class="cal-TextBox" onFocus="this.blur()"
			size="12" readonly type="text" name="actStartDate" value="<%=date_SQL%>"> 
			<a class="so-BtnLink" href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form2','actStartDate','BTN_date');return false;">
			<img align="middle" border="0" name="BTN_date" src="images/btn_date_up.gif" width="22" height="17" alt="活動開始日期"></a> 
			<input type="submit" value="送出"> <input type="hidden" name="action" value="getAll_Acts_By_Date_For_Time">
	</FORM>


	<FORM METHOD="post" ACTION="act.do" name="form1" enctype="multipart/form-data">
		<table border="0">

			<tr>
				<td>活動名稱:</td>
				<td><input type="TEXT" name="actName" size="20"
					value="<%=(actVO == null) ? "" : actVO.getActName()%>" /></td>
			</tr>
			<tr>
				<td>活動內容:</td>
				<td><textarea name="actContent" rows="3" cols="30" value="<%=(actVO == null) ? "" : actVO.getActContent()%>" /></textarea></td>
			</tr>
			<tr>
				<td>日期:</td>
				<td><input type=text name="actStartDate" value="<%= request.getParameter("actStartDate") %>" readonly></td>
			</tr>
			<tr>
				<td>時間:</td>
				<td>開始時間: <select name="startHour" class="cal-TextBox" onFocus="this.blur()" size="1">
						<%
							if ((TreeSet<Integer>) request.getAttribute("occupied") != null) {
								for (int i = 10; i < 23; i++) {
									boolean isOccupied = false;

									for (Integer j : (TreeSet<Integer>) request.getAttribute("occupied")) {
										if (i == j.intValue()) {
											isOccupied = true;
										}
									}
									if (!isOccupied) {
						%>
						<option value=" <%=i%>:00:00"><%=i%>:00
						</option>
						<%
							}
								}
							} else {
						%>
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
				</select> 
				結束時間: <select name="endHour" class="cal-TextBox" onFocus="this.blur()" size="1" readonly>

						<%
							if ((TreeSet<Integer>) request.getAttribute("occupied") != null) {
								for (int i = 10; i < 23; i++) {
									boolean isOccupied = false;

									for (Integer j : (TreeSet<Integer>) request.getAttribute("occupied")) {
										if (i == j.intValue()) {
											isOccupied = true;
										}
									}
									if (!isOccupied) {
						%>
						<option value=" <%=i%>:00:00"><%=i%>:00</option>
						<%
							}
								}
							} else {
						%>
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
				<td>活動照片:</td>
				<td><input type="file" name="actPic" size="20"
					value="<%=(actVO == null) ? "pic" : actVO.getActPic()%>" /></td>
			</tr>

			<tr>
				<td>租借器材:</td>
				<td><textarea name="actEquipment" rows="3" cols="30"
						value="<%=(actVO == null) ? "無" : actVO.getActEquipment()%>" /></textarea></td>
			</tr>
			<tr>
				<td>押金費用:</td>
				<td><input type="TEXT" name="actDeposit" size="20"
					value="<%=(actVO == null) ? "1500" : actVO.getActDeposit()%>"
					readonly /></td>
			</tr>
			<tr>
				<td>租借場地費用:</td>
				<td><input type="TEXT" name="actHostFee" size="20"
					value="<%=(actVO == null) ? "1000" : actVO.getActHostFee()%>"
					readonly /></td>
			</tr>
			<tr>
				<td>活動報名費用:</td>
				<td><input type="TEXT" name="actRegFee" size="20"
					value="<%=(actVO == null) ? "" : actVO.getActRegFee()%>" /></td>
			</tr>
			<tr><td>活動類型:</td>
				<td>
					<input type="radio" name="actStatus" value="A" />私人
					<input type="radio" name="actStatus" value="N" />公開
				</td>
			</tr>
			<tr>
				<td>主辦方會員編號:</td>
				<td><input type="TEXT" name="memNo" size="10"
					value="<%=(actVO == null) ? "" : actVO.getMemNo()%>" /></td>
			</tr>
			<tr>
				<td>審核職員編號:</td>
				<td><input type="TEXT" name="empNo" size="10"
					value="<%=(actVO == null) ? "1003" : actVO.getEmpNo()%>" /></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <br>
		<input type="submit" value="送出新增">

	</FORM>
	</c:if>
			<%@ include file="/menu2.jsp" %> 

</body>

</html>
