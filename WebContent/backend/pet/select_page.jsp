<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%@ page import="com.pow.model.*"%>

<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4003);
	List<PowVO> list = (List<PowVO>)session.getAttribute("list");
 %>
<html>
<head><title>iPETCares Pet: Home</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>

<script src="<%=request.getContextPath()%>/menu.js"></script>		

</head>
<body bgcolor='white'>
	<%@ include file="/menu1.jsp" %> 
    
<!-- jsp開始 -->
<c:if test="<%=list.contains(powVO)%>"> 
<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IPETCares : Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM Pet: Home</p>

<h3>寵物管理:</h3>
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
  <li><a href='listAllPet.jsp'>瀏覽所有寵物</a></li> <br>
  <li><a href='addPet.jsp'>新增寵物</a></li>
<h3>複合查詢</h3>
<ul>  

  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do" name="form1">
        
        <b>輸入會員編號(如:7001):</b>
        <input type="text" name="memNo" value=""><br>
        
</ul>
           
<!--        <b>輸入職員編號:</b> -->
<!--        <input type="text" name="empNo" value="1003"><br> -->
       
<!--        <b>輸入員工職位:</b> -->
<!--        <input type="text" name="job" value="PRESIDENT"><br> -->
    
       <b>選擇寵物類別:</b>
       <select size="1" name="petType" >
          <option value="">
          <option value="dog">狗狗
          <option value="cat">貓咪
          
          
<%--          <c:forEach var="actVO" items="${actSvc.all}" >  --%>
<%--           <option value="${actVO.actStatus}">${actVO.actStatus} --%>
<%--          </c:forEach>    --%>
       </select><br>
           
<!--        <b>活動開始日期:</b> -->
<!-- 		    <input class="cal-TextBox" -->
<!-- 			onFocus="this.blur()" size="9" readonly type="text" name="actStartTime" value="1981-11-17"> -->
<!-- 			<a class="so-BtnLink" -->
<!-- 			href="javascript:calClick();return false;" -->
<!-- 			onmouseover="calSwapImg('BTN_date1', 'img_Date_OVER',true);" -->
<!-- 			onmouseout="calSwapImg('BTN_date1', 'img_Date_UP',true);" -->
<!-- 			onclick="calSwapImg('BTN_date1', 'img_Date_DOWN');showCalendar('form1','actStartTime','BTN_date1');return false;"> -->
<!-- 		    <img align="middle" border="0" name="BTN_date1"	src="images/btn_date_up.gif" width="22" height="17" alt="開始日期"></a> -->
		        
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listPets_ByCompositeQueryForBack">
     </FORM>
  </li>
</ul> 

<!-- <h3>寵物管理</h3> -->
<!-- <ul> -->
<!--   <li><a href='addPet.jsp'>Add</a> a new Pet.</li> -->
<!-- </ul> -->
<!-- jsp結束 -->

 <%@ include file="/menu2.jsp" %> 
</c:if>
</body>

</html>
