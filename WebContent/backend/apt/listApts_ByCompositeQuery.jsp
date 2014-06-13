<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4002);
	List<PowVO> list = (List<PowVO>)session.getAttribute("list");
 %>
<jsp:useBean id="listApts_ByCompositeQuery" scope="request" type="java.util.List" />
<jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService" />
<html>
<head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">

<script>
	
		
		$(function(){
			$('#menu').children('li').click(function(){
				$('#menu').find('ul').removeClass('active');
				$(this).find('ul').toggleClass('active');	
			});
		});
		
</script>
<title>複合查詢 - listApts_ByCompositeQuery.jsp</title>
</head>
<body bgcolor='white'>
<c:if test="<%=list.contains(powVO)%>">  
<%@ include file="/menu1.jsp" %> 
    
 <!--jsp主要開始 -->
<b><font color=blue>
<!-- ☆萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位<br> -->
<!-- ☆此頁作為複合查詢時之結果練習，</font> <font color=red>已增加分頁、送出修改、刪除之功能:</font></b> -->
<c:if test="<%=list.contains(powVO)%>">  
<!-- <table border='1' cellpadding='5' cellspacing='0' width='800'> -->
<!-- 	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'> -->
<!-- 		<td> -->
<!-- 		<h3><font color=red>複合查詢</font>員工 - listApts_ByCompositeQuery.jsp</h3> -->
<%-- 		<a href="<%=request.getContextPath()%>/backend/apt-Knifeman.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回掛號管理首頁</a> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- </table> -->

</c:if>
<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th nowrap>掛號編號&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>掛號日期&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>掛號時段&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>號碼牌編號&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>應到時間&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>寵物編號+名字&nbsp;&nbsp;&nbsp;</th>
	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file"%>
	<c:forEach var="aptVO" items="${listApts_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(aptVO.aptNo==param.aptNo) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td nowrap>${aptVO.aptNo}</td>
			<td nowrap>${aptVO.aptDate}</td>
			<td nowrap>${aptVO.aptPeriod}</td>
			<td nowrap>${aptVO.aptNoSlip}</td>
			<td nowrap>${aptVO.aptRegTime}</td>		
			<td nowrap><c:forEach var="petVO" items="${petSvc.all}">
                    <c:if test="${petVO.petNo==aptVO.petNo}">
	                    ${aptVO.petNo}【<font color=orange>${petVO.petName}</font> 】
	                    <td>
	                    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do">
			    				<input type="hidden" name="petNo" value="${petVO.petNo}"> 
			    				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			   					<input type="hidden" name="action" value="getOne_For_Display">
			    				<input type="submit" value="查詢此寵物">
			    			</FORM>
	                    </td>
                    </c:if>
                </c:forEach>
			</td>
						<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/apt/apt.do">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="aptNo" value="${aptVO.aptNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apt/apt.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="aptNo" value="${aptVO.aptNo}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>

<!-- <br>本網頁的路徑:<br><b> -->
<%--    <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br> --%>
<%--    <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b> --%>

<!--jsp主要結束 -->
    
    
    
    

    <%@ include file="/menu2.jsp" %> 
</c:if>
</body>

</html>
