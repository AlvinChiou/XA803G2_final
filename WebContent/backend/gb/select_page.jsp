<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.gb.model.*"%>
<%  GbVO gbVO = (GbVO)request.getAttribute("gbVO"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>  
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		

<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>

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

</head>

<body bgcolor='#EFF6FF'>
	<%@ include file="/menu1.jsp" %> 

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Gb: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>


<h3>���ܤ峹�d���޲z</h3>

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

<!-- <ul> -->
<%-- 	<li><a href='<%= request.getContextPath()%>/backend/gb/listAllGb.jsp'>��ܩҦ��d��</a></li> <br><br> --%>
	
<!-- 	<li> -->
<%-- 		<form method="post" action="<%= request.getContextPath()%>/backend/gb/gb.do"> --%>
<!-- 			<b>��J�|���s��(�p7001):</b> -->
<!-- 			<input type="text" name="memno"> -->
<!-- 			<input type="submit" value="�e�X"> -->
<!-- 			<input type="hidden" name="action" value="listGb_Bymemno"> -->
<!-- 		</form> -->
<!-- 	</li> -->
	
<!-- 	<li> -->
<%-- 		<form method="post" action="<%= request.getContextPath()%>/backend/gb/gb.do"> --%>
<!-- 			<b>��J���ܤ峹�s��(�p5001):</b> -->
<!-- 			<input type="text" name="lostno"> -->
<!-- 			<input type="submit" value="�e�X"> -->
<!-- 			<input type="hidden" name="action" value="listGb_Bylostno"> -->
<!-- 		</form> -->
<!-- 	</li> -->
<!-- </ul> -->
<ul>	
	<li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/gb/gb.do" name="form1">
        <b><font color=blue>�ƦX�d��:</font></b> <br>
        <b>��J�|���s��(�p7001):</b>
        <input type="text" name="memno" value=""><br>
           
       <b>��J���ܤ峹�s��(�p5001):</b>
       <input type="text" name="lostno" value=""><br>
       
       <b>�d�����:</b>
       		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		    <input type="text" id="datepicker3" name="gbtime" value="<%= (gbVO==null)? "" : gbVO.getGbtime()%>" />
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="listGbs_ByCompositeQuery">
     </FORM>
  </li>
</ul>

	<%@ include file="/menu2.jsp" %> 

</body>
</html>