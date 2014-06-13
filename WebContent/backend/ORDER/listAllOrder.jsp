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
<title>�Ҧ��q����</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
</head>
<body>
<%@ include file="/menu1.jsp" %> 

<c:if test="<%=listPower.contains(powVO)%>"> 

<div bgcolor="yellow" align="center"><h1><b>�����׭q����</b></h1></div>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
<table border='0' bordercolor='#CCCCFF'>
	<tr>
		<th>�q��s��</th>
		<th>�U��ɶ�</th>
		<th>�t�e�a�}</th>
		<th>�R��s���q��</th>
		<th>�X�f���A</th>
		<th>�O�_�e�F</th>		
		<th>�|���s��</th>
		<th>�H�o�q��</th>
		<th>�˵��q�椺�e</th>
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
					<input type="submit" value="${(orderVO.ordstate)==4?'�ʦ��b��':''}${(orderVO.ordstate)==0?'�q���I��':''}${(orderVO.ordstate)==1?'�w�X�f':''}" ${(orderVO.ordstate)==1?'disabled':''}/>
					<input type="hidden" name="ordno" value="${orderVO.ordno}"/>
					<input type="hidden" name="ordstate" value="4"/>
					<input type="hidden" name="action" value="notification"/>
				</FORM>
			</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/backend/ORDERITEM/Productitem.do">
					<input type="submit" value="�˵��q�椺�e"/>
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