<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.lost.model.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />


<%
	LostService lostSvc = new LostService();
	List<LostVO> list = lostSvc.getAll();
	pageContext.setAttribute("list", list);
	
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4011);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>

<%-- 
	PowService powSvc = new PowService();
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4011);
	List<PowVO> listPowVO = (List<PowVO>)session.getAttribute("list");
--%>
<%-- <jsp:useBean id="lostSvc" scope="page" class="com.lost.model.LostService" /> --%>
<%--
    Object account = session.getAttribute("memid");                  // �q session�����X(key) account����
    if (account == null) {                                             // �p�� null, �N��user���n�J�L , �~���H�U�u�@
      session.setAttribute("location", request.getRequestURI());       //*�u�@1 : ���K�O�U�ثe��m , �H�K��login.html�n�J���\�� , ��������ɦܦ�����(���t�XLoginHandler.java)
      response.sendRedirect(request.getContextPath()+"/login.jsp");   //*�u�@2 : �и�user�h�n�J����(login.html) , �i��n�J
      return;
    }
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�Ҧ����ܤ峹 - listAllLost.jsp</title>
</head>
<body bgcolor='white'>
<%@ include file="/menu1.jsp" %> 

<c:if test="<%=listPower.contains(powVO)%>"> 

<b><font color="red"></font></b>
<table bgcolor='1' cellpadding='5' cellspacing='0' width='650'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ����ܤ峹</h3>
		<a href="<%= request.getContextPath() %>/backend/lost/select_page.jsp"><img src="<%= request.getContextPath() %>/backend/lost/images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='650'>
	<tr>
<!-- 		<th nowrap>�峹�s��</th> -->
		<th nowrap>�峹���D</th>
<!-- 		<th nowrap>�Ӥ�</th> -->
<!-- 		<th nowrap>�峹���e</th> -->
		<th nowrap>��M���A</th>
		<th nowrap>�|���s��</th>
		<th nowrap>�n���ɶ�</th>
		<th nowrap>�ק�</th>
		<th nowrap>�R��</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="lostVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(lostVO.lostno == param.lostno)? 'bgcolor=#CCCCFF' : ''}><!--�N�ק諸���@���[�J����Ӥw-->
<%-- 		<td>${lostVO.lostno}</td> --%>
		<td><a href="<%=request.getContextPath()%>/lost/lost.do?action=getOne_For_Display&lostno=${lostVO.lostno}">${lostVO.losttitle}</a></td>
<%-- 		<td><c:if test="${lostVO.lostpic!=null}"><img src="DBGifReader3?lostno=${lostVO.lostno}"></c:if> --%>
<%-- 			<c:if test="${lostVO.lostpic==null}"><img src="images/nopic.jpg"></c:if></td> --%>
<%-- 		<td>${lostVO.lostcontent}</td> --%>
		<td><c:if test="${lostVO.loststate=='0'}">����</c:if>
			<c:if test="${lostVO.loststate=='1'}">�B��</c:if>
			<c:if test="${lostVO.loststate=='2'}">�w���</c:if>
			<c:if test="${lostVO.loststate=='3'}">�w��^</c:if></td>
		<td>${lostVO.memno}</td>
		<td>${lostVO.losttime}</td>
<%-- 		<c:if test="<%=listPowVO.contains(powVO)%>"> --%>
		<td>
			<form method="post" action="<%=request.getContextPath()%>/lost/lost.do">
			<input type="submit" value="�ק�">
			<input type="hidden" name="lostno" value="${lostVO.lostno}">
			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
		    <input type="hidden" name="whichPage"	value="<%=whichPage%>">              <!--�e�X��e�O�ĴX����Controller-->
			<input type="hidden" name="action" value="getOne_For_Update"></form>
		</td>
		<td>
			<form method="post" action="<%=request.getContextPath()%>/lost/lost.do">
			<input type="submit" value="�R��">
			<input type="hidden" name="lostno" value="${lostVO.lostno}">
			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
		    <input type="hidden" name="whichPage"	value="<%=whichPage%>">              <!--�e�X��e�O�ĴX����Controller-->
			<input type="hidden" name="action" value="delete"></form>
		</td>
<%-- 		</c:if> --%>
	</tr>
	</c:forEach>
</table>
	<%@ include file="pages/page2.file" %>
</c:if>

<%@ include file="/menu2.jsp" %> 

</body>
</html>