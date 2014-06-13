<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%
	EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");
%>

<html>
<head>
<title>員工資料新增 - addEmp.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		

<script>
function miracle() {
	document.getElementsByName("empName")[0].value = "xa803g2";
	document.getElementsByName("empTel")[0].value = "0919053489";
	document.getElementsByName("empSalary")[0].value = "35000";
	document.getElementsByName("empID")[0].value = "P123911545";
	document.getElementsByName("empAdd")[0].value = "桃園縣中壢市中大路300-1號";
	document.getElementsByName("empEmail")[0].value = "luke02141@gmail.com";
}
</script>

</head>
<link rel="stylesheet" type="text/css" href="pages/calendar.css">
<script language="JavaScript" src="pages/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<%@ include file="/menu1.jsp" %> 
<input type = "button" id = "btn" value = "神奇小按鈕" onclick = "miracle()">
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>員工資料新增</h3>
			</td>
			<td><a href="<%=request.getContextPath()%>/backend/emp/select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a></td>
		</tr>
	</table>

	<h3>資料員工:</h3>
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

	<FORM METHOD="post" ACTION="emp.do" name="form1" enctype="multipart/form-data">
		<table border="0">

			<tr>
				<td>員工姓名:</td>
				<td><input type="TEXT" name="empName" size="45"
					value="<%=(employeeVO == null) ? "" : employeeVO.getEmpName()%>" /></td>
			</tr>
			<tr>
				<%
					java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
				%>
				<td>出生年月日:</td>
				<td bgcolor="#CCCCFF"><input class="cal-TextBox"
					onFocus="this.blur()" size="9" readonly type="text" name="empBirth"
					value="<%=(employeeVO == null) ? date_SQL : employeeVO.getEmpBirth()%>">
					<a class="so-BtnLink" href="javascript:calClick();return false;"
					onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
					onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
					onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','empBirth','BTN_date');return false;">
					<img align="middle" border="0" name="BTN_date"
						 src="images/btn_date_up.gif" width="22" height="17" alt="開始日期"></a>
				</td>
			</tr>
			<tr>
				<td>電話:</td>
				<td><input type="TEXT" name="empTel" size="45" value="<%=(employeeVO == null) ? "" : employeeVO.getEmpTel()%>" /></td>
			</tr>
			<tr>
				<td>性別:</td>
				<td>
					<input type="radio" name="empSex" size="2" value="<%=(employeeVO == null) ? "M" : employeeVO.getEmpSex()%>" checked='' />男
					<input type="radio" name="empSex" size="2" value="<%=(employeeVO == null) ? "F" : employeeVO.getEmpSex()%>" />女
			    </td>
			</tr>
			<tr>
				<td>職稱:</td>
				<td>
					<select name="empPos">
						<option value="" checked>請選擇</option>
						<option value="系統管理員">系統管理員</option>
						<option value="經理">經理</option>
						<option value="醫療人員">醫療人員</option>
						<option value="倉庫管理人員">倉庫管理人員</option>
						<option value="活動管理人員">活動管理人員</option>
					</select>
<%-- 					<input type="TEXT" name="empPos" size="45" value="<%=(employeeVO == null) ? "醫療人員" : employeeVO.getEmpPos()%>" /> --%>
				</td>
			</tr>
			<tr>
				<td>薪資:</td>
				<td><input type="TEXT" name="empSalary" size="45" value="<%=(employeeVO == null) ? "" : employeeVO.getEmpSalary()%>" /></td>
			</tr>
			<tr>
				<%
					java.sql.Date date_STRSQL = new java.sql.Date(System.currentTimeMillis());
				%>
				<td>到職日:</td>
				<td bgcolor="#CCCCFF"><input class="cal-TextBox"
					onFocus="this.blur()" size="9" readonly type="text"
					name="empArrDate"
					value="<%=(employeeVO == null) ? date_SQL : employeeVO
					.getEmpArrDate()%>">
					<a class="so-BtnLink" href="javascript:calClick();return false;"
					onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
					onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
					onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','empArrDate','BTN_date');return false;">
						<img align="middle" border="0" name="BTN_date"
						src="images/btn_date_up.gif" width="22" height="17" alt="開始日期">
				</a></td>
			</tr>
			
				<%
					java.sql.Date date_ENDSQL = new java.sql.Date(System.currentTimeMillis());
				%>
<!-- 				<td>離職日:</td> -->
				<input class="cal-TextBox" onFocus="this.blur()" size="9" readonly type="hidden" name="empOff"
					value="<%=(employeeVO == null) ? date_ENDSQL : employeeVO.getEmpOff()%>">
<!-- 					<a class="so-BtnLink" href="javascript:calClick();return false;" -->
<!-- 					onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);" -->
<!-- 					onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);" -->
<!-- 					onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','empOff','BTN_date');return false;"> -->
<!-- 						<img align="middle" border="0" name="BTN_date" -->
<!-- 						src="images/btn_date_up.gif" width="22" height="17" alt="開始日期"> -->
<!-- 					</a> -->
				
			
			<tr>
				<td>身分證字號:</td>
				<td><input type="TEXT" name="empID" size="45"
					value="<%=(employeeVO == null) ? "" : employeeVO.getEmpID()%>" /></td>
			</tr>
			<tr>
				<td>住址:</td>
				<td><input type="TEXT" name="empAdd" size="45"
					value="<%=(employeeVO == null) ? "" : employeeVO.getEmpAdd()%>" /></td>
			</tr>
			<tr>
				<td>照片:</td>
				<td><input type="file" name="empPic" size="45"
					value="<%=(employeeVO == null) ? "" : employeeVO.getEmpPic()%>" /></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>密碼:</td> -->
<%-- 				<td><input type="password" name="empPassword" value="<%=(employeeVO == null)? "" : employeeVO.getEmpPassword() %>"></td> --%>
<!-- 			</tr> -->
			<tr>
				<td>E-mail:</td>
				<td><input type="TEXT" name="empEmail" value="<%=(employeeVO == null)? "" : employeeVO.getEmpEmail() %>"></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> 
		     <input type="submit" value="送出新增">
	</FORM>
	
		<%@ include file="/menu2.jsp" %> 
	
</body>

</html>
