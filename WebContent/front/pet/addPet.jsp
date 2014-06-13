<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pet.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%  PetVO petVO = (PetVO) request.getAttribute("petVO");%>

<html>
<head>
<title>寵物資料新增 - addPet.jsp</title>
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
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<div id="wrapper">
		<div id="main_bg">
    		<div id="main_bg01">
        	<div id="apDiv13"></div>
			<c:if test="${memVO == null}"><%@ include file="/header.jsp" %></c:if>
        	<c:if test="${memVO != null}"><%@ include file="/loginHeader.jsp" %></c:if>
			<%@ include file="/ad.jsp" %>   
    	<div>&nbsp;</div>
  		</div>
		</div>
		<div>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
<!-- 	<font color='red'>請修正以下錯誤: -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li>${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<!-- 	</font> -->
</c:if>

<FORM METHOD="post" ACTION="pet.do" name="form1" enctype="multipart/form-data">
<table border="0" align='center'>
	<tr>
		<td><h3>寵物資料:</h3></td>
		<td></td>
	</tr>
	<tr>
		<th>寵物名字:</th>
		<td><input type="TEXT" name="petName" size="10" value="<%= (petVO==null)? "" : petVO.getPetName()%>" /><font color='red'>${errorMsgs.petName}</td>
	</tr>
	<tr>
		<th>寵物性別:</th>
		<td><input type="radio" name="petSex" value="boy" checked/>男生
			<input type="radio" name="petSex" value="girl" />女生
		</td>
	</tr>
	<tr>
		<th>寵物類別:</th>
		<td><input type="radio" name="petType" value="dog" checked/>狗狗
			<input type="radio" name="petType" value="cat" />貓咪
		</td>
	</tr>
	<tr>
		<th>寵物照片:</th>
		<td><input type="file" name="petPic" size="0" value="<%= (petVO==null)? "" : petVO.getPetPic()%>" /><font color='red'>${errorMsgs.petPic}</td>
	</tr>
	<tr>
		<th>寵物年齡:</th>
		<td><input type="TEXT" name="petAge" size="5" value="<%= (petVO==null)? "" : petVO.getPetAge()%>" /><font color='red'>${errorMsgs.petAge}</td>
	</tr>
	<tr>
		<th>會員姓名:</th>
		<td><input type="hidden" name="memNo" size="10"	value="${memVO.memno}" />${memVO.memname}</td>
	</tr>
	<tr>
		<td></td>
		<td align='right'><input type="submit" value="送出新增"></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="insert">
<br>

</FORM>

	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
</body>

</html>
