<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>
<%@ page import="com.doctor.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>


<jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService" />
<jsp:useBean id="drSvc" scope="page" class="com.doctor.model.DoctorService" /> 

<html>
<head>
<title>���-�d���f�Ҹ�� - listAllDrRecordByPetNo.jsp</title>

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
<!-- jsp�}�l -->
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���-�d���f�Ҹ�� - listAllDrRecordByPetNo.jsp</h3>
		<a href="<%=request.getContextPath()%>/backend/drRecord/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^�޲z�f�ҭ���</a>
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
<%--  "${memPetVO.memNo}" --%>
<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>�ݶE�����s��</th>
		<th>�ݶE�����ɶ�</th>
		<th>�ݶE�B��</th>
		<th>��ͽs��</th>
		<th>�d���s��</th>
		<th></th>
	</tr>
	
	<c:forEach var="record" items="${getRecordByPetNo}" >
	
	<c:set var = "petNo" value = "${record.petNo}" scope = "page"/>
		<tr align='center' valign='middle'>
			<td>${record.drRecNo}</td>
			<td>${record.drRecTime}</td>
			<td>${record.drRecPres}</td>
			<td>
			<c:forEach var="doctor" items="${drSvc.all}">
				<c:if test="${ record.drNo == doctor.drNo}">
					${doctor.drName}(${record.drNo})
				</c:if>
			</c:forEach>
			</td>
			<td>
			<c:forEach var="pet" items="${petSvc.all}">
				<c:if test="${ record.petNo ==pet.petNo}">
					${pet.petName}(${record.petNo})
				</c:if>
			</c:forEach>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/drRecord/drRecord.do">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="drRecNo" value="${record.drRecNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>

		</tr>
	</c:forEach>
		<tr>	
			
			  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/drRecord/addDrRecord.jsp">
			    <input type="hidden" name="petNo" value="${petNo}"> 
			    <input type="submit" value="�s�W�f�Ҹ��"> 
			    
			    </FORM>
			
		</tr>
</table>
<%-- <%@ include file="page2.file" %> --%>
<!-- jsp���� -->


    
    
    
 <%@ include file="/menu2.jsp" %> 



</body>
</html>
