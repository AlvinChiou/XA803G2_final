<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* , com.product.model.*, com.order.model.*" %>
<jsp:useBean id="VIEWORDERITEM" scope="request" type="java.util.Set"></jsp:useBean>
<jsp:useBean id="prodItemSvc" scope="page" class="com.order.model.OrderService"/>
<%
	ProductService proSvc = new ProductService();
	List<ProductVO> list = proSvc.getAll();
	pageContext.setAttribute("list", list);
 %>
 <%
	//OrderVO orderVO = (OrderVO) request.getAttribute("orderVO");
	String amount = request.getAttribute("amount").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>無標題文件</title>
</head>

<body>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li>${message}</li>
			</c:forEach>
		</ul>
	</font>
</c:if>
<table width="100%" border="0">
      <tr>
        <td align="center"><strong>商品編號</strong></td>
        <td align="center"><strong>商品名稱</strong></td>
        <td align="center"><strong>購買數量</strong></td>
        <td align="center"><strong>商品單價</strong></td>
		<td align="center"><strong>小計</strong></td>
      </tr>
      <c:forEach var="prodItemVO" items="${VIEWORDERITEM}">
      <tr>
        <td align="center">${prodItemVO.prono}</td>
        <td align="center">
        <c:forEach	var="productVO" items="${list}">
        
				<c:if test="${prodItemVO.prono == productVO.prono}">
					${productVO.productname}
				</c:if>
			 </c:forEach></td>
        <td align="center">${prodItemVO.itemqty}</td>
        <td align="center">${prodItemVO.price}</td>
        <td align="center">${prodItemVO.itemqty * prodItemVO.price}</td>
      </tr>
      </c:forEach>
      <tr>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center"><strong>總金額:</strong></td>
        <td align="center"><strong><font color="red"><%=amount%></font></strong></td>
      </tr>
    </table>
</body>
</html>