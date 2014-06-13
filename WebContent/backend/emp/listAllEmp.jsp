<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    EmployeeService empSvc = new EmployeeService();
    List<EmployeeVO> list = empSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>�Ҧ����u��� - listAllEmp.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>

<script src="<%=request.getContextPath()%>/menu.js"></script>		
</head>
<body bgcolor='white'>
	<%@ include file="/menu1.jsp" %> 


<table border='1' cellpadding='5' cellspacing='0' width='650'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ����u��� </h3>
		<a href="<%=request.getContextPath()%>/backend/emp/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='650'>
	<tr>
		<th nowrap>���u�s��</th>
		<th nowrap>���u�m�W</th>
<!-- 		<th nowrap>�X�ͦ~���</th> -->
<!-- 		<th nowrap>�q��</th> -->
<!-- 		<th nowrap>�ʧO</th> -->
		<th nowrap>¾��</th>
<!-- 		<th nowrap>�~��</th> -->
		<th nowrap>��¾��</th>
<!-- 		<th nowrap>��¾��</th> -->
<!-- 		<th nowrap>�����Ҧr��</th> -->
<!-- 		<th nowrap>��}</th> -->
<!-- 		<th nowrap>�Ӥ�</th> -->
<!-- 		<th nowrap>�K�X</th> -->
		<th nowrap>E-mail</th>
		<th nowrap>�ק�</th>
<!-- 		<th nowrap>�R��</th> -->
	</tr>
	
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="employeeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(employeeVO.empNo==param.empNo) ? 'bgcolor=#CCCCFF':''}>
			<td>${employeeVO.empNo}</td>
			<td><a href="<%= request.getContextPath()%>/backend/emp/emp.do?action=getOne_For_Display&empNo=${employeeVO.empNo}">${employeeVO.empName}</a></td>
<%-- 			<td>${employeeVO.empBirth}</td> --%>
<%-- 			<td>${employeeVO.empTel}</td> --%>
<%-- 			<td><c:if test="${employeeVO.empSex == 'M'}">�ӭ�</c:if> --%>
<%-- 				<c:if test="${employeeVO.empSex == 'F'}">���k</c:if></td> --%>
			<td>${employeeVO.empPos}</td>
<%-- 			<td>${employeeVO.empSalary}</td> --%>
			<td>${employeeVO.empArrDate}</td>
<%-- 			<td>${employeeVO.empOff}</td> --%>
<%-- 			<td>${employeeVO.empID}</td> --%>
<%-- 			<td>${employeeVO.empAdd}</td> --%>
<%-- 			<td><img src="DBGifReader3?empNo=${employeeVO.empNo}" ></td> --%>
<%-- 			<td>${employeeVO.empPassword}</td> --%>
			<td>${employeeVO.empEmail}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/emp/emp.do">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="empNo" value="${employeeVO.empNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/emp/emp.do"> --%>
<!-- 			    <input type="submit" value="�R��"> -->
<%-- 			    <input type="hidden" name="empNo" value="${employeeVO.empNo}"> --%>
<%-- 			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"> --%>
<%-- 			    <input type="hidden" name="whichPage"	value="<%=whichPage%>"> --%>
<!-- 			    <input type="hidden" name="action"value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>
	<%@ include file="/menu2.jsp" %> 

</body>
</html>
