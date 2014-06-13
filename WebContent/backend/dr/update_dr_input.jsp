<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.doctor.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<%@ page import="com.doctor.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	DoctorVO doctorVO = (DoctorVO) request.getAttribute("doctorVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)

	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4004);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>
<html>
<head>


<title>醫師資料修改 - update_dr_input.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<%-- <script src="<%=request.getContextPath()%>/menu.js"></script>		 --%>
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

$(function(){
	$('#menu').children('li').click(function(){
		$('#menu').find('ul').removeClass('active');
		$(this).find('ul').toggleClass('active');	
	});
});
</script>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>
	<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>">  
<!-- jsp開始 -->
	<table border='1' cellpadding='5' cellspacing='0' width='800'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>醫師資料修改</h3> 
				<a href="<%=request.getContextPath()%>/backend/doctor-mo.jsp"><img src="<%= request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
		</tr>
	</table>
<br>
	<h3>醫生資料修改:</h3>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>

	<FORM METHOD="post" ACTION="dr.do" name="form1" enctype="multipart/form-data">
		<table border="0" width='800' align='left'>
			<tr>
				<td>醫師編號:<font color=red><b>*</b></font></td>
				<td><%=doctorVO.getDrNo()%></td>
			</tr>
			<tr>
				<td>醫師姓名:</td>
				<td><input type="TEXT" name="drName" size="45" value="<%=doctorVO.getDrName()%>" /></td>
			</tr>
			<tr>
				<td>學經歷:</td>
				<td>
					<textarea name="drExp" rows="15" cols="55" value="<%=doctorVO.getDrExp()%>" ><%=doctorVO.getDrExp()%></textarea>
				</td>
			</tr>
			<tr>
				<td>性別:</td>
				<td>
					<input type="hidden" name="drSex" size="1" value="<%=(doctorVO==null)? "M" : doctorVO.getDrSex() %>" />
					<c:if test="${doctorVO.drSex=='F'}">女</c:if>
					<c:if test="${doctorVO.drSex=='M'}">男</c:if>
				</td>
			</tr>
			<tr>
				<td>照片:</td>
				<td><c:if test="${doctorVO.drPic!=null}"><img src="<%= request.getContextPath()%>/dr/DBGifReader2?drNo=${doctorVO.drNo}"></c:if>
					<c:if test="${doctorVO.drPic==null}"><img src="images/nopic.jpg"></c:if>
				</td>
			</tr>
	        <tr>
	         	<td></td>
				<td><input type="file" name="drPic" size="45" value="<%=doctorVO.getDrPic()%>" /></td>
			</tr>
	        <tr>
				<td>出生年月日:</td>
				<td>
				<input type="hidden" name="drBirth" value="<%=doctorVO.getDrBirth()%>" /><%=doctorVO.getDrBirth()%>				
				</td>
			</tr>
			<tr>
				<td>住址:</td>
				<td><input type="TEXT" name="drAdd" size="75" value="<%=doctorVO.getDrAdd()%>" /></td>
			</tr>
			<tr>
				<td>電話:</td>
				<td><input type="TEXT" name="drTel" size="35" value="<%=doctorVO.getDrTel()%>" /></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="送出修改">
				</td>
			</tr>
		</table>
		<br> 
		<input type="hidden" name="action" value="update"> 
		<input type="hidden" name="drNo" value="<%=doctorVO.getDrNo()%>">
		<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
		<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
	</FORM>

<!-- <br>送出修改的來源網頁路徑:<br><b> -->
<%--    <font color=blue>request.getParameter("requestURL"):</font> <%= request.getParameter("requestURL")%><br> --%>
<%--    <font color=blue>request.getParameter("whichPage"):</font> <%= request.getParameter("whichPage")%> (此範例目前只用於:istAllEmp.jsp))</b> --%>
<!-- jsp結束 -->
</c:if>
   	<%@ include file="/menu2.jsp" %> 


</body>
</html>
