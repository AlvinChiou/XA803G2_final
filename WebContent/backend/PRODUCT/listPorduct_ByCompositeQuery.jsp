<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="listPorduct_ByCompositeQuery" scope="request" type="java.util.List"/>
<jsp:useBean id="proSvc" scope="page" class="com.product.model.ProductService"/>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>  
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4005);
List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>
<html>
<head>
<title>�̱�����ӫ~</title>
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

<b><h1>�̱�����ӫ~</h1></b>
<ul>
	<li><a href="<%=request.getContextPath()%>/backend/PRODUCT/select_page.jsp">�^����</a></li>
</ul>
<table>
	<form method="post" action="<%=request.getContextPath()%>/backend/PRODUCT/product.do">
	<tr>
		<td>�W�[</td>
		<td>�U�[</td>
		<td align="center">������:</td>
		<td><input type="text" name="productname"></td>
		<td><input type="text" name="category"></td>
		<td><input type="text" name="price"></td>						
		<td colspan="2"><select name="status">
			<option value="">--�ӫ~���A--
			<option value="0">���W�[
			<option value="1">�W�[��
			<option value="2">�w�U�[
		</select></td>
		<td colspan="2"><input type="submit" value="�̱�����">
		<input name="action" value="listPorduct_ByCompositeQuery" type="hidden">
		</td>

        </tr>
	</form> 
	<tr>
		<th><input type="checkbox" name="all" onclick="check_all(this,'added')"></th>
		<th><input type="checkbox" name="all" onclick="check_all(this,'selectedproduct')"></th>
		<th>�ӫ~�Ϥ�</th>
		<th>�ӫ~�W��</th>
		<th>�ӫ~����</th>
		<th>�ӫ~����</th>				
		<th>�{�s�ƶq</th>
		<th>�ӫ~���A</th>
		<th colspan="2">����\��</th>
	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<form method="post" action="<%=request.getContextPath()%>/backend/PRODUCT/product.do">
	<c:forEach var="productVO" items="${listPorduct_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr align='center' valign='middle' ${(productVO.prono==param.prono)?'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->

        <td><input type = "checkbox" name="added" value="${productVO.prono}" ${(productVO.status==1)?'disabled':''}></td>
        <td><input type = "checkbox" name="selectedproduct" value="${productVO.prono}" ${(productVO.status==2)?'disabled':''}></td>
		<td><div><img alt="image1" src="<%=request.getContextPath()%>/backend/PRODUCT/ProductShowImage.do?prono=${productVO.prono}&&image=1"></div></td>
		<td><a href="<%=request.getContextPath()%>/backend/PRODUCT/product.do?action=getOne_For_Display&prono=${productVO.prono}">${productVO.productname}</a></td>
		<td align="center">${productVO.category}</td>
		<td align="center">${(productVO.price)*(productVO.discount)}</td>		
		<td align="center">${productVO.quantity} ${(productVO.quantity < productVO.minimumquantity)?'<font color="#FF1C19">�`�N</font>':''}</td>
		<td align="center">${(productVO.status==0)?"���W�[":''}${(productVO.status==1)?"�w�W�[":''}${(productVO.status==2)?"�w�U�[":''}</td>
		<td align="center"><a href="<%=request.getContextPath()%>/backend/PRODUCT/product.do?action=getOne_For_Update&prono=${productVO.prono}&whichPage=<%=whichPage%>&requestURL=<%=request.getServletPath()%>">�s��</a>
		</td>
	</tr>
	</c:forEach>
	<input type="hidden" name="action" value="CHANGE_PRODUCTS_STATUS">
	<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
	<input type="hidden" name="whichPage" value="<%=whichPage%>"><br>
	<input type="submit" value="�ܧ�ӫ~���A">	
	</form>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>
</c:if>
<%@ include file="/menu2.jsp" %> 
</body>
</html>