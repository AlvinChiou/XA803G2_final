<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%
	EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO"); //EmpServlet.java (Concroller), �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<html>
<head>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	

<title>���u��ƭק� </title>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>
	<%@ include file="/menu1.jsp" %> 

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>���u��ƭק�</h3> <a href="<%=request.getContextPath()%>/backend/emp/select_page.jsp"><img
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/emp/emp.do" name="form1" enctype="multipart/form-data">
		<table border="0">
			<tr>
				<td>���u�s��:<font color=red><b>*</b></font></td>
				<td><input type="hidden" name="empNo" value="<%=employeeVO.getEmpNo()%>" /><%=employeeVO.getEmpNo()%></td>
			</tr>
			<tr>
				<td>���u�m�W:</td>
				<td><input type="TEXT" name="empName" size="45" value="<%=employeeVO.getEmpName()%>" /></td>
			</tr>
			<tr>
				<td>�X�ͦ~���:</td>
				<td bgcolor="#CCCCFF"><input type="hidden" name="empBirth" value="<%=employeeVO.getEmpBirth()%>"><%=employeeVO.getEmpBirth()%></td>
			</tr>
			<tr>
				<td>�q��:</td>
				<td><input type="TEXT" name="empTel" size="45" value="<%=employeeVO.getEmpTel()%>" /></td>
			</tr>
			<tr>
				<td>�ʧO:</td>
				<td><c:if test="${employeeVO.empSex == 'M'}"><input type="hidden" name="empSex" value="<%=employeeVO.getEmpSex()%>" />�ӭ�</c:if>
					<c:if test="${employeeVO.empSex == 'F'}"><input type="hidden" name="empSex" value="<%=employeeVO.getEmpSex()%>" />���k</c:if></td>
			</tr>
			<tr>
				<td>¾��:</td>
				<td>
					<select name="empPos">
						<option value="">�п��</option>
						<option value="�t�κ޲z��">�t�κ޲z��</option>
						<option value="�g�z">�g�z</option>
						<option value="�����H��">�����H��</option>
						<option value="�ܮw�޲z�H��">�ܮw�޲z�H��</option>
						<option value="���ʺ޲z�H��">���ʺ޲z�H��</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>�~��:</td>
				<td><input type="TEXT" name="empSalary" size="45" value="<%=employeeVO.getEmpSalary()%>" /></td>
			</tr>
			<tr>
				<td>��¾��:</td>
				<td bgcolor="#CCCCFF"><input type="hidden" name="empArrDate" value="<%=employeeVO.getEmpArrDate()%>"><%=employeeVO.getEmpArrDate()%></td>
			</tr>
			<tr>
				<td>��¾��:</td>
				<td bgcolor="#CCCCFF"><input class="cal-TextBox"
					onFocus="this.blur()" size="11" readonly type="text" name="empOff"
					value="<%=employeeVO.getEmpOff()%>"> <a class="so-BtnLink"
					href="javascript:calClick();return false;"
					onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
					onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
					onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','empOff','BTN_date');return false;">
						<img align="middle" border="0" name="BTN_date"
						src="images/btn_date_up.gif" width="22" height="17" alt="�}�l���">
				</a></td>
			</tr>
			<tr>
				<td>�����Ҧr��:</td>
				<td><input type="hidden" name="empID" size="45" value="<%=employeeVO.getEmpID()%>" /><%=employeeVO.getEmpID()%></td>
			</tr>
			<tr>
				<td>��}:</td>
				<td><input type="TEXT" name="empAdd" size="45" value="<%=employeeVO.getEmpAdd()%>" /></td>
			</tr>
			<tr>
				<td>�Ӥ�:</td>
				<td><img src="DBGifReader3?empNo=${employeeVO.empNo}"></td>
			<tr>
				<td></td>
				<td><input type="file" name="empPic" size="45" /></td>
			</tr>
			<tr>
				<td>�K�X:</td>
				<td><input type="password" name="empPassword" value="<%=employeeVO.getEmpPassword()%>" /></td>
			</tr>
			<tr>
				<td>E-mail:</td>
				<td><input type="TEXT" name="empEmail" value="<%=employeeVO.getEmpEmail()%>" /></td>
			</tr>

		</table>
		<br> 
		<input type="hidden" name="action" value="update"> 
		<input type="hidden" name="empNo" value="<%=employeeVO.getEmpNo()%>">
		<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
		<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
		<input type="submit" value="�e�X�ק�">
	</FORM>
	<%@ include file="/menu2.jsp" %> 

</body>
</html>
