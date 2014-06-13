<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%@ page import="com.pow.model.*"%>
<%@ page import="com.drRecord.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
 	DrRecordVO drRecVO = (DrRecordVO) request.getAttribute("drRecVO");
	 


	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4003);
	List<PowVO> list = (List<PowVO>)session.getAttribute("list");
		
// 	String[] list2 = drRecVO.getDrRecPres().split(";");	
// 	String description = list2[0];
// 	String method = list2[1];
%>

<html>
<head>
<title>看診紀錄資料 - listAllDrRecord.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>

<script src="<%=request.getContextPath()%>/menu.js"></script>		

</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<%@ include file="/menu1.jsp" %> 
<c:if test="<%=list.contains(powVO)%>"> 
    
<!-- jsp主要開始 -->
<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>看診紀錄資料 - listAllDrRecord.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>看診紀錄:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/drRecord/drRecord.do" name="form1" enctype="multipart/form-data">
<table border="0">

	<tr>
		<td>寵物編號:</td>
		<td><input type="hidden" name="petNo" size="10" value="<%= request.getParameter("petNo") %>" /><%= request.getParameter("petNo") %></td>
	</tr>
	<tr>
		<%java.sql.Date drRecTime_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>看診日期:</td>
		<td>
		    <input type="hidden" size="9"  name="drRecTime" value="<%=drRecTime_SQL%>"><%=drRecTime_SQL%>
		</td>
	</tr>
	
	<tr>
		
		<td>看診描述和處置:</td>
		<td><textarea  name="drRecPres" rows="3" cols="30" value="<%= (drRecVO==null)? "" : drRecVO.getDrRecPres()%>" > 看診描述:
 看診處置:</textarea></td>
	</tr>
	
<!-- 	<tr> -->
<!-- 		<td>看診處置:</td> -->
<%-- 		<td><textarea  name="drRecMethod"  value="<%= (drRecVO==null)? "" : method%>" /><%= method %></textarea></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>醫生:</td>
		<jsp:useBean id="drSvc" scope="page" class="com.doctor.model.DoctorService" />
		<td>
<!-- 		<td><input type="TEXT" name="drNo" size="45" -->
<%-- 			value="<%= (shiftVO==null)? "100" : shiftVO.getDrNo()%>" /></td> --%>
	 <b>選擇醫師:</b>
       <select size="1" name="drNo">
         <c:forEach var="drVO" items="${drSvc.all}" > 
          <option value="${drVO.drNo}">${drVO.drName}
         </c:forEach>   
       </select>
       
       </td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<br>
<input type="submit" value="送出新增">

</FORM>

<!-- jsp主要結束 -->

    
    
 </c:if>   
    
  <%@ include file="/menu2.jsp" %> 



</body>

</html>
