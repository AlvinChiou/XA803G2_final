<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>
<%
	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
%>

<html>
<head>
<title>最新消息新增 - addNews.jsp</title>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>
</head>

<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>最新消息新增 - addNews.jsp</h3>
			</td>
			<td><a href="<%=request.getContextPath()%>/front/news/select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a></td>
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

	<FORM METHOD="post" ACTION="news.do" name="form1" enctype="multipart/form-data">
		<table border="0">

			<tr>
				<td>消息主題:</td>
				<td><input type="TEXT" name="newstitle" size="45"
					value="<%=(newsVO == null) ? "中小型貓跳台造型多樣款式" : newsVO.getNewstitle()%>" /></td>
			</tr>
			<tr>
				<td>消息類別:</td>
				<td><select size="1" name="newstype">
						<option value="1">活動類
						<option value="2">公告類
						<option value="3">商品類
					</select>
				</td>
			</tr>
			<tr>
				<td>消息內容:</td>
				<td><textarea name="newscontent" rows="30" cols="55"><%=(newsVO == null)? "尺寸適合公寓房間的養貓族，靠放在牆邊，讓貓咪有空間蹦蹦跳跳，抓抓磨爪，休息睡覺，心情愉悅，天天運動常保健康。" : newsVO.getNewscontent()%></textarea>
<%-- 				<input type="TEXT" name="newscontent" size="45" value="<%=(newsVO == null) ? "尺寸適合公寓房間的養貓族，靠放在牆邊，讓貓咪有空間蹦蹦跳跳，抓抓磨爪，休息睡覺，心情愉悅，天天運動常保健康。" : newsVO.getNewscontent()%>" /></td> --%>
			</tr>
			<tr>
				<td>照片:</td>
				<td><input type="file" name="newspic" size="45"
					value="<%=(newsVO == null) ? "full" : newsVO.getNewspic()%>" /></td>
			</tr>
			<tr>
				<%
					java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
				%>
			<td>發布時間:</td>
			<td bgcolor="#CCCCFF">
			    <input class="cal-TextBox" onFocus="this.blur()" size="9" readonly type="text" name="newspotime"
				value="<%=(newsVO == null) ? date_SQL : newsVO.getNewspotime()%>">
				<a class="so-BtnLink" href="javascript:calClick();return false;"
				onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
				onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
				onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','newspotime','BTN_date');return false;">
				<img align="middle" border="0" name="BTN_date" src="images/btn_date_up.gif" width="22" height="17" alt="開始日期">
			</a></td>
			</tr>
			<tr>
				<td>員工編號:</td>
				<td><input type="TEXT" name="empno" size="45"
					value="<%=(newsVO == null) ? "1008" : newsVO.getEmpno()%>" /></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> 
		     <input type="submit" value="送出新增">
	</FORM>
</body>

</html>
