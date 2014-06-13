<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.gb.model.*"%>
<%@ page import="com.lost.model.*"%>
<%@ page import="com.mem.model.*" %>
<%--	MemVO memVO = (MemVO)session.getAttribute("memVO");--%>

<%	
	LostVO lostVO1 = (LostVO)session.getAttribute("lostVO");
	int lostno = lostVO1.getLostno();
	
	GbService gbSvc = new GbService();
	List<GbVO> list = gbSvc.listGb_Bylostno(lostno);
	request.setAttribute("list", list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>失蹤留言 - listGb_Bylostno.jsp</title>
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
</script> -->

</head>

<body bgcolor='white'>

<!-- 	<div id="wrapper"> -->
<!--   		<div id="main_bg"> -->
<!--     	<div id="main_bg01"> -->
<!--         	<div id="apDiv13"></div> -->
<%-- 			<c:if test="${memVO == null}"><%@ include file="/header.jsp" %></c:if> --%>
<%--         	<c:if test="${memVO != null}"><%@ include file="/loginHeader.jsp" %></c:if> --%>
<%-- 			<%@ include file="/ad.jsp" %>    --%>
    	
<!--   		</div> -->
<!-- 		</div> -->

<table border='1' bordercolor='#CCCCFF' width='900' align='center' >

	<%@ include file="pages/page1.file" %>
	<c:forEach var="gbVO" items="${list}" varStatus="s" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr bgcolor='#E6CAFF'>
		<th>留言編號</th><td>${gbVO.gbno}</td>
		<th>留言標題</th><td colspan='3'>${gbVO.gbtitle}</td>
	</tr>
	<tr bgcolor='#DDDDFF'>
		<th>留言內容</th><td colspan='4'>${gbVO.gbcontent}</td>
	</tr>
	<tr bgcolor='#FFE6D9'>
		<th width='10%'>會員姓名</th>
		<td width='10%'>
			<c:forEach var="memVO" items="${memSvc.all}">
			<c:if test="${lostVO.memno == memVO.memno}">
				${memVO.memname}
			</c:if>
			</c:forEach>
		</td>
		<th width='10%'>留言時間</th><td width='20%'>${gbVO.gbtime.toString().substring(0,19)}</td>
		
 		<c:if test="${gbVO.memno == memVO.memno}">
		   <script>
         		function presses${s.index}(){
					document.open("<%= request.getContextPath()%>/front/gb/gb.do?gbno=${gbVO.gbno}&action=getOne_For_Update", "" ,"height=300,width=850,left=65,top=157,resizable=yes,scrollbars=yes");
         		}
		   </script>
			<td width='10%' align='center'> 
				<a HREF="javascript:presses${s.index}()"><input type="button" value="修改" ></a>
<%-- 				<form method="post" action="<%= request.getContextPath()%>/gb/gb.do"> --%>
<!-- 				<input type="submit" value="修改"> -->
<%-- 				<input type="hidden" name="gbno" value="${gbVO.gbno}">  --%>
<%-- 				<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 		    	<input type="hidden" name="whichPage"	value="<%=whichPage%>">              <!--送出當前是第幾頁給Controller--> --%>
<!-- 				<input type="hidden" name="action" value="getOne_For_Update"></form> -->
			</td>
		</c:if>
	</tr>
	<tr><td height='50%' colspan='5' bgcolor='#5CADAD'></td></tr>
	</c:forEach>
        
<%-- 	<th>失蹤文章編號</th><td>${gbVO.lostno}</td>  --%>
		
</table>
	<%@ include file="pages/page2.file" %>
<!-- 		<td> -->
<%-- 			<form method="post" action="<%= request.getContextPath()%>/gb/gb.do"> --%>
<!-- 			<input type="submit" value="刪除"> -->
<%-- 			<input type="hidden" name="gbno" value="${gbVO.gbno}"> --%>
<%-- 			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 		    <input type="hidden" name="whichPage"	value="<%=whichPage%>">              <!--送出當前是第幾頁給Controller--> --%>
<!-- 			<input type="hidden" name="action" value="delete"></form> -->
<!-- 		</td> -->
<!-- 		</tr> -->
<!-- 	<div id="main_bg02"></div> -->
<%-- 	<%@ include file="/footer.jsp" %> --%>
<!-- 	</div> -->

</body>
</html>