<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>
<%
	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
%>

<html>
<head>
<title>�̷s�����s�W - addNews.jsp</title>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>
</head>

<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>�̷s�����s�W - addNews.jsp</h3>
			</td>
			<td><a href="<%=request.getContextPath()%>/front/news/select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">�^����</a></td>
		</tr>
	</table>

	<h3>��ƭ��u:</h3>
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

	<FORM METHOD="post" ACTION="news.do" name="form1" enctype="multipart/form-data">
		<table border="0">

			<tr>
				<td>�����D�D:</td>
				<td><input type="TEXT" name="newstitle" size="45"
					value="<%=(newsVO == null) ? "���p���߸��x�y���h�˴ڦ�" : newsVO.getNewstitle()%>" /></td>
			</tr>
			<tr>
				<td>�������O:</td>
				<td><select size="1" name="newstype">
						<option value="1">������
						<option value="2">���i��
						<option value="3">�ӫ~��
					</select>
				</td>
			</tr>
			<tr>
				<td>�������e:</td>
				<td><textarea name="newscontent" rows="30" cols="55"><%=(newsVO == null)? "�ؤo�A�X���J�ж����i�߱ڡA�a��b����A���߫}���Ŷ����۸����A���i���A�𮧺�ı�A�߱��r���A�ѤѹB�ʱ`�O���d�C" : newsVO.getNewscontent()%></textarea>
<%-- 				<input type="TEXT" name="newscontent" size="45" value="<%=(newsVO == null) ? "�ؤo�A�X���J�ж����i�߱ڡA�a��b����A���߫}���Ŷ����۸����A���i���A�𮧺�ı�A�߱��r���A�ѤѹB�ʱ`�O���d�C" : newsVO.getNewscontent()%>" /></td> --%>
			</tr>
			<tr>
				<td>�Ӥ�:</td>
				<td><input type="file" name="newspic" size="45"
					value="<%=(newsVO == null) ? "full" : newsVO.getNewspic()%>" /></td>
			</tr>
			<tr>
				<%
					java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
				%>
			<td>�o���ɶ�:</td>
			<td bgcolor="#CCCCFF">
			    <input class="cal-TextBox" onFocus="this.blur()" size="9" readonly type="text" name="newspotime"
				value="<%=(newsVO == null) ? date_SQL : newsVO.getNewspotime()%>">
				<a class="so-BtnLink" href="javascript:calClick();return false;"
				onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
				onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
				onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','newspotime','BTN_date');return false;">
				<img align="middle" border="0" name="BTN_date" src="images/btn_date_up.gif" width="22" height="17" alt="�}�l���">
			</a></td>
			</tr>
			<tr>
				<td>���u�s��:</td>
				<td><input type="TEXT" name="empno" size="45"
					value="<%=(newsVO == null) ? "1008" : newsVO.getEmpno()%>" /></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> 
		     <input type="submit" value="�e�X�s�W">
	</FORM>
</body>

</html>
