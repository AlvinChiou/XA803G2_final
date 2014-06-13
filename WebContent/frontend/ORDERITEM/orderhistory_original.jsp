<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.order.model.*"%>
<jsp:useBean id="ORDERHISTORY" scope="request" type="java.util.Set"></jsp:useBean>
<%OrderVO orderVO =(OrderVO) request.getAttribute("orderhistory");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>訂單查詢</title>
<link href="<%=request.getContextPath()%>/jQueryAssets/jquery.ui.core.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jQueryAssets/jquery.ui.theme.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/jQueryAssets/jquery.ui.accordion.min.css" rel="stylesheet" type="text/css">

<script src="<%=request.getContextPath()%>/jQueryAssets/jquery-ui-1.9.2.accordion.custom.min.js" type="text/javascript"></script>
<style type="text/css">
.myDiv {
  margin-top: 150px;
}
</style>
</head>
<body>
<div class="myDiv">
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li>${message}</li>
			</c:forEach>
		</ul>
	</font>
</c:if>
<div align="center"><b><h2>訂單查詢</h2></b></div>
<c:forEach var="orderVO" items="${ORDERHISTORY}">
<div class="Accordion">
  <h3><a href="#">訂單編號:${orderVO.ordno}</a></h3>
  <div>
    <table width="100%" border="0">
      <tr>
        <td align="center"><strong>訂單編號</strong></td>
        <td align="center"><strong>訂購時間</strong></td>
        <td align="center"><strong>發貨時間</strong></td>
        <td align="center"><strong>到貨時間</strong></td>
        <td align="center"><strong>結案</strong></td>
        <td align="center"><strong>訂單狀態</strong></td>
        
      </tr>
      <tr>
        <td align="center">${orderVO.ordno}</td>
        <td align="center">${orderVO.ordtime}</td>
        <td align="center">${(orderVO.ordgotime==null)?'未出貨':orderVO.ordgotime}</td>
        <td align="center">${(orderVO.ordarrtime==null)?'未送達':orderVO.ordarrtime}</td>
        <td align="center">${(orderVO.orddeltime==null)?'未結案':'已結案'}</td>
        <td align="center">${(orderVO.ordstate==0)?'未出貨':''}${(orderVO.ordstate==1)?'已出貨':''}${(orderVO.ordstate==4)?'已通知付款':''}</td>
        
      </tr>
    </table>
  </div>
  <h3><a href="<%=request.getContextPath()%>/backend/ORDER/order.do?action=VIEWORDERITEM&&ordno=${orderVO.ordno}">檢視明細</a></h3>
  <div>
<%--      <%@ include file="/frontend/ORDERITEM/includedOrderItems.jsp"%>  --%>
	<iframe src="<%=request.getContextPath()%>/backend/ORDER/order.do?action=VIEWORDERITEM&&ordno=${orderVO.ordno}" width="100%" height="100" scrolling="yes" frameborder="0"> 
	</iframe>
  </div>
</div>
<hr>
</c:forEach>
<script type="text/javascript">
$(function() {
	$( ".Accordion" ).accordion(); 
});
</script>
</div>
</body>
</html>
