<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>

<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />


<%   
    NewsService newsSvc = new NewsService(); 
    List<NewsVO> list = newsSvc.getAll();
    pageContext.setAttribute("list",list);
    
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4008);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>

<html>
<head>
<title>�Ҧ��������</title>

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
		<h3>�Ҧ�������� </h3>
		<a href="<%=request.getContextPath()%>/backend/news/select_page.jsp"><img src="<%=request.getContextPath()%>/backend/news/images/back1.gif" width="100" height="32" border="0">�^����</a>
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

<table border='1' bordercolor='#CCCCFF' width='900'>
	<tr>
		<th>�����s��</th>
		<th>�����D�D</th>
		<th>�������O</th>
<!-- 		<th>�������e</th> -->
		<th>�Ӥ�</th>
		<th>�o���ɶ�</th>
		<th>���u�s��</th>
		<th>�ק�</th>
<!-- 		<th>�R��</th> -->
	</tr>
	
	<%@ include file="page1.file" %> 
	<c:forEach var="newsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(newsVO.newsno==param.newsno) ? 'bgcolor=#CCCCFF':''}>
			<td>${newsVO.newsno}</td>
			<td><a href="<%= request.getContextPath()%>/news/news.do?action=getOne_For_Display&newsno=${newsVO.newsno}">${newsVO.newstitle}</a></td>
			<td>
				<c:if test="${newsVO.newstype==1}">������</c:if>
				<c:if test="${newsVO.newstype==2}">���i��</c:if>
				<c:if test="${newsVO.newstype==3}">�ӫ~��</c:if>
			</td>
<%-- 			<td>${newsVO.newscontent}</td> --%>
			<td><img src="<%= request.getContextPath()%>/news/DBGifReader?newsno=${newsVO.newsno}" ></td>
			<td>${newsVO.newspotime}</td>
			<td>${newsVO.empno}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/news/news.do">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="newsno" value="${newsVO.newsno}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/news/news.do"> --%>
<!-- 			    <input type="submit" value="�R��"> -->
<%-- 			    <input type="hidden" name="newsno" value="${newsVO.newsno}"> --%>
<%-- 			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"> --%>
<%-- 			    <input type="hidden" name="whichPage"	value="<%=whichPage%>"> --%>
<!-- 			    <input type="hidden" name="action"value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
	

</c:if>

		<%@ include file="/menu2.jsp" %> 
	
</body>
</html>
