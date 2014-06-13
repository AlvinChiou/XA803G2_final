<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>
<%@ page import="com.mem.model.*"%>

<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>

<jsp:useBean id="listPets_ByCompositeQuery" scope="request" type="java.util.List" />

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" /> 

<html>
<head>
<title>���-�d���ƦX�d�� - listPetsByCompositeQuery.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>

</head>
<body bgcolor='white'>

	<%@ include file="/menu1.jsp" %> 
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3><font color=red>���-�d���ƦX�d��</font></h3>
		<a href="<%=request.getContextPath()%>/backend/pet/select_page.jsp"><img src="<%= request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>


<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>�d���s��</th>
		<th>�d���W�r</th>
		<th>�d���ʧO</th>
		<th>�d�����O</th>
		<th>�d���Ӥ�</th>
		<th>�d���~��</th>
		<th>�|���s��</th>
	</tr>
<%-- 	<%@ include file="pages/page1_ByCompositeQuery.file" %> --%>
	<c:forEach var="petVO" items="${listPets_ByCompositeQuery}" >
		<tr align='center' valign='middle' >
					
			<td>${petVO.petNo}</td>
			<td>${petVO.petName}</td>
			<td>
				<c:if test="${petVO.petSex == 'boy'}">�k��</c:if>
				<c:if test="${petVO.petSex == 'girl'}">�k��</c:if>
			</td>
			<td>
				<c:if test="${petVO.petType == 'dog'}">����</c:if>
				<c:if test="${petVO.petType == 'cat'}">�߫}</c:if>
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
			</tr>
	</c:forEach>
</table>
<%-- <%@ include file="pages/page2_ByCompositeQuery.file" %> --%>

<%@ include file="/menu2.jsp" %>    
</body>
</body>
</html>
