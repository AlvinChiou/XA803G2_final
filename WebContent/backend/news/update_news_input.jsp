<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>

<%@ page import="java.util.*"%>

<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO"); //EmpServlet.java (Concroller), �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)

	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4008);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>
<html>
<head>
<title>�̷s������ƭק� - update_news_input.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
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
				<h3>�̷s������ƭק�</h3> <a
				href="<%=request.getContextPath()%>/backend/news/select_page.jsp"><img
					src="<%=request.getContextPath()%>/backend/news/images/back1.gif" width="100" height="32" border="0">�^����</a>
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
				<td><input type="TEXT" name="newstitle" size="45" value="<%=newsVO.getNewstitle()%>" /></td>
			</tr>
			<tr>
				<td>�������O:</td>
				<td>
					<select name="newstype">
						<option value='1'>������</option>
						<option value='2'>���i��</option>
						<option value='3'>�ӫ~��</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>�������e:</td>
				<td>
				<textarea name="newscontent" rows="30" cols="55"><%= newsVO.getNewscontent()%></textarea>
				</td>
			</tr>
			<tr>
				<td>�Ӥ�:</td>
				<td><img src="<%= request.getContextPath()%>/news/DBGifReader?newsno=${newsVO.newsno}"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="file" name="newspic" size="45"	value="<%=newsVO.getNewspic()%>" /></td>
			</tr>
			<tr>
				<td>�o���ɶ�:</td>
				<td>
					<input type="hidden" name="newspotime" value="<%= newsVO.getNewspotime()%>"><%= newsVO.getNewspotime()%>
				</td>
			</tr>
			<tr>
				<td>���u�s��:</td>
<%-- 				<td><input type="TEXT" name="empno" size="45" value="${employeeVO.empNo}" />{employeeVO.empNo}</td> --%>
				<td><input type="hidden" name="empno" size="45" value="1001" />1001</td>
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
</c:if> 
	<%@ include file="/menu2.jsp" %> 

</body>
</html>
