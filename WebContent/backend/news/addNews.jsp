<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>
<%	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");%>

<html>
<head>
<title>最新消息新增 - addNews.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>

<script>
function miracle() {
	document.getElementsByName("newstitle")[0].value = "中小型貓跳台造型多樣款式";
	document.getElementsByName("newscontent")[0].value = "尺寸適合公寓房間的養貓族，靠放在牆邊，讓貓咪有空間蹦蹦跳跳，抓抓磨爪，休息睡覺，心情愉悅，天天運動常保健康。";
	
}
</script>


</head>

<body bgcolor='white'>
	<%@ include file="/menu1.jsp" %> 
	<input type = "button" id = "btn" value = "神奇小按鈕" onclick = "miracle()">
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>最新消息新增</h3>
			</td>
			<td><a href="<%=request.getContextPath()%>/backend/news/select_page.jsp"><img src="<%= request.getContextPath()%>/backend/news/images/tomcat.gif" width="100" height="100" border="1">回首頁</a></td>
		</tr>
	</table>

	<h3>資料員工:</h3>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
<!-- 		<font color='red'>請修正以下錯誤: -->
<!-- 			<ul> -->
<%-- 				<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 					<li>${message}</li> --%>
<%-- 				</c:forEach> --%>
<!-- 			</ul> -->
<!-- 		</font> -->
	</c:if>

	<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/news/news.do" name="form1" enctype="multipart/form-data">
		<table border="0">

			<tr>
				<td>消息主題:</td>
				<td>
					<input type="TEXT" name="newstitle" size="45" value="<%=(newsVO == null) ? "" : newsVO.getNewstitle()%>" />${errorMsgs.newstitle}
				</td>
<%-- 				<td>${errorMsgs.newstitle}</td> --%>
			</tr>
			<tr>
				<td>消息類別:</td>
				<td>
					<select size="1" name="newstype">
						<option value="1" selected=''>活動類
						<option value="2">公告類
						<option value="3">商品類
					</select>
				</td>
<%-- 				<td>${errorMsgs.newstype}</td> --%>
			</tr>
			<tr>
				<td>消息內容:</td>
				<td><textarea name="newscontent" rows="30" cols="55"><%=(newsVO == null)? "" : newsVO.getNewscontent()%>${errorMsgs.newcontent}</textarea>
<%-- 				<td>${errorMsgs.newcontent}</td> --%>
<%-- 				<input type="TEXT" name="newscontent" size="45" value="<%=(newsVO == null) ? "尺寸適合公寓房間的養貓族，靠放在牆邊，讓貓咪有空間蹦蹦跳跳，抓抓磨爪，休息睡覺，心情愉悅，天天運動常保健康。" : newsVO.getNewscontent()%>" /></td> --%>
			</tr>
			<tr>
				<td>照片:</td>
				<td><input type="file" name="newspic" size="45" value="<%=(newsVO == null)? "" : newsVO.getNewspic()%>" />${errorMsgs.newspic}</td>
<%-- 				<td>${errorMsgs.newspic}</td> --%>
			</tr>
			<tr>
				<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
				<td>發布時間:</td>
				<td>
		    		<input type="hidden" name="newspotime" value="<%= date_SQL%>" /><%= date_SQL%>
				</td>
			</tr>
			<tr>
				<td>員工編號:</td>
<%-- 				<td><input type="TEXT" name="empno" size="45" value="${employeeVO.empNo}" />${employeeVO.empNo}</td> --%>
				<td><input type="hidden" name="empno" size="45" value="1002" />1002</td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> 
		     <input type="submit" value="送出新增">
	</FORM>
	
		<%@ include file="/menu2.jsp" %> 
</body>

</html>
