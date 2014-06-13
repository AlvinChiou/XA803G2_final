<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.apt.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- aptDateByParameter:<%=request.getParameter("aptDate")%> --%>
<%-- aptPeriodByParameter:<%=request.getParameter("aptPeriod")%> --%>

<%-- aptDateByAttribute:<%=(String)request.getAttribute("aptDate")%> --%>
<%-- aptPeriodByAttribute:<%=(String)request.getAttribute("aptPeriod")%> --%>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script> --%>
<%-- <script src="<%=request.getContextPath()%>/menu.js"></script>		 --%>
<%
	if ( request.getParameter("aptDate") != null && request.getParameter("aptPeriod") != null) {
		request.getSession().setAttribute( "aptDate", request.getParameter("aptDate"));
		request.getSession().setAttribute( "aptPeriod", request.getParameter("aptPeriod"));
	
	}


System.out.println( "aptDate" + request.getParameter("aptDate"));
System.out.println( "aptPeriod" + request.getParameter("aptPeriod"));
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>iPET Cares</title>
<link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" />
<link href="<%= request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css" />
<link href="<%= request.getContextPath()%>/css/box_css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%= request.getContextPath()%>/js/jquery-1.4.2.js"></script>  
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/jquery.fancybox-1.3.4.css" media="screen" />
	
<script type="text/javascript">
	$(document).ready(function() {
		/*
		*   Examples - images
		*/

		$("a#example1").fancybox();
		$("#various3").fancybox({
			'width'				: '75%',
			'height'			: '75%',
			'autoScale'			: false,
			'transitionIn'		: 'none',
			'transitionOut'		: 'none',
			'type'				: 'iframe'
		});
	});
</script>

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
		        	 document.getElementById("form2").submit();
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

<div id="wrapper">
  		<div id="main_bg">
    	<div id="main_bg01">
        	<div id="apDiv13"></div>
			<c:if test="${memVO == null}"><%@ include file="/header.jsp" %></c:if>
        	<c:if test="${memVO != null}"><%@ include file="/loginHeader.jsp" %></c:if>
			<%@ include file="/ad.jsp" %>   
  		</div>
		</div>

<table align='center'>

	<jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService" />
	<tr><td>
<!--  <li> -->
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front/pet/pet.do" name = "form1" >
        <b>會員編號 :</b>
        <input type="text" name="memNo" size="10" value="<%= (memVO.getMemno()==null)? "" : memVO.getMemno()%>" readonly="readonly">
        <input type = "hidden" name = "requestURL" value = "<%=request.getRequestURI()%>">
        <input type="button" value="送出" onclick = "fun1()">
        <input type="hidden" name="action" value="getAll_For_Display_From_MemberFromApt">
    </FORM>
<!--   </li> -->
 	</td></tr>
  
  	<tr><td>
<!--   <li> -->
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/apt/apt.do"   name = "form2" id = "form2">
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
<!--   </li> -->
  	</td></tr>
</table>

	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>

</body>
</html>