<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.apt.model.*"%>
<%
AptVO aptVO = (AptVO) request.getAttribute("aptVO");
String shiftDate = request.getParameter("shiftDate");  //�Y�O�W�@���Ȥ�諸�Z����

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
<title>�����s�W - addApt.jsp</title>

<!-- �ۤv�s�W�� jquery DatePicker -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/>

<script>

		function fun1(){
	
		      with(document.form1){
		         if (aptPeriod.value=="0") {
		             alert("�п�ܱ����ɬq!");
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
		<h3>������Ʒs�W - addApt.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/backend/apt-Knifeman.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">�^�����޲z����</a>
	    </td>
	</tr>
</table>

<h3>��ƭ��u:</h3>
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

<FORM METHOD="post" ACTION="addApt2.jsp" name="form1">
<table border="0">
	
	<tr>
<%-- 		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%> --%>
		<td>�������:</td>

		<td>
				<input type="text"  id="datepicker5"  name = "aptDate"
				value="<%= (aptVO==null)? shiftDate : aptVO.getAptDate()%>" readonly/>
		</td>
		
	</tr>
	<tr>
		<td>�ɬq:</td>
		<td>
		<%=shiftPeriod2%>
			<select name = "aptPeriod">
				<option value = "0">�п�ܱ������ɬq</option>
			<%if( shiftPeriod2 != null && shiftPeriod2.equals("�W��") ) {%>
				 
				<option value = "0910">9:00~10:00</option>
				<option value = "1011">10:00~11:00</option>
				<option value = "1112">11:00~12:00</option>
			<%} else if( shiftPeriod2 != null && shiftPeriod2.equals("�U��")) {%> 
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
<input type="button" value="�U�@�B" onclick= "fun1()"/>
</FORM>

    <%@ include file="/menu2.jsp" %> 


</body>

</html>
