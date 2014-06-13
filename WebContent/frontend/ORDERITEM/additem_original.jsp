<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* , com.productitem.model.ProdItemVO, com.product.model.*" %>
<%@page import="com.mem.model.*"%>
<%  MemVO mem =(MemVO)session.getAttribute("memVO");%>
<jsp:useBean id="shoppingcart" scope="session" type="java.util.List"/>
<jsp:useBean id="prodItemSvc" scope="session" class="com.productitem.model.ProdItemService"/>

<%
	ProductService proSvc = new ProductService();
	List<ProductVO> list = proSvc.getAll();
	pageContext.setAttribute("list", list);
 %>
 <% String amount = (String)session.getAttribute("amount1"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<link href="<%=request.getContextPath()%>/frontend/shop_css/style.css" rel="stylesheet" type="text/css">
<script>

$ ( function ( ) { 
	var pageFrom = '<%=(request.getAttribute("pageFrom")==null)?"":request.getAttribute("pageFrom")%>';
	//alert(pageFrom);
	if(pageFrom != ''){
		openDiv();
	}
    //$ ( '.cart' ) . click ( openDiv) ; 
    $ ( '.bg_checkout' ) . click ( closeDiv ) ; 
} ) ;

function openDiv(){
	$ ( '.bg_checkout' ) . css ( { 'display' : 'block' } ) ; 
    $ ( '.content_checkout' ) . css ( { 'display' : 'block' } ) ; 
}

function closeDiv(){
	$ ( '.bg_checkout' ) . css ( { 'display' : 'none' } ) ; 
    $ ( '.content_checkout' ) . css ( { 'display' : 'none' } ) ; 
    $ ('.loadpage') . css ({'display' : 'none'}); 
}
</script>

</head>
<body>
<c:if test="${(list)==null}">
	<c:redirect url="/frontend/ORDERITEM/market.jsp"></c:redirect>
</c:if>
<div  class = "click" ></div> 
<div  class = "click1" ></div> 
<div  class = "bg_checkout" > </div> 
<div  class = "content_checkout" >
	<div id="loadpage" align="center">
		<iframe src="<%=request.getContextPath()%>/frontend/ORDERITEM/checkout.jsp" width="800" height="500" scrolling="no">
		</iframe>		
	</div>	
</div>
<%Vector<ProdItemVO> buylist = (Vector<ProdItemVO>) session.getAttribute("shoppingcart");%>
<%  System.out.println("buylist="+((ProdItemVO)(buylist.get(0))).getProno()); %>
<%if (buylist != null && (buylist.size() > 0)) {%>
<div class="detial">

  <table width="100%" border="0">
    <tr>
    	<td colspan="6" align="center"><h2><b>購物明細</b></h2></td>
    </tr>
    <tr bgcolor="#00E1D6">
      <td width="10%" align="center"><strong>商品照片</strong></td>
      <td width="58%" align="center"><strong>商品</strong></td>
      <td width="11%" align="center"><strong>數量</strong></td>
      <td width="12%" align="center"><strong>單價</strong></td>
      <td width="9%" align="center"><strong>小計</strong></td>
      <td width="10%" align="center"><strong>變更</strong></td>
    </tr>
    <% 
		for(int index = 0;index<buylist.size();index++){
		ProdItemVO order = buylist.get(index);	
		pageContext.setAttribute("order", order);
	%>
    <tr>
      <td align="left">
      	<c:forEach var="productVO" items="${list}">
				<c:if test="${order.prono==productVO.prono}">
					<div style="display: inline; width: 30%"><a href="<%=request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=${productVO.prono}"><img alt="商品照片" src="<%=request.getContextPath()%>/backend/PRODUCT/ProductShowImage.do?prono=${productVO.prono}&&image=1"></a></div>											
				</c:if>
			</c:forEach>
      </td>
      <td align="left"">
      	<c:forEach var="productVO" items="${list}">
				<c:if test="${order.prono==productVO.prono}">
					<div align="center" style="display: inline; width: 50%"><a href="<%=request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=${productVO.prono}">${productVO.productname}</a></div>						
				</c:if>
			</c:forEach>
      </td>
      <td align="right"><%=order.getItemqty()%></td>
      <td align="right"><%=order.getPrice()%></td>
      <td align="right"><%=(order.getItemqty()*order.getPrice())%></td>
      <td align="center">
      <form name="deleteForm" method="post" action="<%=request.getContextPath()%>/frontend/ORDERITEM/Shopping.do">
				
				<input type="hidden" name="action" value="DELETE">
				<input type="hidden" name="del" value="<%=index%>">
				<input type="submit" value="刪除" <%=(request.getAttribute("pageFrom")==null)?"":"disabled"%>>
			</form></td>
      </tr>
	 <% } %>     
    <tr>
      <td>&nbsp;</td>
      <td align="center">&nbsp;</td>
      <td align="right"><strong>消費總金額:</strong></td>
      <td align="right"><strong><font color="red"><%=amount%></font></strong></td>
  	  <td align="center">&nbsp;</td>
    </tr>
 
  </table>

</div>
 <% } %>
<hr>
<div align="center">
	<form id="checkoutForm" name="checkoutForm" method="post" action="<%=request.getContextPath()%>/frontend/ORDERITEM/Shopping.do">			
			<input type="hidden" name="action" value="CHECKOUT">
			<input type="submit" value="前往結帳" onclick="this.disabled=true;this.form.submit();" <%=(request.getAttribute("pageFrom")==null)?"":"disabled"%>>
			<input type="button" value="繼續購物" onclick="javascript:location.href='<%=request.getContextPath()%>/frontend/ORDERITEM/market.jsp'">
		</form>
</div>
</body>
</html>