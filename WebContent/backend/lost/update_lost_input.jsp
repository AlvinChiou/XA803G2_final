<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lost.model.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
  LostVO lostVO = (LostVO) request.getAttribute("lostVO");//LostServlet.java (Controller), 存入req的lostVO物件 (包括幫忙取出的lostVO, 也包括輸入資料錯誤時的lostVO物件)

  PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4011);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>

<%-- <jsp:useBean id="LostVO" scope="session" class="com.lost.model.LostVO" /> --%>
<%-- <jsp:useBean id="LostVO" scope="request" class="com.lost.model.LostVO" /> --%>
<%-- <jsp:setProperty name="LostVO"  property="lostpic" value="${lostVO.lostpic}" /> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	

<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>失蹤協尋文章資料修改</title>
</head>
<body bgcolor='white'>
<%@ include file="/menu1.jsp" %> 
	<c:if test="<%=listPower.contains(powVO)%>"> 
	
<table border='1' cellpadding='5' cellspacing='0' width='550'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>失蹤協尋文章資料修改</h3>
		<a href="<%= request.getContextPath()%>/backend/lost/select_page.jsp"><img src="<%= request.getContextPath()%>/backend/lost/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<h3>資料修改:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<form method="post" action="lost.do" name="form1" enctype="multipart/form-data">
<table border="0">
	<tr>
		<td>文章編號:</td>
		<td><input type="hidden" name="lostno" size="15" />${lostVO.lostno}</td>
	</tr>
	<tr>
		<td>文章標題:</td>
		<td><input type="text" name="losttitle" size="57" value="<%= lostVO.getLosttitle() %>" ></td>
	</tr>
	<tr>
		<td>照片:</td>
		<td><img src="DBGifReader3?lostno=${lostVO.lostno}"></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="file" name="lostpic" size="57" /></td>
<%-- 	<td><input type="file" name="lostpic" size="57" value="<%= (lostVO==null)? "" : lostVO.getLostpic() %>"/></td> --%>
	</tr>
	<tr>
		<td>文章內容:</td>
		<td><textarea name="lostcontent" rows="30" cols="55" ><c:out value="${lostVO.lostcontent}" /></textarea></td>
	</tr>
	<tr>
		<td>登載時間:</td>
		<td><input type="hidden" name="losttime" size="15" value="<%= lostVO.getLosttime() %>" >${lostVO.losttime}</td>
	</tr>
	<tr>
		<td>找尋狀態:</td>
		<td><select size="1" name="loststate">
			<c:if test="${lostVO.loststate == 0}">
				<option value="0">失蹤
				<option value="2">已找到
			</c:if>
			<c:if test="${lostVO.loststate == 1}">
				<option value="1">拾獲
				<option value="3">已領回
			</c:if>
		</select></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="hidden" name="memno" size="10" value="<%= lostVO.getMemno() %>" />${lostVO.memno}</td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="lostno" value="<%=lostVO.getLostno()%>">
<%-- <input type="hidden" name="lostpic" value="<%=lostVO.getLostpic()%>"> --%>
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%> ">  <!--只用於:listAllLost.jsp-->
<input type="submit" value="送出修改">
</form>
	</c:if>
<%@ include file="/menu2.jsp" %> 

</body>
</html>