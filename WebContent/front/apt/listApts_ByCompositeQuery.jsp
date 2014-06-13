<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>

<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>

<jsp:useBean id="listApts_ByCompositeQuery" scope="request" type="java.util.List" />
<jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService" />

<html>
<head>
<title>�ƦX�d�� - listApts_ByCompositeQuery.jsp</title>
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

<link rel="stylesheet" type="text/css" href="js/calendar.css">
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"> --%>
<script>
	
		
		$(function(){
			$('#menu').children('li').click(function(){
				$('#menu').find('ul').removeClass('active');
				$(this).find('ul').toggleClass('active');	
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
    
 <!--jsp�D�n�}�l -->
<!-- <b><font color=blue> -->
<!-- ���U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ����<br> -->
<!-- �������@���ƦX�d�߮ɤ����G�m�ߡA</font> <font color=red>�w�W�[�����B�e�X�ק�B�R�����\��:</font></b> -->

<table border='1' bordercolor='#CCCCFF' width='950' align='center'>
	<tr>
		<th nowrap>�����s��&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>�������&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>�����ɬq&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>���X�P�s��&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>����ɶ�&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>�d���W�r&nbsp;&nbsp;&nbsp;</th>
	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file"%>
	<c:forEach var="aptVO" items="${listApts_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(aptVO.aptNo==param.aptNo) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
			<td nowrap>${aptVO.aptNo}</td>
			<td nowrap>${aptVO.aptDate}</td>
			<td nowrap>${aptVO.aptPeriod}</td>
			<td nowrap>${aptVO.aptNoSlip}</td>
			<td nowrap>${aptVO.aptRegTime}</td>		
			<td nowrap><c:forEach var="petVO" items="${petSvc.all}">
                    <c:if test="${petVO.petNo==aptVO.petNo}">
<%-- 	                    ${aptVO.petNo} --%>
	                    	<font color=orange>${petVO.petName}</font> 
	                    <td>
	                    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/pet/pet.do">
			    				<input type="hidden" name="petNo" value="${petVO.petNo}"> 
			    				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			   					<input type="hidden" name="action" value="getOne_For_Display">
			    				<input type="submit" value="�d�ߦ��d��">
			    			</FORM>
	                    </td>
                    </c:if>
                </c:forEach>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apt/apt.do"> --%>
<!-- 			     <input type="submit" value="�ק�">  -->
<%-- 			     <input type="hidden" name="aptNo" value="${aptVO.aptNo}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller--> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apt/apt.do"> --%>
<!-- 			    <input type="submit" value="�R��"> -->
<%-- 			    <input type="hidden" name="aptNo" value="${aptVO.aptNo}"> --%>
<%-- 			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--> --%>
<%-- 			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller--> --%>
<!-- 			    <input type="hidden" name="action"value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>

<!-- <br>�����������|:<br><b> -->
<%--    <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br> --%>
<%--    <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b> --%>

<!--jsp�D�n���� -->
    
    <div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>

</body>

</html>
