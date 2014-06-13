<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%@ page import="com.pow.model.*"%>
<%@ page import="com.doctor.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />

<%
	DoctorVO doctorVO = (DoctorVO) request.getAttribute("doctorVO");

	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4004);
	List<PowVO> list = (List<PowVO>)session.getAttribute("list");
%>

<html>
<head>
<title>��v��Ʒs�W - addDr.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />

<script>
	$(function() {
			
		$("#datepicker3").datepicker({
			defaultDate:(new Date()),
			dateFormat:"yy-mm-dd",
			showOn : "button",
			buttonImage : "images/calendar.gif",
			buttonImageOnly : true,
			yearRange:"-90:+0",
			changeMonth: true,
			changeYear: true
		});
	});
</script>

<script>
function miracle() {
	document.getElementsByName("drName")[0].value = "xa803g2";
	document.getElementsByName("drExp")[0].value = "�����j�ǾǤh����Ǩt �����j�Ǫ��]�ʪ���|����D�v��v";
	document.getElementsByName("drAdd")[0].value = "��鿤���c�����j��300-2��";
	document.getElementsByName("drTel")[0].value = "0933568569";

}
</script>

</head>

<body bgcolor='white'>
<%@ include file="/menu1.jsp" %> 
<c:if test="<%=list.contains(powVO)%>"> 
	<input type = "button" id = "btn" value = "���_�p���s" onclick = "miracle()">
<!-- jsp�}�l -->
	<table border='1' cellpadding='5' cellspacing='0' width='570'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>��v��Ʒs�W</h3>
			</td>
			<td><a href="<%=request.getContextPath()%>/backend/doctor-mo.jsp"><img src="<%=request.getContextPath()%>/backend/dr/images/tomcat.gif" width="100" height="100" border="1">�^����</a>
			</td>
		</tr>
	</table>

	<h3>��v���:</h3>
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

	<FORM METHOD="post" ACTION="dr.do" name="form1" enctype="multipart/form-data">
		<table border="0" width='500' height='600'>

			<tr>
				<td nowrap>��v�m�W:</td>
				<td><input type="TEXT" name="drName" size="45" value="<%=(doctorVO == null) ? "" : doctorVO.getDrName()%>" /></td>
			</tr>
			<tr>
				<td>�Ǹg��:</td>
				<td>
					<textarea name="drExp" rows="15" cols="55" value="<%=(doctorVO == null) ? "" : doctorVO.getDrExp()%>" ></textarea>
				</td>
			</tr>
			<tr>
				<td>�ʧO:</td>
				<td>
					<input type="radio" name="drSex" size="2" value="<%=(doctorVO == null) ? "M" : doctorVO.getDrSex()%>" checked='' />�k
					<input type="radio" name="drSex" size="2" value="<%=(doctorVO == null) ? "F" : doctorVO.getDrSex()%>" />�k
				</td>
			</tr>
			<tr>
				<td>�Ӥ�:</td>
				<td><input type="file" name="drPic" size="45" value="<%=(doctorVO == null) ? "" : doctorVO.getDrPic()%>" /></td>
			</tr>
			<tr>
				<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
				<td nowrap>�X�ͦ~���:</td>
				<td>
				    <input type="text" id="datepicker3" name="drBirth" value="<%=(doctorVO == null) ? date_SQL : doctorVO.getDrBirth()%>" />
				</td>
			</tr>
			<tr>
				<td>��}:</td>
				<td><input type="TEXT" name="drAdd" size="45" value="<%=(doctorVO == null) ? "" : doctorVO.getDrAdd()%>" /></td>
			</tr>
			<tr>
				<td>�q��:</td>
				<td><input type="TEXT" name="drTel" size="45" value="<%=(doctorVO == null) ? "" : doctorVO.getDrTel()%>" /></td>
			</tr>
			


		 </table>
		<br> 
		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="�e�X�s�W">
	</FORM>
	
	<!-- jsp���� -->
    
</c:if>    
    
<%@ include file="/menu2.jsp" %> 
</body>

</html>
