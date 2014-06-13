<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService" />
<%-- <jsp:useBean id="getRecordByPetNo" scope="request" type="java.util.Set" /> --%>
<%-- <jsp:useBean id="recordSvc" scope="page" class="com.drRecord.model.DrRecordService" /> --%>

<html>
<head>
<title>寵物病歷資料  - listPetsAllDrRecords.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>寵物病歷資料  - listPetsAllDrRecords.jsp</h3>
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

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>看診紀錄編號</th>
		<th>看診紀錄時間</th>
		<th>看診處方</th>
		<th>醫生編號</th>
		<th>寵物名字(寵物編號)</th>
		
		
		
	</tr>
	
	<c:forEach var="record" items="${getRecordByPetNo}" >
		<tr align='center' valign='middle'>
			<td>${record.drRecNo}</td>
			<td>${record.drRecTime}</td>
			<td>${record.drRecPres}</td>
			<td>${record.drNo}</td>
			<td>
				<c:forEach var="petVO" items="${petSvc.all}">
                    <c:if test="${record.petNo==petVO.petNo}">
	                    ${petVO.petName}(${petVO.petNo})
                    </c:if>
                </c:forEach>
			</td>
			
			
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="petNo" value="${record.petNo}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do"> --%>
<!-- 			    <input type="submit" value="刪除"> -->
<%-- 			    <input type="hidden" name="petNo" value="${record.petNo}"> --%>
<!-- 			    <input type="hidden" name="action"value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>
