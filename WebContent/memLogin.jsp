<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<LINK rel="SHORTCUT ICON" href="<%=request.getContextPath()%>/images/icon_1r_48.png">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<title>memLogin.jsp</title>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
/*	-moz-background-size: cover;
	background-size: cover; */
	background-color: #fdedc9;
	background-image: url(<%=request.getContextPath()%>/images/back-bg.jpg);
	background-repeat: repeat-x;
	background-position: top;
	}	
.index {
/*	background-image: url(./bg1.jpg); */
	height: 1100px;
	width: 960px;
	margin-right: auto;
	margin-left: auto;
	position: relative;
	}
#login {
	position: relative;
	height: 250px;
	width: 208px;
	left: 385px;
	top: 200px;
	font-family: Arial, "微軟正黑體", "新細明體", serif;
	font-size: 21px;
	color: #535353;
	}
#logo {
	position: relative;	
	height: 103px;
	width: 189px;
	left: 385px;
	top: 20px;
	}
.idInput {
	border-radius: 10px;
	outline: none;
	width: 100px;
	height: 23px;
}
.btn {
	border: none;
	color: #fff;
	font-size: 15px;
	font-family: Verdana, "微軟正黑體";
	line-height: 40px;
	/*text-shadow: 0 0 3px #000;*/
	text-indent: 0;
	text-align: center;
	text-decoration: none;
	width: 60px;
	height: 40px;
	display: inline;
	margin: 1em 0;
	background-color: #81511c;
	border-radius: 10px;
}
</style>
<!-- <script> 
 	function goMemAdd(){
		document.location.href="<%= request.getContextPath()%>/memLogin.jsp";
 	}
</script> -->
</head>

<%
if ( request.getParameter("aptDate") != null && request.getParameter("aptPeriod") != null) {
	request.getSession().setAttribute( "aptDate", request.getParameter("aptDate"));
	request.getSession().setAttribute( "aptPeriod", request.getParameter("aptPeriod"));

}


System.out.println( "aptDate" + request.getParameter("aptDate"));
System.out.println( "aptPeriod" + request.getParameter("aptPeriod"));

%>

<body>

	<div class="index">
	<div id="logo"><img src="<%= request.getContextPath()%>/images/0528_logo.png" width="200" height="200"></div>
  	<div id="login">
		<form method="post" action="<%= request.getContextPath()%>/memLoginHandler.do">
  		<table width="100%">
    	<tbody>
			<tr>
				<td height="39" align="center"><font color="#000000">使用者登入</font></td>
			</tr>
			<tr>
				<td height="30"><font color="#000000">帳號：</font>
				<input type="text" class="idInput" size="15" name="memid" id="memid" value="" onkeypress="enterTo()" onfocus="textFocus();">
				</td>
			</tr>
		
			<tr>
				<td height="40"><font color="#000000">密碼：</font>
				<input type="password" class="idInput" size="15" name="mempassword" id="mempassword" value="" onkeypress="enterTo()" onfocus="textFocus();">
				</td>
			</tr>
    
        	<tr>
        		<td height="40" align="center"><span id="checkRes"></span><br>
		   		<input id="addMem" name="addMem" type="button" value="新會員註冊" onclick="location.href='<%= request.getContextPath()%>/front/mem/beforeAddMem.jsp'"/>
        		<input id="loginBtn" name="按鈕" type="submit" value="登入">
		    	</td>
			</tr>
    	</tbody>
    	</table>
    
		</form>
<div id="msg_user_name"></div>
  	</div>
  
<br clear="all">
</div>

</body>
</html>