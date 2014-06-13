<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	

</head>
<body bgcolor='white'>
	<%@ include file="/menu1.jsp" %>
	
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
  		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    		<td><h1>今日出貨路線</h1></td>
  		</tr>
	</table>

	<form method="post" action="<%=request.getContextPath()%>/backend/ORDER/order.do">
		<table border='0' cellpadding='5'>
		  <tr>
			<td>
				<input type = "hidden" name = "action" value = "getRoute" />
				<input type="submit" value="當天出貨路線Get!"/>
			</td>
		  </tr>
		</table>
	</form>
  
    
    
    
    <%@ include file="/menu2.jsp" %>

    
    
  
</body>
</html>