<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.doctor.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%  DoctorVO doctorVO = (DoctorVO) request.getAttribute("doctorVO");%>
<html>
<head>
<title>��v��� - listOneDr.jsp</title>
<link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" />
<title>iPET Cares</title>
<style type="text/css">
</style>
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
<a id="titleimg"><img src="<%= request.getContextPath()%>/images/main_tit01.png"></a>
<br>
<p style="text-align:right; margin-right:70px; color:orange;">�ثe��m�G<a href="<%=request.getContextPath()%>/iPETCares.jsp">����</a>>><a href="<%=request.getContextPath()%>/front/dr/listAllDr.jsp">�Ҧ���v��T</a></p>

<table border='1' bordercolor='#CCCCFF' width='900' align='center'>
	<tr>
		<th>��v�s��</th>
		<td><%=doctorVO.getDrNo()%></td>
	</tr>
	<tr>	
		<th>��v�m�W</th>
		<td><%=doctorVO.getDrName()%></td>
	</tr>
	<tr>	
		<th>�Ǹg��</th>
		<td><%=doctorVO.getDrExp()%></td>
	</tr>
	<tr>	
		<th>�ʧO</th>
		<td><c:if test="${doctorVO.drSex == 'M'}">�ӭ�</c:if>
			<c:if test="${doctorVO.drSex == 'F'}">���k</c:if></td>
	</tr>
	<tr>
		<th>�Ӥ�</th>
		<td><img src="<%= request.getContextPath()%>/dr/DBGifReader2?drNo=${doctorVO.drNo}"></td>
	</tr>
	<tr>	
		<th>�X�ͦ~���</th>
		<td><%=doctorVO.getDrBirth()%></td>
	</tr>
<!-- 	<tr>	 -->
<!-- 		<th>��}</th> -->
<%-- 		<td><%=doctorVO.getDrAdd()%></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<th>�q��</th> -->
<%-- 		<td><%=doctorVO.getDrTel()%></td> --%>
<!-- 	</tr> -->
	
</table>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
</body>
</html>
