<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>
<%@ page import="com.doctor.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>


<jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService" />
<jsp:useBean id="drSvc" scope="page" class="com.doctor.model.DoctorService" /> 

<html>
<head>
<title>後端-寵物病例資料 - listAllDrRecordByPetNo.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		

</head>
<body bgcolor='white'>
<%@ include file="/menu1.jsp" %> 


<!--     <h2>動物留言管理</h2> -->
<!--     <div id="search_bar"> -->
<!--       <select name="xxx"> -->
<!--         <option value="111">111</option> -->
<!--         <option value="222">222</option> -->
<!--         <option value="333">333</option> -->
<!--       </select> -->
<!--       <input type="text" name="zzz" id="zzz" /> -->
<!--       <a href="#" class="btn">搜尋</a> </div> -->
    <!-- end #search_bar -->
<!-- jsp開始 -->
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>後端-寵物病例資料 - listAllDrRecordByPetNo.jsp</h3>
		<a href="<%=request.getContextPath()%>/backend/drRecord/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回管理病例首頁</a>
		</td>
	</tr>
</table>

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
<%--  "${memPetVO.memNo}" --%>
<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>看診紀錄編號</th>
		<th>看診紀錄時間</th>
		<th>看診處方</th>
		<th>醫生編號</th>
		<th>寵物編號</th>
		<th></th>
	</tr>
	
	<c:forEach var="record" items="${getRecordByPetNo}" >
	
	<c:set var = "petNo" value = "${record.petNo}" scope = "page"/>
		<tr align='center' valign='middle'>
			<td>${record.drRecNo}</td>
			<td>${record.drRecTime}</td>
			<td>${record.drRecPres}</td>
			<td>
			<c:forEach var="doctor" items="${drSvc.all}">
				<c:if test="${ record.drNo == doctor.drNo}">
					${doctor.drName}(${record.drNo})
				</c:if>
			</c:forEach>
			</td>
			<td>
			<c:forEach var="pet" items="${petSvc.all}">
				<c:if test="${ record.petNo ==pet.petNo}">
					${pet.petName}(${record.petNo})
				</c:if>
			</c:forEach>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/drRecord/drRecord.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="drRecNo" value="${record.drRecNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>

		</tr>
	</c:forEach>
		<tr>	
			
			  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/drRecord/addDrRecord.jsp">
			    <input type="hidden" name="petNo" value="${petNo}"> 
			    <input type="submit" value="新增病例資料"> 
			    
			    </FORM>
			
		</tr>
</table>
<%-- <%@ include file="page2.file" %> --%>
<!-- jsp結束 -->


    
    
    
 <%@ include file="/menu2.jsp" %> 



</body>
</html>
