<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.doctor.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	DoctorService drSvc = new DoctorService();
	List<DoctorVO> list = drSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>�Ҧ���v��� - listAllDr.jsp</title>
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
			<th nowrap>��v�s��</th>
			<th nowrap>��v�m�W</th>
			<th nowrap>�Ǹg��</th>
<!-- 			<th>�ʧO</th> -->
			<th nowrap>�Ӥ�</th>
			<th nowrap>�X�ͦ~���</th>
<!-- 			<th>��}</th> -->
<!-- 			<th>�q��</th> -->
<!-- 			<th>�ק�</th> -->
<!-- 			<th>�R��</th> -->
		</tr>

		<%@ include file="pages/page1.file"%>
		<c:forEach var="doctorVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle' ${(doctorVO.drNo==param.drNo) ? 'bgcolor=#CCCCFF':''}>
				<td>${doctorVO.drNo}</td>
				<td><a href="<%=request.getContextPath()%>/front/dr/dr.do?action=getOne_For_Display&drNo=${doctorVO.drNo}">${doctorVO.drName}</a></td>
				<td>${doctorVO.drExp}</td>
<%-- 				<td>${doctorVO.drSex}</td> --%>
				<td><c:if test="${doctorVO.drPic != null}"><img src="<%= request.getContextPath()%>/dr/DBGifReader2?drNo=${doctorVO.drNo}"></c:if>
					<c:if test="${doctorVO.drPic == null}"><img src="<%= request.getContextPath()%>/front/dr/images/nopic.jpg"></c:if></td>
				<td>${doctorVO.drBirth}</td>
<%-- 				<td>${doctorVO.drAdd}</td> --%>
<%-- 				<td>${doctorVO.drTel}</td> --%>
<!-- 				<td> -->
<%-- 					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dr/dr.do"> --%>
<!-- 						<input type="submit" value="�ק�"> -->
<%-- 						<input type="hidden" name="drNo" value="${doctorVO.drNo}">  --%>
<%-- 						<input type="hidden" name="requestURL"value="<%=request.getServletPath()%>"> --%>
<%-- 						<input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
<!-- 						<input type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dr/dr.do"> --%>
<!-- 						 <input type="submit" value="�R��">  -->
<%-- 						 <input type="hidden" name="drNo" value="${doctorVO.drNo}">  --%>
<%-- 						 <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<%-- 						 <input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
<!-- 						 <input type="hidden" name="action" value="delete"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
			</tr>
		</c:forEach>
	</table>
	<%@ include file="pages/page2.file"%>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>

</body>
</html>
