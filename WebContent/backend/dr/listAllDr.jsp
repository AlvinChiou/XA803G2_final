<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.doctor.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />

<%
	DoctorService drSvc = new DoctorService();
	List<DoctorVO> list = drSvc.getAll();
	pageContext.setAttribute("list", list);
	

	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4004);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
%>

<html>
<head>
<title>所有醫師資料 - listAllDr.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		

</head>
<body bgcolor='white'>
<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>"> 
<!--     <h2>動物留言管理</h2> -->
<!--     <div id="search_bar"> -->
<!--       <select name="xxx"> -->
<!--         <option value="111">111</option> -->
<!--         <option value="222">222</option> -->
<!--         <option value="333">333</option> -->
<!--       </select> -->
<!--       <input type="text" name="zzz" id="zzz" /> -->
<!--       <a href="#" class="btn">搜尋</a> </div> -->
    <!-- end #search_bar -->
<!-- jsp開始 -->
	
	<table border='1' cellpadding='5' cellspacing='0' width='500'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>所有醫師資料</h3> 
				<a href="<%=request.getContextPath()%>/backend/doctor-mo.jsp">
				<img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
			</td>
		</tr>
	</table>

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

	<table border='1' bordercolor='#CCCCFF' width='500'>
<!-- 		<tr> -->
<!-- 			<th nowrap>醫師編號</th> -->
<!-- 			<th nowrap>醫師姓名</th> -->
<!-- 			<th nowrap>學經歷</th> -->
<!-- 			<th nowrap>性別</th> -->
<!-- 			<th nowrap>照片</th> -->
<!-- 			<th nowrap>出生年月日</th> -->
<!-- 			<th nowrap>住址</th> -->
<!-- 			<th nowrap>電話</th> -->
<!-- 			<th nowrap>修改</th> -->
<!-- 			<th nowrap>刪除</th> -->
<!-- 		</tr> -->

		<%@ include file="pages/page1.file"%>
		<c:forEach var="doctorVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr ${(doctorVO.drNo==param.drNo) ? 'bgcolor=#CCCCFF':''}>
				<th nowrap>醫師編號</th><td>${doctorVO.drNo}</td>
			</tr>
			<tr>
				<th nowrap>醫師姓名</th><td>${doctorVO.drName}</td>
			</tr>
			<tr>
				<th nowrap>學經歷</th><td>${doctorVO.drExp}</td>
			</tr>
			<tr>
				<th nowrap>性別</th>
				<td>
					<c:if test="${doctorVO.drSex == 'M'}">男</c:if>
					<c:if test="${doctorVO.drSex == 'F'}">女</c:if>
				</td>
			</tr>
			<tr>
				<th nowrap>照片</th>
				<td>
					<c:if test="${doctorVO.drPic!=null}"><img src="<%= request.getContextPath()%>/dr/DBGifReader2?drNo=${doctorVO.drNo}"></c:if>
					<c:if test="${doctorVO.drPic==null}"><img src="images/nopic.jpg"></c:if>
				</td>
			</tr>
			<tr>
				<th nowrap>出生年月日</th><td>${doctorVO.drBirth}</td>
			</tr>
			<tr>
				<th nowrap>住址</th><td>${doctorVO.drAdd}</td>
			</tr>
			<tr>
				<th nowrap>電話</th><td>${doctorVO.drTel}</td>
			</tr>
			<tr>
				<th nowrap>修改</th>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dr/dr.do">
						<input type="submit" value="修改">
						<input type="hidden" name="drNo" value="${doctorVO.drNo}"> 
						<input type="hidden" name="requestURL"value="<%=request.getServletPath()%>">
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<th nowrap>刪除</th> -->
<!-- 				<td> -->
<%-- 					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dr/dr.do"> --%>
<!-- 						 <input type="submit" value="刪除">  -->
<%-- 						 <input type="hidden" name="drNo" value="${doctorVO.drNo}">  --%>
<%-- 						 <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<%-- 						 <input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
<!-- 						 <input type="hidden" name="action" value="delete"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
<!-- 			</tr> -->
		</c:forEach>
	</table>
	<%@ include file="pages/page2.file"%>

<!-- 	<br>本網頁的路徑: -->
<!-- 	<br> -->
<%-- 	<b> <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br> --%>
<%-- 		<font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> --%>
<!-- 	</b> -->
<!-- jsp結束 -->
    
  </c:if>  
<%@ include file="/menu2.jsp" %> 



</body>
</html>
