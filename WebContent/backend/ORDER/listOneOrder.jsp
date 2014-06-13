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
<title>單筆訂單資料</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
</head>
<body>

<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>"> 

<div bgcolor="yellow" align="center">單筆訂單資料</div>
<table border='0' bordercolor='#CCCCFF'>
		<tr>
		<th>訂單編號</th>
		<th>下單時間</th>
		<th>配送地址</th>
		<th>買方連絡電話</th>
		<th>出貨時間</th>
		<th>送達時間</th>
		<th>銷單時間</th>
		<th>訂單狀態</th>
		<th>會員編號</th>
		<th>承辦人員工編號</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=orderVO.getOrdno()%></td>
		<td><%=orderVO.getOrdtime()%></td>
		<td><%=orderVO.getOrdaddr()%></td>
		<td><%=orderVO.getOrdtel()%></td>
		<td><%=(orderVO.getOrdgotime()==null?"未出貨":orderVO.getOrdgotime())%></td>
		<td><%=(orderVO.getOrdarrtime()==null?"未送達":orderVO.getOrdarrtime())%></td>
		<td><%=(orderVO.getOrddeltime()==null?"未銷單":orderVO.getOrddeltime())%></td>
		<td><%=orderVO.getOrdstate()%></td>
		<td><%=orderVO.getMemno()%></td>
		<td><%=orderVO.getEmpno()%></td>
	</tr>
</table>
</c:if>
<%@ include file="/menu2.jsp" %> 

</body>
</html>