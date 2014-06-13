<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>  
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4005);
	System.out.println("productVO : " + productVO );
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
 %>
<html>
<head>
<title>�s��ӫ~���</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<script type="text/javascript">
	window.onload = function(){
		CKEDITOR.replace( 'description' ,{toolbar: 'Full' , skin :  'moono' });
	}	
	function test() {  
	    var editor_data = CKEDITOR.instances.content.getData();  
	    if (editor_data== null  || editor_data== "" ){  
	        alert( "�ж�g�ӫ~²�����e" );  
	        return  false ;  
	    }  
	}  
</script>
</head>
<body>
<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>"> 

<b><h1>�s��ӫ~���</h1></b>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
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
<form method="post" action="<%=request.getContextPath()%>/backend/PRODUCT/product.do" enctype="multipart/form-data">
	<table>
		<tr>
			<td>�ӫ~�s��:</td>
			<td>
				<%=productVO.getProno()%>
				<input type="hidden" name="prono" value="<%=productVO.getProno()%>">
			</td>
		</tr>
		<tr>
			<td>�ӫ~�W��:</td>  
			<td>
				<input type="text" name="productname" size="45" value="<%=productVO.getProductname()%>">
			</td>
		</tr>
		<tr>
			<td>�ӫ~����:</td>
			<td>
				<select name="category">
					<option value="">--�п�ܰӫ~����--
					<option value="���}��" ${(productVO.category=="���}��")?'selected':''}>���}��
					<option value="������" ${(productVO.category=="������")?'selected':''}>������
					<option value="����A" ${(productVO.category=="����A")?'selected':''}>����A
					<option value="���ī~" ${(productVO.category=="���ī~")?'selected':''}>���ī~
					<option value="�߹}��" ${(productVO.category=="�߹}��")?'selected':''}>�߹}��
					<option value="�ߪ���" ${(productVO.category=="�ߪ���")?'selected':''}>�ߪ���
					<option value="�ߦ�A" ${(productVO.category=="�ߦ�A")?'selected':''}>�ߦ�A
					<option value="���ī~" ${(productVO.category=="���ī~")?'selected':''}>���ī~
					<option value="��L�M��Ϋ~" ${(productVO.category=="��L�M��Ϋ~")?'selected':''}>��L�M��Ϋ~
				</select>
			</td>
		</tr>
		<tr>
			<td>���:</td>
			<td><input type="text" name="price" size="45" value="<%=productVO.getPrice()%>"></td>
		</tr>
		<tr>
			<td>�ӫ~�Ϥ�:</td>
			<td>
				<div><img alt="�ӫ~�Ϥ�" src="<%=request.getContextPath()%>/backend/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=1"><br>
				<input type="file" name="image1"></div>				
				
				<div><img alt="�ӫ~�Ϥ�" src="<%=request.getContextPath()%>/backend/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=2"><br>
				<input type="file" name="image2"></div>
				
				<div><img alt="�ӫ~�Ϥ�" src="<%=request.getContextPath()%>/backend/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=3"><br>
				<input type="file" name="image3"></div>
					
			</td>
			
		</tr>	
		<tr>
			<td>�ӫ~�ƶq:</td>
			<td><input type="text" name="quantity" size="45" value="<%=productVO.getQuantity()%>"></td>
		</tr>
		<tr>
			<td>�w���w�s�q:</td>
			<td><input type="text" name="minimumquantity" size="45" value="<%=productVO.getMinimumquantity()%>"></td>
		</tr>
		<tr>
			<td>�ӫ~���A:</td>
			<td>
				<select name="status">				
					<option value="0" ${(productVO.status==0)?'selected':''}>���W�[
					<option value="1" ${(productVO.status==1)?'selected':''}>�W�[��
					<option value="2" ${(productVO.status==2)?'selected':''}>�w�U�[
				</select>
			</td>
		</tr>
		<tr>
			<td>�ӫ~����r:</td>
			<td><textarea cols="80" name="keyword" rows="1"><%=productVO.getKeyword()%></textarea></td>
		</tr>
		<tr>
			<td>�ӫ~²��:</td>
			<td><textarea class="ckeditor" id="description" cols="80" name="description" rows="10"><%=productVO.getDescription()%></textarea>
			<input type="hidden" name="relatedProducts1" value="">
			<input type="hidden" name="relatedProducts2" value="">
			<input type="hidden" name="relatedProducts3" value="">
			</td>
		</tr>
		<tr>
			<td>�馩��:<input name="score" value="0" type="hidden"></td>
			<td><input type="text" name="discount" size="45" value="<%=productVO.getPriority()%>">(1.0��ܤ�����)
			<input type="hidden" name="priority" size="45" value="1">
			</td>
		</tr>
	</table>
		<input type="hidden" name="prono" value="<%=productVO.getProno()%>">
		<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
		<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--�Ω�:istAllProduct.jsp �P �ƦX�d�� listProduct_ByCompositeQuery.jsp-->
		<input name="action" value="update" type="hidden">
		<input name="submit" value="�s��ӫ~" type="submit" onclick="test()">
	</form>
	</c:if>
	
	<%@ include file="/menu2.jsp" %> 
</body>
</html>