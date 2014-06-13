<%@page import="com.mem.model.MemVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* , com.productitem.model.ProdItemVO, com.product.model.*" %>
<jsp:useBean id="shoppingcart" scope="session" type="java.util.List"/>
<jsp:useBean id="prodItemSvc" scope="session" class="com.productitem.model.ProdItemService"/>

<%
	ProductService proSvc = new ProductService();
	List<ProductVO> list = proSvc.getAll();
	pageContext.setAttribute("list", list);
	MemVO mem =(MemVO)session.getAttribute("memVO");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/localization/messages_zh_TW.js "></script>

<link href="style.css" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/jQueryAssets/jquery-ui-1.9.2.accordion.custom.min.js" type="text/javascript"></script>

<style type="text/css">
.myDiv {
  margin-top: 150px;
}
</style>

<script type="text/javascript">
$(function(){
  $('#recdata').validate();
});
//以下是在表單必田欄位加入*
$('#recdata .required').each(function(){
	 $(this).siblings('span').first().prepend('*');
	});
</script>
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
<script type="text/javascript" language="javascript"> 

	$(function() {$(".loadpage").load("/frontend/ORDERITEM/Process.jsp");});

</script> 
</head>

<body>
 <div  class = "click" ></div> 
<div  class = "click1" ></div> 
<div  class = "bg_checkout" > </div> 
<div  class = "content_checkout" >
	<div id="loadpage" align="center">				
	</div>	
</div>     
<div class="myDiv">
<form method="post" id="recdata" action="<%=request.getContextPath()%>/frontend/ORDERITEM/Shopping.do">
<div id="Accordion">
  <div align="center"><h3><a href="#">本次消費紀錄</a></h3></div>
  <div>
    <div class="detial">
  
  <table width="100%" border="1" class="table">
    <tr bgcolor="#00E1D6">
      <td width="60%" align="center"><strong>商品</strong></td>
      <td width="9%" align="center"><strong>數量</strong></td>
      <td width="12%" align="center"><strong>單價</strong></td>
      <td width="9%" align="center"><strong>小計</strong></td>

    </tr>
<%Vector<ProdItemVO> buylist = (Vector<ProdItemVO>) session.getAttribute("shoppingcart");
		String amount = (String) session.getAttribute("amount");
	%>
	<%
		for(int i = 0; i<buylist.size() ; i++){
	 		ProdItemVO order = buylist.get(i);
	 		Integer quantity = order.getItemqty();
	 		Integer price = order.getPrice();
	 		pageContext.setAttribute("order", order);
	 %>	
    <tr>
      <td><c:forEach var="productVO" items="${list}">
					<c:if test="${order.prono == productVO.prono}">
						<a href="<%=request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=${productVO.prono}">${productVO.productname}</a>
					</c:if>
				</c:forEach>
      </td>
      <td align="right"><%=quantity%></td>
      <td align="right"><%=price%></td>
      <td align="right"><%=quantity * price%></td>

      </tr>
	 <% } %>     
    <tr>
      <td>&nbsp;</td>
      <td align="center"><input type="hidden" name="itemmemo"></td>
      <td align="center"><strong>消費總金額:</strong></td>
      <td align="center"><strong><font color="red"><%=amount%></font></strong></td>

    </tr>
 
  </table>

</div>
  </div>
  <div align="center"><h3><a href="#">確認配送資料</a></h3></div>
  <div>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
    <div>	
<table width="100%" border="1" class="table">
  
  <tr>
  	<th align="left" bgcolor="CCCCCC"><strong>姓名</strong></th>
  	<th align="left" bgcolor="CCCCCC"><strong>地址</strong></th>
  </tr>
  <tr>
    <td align="left"><input name="memname" type="text" id="memname" size="50" value="<%=mem.getMemname()%>" class="required">
    <input type="hidden" name="memno" value="<%=mem.getMemno()%>"></td>
    <td align="left"><input name="ordaddr" type="text" id="ordaddr" size="50" value="<%=mem.getMemadd()%>" class="required"></td>
  </tr>
  <tr>
  	<td bgcolor="CCCCCC"><strong>電子信箱</strong></td>
    <td bgcolor="CCCCCC"><strong>連絡電話</strong></td>
  </tr>
  <tr>
    <td align="left">
      <input name="mememail" type="text" id="mememail" size="50" value="<%=mem.getMememail()%>" class="required email"></td>
    <td align="left">
      <input name="memtel" type="text" id="memtel" size="50" value="<%=mem.getMemtel()%>" class="required"></td>
  </tr>
  <tr>
  	<td align="right">&nbsp;</td>
    <td><input type="checkbox" name="update" id="checkbox" value="Y">
      <label for="checkbox">一併更新會員資料 </label></td>

  </tr>
</table>
</div>
  </div>
 
</div>


<hr>
<div align="center">
	<input type="submit" value="完成結帳" onclick="if(confirm('確定資料正確嗎?')) return true;else return false">
	<input type="hidden" name="action" value="COMPLETE_ORDERS">		
</div>
</form>
<script type="text/javascript">
$(function() {
	$( "#Accordion1" ).accordion(); 
});
</script>
</div>
</body>
</html>

