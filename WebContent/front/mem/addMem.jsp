<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<% MemVO memVO = (MemVO) request.getAttribute("memVO");%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>會員資料新增 - addMem.jsp</title>
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

<script>

function miracle() {
	document.getElementsByName("memid")[0].value = "xa803g2";
	document.getElementsByName("mempassword")[0].value = "xa803g2";
	document.getElementsByName("checkpassword")[0].value = "xa803g2";
	document.getElementsByName("memname")[0].value = "吳董";
	document.getElementsByName("memidno")[0].value = "P123911545";
	document.getElementsByName("mememail")[0].value = "luke02141@gmail.com";
	document.getElementsByName("membirth")[0].value = "1970-04-03";
	document.getElementsByName("memadd")[0].value = "中壢市中大路300-1號";
	document.getElementsByName("memtel")[0].value = "0919053489";
}
</script>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />

<script>
	$(function() {
		$("#datepicker3").datepicker({
			defaultDate:(new Date()),
			dateFormat:"yy-mm-dd",
			showOn : "button",
			buttonImage : "<%= request.getContextPath()%>/front/mem/images/calendar.gif",
			buttonImageOnly : true,
			yearRange:"-90:+0",
			changeMonth: true,
			changeYear: true
		});
	});
	
</script>

</head>

<body bgcolor='white'>

	<div id="wrapper">
  		<div id="main_bg">
    	<div id="main_bg01">
        	<div id="apDiv13"></div>
			<%@ include file="/header.jsp" %>
			<%@ include file="/ad.jsp" %>   
    	
  		</div>
		</div>

<%-- 錯誤表列  --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font color='red'>請修正以下錯誤: -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li>${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<!-- 	</font> -->
<%-- </c:if> --%>

<form method="post" action="<%= request.getContextPath()%>/front/mem/mem.do" name="form1">
<table border="0" align='center'>
	<tr>
		<th>會員資料:</th>
	</tr>
	<tr>
		<th>會員帳號:<font color=red><b>*</b></font></th>
		<td><input type="text" name="memid" size="20" value="<%= (memVO==null)? "" : memVO.getMemid()%>" /></td>
<%-- 		<td><font color=red>${errorMsgs.memid}</font></td> --%>
	</tr>
	<tr>
		<th>會員密碼:<font color=red><b>*</b></font></th>
		<td><input type="password" name="mempassword" size="21" value="<%= (memVO==null)? "" : memVO.getMempassword()%>" /></td>
		<td><font color=red>${errorMsgs.mempassword}</font></td>
	</tr>	
	<tr>
		<th>確認密碼:<font color=red><b>*</b></font></th>
		<td><input type="password" name="checkpassword" size="21" value="" /></td>
		<td><font color=red>${errorMsgs.checkpassword}</font></td>
	</tr>
	<tr>
		<th>會員姓名:<font color=red><b>*</b></font></th>
		<td><input type="text" name="memname" size="20" value="<%= (memVO==null)? "" : memVO.getMemname()%>" /></td>
		<td><font color=red>${errorMsgs.memname}</font></td>
	</tr>	
	<tr>
		<th>身份證字號:<font color=red><b>*</b></font></th>
		<td><input type="text" name="memidno" size="20" value="<%= (memVO==null)? "" : memVO.getMemidno()%>" /></td>
		<td><font color=red>${errorMsgs.memidno}</font></td>
	</tr>	
	<tr>
		<th>e-mail:<font color=red><b>*</b></font></th>
		<td><input type="text" name="mememail" size="30" value="<%= (memVO==null)? "" : memVO.getMememail()%>" /></td>
		<td><font color=red>${errorMsgs.mememail}</font></td>
	</tr>	
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<th>出生年月日:<font color=red><b>*</b></font></th>
		<td>
		    <input type="text" id="datepicker3" name="membirth" value="<%= (memVO==null)? date_SQL : memVO.getMembirth()%>" />
		</td>
	    <td><font color=red>${errorMsgs.membirth}</font></td>
	</tr>	
	<tr>
		<th>地址:<font color=red><b>*</b></font></th>
		<td><input type="text" id="twzipcode" name="memadd" size="45" value="<%= (memVO==null)? "" : memVO.getMemadd()%>" /></td>
		<td><font color=red>${errorMsgs.memadd}</font></td>
	</tr>	
	<tr>
		<th>性別:<font color=red><b>*</b></font></th>
		<td><input type="radio" name="memsex" size="2" value="<%= (memVO==null)? "1" : memVO.getMemsex()%>" checked='' />男
		    <input type="radio" name="memsex" size="2" value="<%= (memVO==null)? "0" : memVO.getMemsex()%>" />女</td>
		<td><font color=red>${errorMsgs.memsex}</font></td>
	</tr>	
	<tr>
		<th>電話號碼:<font color=red><b>*</b></font></th>
		<td><input type="text" name="memtel" size="20" value="<%= (memVO==null)? "" : memVO.getMemtel()%>" /></td>
		<td><font color=red>${errorMsgs.memtel}</font></td>
	</tr>
	<tr>
		<td><input type=hidden name="memstate" size="5" value="<%= (memVO==null)? "0" : memVO.getMemstate()%>" /></td>
	</tr>
</table>
	
		<div align='right' style="margin-right:310px;">
			<input type="submit" name="送出新增">
			<input type="hidden" name="action" value="insert">
			<input type = "button" id = "btn" value = "click me" onclick = "miracle()">
		</div>
<br>
</form>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>

</body>
</html>