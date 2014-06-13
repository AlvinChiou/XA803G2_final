<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>IBM Emp: Home</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		

</head>
<body bgcolor='white'>
	<%@ include file="/menu1.jsp" %> 

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>iPETCares Emp: Home</h3></td>
  </tr>
</table>
<br>
<h3>���u�޲z:</h3>
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
	
	<ul>
		<li><a href='<%=request.getContextPath()%>/backend/emp/listAllEmp.jsp'>��ܩҦ����u</a></li>
		<br>
		<li><a href='<%=request.getContextPath()%>/backend/emp/addEmp.jsp'>�s�W���u</a></li>
		<br>

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/emp/emp.do">
				<b>��J���u�s�� (�p1001):</b> <input type="text" name="empNo"> <input
					type="submit" value="�e�X"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<jsp:useBean id="empSvc" scope="page"
			class="com.emp.model.EmployeeService" />

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/emp/emp.do">
				<b>��ܭ��u�s��:</b> <select size="1" name="empNo">
					<c:forEach var="employeeVO" items="${empSvc.all}">
						<option value="${employeeVO.empNo}">${employeeVO.empNo}
					</c:forEach>
				</select> <input type="submit" value="�e�X"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/emp/emp.do">
				<b>��ܭ��u�m�W:</b> <select size="1" name="empNo">
					<c:forEach var="employeeVO" items="${empSvc.all}">
						<option value="${employeeVO.empNo}">${employeeVO.empName}
					</c:forEach>
				</select> <input type="submit" value="�e�X"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>
	</ul>


<!-- 	<h3>���u�޲z</h3> -->

<!-- 	<ul> -->
<%-- 		<li><a href='<%=request.getContextPath()%>/backend/emp/addEmp.jsp'>Add</a></li> --%>
<!-- 	</ul> -->
	<%@ include file="/menu2.jsp" %> 

</body>

</html>
