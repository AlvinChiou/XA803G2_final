<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
    <%@ page import="com.apt.model.*"%>  
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		
</head>
<body>


<%@ include file="/menu1.jsp" %> 
	
<h1>您的掛號日期為: ${aptVO.aptDate} <br/><br/> </h1>
<h1>您的號碼牌編號為: ${aptVO.aptNoSlip} <br/><br/> </h1>
<h1>您的應到時間為: ${fn:substring(aptVO.aptRegTime, 0, 16)}</h1>
</body>
<%
	int whichPage = 1;
	final long ONE_DAY_IN_MILLISECONDS = 24 * 60 * 60 * 1000;
	if ( request.getAttribute("aptVO") != null ) {
		java.sql.Date thatDay =   ( ( AptVO )request.getAttribute("aptVO") ).getAptDate();
		java.sql.Date today = new java.sql.Date ( new java.util.Date().getTime() );
		today = java.sql.Date.valueOf( today.toString() );
		whichPage = (int)( ( thatDay.getTime() - today.getTime() ) / ONE_DAY_IN_MILLISECONDS + 1 );
// 		System.out.println( ( thatDay.getTime() - today.getTime() ) / ONE_DAY_IN_MILLISECONDS + 2 );
	}
%>

<form action = "showApt.jsp">
	<input type  = "hidden"  name = "whichPage" value = "<%=whichPage%>" />
	<input type = "submit" value = "跳轉到管理所有掛號頁面">
</form>

<%@ include file="/menu2.jsp" %> 
	
</html>