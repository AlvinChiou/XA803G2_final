<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<sql:setDataSource dataSource="jdbc/iPetCaresDB" var="xxx" scope="application"/>
  
  <sql:query var="rs" dataSource="${xxx}">
   	SELECT petNo
	FROM appointment
	WHERE aptNo = ?
    <sql:param value="${param.aptNo}" />
  </sql:query>
  
   <c:forEach var="row" items="${rs.rowsByIndex}" varStatus="status">
	  <c:forEach var="columnValue" items="${row}">
	    <c:set  var="petNo" value="${columnValue}" scope = "page" />
	  </c:forEach>
 </c:forEach>
 

	<img src = "<%=request.getContextPath()%>/pet/DBGifReader?petNo=${petNo}"/>
</body>
</html>