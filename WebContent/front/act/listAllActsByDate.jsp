<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>



<%-- <% 	ActVO actVO = (ActVO) request.getAttribute("actVO"); %> --%>
<html>
<head>
<title>所有員工資料 - listAllAct.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='1500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' width='20' height='20'>
		<td>
		<h3>所有員工資料 - ListAllAct.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='1500'>
	<tr>
		<th>活動編號</th>
		<th>活動名稱</th>
		<th>活動內容</th>
		<th>開始時間</th>
		<th>結束時間</th>
		<th>活動照片</th>
		<th>租借器材</th>
		<th>押金費用</th>
		<th>主辦方繳交費用</th>
		<th>活動報名費用</th>
		<th>目前活動狀態</th>
		<th>主辦方會員編號</th>
		<th>審核職員編號</th>
		
	</tr>
	
	<c:forEach var="act" items="${listAllActsByDate}" >
		<tr align='center' valign='middle' >
			
			<td>${act.actNo}</td>
			<td>${act.actName}</td>
			<td>${act.actContent}</td>
			<td>${act.actStartTime}</td>
			<td>${act.actEndTime}</td>
			<td><img src="DBGifReader3?actNo=${act.actNo}"></td>
			<td>${act.actEquipment}</td>
			<td>${act.actDeposit}</td>
			<td>${act.actHostFee}</td>
			<td>${act.actRegFee}</td>
			<td>${act.actStatus}</td>
			<td>${act.memNo}</td>
			<td>${act.empNo}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">
			     <c:if test="${act.actStatus == 'N'}">
			     <input type="submit" value="審核">
			     <input type="hidden" name="actNo" value="${act.actNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			     </c:if>
			     <c:if test="${act.actStatus == 'Y'}">
			     <input type="submit" value="已審核" font-color="blue" disabled="disabled">
			     </c:if>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">
			    
			    <input type="submit" value="修改" >
			    <input type="hidden" name="actNo" value="${act.actNo}">
			    <input type="hidden" name="action"value="getOne_For_Update"></FORM>
			</td>
			
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do"> --%>
			    
<%-- 			    <input type="submit" value="刪除" ${(actVO1.actStatus=="Y")? 'disabled=disabled':''} }> --%>
<%-- 			    <input type="hidden" name="actNo" value="${actVO1.actNo}"> --%>
<!-- 			    <input type="hidden" name="action"value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>


<!-- <br>本網頁的路徑:<br><b> -->
<%--    <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br> --%>
<%--    <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b> --%>
   
</body>
</html>
