<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head><title>JSTL01_query</title></head>
<body bgcolor="green" topmargin="1">
  <sql:setDataSource dataSource="jdbc/iPetCaresDB" var="xxx" scope="application"/>
  
  <sql:query var="rs" dataSource="${xxx}">
    select to_char(shift.SHIFTDATE,'yyyy-mm-dd') SHIFTDATE, shift.ShiftPeriod, doctor.drName
	from shift JOIN doctor ON shift.drNo = doctor.drNo 
	where to_char(shift.SHIFTDATE,'yyyy-mm-dd')=? and SHIFTDATE >= sysdate - 1
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
<%-- 		aaa${rs.columnNames[0]}bbb --%>
<%--  <c:set var="string2" value="${fn:substring(row[0], 5, 15)}" /> --%>
	
  <br/>
  
   <a href="<%=request.getContextPath()%>/front/apt/addApt.jsp?shiftDate=${row[0]}&shiftPeriod=${row[1]}" target="_blank">
   <nobr>
  	   <font size = "1" color = "red">${row[1]}</font>
   </a> 	   
       <a href="#"  onclick="window.open('<%=request.getContextPath()%>/front/shift/JSTL01_query4_Index_forEach_param2.jsp?shiftDate=${row[0]}', 'xxx', config='height=500,width=500');" >
       <font color = "yellow" size = "1"><c:out value="${row[2]}"/> </font> <nobr/>
       </a> <br>
 	  
 	 
<%--  	   <a href="<%=request.getContextPath()%>/backend/apt/addApt.jsp?shiftDate=${row[0]}&shiftPeriod=${row[1]}" target="_blank">xxx </a> --%>
<%--    	   <a href="<%=request.getContextPath()%>/backend/apt/apt.do?action=gotoApt&shiftDate=${row[0]}&shiftPeriod=${row[1]}" target="_blank"> --%>
<%--  		 【<c:out value="${row[1]}"/>】  --%>
<!--        </a>   -->
<%--     <a href = '<%=request.getContextPath()%>/backend/shift/shift.do?action=getOne_For_Update&shiftDate=${row[0]}&shiftPeriod=${row[1]}' target="_blank"> --%>
<!--        <font size = "1" color = "white">       -->
<!--             	修改 -->
<!--        </font>      -->
<!--     </a>  -->
<%--      <a href = '<%=request.getContextPath()%>/backend/shift/shift.do?action=delete&shiftDate=${row[0]}&shiftPeriod=${row[1]}'> --%>
<!--        	 <font size = "1" color = "white">      -->
<!--        	 	刪除  -->
<!--          </font>      -->
<!--    </a>  -->
<%--   <br/>【醫師:<c:out value="${row[2]}"/> 】 --%>
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