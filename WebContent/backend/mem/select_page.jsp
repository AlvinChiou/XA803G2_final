<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
</head>
<%
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4010);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
	
	 %>
<body bgcolor='#EFF6FF'>
	<%@ include file="/menu1.jsp" %> 
		<c:if test="<%=listPower.contains(powVO)%>"> 

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Mem: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<BR>
<BR>
<BR>

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
		<FORM method="post" action="<%= request.getContextPath()%>/backend/mem/mem.do">
			<b>��J�|���s��(�p7001):</b>
			<input type="text" name="memno">
			<input type="submit" value="�e�X">
			<input type="hidden" name="action" value="getOne_For_Display">
		</FORM>
	</li>

	<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
	
	<li><a href='<%= request.getContextPath()%>/backend/mem/listAllMem.jsp'>��ܩҦ��|�����</a></li>
	<br>
	<li><a href='<%= request.getContextPath()%>/backend/mem/addMem.jsp'>�[�J�|��</a></li>
	
	<li>
		<form method="post" action="<%= request.getContextPath()%>/backend/mem/mem.do">
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
		<form method="post" action="<%= request.getContextPath()%>/backend/mem/mem.do">
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
		<form method="post" action="<%= request.getContextPath()%>/backend/mem/mem.do">
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
	
</ul>

<!-- <h3>�[�J�|��</h3> -->

<!-- <ul> -->
<%-- 	<li><a href='<%= request.getContextPath()%>/backend/mem/addMem.jsp'>�[�J�|��</a></li> --%>
<!-- </ul> -->
	</c:if>
		<%@ include file="/menu2.jsp" %> 
	
</body>
</html>