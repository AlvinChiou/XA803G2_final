<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mem.model.*"%>
<%
	MemVO memVO = (MemVO)session.getAttribute("memVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�|����� - listOneMem.jsp</title>
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

<table border='1' bordercolor='#CCCCFF' width='500' align='center'>
	<tr>
		<th>�|���s��</th>
		<td>${memVO.memno}</td>
	</tr>
	<tr><th>�|���b��</th>
		<td>${memVO.memid}</td>
	</tr>
	<tr>
		<th>�|���K�X</th>
		<td>${memVO.mempassword}</td>
	</tr>
	<tr>
		<th>�|���m�W</th>
		<td>${memVO.memname}</td>
	</tr>
	<tr>
		<th>�����Ҧr��</th>
		<td>${memVO.memidno}</td>
	</tr>
	<tr>
		<th>�|���H�c</th>
		<td>${memVO.mememail}</td>
	</tr>
	<tr>
		<th>�X�ͦ~���</th>
		<td>${memVO.membirth}</td>
	</tr>
	<tr>
		<th>��}</th>
		<td>${memVO.memadd}</td>
	</tr>
	<tr><th>�ʧO</th>
		<td><c:if test="${memVO.memsex=='0'}">�k</c:if>
		<c:if test="${memVO.memsex=='1'}">�k</c:if></td>
	</tr>
	<tr>
		<th>�q��</th>
		<td>${memVO.memtel}</td>
	</tr>
	<tr>
		<th>���A</th>
		<td>
			<c:if test="${memVO.memstate=='0'}">���{��</c:if>
			<c:if test="${memVO.memstate=='1'}">�w�{��</c:if>
			<c:if test="${memVO.memstate=='2'}">�w���v</c:if>
		</td>
	</tr>
</table>
		<div align='right' style="margin-right:260px;">	
			<form method="post" action="<%=request.getContextPath()%>/front/mem/mem.do">
			<input type="submit" value="�ק�">
			<input type="hidden" name="memno" value="${memVO.memno}">
			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"> <!--�e�X�����������|��Controller-->
			<input type="hidden" name="action" value="getOne_For_Update"></form>
		</div>
		
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
</body>
</html>