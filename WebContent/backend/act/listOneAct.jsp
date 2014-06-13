<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.act.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
ActVO actVO = (ActVO) request.getAttribute("actVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4001);
List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");

%>
<html>
<head>
<title>活動資料 - listOneEmp.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<title>後端活動 -listOneAct.jsp</title>

</head>
<body bgcolor='white'>
			<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>"> 

<table border='1' cellpadding='5' cellspacing='0' width='900'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>後端活動 </h3>
		<a href="<%=request.getContextPath()%>/backend/act/select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='900'>
	<tr>
		<th>活動編號</th><td>${actVO.actNo}</td>
	</tr>
	<tr>
		<th>活動名稱</th><td>${actVO.actName}</td>
	</tr>
	<tr>
		<th>活動內容</th>	<td>${actVO.actContent}</td>
	</tr>
	<tr>
		<th>開始時間</th><td>${actVO.actStartTime}</td>
	</tr>
	<tr>
		<th>結束時間</th><td>${actVO.actEndTime}</td>
	</tr>
	<tr>
		<th>活動照片</th><td><img src="<%=request.getContextPath()%>/act/DBGifReader3?actNo=${actVO.actNo}"></td>
	</tr>
	<tr>
		<th>租借器材</th><td>${actVO.actEquipment}</td>
	</tr>
	<tr>
		<th>押金費用</th><td>${actVO.actDeposit}</td>
	</tr>
	<tr>
		<th>主辦方繳交費用</th><td>${actVO.actHostFee}</td>
	</tr>
	<tr>
		<th>活動報名費用</th><td>${actVO.actRegFee}</td>
	</tr>
	<tr>
		<th>目前活動狀態</th><td>${actVO.actStatus}</td>
	</tr>
	<tr>
		<th>主辦方會員編號</th><td>${actVO.memNo}</td>
	</tr>
	<tr>
		<th>審核職員編號</th><td>${actVO.empNo}</td>
	</tr>
	
</table>
</c:if>
			<%@ include file="/menu2.jsp" %> 

</body>
</html>
