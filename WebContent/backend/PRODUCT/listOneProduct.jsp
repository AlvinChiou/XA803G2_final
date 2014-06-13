<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*" %>
<%@ page import="com.productitem.model.*" %>    
<%--Controller�w�w���N�d�ߧ�������Ʃ�J ProductVO�æs�Jrequest--%>
<%ProductVO productVO = (ProductVO) request.getAttribute("productVO"); %>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>  
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%--�ھ�ProductItemVO���X������productVO����--%>
<% 
	ProdItemService prodItemSvc = new ProdItemService();
PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4005);
List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�ӫ~���</title>
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
<div><h1><b>�ӫ~���(�s��:<%=productVO.getProno()%>)</b></h1></div>
<table border='1'>
	
	<tr>
		<th>�ӫ~�W��</th>
		<th>�ӫ~����</th>
		<th>�ӫ~����</th>
		<th>�{�s�ƶq</th>
		<th>�w���s�q</th>
		<th>�ӫ~���A</th>
		<th>����r</th>		
		<th>�馩</th>
		
	</tr>
	<tr align='center' valign='middle'>
		<td><%=productVO.getProductname()%></td>
		<td><%=productVO.getCategory()%></td>
		<td><%=productVO.getPrice()%></td>
		<td><%=productVO.getQuantity()%></td>
		<td><%=productVO.getMinimumquantity()%></td>
		<td><%=productVO.getStatus()%></td>
		<td><%=productVO.getKeyword()%></td>		
		<td><%=productVO.getDiscount()%></td>		
	</tr>
	<tr>
		<td colspan="8" align="center"><img alt="image1" src="<%=request.getContextPath()%>/backend/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=1"></td>				
	</tr>
	<tr>
		<td colspan="8" align="center"><img alt="image2" src="<%=request.getContextPath()%>/backend/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=2"></td>
	</tr>
	<tr>
		<td colspan="8" align="center"><img alt="image3" src="<%=request.getContextPath()%>/backend/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=3"></td>
	</tr>
</table>
<%@ include file="/menu2.jsp" %> 
</c:if>
</body>
</html>