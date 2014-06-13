<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.drRecord.model.*"%>
<%@ page import="com.pet.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<%@ page import="com.drRecord.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService" />
<%
	DrRecordVO drRecVO = (DrRecordVO) request.getAttribute("drRecVO"); //EmpServlet.java (Concroller), �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)

	PetVO petVO = (PetVO) request.getAttribute("petVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
	
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4003);
	List<PowVO> list = (List<PowVO>)session.getAttribute("list");
%>
<html>
<head>
<title>�N�E�����ק� - update_drRecord_input.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<%@ include file="/menu1.jsp" %> 
<c:if test="<%=list.contains(powVO)%>"> 
<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�N�E�����ק� - update_drRecord_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></td>
	</tr>
</table>

<h3>�N�E�����ק� :</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/drRecord/drRecord.do" name="form1">
<table border="0" width='400'>
	<tr>
		<td>�ݶE�����s��:<font color=red><b>*</b></font></td>
		<td><%=drRecVO.getDrRecNo()%></td>
	</tr>
	<tr>
		<td>�d���W�r:</td>
		<td>
			<input type="hidden" name="petNo" value="<%=drRecVO.getPetNo()%>" />
			<c:forEach var="pet" items="${petSvc.all}">
				<c:if test="${pet.petNo == drRecVO.petNo}">${pet.petName}</c:if>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<td>�ݶE�ɶ�:</td>
		<td>
		    <input type="hidden" name="drRecTime" value="<%=drRecVO.getDrRecTime()%>"><%=drRecVO.getDrRecTime()%>
		</td>
	</tr>
	<tr>
		<td>�ݶE�B��:</td>
		<td><textarea type="TEXT" name="drRecPres" cols='30' rows='10' value="<%=drRecVO.getDrRecPres()%>" ><%=drRecVO.getDrRecPres()%></textarea></td>
	</tr>
	<tr>
		<jsp:useBean id="drSvc" scope="page" class="com.doctor.model.DoctorService" />
		<td><b>�����v:</b></td>
		<td><input type="hidden" name="drNo" value="<%= (drRecVO==null)? "�ڤڰ�" : drRecVO.getDrNo()%>" />
			<c:forEach var="drVO" items="${drSvc.all}">
				<c:if test="${drVO.drNo == drRecVO.drNo}">${drVO.drName}</c:if>
			</c:forEach>
		</td>
	
<!--        <select size="1" name="drNo"> -->
<%--          <c:forEach var="drVO" items="${drSvc.all}" >  --%>
<%--           <option value="${drVO.drNo}">${drVO.drName} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
       
       </td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="drRecNo" value="<%=drRecVO.getDrRecNo()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</c:if>
<%@ include file="/menu2.jsp" %> 

</body>
</html>
