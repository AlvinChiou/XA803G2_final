<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>

<jsp:useBean id="powSvcXX" scope="page" class="com.pow.model.PowService" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
	<!----------- �o�O���j�u!!!!!------------ -->
	<div id="top">${employeeVO.empName}�z�n�I<a
			href="<%=request.getContextPath()%>/empLogoutHandler.do">�n�X</a>
	</div>
	<!-- end #top -->
	<div id="container">
		<div id="sidebar" style="overflow:auto">
			<a href="<%=request.getContextPath()%>/index.jsp"><img
				src="<%=request.getContextPath()%>/images/0519_logo.png" name="logo"
				width="110" height="100" id="logo" /></a>
			<ul id="menu">
				<%
					PowVO powVOXX = powSvcXX.getOnePowByPKs(
							(Integer) session.getAttribute("empNo"), 4002);
					List<PowVO> listPowerXX = (List<PowVO>) session
							.getAttribute("list");
				%>
				<c:if test="<%=listPowerXX.contains(powVOXX)%>">

					<li id="menu_1"><a href="#">&nbsp;</a>
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/backend/shift-Knifeman.jsp">�s�W����</a></li>
							<li><a
								href="<%=request.getContextPath()%>/backend/apt-Knifeman.jsp">�����{���޲z</a></li>
						</ul></li>

				</c:if>

				<%
					powVOXX = powSvcXX.getOnePowByPKs(
							(Integer) session.getAttribute("empNo"), 4004);
				%>

				<c:if test="<%=listPowerXX.contains(powVOXX)%>">
					<li id="menu_2"><a
						href="<%=request.getContextPath()%>/backend/shift-Knifeman.jsp">��v�Z��޲z</a></li>
				</c:if>
				<%
					powVOXX = powSvcXX.getOnePowByPKs(
							(Integer) session.getAttribute("empNo"), 4003);
				%>
				<c:if test="<%=listPowerXX.contains(powVOXX)%>">
					<li id="menu_3"><a
						href="<%=request.getContextPath()%>/backend/drRecord/select_page.jsp">�f���޲z</a></li>
				</c:if>
				<%
					powVOXX = powSvcXX.getOnePowByPKs(
							(Integer) session.getAttribute("empNo"), 4007);
				%>
				<c:if test="<%=listPowerXX.contains(powVOXX)%>">
					<li id="menu_4"><a
						href="<%=request.getContextPath()%>/backend/pet/select_page.jsp">�d���޲z</a></li>
				</c:if>
				<%
					powVOXX = powSvcXX.getOnePowByPKs(
							(Integer) session.getAttribute("empNo"), 4013);
				%>

				<c:if test="<%=listPowerXX.contains(powVOXX)%>">
					<li id="menu_5"><a
						href="<%=request.getContextPath()%>/backend/pow&func-Yo.jsp">�v���޲z</a></li>
				</c:if>
				<%
					powVOXX = powSvcXX.getOnePowByPKs(
							(Integer) session.getAttribute("empNo"), 4010);
				%>
				<c:if test="<%=listPowerXX.contains(powVOXX)%>">
					<li id="menu_6"><a
						href="<%=request.getContextPath()%>/backend/mem/select_page.jsp">�|���޲z</a></li>
				</c:if>
				<%
					powVOXX = powSvcXX.getOnePowByPKs(
							(Integer) session.getAttribute("empNo"), 4005);
				%>
				<c:if test="<%=listPowerXX.contains(powVOXX)%>">
					<li id="menu_7"><a
						href="<%=request.getContextPath()%>/backend/PRODUCT/select_page.jsp">�ӫ~�޲z</a></li>
				</c:if>
				<%
					powVOXX = powSvcXX.getOnePowByPKs(
							(Integer) session.getAttribute("empNo"), 4006);
				%>
				<c:if test="<%=listPowerXX.contains(powVOXX)%>">
					<li id="menu_8"><a
						href="<%=request.getContextPath()%>/backend/ORDER/select_page.jsp">�q��޲z</a></li>
				</c:if>

				<%
					powVOXX = powSvcXX.getOnePowByPKs(
							(Integer) session.getAttribute("empNo"), 4012);
				%>
				<c:if test="<%=listPowerXX.contains(powVOXX)%>">
					<li id="menu_9"><a
						href="<%=request.getContextPath()%>/gotoWaypoints.jsp">�X�f���u�޲z</a></li>
				</c:if>
				<%
					powVOXX = powSvcXX.getOnePowByPKs(
							(Integer) session.getAttribute("empNo"), 4011);
				%>
				<c:if test="<%=listPowerXX.contains(powVOXX)%>">
					<li id="menu_10"><a
						href="<%=request.getContextPath()%>/backend/lost/select_page.jsp">���ܤ峹�޲z</a></li>
				</c:if>

				<%
					powVOXX = powSvcXX.getOnePowByPKs(
							(Integer) session.getAttribute("empNo"), 4008);
				%>
				<c:if test="<%=listPowerXX.contains(powVOXX)%>">
					<li id="menu_11"><a
						href="<%=request.getContextPath()%>/backend/news/select_page.jsp">�s�D�޲z</a></li>
				</c:if>
				<%
					powVOXX = powSvcXX.getOnePowByPKs(
							(Integer) session.getAttribute("empNo"), 4009);
				%>
				<c:if test="<%=listPowerXX.contains(powVOXX)%>">
					<li id="menu_12"><a
						href="<%=request.getContextPath()%>/backend/emp/select_page.jsp">���u�޲z</a></li>
				</c:if>
				<%
					powVOXX = powSvcXX.getOnePowByPKs(
							(Integer) session.getAttribute("empNo"), 4001);
				%>
				<c:if test="<%=listPowerXX.contains(powVOXX)%>">

					<li id="menu_13"><a
						href="<%=request.getContextPath()%>/backend/act/select_page.jsp">���ʺ޲z</a></li>
				</c:if>
				<%
					powVOXX = powSvcXX.getOnePowByPKs(
							(Integer) session.getAttribute("empNo"), 4014);
				%>
				<c:if test="<%=listPowerXX.contains(powVOXX)%>">

					<li id="menu_14"><a
						href="<%=request.getContextPath()%>/backend/gb/select_page.jsp">�d���O�޲z</a>

					</li>

				</c:if>

				<%
					powVOXX = powSvcXX.getOnePowByPKs(
							(Integer) session.getAttribute("empNo"), 4004);
				%>
				<c:if test="<%=listPowerXX.contains(powVOXX)%>">

					<li id="menu_15"><a
						href="<%=request.getContextPath()%>/backend/doctor-mo.jsp">��v�򥻸�ƺ޲z</a>

					</li>

				</c:if>
			</ul>
		</div>
		<!-- end #sidebar -->
		<div id="main">
</body>
</html>