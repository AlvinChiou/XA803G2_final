<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.actregister.model.*"%>
<%
ActRegisterVO actRegVO = (ActRegisterVO) request.getAttribute("actRegVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>
<html>
<head>
<title> ���ʳ��W��� - listOneActRegister.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���ʳ��W��� - listOneActRegister.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>
<a id="titleimg"><img src="<%= request.getContextPath()%>/images/main_tit03.png"></a>
<br>
<p style="text-align:right; margin-right:50px; color:orange;">�ثe��m�G<a href="<%=request.getContextPath()%>/iPETCares.jsp">����</a>>><a href="<%=request.getContextPath()%>/front/act/listAllAct.jsp">�Ҧ����ʸ�T</a>
 >><a href="<%=request.getContextPath()%>/front/actRegister/listOneActRegister.jsp">���W���p</a></p>

<table border='1' bordercolor='#CCCCFF' width='900'>
	<tr>
		<th>���ʳ��W�s��</th>
		<th>���W���ʦW��</th>
		<th>���W���</th>
		<th>���W�ɶ�</th>
		<th>�I�O���A</th>
		<th>�|���s��</th>
		<th>���ʽs��</th>
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
