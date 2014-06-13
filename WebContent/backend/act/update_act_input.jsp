<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.act.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	ActVO fromListActsByUpdate = (ActVO)request.getAttribute("fromListActsByUpdate"); //EmpServlet.java (Concroller), �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4001);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
// 	 	System.out.print("actVO1 In JSP:" + fromListActsByUpdate);
// 	ActVO actVO1 = actVO;
// 	 System.out.println("aaa:"+updateFromListAll.getActNo());
%>
<html>
<head>
<title>���-���ʸ�ƭק� - update_emp_input.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<title>�e��-���ʷs�W</title>

</head>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/front/act/js/calendar.css">
<script language="JavaScript" src="<%= request.getContextPath()%>/front/act/js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>
			<%@ include file="/menu1.jsp" %> 

<c:if test="<%=listPower.contains(powVO)%>"> 

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���-���ʸ�ƭק� </h3>
		<a href="<%=request.getContextPath()%>/backend/act/select_page.jsp"><img src="<%= request.getContextPath()%>/front/act/images/back1.gif" width="100" height="32" border="0">�^����</a></td>
	</tr>
</table>

<h3>��ƭק�:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do" name="form1" enctype="multipart/form-data">
<table border="0">
	<tr>
		<td>���ʽs��:<font color=red><b>*</b></font></td>
		<td><%=fromListActsByUpdate.getActNo()%></td>
	</tr>
	<tr>
		<td>���ʦW��:</td>
		<td><input type="hidden" name="actName" size="45" value="<%=fromListActsByUpdate.getActName()%>" /><%=fromListActsByUpdate.getActName()%></td>
	</tr>
	<tr>
		<td>���ʤ��e:</td>
		<td><input type="hidden" name="actContent" size="45"	value="<%=fromListActsByUpdate.getActContent()%>" /><%=fromListActsByUpdate.getActContent()%></td>
	</tr>
	<tr>
		<td>�}�l�ɶ�:</td>
		<td>
		    <input  size="18" type="hidden" name="actStartTime" value="<%= fromListActsByUpdate.getActStartTime()%>" /><%= fromListActsByUpdate.getActStartTime()%>
		</td>
	</tr>
	<tr>
		<td>�����ɶ�:</td>
		<td >
		    <input size="18" type="hidden" name="actEndTime" value="<%=fromListActsByUpdate.getActEndTime()%>" /><%=fromListActsByUpdate.getActEndTime()%>
		</td>
	</tr>
	
		<input type="hidden" name="actPic" value="<%=fromListActsByUpdate.getActPic()%>" size="45" readonly />
	
	<tr>
		<td>���ɾ���:</td>
		<td><textarea  name="actEquipment" rows="3" cols="30" value="<%=fromListActsByUpdate.getActEquipment()%>"  />�L</textarea></td>
	</tr>
	<tr>
		<td>����O��:</td>
		<td><input type="hidden" name="actDeposit" size="45" value="<%=fromListActsByUpdate.getActDeposit()%>" /><%=fromListActsByUpdate.getActDeposit()%></td>
	</tr>
	<tr>
		<td>�D���ú��O��:</td>
		<td><input type="hidden" name="actHostFee" size="45" value="<%=fromListActsByUpdate.getActHostFee()%>" /><%=fromListActsByUpdate.getActHostFee()%></td>
	</tr>
	<tr>
		<td>���ʳ��W�O��:</td>
		<td><input type="hidden" name="actRegFee" size="45" value="<%=fromListActsByUpdate.getActRegFee()%>" /><%=fromListActsByUpdate.getActRegFee()%></td>
	</tr>
	<tr >
		<td>�ثe���ʪ��A:</td>
		<td>
			<select size="1" name="actStatus">
			<c:if test="${fromListActsByUpdate.actStatus=='N'}">
				<option value="Y">�f�ֳq�L
				<option value="N">�f�֥��q�L
			</c:if>
			
			<c:if test="${fromListActsByUpdate.actStatus=='A'}">
				<option value="B">�f�ֳq�L(�p�H����)
				<option value="A">�f�֥��q�L(�p�H����)
			</c:if>
			
			<c:if test="${fromListActsByUpdate.actStatus=='Y'}">
				<option value="N">���ʭ��s�f��
			</c:if>
			
			<c:if test="${fromListActsByUpdate.actStatus=='B'}">
				<option value="A">���ʭ��s�f��(�p�H����)
			</c:if>
			</select>
		</td>
		
	</tr>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />	
	<tr>
		<td>�D���|���s��:</td>
		<td>
			<input type="hidden" name="memNo" size="45" value="<%=fromListActsByUpdate.getMemNo()%>" />
				<c:forEach var="memVO" items="${memSvc.all}">
					<c:if test="${fromListActsByUpdate.memNo == memVO.memno}">
						${memVO.memname}
					</c:if>
				</c:forEach>
		</td>
	</tr>
	<tr>
		<td>�f��¾���s��:</td>
		<td><input type="hidden" name="empNo" size="45" value="<%=fromListActsByUpdate.getEmpNo()%>" /><%=fromListActsByUpdate.getEmpNo()%></td>
	</tr>
	


</table>
<br>
<input type="hidden" name="action" value="updateForBack">
<input type="hidden" name="actNo" value="<%=fromListActsByUpdate.getActNo()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
<input type="submit" value="�T�w�ק�"></FORM>


			<%@ include file="/menu2.jsp" %> 

</c:if>
</body>
</html>
