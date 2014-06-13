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
System.out.println("�Ӧ�addProduct.jsp ��  productVO="+productVO);
PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4005);
List<PowVO> list = (List<PowVO>)session.getAttribute("list");
%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<title>�s�W�ӫ~</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ckeditor/ckeditor.js"></script>
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

<script>
function miracle() {
	document.getElementsByName("productname")[0].value = "�d�����y - ���߱M�ΰ��d���I�w�� (L)";
	document.getElementsByName("price")[0].value = "249";
	document.getElementsByName("quantity")[0].value = "100";
	document.getElementsByName("minimumquantity")[0].value = "20";	
	document.getElementsByName("status")[0].value = "0";
	document.getElementsByName("keyword")[0].value = "�M��Ϋ~,��,��,����,�߫}";
	document.getElementsByName("description")[0].value = "�����G�w�� �@��ϥΩ�W�[��v���P�άO������@�K����v�����h�����޲z�A	�z�L�޲z�i�H�קK��v�����A���g�ѨӦ^�ʧ@�����ֽ��A�P�i��v�ͪ�����ֽ��e�f���͡C�~�a���U�ɡA��ܾA�X�d�����ޤl�O�ܭ��n���A��M��n�C��T�����d���޲z�A�~��o��̦n���ĪG��I�ӫ~�S��G�A�Ω󤤵u�򤤡B�j�����ߨϥΨϥΰ�T�@�Τ��ÿ�����S���ƱM�~����]�p�A���㪬�p�U���໴�P�޲z�ֳt�M�z����/�����d����C��޲z�A����v���d�W�[���P�P����v�����h�����޲z�����ֽ��A�P�i��v�ͪ�����ֽ��e�f����	���I�w���s���]�p�A���˷R���ηR��";
}
</script>

</head>

<body>
	<%@ include file="/menu1.jsp" %> 

<c:if test="<%=list.contains(powVO)%>"> 
<b><h1>�s�W�ӫ~</h1></b>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~
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
			<td>�ӫ~�W��:</td>
			<td align="left">
				<input type="text" name="productname" size="45">
			</td>
		</tr>
		<tr>
			<td>�ӫ~����:</td>
			<td>
				<select name="category">
					<option value="">--�п�ܰӫ~����--
					<option value="���}��">���}��
					<option value="������">������
					<option value="����A">����A
					<option value="���ī~">���ī~
					<option value="�߹}��">�߹}��
					<option value="�ߪ���">�ߪ���
					<option value="�ߦ�A">�ߦ�A
					<option value="���ī~">���ī~
					<option value="��L�M��Ϋ~">��L�M��Ϋ~
				</select>
			</td>
		</tr>
		<tr>
			<td>���:</td>
			<td><input type="text" name="price" size="45" value="<%=(productVO==null)?"":productVO.getPrice()%>"></td>
		</tr>
		<tr><td>�ӫ~�Ϥ�:</td>
			<td><input type="file" name="image1"><input type="file" name="image2"><input type="file" name="image3"></td>
		</tr>		
		<tr>
			<td>�ӫ~�ƶq:</td>
			<td><input type="text" name="quantity" size="45" value="<%=(productVO==null)?"":productVO.getQuantity()%>"></td>
		</tr>
		<tr>
			<td>�w���w�s�q:</td>
			<td><input type="text" name="minimumquantity" size="45" value="<%=(productVO==null)?"":productVO.getMinimumquantity()%>"></td>
		</tr>
		<tr>
			<td>�ӫ~���A:</td>
			<td>
				<select name="status">
					<option value="">--�п�ܰӫ~���A--
					<option value="0">���W�[
					<option value="1">�W�[��
				</select>
			</td>
		</tr>
		<tr>
			<td>�ӫ~����r:</td>
			<td><textarea cols="80" name="keyword" rows="1"><%=(productVO==null)?"":productVO.getKeyword()%></textarea></td>
		</tr>
		<tr>
			<td>�ӫ~²��:</td>
			<td><textarea class="ckeditor" id="description" cols="80" name="description" rows="10"><%=(productVO==null)?"":productVO.getDescription()%></textarea>
			<input type="hidden" name="relatedProducts1" value="">
			<input type="hidden" name="relatedProducts2" value="">
			<input type="hidden" name="relatedProducts3" value="">
			</td>
		</tr>
		<tr>
			<td>�馩��:<input name="score" value="0" type="hidden"></td>
			<td><input type="text" name="discount" size="45" value="1.0">(1.0��ܤ�����)
				<input type="hidden" name="priority" size="45" value="1"></td>
		</tr>
	</table>
	<br>
		<div align='right' style="margin-right:70px;">
			<input name="action" value="insert" type="hidden">
			<input name="submit" value="�s�W�ӫ~" type="submit" onclick="test()">
			<input type = "button" id = "btn" value = "click me" onclick = "miracle()">
		</div>
	</form>
	</c:if>
	
		<%@ include file="/menu2.jsp" %> 
</body>
</html>