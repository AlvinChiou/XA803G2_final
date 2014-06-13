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
<h3>員工管理:</h3>
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
		<li><a href='<%=request.getContextPath()%>/backend/emp/listAllEmp.jsp'>顯示所有員工</a></li>
		<br>
		<li><a href='<%=request.getContextPath()%>/backend/emp/addEmp.jsp'>新增員工</a></li>
		<br>

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/emp/emp.do">
				<b>輸入員工編號 (如1001):</b> <input type="text" name="empNo"> <input
					type="submit" value="送出"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<jsp:useBean id="empSvc" scope="page"
			class="com.emp.model.EmployeeService" />

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/emp/emp.do">
				<b>選擇員工編號:</b> <select size="1" name="empNo">
					<c:forEach var="employeeVO" items="${empSvc.all}">
						<option value="${employeeVO.empNo}">${employeeVO.empNo}
					</c:forEach>
				</select> <input type="submit" value="送出"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/emp/emp.do">
				<b>選擇員工姓名:</b> <select size="1" name="empNo">
					<c:forEach var="employeeVO" items="${empSvc.all}">
						<option value="${employeeVO.empNo}">${employeeVO.empName}
					</c:forEach>
				</select> <input type="submit" value="送出"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>
	</ul>


<!-- 	<h3>員工管理</h3> -->

<!-- 	<ul> -->
<%-- 		<li><a href='<%=request.getContextPath()%>/backend/emp/addEmp.jsp'>Add</a></li> --%>
<!-- 	</ul> -->
	<%@ include file="/menu2.jsp" %> 

</body>

</html>
