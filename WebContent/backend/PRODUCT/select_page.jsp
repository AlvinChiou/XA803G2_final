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
<title>商品查詢</title>
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
<%--錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
	<c:forEach var="message" items="${errorMsgs}">
		<li>${message}</li>
	</c:forEach>
	</ul>
	</font>
</c:if>

<!-- <ul>	 -->
<!-- 	<b>商品管理</b> -->
<%-- 	<li><a href='<%=request.getContextPath()%>/backend/PRODUCT/listAllProduct.jsp'>檢視所有商品</a></li>	 --%>
<%-- 	<li><a href='<%=request.getContextPath()%>/backend/PRODUCT/addProduct.jsp'>新增商品</a></li> --%>

<!-- <ul> -->
	<b><h2>商品管理</h2></b><br>
	<li><a href='<%=request.getContextPath()%>/backend/PRODUCT/addProduct.jsp'>新增商品</a></li>
	<li><a href='<%=request.getContextPath()%>/backend/PRODUCT/listAllProduct.jsp'>檢視所有商品</a></li>

	<li>
		<form method="post" action="<%=request.getContextPath()%>/backend/PRODUCT/product.do">
			<b>請輸入商品編號:</b>
			<input type="text" name="prono">
			<input type="submit" value="查詢商品">
			<input type="hidden" name="action" value="getOne_For_Display">
		</form>
	</li>
	
	<jsp:useBean id="proSvc" scope="page" class="com.product.model.ProductService"/>
	<li>
		<form method="post" action="<%=request.getContextPath()%>/backend/PRODUCT/product.do">
			<b>請選擇商品編號:</b>
			<select size="1" name="prono">
				<c:forEach var="productVO" items="${proSvc.all}">
					<option value="${productVO.prono}">${productVO.prono}
				</c:forEach>
			</select>
			<input type="submit" value="查詢商品">
			<input type="hidden" name="action" value="getOne_For_Display">
		</form>
	</li>
	<li>
	<sql:setDataSource dataSource="jdbc/TestDB" var="productCategory" scope="application"/>
	<sql:query var="rs" dataSource="${productCategory}">
		SELECT DISTINCT category FROM product
	</sql:query>
		<form method="post" action="<%=request.getContextPath()%>/backend/PRODUCT/product.do" name="form1">
		<b><font color=blue>請設定查詢條件</font></b><br>
		<b>商品編號:</b>
		<input type="text" name="prono" value=""><br>
		<b>商品名稱:</b>
		<input type="text" name="productname" value=""><br>
		<b>商品分類:</b>

		<select name="category">
			<option value="">--請選擇商品分類--
			<c:forEach var="row" items="${rs.rows}" varStatus="status">
				<option value='<c:out value="${row.category}"/>'><c:out value="${row.category}"/>
			</c:forEach>
		</select><br>
		<b>商品狀態:</b>
		<select name="status">
			<option value="">--請選擇商品狀態--
			<option value="0">未上架
			<option value="1">上架中
			<option value="2">已下架
		</select><br>
		<b>商品關鍵字:</b>
		<input type="text" name="keyword" value="">
		<input name="action" value="listPorduct_ByCompositeQuery" type="hidden"><br> 
		<input value="查詢商品" type="submit">
		<input value="重設條件" type="reset">
	</form>
	</li>
	
</ul>

</c:if>
<%@ include file="/menu2.jsp" %> 

</body>
</html>
