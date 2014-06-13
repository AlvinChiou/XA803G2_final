<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	MemVO memVO = (MemVO) request.getAttribute("memVO");

PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4010);
List<PowVO> list = (List<PowVO>)session.getAttribute("list");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>會員資料新增 - addMem.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<%-- <script src="<%=request.getContextPath()%>/menu.js"></script>	 --%>
</head>

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
			buttonImage : "images/calendar.gif",
			buttonImageOnly : true,
			yearRange:"-90:+0",
			changeMonth: true,
			changeYear: true
		});
	});
	
	
	$(function(){
		$('#menu').children('li').click(function(){
			$('#menu').find('ul').removeClass('active');
			$(this).find('ul').toggleClass('active');	
		});
	});
</script>

<body bgcolor='white'>
	<%@ include file="/menu1.jsp" %> 
	<c:if test="<%=list.contains(powVO)%>"> 
	

<table border='1' cellpadding='5' cellspacing='0' width='500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>會員資料新增 </h3>
		</td>
		<td>
<%-- 			<a href="<%= request.getContextPath()%>/backend/mem/select_page.jsp"><img src="<%= request.getContextPath()%>/front/mem/images/tomcat.gif" width="120" height="100" border="1">回首頁</a> --%>
		</td>
	</tr>
</table>

<h3>會員資料:</h3>
<%-- 錯誤表列  --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<form method="post" action="<%= request.getContextPath()%>/backend/mem/mem.do" name="form1">
<table border="0">
	<tr>
		<td>會員帳號:</td>
		<td><input type="text" name="memid" size="20" value="<%= (memVO==null)? "" : memVO.getMemid()%>" /></td>
	</tr>
	<tr>
		<td>會員密碼:</td>
		<td><input type="password" name="mempassword" size="21" value="<%= (memVO==null)? "" : memVO.getMempassword()%>" /></td>
	</tr>	
	<tr>
		<td>確認密碼:</td>
		<td><input type="password" name="checkpassword" size="21" value="" /></td>
	</tr>
	<tr>
		<td>會員姓名:</td>
		<td><input type="text" name="memname" size="20" value="<%= (memVO==null)? "" : memVO.getMemname()%>" /></td>
	</tr>	
	<tr>
		<td>身份證字號:</td>
		<td><input type="text" name="memidno" size="20" value="<%= (memVO==null)? "" : memVO.getMemidno()%>" /></td>
	</tr>	
	<tr>
		<td>e-mail:</td>
		<td><input type="text" name="mememail" size="30" value="<%= (memVO==null)? "" : memVO.getMememail()%>" /></td>
	</tr>	
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>出生年月日:</td>
		<td>
		    <input type="text" id="datepicker3" name="membirth" value="<%= (memVO==null)? date_SQL : memVO.getMembirth()%>" />
		</td>
	</tr>	
	<tr>
		<td>地址:</td>
		<td><input type="text" name="memadd" size="45" value="<%= (memVO==null)? "" : memVO.getMemadd()%>" /></td>
	</tr>	
	<tr>
		<td>性別:</td>
		<td><input type="radio" name="memsex" size="2" value="<%= (memVO==null)? "1" : memVO.getMemsex()%>" checked='' />男
		    <input type="radio" name="memsex" size="2" value="<%= (memVO==null)? "0" : memVO.getMemsex()%>" />女</td>
	</tr>	
	<tr>
		<td>電話號碼:</td>
		<td><input type="text" name="memtel" size="20" value="<%= (memVO==null)? "" : memVO.getMemtel()%>" /></td>
	</tr>
	<tr>
		<td><input type=hidden name="memstate" size="5" value="<%= (memVO==null)? "0" : memVO.getMemstate()%>" /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" name="送出新增">
</form>
</c:if>
    <%@ include file="/menu2.jsp" %> 

</body>
</html>