<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.doctor.model.*"%>
<%
	DoctorVO doctorVO = (DoctorVO) request.getAttribute("doctorVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>醫師資料修改 - update_dr_input.jsp</title>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>醫師資料修改 - update_dr_input.jsp</h3> 
				<a href="<%=request.getContextPath()%>/front/dr/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
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

	<FORM METHOD="post" ACTION="dr.do" name="form1" enctype="multipart/form-data">
		<table border="0">
			<tr>
				<td>醫師編號:<font color=red><b>*</b></font></td>
				<td><%=doctorVO.getDrNo()%></td>
			</tr>
			<tr>
				<td>醫師姓名:</td>
				<td><input type="TEXT" name="drName" size="45"
					value="<%=doctorVO.getDrName()%>" /></td>
			</tr>
			<tr>
				<td>學經歷:</td>
				<td><input type="TEXT" name="drExp" size="45"
					value="<%=doctorVO.getDrExp()%>" /></td>
			</tr>
			<tr>
				<td>性別:</td>
				<td><input type="TEXT" name="drSex" size="45"
					value="<%=doctorVO.getDrSex()%>" /></td>
			</tr>
			<tr>
				<td>照片:</td>
				<td><input type="file" name="drPic" size="45"
					value="<%=doctorVO.getDrPic()%>" /></td>
			</tr>
	         <tr>
				<td>出生年月日:</td>
				<td bgcolor="#CCCCFF">
				    <input class="cal-TextBox"
					onFocus="this.blur()" size="9" readonly type="text" name="drBirth"
					value="<%=doctorVO.getDrBirth()%>"> <a
					class="so-BtnLink" href="javascript:calClick();return false;"
					onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
					onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
					onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','drBirth','BTN_date');return false;">
					<img align="middle" border="0" name="BTN_date" src="images/btn_date_up.gif" width="22" height="17" alt="開始日期"></a>
				</td>
			</tr>
			<tr>
				<td>住址:</td>
				<td><input type="TEXT" name="drAdd" size="45"
					value="<%=doctorVO.getDrAdd()%>" /></td>
			</tr>
			<tr>
				<td>電話:</td>
				<td><input type="TEXT" name="drTel" size="45"
					value="<%=doctorVO.getDrTel()%>" /></td>
			</tr>
			


		</table>
		<br> 
		<input type="hidden" name="action" value="update"> 
		<input type="hidden" name="drNo" value="<%=doctorVO.getDrNo()%>">
		<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
		<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
		<input type="submit" value="送出修改">
	</FORM>

<br>送出修改的來源網頁路徑:<br><b>
   <font color=blue>request.getParameter("requestURL"):</font> <%= request.getParameter("requestURL")%><br>
   <font color=blue>request.getParameter("whichPage"):</font> <%= request.getParameter("whichPage")%> (此範例目前只用於:istAllEmp.jsp))</b>

</body>
</html>
