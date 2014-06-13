<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>

<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />

<%
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4003);
	List<PowVO> list = (List<PowVO>)session.getAttribute("list");
	
	
 %>
<html>
<head>
<title>IBM DrRecord: Home</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		


</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">

<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>
<body bgcolor='white'>


	<%@ include file="/menu1.jsp" %> 
<!-- 主要jsp開始 -->
<c:if test="<%=list.contains(powVO)%>"> 
<!-- <table border='1' cellpadding='5' cellspacing='0' width='400'> -->
<!--   <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'> -->
<!--     <td><h3>IBM DrRecord: Home</h3><font color=red>( MVC )</font></td> -->
<!--   </tr> -->
<!-- </table> -->

<!-- <p>This is the Home page for IBM DrRecord: Home</p> -->


<h3>資料查詢:</h3>
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
<!--   <li><a href='listAllDrRecord.jsp'>List</a> all DrRecord. </li> <br><br> -->
  

  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/backend/pet/pet.do" >
        <b>輸入會員編號 (如7002):</b>
        <input type="text" name="memNo" value="${memNo}">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getAll_For_Display_From_Member">
    </FORM>
  </li>
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/pet/pet.do" >
       <b>請選擇寵物:</b>
     
       <select size="1" name="petNo">
         <c:forEach var="petVO" items="${memPetVO}" > 
         	<option value="${petVO.petNo}">${petVO.petName}
         </c:forEach>   
       </select>
      
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display_From_PetNo">
    </FORM>
  </li>
</ul>






<!-- <h3>活動管理</h3> -->

<!-- <ul> -->
<!--   <li><a href='addDrRecord.jsp'>Add</a> a new DrRecord.</li> -->
<!-- </ul> -->

<!-- 主要jsp結束 -->
    
 </c:if>   
  <%@ include file="/menu2.jsp" %> 


</body>

</html>
