<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.lost.model.*" %>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%--
  List<LostVO> listLost_ByMemno = (List<LostVO>) request.getAttribute("listLost_ByMemno");
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>失蹤協尋資料 - listLosts_ByMemno.jsp</title>
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

<table border='1' bordercolor='#CCCCFF' width='900' align='center'>
	<tr>
		<th nowrap>文章編號</th>
		<th nowrap>文章標題</th>
<!-- 		<th nowrap>照片</th> -->
<!-- 		<th nowrap>文章內容</th> -->
		<th nowrap>登載時間</th>
		<th nowrap>找尋狀態</th>
		<th nowrap>會員編號</th>
	</tr>
	<c:forEach var="lostVO" items="${listLost_ByMemno}" >
		<tr align='center' valign='middle' ${(lostVO.lostno == param.lostno)? 'bgcolor=#CCCCFF' : ''}><!--將修改的那一筆加入對比色而已-->
		<td>${lostVO.lostno}</td>
		<td><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=getOne_For_Display&lostno=${lostVO.lostno}">${lostVO.losttitle}</a></td>
<%-- 		<td><c:if test="${lostVO.lostpic!=null}"><img src="DBGifReader3?lostno=${lostVO.lostno}"></c:if> --%>
<%-- 			<c:if test="${lostVO.lostpic==null}"><img src="images/nopic.jpg"></c:if></td> --%>
<%-- 		<td>${lostVO.lostcontent}</td> --%>
		<td>${lostVO.losttime}</td>
		<td><c:if test="${lostVO.loststate=='0'}">失蹤</c:if>
			<c:if test="${lostVO.loststate=='1'}">拾獲</c:if>
			<c:if test="${lostVO.loststate=='2'}">已找到</c:if>
			<c:if test="${lostVO.loststate=='3'}">已領回</c:if></td>
		<td>${lostVO.memno}</td>
		
		<td>
			<form method="post" action="<%=request.getContextPath()%>/front/lost/lost.do">
			<input type="submit" value="修改">
			<input type="hidden" name="lostno" value="${lostVO.lostno}">
			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			<input type="hidden" name="action" value="getOne_For_Update"></form>
		</td>
<!-- 		<td> -->
<%-- 			<form method="post" action="<%=request.getContextPath()%>/lost/lost.do"> --%>
<!-- 			<input type="submit" value="刪除"> -->
<%-- 			<input type="hidden" name="lostno" value="${lostVO.lostno}"> --%>
<%-- 			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<!-- 			<input type="hidden" name="action" value="delete"></form> -->
<!-- 		</td> -->
		
	</tr>
	</c:forEach>
</table>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
</body>
</html>