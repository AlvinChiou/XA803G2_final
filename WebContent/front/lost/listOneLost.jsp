<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.lost.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%  
	LostVO lostVO = (LostVO)session.getAttribute("lostVO");
// 	session.setAttribute("lostVO", lostVO);
%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>失蹤文章 - listOneLost.jsp</title>
<link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" />
<link href="<%= request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css" />
<link href="<%= request.getContextPath()%>/css/box_css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%= request.getContextPath()%>/js/jquery-1.4.2.js"></script>  
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/jquery.fancybox-1.3.4.css" media="screen" />

<script>
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

	function btnDivShow_onclick() {
		var btn = document.getElementById("btnDivShow");
		var div = document.getElementById("div1");
		var State = div1.style.display;
		var memVO = <%=(session.getAttribute("memVO")==null ? 0 : 1)%>;
		if(memVO == 0){
			alert("尚未登入");
			div.style.display='none';
			document.location.href="<%= request.getContextPath()%>/memLogin.jsp";
		}else{
			if(State=='none'){
				div.style.display='inline';
				btn.value='取消留言';
			}else{
				div.style.display='none';
				btn.value='新增留言';
			}
		}
	}
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
	<a id="titleimg"><img src="<%= request.getContextPath()%>/images/main_tit02.png"></a>
<br>
<p style="text-align:right; margin-right:50px; color:orange;">目前位置：<a href="<%=request.getContextPath()%>/iPETCares.jsp">首頁</a>>><a href="<%=request.getContextPath()%>/front/lost/listAllLost.jsp">所有失蹤協尋文章</a></p>	
<h3 style="margin-left:56px;">失蹤協尋</h3>
<table border='1' bordercolor='#CCCCFF' width='900' align='center'>
	<tr>
		<th nowrap>文章編號</th>
		<td>${lostVO.lostno}</td>
	</tr>
	<tr>	
		<th nowrap>文章標題</th>
		<td>${lostVO.losttitle}</td>
	</tr>
	<tr>	
		<th nowrap>照片</th>
		<td><c:if test="${lostVO.lostpic!=null}"><img src="<%= request.getContextPath()%>/lost/DBGifReader3?lostno=${lostVO.lostno}"></c:if>
		    <c:if test="${lostVO.lostpic==null}"><img src="images/nopic.jpg"></c:if></td>
	</tr>
	<tr>	
		<th nowrap>文章內容</th>
		<td>${lostVO.lostcontent}</td>
	</tr>
	<tr>	
		<th nowrap>找尋狀態</th>
		<td><c:if test="${lostVO.loststate=='0'}">失蹤</c:if>
			<c:if test="${lostVO.loststate=='1'}">拾獲</c:if>
			<c:if test="${lostVO.loststate=='2'}">已找到</c:if>
			<c:if test="${lostVO.loststate=='3'}">已領回</c:if>
		</td>
	</tr>
	<tr>	
		<th nowrap>登載時間</th>
		<td>${lostVO.losttime}</td>
	</tr>
	<tr>		
		<th nowrap>會員姓名</th>
		<td>
			<c:forEach var="memVO" items="${memSvc.all}">
				<c:if test="${lostVO.memno == memVO.memno}">${memVO.memname}</c:if>
			</c:forEach>
		</td>
	</tr>
<!-- 	<tr> -->
<!-- 		<th></th> -->
<!-- 		<td align='right'> -->
<%-- 			<c:if test="${memVO != null}"> --%>
<%-- 			<form method="post" action="<%=request.getContextPath()%>/lost/lost.do"> --%>
<!-- 			<input type="submit" value="修改"> -->
<%-- 			<input type="hidden" name="lostno" value="${lostVO.lostno}"> --%>
<%-- 			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<!-- 			<input type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 			</form> -->
<%-- 			</c:if> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
	
</table> 
	<div style="margin-left:60px;">
		<h3>留言內容</h3>
<%-- 	<c:if test="${memVO != null}"> --%>
			<input type="button" id="btnDivShow" onclick="btnDivShow_onclick()" value="新增留言" />
<%-- 	</c:if> --%>
	</div>
	<br>
	<div id="div1" style="display:none">
	<%@ include file="/front/gb/addGb.jsp" %>   
	</div>
	
	<%@ include file="/front/gb/listGb_Bylostno.jsp" %>  
	<div id="main_bg02"></div>
	
	<%@ include file="/footer.jsp" %>
	</div>
	
</body>
</html>