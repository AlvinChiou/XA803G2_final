<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.apt.model.*"%>  
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
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

<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script> --%>
<%-- <script src="<%=request.getContextPath()%>/menu.js"></script>		 --%>
</head>

<body>

<div id="wrapper">
  		<div id="main_bg">
    	<div id="main_bg01">
        	<div id="apDiv13"></div>
			<c:if test="${memVO == null}"><%@ include file="/header.jsp" %></c:if>
        	<c:if test="${memVO != null}"><%@ include file="/loginHeader.jsp" %></c:if>
			<%@ include file="/ad.jsp" %>   
  		</div>
		</div>
	<div align='center'>
		<h1>您的掛號日期為: ${aptVO.aptDate} <br/><br/> </h1>
		<h1>您的號碼牌編號為: ${aptVO.aptNoSlip} <br/><br/> </h1>
		<h1>您的應到時間為: ${fn:substring(aptVO.aptRegTime, 0, 16)}</h1>
	</div>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
</body>

<%
	int whichPage = 1;
	final long ONE_DAY_IN_MILLISECONDS = 24 * 60 * 60 * 1000;
	if ( request.getAttribute("aptVO") != null ) {
		java.sql.Date thatDay =   ( ( AptVO )request.getAttribute("aptVO") ).getAptDate();
		java.sql.Date today = new java.sql.Date ( new java.util.Date().getTime() );
		today = java.sql.Date.valueOf( today.toString() );
		whichPage = (int)( ( thatDay.getTime() - today.getTime() ) / ONE_DAY_IN_MILLISECONDS + 1 );
// 		System.out.println( ( thatDay.getTime() - today.getTime() ) / ONE_DAY_IN_MILLISECONDS + 2 );
	}
%>

<!-- <form action = "showApt.jsp"> -->
<%-- 	<input type  = "hidden"  name = "whichPage" value = "<%=whichPage%>" /> --%>
<!-- 	<input type = "submit" value = "跳轉到管理所有掛號頁面"> -->
<!-- </form> -->

</html>