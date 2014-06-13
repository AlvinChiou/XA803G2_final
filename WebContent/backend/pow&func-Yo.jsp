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

<!--  jsp開始 -->
<!-- <table border='1' cellpadding='5' cellspacing='0' width='600'> -->
<!--   <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'> -->
<%--     <td><img src="<%= request.getContextPath()%>/mem/images/photo.jpg" width='150' height='150' align="left"/> --%>
<!--     	<h3>iPET Mem: Home</h3><font color=red>( MVC )</font></td> -->
<!--   </tr> -->
<!-- </table> -->

${employeeVO.empName}，你好。<BR>
<%-- <c:forEach var="funcVO" items="${funcSvc.all}"> --%>
<%-- 	<c:forEach var="powVO" items="${list}"> --%>
<%-- 		<c:if test="${powVO.funcno==funcVO.funcno}">${funcVO.funcname}</c:if> --%>
<%--     </c:forEach> --%>
<%-- </c:forEach> --%>


<!-- <h3>資料查詢</h3> -->
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

<!-- <ul> -->
<%-- 	<li><a href='<%= request.getContextPath()%>/mem/listAllMem.jsp'>List</a> all Mems. </li> <br><br> --%>
	
<!-- 	<li> -->
<%-- 		<FORM method="post" action="<%= request.getContextPath()%>/mem/mem.do"> --%>
<!-- 			<b>輸入會員編號(如7001):</b> -->
<!-- 			<input type="text" name="memno"> -->
<!-- 			<input type="submit" value="送出"> -->
<!-- 			<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 		</FORM> -->
<!-- 	</li> -->

<%-- 	<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" /> --%>
	
<!-- 	<li> -->
<%-- 		<form method="post" action="<%= request.getContextPath()%>/mem/mem.do"> --%>
<!-- 			<b>選擇會員編號:</b> -->
<!-- 			<select size="1" name="memno" > -->
<%-- 				<c:forEach var="memVO" items="${memSvc.all}" > --%>
<%-- 					<option value="${memVO.memno}">${memVO.memno} --%>
<%-- 				</c:forEach> --%>
<!-- 			</select> -->
<!-- 			<input type="submit" value="送出"> -->
<!-- 			<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 		</form> -->
<!-- 	</li> -->
	
<!-- 	<li> -->
<%-- 		<form method="post" action="<%= request.getContextPath()%>/mem/mem.do"> --%>
<!-- 			<b>選擇會員姓名:</b> -->
<!-- 			<select size="1" name="memno"> -->
<%-- 				<c:forEach var="memVO" items="${memSvc.all}"> --%>
<%-- 					<option value="${memVO.memno}">${memVO.memname} --%>
<%-- 				</c:forEach> --%>
<!-- 			</select> -->
<!-- 			<input type="submit" value="送出"> -->
<!-- 			<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 		</form> -->
<!-- 	</li> -->
	
<!-- 	<li> -->
<%-- 		<form method="post" action="<%= request.getContextPath()%>/mem/mem.do"> --%>
<!-- 			<b><font color=orange>選擇會員狀態:</font></b> -->
<!-- 			<select size="1" name="memstate"> -->
<!--           			<option value="0">未認證 -->
<!--           			<option value="1">已認證 -->
<!--           			<option value="2">已停權 -->
<!-- 			</select> -->
<!-- 			<input type="submit" value="送出"> -->
<!-- 			<input type="hidden" name="action" value="listMem_ByState"> -->
<!-- 		</form> -->
<!-- 	</li> -->
<!-- </ul> -->

<!-- <h3>會員管理</h3> -->

<!-- <ul> -->
<%-- 	<li><a href='<%= request.getContextPath()%>/mem/addMem.jsp'>Add</a> a new Mem.</li> --%>
<!-- </ul> -->

<!-- <h3>失蹤協尋文章查詢</h3> -->

<!-- <ul> -->
<%-- 	<li><a href='<%= request.getContextPath()%>/lost/listAllLost.jsp'>List</a> all Losts. </li> <br><br> --%>
	
<!-- 	<li> -->
<%-- 		<form method="post" action="<%= request.getContextPath()%>/lost/lost.do"> --%>
<!-- 			<b>輸入文章編號(如5001):</b> -->
<!-- 			<input type="text" name="lostno"> -->
<!-- 			<input type="submit" value="送出"> -->
<!-- 			<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 		</form> -->
<!-- 	</li> -->
	
<!-- 	<li> -->
<%-- 		<form method="post" action="<%= request.getContextPath()%>/lost/lost.do"> --%>
<!-- 			<b>輸入會員編號(如7001):</b> -->
<!-- 			<input type="text" name="memno"> -->
<!-- 			<input type="submit" value="送出"> -->
<!-- 			<input type="hidden" name="action" value="listLost_ByMemno"> -->
<!-- 		</form> -->
<!-- 	</li> -->
	
<%-- 	<jsp:useBean id="lostSvc" scope="page" class="com.lost.model.LostService" /> --%>

<!-- 	<li> -->
<%-- 		<form method="post" action="<%= request.getContextPath()%>/lost/lost.do"> --%>
<!-- 			<b><font color=orange>選擇文章狀態:</font></b> -->
<!-- 			<select size="1" name="loststate"> -->
<!--           		<option value="0">失蹤 -->
<!--           		<option value="1">拾獲 -->
<!--           		<option value="2">已找到 -->
<!--           		<option value="3">已領回 -->
<!-- 			</select> -->
<!-- 			<input type="submit" value="送出"> -->
<!-- 			<input type="hidden" name="action" value="listLost_ByState"> -->
<!-- 		</form> -->
<!-- 	</li> -->
<!-- </ul> -->

<!-- <h3>失蹤協尋文章管理</h3> -->

<!-- <ul> -->
<%-- 	<li><a href='<%= request.getContextPath()%>/lost/addLost.jsp'>Add</a> a new Lost.</li> --%>
<!-- </ul> -->

<h3>員工權限管理</h3>

<ul>
	<li><a href='<%= request.getContextPath()%>/backend/pow/listAllPow.jsp'>所有員工權限列表</a></li><br>

	<li><a href='<%= request.getContextPath()%>/backend/func/listAllFunc.jsp'>所有權限功能表</a></li><br>
<%-- 	<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" /> --%>
<%-- 	<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmployeeService" /> --%>
<%-- 	<jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" /> --%>

	<li>
		<form method="post" action="<%= request.getContextPath()%>/pow/pow.do">
			<b>選擇員工姓名:</b>
			<select size="1" name="empno">
				<c:forEach var="empVO" items="${empSvc.all}">
					<option value="${empVO.empNo}">${empVO.empName}
				</c:forEach>
			</select>
			<input type="submit" value="送出">
			<input type="hidden" name="action" value="getOne_For_Display">
		</form>
	</li>

	<li>
		<form method="post" action="<%= request.getContextPath()%>/pow/pow.do">
			<b>選擇權限名稱:</b>
			<select size="1" name="funcno">
				<c:forEach var="funcVO" items="${funcSvc.all}">
					<option value="${funcVO.funcno}">${funcVO.funcname}
				</c:forEach>
			</select>
			<input type="submit" value="送出">
			<input type="hidden" name="action" value="listPow_Byfuncname">
		</form>
	</li>
	
</ul>

<!-- <h3>權限功能管理</h3> -->

<!-- <ul> -->


<!-- 	<li> -->
<%-- 		<form method="post" action="<%= request.getContextPath()%>/func/func.do"> --%>
<!-- 			<b>輸入權限功能編號(如4001):</b> -->
<!-- 			<input type="text" name="funcno"> -->
<!-- 			<input type="submit" value="送出"> -->
<!-- 			<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 		</form> -->
<!-- 	</li> -->
 
<!-- </ul> -->
<!--  jsp結束 -->

    
    
    
  <%@ include file="/menu2.jsp" %> 
    

</body>

</html>