<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.lost.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body bgcolor='#EFF6FF'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><img src="<%= request.getContextPath()%>/images/photo.jpg" width='150' height='150' align="left"/>
    	<h3>iPET Mem: Home</h3>
    	<a href="<%= request.getContextPath() %>/index.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
    	</td>
  </tr>
</table>

${memVO.memname} ，你好。<input type="button" value="登出" onclick=""></button><BR>

<h3>失蹤協尋文章查詢</h3>

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

<ul>
	<li><a href='<%= request.getContextPath()%>/front/lost/listAllLost.jsp'>List</a> all Losts. </li> <br><br>
	
	<li>
		<form method="post" action="<%= request.getContextPath()%>/lost/lost.do">
			<b>輸入文章編號(如5001):</b>
			<input type="text" name="lostno">
			<input type="submit" value="送出">
			<input type="hidden" name="action" value="getOne_For_Display">
		</form>
	</li>
	
	<li>
		<form method="post" action="<%= request.getContextPath()%>/lost/lost.do">
			<b>輸入會員編號(如7001):</b>
			<input type="text" name="memno">
			<input type="submit" value="送出">
			<input type="hidden" name="action" value="listLost_ByMemno">
		</form>
	</li>
	
	<jsp:useBean id="lostSvc" scope="page" class="com.lost.model.LostService" />

	<li>
		<form method="post" action="<%= request.getContextPath()%>/lost/lost.do">
			<b><font color=orange>選擇文章狀態:</font></b>
			<select size="1" name="loststate">
          		<option value="0">失蹤
          		<option value="1">拾獲
          		<option value="2">已找到
          		<option value="3">已領回
			</select>
			<input type="submit" value="送出">
			<input type="hidden" name="action" value="listLost_ByState">
		</form>
	</li>
</ul>

<h3>失蹤協尋文章管理</h3>

<ul>
	<li><a href='<%= request.getContextPath()%>/front/lost/addLost.jsp'>Add</a> a new Lost.</li>
</ul>
</body>
</html>