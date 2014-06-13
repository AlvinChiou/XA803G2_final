<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%@ page import="com.actregister.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	ActService actSvc = new ActService();
	List<ActVO> list = actSvc.getAll();
	pageContext.setAttribute("list", list);
	
// 	ActRegisterService actRegSvc = new ActRegisterService();
// 	List<ActRegisterVO> actReg = actRegSvc.getAllActRegistersByMemNo(7005);
// 	pageContext.setAttribute("actReg", actReg);

%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmployeeService"/>
<jsp:useBean id="actRegSvc1" scope="page" class="com.actregister.model.ActRegisterService" /> 

<%-- <% 	ActVO actVO = (ActVO) request.getAttribute("actVO"); %> --%>
<html>
<head>
<title>�e��-�Ҧ����� - listAllAct.jsp</title>
<link href="<%= request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css" />
<link href="<%= request.getContextPath()%>/css/box_css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%= request.getContextPath()%>/js/jquery-1.4.2.js"></script>  
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/jquery.fancybox-1.3.4.css" media="screen" />
	
<script type="text/javascript">
	$(document).ready(function() {
		/*
		*   Examples - images
		*/

		$("a#example1").fancybox();
		$("#various3").fancybox({
			'width'				: '75%',
			'height'			: '75%',
			'autoScale'			: false,
			'transitionIn'		: 'none',
			'transitionOut'		: 'none',
			'type'				: 'iframe'
		});
	});
	
</script>

</head>

<body bgcolor='white'>
	
	<div id="wrapper">
  		<div id="main_bg">
    	<div id="main_bg01">
        	<div id="apDiv13"></div>
			<c:if test="${memVO == null}"><%@ include file="/header.jsp" %></c:if>
        	<c:if test="${memVO != null}"><%@ include file="/loginHeader.jsp" %></c:if>
			<%@ include file="/ad.jsp" %>   
  		</div>
		</div>
<a id="titleimg"><img src="<%= request.getContextPath()%>/images/main_tit03.png"></a>
<br>
<p style="text-align:right; margin-right:50px; color:orange;">�ثe��m�G<a href="<%=request.getContextPath()%>/iPETCares.jsp">����</a>>><a href="<%=request.getContextPath()%>/front/act/listAllAct.jsp">�Ҧ����ʸ�T</a></p>

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
<%-- 	<%= ( (ActVO)request.getAttribute("actVO") ).getActNo() %> --%>

	<table border='1' bordercolor='#CCCCFF' width='950' align='center'>
<!-- 		<tr> -->
<!-- 			<th nowrap>���ʽs��</th> -->
<!-- 			<th nowrap>�ثe���A</th> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<th nowrap>���ʦW��</th> -->
<!-- 			<th nowrap>���ʤ��e</th> -->
<!-- 			<th nowrap>�}�l�ɶ�</th> -->
<!-- 			<th nowrap>�����ɶ�</th> -->
<!-- 			<th>���ʷӤ�</th> -->
<!-- 			<th>���ɾ���</th> -->
<!-- 			<th>����O��</th> -->
<!-- 			<th>�D���ú��O��</th> -->
<!-- 			<th nowrap>���W�O��</th> -->
<!-- 			<th nowrap>�D��|��</th> -->
<!-- 			<th>�f��¾���s��</th> -->

<!-- 		</tr> -->
		 
		<c:forEach var="actVO1" items="${list}" >
<%-- 			<tr align='center' valign='middle' ${(actVO1.actNo==actVO.actNo) ? 'bgcolor=#CCCCFF':''}> --%>
				<c:if test="${actVO1.actStatus == 'Y' || ((actVO1.actStatus == 'N' || actVO1.actStatus == 'A' || actVO1.actStatus == 'B' ) && actVO1.memNo == memVO.memno)}">
				<tr valign='middle' ${(actVO1.actNo==actVO.actNo) ? 'bgcolor=#CCCCFF':''}>
					<th>���ʽs��</th><td>${actVO1.actNo}</td>
					
					<th>�ثe���A</th><td colspan='3'><c:if test="${actVO1.actStatus == 'Y'}">���W��</c:if>
									  <c:if test="${actVO1.actStatus == 'N'}">�f�֤�</c:if>
									  <c:if test="${actVO1.actStatus == 'A'}">�p�H����<br>���f��</c:if>
									  <c:if test="${actVO1.actStatus == 'B'}">�p�H����</c:if>
									  <c:if test="${actVO1.actStatus == 'E'}">���ʵ���</c:if>
								  </td>
				</tr>
				<tr>
					<th>���ʦW��</th><td><a href="<%=request.getContextPath()%>/front/act/act.do?action=getOne_For_Display&actNo=${actVO1.actNo}">${actVO1.actName}</a></td>
					<th>�}�l�ɶ�</th><td>${actVO1.actStartTime}</td>
	  	 			<th>�����ɶ�</th><td>${actVO1.actEndTime}</td>
				</tr>
				<tr>
					<th>���W�O��</th><td>${actVO1.actRegFee}</td>
					<th>�D��|��</th><td>
											 <c:forEach var="mem" items="${memSvc.all}">
											 	<c:if test="${actVO1.memNo == mem.memno}">${pageScope.mem.memname} - ${actVO1.memNo}</c:if>
											 </c:forEach>
										 </td>
					
					<td colspan='2'>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/act/act.do" style="display:inline; float:left;">
							<c:if test="${actVO1.memNo == memVO.memno && (actVO1.actStatus == 'N' || actVO1.actStatus == 'A')}">
								<input type="submit" value="�ק�">
								<input type="hidden" name="actNo" value="${actVO1.actNo}">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<input type="hidden" name="action" value="getOne_For_Update">
							</c:if>
						</FORM>
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/act/act.do" style="display:inline;">	
						<c:set  var = "isRegister" value = "false" />
							<c:forEach var="actReg" items="${actRegSvc1.all}">
								<c:if test="${actVO1.actStatus == 'Y' && actReg.memNo == memVO.memno}">
<%-- 								<br/>index : ${s.index} +  ////  actReg :${actReg.actNo}   --%>
<!-- 								�]�m�@�ӱ����ܼ�isRegister,�ӧP�_�O�_�w���W,�w�]�Ofalse	 -->
									
<%-- 								${isRegister} 121 --%>
<!-- 								�p�G�b���j�餺,�o�{�����ʤw���W,�~�אּtrue	 -->
									    <c:if test="${actReg.actNo == actVO1.actNo}">
									    	<c:set  var = "isRegister" value = "true" />	
<%-- 									    	<br/> 125 : ${isRegister} 125 --%>
									    </c:if>
											
										
								</c:if>
							</c:forEach>	
<%-- 							<%=pageContext.getAttribute("isRegister") + ":131"%> --%>
<!-- 						�b�j��~�A�ӧP�_�Ӭ��� �O�_ �w���W�L	,(�����b�j�餺�L�X) -->
							<c:if test="${isRegister == false && actVO1.memNo != memVO.memno && memVO != null}">									
								<input type="submit" value="���W" id="btn" >	
								<input type="hidden" name="actNo" value="${actVO1.actNo}">
<%-- 								<input type="hidden" name="actReg" value="${actReg.actNo}"> --%>
								<input type="hidden" name="action" value="getOne_For_ActRegister">
							
							</c:if>
							<c:if test="${isRegister == true && actVO1.actStatus == 'Y' }">	
						   		<input type="submit" value="�w���W" disabled>
							</c:if>
							
						</FORM>

						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/actRegister/actRegister.do" style="display:inline;">
							  <c:if test="${actVO1.actStatus == 'Y' && actVO1.memNo == memVO.memno}">
							   		<input type="submit" value="�d�߳��W���p" >
							    	<input type="hidden" name="actNo" value="${actVO1.actNo}">
							    	<input type="hidden" name="action"value="getAll_For_Display_By_ActNo">
							  </c:if>
						</FORM>
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/act/act.do" style="display:inline;">
								<c:if test="${actVO1.memNo == memVO.memno && (actVO1.actStatus == 'N' || actVO1.actStatus == 'A')}">
							    	<input type="submit" value="�R��" >
							    	<input type="hidden" name="actNo" value="${actVO1.actNo}">
							    	<input type="hidden" name="action"value="delete">
							    </c:if>
						</FORM>
					
<%-- 						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/actRegister/actRegister.do"> --%>
<%-- 								<c:forEach var="actReg" items="${actRegSvc.all}"> --%>
<%-- 									<c:if test="${actVO1.actStatus == 'Y' && actReg.memNo == '7005' && actReg.actNo == actVO1.actNo}"> --%>
<!-- 							    		<input type="submit" value="�������W"> -->
<%-- 							    		<input type="hidden" name="actNo" value="${actVO1.actNo}"> --%>
<!-- 							    		<input type="hidden" name="memNo" value="7005"> -->
<!-- 							    		<input type="hidden" name="action"value="delete_By_MemNo_And_ActNo"> -->
<%-- 							    	</c:if> --%>
<%-- 								</c:forEach> --%>
<!-- 						</FORM> -->
					</td>
				</tr>
<%-- 					<td>${actVO1.actContent}</td> --%>
					
					
<%-- 					<td><img src="<%=request.getContextPath()%>/act/DBGifReader3?actNo=${actVO1.actNo}"></td> --%>
<%-- 					<td>${actVO1.actEquipment}</td> --%>
<%-- 					<td>${actVO1.actDeposit}</td> --%>
<%-- 					<td>${actVO1.actHostFee}</td> --%>
						
					
					
<!-- 					<td> -->
<%-- 						<c:forEach var="emp" items="${empSvc.all}"> --%>
<%-- 							<c:if test="${actVO1.empNo == emp.empNo }"> --%>
<%-- 								${emp.empName}-${actVO1.empNo} --%>
<%-- 							</c:if> --%>
<%-- 						</c:forEach> --%>
<!-- 					</td> -->
		
				</c:if>
<!-- 			</tr> -->
		</c:forEach>
	</table>
	
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>

	<!-- <br>�����������|:<br><b> -->
	<%--    <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br> --%>
	<%--    <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b> --%>

</body>
</html>
