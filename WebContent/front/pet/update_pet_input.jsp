<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pet.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%
	PetVO petVO = (PetVO) request.getAttribute("petVO"); //EmpServlet.java (Concroller), �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<html>
<head>
<title>�e��-�d����ƭק� - update_pet_input.jsp</title>
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

<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<div id="wrapper">
		<div id="main_bg">
    		<div id="main_bg01">
        	<div id="apDiv13"></div>
			<c:if test="${memVO == null}"><%@ include file="/header.jsp" %></c:if>
        	<c:if test="${memVO != null}"><%@ include file="/loginHeader.jsp" %></c:if>
			<%@ include file="/ad.jsp" %>   
    	<div>&nbsp;</div>
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

<FORM METHOD="post" ACTION="pet.do" name="form1" enctype="multipart/form-data">
<table border="0" align='center'>
	<tr>
		<th>�d����ƭק�:</th>
	</tr>
	<tr>
		<td>�d���s��:<font color=red><b>*</b></font></td>
		<td><input type="hidden" name="petNo" value="<%=petVO.getPetNo()%>"><%=petVO.getPetNo()%></td>
	</tr>
	<tr>
		<td>�d���W�r:</td>
		<td><input type="TEXT" name="petName" size="45" value="<%=petVO.getPetName()%>" /></td>
	</tr>
	<tr>
		<td>�d���ʧO:</td>
		<td><input type="hidden" name="petSex" size="45" value="<%=petVO.getPetSex()%>" />
			<c:if test="${petVO.petSex == 'boy' }">�k��</c:if>
			<c:if test="${petVO.petSex == 'girl' }">�k��></c:if>
		</td>
	</tr>
	<tr>
		<td>�d�����O:</td>
		<td><input type="hidden" name="petType" size="45" value="<%=petVO.getPetType()%>" />
			<c:if test="${petVO.petType == 'dog' }">��</c:if>
			<c:if test="${petVO.petType == 'cat' }">��</c:if>
		</td>	
	</tr>
	<tr>
		<td>�d���Ӥ�:</td>
		<td><img src="DBGifReader3?petNo=${petVO.petNo}"></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="file" name="petPic" size="45"	value="<%=petVO.getPetPic()%>" /></td>
	</tr>
	<tr>
		<td>�d���~��:</td>
		<td><input type="TEXT" name="petAge" size="45" value="<%=petVO.getPetAge()%>" /></td>
	</tr>
	<tr>
		<td>�|���s��:</td>
		<td><input type="TEXT" name="memNo" size="45" value="<%=petVO.getMemNo()%>" /></td>
	</tr>
	<tr>
		<td></td>
		<td align='right'><input type="submit" value="�e�X�ק�"></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="petNo" value="<%=petVO.getPetNo()%>">
<input type="hidden" name="requestURL" value="<%= request.getParameter("requestURL") %>">
</FORM>

	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
	
<!-- <br>�e�X�ק諸�ӷ��������|:<br><b> -->
<%--    <font color=blue>request.getParameter("requestURL"):</font> <%= request.getParameter("requestURL")%><br> --%>
</body>
</html>
