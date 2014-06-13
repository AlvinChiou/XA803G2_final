<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>

<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmployeeService" />
<jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>iPET Mem: home</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		
</head>
<body bgcolor='#EFF6FF'>
	<%@ include file="/menu1.jsp" %> 

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Pow: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<!--  jsp�}�l -->
<!-- <table border='1' cellpadding='5' cellspacing='0' width='600'> -->
<!--   <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'> -->
<%--     <td><img src="<%= request.getContextPath()%>/mem/images/photo.jpg" width='150' height='150' align="left"/> --%>
<!--     	<h3>iPET Mem: Home</h3><font color=red>( MVC )</font></td> -->
<!--   </tr> -->
<!-- </table> -->

${employeeVO.empName}�A�A�n�C<BR>
<%-- <c:forEach var="funcVO" items="${funcSvc.all}"> --%>
<%-- 	<c:forEach var="powVO" items="${list}"> --%>
<%-- 		<c:if test="${powVO.funcno==funcVO.funcno}">${funcVO.funcname}</c:if> --%>
<%--     </c:forEach> --%>
<%-- </c:forEach> --%>


<!-- <h3>��Ƭd��</h3> -->
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

<!-- <ul> -->
<%-- 	<li><a href='<%= request.getContextPath()%>/mem/listAllMem.jsp'>List</a> all Mems. </li> <br><br> --%>
	
<!-- 	<li> -->
<%-- 		<FORM method="post" action="<%= request.getContextPath()%>/mem/mem.do"> --%>
<!-- 			<b>��J�|���s��(�p7001):</b> -->
<!-- 			<input type="text" name="memno"> -->
<!-- 			<input type="submit" value="�e�X"> -->
<!-- 			<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 		</FORM> -->
<!-- 	</li> -->

<%-- 	<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" /> --%>
	
<!-- 	<li> -->
<%-- 		<form method="post" action="<%= request.getContextPath()%>/mem/mem.do"> --%>
<!-- 			<b>��ܷ|���s��:</b> -->
<!-- 			<select size="1" name="memno" > -->
<%-- 				<c:forEach var="memVO" items="${memSvc.all}" > --%>
<%-- 					<option value="${memVO.memno}">${memVO.memno} --%>
<%-- 				</c:forEach> --%>
<!-- 			</select> -->
<!-- 			<input type="submit" value="�e�X"> -->
<!-- 			<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 		</form> -->
<!-- 	</li> -->
	
<!-- 	<li> -->
<%-- 		<form method="post" action="<%= request.getContextPath()%>/mem/mem.do"> --%>
<!-- 			<b>��ܷ|���m�W:</b> -->
<!-- 			<select size="1" name="memno"> -->
<%-- 				<c:forEach var="memVO" items="${memSvc.all}"> --%>
<%-- 					<option value="${memVO.memno}">${memVO.memname} --%>
<%-- 				</c:forEach> --%>
<!-- 			</select> -->
<!-- 			<input type="submit" value="�e�X"> -->
<!-- 			<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 		</form> -->
<!-- 	</li> -->
	
<!-- 	<li> -->
<%-- 		<form method="post" action="<%= request.getContextPath()%>/mem/mem.do"> --%>
<!-- 			<b><font color=orange>��ܷ|�����A:</font></b> -->
<!-- 			<select size="1" name="memstate"> -->
<!--           			<option value="0">���{�� -->
<!--           			<option value="1">�w�{�� -->
<!--           			<option value="2">�w���v -->
<!-- 			</select> -->
<!-- 			<input type="submit" value="�e�X"> -->
<!-- 			<input type="hidden" name="action" value="listMem_ByState"> -->
<!-- 		</form> -->
<!-- 	</li> -->
<!-- </ul> -->

<!-- <h3>�|���޲z</h3> -->

<!-- <ul> -->
<%-- 	<li><a href='<%= request.getContextPath()%>/mem/addMem.jsp'>Add</a> a new Mem.</li> --%>
<!-- </ul> -->

<!-- <h3>���ܨ�M�峹�d��</h3> -->

<!-- <ul> -->
<%-- 	<li><a href='<%= request.getContextPath()%>/lost/listAllLost.jsp'>List</a> all Losts. </li> <br><br> --%>
	
<!-- 	<li> -->
<%-- 		<form method="post" action="<%= request.getContextPath()%>/lost/lost.do"> --%>
<!-- 			<b>��J�峹�s��(�p5001):</b> -->
<!-- 			<input type="text" name="lostno"> -->
<!-- 			<input type="submit" value="�e�X"> -->
<!-- 			<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 		</form> -->
<!-- 	</li> -->
	
<!-- 	<li> -->
<%-- 		<form method="post" action="<%= request.getContextPath()%>/lost/lost.do"> --%>
<!-- 			<b>��J�|���s��(�p7001):</b> -->
<!-- 			<input type="text" name="memno"> -->
<!-- 			<input type="submit" value="�e�X"> -->
<!-- 			<input type="hidden" name="action" value="listLost_ByMemno"> -->
<!-- 		</form> -->
<!-- 	</li> -->
	
<%-- 	<jsp:useBean id="lostSvc" scope="page" class="com.lost.model.LostService" /> --%>

<!-- 	<li> -->
<%-- 		<form method="post" action="<%= request.getContextPath()%>/lost/lost.do"> --%>
<!-- 			<b><font color=orange>��ܤ峹���A:</font></b> -->
<!-- 			<select size="1" name="loststate"> -->
<!--           		<option value="0">���� -->
<!--           		<option value="1">�B�� -->
<!--           		<option value="2">�w��� -->
<!--           		<option value="3">�w��^ -->
<!-- 			</select> -->
<!-- 			<input type="submit" value="�e�X"> -->
<!-- 			<input type="hidden" name="action" value="listLost_ByState"> -->
<!-- 		</form> -->
<!-- 	</li> -->
<!-- </ul> -->

<!-- <h3>���ܨ�M�峹�޲z</h3> -->

<!-- <ul> -->
<%-- 	<li><a href='<%= request.getContextPath()%>/lost/addLost.jsp'>Add</a> a new Lost.</li> --%>
<!-- </ul> -->

<h3>���u�v���޲z</h3>

<ul>
	<li><a href='<%= request.getContextPath()%>/backend/pow/listAllPow.jsp'>�Ҧ����u�v���C��</a></li><br>

	<li><a href='<%= request.getContextPath()%>/backend/func/listAllFunc.jsp'>�Ҧ��v���\���</a></li><br>
<%-- 	<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" /> --%>
<%-- 	<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmployeeService" /> --%>
<%-- 	<jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" /> --%>

	<li>
		<form method="post" action="<%= request.getContextPath()%>/pow/pow.do">
			<b>��ܭ��u�m�W:</b>
			<select size="1" name="empno">
				<c:forEach var="empVO" items="${empSvc.all}">
					<option value="${empVO.empNo}">${empVO.empName}
				</c:forEach>
			</select>
			<input type="submit" value="�e�X">
			<input type="hidden" name="action" value="getOne_For_Display">
		</form>
	</li>

	<li>
		<form method="post" action="<%= request.getContextPath()%>/pow/pow.do">
			<b>����v���W��:</b>
			<select size="1" name="funcno">
				<c:forEach var="funcVO" items="${funcSvc.all}">
					<option value="${funcVO.funcno}">${funcVO.funcname}
				</c:forEach>
			</select>
			<input type="submit" value="�e�X">
			<input type="hidden" name="action" value="listPow_Byfuncname">
		</form>
	</li>
	
</ul>

<!-- <h3>�v���\��޲z</h3> -->

<!-- <ul> -->


<!-- 	<li> -->
<%-- 		<form method="post" action="<%= request.getContextPath()%>/func/func.do"> --%>
<!-- 			<b>��J�v���\��s��(�p4001):</b> -->
<!-- 			<input type="text" name="funcno"> -->
<!-- 			<input type="submit" value="�e�X"> -->
<!-- 			<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 		</form> -->
<!-- 	</li> -->
 
<!-- </ul> -->
<!--  jsp���� -->

    
    
    
  <%@ include file="/menu2.jsp" %> 
    

</body>

</html>