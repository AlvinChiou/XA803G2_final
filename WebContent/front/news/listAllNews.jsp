<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>
<%@ page import="com.mem.model.*" %>
<%--  MemVO memVO3 = (MemVO)session.getAttribute("memVO");--%>

<%
    NewsService newsSvc = new NewsService(); 
    List<NewsVO> list = newsSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>�Ҧ�������� - listAllNews.jsp</title>
<link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" />
<title>iPET Cares</title>
<style type="text/css">
</style>
<link href="<%= request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css" />
<link href="<%= request.getContextPath()%>/css/box_css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%= request.getContextPath()%>/js/jquery-1.4.2.js"></script>  
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/jquery.fancybox-1.3.4.css" media="screen" />
	
<script type="text/javascript">
	$(document).ready(function() {
		/*
		*   Examples - images
		*/

		$("a#example1").fancybox();
		$("#various3").fancybox({
			'width'				: '75%',
			'height'			: '75%',
			'autoScale'			: false,
			'transitionIn'		: 'none',
			'transitionOut'		: 'none',
			'type'				: 'iframe'
		});
	});
</script>

</head>

<body bgcolor='white'>

  	<div id="wrapper">
		<div id="main_bg">
    		<div id="main_bg01">
        	<div id="apDiv13"></div>
			<c:if test="${memVO == null}"><%@ include file="/header.jsp" %></c:if>
        	<c:if test="${memVO != null}"><%@ include file="/loginHeader.jsp" %></c:if>
			<%@ include file="/ad.jsp" %>   
    	
  		</div>
		</div>
<a id="titleimg"><img src="<%= request.getContextPath()%>/images/main_tit.png"></a>
<p style="text-align:right; margin-right:70px; color:orange;">�ثe��m�G<a href="<%=request.getContextPath()%>/iPETCares.jsp">����</a>>><a href="<%=request.getContextPath()%>/front/news/listAllNews.jsp">�Ҧ�����</a></p>
<table border='1' bordercolor='#CCCCFF' width='900' align='center'>
	<tr>
		<th nowrap>�����s��</th>
		<th nowrap>�����D�D</th>
		<th nowrap>�������O</th>
<!-- 		<th nowrap>�������e</th> -->
		<th nowrap>�Ӥ�</th>
		<th nowrap>�o���ɶ�</th>
<!-- 		<th nowrap>���u�s��</th> -->
<!-- 		<th>�ק�</th> -->
<!-- 		<th>�R��</th> -->
	</tr>
	
	<%@ include file="page1.file" %> 
	<c:forEach var="newsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(newsVO.newsno==param.newsno) ? 'bgcolor=#CCCCFF':''}>
			<td>${newsVO.newsno}</td>
			<td><a href="<%= request.getContextPath()%>/front/news/news.do?action=getOne_For_Display&newsno=${newsVO.newsno}">${newsVO.newstitle}</a></td>
			<td>
				<c:if test="${newsVO.newstype==1}">������</c:if>
				<c:if test="${newsVO.newstype==2}">���i��</c:if>
				<c:if test="${newsVO.newstype==3}">�ӫ~��</c:if>
			</td>
<%-- 			<td>${newsVO.newscontent}</td> --%>
			<td><img src="<%= request.getContextPath()%>/news/DBGifReader?newsno=${newsVO.newsno}" ></td>
			<td>${newsVO.newspotime}</td>
<%-- 			<td>${newsVO.empno}</td> --%>
			
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/news/news.do"> --%>
<!-- 			     <input type="submit" value="�ק�"> -->
<%-- 			     <input type="hidden" name="newsno" value="${newsVO.newsno}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
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
	<br>
	<br>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>

</body>
</html>
