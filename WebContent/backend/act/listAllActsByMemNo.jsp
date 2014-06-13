<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<% 
PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4001);
List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>

<%-- <% 	ActVO actVO = (ActVO) request.getAttribute("actVO"); %> --%>
<html>
<head>
<title>�Ҧ����u��� - listAllActsByMemNo.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<title>�e��-���ʷs�W - addAct.jsp</title>

</head>
<body bgcolor='white'>

			<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>"> 

	<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
	<table border='1' cellpadding='5' cellspacing='0' width='1500'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' width='20'
			height='20'>
			<td>
				<h3>�Ҧ����u��� - listAllActsByMemNo.jsp</h3> <a href="select_page.jsp"><img
					src="images/back1.gif" width="100" height="32" border="0">�^����</a>
			</td>
		</tr>
	</table>

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
	<%-- <%= ( (ActVO)request.getAttribute("actVO") ).getActNo() %> --%>

	<table border='1' bordercolor='#CCCCFF' width='1500'>
		<tr>
			<th>���ʽs��</th>
			<th>���ʦW��</th>
			<th>���ʤ��e</th>
			<th>�}�l�ɶ�</th>
			<th>�����ɶ�</th>
			<th>���ʷӤ�</th>
			<th>���ɾ���</th>
			<th>����O��</th>
			<th>�D���ú��O��</th>
			<th>���ʳ��W�O��</th>
			<th>�ثe���ʪ��A</th>
			<th>�D���|���s��</th>
			<th>�f��¾���s��</th>

		</tr>

		<c:forEach var="act" items="${listAllActsByMemNo}">
			<tr align='center' valign='middle' >
				
					<td>${act.actNo}</td>
					<td>${act.actName}</td>
					<td>${act.actContent}</td>
					<td>${act.actStartTime}</td>
					<td>${act.actEndTime}</td>
					<td><img src="<%=request.getContextPath()%>/act/DBGifReader3?actNo=${act.actNo}"></td>
					<td>${act.actEquipment}</td>
					<td>${act.actDeposit}</td>
					<td>${act.actHostFee}</td>
					<td>${act.actRegFee}</td>
						<c:if test="${act.actStatus == 'Y'}">
							<td>�}��</td>
						</c:if>
						<c:if test="${act.actStatus == 'N'}">
							<td>�ݼf��</td>
						</c:if>
					<td>${act.memNo}</td>
					<td>${act.empNo}</td>
<!-- 					<td> -->
<!-- 						<FORM METHOD="post" -->
<%-- 							ACTION="<%=request.getContextPath()%>/act/act.do"> --%>
<%-- 							<c:if test="${act.actStatus == 'N'}"> --%>
<!-- 								<input type="submit" value="���f��"> -->
<%-- 								<input type="hidden" name="actNo" value="${act.actNo}"> --%>
<!-- 								<input type="hidden" name="requestURL" -->
<%-- 									value="<%=request.getServletPath()%>"> --%>
<!-- 								<input type="hidden" name="action" value="getOne_For_Update"> -->
<%-- 							</c:if> --%>
<%-- 							<c:if test="${act.actStatus == 'Y'}"> --%>
<!-- 								<input type="submit" value="�w�f��" disabled="disabled"> -->
<%-- 							</c:if> --%>
<!-- 						</FORM> -->
<!-- 					</td> -->

					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">
							<c:if test="${act.actStatus == 'N'}">
								<input type="submit" value="�ק�" > 
								<input type="hidden" name="actNo" value="${act.actNo}">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
								<input type="hidden" name="action" value="getOne_For_Update">
							</c:if>
						</FORM>
					</td>

					<!-- 			<td> -->
					<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do"> --%>

					<%-- 			    <input type="submit" value="�R��" ${(actVO1.actStatus=="Y")? 'disabled=disabled':''} }> --%>
					<%-- 			    <input type="hidden" name="actNo" value="${actVO1.actNo}"> --%>
					<!-- 			    <input type="hidden" name="action"value="delete"></FORM> -->
					<!-- 			</td> -->
				
			</tr>
		</c:forEach>
	</table>


	<!-- <br>�����������|:<br><b> -->
	<%--    <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br> --%>
	<%--    <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b> --%>
			
			</c:if>
			<%@ include file="/menu2.jsp" %> 

</body>
</html>
