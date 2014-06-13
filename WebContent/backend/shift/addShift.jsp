<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shift.model.*"%>
<%
ShiftVO shiftVO = (ShiftVO) request.getAttribute("shiftVO");
String shiftDate = request.getParameter("shiftDate"); 

%>

<html>
<head>
<title>員工資料新增 - addEmp.jsp</title></head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>

<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>
	<%@ include file="/menu1.jsp" %> 

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>iPETCares Dr-shift: Home</h3><font color=red>( MVC )</font></td>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shift/shift.do" name="form1">
<table border="0">
<!-- 	<tr> -->
<!-- 		<td>班表號碼</td> -->
<!-- 		<td><input type="TEXT" name="shiftNo" size="45"  -->
<%-- 			value="<%= (shiftVO==null)? "吳永志" : shiftVO.getShiftNo()%>" /></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>班表日期:</td>
		<td>
			<% if ( shiftDate != null ) { %>
				<%=shiftDate%>
			<% } else {%>
		<input type="TEXT" name="shiftDate" size="45"
			value="<%= (shiftVO==null)? "2014-5-16" : shiftVO.getShiftDate()%>" />
		<%}%>
		</td>
	</tr>
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
<!-- 		<td>班表看診最大人數:</td> -->
<!-- 		<td bgcolor="#CCCCFF"> -->
<!-- 		    <input class="cal-TextBox" -->
<%-- 			onFocus="this.blur()" size="9" readonly type="text" name="hiredate" value="<%= (empVO==null)? date_SQL : empVO.getHiredate()%>"> --%>
<!-- 			<a class="so-BtnLink" -->
<!-- 			href="javascript:calClick();return false;" -->
<!-- 			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);" -->
<!-- 			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);" -->
<!-- 			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','hiredate','BTN_date');return false;"> -->
<!-- 		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="開始日期"></a> -->
<!-- 		</td> -->
<!-- 	<td><input type="TEXT" name="shiftMaximum" size="45" -->
<%-- 			value="<%= (shiftVO==null)? "看診最大人數" : shiftVO.getShiftMaximum()%>" /></td> --%>
	</tr>
		<jsp:useBean id="shiftSvc" scope="page" class="com.shift.model.ShiftService" />
	
	<tr>
		<td>班表時段 (上午下午):</td>
		<td>
<!-- 			<input type="TEXT" name="shiftPeriod" size="45" -->
<%-- 			value="<%= (shiftVO==null)? "上午" : shiftVO.getShiftPeriod()%>" /> --%>
	   <select size="1" name="shiftPeriod">
          <option value="上午">上午</option>
   		  <option value="下午">下午</option>
       </select>
		</td>
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

<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="shiftNo"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="insert">
<% if ( shiftDate != null ) { %>	
	<input type = "hidden" name = "shiftDate" value = "<%=shiftDate%>"/>
<%}%>
<input type="submit" value="送出新增"></FORM>
<%@ include file="/menu2.jsp" %> 
</body>

</html>
