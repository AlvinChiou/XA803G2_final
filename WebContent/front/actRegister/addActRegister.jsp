<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actregister.model.*"%>
<%@ page import="com.act.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>

<%
//ActRegisterVO actRegVO = (ActRegisterVO) request.getAttribute("actRegVO");
ActVO actVO = (ActVO) request.getAttribute("actVO1");
%>

<html>
<head>
<title>前端-活動報名  addActRegister.jsp</title>
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

<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>

<script type="text/javascript">
	function sendForm(actionVal){
		alert("報名成功!");
		 
		document.getElementById("action").value=actionVal;
		document.getElementById("form1").submit();
	}
	
</script>
</head>

<div id="popupcalendar" class="text"></div>

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

<h3 align='center'>活動報名資料:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/actRegister/actRegister.do" id="form1" name="form1" >
<table border="0" align='center'>

	<tr>
		<td>活動編號:</td>
		<td><input type="hidden" name="actNo" size="45" value="<%= actVO.getActNo()%>" />
			<%= actVO.getActNo()%>
		</td>
	</tr>
	<tr>
		<td>報名活動名稱:</td>
		<td><input type="hidden" name="actRegName" size="45" value="<%= actVO.getActName()%>" />
			<%= actVO.getActName()%>
		</td>
	</tr>
	<tr>
		<td>活動日期:</td>
		<td>
		    <input type="hidden" size="9" name="actRegDate" value="<%= actVO.getActStartTime().toString().substring(0,10)%>" >
		    <%= actVO.getActStartTime().toString().substring(0,10)%>
		</td>
	</tr>
	<tr>
		<td>開始時間:</td>
		<td>
		    <input type="hidden" size="9" name="actRegTime" value="<%= actVO.getActStartTime().toString().substring(11,19)%>" >
		    <%= actVO.getActStartTime().toString().substring(11,19)%>
		</td>
	</tr>
  	<tr>
		<input type="hidden" name="actRegPayState" size="2" value="N" />
	</tr>
	<tr>
		<td>報名會員帳號:</td>
		<td><input type="hidden" name="memNo" size="45" value="${memVO.memno}" />${memVO.memname}</td>
	</tr>
	<tr>
		<td></td>
		<td align='right'>
		<input type="hidden"  id="action" name="action" value="">
		<input type="button"  value="確定報名" onclick="sendForm('insert')"></td>
	</tr>

</table>
</FORM>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
<br>
<!-- <input type="hidden" name="memNo" value="7005"> -->
<!-- <input type="hidden" name="action" value="insert"> -->
<br>


</FORM>
</body>

</html>
