<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");%>
<html>
<head>
<title>消息資料 - listOneNews.jsp</title>
<link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" />
<title>iPET Cares</title>
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
<br>
<p style="text-align:right; margin-right:70px; color:orange;">目前位置：<a href="<%=request.getContextPath()%>/iPETCares.jsp">首頁</a>>><a href="<%=request.getContextPath()%>/front/news/listAllNews.jsp">所有消息</a></p>
<br>
<table border='1' bordercolor='#CCCCFF' width='900' align='center'>

	<tr>
		<th nowrap>消息編號</th>
		<td><%=newsVO.getNewsno()%></td>
	</tr>
	<tr>	
		<th nowrap>消息主題</th>
		<td><%=newsVO.getNewstitle()%></td>
	</tr>
	<tr>
		<th nowrap>消息類別</th>
		<td><c:if test="${newsVO.newstype==1}">活動類</c:if>
			<c:if test="${newsVO.newstype==2}">公告類</c:if>
			<c:if test="${newsVO.newstype==3}">商品類</c:if></td>
	</tr>
	<tr>
		<th nowrap>消息內容</th>
		<td><%=newsVO.getNewscontent()%></td>
	</tr>
	<tr>
		<th nowrap>照片</th>
		<td><img src="<%= request.getContextPath()%>/news/DBGifReader?newsno=${newsVO.newsno}"></td>
	</tr>
	<tr>
		<th nowrap>發布時間</th>
		<td><%=newsVO.getNewspotime()%></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<th nowrap>員工編號</th> -->
<%-- 		<td><%=newsVO.getEmpno()%></td> --%>
<!-- 	</tr> -->
	
</table>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
</div>
</body>
</html>
