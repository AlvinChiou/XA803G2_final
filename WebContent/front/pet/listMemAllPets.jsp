<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%--
	//PetVO petVO = (PetVO) request.getAttribute("petVO");//EmpServlet.java(Concroller), �s�Jreq��empVO����
	List<PetVO> memPet = (List<PetVO>) request.getAttribute("memPetVO");
	pageContext.setAttribute("memPet",memPet);
--%>
<html>
<head>
<title>�Ҧ��d����� - listMemAllPets.jsp</title>
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
			<input type="button" value="�s�W�d�����" onclick="location.href='<%= request.getContextPath()%>/front/pet/addPet.jsp'">	
		</div>
<%-- <c:if test="${!memPet.isEmpty()}"> --%>
<table border='1' bordercolor='#CCCCFF' width='800' align='center'>
	
	<tr>
<!-- 		<th nowrap>�d���s��</th> -->
		<th nowrap>�d���W�r</th>
		<th nowrap>�d���ʧO</th>
		<th nowrap>�d�����O</th>
		<th nowrap>�d���Ӥ�</th>
		<th nowrap>�d���~��</th>
<!-- 		<th nowrap>�|���s��</th> -->
		<th nowrap>�ק�</th>
		
	</tr>
	
	<c:forEach var="memPet" items="${memPetVO}" > 
		<tr align='center' valign='middle'>
<%-- 			<td>${memPet.petNo}</td> --%>
			<td>${memPet.petName}</td>
			<td><c:if test="${memPet.petSex == 'boy'}">�k��</c:if>
				<c:if test="${memPet.petSex == 'girl'}">�k��</c:if></td>
			<td><c:if test="${memPet.petType == 'dog'}">����</c:if>
				<c:if test="${memPet.petType == 'girl'}">�߫}</c:if>${memPet.petType}</td>
			<td><img src="DBGifReader3?petNo=${memPet.petNo}"></td>
			<td>${memPet.petAge}</td>
<%-- 			<td>${memPet.memNo}</td> --%>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/pet/pet.do">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="petNo" value="${memPet.petNo}">
			     <input type="hidden" name="requestURL" value="<%= request.getServletPath() %>">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do"> --%>
<!-- 			    <input type="submit" value="�R��"> -->
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
