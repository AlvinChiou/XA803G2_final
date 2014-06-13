<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>  


<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4005);
List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�ӫ~�d��</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
</head>
<body>
<%@ include file="/menu1.jsp" %> 

<c:if test="<%=listPower.contains(powVO)%>"> 

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>iPETCares : Home</h3></td>
  </tr>
</table>
<br>
<%--���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
	<c:forEach var="message" items="${errorMsgs}">
		<li>${message}</li>
	</c:forEach>
	</ul>
	</font>
</c:if>

<!-- <ul>	 -->
<!-- 	<b>�ӫ~�޲z</b> -->
<%-- 	<li><a href='<%=request.getContextPath()%>/backend/PRODUCT/listAllProduct.jsp'>�˵��Ҧ��ӫ~</a></li>	 --%>
<%-- 	<li><a href='<%=request.getContextPath()%>/backend/PRODUCT/addProduct.jsp'>�s�W�ӫ~</a></li> --%>

<!-- <ul> -->
	<b><h2>�ӫ~�޲z</h2></b><br>
	<li><a href='<%=request.getContextPath()%>/backend/PRODUCT/addProduct.jsp'>�s�W�ӫ~</a></li>
	<li><a href='<%=request.getContextPath()%>/backend/PRODUCT/listAllProduct.jsp'>�˵��Ҧ��ӫ~</a></li>

	<li>
		<form method="post" action="<%=request.getContextPath()%>/backend/PRODUCT/product.do">
			<b>�п�J�ӫ~�s��:</b>
			<input type="text" name="prono">
			<input type="submit" value="�d�߰ӫ~">
			<input type="hidden" name="action" value="getOne_For_Display">
		</form>
	</li>
	
	<jsp:useBean id="proSvc" scope="page" class="com.product.model.ProductService"/>
	<li>
		<form method="post" action="<%=request.getContextPath()%>/backend/PRODUCT/product.do">
			<b>�п�ܰӫ~�s��:</b>
			<select size="1" name="prono">
				<c:forEach var="productVO" items="${proSvc.all}">
					<option value="${productVO.prono}">${productVO.prono}
				</c:forEach>
			</select>
			<input type="submit" value="�d�߰ӫ~">
			<input type="hidden" name="action" value="getOne_For_Display">
		</form>
	</li>
	<li>
	<sql:setDataSource dataSource="jdbc/TestDB" var="productCategory" scope="application"/>
	<sql:query var="rs" dataSource="${productCategory}">
		SELECT DISTINCT category FROM product
	</sql:query>
		<form method="post" action="<%=request.getContextPath()%>/backend/PRODUCT/product.do" name="form1">
		<b><font color=blue>�г]�w�d�߱���</font></b><br>
		<b>�ӫ~�s��:</b>
		<input type="text" name="prono" value=""><br>
		<b>�ӫ~�W��:</b>
		<input type="text" name="productname" value=""><br>
		<b>�ӫ~����:</b>

		<select name="category">
			<option value="">--�п�ܰӫ~����--
			<c:forEach var="row" items="${rs.rows}" varStatus="status">
				<option value='<c:out value="${row.category}"/>'><c:out value="${row.category}"/>
			</c:forEach>
		</select><br>
		<b>�ӫ~���A:</b>
		<select name="status">
			<option value="">--�п�ܰӫ~���A--
			<option value="0">���W�[
			<option value="1">�W�[��
			<option value="2">�w�U�[
		</select><br>
		<b>�ӫ~����r:</b>
		<input type="text" name="keyword" value="">
		<input name="action" value="listPorduct_ByCompositeQuery" type="hidden"><br> 
		<input value="�d�߰ӫ~" type="submit">
		<input value="���]����" type="reset">
	</form>
	</li>
	
</ul>

</c:if>
<%@ include file="/menu2.jsp" %> 

</body>
</html>
