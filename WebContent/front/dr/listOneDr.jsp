<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.doctor.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%  DoctorVO doctorVO = (DoctorVO) request.getAttribute("doctorVO");%>
<html>
<head>
<title>醫師資料 - listOneDr.jsp</title>
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
<a id="titleimg"><img src="<%= request.getContextPath()%>/images/main_tit01.png"></a>
<br>
<p style="text-align:right; margin-right:70px; color:orange;">目前位置：<a href="<%=request.getContextPath()%>/iPETCares.jsp">首頁</a>>><a href="<%=request.getContextPath()%>/front/dr/listAllDr.jsp">所有醫師資訊</a></p>

<table border='1' bordercolor='#CCCCFF' width='900' align='center'>
	<tr>
		<th>醫師編號</th>
		<td><%=doctorVO.getDrNo()%></td>
	</tr>
	<tr>	
		<th>醫師姓名</th>
		<td><%=doctorVO.getDrName()%></td>
	</tr>
	<tr>	
		<th>學經歷</th>
		<td><%=doctorVO.getDrExp()%></td>
	</tr>
	<tr>	
		<th>性別</th>
		<td><c:if test="${doctorVO.drSex == 'M'}">帥哥</c:if>
			<c:if test="${doctorVO.drSex == 'F'}">美女</c:if></td>
	</tr>
	<tr>
		<th>照片</th>
		<td><img src="<%= request.getContextPath()%>/dr/DBGifReader2?drNo=${doctorVO.drNo}"></td>
	</tr>
	<tr>	
		<th>出生年月日</th>
		<td><%=doctorVO.getDrBirth()%></td>
	</tr>
<!-- 	<tr>	 -->
<!-- 		<th>住址</th> -->
<%-- 		<td><%=doctorVO.getDrAdd()%></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<th>電話</th> -->
<%-- 		<td><%=doctorVO.getDrTel()%></td> --%>
<!-- 	</tr> -->
	
</table>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
</body>
</html>
