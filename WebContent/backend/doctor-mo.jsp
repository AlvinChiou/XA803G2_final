<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.*"%>

<%@ page import="com.pow.model.*"%>

<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService" />
<%
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4004);
	List<PowVO> list = (List<PowVO>)session.getAttribute("list");
 %>
<html>
<head><title>IBM Dr: Home</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		

</head>
<body bgcolor='white'>
	<%@ include file="/menu1.jsp" %> 

<c:if test="<%=list.contains(powVO)%>"> 
<!-- jsp開始 -->
<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>iPETCares Dr: Home</h3></td>
  </tr>
</table>

<br>
<h3>醫師資料管理:</h3>
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
  <li><a href='<%=request.getContextPath()%>/backend/dr/listAllDr.jsp'>所有醫師列表</a></li> 
  <br>
  <li><a href='<%=request.getContextPath()%>/backend/dr/addDr.jsp'>新增醫師資料</a></li>
</ul>


<!-- <h3>醫師管理</h3> -->

<!-- <ul> -->
<%--   <li><a href='<%=request.getContextPath()%>/backend/dr/addDr.jsp'>Add</a> a new Dr.</li> --%>
<!-- </ul> -->
<!-- jsp結束 -->

    </c:if>
    
    
   
    <%@ include file="/menu2.jsp" %> 
    


</body>

</html>
