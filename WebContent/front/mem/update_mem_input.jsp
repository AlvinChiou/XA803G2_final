<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
	MemVO memVO = (MemVO) request.getAttribute("memVO");//MemServlet.java (Controller), �s�Jreq��memVO���� (�]�A�������X��memVO, �]�]�A��J��ƿ��~�ɪ�memVO����)
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�|����ƭק� - update_mem_input.jsp</title>
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

<form method="post" action="mem.do" name="form1">
<table border="1" align='center'>
	<tr>
		<td>�|���s��:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMemno()%></td>
	</tr>
	<tr>
		<td>�|���b��:</td>
		<td><input type="hidden" name="memid" size="25" value="<%=memVO.getMemid()%>" />${memVO.memid}</td>
	</tr>
	<tr>
		<td>�|���K�X:</td>
		<td><input type="password" name="mempassword" size="26" value="<%=memVO.getMempassword()%>" /></td>
	</tr>
	<tr>
		<td>�T�{�K�X:</td>
		<td><input type="password" name="checkpassword" size="21" value="" /></td>
	</tr>
	<tr>
		<td>�|���m�W:</td>
		<td><input type="text" name="memname" size="25" value="<%=memVO.getMemname()%>" /></td>
	</tr>
	<tr>
		<td>�����Ҧr��:</td>
		<td><input type="hidden" name="memidno" size="25" value="<%=memVO.getMemidno()%>" />${memVO.memidno}</td>
	</tr>
	<tr>
		<td>e-mail:</td>
		<td><input type="text" name="mememail" size="25" value="<%=memVO.getMememail()%>" /></td>
	</tr>
	<tr>
		<td>�X�ͦ~���:</td>
		<td><input type="hidden" name="membirth" size="25" value="<%=memVO.getMembirth()%>" />${memVO.membirth}</td>
	</tr>	
	<tr>
		<td>�|���a�}:</td>
		<td><input type="text" name="memadd" size="45" value="<%=memVO.getMemadd()%>" /></td>
	</tr>	
	<tr>
		<td>�ʧO:</td>
		<td>
			<input type="hidden" name="memsex" size="1" value="<%=(memVO==null)? "1" : memVO.getMemsex() %>" />
			<c:if test="${memVO.memsex=='0'}">�k</c:if>
			<c:if test="${memVO.memsex=='1'}">�k</c:if>
		</td>
	</tr>	
	<tr>
		<td>�q��:</td>
		<td><input type="text" name="memtel" size="15" value="<%=memVO.getMemtel()%>" /></td>
	</tr>
	
	<!-- 
	<tr>
		<td>���A:</td>
		<td><input type="radio" name="memstate" size="5" value="<%= (memVO!=null)? "1" : memVO.getMemstate()%>" checked/>�w�{��
			<input type="radio" name="memstate" size="5" value="<%= (memVO!=null)? "2" : memVO.getMemstate()%>" />�w���v</td>
	</tr>	
	 -->
</table>
	 <div align='right' style="margin-right:310px;">
	 	<input type="submit" value="�e�X�ק�"></td>
	 </div>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="memno" value="<%=memVO.getMemno()%>">
<input type="hidden" name="memstate" value="<%=memVO.getMemstate()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--�u�Ω�:istAllEmp.jsp-->
</form>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
</body>
</html>