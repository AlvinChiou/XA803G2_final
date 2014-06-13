<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>
<%
	PetVO petVO = (PetVO) request.getAttribute("petVO");//EmpServlet.java(Concroller), 存入req的empVO物件
	
%>


<html>
<head>
<title>所有寵物資料 - listOnePet.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		
</head>
<body bgcolor='white'>
<%@ include file="/menu1.jsp" %> 
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
		<th>會員編號123</th>
		
	</tr>
	<tr align='center' valign='middle'>
			
			<td>${petVO.petNo}</td>
			<td>${petVO.petName}</td>
			<td>${petVO.petSex}</td>
			<td>${petVO.petType}</td>
			<td><img src="DBGifReader3?actNo=${petVO.petPic}"></td>
			<td>${petVO.petAge}</td>
			
			<td nowrap>
			<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
			
				<c:forEach var="memVO" items="${memSvc.all}">
                    <c:if test="${petVO.memNo==memVO.memno}">
	                    ${memVO.memno}【<font color=orange>${memVO.memname}</font> 】
	                    <td>
	                    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do">
			    				<input type="hidden" name="memno" value="${memVO.memno}"> 
			    				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			   					<input type="hidden" name="action" value="getOne_For_Display">
			    				<input type="submit" value="查詢此會員">
			    			</FORM>
	                    </td>
                    </c:if>
                </c:forEach>
			</td>
	</tr>
</table>

<%@ include file="/menu2.jsp" %> 

</body>
</html>
