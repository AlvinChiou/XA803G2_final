<%@ page contentType="text/html; charset=Big5" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>

<%
	Map<Integer, String> map = new LinkedHashMap<Integer, String>();
	map.put(0, "���{��");
	map.put(1, "�w�{��");
	map.put(2, "�w���v");
	
// 	MemVO memVO = new MemVO();
// 	memVO.setMemstate(1);
	
	application.setAttribute("map", map);
// 	pageContext.setAttribute("memVO", memVO);
	
	
%>
<html>
<head>
<title>EL07A</title>
</head>
<body>
	
	${map['1']}
    <br>
	${map["1"]}
    <br>
    ${map['2']}
    <br>
    ${map["2"]}
    <br>
    ${map[memVO.memstate]}


</body>
</html>
