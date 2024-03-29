<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shift.model.*"%>
<%
	ShiftVO shiftVO = (ShiftVO) request.getAttribute("shiftVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<title>員工資料修改 - update_emp_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>
	<%@ include file="/menu1.jsp"%>

<!-- <table border='1' cellpadding='5' cellspacing='0' width='400'> -->
<!-- 	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'> -->
<!-- 		<td> -->
<!-- 		<h3>員工資料修改 - update_emp_input.jsp</h3> -->
<!-- 		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td> -->
<!-- 	</tr> -->
<!-- </table> -->

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

<FORM METHOD="post" ACTION="shift.do" name="form1">
<table border="0">
<!-- 	<tr> -->
<!-- 		<td>班表編號:<font color=red><b>*</b></font></td> -->
<%-- 		<td><%=shiftVO.getShiftNo()%></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>班日期:</td>
		<td><%=shiftVO.getShiftDate()%></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>最大看診人數:</td> -->
<%-- 		<td><input type="TEXT" name="shiftMaximum" size="45"	value="<%=shiftVO.getShiftMaximum()%>" /></td> --%>
<!-- 	</tr> -->
	<tr>
<!-- 		<td>雇用日期:</td> -->
<!-- 		<td bgcolor="#CCCCFF"> -->
<!-- 		    <input class="cal-TextBox" -->
<%-- 			onFocus="this.blur()" size="9" readonly type="text" name="hiredate" value="<%=empVO.getHiredate()%>"> --%>
<!-- 			<a class="so-BtnLink" -->
<!-- 			href="javascript:calClick();return false;" -->
<!-- 			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);" -->
<!-- 			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);" -->
<!-- 			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','hiredate','BTN_date');return false;"> -->
<!-- 		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="開始日期"></a> -->
<!-- 		</td> -->
	</tr>
	<tr>
		<td>時段(A.M. or P.M):</td>
		<td><%=shiftVO.getShiftPeriod()%></td>
	</tr>
	<tr>
		<jsp:useBean id="drSvc" scope="page" class="com.doctor.model.DoctorService" />
	
	
		<td>
<!-- 		<td><input type="TEXT" name="drNo" size="45" -->
<%-- 			value="<%= (shiftVO==null)? "100" : shiftVO.getDrNo()%>" /></td> --%>
	 <b>選擇醫師:</b>
       <select size="1" name="drNo">
         <c:forEach var="drVO" items="${drSvc.all}" > 
          <option value="${drVO.drNo}">${drVO.drName}
         </c:forEach>   
       </select>
       
       </td>
	</tr>

	<jsp:useBean id="shiftSvc" scope="page" class="com.shift.model.ShiftService" />
<!-- 	<tr> -->
<!-- 		<td>醫生:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="shiftNo"> -->
<%-- 			<c:forEach var="shiftVO" items="${shiftSvc.all}"> --%>
<%-- 				<option value="${shiftVO.shiftNo}" ${(shiftVO.shiftNo==shiftVO.shiftNo)?'selected':'' } >${shiftVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="shiftNo" value="<%=shiftVO.getShiftNo()%>">
<input type="hidden" name="shiftDate" value="<%=shiftVO.getShiftDate()%>">
<input type="hidden" name="shiftPeriod" value="<%=shiftVO.getShiftPeriod()%>">
<input type="submit" value="送出修改"></FORM>
	<%@ include file="/menu2.jsp"%>

</body>
</html>
