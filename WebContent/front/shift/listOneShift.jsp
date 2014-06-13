<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.shift.model.*"%>
<%
ShiftVO shiftVO = (ShiftVO) request.getAttribute("shiftVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料 - ListOneEmp.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>掛號編號</th>
		<th>掛號日期</th>
		<th>最大人數</th>
		<th>班表時段</th>
		<th>醫生編號</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=shiftVO.getShiftNo()%></td>
		<td><%=shiftVO.getShiftDate()%></td>
		<td><%=shiftVO.getShiftMaximum()%></td>
		<td><%=shiftVO.getShiftPeriod()%></td>
		<td><%=shiftVO.getDrNo()%></td>

	</tr>
</table>

</body>
</html>
