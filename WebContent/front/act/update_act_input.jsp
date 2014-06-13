<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.act.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%
	ActVO actVO = (ActVO)request.getAttribute("actVO1"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
// 	System.out.print("actVO1:" + actVO);
%>
<html>
<head>
<title>前端-活動修改修改 - update_emp_input.jsp</title></head>
<link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" />
<link href="<%= request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css" />
<link href="<%= request.getContextPath()%>/css/box_css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%= request.getContextPath()%>/js/jquery-1.4.2.js"></script>  
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/jquery.fancybox-1.3.4.css" media="screen" />
	
<script type="text/javascript">
	$(document).ready(function() {
		/*
		*   Examples - images
		*/

		$("a#example1").fancybox();
		$("#various3").fancybox({
			'width'				: '75%',
			'height'			: '75%',
			'autoScale'			: false,
			'transitionIn'		: 'none',
			'transitionOut'		: 'none',
			'type'				: 'iframe'
		});
	});
</script>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/front/act/js/calendar.css">
<script language="JavaScript" src="<%= request.getContextPath()%>/front/act/js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<div id="wrapper">
  		<div id="main_bg">
    	<div id="main_bg01">
        	<div id="apDiv13"></div>
			<c:if test="${memVO == null}"><%@ include file="/header.jsp" %></c:if>
        	<c:if test="${memVO != null}"><%@ include file="/loginHeader.jsp" %></c:if>
			<%@ include file="/ad.jsp" %>   
  		</div>
		</div>

<a id="titleimg"><img src="<%= request.getContextPath()%>/images/main_tit03.png"></a>
<br>
<p style="text-align:right; margin-right:50px; color:orange;">目前位置：<a href="<%=request.getContextPath()%>/iPETCares.jsp">首頁</a>>><a href="<%=request.getContextPath()%>/front/act/listAllAct.jsp">所有活動資訊</a></p>
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

<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/front/act/act.do" name="form1" enctype="multipart/form-data">
<table border="0" align='center'>
<h3 style="margin-left:120px;">資料修改:</h3>
	<tr>
		<td>活動編號:<font color=red><b>*</b></font></td>
		<td><%=actVO.getActNo()%></td>
	</tr>
	<tr>
		<td>活動名稱:</td>
		<td><input type="TEXT" name="actName" size="45" value="<%=actVO.getActName()%>" /></td>
	</tr>
	<tr>
		<td>活動內容:</td>
		<td><textarea name="actContent" rows="10" cols="50"value="<%=actVO.getActContent()%>" /><%=actVO.getActContent()%></textarea></td>
	</tr>
	<tr>
		<td>開始時間:</td>
		<td>
		    <input size="18" readonly type="hidden" name="actStartDate" value="<%= actVO.getActStartTime().toString().substring(0,19)%>"><%= actVO.getActStartTime().toString().substring(0,19)%>
		</td>
	</tr>
	<tr>
		<td>結束時間:</td>
		<td>
		    <input size="18" readonly type="hidden" name="actEndDate" value="<%=actVO.getActEndTime().toString().substring(0,19)%>"><%=actVO.getActEndTime().toString().substring(0,19)%>
			
		</td>
	</tr>
	<tr>
		<td>活動照片:</td>
		<td><input type="file" name="actPic" size="45" /></td>
	</tr>
	<tr>
		<td>租借器材:</td>
		<td><textarea name="actEquipment" rows="3"  cols="30"value="<%=actVO.getActEquipment()%>" /><%=actVO.getActEquipment()%></textarea></td>
	</tr>
	<tr>
		<td>押金費用:</td>
		<td><input type="hidden" name="actDeposit" size="45" value="<%=actVO.getActDeposit()%>" /><%=actVO.getActDeposit()%></td>
	</tr>
	<tr>
		<td>主辦方繳交費用:</td>
		<td><input type="hidden" name="actHostFee" size="45" value="<%=actVO.getActHostFee()%>" /><%=actVO.getActHostFee()%></td>
	</tr>
	<tr>
		<td>活動報名費用:</td>
		<td><input type="TEXT" name="actRegFee" size="45" value="<%=actVO.getActRegFee()%>" /></td>
	</tr>
	<tr>
		<td>
		<input type="hidden" name="actStatus" size="45" value="N" readonly/>
		</td>
	</tr>
	<tr>
		<td>主辦方會員編號:</td>
		<td><input type="hidden" name="memNo" size="45" value="<%=actVO.getMemNo()%>" /><%=memVO.getMemname()%></td>
	</tr>
	<tr>
		
		<td><input type="hidden" name="empNo" size="45" value="<%=actVO.getEmpNo()%>" /></td>
	</tr>
	<tr>
		<td>
		<input type="submit" value="確定修改">
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="actNo" value="<%=actVO.getActNo()%>">
		<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
		</td>
	</tr>
</table>
</FORM>
<br>

	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
<br>

<!-- <br>送出修改的來源網頁路徑:<br><b> -->
<%--    <font color=blue>request.getParameter("requestURL"):</font> <%= request.getParameter("requestURL")%><br> --%>
</body>
</html>
