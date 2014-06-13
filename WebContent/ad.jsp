<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title></title>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.slideshow.lite-0.5.3.js"></script>

<link href="<%= request.getContextPath()%>/css/slideshow.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	$(function (){ 
		$('#main_ad').slideshow({ pauseSeconds: 4, height: 228, width: 950, caption: false });
		$('#main_ad img').show();
	});
</script>
</head>
<body>

<div id="top_box">
	<div class="top_im_box">
    	<div class="top_im">
			<div id="main_ad" class="slideshowlite" style="width: 724px; height: 228px;">
			  <a   rel="1" style="display: block; z-index: 1;"><img src="<%= request.getContextPath()%>/images/top-image02.jpg" alt="" width="724" height="228" border="0" style=""></a>
			  <a   rel="2" style="display: none;  z-index: 1;"><img src="<%= request.getContextPath()%>/images/top-image04.jpg" alt="" width="724" height="228" border="0" style=""></a>
			  <a   rel="3" style="display: none;  z-index: 1;"><img src="<%= request.getContextPath()%>/images/web24h.jpg" alt="" width="724" height="228" border="0" style=""></a>
			  <a   rel="4" style="display: none;  z-index: 1;"><img src="<%= request.getContextPath()%>/images/top-image05.jpg" alt="" width="724" height="228" border="0" style=""></a>
			  <a   rel="5" style="display: none;  z-index: 1;"><img src="<%= request.getContextPath()%>/images/top-image03.jpg" alt="" width="724" height="228" border="0" style=""></a>
			  <a   rel="6" style="display: none;  z-index: 1;"><img src="<%= request.getContextPath()%>/images/top-image01.jpg" alt="" width="724" height="228" border="0" style=""></a>
			</div>
		</div>
    </div>
</div>  

</body>
</html>