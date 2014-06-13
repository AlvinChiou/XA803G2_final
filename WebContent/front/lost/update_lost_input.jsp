<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lost.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%
  LostVO lostVO = (LostVO) request.getAttribute("lostVO");//LostServlet.java (Controller), 存入req的lostVO物件 (包括幫忙取出的lostVO, 也包括輸入資料錯誤時的lostVO物件)
%>

<%-- <jsp:useBean id="LostVO" scope="session" class="com.lost.model.LostVO" /> --%>
<%-- <jsp:useBean id="LostVO" scope="request" class="com.lost.model.LostVO" /> --%>
<%-- <jsp:setProperty name="LostVO"  property="lostpic" value="${lostVO.lostpic}" /> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>失蹤協尋文章資料修改 - update_lost_input.jsp</title>
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
</head>
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

<form method="post" action="lost.do" name="form1" enctype="multipart/form-data">
<table border="0" align='center' bordercolor='#CCCCFF' >
	<tr>
		<th>文章編號:</th>
		<td><input type="hidden" name="lostno" size="15" />${lostVO.lostno}</td>
	</tr>
	<tr>
		<th>文章標題:</th>
		<td><input type="text" name="losttitle" size="72" value="<%= lostVO.getLosttitle() %>" ></td>
	</tr>
	<tr>
		<th>照片:</th>
		<td><img src="DBGifReader3?lostno=${lostVO.lostno}"></td>
	</tr>
	<tr>
		<th></th>
		<td><input type="file" name="lostpic" size="57" /></td>
	</tr>
	<tr>
		<th>文章內容:</th>
		<td><textarea name="lostcontent" rows="30" cols="55" ><c:out value="${lostVO.lostcontent}" /></textarea></td>
	</tr>
	<tr>
		<th>登載時間:</th>
		<td><input type="hidden" name="losttime" size="15" value="<%= lostVO.getLosttime() %>" >${lostVO.losttime}</td>
	</tr>
	<tr>
		<th>找尋狀態:</th>
		<td><select size="1" name="loststate">
			<c:if test="${lostVO.loststate == 0}">
				<option value="0">失蹤
				<option value="2">已找到
			</c:if>
			<c:if test="${lostVO.loststate == 1}">
				<option value="1">拾獲
				<option value="3">已領回
			</c:if>
		</select></td>
	</tr>
	<tr>
		<th>會員編號:</th>
		<td><input type="hidden" name="memno" size="10" value="<%= lostVO.getMemno() %>" />${lostVO.memno}</td>
	</tr>
	<tr>
		<th></th>
		<td align='right'>
			<input type="submit" value="送出修改">
		</td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="lostno" value="<%=lostVO.getLostno()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%> ">  <!--只用於:listAllLost.jsp-->
</form>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
</body>
</html>