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
<script src="/jQueryAssets/jquery-1.8.3.min.js" type="text/javascript"></script>

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

<body onload="MM_preloadImages('<%= request.getContextPath()%>/images/top_icon01.jpg','<%= request.getContextPath()%>/images/top_icon02.jpg','<%= request.getContextPath()%>/images/top_icon03.jpg','<%= request.getContextPath()%>/images/top_icon04.jpg','<%= request.getContextPath()%>/images/top_icon05.jpg','<%= request.getContextPath()%>/images/top_member_icon.jpg','<%= request.getContextPath()%>/images/right_search_icon.jpg','<%= request.getContextPath()%>/images/index_main_icon.jpg')">
	 
	<div id="wrapper">
		<div id="main_bg">
    		<div id="main_bg01">
        		<div id="apDiv13"></div>
        		<c:if test="${memVO == null}">
        			<c:redirect url="/memLogin2.jsp"></c:redirect>
        		</c:if>
				<%@ include file="/frontend/ORDERITEM/checkout_original.jsp" %>    				
  			</div>
   			<div id="main_bg02"></div>
		</div>
	</div>
</body>
</html>