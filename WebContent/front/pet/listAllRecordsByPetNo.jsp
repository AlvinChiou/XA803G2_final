<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService" />
<%-- <jsp:useBean id="getRecordByPetNo" scope="request" type="java.util.Set" /> --%>
<%-- <jsp:useBean id="recordSvc" scope="page" class="com.drRecord.model.DrRecordService" /> --%>

<html>
<head>
<title>�d���f�����  - listPetsAllDrRecords.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�d���f�����  - listPetsAllDrRecords.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

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
		<th>�ݶE�����s��</th>
		<th>�ݶE�����ɶ�</th>
		<th>�ݶE�B��</th>
		<th>��ͽs��</th>
		<th>�d���W�r(�d���s��)</th>
		
		
		
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
<!-- 			     <input type="submit" value="�ק�"> -->
<%-- 			     <input type="hidden" name="petNo" value="${record.petNo}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do"> --%>
<!-- 			    <input type="submit" value="�R��"> -->
<%-- 			    <input type="hidden" name="petNo" value="${record.petNo}"> --%>
<!-- 			    <input type="hidden" name="action"value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>
