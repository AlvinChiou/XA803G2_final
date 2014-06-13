<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%
// ActVO actVO = (ActVO) request.getAttribute("actVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
	ActService actSvc = new ActService();
	List<ActVO> list = actSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>�e��-���ʸԲӸ�� - listOneAct.jsp</title>
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

<table border='1' bordercolor='#CCCCFF' width='900' align='center'>
	<tr>
		<th nowrap>���ʽs��:</th>
		<td>${actVO.actNo}</td>
	</tr>
	<tr>	
		<th nowrap>���ʦW��:</th>
		<td>${actVO.actName}</td>
	</tr>
	<tr>	
		<th nowrap>���ʤ��e:</th>
		<td>${actVO.actContent}</td>
	</tr>
	<tr>	
		<th nowrap>�}�l�ɶ�:</th>
		<td>${actVO.actStartTime}</td>
	</tr>	
	<tr>	
		<th nowrap >�����ɶ�:</th>
		<td>${actVO.actEndTime}</td>
	</tr>	
	<tr>	
		<th nowrap>���ʷӤ�:</th>
		<td><img src="<%=request.getContextPath()%>/act/DBGifReader3?actNo=${actVO.actNo}"></td>
	</tr>	
	<tr>	
		<th nowrap>���ɾ���:</th>
		<td>${actVO.actEquipment}</td>
	</tr>	
	<tr>	
		<th nowrap>����O��:</th>
		<td>${actVO.actDeposit}</td>
	</tr>
	<tr>	
		<th nowrap>�D���ú��O��:</th>
		<td>${actVO.actHostFee}</td>
	</tr>
	<tr>	
		<th nowrap>���ʳ��W�O��:</th>
		<td>${actVO.actRegFee}</td>
	</tr>
	<tr>	
		<th nowrap>�ثe���ʪ��A:</th>
		<td>		
			<c:if test="${actVO.actStatus == 'Y'}">���W��</c:if>
			<c:if test="${actVO.actStatus == 'N'}">�f�֤�</c:if>
			<c:if test="${actVO.actStatus == 'A'}">�p�H����<br>���f��</c:if>
			<c:if test="${actVO.actStatus == 'B'}">�p�H����</c:if>
			<c:if test="${actVO.actStatus == 'E'}">���ʵ���</c:if>
			</td>
	
	</tr>
	
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
	<tr>	
		<th>�D���|���s��:</th>
		<td>
			<c:forEach var="mem" items="${memSvc.all}">
				<c:if test="${actVO.memNo == mem.memno}">
					${pageScope.mem.memname} - ${actVO.memNo}
				</c:if>
			</c:forEach>
			</td>
	
	</tr>
	
<%-- <jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmployeeService"/>	 --%>
<!-- 	<tr>	 -->
<!-- 		<th>�f��¾��:</th> -->
<!-- 		<td> -->
<%-- 			<c:forEach var="emp" items="${empSvc.all}"> --%>
<%-- 				<c:if test="${actVO.empNo == emp.empNo }"> --%>
<%-- 					${emp.empName} - ${actVO.empNo} --%>
<%-- 				</c:if> --%>
<%-- 			</c:forEach> --%>
<!-- 					</td> -->
<!-- 	</tr> -->
</table>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
	
<jsp:useBean id="actRegSvc1" scope="page" class="com.actregister.model.ActRegisterService" />
	
	

</body>
</html>
