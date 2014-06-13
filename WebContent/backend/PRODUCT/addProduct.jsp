<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%@ page import="java.util.*"%>

<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO");
System.out.println("來自addProduct.jsp 的  productVO="+productVO);
PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4005);
List<PowVO> list = (List<PowVO>)session.getAttribute("list");
%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<title>新增商品</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	window.onload = function(){
		CKEDITOR.replace( 'description' ,{toolbar: 'Full' , skin :  'moono' });
	}
	function test() {  
	    var editor_data = CKEDITOR.instances.content.getData();  
	    if (editor_data== null  || editor_data== "" ){  
	        alert( "請填寫商品簡介內容" );  
	        return  false ;  
	    }  
	}  
</script>

<script>
function miracle() {
	document.getElementsByName("productname")[0].value = "寵物物語 - 犬貓專用健康圓點針梳 (L)";
	document.getElementsByName("price")[0].value = "249";
	document.getElementsByName("quantity")[0].value = "100";
	document.getElementsByName("minimumquantity")[0].value = "20";	
	document.getElementsByName("status")[0].value = "0";
	document.getElementsByName("keyword")[0].value = "清潔用品,狗,貓,狗狗,貓咪";
	document.getElementsByName("description")[0].value = "說明：針梳 一般使用於增加毛髮蓬鬆或是讓比較濃密的毛髮做有層次的梳理，	透過梳理可以避免毛髮打結，更能經由來回動作按摩皮膚，促進毛髮生長防止皮膚疾病產生。居家照顧時，選擇適合寵物的梳子是很重要的，當然更要每日確實幫寵物梳理，才能得到最好的效果喔！商品特色：適用於中短毛中、大型犬貓使用使用堅固耐用不鏽鋼材質特殊防滑專業握把設計，潮濕狀況下仍能輕鬆梳理快速清理雜毛/不健康的毛每日梳理，讓毛髮健康增加蓬鬆感讓毛髮做有層次的梳理按摩皮膚，促進毛髮生長防止皮膚疾病產生	圓點針梳彎曲設計，不傷愛犬或愛貓";
}
</script>

</head>

<body>
	<%@ include file="/menu1.jsp" %> 

<c:if test="<%=list.contains(powVO)%>"> 
<b><h1>新增商品</h1></b>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
<sql:setDataSource dataSource="jdbc/TestDB" var="relatedProducts" scope="application"/>
	<sql:query var="rs" dataSource="${relatedProducts}">
		SELECT * FROM product
	</sql:query>
<form method="post" action="product.do" enctype="multipart/form-data">
	<table>
		<tr>
			<td>商品名稱:</td>
			<td align="left">
				<input type="text" name="productname" size="45">
			</td>
		</tr>
		<tr>
			<td>商品分類:</td>
			<td>
				<select name="category">
					<option value="">--請選擇商品分類--
					<option value="狗飼料">狗飼料
					<option value="狗玩具">狗玩具
					<option value="狗衣服">狗衣服
					<option value="狗藥品">狗藥品
					<option value="貓飼料">貓飼料
					<option value="貓玩具">貓玩具
					<option value="貓衣服">貓衣服
					<option value="貓藥品">貓藥品
					<option value="其他清潔用品">其他清潔用品
				</select>
			</td>
		</tr>
		<tr>
			<td>售價:</td>
			<td><input type="text" name="price" size="45" value="<%=(productVO==null)?"":productVO.getPrice()%>"></td>
		</tr>
		<tr><td>商品圖片:</td>
			<td><input type="file" name="image1"><input type="file" name="image2"><input type="file" name="image3"></td>
		</tr>		
		<tr>
			<td>商品數量:</td>
			<td><input type="text" name="quantity" size="45" value="<%=(productVO==null)?"":productVO.getQuantity()%>"></td>
		</tr>
		<tr>
			<td>安全庫存量:</td>
			<td><input type="text" name="minimumquantity" size="45" value="<%=(productVO==null)?"":productVO.getMinimumquantity()%>"></td>
		</tr>
		<tr>
			<td>商品狀態:</td>
			<td>
				<select name="status">
					<option value="">--請選擇商品狀態--
					<option value="0">未上架
					<option value="1">上架中
				</select>
			</td>
		</tr>
		<tr>
			<td>商品關鍵字:</td>
			<td><textarea cols="80" name="keyword" rows="1"><%=(productVO==null)?"":productVO.getKeyword()%></textarea></td>
		</tr>
		<tr>
			<td>商品簡介:</td>
			<td><textarea class="ckeditor" id="description" cols="80" name="description" rows="10"><%=(productVO==null)?"":productVO.getDescription()%></textarea>
			<input type="hidden" name="relatedProducts1" value="">
			<input type="hidden" name="relatedProducts2" value="">
			<input type="hidden" name="relatedProducts3" value="">
			</td>
		</tr>
		<tr>
			<td>折扣數:<input name="score" value="0" type="hidden"></td>
			<td><input type="text" name="discount" size="45" value="1.0">(1.0表示不打折)
				<input type="hidden" name="priority" size="45" value="1"></td>
		</tr>
	</table>
	<br>
		<div align='right' style="margin-right:70px;">
			<input name="action" value="insert" type="hidden">
			<input name="submit" value="新增商品" type="submit" onclick="test()">
			<input type = "button" id = "btn" value = "click me" onclick = "miracle()">
		</div>
	</form>
	</c:if>
	
		<%@ include file="/menu2.jsp" %> 
</body>
</html>