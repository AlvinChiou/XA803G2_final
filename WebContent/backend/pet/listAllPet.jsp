<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>
<%@ page import="com.mem.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    PetService petSvc = new PetService();
    List<PetVO> list = petSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<html>
<head>
<title>�Ҧ��d����� </title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>

<script src="<%=request.getContextPath()%>/menu.js"></script>		

</head>
<body bgcolor='white'>
<%@ include file="/menu1.jsp" %> 
	
<!--     <h2>�ʪ��d���޲z</h2> -->
<!--     <div id="search_bar"> -->
<!--       <select name="xxx"> -->
<!--         <option value="111">111</option> -->
<!--         <option value="222">222</option> -->
<!--         <option value="333">333</option> -->
<!--       </select> -->
<!--       <input type="text" name="zzz" id="zzz" /> -->
<!--       <a href="#" class="btn">�j�M</a> </div> -->
    <!-- end #search_bar -->
    
<!-- jsp�D�n�}�l -->

<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ��d�����</h3>  
		<a href="<%=request.getContextPath()%>/backend/pet/select_page.jsp"><img src="<%=request.getContextPath()%>/backend/pet/images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>
<br>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th nowrap>�d���s�� </th>
		<th nowrap>�d���W�r</th>
		<th nowrap>�d���ʧO</th>
		<th nowrap>�d�����O</th>
		<th nowrap>�d���Ӥ�</th>
		<th nowrap>�d���~��</th>
		<th nowrap>�|���s��</th>
		<th> </th>
		<th> </th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="petVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
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
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="petNo" value="${petVO.petNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/apt/apt.do">
			     <input type="submit" value="����">
			     <input type="hidden" name="petNo" value="${petVO.petNo}">
			     <input type="hidden" name="action"	value="insert"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do"> --%>
<!-- 			    <input type="submit" value="�R��"> -->
<%-- 			    <input type="hidden" name="petNo" value="${petVO.petNo}"> --%>
<!-- 			    <input type="hidden" name="action"value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<!-- jsp�D�n���� -->

    
<%@ include file="/menu2.jsp" %> 


</body>
</html>
