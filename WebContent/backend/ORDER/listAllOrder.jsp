<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.productitem.model.*"%>
<%@ page import="com.order.model.*"%>
<%@ page import="java.util.*"%>

<%@ page import="com.pow.model.*"%>
<%@ page import="com.doctor.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	OrderService ordSvc = new OrderService();
	List<OrderVO> list = ordSvc.getAll();
	pageContext.setAttribute("list", list);
	
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4006);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>
<jsp:useBean id="orderSvc" scope="page" class="com.order.model.OrderService"/>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>所有訂單資料</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
</head>
<body>
<%@ include file="/menu1.jsp" %> 

<c:if test="<%=listPower.contains(powVO)%>"> 

<div bgcolor="yellow" align="center"><h1><b>未結案訂單資料</b></h1></div>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
<table border='0' bordercolor='#CCCCFF'>
	<tr>
		<th>訂單編號</th>
		<th>下單時間</th>
		<th>配送地址</th>
		<th>買方連絡電話</th>
		<th>出貨狀態</th>
		<th>是否送達</th>		
		<th>會員編號</th>
		<th>寄發通知</th>
		<th>檢視訂單內容</th>
	</tr>
	
	<c:forEach var="orderVO" items="${orderSvc.all}">
		<tr align='center' valign="middle" ${(orderVO.ordno==param.ordno) ? 'bgcolor=#FFD8AF':''}>
			<td>${orderVO.ordno}</td>
			<td>${orderVO.ordtime}</td>
			<td>${orderVO.ordaddr}</td>
			<td>${orderVO.ordtel}</td>
			<td>${(orderVO.ordgotime)==null?"X":"V"}</td>
			<td>${(orderVO.ordarrtime)==null?"X":"V"}</td>
			<td>${orderVO.memno}</td>
			<td>
				<FORM method="post" action="<%=request.getContextPath()%>/backend/ORDER/Sendmail.do">
					<input type="submit" value="${(orderVO.ordstate)==4?'催收帳款':''}${(orderVO.ordstate)==0?'通知付款':''}${(orderVO.ordstate)==1?'已出貨':''}" ${(orderVO.ordstate)==1?'disabled':''}/>
					<input type="hidden" name="ordno" value="${orderVO.ordno}"/>
					<input type="hidden" name="ordstate" value="4"/>
					<input type="hidden" name="action" value="notification"/>
				</FORM>
			</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/backend/ORDERITEM/Productitem.do">
					<input type="submit" value="檢視訂單內容"/>
					<input type="hidden" name="ordno" value="${orderVO.ordno}"/>
					<!-- <input type="hidden" name="action" value="listOrderItemsByOrdNo"/> -->
					<input type="hidden" name="action" value="getone_For_Update"/>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
</c:if>
<%@ include file="/menu2.jsp" %> 

</body>
</html>