<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>
<%@ page import="com.mem.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    PetService petSvc = new PetService();
    List<PetVO> list = petSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<html>
<head>
<title>所有寵物資料 </title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>

<script src="<%=request.getContextPath()%>/menu.js"></script>		

</head>
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
    
<!-- jsp主要開始 -->

<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有寵物資料</h3>  
		<a href="<%=request.getContextPath()%>/backend/pet/select_page.jsp"><img src="<%=request.getContextPath()%>/backend/pet/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>
<br>
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
		<th nowrap>寵物編號 </th>
		<th nowrap>寵物名字</th>
		<th nowrap>寵物性別</th>
		<th nowrap>寵物類別</th>
		<th nowrap>寵物照片</th>
		<th nowrap>寵物年齡</th>
		<th nowrap>會員編號</th>
		<th> </th>
		<th> </th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="petVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${petVO.petNo}</td>
			<td>${petVO.petName}</td>
			<td>
				<c:if test="${petVO.petSex == 'boy'}">男生</c:if>
				<c:if test="${petVO.petSex == 'girl'}">女生</c:if>
			</td>
			<td>
				<c:if test="${petVO.petType == 'dog'}">狗狗</c:if>
				<c:if test="${petVO.petType == 'cat'}">貓咪</c:if>
			</td>
			<td><img src="<%=request.getContextPath()%>/pet/DBGifReader3?petNo=${petVO.petNo}"></td>
			<td>${petVO.petAge}</td>
			<td>
				<c:forEach var="memVO" items="${memSvc.all}">
					<c:if test="${petVO.memNo == memVO.memno}">
						${memVO.memname}(${petVO.memNo })
					</c:if>
				</c:forEach>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="petNo" value="${petVO.petNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/apt/apt.do">
			     <input type="submit" value="掛號">
			     <input type="hidden" name="petNo" value="${petVO.petNo}">
			     <input type="hidden" name="action"	value="insert"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do"> --%>
<!-- 			    <input type="submit" value="刪除"> -->
<%-- 			    <input type="hidden" name="petNo" value="${petVO.petNo}"> --%>
<!-- 			    <input type="hidden" name="action"value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<!-- jsp主要結束 -->

    
<%@ include file="/menu2.jsp" %> 


</body>
</html>
