<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.act.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listActs_ByCompositeQuery" scope="request" type="java.util.List" />

<%-- <jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<%
PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4001);
List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<title>後端-複合查詢 - listActsByCompositeQuery.jsp</title>
</head>
<body bgcolor='white'>
			<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>"> 


<table border='1' cellpadding='5' cellspacing='0' width='700'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3><font color=red>後端-複合查詢</font></h3>
		<a href="<%=request.getContextPath()%>/backend/act/select_page.jsp"><img src="<%=request.getContextPath()%>/backend/act/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>


<table border='1' bordercolor='#CCCCFF' width='700'>
		<tr>
<!-- 			<th>活動編號</th> -->
			<th>活動名稱</th>
<!-- 			<th>活動內容</th> -->
			<th>開始時間</th>
			<th>結束時間</th>
<!-- 			<th>活動照片</th> -->
<!-- 			<th>租借器材</th> -->
<!-- 			<th>押金費用</th> -->
<!-- 			<th>主辦方繳交費用</th> -->
<!-- 			<th>活動報名費用</th> -->
			<th>目前活動狀態</th>
			<th>主辦方會員編號</th>
<!-- 			<th>審核職員編號</th> -->
			<th> </th>

		</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="actVO1" items="${listActs_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(actVO1.actNo == fromBackUpdateInput.actNo) ? 'bgcolor=#CCCCFF':''}>
					
<%-- 					<td>${actVO1.actNo}</td> --%>
					<td><a href="<%=request.getContextPath()%>/act/act.do?action=getOne_For_Display&actNo=${actVO1.actNo}">${actVO1.actName}</a></td>
<%-- 					<td>${actVO1.actContent}</td> --%>
					<td>${actVO1.actStartTime}</td>
					<td>${actVO1.actEndTime}</td>
<%-- 					<td><img src="<%=request.getContextPath()%>/act/DBGifReader3?actNo=${actVO1.actNo}"></td> --%>
<%-- 					<td>${actVO1.actEquipment}</td> --%>
<%-- 					<td>${actVO1.actDeposit}</td> --%>
<%-- 					<td>${actVO1.actHostFee}</td> --%>
<%-- 					<td>${actVO1.actRegFee}</td> --%>
					<td>	
						<c:if test="${actVO1.actStatus == 'Y'}">開放中</c:if>
						<c:if test="${actVO1.actStatus == 'N'}">審核中</c:if>
						<c:if test="${actVO1.actStatus == 'A'}">私人活動<br>審核中</c:if>
						<c:if test="${actVO1.actStatus == 'B'}">>私人活動</c:if>
						<c:if test="${actVO1.actStatus == 'E'}">活動結束</c:if>
					</td>
					<td>${actVO1.memNo}</td>
<%-- 					<td>${actVO1.empNo}</td> --%>

					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">
							<c:if test="${actVO1.actStatus == 'N' || actVO1.actStatus == 'A'}">
								<input type="submit" value="審核">
								<input type="hidden" name="actNo" value="${actVO1.actNo}">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<input type="hidden" name="whichPage"  value="<%=whichPage%>"> 
								<input type="hidden" name="action" value="getOne_For_Update_back">
							</c:if>
						</FORM>
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">
							<c:if test="${actVO1.actStatus == 'Y' || actVO1.actStatus == 'B'}">
								<input type="submit" value="重新審核">
								<input type="hidden" name="actNo" value="${actVO1.actNo}">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<input type="hidden" name="whichPage"  value="<%=whichPage%>"> 
								<input type="hidden" name="action" value="getOne_For_Update_back">
							</c:if>
						</FORM>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">
							<c:if test="${actVO1.actStatus == 'Y' || actVO1.actStatus == 'B'}">
								<input type="submit" value="已審核" disabled="disabled">
							</c:if>
						</FORM>
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">
							<c:if test="${actVO1.actStatus == 'E' }">
								<input type="submit" value="活動結束" disabled="disabled">
							</c:if>
						</FORM>
					</td>
			</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>


   			</c:if>
   			<%@ include file="/menu2.jsp" %> 
   
</body>
</body>
</html>
