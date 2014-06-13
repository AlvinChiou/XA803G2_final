<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.gb.model.*"%>
<%@ page import="com.lost.model.*"%>
<%	List<GbVO> listGb_Bymemno = (List<GbVO>)request.getAttribute("listGb_Bymemno");%>
  
<%--	int lostno = new Integer(request.getQueryString().substring(33));--%><%--.substring(33) --%>
<%--
	GbService gbSvc = new GbService();
	List<GbVO> list = gbSvc.listGb_Bymemno(memno);
	request.setAttribute("list", list);
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		

<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<%-- <link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" /> --%>
<!-- <title>iPET Cares</title> -->
<!-- <style type="text/css"> -->
<!-- </style> -->
<%-- <link href="<%= request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css" /> --%>
<%-- <link href="<%= request.getContextPath()%>/css/box_css.css" rel="stylesheet" type="text/css" /> --%>
<%-- <script language="JavaScript" src="<%= request.getContextPath()%>/js/jquery-1.4.2.js"></script>   --%>
<%-- <script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.mousewheel-3.0.4.pack.js"></script> --%>
<%-- <script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.fancybox-1.3.4.pack.js"></script> --%>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/jquery.fancybox-1.3.4.css" media="screen" /> --%>
	
<!-- 
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
 function btnDivShow_onclick2() {
	var btn = document.getElementById("btnDivShow2");
	var div=document.getElementById("div2");
	var State=div2.style.display;
	if(State=='none')
	{
		div.style.display='';
		btn.value='取消修改';
	}
	else
	{
		div.style.display='none';
		btn.value='修改';
	}
}
</script> -->
</head>

<body bgcolor='white'>
	<%@ include file="/menu1.jsp" %> 

<!-- 	<div id="wrapper"> -->
<!--   		<div id="main_bg"> -->
<!--     	<div id="main_bg01"> -->
<!--         	<div id="apDiv13"></div> -->
<%-- 			<c:if test="${memVO == null}"><%@ include file="/header.jsp" %></c:if> --%>
<%--         	<c:if test="${memVO != null}"><%@ include file="/loginHeader.jsp" %></c:if> --%>
<%-- 			<%@ include file="/ad.jsp" %>    --%>
    	
<!--   		</div> -->
<!-- 		</div> -->

<table border='1' bordercolor='#CCCCFF' width='900' align='center'>

	<tr>
		<th>留言編號</th>
		<th>留言標題</th>
		<th>留言內容</th>
		<th>會員編號</th>
		<th>留言時間</th>
	</tr>
	<c:forEach var="gbVO" items="${listGb_Bymemno}">
	<tr>
		<td>${gbVO.gbno}</td>
		<td>${gbVO.gbtitle}</td>
		<td>${gbVO.gbcontent}</td>
		<td>${gbVO.memno}</td>
		<td>${gbVO.gbtime}</td>
		
<%--  		<c:if test="${gbVO.memno == memVO.memno}"> --%>
<!-- 			<td> -->
<!-- 				<input type="button" id=btnDivShow2 name=btnDivShow2 onclick="return btnDivShow_onclick2()" value="修改" > -->
<%-- 				<form method="post" action="<%= request.getContextPath()%>/gb/gb.do"> --%>
<!-- 				<input type="submit" value="修改"> -->
<%-- 				<input type="hidden" name="gbno" value="${gbVO.gbno}">  --%>
<%-- 				<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 		    	<input type="hidden" name="whichPage"	value="<%=whichPage%>">              <!--送出當前是第幾頁給Controller--> --%>
<!-- 				<input type="hidden" name="action" value="getOne_For_Update"></form> -->
<!-- 			</td> -->
<%-- 		</c:if> --%>
		<td>
			<form method="post" action="<%= request.getContextPath()%>/backend/gb/gb.do">
			<input type="submit" value="刪除">
			<input type="hidden" name="gbno" value="${gbVO.gbno}">
			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
<%-- 		    <input type="hidden" name="whichPage"	value="<%=whichPage%>">              <!--送出當前是第幾頁給Controller--> --%>
			<input type="hidden" name="action" value="delete"></form>
		</td>
	</tr>
	
<%-- 	<th>失蹤文章編號</th><td>${gbVO.lostno}</td>  --%>
		
	</c:forEach>
</table>

<!-- 		</tr> -->
<!-- 	<div id="main_bg02"></div> -->
<%-- 	<%@ include file="/footer.jsp" %> --%>
<!-- 	</div> -->
	<%@ include file="/menu2.jsp" %> 

</body>
</html>