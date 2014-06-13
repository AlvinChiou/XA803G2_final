<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>
<%
	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO"); //EmpServlet.java (Concroller), �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<html>
<head>
<title>�̷s������ƭק� - update_news_input.jsp</title>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>�̷s������ƭק� - update_news_input.jsp</h3> <a
				href="<%=request.getContextPath()%>/select_page.jsp"><img
					src="images/back1.gif" width="100" height="32" border="0">�^����</a>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>
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
				<td>�����s��:<font color=red><b>*</b></font></td>
				<td>${newsVO.newsno}</td>
			</tr>
			<tr>
				<td>�����D�D:</td>
				<td><input type="TEXT" name="newstitle" size="45"
					value="<%=newsVO.getNewstitle()%>" /></td>
			</tr>
			<tr>
				<td>�������O:</td>
				<td><input type="TEXT" name="newstype" size="45"
					value="<%=newsVO.getNewstype()%>" /></td>
			</tr>
			<tr>
				<td>�������e:</td>
				<td><input type="TEXT" name="newscontent" size="45"
					value="<%=newsVO.getNewscontent()%>" /></td>
			</tr>
			<tr>
				<td>�Ӥ�:</td>
				<td><input type="file" name="newspic" size="45"
					value="<%=newsVO.getNewspic()%>" /></td>
			</tr>
			<tr>
				<td>�o���ɶ�:</td>
				<td bgcolor="#CCCCFF"><input class="cal-TextBox"
					onFocus="this.blur()" size="9" readonly type="text"
					name="newspotime" value="<%=newsVO.getNewspotime()%>"> <a
					class="so-BtnLink" href="javascript:calClick();return false;"
					onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
					onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
					onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','newspotime','BTN_date');return false;">
						<img align="middle" border="0" name="BTN_date"
						src="images/btn_date_up.gif" width="22" height="17" alt="�}�l���">
				</a></td>
			</tr>
			<tr>
				<td>���u�s��:</td>
				<td><input type="TEXT" name="empno" size="45"
					value="<%=newsVO.getEmpno()%>" /></td>
			</tr>




		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="newsno" value="<%=newsVO.getNewsno()%>"> <input
			type="hidden" name="requestURL"
			value="<%=request.getParameter("requestURL")%>"> <input
			type="hidden" name="whichPage"
			value="<%=request.getParameter("whichPage")%>"> <input
			type="submit" value="�e�X�ק�">
	</FORM>

</body>
</html>
