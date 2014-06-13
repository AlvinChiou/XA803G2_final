<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<%@ page import="com.order.model.*" %>

<%@ page import="java.util.*"%>

<%@ page import="com.pow.model.*"%>
<%@ page import="com.doctor.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
OrderVO orderVO = (OrderVO) request.getAttribute("orderVO");

PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4006);
List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");


 %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�浧�q����</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
</head>
<body>

<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>"> 

<div bgcolor="yellow" align="center">�浧�q����</div>
<table border='0' bordercolor='#CCCCFF'>
		<tr>
		<th>�q��s��</th>
		<th>�U��ɶ�</th>
		<th>�t�e�a�}</th>
		<th>�R��s���q��</th>
		<th>�X�f�ɶ�</th>
		<th>�e�F�ɶ�</th>
		<th>�P��ɶ�</th>
		<th>�q�檬�A</th>
		<th>�|���s��</th>
		<th>�ӿ�H���u�s��</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=orderVO.getOrdno()%></td>
		<td><%=orderVO.getOrdtime()%></td>
		<td><%=orderVO.getOrdaddr()%></td>
		<td><%=orderVO.getOrdtel()%></td>
		<td><%=(orderVO.getOrdgotime()==null?"���X�f":orderVO.getOrdgotime())%></td>
		<td><%=(orderVO.getOrdarrtime()==null?"���e�F":orderVO.getOrdarrtime())%></td>
		<td><%=(orderVO.getOrddeltime()==null?"���P��":orderVO.getOrddeltime())%></td>
		<td><%=orderVO.getOrdstate()%></td>
		<td><%=orderVO.getMemno()%></td>
		<td><%=orderVO.getEmpno()%></td>
	</tr>
</table>
</c:if>
<%@ include file="/menu2.jsp" %> 

</body>
</html>