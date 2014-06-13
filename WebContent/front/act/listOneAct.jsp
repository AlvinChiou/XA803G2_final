<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%
// ActVO actVO = (ActVO) request.getAttribute("actVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
	ActService actSvc = new ActService();
	List<ActVO> list = actSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>前端-活動詳細資料 - listOneAct.jsp</title>
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
<p style="text-align:right; margin-right:50px; color:orange;">目前位置：<a href="<%=request.getContextPath()%>/iPETCares.jsp">首頁</a>>><a href="<%=request.getContextPath()%>/front/act/listAllAct.jsp">所有活動資訊</a></p>

<table border='1' bordercolor='#CCCCFF' width='900' align='center'>
	<tr>
		<th nowrap>活動編號:</th>
		<td>${actVO.actNo}</td>
	</tr>
	<tr>	
		<th nowrap>活動名稱:</th>
		<td>${actVO.actName}</td>
	</tr>
	<tr>	
		<th nowrap>活動內容:</th>
		<td>${actVO.actContent}</td>
	</tr>
	<tr>	
		<th nowrap>開始時間:</th>
		<td>${actVO.actStartTime}</td>
	</tr>	
	<tr>	
		<th nowrap >結束時間:</th>
		<td>${actVO.actEndTime}</td>
	</tr>	
	<tr>	
		<th nowrap>活動照片:</th>
		<td><img src="<%=request.getContextPath()%>/act/DBGifReader3?actNo=${actVO.actNo}"></td>
	</tr>	
	<tr>	
		<th nowrap>租借器材:</th>
		<td>${actVO.actEquipment}</td>
	</tr>	
	<tr>	
		<th nowrap>押金費用:</th>
		<td>${actVO.actDeposit}</td>
	</tr>
	<tr>	
		<th nowrap>主辦方繳交費用:</th>
		<td>${actVO.actHostFee}</td>
	</tr>
	<tr>	
		<th nowrap>活動報名費用:</th>
		<td>${actVO.actRegFee}</td>
	</tr>
	<tr>	
		<th nowrap>目前活動狀態:</th>
		<td>		
			<c:if test="${actVO.actStatus == 'Y'}">報名中</c:if>
			<c:if test="${actVO.actStatus == 'N'}">審核中</c:if>
			<c:if test="${actVO.actStatus == 'A'}">私人活動<br>未審核</c:if>
			<c:if test="${actVO.actStatus == 'B'}">私人活動</c:if>
			<c:if test="${actVO.actStatus == 'E'}">活動結束</c:if>
			</td>
	
	</tr>
	
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
	<tr>	
		<th>主辦方會員編號:</th>
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
<!-- 		<th>審核職員:</th> -->
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
