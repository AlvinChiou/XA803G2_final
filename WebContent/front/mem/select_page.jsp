<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body bgcolor='#EFF6FF'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><img src="<%= request.getContextPath()%>/front/mem/images/photo.jpg" width='150' height='150' align="left"/>
    	<h3>iPET Mem: Home</h3>
    	<a href="<%= request.getContextPath() %>/index.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
    </td>
  </tr>
</table>

${memVO.memname}�A�n�C<input type="button" value="�n�X" onclick=""></button><BR>

<p>This is the home page for iPET Mem: Home</p>

<h3>��Ƭd��</h3>
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
	
	<li>
		<FORM method="post" action="<%= request.getContextPath()%>/mem/mem.do">
			<b>��J�|���s��(�p7001):</b>
			<input type="text" name="memno">
			<input type="submit" value="�e�X">
			<input type="hidden" name="action" value="getOne_For_Display">
		</FORM>
	</li>

	<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<!-- 

	<li><a href='<%= request.getContextPath()%>/front/mem/listAllMem.jsp'>List</a> all Mems. </li> <br><br>
	
	<li>
		<form method="post" action="<%= request.getContextPath()%>/mem/mem.do">
			<b>��ܷ|���s��:</b>
			<select size="1" name="memno" >
				<c:forEach var="memVO" items="${memSvc.all}" >
					<option value="${memVO.memno}">${memVO.memno}
				</c:forEach>
			</select>
			<input type="submit" value="�e�X">
			<input type="hidden" name="action" value="getOne_For_Display">
		</form>
	</li>
	
	<li>
		<form method="post" action="<%= request.getContextPath()%>/mem/mem.do">
			<b>��ܷ|���m�W:</b>
			<select size="1" name="memno">
				<c:forEach var="memVO" items="${memSvc.all}">
					<option value="${memVO.memno}">${memVO.memname}
				</c:forEach>
			</select>
			<input type="submit" value="�e�X">
			<input type="hidden" name="action" value="getOne_For_Display">
		</form>
	</li>
	
	
	<li>
		<form method="post" action="<%= request.getContextPath()%>/mem/mem.do">
			<b><font color=orange>��ܷ|�����A:</font></b>
			<select size="1" name="memstate">
          			<option value="0">���{��
          			<option value="1">�w�{��
          			<option value="2">�w���v
			</select>
			<input type="submit" value="�e�X">
			<input type="hidden" name="action" value="listMem_ByState">
		</form>
	</li>
 -->	
</ul>

<h3>�[�J�|��</h3>

<ul>
	<li><a href='<%= request.getContextPath()%>/front/mem/addMem.jsp'>Add</a> a new Mem.</li>
</ul>
	
</body>
</html>