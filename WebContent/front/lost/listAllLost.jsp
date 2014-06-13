<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.lost.model.*"%>
<%@ page import="com.pow.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%
	LostService lostSvc = new LostService();
	List<LostVO> list = lostSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<%-- 
	PowService powSvc = new PowService();
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4011);
	List<PowVO> listPowVO = (List<PowVO>)session.getAttribute("list");
--%>
<%-- <jsp:useBean id="lostSvc" scope="page" class="com.lost.model.LostService" /> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�Ҧ����ܤ峹 - listAllLost.jsp</title>
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
<a id="titleimg"><img src="<%= request.getContextPath()%>/images/main_tit02.png"></a>
<br>
<p style="text-align:right; margin-right:50px; color:orange;">�ثe��m�G<a href="<%=request.getContextPath()%>/iPETCares.jsp">����</a>>><a href="<%=request.getContextPath()%>/front/lost/listAllLost.jsp">�Ҧ����ܨ�M�峹</a></p>
		
<%-- ���~��C  --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<div style="margin-left:60px; margin-right:20px">

<form method="post" action="<%= request.getContextPath()%>/front/lost/lost.do" style="display:inline">
	<b>��J�峹�s��(�p5001):</b>
	<input type="text" name="lostno">
	<input type="submit" value="�e�X">
	<input type="hidden" name="action" value="getOne_For_Display">
</form>


<form method="post" action="<%= request.getContextPath()%>/front/lost/lost.do" style="display:inline; float:right; margin-right:40px" >
	<b><font color=orange>��ܤ峹���A:</font></b>
	<select size="1" name="loststate">
        <option value="0">����
        <option value="1">�B��
        <option value="2">�w���
        <option value="3">�w��^
	</select>
	<input type="submit" value="�e�X">
	<input type="hidden" name="action" value="listLost_ByState">
</form>

</div>
		
<table border='1' bordercolor='#CCCCFF' width='900' align='center'>
	<tr>
<!-- 		<th nowrap>�峹�s��</th> -->
		<th nowrap>�峹���D</th>
<!-- 		<th nowrap>�Ӥ�</th> -->
<!-- 		<th nowrap>�峹���e</th> -->
		<th nowrap>��M���A</th>
		<th nowrap>�|���s��</th>
		<th nowrap>�n���ɶ�</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="lostVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(lostVO.lostno == param.lostno)? 'bgcolor=#CCCCFF' : ''}><!--�N�ק諸���@���[�J����Ӥw-->
<%-- 		<td>${lostVO.lostno}</td> --%>
		<td><a href="<%=request.getContextPath()%>/front/lost/lost.do?action=getOne_For_Display&lostno=${lostVO.lostno}">${lostVO.losttitle}</a></td>
<%-- 		<td><c:if test="${lostVO.lostpic!=null}"><img src="DBGifReader3?lostno=${lostVO.lostno}"></c:if> --%>
<%-- 			<c:if test="${lostVO.lostpic==null}"><img src="images/nopic.jpg"></c:if></td> --%>
<%-- 		<td>${lostVO.lostcontent}</td> --%>
		<td><c:if test="${lostVO.loststate=='0'}">����</c:if>
			<c:if test="${lostVO.loststate=='1'}">�B��</c:if>
			<c:if test="${lostVO.loststate=='2'}">�w���</c:if>
			<c:if test="${lostVO.loststate=='3'}">�w��^</c:if></td>
		<td>${lostVO.memno}</td>
		<td>${lostVO.losttime}</td>
<%-- 		<c:if test="<%=listPowVO.contains(powVO)%>"> --%>
<!-- 		<td> -->
<%-- 			<form method="post" action="<%=request.getContextPath()%>/lost/lost.do"> --%>
<!-- 			<input type="submit" value="�ק�"> -->
<%-- 			<input type="hidden" name="lostno" value="${lostVO.lostno}"> --%>
<%-- 			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--> --%>
<%-- 		    <input type="hidden" name="whichPage"	value="<%=whichPage%>">              <!--�e�X��e�O�ĴX����Controller--> --%>
<!-- 			<input type="hidden" name="action" value="getOne_For_Update"></form> -->
<!-- 		</td> -->
<!-- 		<td> -->
<%-- 			<form method="post" action="<%=request.getContextPath()%>/lost/lost.do"> --%>
<!-- 			<input type="submit" value="�R��"> -->
<%-- 			<input type="hidden" name="lostno" value="${lostVO.lostno}"> --%>
<%-- 			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--> --%>
<%-- 		    <input type="hidden" name="whichPage"	value="<%=whichPage%>">              <!--�e�X��e�O�ĴX����Controller--> --%>
<!-- 			<input type="hidden" name="action" value="delete"></form> -->
<!-- 		</td> -->
<%-- 		</c:if> --%>
	</tr>
	</c:forEach>
</table>
	<%@ include file="pages/page2.file" %>
	<div id="main_bg02"></div>
	<br>
	<%@ include file="/footer.jsp" %>
	</div>
</body>
</html>