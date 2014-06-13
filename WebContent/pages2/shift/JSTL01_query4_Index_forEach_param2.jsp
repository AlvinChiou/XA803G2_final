<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
<head><title>JSTL01_query</title></head>
<body bgcolor=navy topmargin="1">
  <sql:setDataSource dataSource="jdbc/iPetCaresDB" var="xxx" scope="application"/>
  
  <sql:query var="rs" dataSource="${xxx}">
    select to_char(shift.SHIFTDATE,'yyyy-mm-dd') SHIFTDATE, shift.ShiftPeriod, doctor.drName
	from shift JOIN doctor ON shift.drNo = doctor.drNo 
	where to_char(shift.SHIFTDATE,'yyyy-mm-dd')=?
    <sql:param value="${param.shiftDate}" />
  </sql:query>


<%-- 
 <c:forEach var="row" items="${rs.rowsByIndex}" varStatus="status">
	  <c:forEach var="columnValue" items="${row}">
	    <c:out value="${columnValue}"/>
	  </c:forEach>
 </c:forEach>
--%> 

<font color=cyan><b>
<c:forEach var="row" items="${rs.rowsByIndex}" varStatus="status">
  <br/><c:out value="${row[0]}"/> <br>
<%--   <a href="<%=request.getContextPath()%>/apt/addApt.jsp?shiftDate=${row[0]}&shiftPeriod=${row[1]}" target="_blank"> --%>
   <a href="<%=request.getContextPath()%>/apt/apt.do?action=gotoApt&shiftDate=${row[0]}&shiftPeriod=${row[1]}" target="_blank">
 		 【<c:out value="${row[1]}"/>】</a> <a href = '<%=request.getContextPath()%>/shift/shift.do?action=getOne_For_Update&shiftDate=${row[0]}&shiftPeriod=${row[1]}'>修改</a> 
 		                                    <a href = '<%=request.getContextPath()%>/shift/shift.do?action=delete&shiftDate=${row[0]}&shiftPeriod=${row[1]}'>刪除</a>
  <br/>【醫師:<c:out value="${row[2]}"/> 】
</c:forEach>
</font></b>



</body>
</html>

<!--
select to_char(HIREDATE,'yyyy-mm-dd') hiredate, count(HIREDATE) as count  from emp2 GROUP BY HIREDATE
select to_char(HIREDATE,'yyyy-mm-dd') hiredate, count(HIREDATE) as count  from emp2 where to_char(HIREDATE,'yyyy-mm-dd')='2014-01-01' GROUP BY HIREDATE
select count(HIREDATE) as count  from emp2 where to_char(HIREDATE,'yyyy-mm-dd')='2014-01-01' GROUP BY HIREDATE
-->

<!--
http://localhost:8081/jsp_calender3_Note2/JSTL01_query4_Index_forEach_param.jsp?hiredate=2014-01-01
-->