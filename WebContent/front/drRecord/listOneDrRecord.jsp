<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.drRecord.model.*"%>
<%@ page import="com.pet.model.*"%>
<%
PetVO petVO = (PetVO) request.getAttribute("petVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����

%>
<html>
<head>
<title>�ݶE������� - listOneDrRecord.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�ݶE������� - listOneDrRecord.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>�ݶE�����s��</th>
		<th>�d���W�r</th>
		<th>�ɶ�</th>
		<th>�B��</th>
		<th>���</th>
		
	</tr>
	<tr align='center' valign='middle'>
			<td>${drRecVO.drRecNo}</td>
			<td>${drRecVO.petNo}</td>
			<td>${drRecVO.drRecTime}</td>
			<td>${drRecVO.drRecPres}</td>
			<td>${drRecVO.drNo}</td>
			
	</tr>
</table>

</body>
</html>
