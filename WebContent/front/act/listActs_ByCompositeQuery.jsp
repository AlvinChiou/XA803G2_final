<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>

<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>

<jsp:useBean id="listActs_ByCompositeQuery" scope="request" type="java.util.List" />
<%-- <% List<ActVO> listActs_ByCompositeQuery = (List<ActVO>) request.getAttribute("listActs_ByCompositeQuery"); %> --%>
<%-- <jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<html>
<head>
<title>�e��-�ƦX�d�� - listEmps_ByCompositeQuery.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=blue>
���U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ����<br>
�������u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='1500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3><font color=red>�e��-�ƦX�d��</font>���u - listEmps_ByCompositeQuery.jsp</h3>
		<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>


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
	<c:forEach var="actVO1" items="${listActs_ByCompositeQuery}">
		<tr align='center' valign='middle'>
			<td>${actVO1.actNo}</td>
			<td>${actVO1.actName}</td>
			<td>${actVO1.actContent}</td>
			<td>${actVO1.actStartTime}</td>
			<td>${actVO1.actEndTime}</td>
			<td><img src="DBGifReader3?actNo=${actVO1.actNo}"></td>
			<td>${actVO1.actEquipment}</td>
			<td>${actVO1.actDeposit}</td>
			<td>${actVO1.actHostFee}</td>
			<td>${actVO1.actRegFee}</td>
						<c:if test="${actVO1.actStatus == 'Y'}">
							<td>���W��</td>
						</c:if>
						<c:if test="${actVO1.actStatus == 'N'}">
							<td>�f�֤�</td>
						</c:if>
						<c:if test="${actVO1.actStatus == 'A'}">
							<td>�p�H����<br>
								���f��
							</td>
						</c:if>
						<c:if test="${actVO1.actStatus == 'B'}">
							<td>�p�H����<br></td>
						</c:if>
			<td>${actVO1.memNo}</td>
			<td>${actVO1.empNo}</td>
			<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">
							<c:if test="${(actVO1.actStatus == 'N' || actVO1.actStatus == 'A') && actVO1.memNo == '7005'}">
								<input type="submit" value="�ק�">
								<input type="hidden" name="actNo" value="${actVO1.actNo}">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<input type="hidden" name="action" value="getOne_For_Update">
							</c:if>
						</FORM>
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">	
							<c:if test="${actVO1.actStatus == 'Y' && actVO1.memNo != '7005'}">
								<input type="submit" value="���W" >
								<input type="hidden" name="actNo" value="${actVO1.actNo}">
								<input type="hidden" name="action" value="getOne_For_ActRegister">
							</c:if>
						</FORM>
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/actRegister/actRegister.do">
							  <c:if test="${actVO1.memNo == '7005' && actVO1.actStatus == 'Y'}">
							   		<input type="submit" value="�d�߳��W���p" >
							    	<input type="hidden" name="actNo" value="${actVO1.actNo}">
							    	<input type="hidden" name="action"value="getAll_For_Display_By_ActNo">
							  </c:if>
						</FORM>
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do">
								<c:if test="${(actVO1.actStatus == 'N' || actVO1.actStatus == 'A') && actVO1.memNo == '7005'}">
							    	<input type="submit" value="�R��" >
							    	<input type="hidden" name="actNo" value="${actVO1.actNo}">
							    	<input type="hidden" name="action"value="delete">
							    </c:if>
						</FORM>
						
					</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>
