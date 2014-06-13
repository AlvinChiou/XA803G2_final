<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*, com.productitem.model.ProdItemVO"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<jsp:useBean id="listPorduct_ByCompositeQuery" scope="request" type="java.util.List"/>
<jsp:useBean id="proSvc" scope="page" class="com.product.model.ProductService"/>
<%
	ProductService proSvc2 = new ProductService();
	List<ProductVO> list = proSvc2.getAll();
	pageContext.setAttribute("list", list);
%>
<%Vector<ProdItemVO> buylist = (Vector<ProdItemVO>) session.getAttribute("shoppingcart");%>
<html>
<head>
<title>搜尋商品</title>
<style type="text/css">
.myDiv {
  margin-top: 150px;
}
</style>
</head>
<body>
<div class="myDiv">
<sql:setDataSource dataSource="jdbc/iPetCaresDB" var="productCategory" scope="application" />
	<sql:query var="rs" dataSource="${productCategory}">
		SELECT DISTINCT category FROM product
	</sql:query>
<table>	
	<tr>
		<td colspan="11" align="right">
			<% if (buylist != null && (buylist.size() > 0)) {%>
					<input type="button"
						onclick="javascript:location.href='<%=request.getContextPath()%>/frontend/ORDERITEM/additem.jsp'"
						value="檢視購物車">
					<%}%>
		</td>
	</tr>
	<tr>
			<td colspan="11" align="center">
				<form method="post"
					action="<%=request.getContextPath()%>/backend/PRODUCT/product.do">
					關鍵字:<input type="text" name="keyword" value="" size="50">
					分類:<select name="category">
						<option value="">--請選擇商品分類--
							<c:forEach var="row" items="${rs.rows}" varStatus="status">
								<option value='<c:out value="${row.category}"/>'><c:out
										value="${row.category}" />
							</c:forEach>
					</select> <input type="submit" value="搜尋商品"> <input name="action"
						value="CompositeQueryFromMarket" type="hidden">
				</form>
			</td>
		</tr>  
	<%@ include file="/frontend/ORDERITEM/pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="productVO" items="${listPorduct_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<c:if test="${productVO.status == 1}">
	<tr align='center' valign='middle' ${(productVO.prono==param.prono) ? 'bgcolor=#CCCCFF':''}>
		<td><div style="display: inline; width: 30%"><a href="<%=request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=${productVO.prono}"><img alt="商品照片" src="<%=request.getContextPath()%>/backend/PRODUCT/ProductShowImage.do?prono=${productVO.prono}&&image=1"></a></div>	</td>
		<td><a href="<%=request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=${productVO.prono}">${productVO.productname}</a></td>		
		<td colspan="2">${productVO.price}</td>		
		<td>${(productVO.discount)<1?'特價中':''}</td>
		<td>${productVO.keyword}</td>
		<td align="center"><select name="itemqty"
							${(productVO.quantity<=0)?'disabled':''}>
								<option value="1" selected>${(productVO.quantity<=0)?'待補貨':'1'}
									<c:if test="${productVO.quantity >= 2}">
										<option value="2">2
									</c:if>
									<c:if test="${productVO.quantity >= 3}">
										<option value="3">3
									</c:if>
									<c:if test="${productVO.quantity >= 4}">
										<option value="4">4
									</c:if>
									<c:if test="${productVO.quantity >= 5}">
										<option value="5">5
									</c:if>
						</select></td>
		<td><input type="submit" value="加入購物車"
							${(productVO.quantity<=0)?'disabled':''}> <input
							type="hidden" name="productname" value="${productVO.productname}">
							<input type="hidden" name="price" value="${(productVO.price)}">
							<input type="hidden" name="prono" value="${productVO.prono}">
							<input type="hidden" name="action" value="ADD"></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
		</c:if>
	</c:forEach>
</table>
<%@ include file="/frontend/ORDERITEM/pages/page2_ByCompositeQuery.file" %>
</div>
</body>
</html>