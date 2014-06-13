<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.drRecord.model.*"%>
<%@ page import="com.pet.model.*"%>
<%
PetVO petVO = (PetVO) request.getAttribute("petVO"); //EmpServlet.java(Concroller), 存入req的empVO物件

%>
<html>
<head>
<title>看診紀錄資料 - listOneDrRecord.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>看診紀錄資料 - listOneDrRecord.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>看診紀錄編號</th>
		<th>寵物名字</th>
		<th>時間</th>
		<th>處方</th>
		<th>醫生</th>
		
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
