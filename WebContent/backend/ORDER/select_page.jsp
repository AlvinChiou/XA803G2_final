<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<%@ page import="com.pow.model.*"%>
<%@ page import="com.doctor.model.*"%>
<%@ page import="java.util.* , com.product.model.*, com.order.model.*" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>查詢訂單</title>


<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/backend/js/jQueryDatetimepicker/jquery.simple-dtpicker.js"></script>
<link type="text/css" href="<%=request.getContextPath()%>/backend/js/jquery.simple-dtpicker.css" rel="stylesheet" />
</head>
<%
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4006);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
	 %>
<body>
<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>"> 


<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IPETCares : Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>
<br>
<h3>資料查詢:</h3><br>
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
<ul>
	<li><a href="listAllOrder.jsp">檢視所有訂單</a></li><br><br>
	<li>
		<form method="post" action="order.do">
			<b>請輸入訂單編號(例如:201404011001):</b>
			<input type="text" name="ordno">
			<input type="submit" value="送出查詢">
			<input type="hidden" name="action" value="getOne_for_Display">
		</form>
	</li>
	<jsp:useBean id="orderService" scope="page" class="com.order.model.OrderService"/>
<!-- 	<li> -->
<!-- 		<form method="post" action="order.do"> -->
<!-- 			<b>選擇訂單編號:</b> -->
<!-- 			<select name="ordno"> -->
<%-- 				<c:forEach var="orderVO" items="${orderService.all}"> --%>
<%-- 				<option  value="${orderVO.ordno}">${orderVO.ordno} --%>
<%-- 				</c:forEach> --%>
<!-- 			</select> -->
<!-- 			<input type="submit" value="送出查詢"> -->
<!-- 			<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 		</form> -->
<!-- 	</li> -->
</ul>
</c:if> 

<%@ include file="/menu2.jsp" %> 
</body>
</html>