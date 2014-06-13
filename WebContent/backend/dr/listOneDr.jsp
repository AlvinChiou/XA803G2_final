<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.doctor.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<%@ page import="com.doctor.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
DoctorVO doctorVO = (DoctorVO) request.getAttribute("doctorVO");

PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4004);
List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>
<html>
<head>
<title>醫師資料 - listOneDr.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		

</head>
<body bgcolor='white'>


	<%@ include file="/menu1.jsp" %> 
	<c:if test="<%=listPower.contains(powVO)%>"> 
<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料 - ListOneDr.jsp</h3>
		<a href="<%=request.getContextPath()%>/index.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='1000'>
	<tr>
		<th>醫師編號</th>
		<th>醫師姓名</th>
		<th>學經歷</th>
		<th>性別</th>
		<th>照片</th>
		<th>出生年月日</th>
		<th>住址</th>
		<th>電話</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=doctorVO.getDrNo()%></td>
		<td><%=doctorVO.getDrName()%></td>
		<td><%=doctorVO.getDrExp()%></td>
		<td><%=doctorVO.getDrSex()%></td>
		<td><img src="DBGifReader2?drNo=${doctorVO.drNo}"></td>
		<td><%=doctorVO.getDrBirth()%></td>
		<td><%=doctorVO.getDrAdd()%></td>
		<td><%=doctorVO.getDrTel()%></td>
		
	</tr>
</table>
 	</c:if>
    <%@ include file="/menu2.jsp" %> 
    
</body>
</html>
