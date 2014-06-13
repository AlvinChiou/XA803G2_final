<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%
	PetVO petVO = (PetVO) request.getAttribute("vo3");//EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>所有寵物資料 - listOnePet.jsp</title>
<link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" />
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

<table border='1' bordercolor='#CCCCFF' width='600' align='center'>

	<tr>
		<th>寵物編號</th>
		<td>${petVO.petNo}</td>
	</tr>
	<tr>	
		<th>寵物名字</th>
		<td>${petVO.petName}</td>
	</tr>
	<tr>	
		<th>寵物性別</th>
		<td>${petVO.petSex}</td>
	</tr>
	<tr>	
		<th>寵物類別</th>
		<td>${petVO.petType}</td>
	</tr>
	<tr>	
		<th>寵物照片</th>
		<td><img src="DBGifReader3?petNo=${petVO.petNo}"></td>
	</tr>
	<tr>	
		<th>寵物年齡</th>
		<td>${petVO.petAge}</td>
	</tr>
	<tr>	
		<th>會員編號</th>
		<td>${petVO.memNo}</td>
	</tr>
	
</table>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>

</body>
</html>
