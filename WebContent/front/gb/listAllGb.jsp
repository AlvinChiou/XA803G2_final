<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.gb.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%
	GbService gbSvc = new GbService();
	List<GbVO> list = gbSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
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

<table border='1' bordercolor='#CCCCFF' width='900' align='center'>
	<tr>
		<th>留言編號</th>
		<th>留言標題</th>
		<th>留言內容</th>
		<th>留言時間</th>
		<th>失蹤文章編號</th>
		<th>會員編號</th>
	</tr>
	<c:forEach var="gbVO" items="${list}">
		<tr align='center' valign='middle'>
			<td>${gbVO.gbno}</td>
			<td>${gbVO.gbtitle}</td>
			<td>${gbVO.gbcontent}</td>
			<td>${gbVO.gbtime}</td>
			<td>${gbVO.lostno}</td>
			<td>${gbVO.memno}</td>
			
		<td>
			<form method="post" action="<%= request.getContextPath()%>/gb/gb.do">
			<input type="submit" value="修改">
			<input type="hidden" name="gbno" value="${gbVO.gbno}">
			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
<%-- 		    <input type="hidden" name="whichPage"	value="<%=whichPage%>">              <!--送出當前是第幾頁給Controller--> --%>
			<input type="hidden" name="action" value="getOne_For_Update"></form>
		</td>
<!-- 		<td> -->
<%-- 			<form method="post" action="<%= request.getContextPath()%>/gb/gb.do"> --%>
<!-- 			<input type="submit" value="刪除"> -->
<%-- 			<input type="hidden" name="gbno" value="${gbVO.gbno}"> --%>
<%-- 			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 		    <input type="hidden" name="whichPage"	value="<%=whichPage%>">              <!--送出當前是第幾頁給Controller--> --%>
<!-- 			<input type="hidden" name="action" value="delete"></form> -->
<!-- 		</td> -->
		</tr>
	</c:forEach>
</table>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
</body>
</html>