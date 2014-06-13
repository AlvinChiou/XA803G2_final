<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.actregister.model.*"%>
<%
ActRegisterVO actRegVO = (ActRegisterVO) request.getAttribute("actRegVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title> 活動報名資料 - listOneActRegister.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>活動報名資料 - listOneActRegister.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>
<a id="titleimg"><img src="<%= request.getContextPath()%>/images/main_tit03.png"></a>
<br>
<p style="text-align:right; margin-right:50px; color:orange;">目前位置：<a href="<%=request.getContextPath()%>/iPETCares.jsp">首頁</a>>><a href="<%=request.getContextPath()%>/front/act/listAllAct.jsp">所有活動資訊</a>
 >><a href="<%=request.getContextPath()%>/front/actRegister/listOneActRegister.jsp">報名狀況</a></p>

<table border='1' bordercolor='#CCCCFF' width='900'>
	<tr>
		<th>活動報名編號</th>
		<th>報名活動名稱</th>
		<th>報名日期</th>
		<th>報名時間</th>
		<th>付費狀態</th>
		<th>會員編號</th>
		<th>活動編號</th>
	</tr>
	<tr align='center' valign='middle'>
			<td>${actRegVO.actRegNo}</td>
			<td>${actRegVO.actRegName}</td>
			<td>${actRegVO.actRegDate}</td>
			<td>${actRegVO.actRegTime}</td>
			<td>${actRegVO.actRegPayState}</td>
			<td>${actRegVO.memNo}</td>
			<td>${actRegVO.actNo}</td>
			
	</tr>
</table>

</body>
</html>
