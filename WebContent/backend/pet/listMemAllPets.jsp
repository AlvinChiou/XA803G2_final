<%@ page contentType="text/html; charset=Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>
<%
	//PetVO petVO = (PetVO) request.getAttribute("petVO");//EmpServlet.java(Concroller), 存入req的empVO物件
	List<PetVO> memPet = (List<PetVO>) request.getAttribute("memPetVO");
	pageContext.setAttribute("memPet",memPet);

	
%>
<html>
<head>
<title>所有寵物資料 - listOnePet.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>

<script src="<%=request.getContextPath()%>/menu.js"></script>		

</head>
<body bgcolor='white'>
<%@ include file="/menu1.jsp" %> 

<!-- jsp開始 -->
<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有寵物資料</h3>
		<a href="<%= request.getContextPath()%>/backend/pet/select_page.jsp"><img src="<%= request.getContextPath()%>/backend/pet/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>



<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>寵物編號</th>
		<th>寵物名字</th>
		<th>寵物性別</th>
		<th>寵物類別</th>
		<th>寵物照片</th>
		<th>寵物年齡</th>
		<th>會員編號</th>
		
	</tr>
	
	<c:forEach var="memPet1" items="${memPet}" > 
		<tr align='center' valign='middle'>
			
			<td>${memPet1.petNo}</td>
			<td>${memPet1.petName}</td>
			<td>${memPet1.petSex}</td>
			<td>${memPet1.petType}</td>
			<td><img src="DBGifReader3?actNo=${memPet1.petPic}"></td>
			<td>${memPet1.petAge}</td>
			<td>${memPet1.memNo}</td>
		</tr>
	</c:forEach>
</table>

<!-- jsp結束 -->

    
    
    
<%@ include file="/menu2.jsp" %> 



</body>
</html>
