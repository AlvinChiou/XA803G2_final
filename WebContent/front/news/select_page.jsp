<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>IBM News: Home</title>
</head>
<body bgcolor='#EFF6FF'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><img src="<%= request.getContextPath()%>/front/news/images/photo.jpg" width='150' height='150' align="left"/>
    	<h3>iPET Mem: Home</h3>
    	<a href="<%= request.getContextPath() %>/index.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
    </td>
  </tr>
</table>
	<p>This is the Home page for IBM News: Home</p>

	<h3>��Ƭd��:</h3>
<ul>
		<li><a href='<%=request.getContextPath()%>/front/news/listAllNews.jsp'>List</a> all News.</li>
		<br>
		<br>

<!-- 		<li> -->
<!-- 			<FORM METHOD="post" -->
<%-- 				ACTION="<%=request.getContextPath()%>/news/news.do"> --%>
<!-- 				<b>��J�����s�� (�p6001):</b> <input type="text" name="newsno">  -->
<!-- 				<input type="submit" value="�e�X">  -->
<!-- 				<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 			</FORM> -->
<!-- 		</li> -->

<%-- 		<jsp:useBean id="newsSvc" scope="page" class="com.news.model.NewsService" /> --%>
	
<!-- 		<li> -->
<!-- 			<FORM METHOD="post" -->
<%-- 				ACTION="<%=request.getContextPath()%>/news/news.do"> --%>
<!-- 				<b>��ܮ����s��:</b> <select size="1" name="newsno"> -->
<%-- 					<c:forEach var="newsVO" items="${newsSvc.all}"> --%>
<%-- 						<option value="${newsVO.newsno}">${newsVO.newsno} --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> <input type="submit" value="�e�X"> <input type="hidden" -->
<!-- 					name="action" value="getOne_For_Display"> -->
<!-- 			</FORM> -->
<!-- 		</li> -->

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/news/news.do">
				<b>��ܮ������O:</b> <select size="1" name="newstype">
						<option value="1">������
						<option value="2">�ӫ~��
						<option value="3">���i��
				</select> <input type="submit" value="�e�X"> <input type="hidden" name="action" value="listNews_Bytype">
			</FORM>
		</li>
</ul>
<!-- 		<h3>�s�W�峹</h3> -->

<!-- 		<ul> -->
<%-- 			<li><a href='<%=request.getContextPath()%>/front/news/addNews.jsp'>Add</a> --%>
<!-- 				a new News.</li> -->
<!-- 		</ul> -->

</body>

</html>
