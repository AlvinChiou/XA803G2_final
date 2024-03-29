<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actregister.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ActRegisterService actRegSvc = new ActRegisterService();
    List<ActRegisterVO> list = actRegSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>前端-所有活動報名資料 - listAllActRegister.jsp</title>
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
 >><a href="<%=request.getContextPath()%>/front/actRegister/listAllActRegister.jsp">報名狀況</a></p>

<table border='1' bordercolor='#CCCCFF' width='950' align='center'>
	<tr>
		<th>參加會員編號</th>
		<th>參加活動名稱(活動編號)</th>
		<th>付費狀態</th>
		<th>報名狀態</th>
		
	</tr>
	 
	<c:forEach var="actRegVO" items="${list}" >
		<c:if test="${actRegVO.memNo == memVO.memno}">
		<tr align='center' valign='middle'>
			<td>${actRegVO.memNo}</td>
			<td>${actRegVO.actRegName}(${actRegVO.actNo})</td>
			<c:if test="${actRegVO.actRegPayState == 'Y'}">
				<td>已繳費</td>
			</c:if>
			<c:if test="${actRegVO.actRegPayState == 'N'}">
				<td>未繳費</td>
			</c:if>  
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actRegister/act.do"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="actRegNo" value="${actRegVO.actRegNo}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			<td>
			  <c:if test="${actRegVO.memNo == memVO.memno}">
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/actRegister/actRegister.do">
			    <input type="submit" value="取消報名">
			    <input type="hidden" name="actRegNo" value="${actRegVO.actRegNo}">
			    <input type="hidden" name="action" value="delete">
			  </FORM>
			  
			  </c:if>
			  
			  <c:if test="${actRegVO.actRegPayState =='Y'}">
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actRegister/act.do">
			    <input type="submit" value="報名完成"  disabled="disabled">
			  </FORM>
			    
			  </c:if>
			</td>
		</tr>
		</c:if>
	</c:forEach>
</table>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>

</body>
</html>
