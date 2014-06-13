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
	-moz-background-size: cover;
	background-size: cover;
	background-color: #000;
	background-image: url(<%=request.getContextPath()%>/images/bg1.jpg);
	background-repeat: no-repeat;
	background-position: center center;
	}
.index {
	/* [disabled]background-image: url(./bg1.jpg); */
	height: 1100px;
	width: 960px;
	margin-right: auto;
	margin-left: auto;
	position: relative;
	}
#login {
	position: absolute;
	height: 250px;
	width: 300px;
	left: 340px;
	top: 231px;
	}
#logo {
	position: absolute;	
	height: 103px;
	width: 189px;
	left: 385px;
	top: 20px;
	}
.idInput {
	border-radius: 20px;
	outline: none;
	width: 100px;
	height: 15px;
	}
</style>

</head>
<body>

	<div class="index">
	<div id="logo"><img src="<%= request.getContextPath()%>/images/0519_logo.png" width="200" height="200"></div>
  	<div id="login">

  	<table width="100%">
    <tbody>
		<tr>
			<td height="39" align="center"><font color="#000000">恭喜你，註冊成功!!</font></td>
		</tr>
		<tr>
			<td height="39" align="center"><font color="#000000">請至信箱收取信件，啟動帳號!!</font></td>
		</tr>
		<tr>
		    <form action="<%= request.getContextPath()%>/iPETCares.jsp">
			<td height="39" align="center"><font color="#000000"><input type="submit" value="返回首頁"></font></td>
			</form>
		</tr>
    </tbody>
    </table>
    
  	</div>
  
<br clear="all">
</div>

</body>
</html>