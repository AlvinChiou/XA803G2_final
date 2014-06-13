<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%-- <% 	ActVO actVO = (ActVO) request.getAttribute("actVO"); %> --%>
<html>
<head>
<title>�Ҧ��D�쬡�ʸ�� - listAllActsByMemNo.jsp</title>
<link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" />
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

	<table border='1' bordercolor='#CCCCFF' width='950' align='center'>
		<tr>
			<th nowrap>���ʽs��</th>
			<th nowrap>���ʦW��</th>
<!-- 			<th>���ʤ��e</th> -->
			<th nowrap>�}�l�ɶ�</th>
			<th nowrap>�����ɶ�</th>
<!-- 			<th>���ʷӤ�</th> -->
			<th nowrap>���ɾ���</th>
			<th nowrap>����O��</th>
			<th nowrap>�D��ú��O��</th>
			<th nowrap>���ʳ��W�O��</th>
			<th nowrap>�ثe���A</th>
<!-- 			<th nowrap>�D���|���s��</th> -->
<!-- 			<th>�f��¾���s��</th> -->

		</tr>

		<c:forEach var="act" items="${listAllActsByMemNo}">
			<tr align='center' valign='middle' >
				
					<td>${act.actNo}</td>
					<td><a href="<%= request.getContextPath()%>/front/act/act.do?action=getOne_For_Display&actNo=${act.actNo}">${act.actName}</a></td>
<%-- 					<td>${act.actContent}</td> --%>
					<td>${act.actStartTime}</td>
					<td>${act.actEndTime}</td>
<%-- 					<td><img src="<%=request.getContextPath()%>/act/DBGifReader3?actNo=${act.actNo}"></td> --%>
					<td>${act.actEquipment}</td>
					<td>${act.actDeposit}</td>
					<td>${act.actHostFee}</td>
					<td>${act.actRegFee}</td>
					<td><c:if test="${act.actStatus == 'Y'}">�}��</td></c:if>
						<c:if test="${act.actStatus == 'N'}">�ݼf��</td></c:if>
						<c:if test="${act.actStatus == 'E'}">���ʵ���</td></c:if>
<%-- 					<td>${act.memNo}</td> --%>
<%-- 					<td>${act.empNo}</td> --%>
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

					<c:if test="${act.actStatus == 'N'}">
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/act/act.do">
								<input type="submit" value="�ק�" > 
								<input type="hidden" name="actNo" value="${act.actNo}">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
								<input type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
					</c:if>

					<!-- 			<td> -->
					<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/act/act.do"> --%>

					<%-- 			    <input type="submit" value="�R��" ${(actVO1.actStatus=="Y")? 'disabled=disabled':''} }> --%>
					<%-- 			    <input type="hidden" name="actNo" value="${actVO1.actNo}"> --%>
					<!-- 			    <input type="hidden" name="action"value="delete"></FORM> -->
					<!-- 			</td> -->
				
			</tr>
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
