<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actregister.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	ActRegisterService actRegSvc = new ActRegisterService();
    List<ActRegisterVO> list = actRegSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>�e��-�Ҧ����ʳ��W��� - listAllActRegister.jsp</title>
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
<a id="titleimg"><img src="<%= request.getContextPath()%>/images/main_tit03.png"></a>
<br>
<p style="text-align:right; margin-right:50px; color:orange;">�ثe��m�G<a href="<%=request.getContextPath()%>/iPETCares.jsp">����</a>>><a href="<%=request.getContextPath()%>/front/act/listAllAct.jsp">�Ҧ����ʸ�T</a>
 >><a href="<%=request.getContextPath()%>/front/actRegister/listAllActRegister.jsp">���W���p</a></p>

<table border='1' bordercolor='#CCCCFF' width='950' align='center'>
	<tr>
		<th>�ѥ[�|���s��</th>
		<th>�ѥ[���ʦW��(���ʽs��)</th>
		<th>�I�O���A</th>
		<th>���W���A</th>
		
	</tr>
	 
	<c:forEach var="actRegVO" items="${list}" >
		<c:if test="${actRegVO.memNo == memVO.memno}">
		<tr align='center' valign='middle'>
			<td>${actRegVO.memNo}</td>
			<td>${actRegVO.actRegName}(${actRegVO.actNo})</td>
			<c:if test="${actRegVO.actRegPayState == 'Y'}">
				<td>�wú�O</td>
			</c:if>
			<c:if test="${actRegVO.actRegPayState == 'N'}">
				<td>��ú�O</td>
			</c:if>  
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actRegister/act.do"> --%>
<!-- 			     <input type="submit" value="�ק�"> -->
<%-- 			     <input type="hidden" name="actRegNo" value="${actRegVO.actRegNo}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			<td>
			  <c:if test="${actRegVO.memNo == memVO.memno}">
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/actRegister/actRegister.do">
			    <input type="submit" value="�������W">
			    <input type="hidden" name="actRegNo" value="${actRegVO.actRegNo}">
			    <input type="hidden" name="action" value="delete">
			  </FORM>
			  
			  </c:if>
			  
			  <c:if test="${actRegVO.actRegPayState =='Y'}">
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actRegister/act.do">
			    <input type="submit" value="���W����"  disabled="disabled">
			  </FORM>
			    
			  </c:if>
			</td>
		</tr>
		</c:if>
	</c:forEach>
</table>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>

</body>
</html>
