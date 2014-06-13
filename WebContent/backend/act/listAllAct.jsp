<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	ActService actSvc = new ActService();
	List<ActVO> list = actSvc.getAll();
	pageContext.setAttribute("list", list);
	
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4001);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>

<%-- <% 	ActVO actVO = (ActVO) request.getAttribute("actVO"); %> --%>
<html>
<head>
<title>���-�Ҧ����ʸ�� - listAllAct.jsp</title>
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
				<h3>���-�Ҧ����ʸ�� - ListAllAct.jsp</h3> <a href="select_page.jsp"><img
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

	<table border='1' bordercolor='#CCCCFF' width='1000'>
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
		<%@ include file="page1.file"%>
		<c:forEach var="actVO1" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle' ${(actVO1.actNo == fromBackUpdateInput.actNo) ? 'bgcolor=#CCCCFF':''}>
				
			
					<td>${actVO1.actNo}</td>
					<td nowrap>${actVO1.actName}</td>
					<td>${actVO1.actContent}</td>
					<td>${actVO1.actStartTime}</td>
					<td>${actVO1.actEndTime}</td>
					<td><img src="<%=request.getContextPath()%>/act/DBGifReader3?actNo=${actVO1.actNo}"></td>
					<td>${actVO1.actEquipment}</td>
					<td>${actVO1.actDeposit}</td>
					<td>${actVO1.actHostFee}</td>
					<td>${actVO1.actRegFee}</td>
					<td>	
						<c:if test="${actVO1.actStatus == 'Y'}">�}��</c:if>
						<c:if test="${actVO1.actStatus == 'N'}">�f�֤�</c:if>
						<c:if test="${actVO1.actStatus == 'A'}">�p�H����<br>�f�֤�</c:if>
						<c:if test="${actVO1.actStatus == 'B'}">>�p�H����</td></c:if>
						<c:if test="${actVO1.actStatus == 'E'}">���ʵ���</c:if>
					<td>${actVO1.memNo}</td>
					<td>${actVO1.empNo}</td>

					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">
							<c:if test="${actVO1.actStatus == 'N' || actVO1.actStatus == 'A'}">
								<input type="submit" value="�f��">
								<input type="hidden" name="actNo" value="${actVO1.actNo}">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<input type="hidden" name="action" value="getOne_For_Update_back">
							</c:if>
						</FORM>
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">
							<c:if test="${actVO1.actStatus == 'Y' || actVO1.actStatus == 'B'}">
								<input type="submit" value="�w�f��" disabled="disabled">
							</c:if>
						</FORM>
						
<%-- 						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">	 --%>
<%-- 							<c:if test="${actVO1.actStatus == 'Y' && actVO1.memNo != '7003'}"> --%>
<!-- 								<input type="submit" value="���W" > -->
<%-- 								<input type="hidden" name="actNo" value="${actVO1.actNo}"> --%>
<!-- 								<input type="hidden" name="action" value="getOne_For_ActRegister"> -->
<%-- 							</c:if> --%>
<!-- 						</FORM> -->
						
<%-- 						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/actRegister/actRegister.do"> --%>
<%-- 							  <c:if test="${actVO1.memNo == '7003'}"> --%>
<!-- 							   		<input type="submit" value="�d�߳��W���p" > -->
<%-- 							    	<input type="hidden" name="actNo" value="${actVO1.actNo}"> --%>
<!-- 							    	<input type="hidden" name="action"value="getAll_For_Display_By_ActNo"> -->
<%-- 							  </c:if> --%>
<!-- 						</FORM> -->
							    
<%-- 						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do"> --%>
<!-- 							  <input type="submit" value="�ק�" }> -->
<%-- 							  <input type="hidden" name="actNo" value="${actVO1.actNo}"> --%>
<%-- 							  <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<!-- 							  <input type="hidden" name="action"value="getOne_For_Update_back"> -->
<!-- 						</FORM> -->
					</td>
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
