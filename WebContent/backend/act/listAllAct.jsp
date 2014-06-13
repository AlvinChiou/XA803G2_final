<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	ActService actSvc = new ActService();
	List<ActVO> list = actSvc.getAll();
	pageContext.setAttribute("list", list);
	
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4001);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>

<%-- <% 	ActVO actVO = (ActVO) request.getAttribute("actVO"); %> --%>
<html>
<head>
<title>後端-所有活動資料 - listAllAct.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<title>前端-活動新增 - addAct.jsp</title>

</head>
<body bgcolor='white'>
			<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>"> 

	<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
	<table border='1' cellpadding='5' cellspacing='0' width='1500'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' width='20'
			height='20'>
			<td>
				<h3>後端-所有活動資料 - ListAllAct.jsp</h3> <a href="select_page.jsp"><img
					src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
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

	<table border='1' bordercolor='#CCCCFF' width='1000'>
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
		<%@ include file="page1.file"%>
		<c:forEach var="actVO1" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle' ${(actVO1.actNo == fromBackUpdateInput.actNo) ? 'bgcolor=#CCCCFF':''}>
				
			
					<td>${actVO1.actNo}</td>
					<td nowrap>${actVO1.actName}</td>
					<td>${actVO1.actContent}</td>
					<td>${actVO1.actStartTime}</td>
					<td>${actVO1.actEndTime}</td>
					<td><img src="<%=request.getContextPath()%>/act/DBGifReader3?actNo=${actVO1.actNo}"></td>
					<td>${actVO1.actEquipment}</td>
					<td>${actVO1.actDeposit}</td>
					<td>${actVO1.actHostFee}</td>
					<td>${actVO1.actRegFee}</td>
					<td>	
						<c:if test="${actVO1.actStatus == 'Y'}">開放中</c:if>
						<c:if test="${actVO1.actStatus == 'N'}">審核中</c:if>
						<c:if test="${actVO1.actStatus == 'A'}">私人活動<br>審核中</c:if>
						<c:if test="${actVO1.actStatus == 'B'}">>私人活動</td></c:if>
						<c:if test="${actVO1.actStatus == 'E'}">活動結束</c:if>
					<td>${actVO1.memNo}</td>
					<td>${actVO1.empNo}</td>

					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">
							<c:if test="${actVO1.actStatus == 'N' || actVO1.actStatus == 'A'}">
								<input type="submit" value="審核">
								<input type="hidden" name="actNo" value="${actVO1.actNo}">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<input type="hidden" name="action" value="getOne_For_Update_back">
							</c:if>
						</FORM>
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">
							<c:if test="${actVO1.actStatus == 'Y' || actVO1.actStatus == 'B'}">
								<input type="submit" value="已審核" disabled="disabled">
							</c:if>
						</FORM>
						
<%-- 						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">	 --%>
<%-- 							<c:if test="${actVO1.actStatus == 'Y' && actVO1.memNo != '7003'}"> --%>
<!-- 								<input type="submit" value="報名" > -->
<%-- 								<input type="hidden" name="actNo" value="${actVO1.actNo}"> --%>
<!-- 								<input type="hidden" name="action" value="getOne_For_ActRegister"> -->
<%-- 							</c:if> --%>
<!-- 						</FORM> -->
						
<%-- 						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/actRegister/actRegister.do"> --%>
<%-- 							  <c:if test="${actVO1.memNo == '7003'}"> --%>
<!-- 							   		<input type="submit" value="查詢報名狀況" > -->
<%-- 							    	<input type="hidden" name="actNo" value="${actVO1.actNo}"> --%>
<!-- 							    	<input type="hidden" name="action"value="getAll_For_Display_By_ActNo"> -->
<%-- 							  </c:if> --%>
<!-- 						</FORM> -->
							    
<%-- 						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do"> --%>
<!-- 							  <input type="submit" value="修改" }> -->
<%-- 							  <input type="hidden" name="actNo" value="${actVO1.actNo}"> --%>
<%-- 							  <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<!-- 							  <input type="hidden" name="action"value="getOne_For_Update_back"> -->
<!-- 						</FORM> -->
					</td>
			</tr>


		</c:forEach>
	</table>
	

	<!-- <br>本網頁的路徑:<br><b> -->
	<%--    <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br> --%>
	<%--    <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b> --%>
			</c:if>
			<%@ include file="/menu2.jsp" %> 

</body>
</html>
