<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productitem.model.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	ProductService proSvc = new ProductService();
	List<ProductVO> list = proSvc.getAll();
	pageContext.setAttribute("list", list);
	
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");

	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4005);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>檢視全部商品</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<script type="text/javascript"> 
function check_all(obj,cName) 
{ 
    var checkboxs = document.getElementsByName(cName); 
    for(var i=0;i<checkboxs.length;i++){checkboxs[i].checked = obj.checked;} 
} 
</script> 
</head>
<body>
	<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>"> 
<div bgcolor="yellow">
<b><h1>檢視所有商品</h1></b>
</div>
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
	<li><a href="<%=request.getContextPath()%>/backend/PRODUCT/select_page.jsp">回首頁</a></li>
</ul>
<table border='1'>	
	<form method="post" action="<%=request.getContextPath()%>/backend/PRODUCT/product.do">
	<tr>
		<td nowrap>上架</td>
		<td nowrap>下架</td>
		<td align="center">塞選條件:</td>
		<td><input type="text" name="productname"></td>
		<td><input type="text" name="category"></td>
		<td><input type="text" name="price"></td>						
		<td colspan="2">
		<select name="status">
			<option value="">--商品狀態--
			<option value="0">未上架
			<option value="1">上架中
			<option value="2">已下架
		</select></td>
		
		<td colspan="2"><input type="submit" value="依條件塞選">
		<input name="action" value="listPorduct_ByCompositeQuery" type="hidden">
		</td>
    </tr>
	</form>
	<tr>
		<th><input type="checkbox" name="all" onclick="check_all(this,'added')"></th>
		<th><input type="checkbox" name="all" onclick="check_all(this,'selectedproduct')"></th>
		<th>商品圖片</th>
		<th>商品名稱</th>
		<th>商品分類</th>
		<th>商品價格</th>						
		<th nowrap>商品狀態</th>
		<th nowrap>現存數量</th>
		<th colspan="2">執行功能</th>
	</tr>
	<%@ include file="pages/page1.file" %>
	<form method="post" action="<%=request.getContextPath()%>/backend/PRODUCT/product.do">		
	<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr align='center' valign='middle' ${(productVO.prono==param.prono)?'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->

        <td><input type = "checkbox" name="added" value="${productVO.prono}" ${(productVO.status==1)?'disabled':''}></td>
        <td><input type = "checkbox" name="selectedproduct" value="${productVO.prono}" ${(productVO.status==2)?'disabled':''}></td>
		<td><div><img alt="image1" src="<%=request.getContextPath()%>/backend/PRODUCT/ProductShowImage.do?prono=${productVO.prono}&&image=1"></div></td>
		<td><a href="<%=request.getContextPath()%>/backend/PRODUCT/product.do?action=getOne_For_Display&prono=${productVO.prono}">${productVO.productname}</a></td>
		<td align="center">${productVO.category}</td>
		<td align="center">${(productVO.price)*(productVO.discount)}</td>						
		<td align="center">${productVO.quantity} ${(productVO.quantity < productVO.minimumquantity)?'<font color="#FF1C19">注意</font>':''}</td>
		<td align="center">${(productVO.status==0)?"未上架":''}${(productVO.status==1)?"已上架":''}${(productVO.status==2)?"已下架":''}</td>
		<td align="center"><a href="<%=request.getContextPath()%>/backend/PRODUCT/product.do?action=getOne_For_Update&prono=${productVO.prono}&whichPage=<%=whichPage%>&requestURL=<%=request.getServletPath()%>">編輯</a>
		</td>
	</tr>
	
	</c:forEach>
	<input type="hidden" name="action" value="CHANGE_PRODUCTS_STATUS">
	<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
	<input type="hidden" name="whichPage" value="<%=whichPage%>"><br>
	<input type="submit" value="變更商品狀態">	
	</form>
</table>
<%@ include file="pages/page2.file" %>
<%@ include file="/menu2.jsp" %> 
</c:if>
</body>
</html>