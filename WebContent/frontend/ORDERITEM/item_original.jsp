<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*,  com.productitem.model.ProdItemVO"%>
<% 
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%> 

<%--
String relatedProducts1 = null;
String relatedProducts2 = null;
String relatedProducts3 = null;
	System.out.println("relatedProducts="+request.getAttribute("relatedProducts"));
	System.out.println(request.getAttribute("relatedProducts1"));
	System.out.println(request.getAttribute("relatedProducts2"));
	System.out.println(request.getAttribute("relatedProducts3"));
	if(request.getAttribute("relatedProducts") != null){
		relatedProducts1 = request.getAttribute("relatedProducts1").toString();
		relatedProducts2 = request.getAttribute("relatedProducts2").toString();
		relatedProducts3 = request.getAttribute("relatedProducts3").toString();
	}
--%>
<%--�ھ�ProductItemVO���X������productVO����--%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link href="<%=request.getContextPath()%>/frontend/shop_css/style.css" rel="stylesheet" type="text/css">
<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
<script>

$ ( function ( ) { 
	var pageFrom = '<%=(request.getAttribute("pageFrom")==null)?"":request.getAttribute("pageFrom")%>';
	//alert(pageFrom);
	if(pageFrom != ''){
		openDiv();
	}
    //$ ( '.cart' ) . click ( openDiv) ; 
    $ ( '.bg' ) . click ( closeDiv ) ; 
} ) ;

function openDiv(){
	$ ( '.bg' ) . css ( { 'display' : 'block' } ) ; 
    $ ( '.content' ) . css ( { 'display' : 'block' } ) ; 
}

function closeDiv(){
	$ ( '.bg' ) . css ( { 'display' : 'none' } ) ; 
    $ ( '.content' ) . css ( { 'display' : 'none' } ) ; 
}
</script>
<title><%=productVO.getProductname()%></title>
</head>
<body>
<div  class = "click" ></div> 
<div  class = "click1" ></div> 
<div  class = "bg" > </div> 
<div  class = "content" >
	<div align="center">
		<p><img src="<%=request.getContextPath()%>/images/check.png"></p>
		<p>�ӫ~�w�[�J�ʪ����I</p>
		<input type="button" onclick="javascript:location.href='<%=request.getContextPath()%>/frontend/ORDERITEM/market.jsp'" value="�~���ʪ�">
		<input type="button" onclick="javascript:location.href='<%=request.getContextPath()%>/frontend/ORDERITEM/additem.jsp'" value="�e�����b">
	</div>	
</div>
<%Vector<ProdItemVO> buylist = (Vector<ProdItemVO>) session.getAttribute("shoppingcart");%>
<table width="100%" border="0">
  <tr>
    <td width="62%" align="center"><img alt="image1" src="<%=request.getContextPath()%>/backend/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=10"></td>
    <td>
    <h2><%=productVO.getProductname()%></h2>
    <form method="post" action="<%=request.getContextPath()%>/frontend/ORDERITEM/Shopping.do">
    <h2><b><font color="red">������:${productVO.price}</font></b><br></h2>
    <p>
    �ʶR�ƶq:<select name="itemqty" ${(productVO.quantity<=0)?'disabled':''}>
				<option value="1" selected>${(productVO.quantity<=0)?'�ݸɳf':'1'}
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
			</select><p>
			<input type="submit" class="cart" value="�[�J�ʪ���" ${(productVO.quantity<=0)?'disabled':''}>
			<%if (buylist != null && (buylist.size() > 0)) {%>
				<input type="button" onclick="javascript:location.href='<%=request.getContextPath()%>/frontend/ORDERITEM/additem.jsp'" value="�˵��ʪ���">
			<% }%>
			<input type="hidden" name="productname" value="${productVO.productname}">
			<input type="hidden" name="price" value="${(productVO.price)}">
			<input type="hidden" name="prono" value="${productVO.prono}">
			<input type="hidden" name="action" value="ADDFROMITEM">	    
    </form></td>
  </tr>
</table>
<div id="description">
<hr>
<span align="center"><b><h3>�ӫ~����</h3></b></span>
  <div align="center">
  	<img alt="image1" src="<%=request.getContextPath()%>/backend/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=100"><br>
    <img alt="image1" src="<%=request.getContextPath()%>/backend/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=200"><br>
    <img alt="image1" src="<%=request.getContextPath()%>/backend/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=300"><br>
    </div>
    <article><%=productVO.getDescription()%></article>
    
<hr>
     <form method="post" action="<%=request.getContextPath()%>/frontend/ORDERITEM/Shopping.do">  
    �ʶR�ƶq:<select name="itemqty" ${(productVO.quantity<=0)?'disabled':''}>
				<option value="1" selected>${(productVO.quantity<=0)?'�ݸɳf':'1'}
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
			</select>
			<input type="submit" class="cart" value="�[�J�ʪ���" ${(productVO.quantity<=0)?'disabled':''}>
			<%if (buylist != null && (buylist.size() > 0)) {%>
				<input type="button" onclick="javascript:location.href='<%=request.getContextPath()%>/frontend/ORDERITEM/additem.jsp'" value="�˵��ʪ���">
			<% }%>
			<input type="hidden" name="productname" value="${productVO.productname}">
			<input type="hidden" name="price" value="${(productVO.price)}">
			<input type="hidden" name="prono" value="${productVO.prono}">
			<input type="hidden" name="action" value="ADDFROMITEM">	    
    </form>
  <p>&nbsp;</p>
</div>
<p>&nbsp;</p>
</body>
</html>