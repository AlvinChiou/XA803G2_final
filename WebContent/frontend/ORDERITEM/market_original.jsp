<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*, com.productitem.model.ProdItemVO"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%
	ProductService proSvc = new ProductService();
	List<ProductVO> list = proSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<%Vector<ProdItemVO> buylist = (Vector<ProdItemVO>) session.getAttribute("shoppingcart");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>網路商店</title>

</head>
<body>

	<sql:setDataSource dataSource="jdbc/iPetCaresDB" var="productCategory"
		scope="application" />
	<sql:query var="rs" dataSource="${productCategory}">
		SELECT DISTINCT category FROM product
	</sql:query>

	<table border="0">
		<tr>
			<td colspan="8" align="right">
					<%
						if (buylist != null && (buylist.size() > 0)) {
					%>
					<input type="button" id=""
						onclick="javascript:location.href='<%=request.getContextPath()%>/frontend/ORDERITEM/additem.jsp'"
						value="檢視購物車">
					<%
						}
					%>
				</td>
		</tr>
		<tr>
			<td colspan="8" align="center">
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
		<c:forEach var="productVO" items="${list}">
			<c:if test="${productVO.status == 1}">				
				<form name="shoppingForm" method="post" action="<%=request.getContextPath()%>/frontend/ORDERITEM/Shopping.do">
				<tr>							
						<td><div style="display: inline; width: 30%"><a href="<%=request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=${productVO.prono}"><img alt="商品照片" src="<%=request.getContextPath()%>/backend/PRODUCT/ProductShowImage.do?prono=${productVO.prono}&&image=1"></a></div>	</td>
						<td><a
							href="<%=request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=${productVO.prono}">${productVO.productname}</a></td>
						<td colspan="2">NTD${productVO.price}</td>						
						<td>${(productVO.discount)<1?'特價中':''}</td>
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
						<td><input type="submit" value="加入購物車" ${(productVO.quantity<=0)?'disabled':''} onclick="alert('已加入購物車');"> 
							<input type="hidden" name="productname" value="${productVO.productname}">
							<input type="hidden" name="price" value="${(productVO.price)}">
							<input type="hidden" name="prono" value="${productVO.prono}">
							<input type="hidden" name="action" value="ADD"></td>
				</tr>	
				</form>				
			</c:if>
		</c:forEach>
	</table>
	<%
		//if(!$pageCurrent) $pageCurrent=1;
		int rowNumber = list.size();
		String URI = request.getRequestURI();

		int thisPage = 1;
		try {
			thisPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			thisPage = 1;
		}
		com.utilities.SubPages pages = new com.utilities.SubPages(10,
				rowNumber, thisPage, 10, URI + "?page=");
		//out.print("樣式一："+pages.subPageCss1());
		//out.print("</br>");
		out.print(pages.subPageCss2());
	%>
</body>
</html>