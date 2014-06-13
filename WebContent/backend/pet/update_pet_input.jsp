<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pet.model.*"%>
<%
	PetVO petVO = (PetVO) request.getAttribute("petVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>


<head>
<title>寵物資料修改 - update_pet_input.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		

</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>
<%@ include file="/menu1.jsp" %> 

<!--     <h2>動物留言管理</h2> -->
<!--     <div id="search_bar"> -->
<!--       <select name="xxx"> -->
<!--         <option value="111">111</option> -->
<!--         <option value="222">222</option> -->
<!--         <option value="333">333</option> -->
<!--       </select> -->
<!--       <input type="text" name="zzz" id="zzz" /> -->
<!--       <a href="#" class="btn">搜尋</a> </div> -->
    <!-- end #search_bar -->
<!-- jsp開始 -->
<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>寵物資料修改</h3>
		<a href="<%= request.getContextPath()%>/backend/pet/select_page.jsp"><img src="<%= request.getContextPath()%>/backend/pet/images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>寵物資料修改:</h3>
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

<FORM METHOD="post" ACTION="pet.do" name="form1">
<table border="0">
	<tr>
		<td>寵物編號:<font color=red><b>*</b></font></td>
		<td><%=petVO.getPetNo()%></td>
	</tr>
	<tr>
		<td>寵物名字:</td>
		<td><input type="TEXT" name="petName" size="45" value="<%=petVO.getPetName()%>" /></td>
	</tr>
	<tr>
		<td>寵物性別:</td>
		<td><input type="TEXT" name="petSex" size="45"	value="<%=petVO.getPetSex()%>" /></td>
	</tr>
	<tr>
		<td>寵物類別:</td>
		<td><input type="TEXT" name="petType" size="45" value="<%=petVO.getPetType()%>" /></td>
	</tr>
	<tr>
		<td>寵物照片:</td>
		<td><input type="TEXT" name="petPic" size="45"	value="<%=petVO.getPetPic()%>" /></td>
	</tr>
	<tr>
		<td>寵物年齡:</td>
		<td><input type="TEXT" name="petAge" size="45" value="<%=petVO.getPetAge()%>" /></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="memNo" size="45" value="<%=petVO.getMemNo()%>" /></td>
	</tr>
	


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="petNo" value="<%=petVO.getPetNo()%>">
<input type="submit" value="送出修改"></FORM>
<!-- jsp結束 -->


<%@ include file="/menu2.jsp" %> 


</body>
</html>
