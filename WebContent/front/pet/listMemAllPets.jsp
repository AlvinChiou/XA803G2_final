<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%--
	//PetVO petVO = (PetVO) request.getAttribute("petVO");//EmpServlet.java(Concroller), 存入req的empVO物件
	List<PetVO> memPet = (List<PetVO>) request.getAttribute("memPetVO");
	pageContext.setAttribute("memPet",memPet);
--%>
<html>
<head>
<title>所有寵物資料 - listMemAllPets.jsp</title>
<link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" />
<link href="<%= request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css" />
<link href="<%= request.getContextPath()%>/css/box_css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%= request.getContextPath()%>/js/jquery-1.4.2.js"></script>  
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/jquery.fancybox-1.3.4.css" media="screen" />
<script type="text/javascript">
	$(document).ready(function() {
		/*
		*   Examples - images
		*/

		$("a#example1").fancybox();
		$("#various3").fancybox({
			'width'				: '75%',
			'height'			: '75%',
			'autoScale'			: false,
			'transitionIn'		: 'none',
			'transitionOut'		: 'none',
			'type'				: 'iframe'
		});
	});
</script>

</head>

<body bgcolor='white'>

	<div id="wrapper">
		<div id="main_bg">
    		<div id="main_bg01">
        	<div id="apDiv13"></div>
			<c:if test="${memVO == null}"><%@ include file="/header.jsp" %></c:if>
        	<c:if test="${memVO != null}"><%@ include file="/loginHeader.jsp" %></c:if>
			<%@ include file="/ad.jsp" %>   
    	<div>&nbsp;</div>
  		</div>
		</div>
		<div style="margin-left:110px">
			<input type="button" value="新增寵物資料" onclick="location.href='<%= request.getContextPath()%>/front/pet/addPet.jsp'">	
		</div>
<%-- <c:if test="${!memPet.isEmpty()}"> --%>
<table border='1' bordercolor='#CCCCFF' width='800' align='center'>
	
	<tr>
<!-- 		<th nowrap>寵物編號</th> -->
		<th nowrap>寵物名字</th>
		<th nowrap>寵物性別</th>
		<th nowrap>寵物類別</th>
		<th nowrap>寵物照片</th>
		<th nowrap>寵物年齡</th>
<!-- 		<th nowrap>會員編號</th> -->
		<th nowrap>修改</th>
		
	</tr>
	
	<c:forEach var="memPet" items="${memPetVO}" > 
		<tr align='center' valign='middle'>
<%-- 			<td>${memPet.petNo}</td> --%>
			<td>${memPet.petName}</td>
			<td><c:if test="${memPet.petSex == 'boy'}">男生</c:if>
				<c:if test="${memPet.petSex == 'girl'}">女生</c:if></td>
			<td><c:if test="${memPet.petType == 'dog'}">狗狗</c:if>
				<c:if test="${memPet.petType == 'girl'}">貓咪</c:if>${memPet.petType}</td>
			<td><img src="DBGifReader3?petNo=${memPet.petNo}"></td>
			<td>${memPet.petAge}</td>
<%-- 			<td>${memPet.memNo}</td> --%>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/pet/pet.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="petNo" value="${memPet.petNo}">
			     <input type="hidden" name="requestURL" value="<%= request.getServletPath() %>">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do"> --%>
<!-- 			    <input type="submit" value="刪除"> -->
<%-- 			    <input type="hidden" name="petNo" value="${memPet.petNo}"> --%>
<!-- 			    <input type="hidden" name="action"value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%-- </c:if> --%>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
</body>
</html>
