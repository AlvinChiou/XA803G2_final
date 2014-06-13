<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0022)http://www.vet.com.tw/ -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" />
<title>iPET Cares</title>
<link href="<%= request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css" />
<link href="<%= request.getContextPath()%>/css/box_css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%= request.getContextPath()%>/js/jquery-1.4.2.js"></script>  
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/jquery.fancybox-1.3.4.css" media="screen" />
<script>
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

<body onload="MM_preloadImages('<%= request.getContextPath()%>/images/tiPETCares.jsp','<%= request.getContextPath()%>/images/top_icon02.jpg','<%= request.getContextPath()%>/images/top_icon03.jpg','<%= request.getContextPath()%>/images/top_icon04.jpg','<%= request.getContextPath()%>/images/top_icon05.jpg','<%= request.getContextPath()%>/images/top_member_icon.jpg','<%= request.getContextPath()%>/images/right_search_icon.jpg','<%= request.getContextPath()%>/images/index_main_icon.jpg')">
<c:if test="${sessionScope.memVO.memstate != 2}">
	<div id="wrapper">
		<div id="main_bg">
    		<div id="main_bg01">
        		<div id="apDiv13"></div>
        		<c:if test="${sessionScope.memVO == null}"><%@ include file="/header.jsp" %></c:if>
        		<c:if test="${sessionScope.memVO != null}"><%@ include file="/loginHeader.jsp" %></c:if>
				<%@ include file="/ad.jsp" %>   
				<%@ include file="/main.jsp" %>   
 				<%@ include file="/footer.jsp" %>
  			</div>
		</div>
   			<div id="main_bg02"></div>
	</div>
</c:if>
</body>
</html>