<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	MemVO memVO = (MemVO) request.getAttribute("memVO");//MemServlet.java (Controller), �s�Jreq��memVO���� (�]�A�������X��memVO, �]�]�A��J��ƿ��~�ɪ�memVO����)
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4010);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�|����ƭק�</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
</head>
<body bgcolor='white'>
		<%@ include file="/menu1.jsp" %> 
		<c:if test="<%=listPower.contains(powVO)%>"> 

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�|����ƭק� </h3>
		<a href="<%= request.getContextPath()%>/backend/mem/select_page.jsp"><img src="<%= request.getContextPath()%>/backend/mem/images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<h3>��ƭק�:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<form method="post" action="mem.do" name="form1">
<table border="0">
	<tr>
		<td>�|���s��:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMemno()%></td>
	</tr>
	<tr>
		<td>�|���b��:</td>
		<td><input type="hidden" name="memid" size="25" value="<%=memVO.getMemid()%>" />${memVO.memid}</td>
	</tr>
	<tr>
		<td>�|���K�X:</td>
		<td><input type="password" name="mempassword" size="26" value="<%=memVO.getMempassword()%>" /></td>
	</tr>
	<tr>
		<td>�|���m�W:</td>
		<td><input type="text" name="memname" size="25" value="<%=memVO.getMemname()%>" /></td>
	</tr>
	<tr>
		<td>�����Ҧr��:</td>
		<td><input type="hidden" name="memidno" size="25" value="<%=memVO.getMemidno()%>" />${memVO.memidno}</td>
	</tr>
	<tr>
		<td>e-mail:</td>
		<td><input type="text" name="mememail" size="25" value="<%=memVO.getMememail()%>" /></td>
	</tr>
	<tr>
		<td>�X�ͦ~���:</td>
		<td><input type="hidden" name="membirth" size="25" value="<%=memVO.getMembirth()%>" />${memVO.membirth}</td>
	</tr>	
	<tr>
		<td>�|���a�}:</td>
		<td><input type="text" name="memadd" size="45" value="<%=memVO.getMemadd()%>" /></td>
	</tr>	
	<tr>
		<td>�ʧO:</td>
		<td><input type="hidden" name="memsex" size="1" value="<%=(memVO==null)? "1" : memVO.getMemsex() %>" />
			<c:if test="${memVO.memsex=='0'}">�k</c:if>
			<c:if test="${memVO.memsex=='1'}">�k</c:if></td>
	</tr>	
	<tr>
		<td>�q��:</td>
		<td><input type="text" name="memtel" size="15" value="<%=memVO.getMemtel()%>" /></td>
	</tr>
	
	
	<tr>
		<td>���A:</td>
		<td><input type="radio" name="memstate" size="5" value="<%= (memVO!=null)? "1" : memVO.getMemstate()%>" checked/>�w�{��
			<input type="radio" name="memstate" size="5" value="<%= (memVO!=null)? "2" : memVO.getMemstate()%>" />�w���v</td>
	</tr>	
	 
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="memno" value="<%=memVO.getMemno()%>">
<input type="hidden" name="memstate" value="<%=memVO.getMemstate()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--�u�Ω�:istAllEmp.jsp-->
<input type="submit" value="�e�X�ק�">
</form>
</c:if>
		<%@ include file="/menu2.jsp" %> 

</body>
</html>