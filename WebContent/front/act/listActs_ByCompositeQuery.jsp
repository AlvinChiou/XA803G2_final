<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listActs_ByCompositeQuery" scope="request" type="java.util.List" />
<%-- <% List<ActVO> listActs_ByCompositeQuery = (List<ActVO>) request.getAttribute("listActs_ByCompositeQuery"); %> --%>
<%-- <jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<html>
<head>
<title>前端-複合查詢 - listEmps_ByCompositeQuery.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=blue>
☆萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位<br>
☆此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='1500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3><font color=red>前端-複合查詢</font>員工 - listEmps_ByCompositeQuery.jsp</h3>
		<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>


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
	<c:forEach var="actVO1" items="${listActs_ByCompositeQuery}">
		<tr align='center' valign='middle'>
			<td>${actVO1.actNo}</td>
			<td>${actVO1.actName}</td>
			<td>${actVO1.actContent}</td>
			<td>${actVO1.actStartTime}</td>
			<td>${actVO1.actEndTime}</td>
			<td><img src="DBGifReader3?actNo=${actVO1.actNo}"></td>
			<td>${actVO1.actEquipment}</td>
			<td>${actVO1.actDeposit}</td>
			<td>${actVO1.actHostFee}</td>
			<td>${actVO1.actRegFee}</td>
						<c:if test="${actVO1.actStatus == 'Y'}">
							<td>報名中</td>
						</c:if>
						<c:if test="${actVO1.actStatus == 'N'}">
							<td>審核中</td>
						</c:if>
						<c:if test="${actVO1.actStatus == 'A'}">
							<td>私人活動<br>
								未審核
							</td>
						</c:if>
						<c:if test="${actVO1.actStatus == 'B'}">
							<td>私人活動<br></td>
						</c:if>
			<td>${actVO1.memNo}</td>
			<td>${actVO1.empNo}</td>
			<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">
							<c:if test="${(actVO1.actStatus == 'N' || actVO1.actStatus == 'A') && actVO1.memNo == '7005'}">
								<input type="submit" value="修改">
								<input type="hidden" name="actNo" value="${actVO1.actNo}">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<input type="hidden" name="action" value="getOne_For_Update">
							</c:if>
						</FORM>
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">	
							<c:if test="${actVO1.actStatus == 'Y' && actVO1.memNo != '7005'}">
								<input type="submit" value="報名" >
								<input type="hidden" name="actNo" value="${actVO1.actNo}">
								<input type="hidden" name="action" value="getOne_For_ActRegister">
							</c:if>
						</FORM>
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/actRegister/actRegister.do">
							  <c:if test="${actVO1.memNo == '7005' && actVO1.actStatus == 'Y'}">
							   		<input type="submit" value="查詢報名狀況" >
							    	<input type="hidden" name="actNo" value="${actVO1.actNo}">
							    	<input type="hidden" name="action"value="getAll_For_Display_By_ActNo">
							  </c:if>
						</FORM>
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">
								<c:if test="${(actVO1.actStatus == 'N' || actVO1.actStatus == 'A') && actVO1.memNo == '7005'}">
							    	<input type="submit" value="刪除" >
							    	<input type="hidden" name="actNo" value="${actVO1.actNo}">
							    	<input type="hidden" name="action"value="delete">
							    </c:if>
						</FORM>
						
					</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>
