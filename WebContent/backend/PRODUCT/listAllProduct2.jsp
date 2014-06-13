<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>  
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
<title>�˵������ӫ~</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
</head>
<body>
	<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>"> 
<div bgcolor="yellow">
<b><h1>�˵��Ҧ��ӫ~</h1></b>
</div>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
<ul>
	<li><a href="<%=request.getContextPath()%>/backend/PRODUCT/select_page.jsp">�^����</a></li>
</ul>
<table border='1'>
	<tr>
		<th>�ӫ~�s��</th>
		<th>�ӫ~�W��</th>
		<th>�ӫ~����</th>
		<th>�ӫ~����</th>
		<th>�D�n�Ϥ�</th>
		<th>���n�Ϥ�2</th>
		<th>���n�Ϥ�3</th>
		<th>�{�s�ƶq</th>
		<th>�w���s�q</th>
		<th>�ӫ~���A</th>
		<th>����r</th>
		<th>�ӫ~²��</th>
		<th>�馩</th>
		<th>�s��</th>
		<th>�R��</th>
	</tr>
	<%@ include file="pages/page1.file" %>
	<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr align='center' valign='middle' ${(productVO.prono==param.prono)?'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
		<td>${productVO.prono}</td>
		<td>${productVO.productname}</td>
		<td>${productVO.category}</td>
		<td>${(productVO.price)*(productVO.discount)}</td>
		<td><div><img alt="image1" src="<%=request.getContextPath()%>/backend/PRODUCT/ProductShowImage.do?prono=${productVO.prono}&&image=1"></div></td>
		<td><div><img alt="image1" src="<%=request.getContextPath()%>/backend/PRODUCT/ProductShowImage.do?prono=${productVO.prono}&&image=2"></div></td>
		<td><div><img alt="image1" src="<%=request.getContextPath()%>/backend/PRODUCT/ProductShowImage.do?prono=${productVO.prono}&&image=3"></div></td>
		<td>${productVO.quantity}</td>
		<td>${productVO.minimumquantity}</td>
		<td>${(productVO.status==0)?"���W�[":''}${(productVO.status==1)?"�w�W�[":''}${(productVO.status==2)?"�w�U�[":''}</td>
		<td>${productVO.keyword}</td>
		<td>${productVO.description}</td>
		<td>${(productVO.discount<1.0)?"�u�f��":"�L�馩"}</td>
		<td>
			<form method="post" action="<%=request.getContextPath()%>/backend/PRODUCT/product.do">
				<input type="submit" value="�s��">
				<input type="hidden" name="prono" value="${productVO.prono}">
				<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				<input type="hidden" name="whichPage" value="<%=whichPage%>">
				<input type="hidden" name="action" value="getOne_For_Update">
			</form>
		</td>
		<td>
			<form method="post" action="<%=request.getContextPath()%>/backend/PRODUCT/product.do">
				<input type="submit" value="�R��">
				<input type="hidden" name="prono" value="${productVO.prono}">
				<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				<input type="hidden" name="whichPage" value="<%=whichPage%>">
				<input type="hidden" name="action" value="delete">
			</form>
		</td>
	</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>
<%@ include file="/menu2.jsp" %> 
</c:if>
</body>
</html>