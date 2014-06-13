<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%-- <% 	ActVO actVO = (ActVO) request.getAttribute("actVO"); %> --%>
<html>
<head>
<title>所有主辦活動資料 - listAllActsByMemNo.jsp</title>
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
	<%-- <%= ( (ActVO)request.getAttribute("actVO") ).getActNo() %> --%>

	<table border='1' bordercolor='#CCCCFF' width='950' align='center'>
		<tr>
			<th nowrap>活動編號</th>
			<th nowrap>活動名稱</th>
<!-- 			<th>活動內容</th> -->
			<th nowrap>開始時間</th>
			<th nowrap>結束時間</th>
<!-- 			<th>活動照片</th> -->
			<th nowrap>租借器材</th>
			<th nowrap>押金費用</th>
			<th nowrap>主辦繳交費用</th>
			<th nowrap>活動報名費用</th>
			<th nowrap>目前狀態</th>
<!-- 			<th nowrap>主辦方會員編號</th> -->
<!-- 			<th>審核職員編號</th> -->

		</tr>

		<c:forEach var="act" items="${listAllActsByMemNo}">
			<tr align='center' valign='middle' >
				
					<td>${act.actNo}</td>
					<td><a href="<%= request.getContextPath()%>/front/act/act.do?action=getOne_For_Display&actNo=${act.actNo}">${act.actName}</a></td>
<%-- 					<td>${act.actContent}</td> --%>
					<td>${act.actStartTime}</td>
					<td>${act.actEndTime}</td>
<%-- 					<td><img src="<%=request.getContextPath()%>/act/DBGifReader3?actNo=${act.actNo}"></td> --%>
					<td>${act.actEquipment}</td>
					<td>${act.actDeposit}</td>
					<td>${act.actHostFee}</td>
					<td>${act.actRegFee}</td>
					<td><c:if test="${act.actStatus == 'Y'}">開放中</td></c:if>
						<c:if test="${act.actStatus == 'N'}">待審核</td></c:if>
						<c:if test="${act.actStatus == 'E'}">活動結束</td></c:if>
<%-- 					<td>${act.memNo}</td> --%>
<%-- 					<td>${act.empNo}</td> --%>
<!-- 					<td> -->
<!-- 						<FORM METHOD="post" -->
<%-- 							ACTION="<%=request.getContextPath()%>/act/act.do"> --%>
<%-- 							<c:if test="${act.actStatus == 'N'}"> --%>
<!-- 								<input type="submit" value="未審核"> -->
<%-- 								<input type="hidden" name="actNo" value="${act.actNo}"> --%>
<!-- 								<input type="hidden" name="requestURL" -->
<%-- 									value="<%=request.getServletPath()%>"> --%>
<!-- 								<input type="hidden" name="action" value="getOne_For_Update"> -->
<%-- 							</c:if> --%>
<%-- 							<c:if test="${act.actStatus == 'Y'}"> --%>
<!-- 								<input type="submit" value="已審核" disabled="disabled"> -->
<%-- 							</c:if> --%>
<!-- 						</FORM> -->
<!-- 					</td> -->

					<c:if test="${act.actStatus == 'N'}">
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/act/act.do">
								<input type="submit" value="修改" > 
								<input type="hidden" name="actNo" value="${act.actNo}">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
								<input type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
					</c:if>

					<!-- 			<td> -->
					<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/act/act.do"> --%>

					<%-- 			    <input type="submit" value="刪除" ${(actVO1.actStatus=="Y")? 'disabled=disabled':''} }> --%>
					<%-- 			    <input type="hidden" name="actNo" value="${actVO1.actNo}"> --%>
					<!-- 			    <input type="hidden" name="action"value="delete"></FORM> -->
					<!-- 			</td> -->
				
			</tr>
		</c:forEach>
	</table>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>

	<!-- <br>本網頁的路徑:<br><b> -->
	<%--    <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br> --%>
	<%--    <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b> --%>

</body>
</html>
