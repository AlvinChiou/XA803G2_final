<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.apt.model.*"%>
<%@ page import="com.apt.controller.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />

<%
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4002);
	List<PowVO> list = (List<PowVO>)session.getAttribute("list");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/style.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js"></script>

	
<script>

	$(function() {
		$('#menu').children('li').click(function() {
			$('#menu').find('ul').removeClass('active');
			$(this).find('ul').toggleClass('active');
		});
	});
	
	
</script>
</head>
<body>
	<%@ include file="/menu1.jsp"%>
<c:if test="<%=list.contains(powVO)%>">  


	<!-- jsp主要開始 -->
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<!-- 	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'> -->
		<!-- 		<td> -->
		<%-- 		   <a href="<%=request.getContextPath()%>/backend/apt-Knifeman.jsp">回掛號首頁</a> --%>
		<!-- 	    </td> -->
		<!-- 	</tr> -->
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

	<%-- ${regDays}  --%>
	<table border='1'>

		<!--以下為show出RegDays[]陣列的code!-->
		<%
			RegDay[] regDays = (RegDay[]) application.getAttribute("regDays");
		%>
		<%-- <%RegDay[] regDays = (RegDay[])request.getAttribute("regDays");%> --%>
		<br />
		<%@ include file="pages/page1.file"%>
		<%
			if (regDays != null) {
		%>
		<%-- regDays:<%=regDays%> --%>
		<%
			for (int i = pageIndex; i < pageIndex + rowsPerPage; i++) {
		%>
		<%
			if (regDays[i] != null) {
		%>
		<tr>
			<th>日期 :
				<h2><%=regDays[i].getDate()%><h2 />
			</th>

			<%
				for (int k = 1; k <= 5; k++) {
			%>
			<th><%=k%></th>
			<%
				}
			%>
			<th>確認交換(swap)</th>
			<th>確認出隊(deQueue)</th>


		</tr>
		<%
		// TR 的for在這!!!
			for (int j = 0; j < regDays[i].getRegPeriods().length; j++) {
		%>
		<%
			if (regDays[i].getRegPeriods()[j] != null) {
		%>

		<tr id = "tr<%=i%><%=j%>">
			<td>
				<%
					String[] result = regDays[i].getRegPeriods()[j]
												.myToString();
				%> 時段 :
				<%=regDays[i].getRegPeriods()[j]
										.getPeriod().substring(0, 2)
										+ "點-"
										+ regDays[i].getRegPeriods()[j]
												.getPeriod().substring(2) + "點"%>
			</td>
<!-- 			<form method="post" -->
<%-- 				action="<%=request.getContextPath()%>/backend/apt/apt.do"> --%>
<!-- 				<input type="hidden" name="action" value="swap"> <input -->
<%-- 					type="hidden" name="whichDay" value=<%=i%>> <input --%>
<%-- 					type="hidden" name="whichPeriod" value=<%=j%>> --%>
				<%
				// TD的for在這!!!

					for (int z = 0; z < AptServlet.QUEUE_SIZE; z++) {
				%>

				<td align="center">
					<%
						if (z < result.length && result[z] != null) {
					%> 
					<input type="checkbox" class="selectedElements" name="selectedElements" value=<%=z + 1%>>

					<a
					href="<%=request.getContextPath()%>/backend/apt/apt.do?action=getOne_For_DisplayInTheSamePage&aptNo=<%=result[z]%>&whichPage=<%=whichPage%>">
						<jsp:include page="/included.jsp" flush="true">
							<jsp:param name="aptNo" value="<%=result[z]%>" />
						</jsp:include> 
					</a> <%
 	} else {
 %> <%
 	}
 %>
				</td>

				<%
					}
				%>
				<td align="center"><input type="button" value="確認交換"  id  = "swap<%=i%><%=j%>">
<%-- 					<input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
<!-- 					送出當前是第幾頁給Controller</td> -->
<!-- 			</form> -->
<script>

//var val = new Array(5);
//var k = 0;

//$(':checkbox:checked').each(function(i){
//val  = $(this).val();
//});

 $(document).ready(function (){ 
	
 	 $('#swap<%=i%><%=j%>').click(function(){ 
 		var checkVal = "";
 		$(':checkbox:checked').each(function(i){
 			checkVal += $(this).val()+",";
 		});
 		 $.ajax({ 
 			 type:"GET", 
			 url:"apt.do", 
			 data:{"action":"swap","whichDay":<%=i%>,"whichPeriod":<%=j%>,"whichPage":<%=whichPage%>
			 ,  "selectedElements" : checkVal}, 
			 dataType:"json", 
			 success:function (data){ 
				 var url_0=$(':checkbox:checked').eq(0).next().find("img").attr("src");
				 var url_1=$(':checkbox:checked').eq(1).next().find("img").attr("src");
				 var val_0=$(':checkbox:checked').eq(0).attr("value");
				 var val_1=$(':checkbox:checked').eq(1).attr("value");
// 				 alert(val_0+","+val_1);
				 $(':checkbox:checked').eq(1).next().find("img").attr("src",url_0);
				 $(':checkbox:checked').eq(0).next().find("img").attr("src",url_1);
				 $(':checkbox:checked').eq(0).attr("value",val_1);
				 $(':checkbox:checked').eq(1).attr("value",val_0);
// 				 alert($(':checkbox:checked').eq(0).attr("value")+","+$(':checkbox:checked').eq(1).attr("value"));
				 alert("交換成功!");
		     }, 
             error:function(){alert("error");} 
         })   
         
         
	 }) 
	
  })
</script>

			</td>
<!-- 			</form> -->
			
			
			<td align="center">
<!-- 				<form method="post" -->
<%-- 					action="<%=request.getContextPath()%>/backend/apt/apt.do"> --%>
<!-- 					<input type="hidden" name="action" value="deQueue"> <input -->
<%-- 						type="hidden" name="whichDay" value=<%=i%>> <input --%>
<%-- 						type="hidden" name="whichPeriod" value=<%=j%>>  --%>
						<input  
						type="button" value="出隊操作" id  = "deQueue<%=i%><%=j%>"> 
<!-- 						<input type="hidden" -->
<%-- 						name="whichPage" value="<%=whichPage%>"> --%>
					<!--送出當前是第幾頁給Controller-->

<!-- 				</form> -->


<script>
 $(document).ready(function (){ 
 	 $('#deQueue<%=i%><%=j%>').click(function(){ 
 		 $.ajax({ 
 			 type:"GET", 
			 url:"apt.do", 
			 data:{"action":"deQueue","whichDay":<%=i%>,"whichPeriod":<%=j%>,"whichPage":<%=whichPage%>}, 
			 dataType:"json", 
			 success:function (data){ 
				 location.reload(true)
		     }, 
             error:function(){alert("error");} 
         })   
	 }) 
  })
</script>
			</td>



			<%
				}
			%>
		</tr>
		<%
			}
		%>


		<%
			} else {
		%>
		<h1><%=new java.sql.Date(new java.util.Date()
								.getTime()
								+ i
								* AptServlet.ONE_DAY_IN_MILLISECONDS)%>
			此天休診
		</h1> 
		<%
			}
		%>
		<%
			}
		%>
		<%
			}
		%>
		<%@ include file="pages/page2.file"%>
	</table>

	<%
		if (request.getAttribute("aptVO") != null) {
	%>
	<jsp:include page="/backend/apt/listOneApt.jsp" />
	<%
		}
	%>

	<!-- jsp主要結束 -->


	<%
		
	%>


	<%@ include file="/menu2.jsp"%>

<!-- <script> -->
<!-- 	function showRow() { -->
<!-- 		var json = -->
<!-- 	} -->
<!-- </script> -->

</c:if>
</body>
</html>