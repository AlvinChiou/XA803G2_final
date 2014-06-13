<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.drRecord.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%@ page import="com.pow.model.*"%>
<%@ page import="com.drRecord.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	DrRecordService drRecSvc = new DrRecordService();
    List<DrRecordVO> list = drRecSvc.getAll();
    pageContext.setAttribute("list",list);
    
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4003);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
	
%>

<html>
<head>
<title>所有看診紀錄資料 - listAllDrRecord.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		

</head>
<body bgcolor='white'>

<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>"> 
<!-- jsp主要開始 -->
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有看診紀錄資料 - ListAllDrRecord.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
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

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>看診紀錄編號</th>
		<th>寵物名字</th>
		<th>時間</th>
		<th>處方</th>
		<th>醫生</th>
		
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="drRecVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${drRecVO.drRecNo}</td>
	<jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService" />
			
		<td nowrap><c:forEach var="petVO" items="${petSvc.all}">
                    <c:if test="${petVO.petNo==drRecVO.petNo}">
	                    ${petVO.petNo}【<font color=orange>${petVO.petName}</font> 】
                    </c:if>
                </c:forEach>
		</td>
			<td>${drRecVO.drRecTime}</td>
			<td>${drRecVO.drRecPres}</td>
			<jsp:useBean id="drSvc" scope="page" class="com.doctor.model.DoctorService" />
			
			<td nowrap><c:forEach var="drVO" items="${drSvc.all}">
                    <c:if test="${drVO.drNo==drRecVO.drNo}">
	                    ${drVO.drNo}【<font color=orange>${drVO.drName}</font> 】
                    </c:if>
                </c:forEach>
			</td>
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/drRecord/drRecord.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="drRecNo" value="${drRecVO.drRecNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/drRecord/drRecord.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="drRecNo" value="${drRecVO.drRecNo}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

<!-- jsp主要結束 -->
    
    
    
  </c:if>  
  <%@ include file="/menu2.jsp" %> 


</body>
</html>
