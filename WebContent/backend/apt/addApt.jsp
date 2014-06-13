<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.apt.model.*"%>
<%
AptVO aptVO = (AptVO) request.getAttribute("aptVO");
String shiftDate = request.getParameter("shiftDate");  //即是上一頁客戶選的班表日期

 request.setCharacterEncoding("Big5");
 String shiftPeriod = request.getParameter("shiftPeriod");
 String shiftPeriod2 = null;
 if( shiftPeriod != null ){
 	shiftPeriod2 = new String( shiftPeriod.getBytes("ISO-8859-1"), "UTF-8" );
 } else {
	 shiftPeriod2 = (String)request.getAttribute("shiftPeriod");
 }
%>

<html>
<head>
<title>掛號新增 - addApt.jsp</title>

<!-- 自己新增的 jquery DatePicker -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/>

<script>

		function fun1(){
	
		      with(document.form1){
		         if (aptPeriod.value=="0") {
		             alert("請選擇掛號時段!");
		         }
		         else {
		             submit();
		         }
		      }
		   }


		$(function() {
		
			$("#datepicker5").datepicker({
				showOn : "button",
				buttonImage : "images/calendar.gif",
				buttonImageOnly : true,
				minDate: 0,
				maxDate: "+19D",
				dateFormat: "yy-mm-dd"
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
<body bgcolor='white'>
    <%@ include file="/menu1.jsp" %> 

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>掛號資料新增 - addApt.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/backend/apt-Knifeman.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回掛號管理首頁</a>
	    </td>
	</tr>
</table>

<h3>資料員工:</h3>
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

<FORM METHOD="post" ACTION="addApt2.jsp" name="form1">
<table border="0">
	
	<tr>
<%-- 		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%> --%>
		<td>掛號日期:</td>

		<td>
				<input type="text"  id="datepicker5"  name = "aptDate"
				value="<%= (aptVO==null)? shiftDate : aptVO.getAptDate()%>" readonly/>
		</td>
		
	</tr>
	<tr>
		<td>時段:</td>
		<td>
		<%=shiftPeriod2%>
			<select name = "aptPeriod">
				<option value = "0">請選擇欲掛號時段</option>
			<%if( shiftPeriod2 != null && shiftPeriod2.equals("上午") ) {%>
				 
				<option value = "0910">9:00~10:00</option>
				<option value = "1011">10:00~11:00</option>
				<option value = "1112">11:00~12:00</option>
			<%} else if( shiftPeriod2 != null && shiftPeriod2.equals("下午")) {%> 
				<option value = "1314">13:00~14:00</option>
				<option value = "1415">14:00~15:00</option>
				<option value = "1516">15:00~16:00</option>
				<option value = "1617">16:00~17:00</option>
				<%}%>
			</select>
		</td>
	</tr>
	
	<jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService" />
	



</table>
<br>
<input type="button" value="下一步" onclick= "fun1()"/>
</FORM>

    <%@ include file="/menu2.jsp" %> 


</body>

</html>
