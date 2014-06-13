<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.apt.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	AptVO aptVO = (AptVO) request.getAttribute("aptVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
	String aptPeriod = aptVO.getAptPeriod().substring(0,2) + "點-" + aptVO.getAptPeriod().substring(2) + "點";
	aptVO.setAptPeriod(aptPeriod);
	pageContext.setAttribute("aptVO",aptVO);
	String aptRegTime = aptVO.getAptRegTime().toString();
	pageContext.setAttribute("aptRegTime", aptRegTime.substring(0, 19)); 
%>
<jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService" />

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>掛號資料 - ListOneApt.jsp</h3>
		<a href="<%=request.getContextPath()%>/backend/apt-Knifeman.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回掛號管理首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th nowrap>掛號編號&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>掛號日期&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>掛號時段&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>號碼牌編號&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>應到時間&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>寵物編號+名字&nbsp;&nbsp;&nbsp;</th>
	</tr>
	<tr align='center' valign='middle'>
			<td nowrap>${aptVO.aptNo}</td>
			<td nowrap>${aptVO.aptDate}</td>
			<td nowrap>${aptVO.aptPeriod}</td>
			<td nowrap>${aptVO.aptNoSlip}</td>
			<td nowrap>${aptRegTime}</td>
			<td nowrap><c:forEach var="petVO" items="${petSvc.all}">
                    <c:if test="${petVO.petNo==aptVO.petNo}">
	                    ${aptVO.petNo}【<font color=orange>${petVO.petName}</font> 】
	                    <td>
	                    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do">
			    				<input type="hidden" name="petNo" value="${petVO.petNo}"> 
			    				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			   					<input type="hidden" name="action" value="getOne_For_Display">
			    				<input type="submit" value="查詢此寵物">
			    			</FORM>
	                    </td>
	                    <td>
	                    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/drRecord/addDrRecord.jsp">
			    				<input type="hidden" name="petNo" value="${petVO.petNo}"> 
			    				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			   					<input type="hidden" name="action" value="insert">
			    				<input type="submit" value="看診處理">
			    			</FORM>
	                    
						</td>
                    </c:if>
                </c:forEach>
			</td>
			
	</tr>
</table>

</body>
</html>
