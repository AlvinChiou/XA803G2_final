<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.news.model.*"%>
<%@ page import="java.util.*"%>

<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
	pageContext.setAttribute("newsVO", newsVO);
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4008);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>
<html>
<head>
<title>������� - listOneNews.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		
</head>
<body bgcolor='white'>
	<%@ include file="/menu1.jsp" %> 

<c:if test="<%=listPower.contains(powVO)%>"> 

<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>������� </h3>
		<a href="<%=request.getContextPath()%>/backend/news/select_page.jsp"><img src="<%=request.getContextPath()%>/backend/news/images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='800'>

	<tr>
		<th nowrap>�����s��</th><td><%=newsVO.getNewsno()%></td>
	</tr>
	<tr>
		<th nowrap>�����D�D</th><td><%=newsVO.getNewstitle()%></td>
	</tr>
	<tr>
		<th nowrap>�������O</th>
		<td>
			<c:if test="${newsVO.newstype==1}">������</c:if>
			<c:if test="${newsVO.newstype==2}">���i��</c:if>
			<c:if test="${newsVO.newstype==3}">�ӫ~��</c:if>
		</td>
	</tr>
	<tr>
		<th nowrap>�������e</th><td><%=newsVO.getNewscontent()%></td>
	</tr>
	<tr>
		<th nowrap>�Ӥ�</th><td><img src="DBGifReader?newsno=${newsVO.newsno}"></td>
	</tr>
	<tr>
		<th nowrap>�o���ɶ�</th><td><%=newsVO.getNewspotime()%></td>
	</tr>
	<tr>
		<th nowrap>���u�s��</th><td><%=newsVO.getEmpno()%></td>
	</tr>
	
</table>
</c:if>
	<%@ include file="/menu2.jsp" %> 

</body>
</html>
