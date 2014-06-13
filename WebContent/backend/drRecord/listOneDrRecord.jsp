<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.drRecord.model.*"%>
<%@ page import="com.pet.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<%@ page import="com.drRecord.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService" />
<jsp:useBean id="drSvc" scope="page" class="com.doctor.model.DoctorService" />
<%
PetVO petVO = (PetVO) request.getAttribute("petVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4003);
List<PowVO> list = (List<PowVO>)session.getAttribute("list");
%>
<html>
<head>
<title>看診紀錄資料 - listOneDrRecord.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		

</head>
<body bgcolor='white'>

<%@ include file="/menu1.jsp" %> 
  <c:if test="<%=list.contains(powVO)%>">  
<!-- jsp主要開始 -->
<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>看診紀錄資料 - listOneDrRecord.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>看診紀錄編號</th>
		<th>寵物名字</th>
		<th>時間</th>
		<th>處方</th>
		<th>醫生</th>
		
	</tr>
	<tr align='center' valign='middle'>
			<td>${drRecVO.drRecNo}</td>
			<td>
				<c:forEach var="pet" items="${petSvc.all}">
					<c:if test="${pet.petNo == drRecVO.petNo}">${pet.petName}</c:if>
				</c:forEach>
			</td>
			<td>${drRecVO.drRecTime}</td>
			<td>${drRecVO.drRecPres}</td>
			<td nowrap>
				<c:forEach var="drVO" items="${drSvc.all}">
                    <c:if test="${drVO.drNo==drRecVO.drNo}">
	                    ${drVO.drNo}【<font color=orange>${drVO.drName}</font> 】
                    </c:if>
                </c:forEach>
			</td>			
	</tr>
</table>
<!-- jsp主要結束 -->
    
 </c:if>   
<%@ include file="/menu2.jsp" %> 



</body>
</html>
