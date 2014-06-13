<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.apt.model.*"%>
<%@ page import="com.apt.controller.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" />
<title>iPET Cares</title>
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
<!-- <link rel="stylesheet" type="text/css" -->
<%-- 	href="<%=request.getContextPath()%>/style.css" /> --%>
<!-- <script type="text/javascript" -->
<%-- 	src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script> --%>
<script>
	$(function() {
		$('#menu').children('li').click(function() {
			$('#menu').find('ul').removeClass('active');
			$(this).find('ul').toggleClass('active');
		});
	});
</script>
</head>

<body>

<div id="wrapper">
  		<div id="main_bg">
    	<div id="main_bg01">
        	<div id="apDiv13"></div>
			<c:if test="${memVO == null}"><%@ include file="/header.jsp" %></c:if>
        	<c:if test="${memVO != null}"><%@ include file="/loginHeader.jsp" %></c:if>
			<%@ include file="/ad.jsp" %>   
  		</div>
		</div>
<a id="titleimg"><img src="<%= request.getContextPath()%>/images/main_tit04.png"></a>
<p style="text-align:right; margin-right:70px; color:orange;">�ثe��m�G<a href="<%=request.getContextPath()%>/iPETCares.jsp">����</a>>><a href="<%=request.getContextPath()%>/front/apt/showApt.jsp">�����i��</a></p>

	<!-- jsp�D�n�}�l -->
<!-- 	<table border='1' cellpadding='5' cellspacing='0' width='400'> -->
		<!-- 	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'> -->
		<!-- 		<td> -->
		<%-- 		   <a href="<%=request.getContextPath()%>/backend/apt-Knifeman.jsp">�^��������</a> --%>
		<!-- 	    </td> -->
		<!-- 	</tr> -->
<!-- 	</table> -->
<%-- 	<%-- ���~��C --%>
<%-- 	<c:if test="${not empty errorMsgs}"> --%>
<!-- 		<font color='red'>�Эץ��H�U���~: -->
<!-- 			<ul> -->
<%-- 				<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 					<li>${message}</li> --%>
<%-- 				</c:forEach> --%>
<!-- 			</ul> -->
<!-- 		</font> -->
<%-- 	</c:if> --%>

	<%-- ${regDays}  --%>
	<table border='1' align='center' width='800'>

		<!--�H�U��show�XRegDays[]�}�C��code!-->
		<%
			RegDay[] regDays = (RegDay[]) application.getAttribute("regDays");
		%>
		<%-- <%RegDay[] regDays = (RegDay[])request.getAttribute("regDays");%> --%>
		<br />
		<%@ include file="pages/page1.file"%>
		<%
			if (regDays != null) {
		%>
		<%-- regDays:<%=regDays%> --%>
		<%
			for (int i = pageIndex; i < pageIndex + rowsPerPage; i++) {
		%>
		<%
			if (regDays[i] != null) {
		%>
		<tr>
			<th align='left' width='150'>��� :
				<h2><%=regDays[i].getDate()%></h2>
			</th>

			<%
				for (int k = 1; k <= 5; k++) {
			%>
			<th><%=k%></th>
			<%
				}
			%>
<!-- 			<th>�T�{�洫(swap)</th> -->
<!-- 			<th>�T�{�X��(deQueue)</th> -->

		</tr>
		<%
			for (int j = 0; j < regDays[i].getRegPeriods().length; j++) {
		%>
		<%
			if (regDays[i].getRegPeriods()[j] != null) {
		%>

		<tr>
			<td>
				<%
					String[] result = regDays[i].getRegPeriods()[j]
												.myToString();
				%> �ɬq :
				<%=regDays[i].getRegPeriods()[j]
										.getPeriod().substring(0, 2)
										+ "�I-"
										+ regDays[i].getRegPeriods()[j]
												.getPeriod().substring(2) + "�I"%>
			</td>
			<form method="post"	action="<%=request.getContextPath()%>/front/apt/apt.do">
			<input type="hidden" name="action" value="swap"> 
			<input type="hidden" name="whichDay" value=<%=i%>>
			<input type="hidden" name="whichPeriod" value=<%=j%>>
				<%
					for (int z = 0; z < AptServlet.QUEUE_SIZE; z++) {
				%>

				<td align="center">
					<%
						if (z < result.length && result[z] != null) {
					%> 
<%-- 					<input type="checkbox" name="selectedElements" value=<%=z + 1%>> --%>

<%-- 					<a href="<%=request.getContextPath()%>/front/apt/apt.do?action=getOne_For_DisplayInTheSamePage&aptNo=<%=result[z]%>&whichPage=<%=whichPage%>"> --%>
						<jsp:include page="/included.jsp" flush="true">
							<jsp:param name="aptNo" value="<%=result[z]%>" />
						</jsp:include> 
<!-- 					</a>  -->
					<%
						} else {
					%> <%
 	}
 %>

				</td>

				<%
					}
				%>
<!-- 				<td align="center"><input type="submit" value="�T�{�洫"> -->
				<input type="hidden" name="whichPage" value="<%=whichPage%>">
					<!--�e�X��e�O�ĴX����Controller--></td>
			</form>
<!-- 			<td align="center"> -->
<!-- 				<form method="post" -->
<%-- 					action="<%=request.getContextPath()%>/front/apt/apt.do"> --%>
<!-- 					<input type="hidden" name="action" value="deQueue"> -->
<%-- 					<input type="hidden" name="whichDay" value=<%=i%>>  --%>
<%-- 					<input type="hidden" name="whichPeriod" value=<%=j%>> --%>
<%-- 					<input type="submit" value="�X���ާ@"> <input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
<!-- 					�e�X��e�O�ĴX����Controller -->
<!-- 				</form> -->
<!-- 			</td> -->

			<%
				}
			%>
		</tr>
		<%
			}
		%>


		<%
			} else {
		%>
		<h1><%=new java.sql.Date(new java.util.Date()
								.getTime()
								+ i
								* AptServlet.ONE_DAY_IN_MILLISECONDS)%>
			���ѥ�E
		</h1>
		<%
			}
		%>
		<%
			}
		%>
		<%
			}
		%>
		<%@ include file="pages/page2.file"%>
	</table>

	<%
		if (request.getAttribute("aptVO") != null) {
	%>
	<jsp:include page="/front/apt/listOneApt.jsp" />
	<%
		}
	%>

	<!-- jsp�D�n���� -->

	<br>
	<br>
	<%
		
	%>

	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>

</body>
</html>