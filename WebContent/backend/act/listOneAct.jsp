<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.act.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
ActVO actVO = (ActVO) request.getAttribute("actVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4001);
List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");

%>
<html>
<head>
<title>���ʸ�� - listOneEmp.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<title>��ݬ��� -listOneAct.jsp</title>

</head>
<body bgcolor='white'>
			<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>"> 

<table border='1' cellpadding='5' cellspacing='0' width='900'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>��ݬ��� </h3>
		<a href="<%=request.getContextPath()%>/backend/act/select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='900'>
	<tr>
		<th>���ʽs��</th><td>${actVO.actNo}</td>
	</tr>
	<tr>
		<th>���ʦW��</th><td>${actVO.actName}</td>
	</tr>
	<tr>
		<th>���ʤ��e</th>	<td>${actVO.actContent}</td>
	</tr>
	<tr>
		<th>�}�l�ɶ�</th><td>${actVO.actStartTime}</td>
	</tr>
	<tr>
		<th>�����ɶ�</th><td>${actVO.actEndTime}</td>
	</tr>
	<tr>
		<th>���ʷӤ�</th><td><img src="<%=request.getContextPath()%>/act/DBGifReader3?actNo=${actVO.actNo}"></td>
	</tr>
	<tr>
		<th>���ɾ���</th><td>${actVO.actEquipment}</td>
	</tr>
	<tr>
		<th>����O��</th><td>${actVO.actDeposit}</td>
	</tr>
	<tr>
		<th>�D���ú��O��</th><td>${actVO.actHostFee}</td>
	</tr>
	<tr>
		<th>���ʳ��W�O��</th><td>${actVO.actRegFee}</td>
	</tr>
	<tr>
		<th>�ثe���ʪ��A</th><td>${actVO.actStatus}</td>
	</tr>
	<tr>
		<th>�D���|���s��</th><td>${actVO.memNo}</td>
	</tr>
	<tr>
		<th>�f��¾���s��</th><td>${actVO.empNo}</td>
	</tr>
	
</table>
</c:if>
			<%@ include file="/menu2.jsp" %> 

</body>
</html>
