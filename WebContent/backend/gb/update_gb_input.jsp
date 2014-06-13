<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.gb.model.*"%>
<%@ page import="com.mem.model.*" %>
<%@ page import="com.lost.model.*"%>
<%--  MemVO memVO3 = (MemVO)session.getAttribute("memVO");--%>
<%  GbVO gbVO = (GbVO) request.getAttribute("gbVO");%>
<%  LostVO lostVO = (LostVO)request.getAttribute("lostVO");%>
<%--  pageContext.setAttribute("gbVO2", gbVO2); --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>   
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		

<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>iPET Cares</title>
<%-- <link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" /> --%>
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
	<%@ include file="/menu1.jsp" %> 

<!--   	<div id="wrapper"> -->
<!-- 		<div id="main_bg"> -->
<!--     		<div id="main_bg01"> -->
<!--         	<div id="apDiv13"></div> -->
<%-- 			<c:if test="${memVO == null}"><%@ include file="/header.jsp" %></c:if> --%>
<%--         	<c:if test="${memVO != null}"><%@ include file="/loginHeader.jsp" %></c:if> --%>
<%-- 			<%@ include file="/ad.jsp" %>    --%>
    	
<!--   		</div> -->
<!-- 		</div> -->
		
<form method="post" action="gb.do" name="form1">
<table border="0" align='center' bordercolor='#CCCCFF' >
	
	<tr>
		<th>留言編號:</th>
		<td><input type="hidden" name="gbno" size="15" value="<%= (gbVO==null)? "" : gbVO.getGbno() %>"/><%= gbVO.getGbno()%></td>
	</tr>
	<tr>
		<td>留言標題:</td>
		<td><input type="text" name="gbtitle" size="60" value="<%= (gbVO==null)? "" : gbVO.getGbtitle()%>" /></td>
	</tr>
	<tr>
		<td>留言內容:</td>
		<td><textarea name="gbcontent" rows="5" cols="70" ><%= (gbVO==null)? "" : gbVO.getGbcontent()%></textarea></td>
	</tr>
	<tr>
		<%java.sql.Timestamp date_SQL = new java.sql.Timestamp(System.currentTimeMillis());%>
		<td>留言時間:</td>
		<td>
		    <input type="hidden" id="datepicker3" name="gbtime" value="<%= date_SQL%>" />
		    <%= date_SQL%>
		</td>
	</tr>
	<tr>
		<td>失蹤文章編號:</td>
		<td><input type="hidden" name="lostno" size="10" value="<%= (gbVO==null)? "" : gbVO.getLostno()%>"/><%= gbVO.getLostno()%></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="hidden" name="memno" size="10" value="<%= (gbVO==null)? "" : gbVO.getMemno()%>"/><%= gbVO.getMemno()%></td>
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
<input type="hidden" name="gbno" value="<%=gbVO.getGbno()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
<%-- <input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%> ">  <!--只用於:listAllLost.jsp--> --%>
</form>
<!-- 	<div id="main_bg02"></div> -->
<%-- 	<%@ include file="/footer.jsp" %> --%>
<!-- 	</div> -->

	<%@ include file="/menu2.jsp" %> 

</body>
</html>