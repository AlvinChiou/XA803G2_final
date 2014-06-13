<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actregister.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<jsp:useBean id="actRegSvc" scope="page" class="com.actregister.model.ActRegisterService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<html>
<head>
<title>前端-會員發起活動的報名狀況 - listAllActRegistersByMemNo.jsp</title>
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
<a id="titleimg"><img src="<%= request.getContextPath()%>/images/main_tit03.png"></a>
<br>
<p style="text-align:right; margin-right:50px; color:orange;">目前位置：<a href="<%=request.getContextPath()%>/iPETCares.jsp">首頁</a>>><a href="<%=request.getContextPath()%>/front/act/listAllAct.jsp">所有活動資訊</a>
 >><a href="<%=request.getContextPath()%>/front/actRegister/listAllActRegisterBymemNo.jsp">報名狀況</a></p>

<table border='1' bordercolor='#CCCCFF' width='900' align='center'>
	<tr>
		<th>參加活動名稱</th>
		<th>參加活動時間</th>
		<th>參加會員姓名</th>
<!-- 		<th>付費狀態</th> -->
		
	</tr>
	 
	<c:forEach var="actRegVO" items="${allActRegsByMemNo}" >
		<tr align='center' valign='middle'>
			<td>${actRegVO.actRegName}</td>
			<td>${actRegVO.actRegDate.toString().substring(0,10)} ${actRegVO.actRegTime.toString().substring(11,19)}</td>
			<td>
				<c:forEach var="memVO" items="${memSvc.all}">
					<c:if test="${memVO.memno == actRegVO.memNo}">${memVO.memname}</c:if>
				</c:forEach>
			</td>
<!-- 			<td> -->
<%-- 				<c:if test="${actRegVO.actRegPayState == 'N'}">未付費</c:if> --%>
<%-- 				<c:if test="${actRegVO.actRegPayState == 'Y'}">已付費</c:if> --%>
			
<!-- 			</td> -->
			
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actRegister/act.do"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="actRegNo" value="${actRegVO.actRegNo}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			<td>
			  <c:if test="${actRegVO.actRegPayState =='N'}">
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/actRegister/actRegister.do">
			    <input type="submit" value="取消報名">
			    <input type="hidden" name="actRegNo" value="${actRegVO.actRegNo}">
			    <input type="hidden" name="action" value="delete"></FORM>
			  </c:if>
			  
			  <c:if test="${actRegVO.actRegPayState =='Y'}">
			  
			    <input type="submit" value="報名完成"  disabled="disabled">
			    
			  </c:if>
			</td>
		</tr>
	</c:forEach>
</table>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>

</body>
</html>
