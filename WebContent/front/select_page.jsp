<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="lostSvc" scope="page" class="com.lost.model.LostService" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>iPET Mem: home</title>
<link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" />
<title>iPET Cares</title>
<style type="text/css">
.stage{
	width:150px;
}
</style>
<link href="<%= request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css" />
<link href="<%= request.getContextPath()%>/css/box_css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%= request.getContextPath()%>/js/jquery-1.4.2.js"></script>  
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/jquery.fancybox-1.3.4.css" media="screen" />
<link href="<%= request.getContextPath()%>/css/ResponsiveList.css" rel='stylesheet' type='text/css' />
<script type="text/javascript" src="<%= request.getContextPath()%>/js/ResponsiveList.js"></script>	
	
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
<body bgcolor='#EFF6FF'>

<div id="wrapper">
  		<div id="main_bg">
    	<div id="main_bg01">
        	<div id="apDiv13"></div>
			<c:if test="${memVO == null}"><%@ include file="/header.jsp" %></c:if>
        	<c:if test="${memVO != null}"><%@ include file="/loginHeader.jsp" %></c:if>
			<%@ include file="/ad.jsp" %>   
    	
  		</div>
		</div>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

	<div id="main_seclet" style = "postion:relative; z-index:0;">
    <div class="responsive" style = "postion:relative; z-index:0;">
<ul class="content">

	<!-- 1 -->
	<li>
		<div class="card-front">
			<h2><b>我的基本資料</b></h2>
			
		</div>
		<div class="card-back">
      		<a href="<%= request.getContextPath()%>/front/mem/mem.do?action=getOne_For_Display&memno=${memVO.memno}"><h2><b>Click here</b></h2></a>
		</div>

		<!-- Content -->
		<div class="all-content">
			<h1></h1>
		</div>
	</li>

	<li>
		<div class="card-front">
			<h2><b>我的訂單記錄</b></h2>
			
		</div>
		<div class="card-back">
			<a href="<%= request.getContextPath()%>/backend/ORDER/order.do?action=ORDERHISTORY"><h2><b>Click here</b></h2></a>
		</div>

		<!-- Content -->
		<div class="all-content">
			<h1></h1>
		</div>
	</li>

	<li>
		<div class="card-front">
			<h2><b>我的失蹤協尋</b></h2>
			<p></p>
		</div>
		<div class="card-back">
			<a href="<%= request.getContextPath()%>/front/lost/lost.do?action=listLost_ByMemno&memno=${memVO.memno}"><h2><b>Click here</b></h2></a>
		</div>

		<!-- Content -->
		<div class="all-content">
			<h1></h1>
		</div>
	</li>


	<!-- 2 -->
	<li>
		<div class="card-front">
			<h2><b>我的寵物資料</b></h2>
			<p></p>
		</div>
		<div class="card-back">
			<a href="<%= request.getContextPath()%>/front/pet/pet.do?action=getAll_For_Display_From_Member&memNo=${memVO.memno}&requestURL=<%= request.getRequestURI()%>"><h2><b>Click here</b></h2></a>
		</div>

		<!-- Content -->
		<div class="all-content">
			<h1></h1>
		</div>
	</li>

	<li>
		<div class="card-front">
			<h2><b></b></h2>
		</div>
		<div class="card-back">
			<img src="<%= request.getContextPath()%>/images/0519_logo.png" />
		</div>

		<!-- Content -->
		<div class="all-content">
			<h1></h1>
		</div>
	</li>
	
	<li>
		<div class="card-front">
			<h2><b>參加活動紀錄</b></h2>
			<p></p>
		</div>
		<div class="card-back">
			<a href="<%= request.getContextPath()%>/front/actRegister/actRegister.do?action=getAll_For_Display_By_MemNo&memNo=${memVO.memno}"><h2><b>Click here</b></h2></a>
		</div>

		<!-- Content -->
		<div class="all-content">
			<h1></h1>
		</div>
	</li>

	<!-- 3 -->
	<li>
		<div class="card-front">
			<h2><b>寵物掛號紀錄</b></h2>
			<p></p>
		</div>
		<div class="card-back">
			<a href="<%= request.getContextPath()%>/front/apt/select_page.jsp"><h2><b>Click here</b></h2></a>
		</div>
		
		<!-- Content -->
		<div class="all-content">
			<h1></h1>
		</div>
	</li>

	<li>
		<div class="card-front">
			<h2><b>主辦活動紀錄</b></h2>
			<p></p>
		</div>
		<div class="card-back">
			<a href="<%= request.getContextPath()%>/front/act/act.do?action=getAll_Acts_By_MemNo&memNo=${memVO.memno}"><h2><b>Click here</b></h2></a>
		</div>

		<!-- Content -->
		<div class="all-content">
			<h1></h1>
		</div>
	</li>
    
    <li>
		<div class="card-front">
			<h2><b></b></h2>
			<p></p>
		</div>
		<div class="card-back">
			<a href="#"><h2><b></b></h2></a>
		</div>

		<!-- Content -->
		<div class="all-content">
			<h1></h1>
		</div>
	</li>
	
</ul>
</div>  
</div>

<table border='0' bordercolor='#CCCCFF' width='800' align='center'>
<!-- 	<tr> -->
<!-- 		<td class="stage"> -->
<%-- 			<a href="<%= request.getContextPath()%>/front/mem/mem.do?action=getOne_For_Display&memno=${memVO.memno}"><b>我的基本資料</b></a> --%>
<!-- 		</td> -->
<!-- 		<td class="stage"> -->
<%-- 			<a href="<%= request.getContextPath()%>/backend/ORDER/order.do?action=ORDERHISTORY"><b>我的訂單紀錄</b></a> --%>
<!-- 		</td> -->
<!-- 		<td class="stage"> -->
<%-- 			<a href="<%= request.getContextPath()%>/front/act/act.do?action=getAll_Acts_By_MemNo&memNo=${memVO.memno}"><b>主辦活動紀錄</b></a> --%>
<!-- 		</td> -->
<!-- 		<td class="stage"> -->
<%-- 			<a href="<%= request.getContextPath()%>/front/lost/lost.do?action=listLost_ByMemno&memno=${memVO.memno}"><b>我的失蹤協尋文章</b></a> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td> -->
<%-- 			<a href="<%= request.getContextPath()%>/front/mem/mem.do?action=getOne_For_Update&memno=${memVO.memno}"><b>修改基本資料</b></a> --%>
<!-- 		</td> -->
<!-- 		<td> -->
<%-- 			<a href="<%= request.getContextPath()%>/front/pet/pet.do?action=getAll_For_Display_From_Member&memNo=${memVO.memno}&requestURL=<%= request.getRequestURI()%>"><b>我的寵物資料</b></a> --%>
<!-- 		</td> -->
<!-- 		<td> -->
<%-- 			<a href="<%= request.getContextPath()%>/front/apt/select_page.jsp"><b>寵物掛號紀錄</b></a> --%>
<!-- 		</td> -->
<!-- 		<td> -->
<%-- 			<a href="<%= request.getContextPath()%>/front/actRegister/actRegister.do?action=getAll_For_Display_By_MemNo&memNo=${memVO.memno}"><b>參加活動紀錄</b></a> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td> -->
<%-- 			<a href="<%= request.getContextPath()%>/front/pet/pet.do?action=getAll_For_Display_From_Member&memNo=${memVO.memno}&requestURL=<%= request.getRequestURI()%>"><b>我的寵物資料</b></a> --%>
<!-- 		</td> -->
<!-- 		<td> -->
<%-- 			<a href="<%= request.getContextPath()%>/front/apt/select_page.jsp"><b>寵物掛號紀錄</b></a> --%>
<!-- 		</td> -->
<!-- 		<td> -->
<%-- 			<a href="<%= request.getContextPath()%>/front/actRegister/actRegister.do?action=getAll_For_Display_By_MemNo_For_Delete&memNo=${memVO.memno}"><b>取消已報名活動</b></a> --%>
<!-- 		</td> -->
<!-- 		<td> -->
<!-- 			<a href="#"><b></b></a> -->
<!-- 		</td> -->
<!-- 	</tr> -->
</table>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
</body>

</html>