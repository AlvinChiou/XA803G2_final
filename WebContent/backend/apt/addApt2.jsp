<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.apt.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- aptDateByParameter:<%=request.getParameter("aptDate")%> --%>
<%-- aptPeriodByParameter:<%=request.getParameter("aptPeriod")%> --%>

<%-- aptDateByAttribute:<%=(String)request.getAttribute("aptDate")%> --%>
<%-- aptPeriodByAttribute:<%=(String)request.getAttribute("aptPeriod")%> --%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		
<%
	if (request.getParameter("aptDate") != null && request.getParameter("aptPeriod") != null) {
		request.getSession().setAttribute( "aptDate", request.getParameter("aptDate"));
		request.getSession().setAttribute( "aptPeriod", request.getParameter("aptPeriod"));
	}

	

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>


<script>

		function fun1(){
	
		      with(document.form1){
		         if (	memNo.value== "" ) {
		             alert("請輸入會員編號!");
		         }
		         else {
		             submit();
		         }
		      }
		   }
		function fun2(){
			
		      with(document.form2){
		         if (	petNo.value== "" ) {
		             alert("請輸入寵物名稱!");
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
<body>
<%@ include file="/menu1.jsp" %> 


<table>

	<jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService" />

 <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/backend/pet/pet.do" name = "form1" >
        <b>輸入會員編號 (如7002):</b>
        <input type="text" name="memNo" value="${memNo}">
        <input type = "hidden" name = "requestURL" value = "<%=request.getRequestURI()%>">
        <input type="button" value="送出" onclick = "fun1()">
        <input type="hidden" name="action" value="getAll_For_Display_From_MemberFromApt">
    </FORM>
  </li>
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/apt/apt.do"   name = "form2">
       <b>請選擇寵物:</b>
     
       <select size="1" name="petNo">
         <c:forEach var="petVO" items="${memPetVO}" > 
         	<option value="${petVO.petNo}">${petVO.petName}
         </c:forEach>   
       </select>
<%--       	<%HttpSession sn = request.getSession(); --%>
<!--      	  String s	= (String)(sn.getAttribute("aptDate")); -->
<%--       	%> --%>
<%--        <input type = "hidden"  name = "aptDate" value = "<%=(String)(sn.getAttribute("aptDate")))%>" /> --%>
<%--        <input type = "hidden"  name = "aptPeriod" value = "<%= (String)(sn.getAttribute("aptPeriod"))%>" /> --%>
       <input type = "hidden" name = "requestURL" value = "<%=request.getRequestURI()%>" /> 
       <input type="hidden" name="action" value="insert">
       <input type="button" value="掛號" onclick = "fun2()">
       
    </FORM>
  </li>
  <br/>
  <br/>
  <li>
  	 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/mem/mem.do"   name = "form3">
       <b>非會員通道:</b>
     
       <input type="hidden" name="action" value="autoInsert">
       <input type="submit" value="非會員通道按鈕" />
       
    </FORM>
  </li>
</table>
 <%@ include file="/menu2.jsp" %> 
</body>
</html>