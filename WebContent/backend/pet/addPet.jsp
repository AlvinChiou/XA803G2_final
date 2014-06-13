<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pet.model.*"%>
<%
PetVO petVO = (PetVO) request.getAttribute("petVO");
%>

<html>
<head>
<title>寵物資料新增 - addPet.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>

<script src="<%=request.getContextPath()%>/menu.js"></script>		

</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<%@ include file="/menu1.jsp" %> 
<!-- jsp開始 -->
<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>寵物資料新增</h3>
		</td>
		<td>
		   <a href="<%= request.getContextPath()%>/backend/pet/select_page.jsp"><img src="<%= request.getContextPath()%>/backend/pet/images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>寵物資料:</h3>
<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font color='red'>請修正以下錯誤: -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li>${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<!-- 	</font> -->
<%-- </c:if> --%>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/pet/pet.do" name="form1" enctype="multipart/form-data">
<table border="0">

	<tr>
		<td>寵物名字:</td>
		<td><input type="TEXT" name="petName" size="10" value="<%= (petVO==null)? "" : petVO.getPetName()%>" /><font color='red'>${errorMsgs.petName}</td>
		
	</tr>
	<tr>
		<td>寵物性別:</td>
		<td><input type="radio" name="petSex" value="boy" checked/>男生
			<input type="radio" name="petSex" value="girl" />女生
		</td>
	</tr>
	<tr>
		<td>寵物類別:</td>
		<td><input type="radio" name="petType" value="dog" checked/>狗狗
			<input type="radio" name="petType" value="cat" />貓咪
		</td>
	</tr>
	<tr>
		<td>寵物照片:</td>
		<td><input type="file" name="petPic" size="0" value="<%= (petVO==null)? "" : petVO.getPetPic()%>" /><font color='red'>${errorMsgs.petPic}</td>
	</tr>
	<tr>
		<td>寵物年齡:</td>
		<td><input type="TEXT" name="petAge" size="5" value="<%= (petVO==null)? "" : petVO.getPetAge()%>" /><font color='red'>${errorMsgs.petAge}</td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="memNo" size="10" value="<%= (petVO==null)? "" : petVO.getMemNo()%>" /><font color='red'>${errorMsgs.memNo}</td>
<!-- 		(Integer)request.getAttribute("memNoFromAuto") -->
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="insert">
<br>
<input type="submit" value="送出新增">

</FORM>

<!-- jsp結束 -->


    
  <%@ include file="/menu2.jsp" %> 

</body>

</html>
