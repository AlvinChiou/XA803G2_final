<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.act.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	ActVO fromListActsByUpdate = (ActVO)request.getAttribute("fromListActsByUpdate"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4001);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
// 	 	System.out.print("actVO1 In JSP:" + fromListActsByUpdate);
// 	ActVO actVO1 = actVO;
// 	 System.out.println("aaa:"+updateFromListAll.getActNo());
%>
<html>
<head>
<title>後端-活動資料修改 - update_emp_input.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<title>前端-活動新增</title>

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
		<h3>後端-活動資料修改 </h3>
		<a href="<%=request.getContextPath()%>/backend/act/select_page.jsp"><img src="<%= request.getContextPath()%>/front/act/images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do" name="form1" enctype="multipart/form-data">
<table border="0">
	<tr>
		<td>活動編號:<font color=red><b>*</b></font></td>
		<td><%=fromListActsByUpdate.getActNo()%></td>
	</tr>
	<tr>
		<td>活動名稱:</td>
		<td><input type="hidden" name="actName" size="45" value="<%=fromListActsByUpdate.getActName()%>" /><%=fromListActsByUpdate.getActName()%></td>
	</tr>
	<tr>
		<td>活動內容:</td>
		<td><input type="hidden" name="actContent" size="45"	value="<%=fromListActsByUpdate.getActContent()%>" /><%=fromListActsByUpdate.getActContent()%></td>
	</tr>
	<tr>
		<td>開始時間:</td>
		<td>
		    <input  size="18" type="hidden" name="actStartTime" value="<%= fromListActsByUpdate.getActStartTime()%>" /><%= fromListActsByUpdate.getActStartTime()%>
		</td>
	</tr>
	<tr>
		<td>結束時間:</td>
		<td >
		    <input size="18" type="hidden" name="actEndTime" value="<%=fromListActsByUpdate.getActEndTime()%>" /><%=fromListActsByUpdate.getActEndTime()%>
		</td>
	</tr>
	
		<input type="hidden" name="actPic" value="<%=fromListActsByUpdate.getActPic()%>" size="45" readonly />
	
	<tr>
		<td>租借器材:</td>
		<td><textarea  name="actEquipment" rows="3" cols="30" value="<%=fromListActsByUpdate.getActEquipment()%>"  />無</textarea></td>
	</tr>
	<tr>
		<td>押金費用:</td>
		<td><input type="hidden" name="actDeposit" size="45" value="<%=fromListActsByUpdate.getActDeposit()%>" /><%=fromListActsByUpdate.getActDeposit()%></td>
	</tr>
	<tr>
		<td>主辦方繳交費用:</td>
		<td><input type="hidden" name="actHostFee" size="45" value="<%=fromListActsByUpdate.getActHostFee()%>" /><%=fromListActsByUpdate.getActHostFee()%></td>
	</tr>
	<tr>
		<td>活動報名費用:</td>
		<td><input type="hidden" name="actRegFee" size="45" value="<%=fromListActsByUpdate.getActRegFee()%>" /><%=fromListActsByUpdate.getActRegFee()%></td>
	</tr>
	<tr >
		<td>目前活動狀態:</td>
		<td>
			<select size="1" name="actStatus">
			<c:if test="${fromListActsByUpdate.actStatus=='N'}">
				<option value="Y">審核通過
				<option value="N">審核未通過
			</c:if>
			
			<c:if test="${fromListActsByUpdate.actStatus=='A'}">
				<option value="B">審核通過(私人活動)
				<option value="A">審核未通過(私人活動)
			</c:if>
			
			<c:if test="${fromListActsByUpdate.actStatus=='Y'}">
				<option value="N">活動重新審核
			</c:if>
			
			<c:if test="${fromListActsByUpdate.actStatus=='B'}">
				<option value="A">活動重新審核(私人活動)
			</c:if>
			</select>
		</td>
		
	</tr>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />	
	<tr>
		<td>主辦方會員編號:</td>
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
		<td>審核職員編號:</td>
		<td><input type="hidden" name="empNo" size="45" value="<%=fromListActsByUpdate.getEmpNo()%>" /><%=fromListActsByUpdate.getEmpNo()%></td>
	</tr>
	


</table>
<br>
<input type="hidden" name="action" value="updateForBack">
<input type="hidden" name="actNo" value="<%=fromListActsByUpdate.getActNo()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
<input type="submit" value="確定修改"></FORM>


			<%@ include file="/menu2.jsp" %> 

</c:if>
</body>
</html>
