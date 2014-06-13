<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pet.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%
	PetVO petVO = (PetVO) request.getAttribute("petVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>前端-寵物資料修改 - update_pet_input.jsp</title>
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

<FORM METHOD="post" ACTION="pet.do" name="form1" enctype="multipart/form-data">
<table border="0" align='center'>
	<tr>
		<th>寵物資料修改:</th>
	</tr>
	<tr>
		<td>寵物編號:<font color=red><b>*</b></font></td>
		<td><input type="hidden" name="petNo" value="<%=petVO.getPetNo()%>"><%=petVO.getPetNo()%></td>
	</tr>
	<tr>
		<td>寵物名字:</td>
		<td><input type="TEXT" name="petName" size="45" value="<%=petVO.getPetName()%>" /></td>
	</tr>
	<tr>
		<td>寵物性別:</td>
		<td><input type="hidden" name="petSex" size="45" value="<%=petVO.getPetSex()%>" />
			<c:if test="${petVO.petSex == 'boy' }">男生</c:if>
			<c:if test="${petVO.petSex == 'girl' }">女生></c:if>
		</td>
	</tr>
	<tr>
		<td>寵物類別:</td>
		<td><input type="hidden" name="petType" size="45" value="<%=petVO.getPetType()%>" />
			<c:if test="${petVO.petType == 'dog' }">狗</c:if>
			<c:if test="${petVO.petType == 'cat' }">貓</c:if>
		</td>	
	</tr>
	<tr>
		<td>寵物照片:</td>
		<td><img src="DBGifReader3?petNo=${petVO.petNo}"></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="file" name="petPic" size="45"	value="<%=petVO.getPetPic()%>" /></td>
	</tr>
	<tr>
		<td>寵物年齡:</td>
		<td><input type="TEXT" name="petAge" size="45" value="<%=petVO.getPetAge()%>" /></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="memNo" size="45" value="<%=petVO.getMemNo()%>" /></td>
	</tr>
	<tr>
		<td></td>
		<td align='right'><input type="submit" value="送出修改"></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="petNo" value="<%=petVO.getPetNo()%>">
<input type="hidden" name="requestURL" value="<%= request.getParameter("requestURL") %>">
</FORM>

	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
	
<!-- <br>送出修改的來源網頁路徑:<br><b> -->
<%--    <font color=blue>request.getParameter("requestURL"):</font> <%= request.getParameter("requestURL")%><br> --%>
</body>
</html>
