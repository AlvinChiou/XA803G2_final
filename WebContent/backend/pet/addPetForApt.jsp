<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pet.model.*"%>
<%
PetVO petVO = (PetVO) request.getAttribute("petVO");
%>

<html>
<head>
<title>�d����Ʒs�W - addPet.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>

<script src="<%=request.getContextPath()%>/menu.js"></script>		

</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<%@ include file="/menu1.jsp" %> 
<!-- jsp�}�l -->
<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�d����Ʒs�W</h3>
		</td>
		<td>
		   <a href="<%= request.getContextPath()%>/backend/pet/select_page.jsp"><img src="<%= request.getContextPath()%>/backend/pet/images/tomcat.gif" width="100" height="100" border="1">�^����</a>
	    </td>
	</tr>
</table>

<h3>�d�����:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/pet/pet.do" name="form1" enctype="multipart/form-data">
<table border="0">

	<tr>
		<td>�d���W�r:</td>
		<td><input type="TEXT" name="petName" size="45" 
			value="<%= (petVO==null)? "" : petVO.getPetName()%>" /></td>
	</tr>
	<tr>
		<td>�d���ʧO:</td>
		<td><input type="radio" name="petSex" value="boy" />�k��
			<input type="radio" name="petSex" value="girl" />�k��
		</td>
	</tr>
	<tr>
		<td>�d�����O:</td>
		<td><input type="radio" name="petType" value="dog" />����
			<input type="radio" name="petType" value="cat" />�߫}
		</td>
	</tr>
	<tr>
		<td>�d���Ӥ�:</td>
		<td><input type="file" name="petPic" size="45"
			value="<%= (petVO==null)? "" : petVO.getPetPic()%>" /></td>
	</tr>
	<tr>
		<td>�d���~��:</td>
		<td><input type="TEXT" name="petAge" size="45"
			value="<%= (petVO==null)? "" : petVO.getPetAge()%>" /></td>
	</tr>
	<tr>
		<td>�|���s��:</td>
		<td >
			<input type = "hidden" name="memNo" 
			value = "<%= (petVO==null)? (Integer)request.getAttribute("memNoFromAuto") : petVO.getMemNo()%>" />
	  		 <%= (petVO==null)? (Integer)request.getAttribute("memNoFromAuto") : petVO.getMemNo()%>
	   </td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="insert">
<br>
<input type="submit" value="�s�W�d��">

</FORM>

<!-- jsp���� -->


    
  <%@ include file="/menu2.jsp" %> 

</body>

</html>
