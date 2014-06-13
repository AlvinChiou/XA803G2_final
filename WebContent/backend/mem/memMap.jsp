<%@ page contentType="text/html; charset=Big5" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />

<%
	Map<Integer, String> map = new LinkedHashMap<Integer, String>();
	map.put(0, "未認證");
	map.put(1, "已認證");
	map.put(2, "已停權");
	
// 	MemVO memVO = new MemVO();
// 	memVO.setMemstate(1);
	
	application.setAttribute("map", map);
// 	pageContext.setAttribute("memVO", memVO);
	
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4010);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>
<html>
<head>
<title>EL07A</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
</head>
<body>
		<%@ include file="/menu1.jsp" %> 
		<c:if test="<%=listPower.contains(powVO)%>"> 
	
	${map['1']}
    <br>
	${map["1"]}
    <br>
    ${map['2']}
    <br>
    ${map["2"]}
    <br>
    ${map[memVO.memstate]}
	</c:if>
    <%@ include file="/menu2.jsp" %> 

</body>
</html>
