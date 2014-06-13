<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>IBM Dr: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>iPETCares Dr: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<h3>醫師資料管理:</h3>
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
  <li><a href='<%=request.getContextPath()%>/dr/listAllDr.jsp'>所有醫師列表</a></li>
  <br><br>
  <li><a href='<%=request.getContextPath()%>/dr/addDr.jsp'>新增醫師資料</a></li>
</ul>


<!-- <h3>醫師管理</h3> -->

<!-- <ul> -->
<%--   <li><a href='<%=request.getContextPath()%>/dr/addDr.jsp'>Add</a> a new Dr.</li> --%>
<!-- </ul> -->

</body>

</html>
