<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>
<%	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");%>

<html>
<head>
<title>�̷s�����s�W - addNews.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>

<script>
function miracle() {
	document.getElementsByName("newstitle")[0].value = "���p���߸��x�y���h�˴ڦ�";
	document.getElementsByName("newscontent")[0].value = "�ؤo�A�X���J�ж����i�߱ڡA�a��b����A���߫}���Ŷ����۸����A���i���A�𮧺�ı�A�߱��r���A�ѤѹB�ʱ`�O���d�C";
	
}
</script>


</head>

<body bgcolor='white'>
	<%@ include file="/menu1.jsp" %> 
	<input type = "button" id = "btn" value = "���_�p���s" onclick = "miracle()">
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>�̷s�����s�W</h3>
			</td>
			<td><a href="<%=request.getContextPath()%>/backend/news/select_page.jsp"><img src="<%= request.getContextPath()%>/backend/news/images/tomcat.gif" width="100" height="100" border="1">�^����</a></td>
		</tr>
	</table>

	<h3>��ƭ��u:</h3>
	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
<!-- 		<font color='red'>�Эץ��H�U���~: -->
<!-- 			<ul> -->
<%-- 				<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 					<li>${message}</li> --%>
<%-- 				</c:forEach> --%>
<!-- 			</ul> -->
<!-- 		</font> -->
	</c:if>

	<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/news/news.do" name="form1" enctype="multipart/form-data">
		<table border="0">

			<tr>
				<td>�����D�D:</td>
				<td>
					<input type="TEXT" name="newstitle" size="45" value="<%=(newsVO == null) ? "" : newsVO.getNewstitle()%>" />${errorMsgs.newstitle}
				</td>
<%-- 				<td>${errorMsgs.newstitle}</td> --%>
			</tr>
			<tr>
				<td>�������O:</td>
				<td>
					<select size="1" name="newstype">
						<option value="1" selected=''>������
						<option value="2">���i��
						<option value="3">�ӫ~��
					</select>
				</td>
<%-- 				<td>${errorMsgs.newstype}</td> --%>
			</tr>
			<tr>
				<td>�������e:</td>
				<td><textarea name="newscontent" rows="30" cols="55"><%=(newsVO == null)? "" : newsVO.getNewscontent()%>${errorMsgs.newcontent}</textarea>
<%-- 				<td>${errorMsgs.newcontent}</td> --%>
<%-- 				<input type="TEXT" name="newscontent" size="45" value="<%=(newsVO == null) ? "�ؤo�A�X���J�ж����i�߱ڡA�a��b����A���߫}���Ŷ����۸����A���i���A�𮧺�ı�A�߱��r���A�ѤѹB�ʱ`�O���d�C" : newsVO.getNewscontent()%>" /></td> --%>
			</tr>
			<tr>
				<td>�Ӥ�:</td>
				<td><input type="file" name="newspic" size="45" value="<%=(newsVO == null)? "" : newsVO.getNewspic()%>" />${errorMsgs.newspic}</td>
<%-- 				<td>${errorMsgs.newspic}</td> --%>
			</tr>
			<tr>
				<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
				<td>�o���ɶ�:</td>
				<td>
		    		<input type="hidden" name="newspotime" value="<%= date_SQL%>" /><%= date_SQL%>
				</td>
			</tr>
			<tr>
				<td>���u�s��:</td>
<%-- 				<td><input type="TEXT" name="empno" size="45" value="${employeeVO.empNo}" />${employeeVO.empNo}</td> --%>
				<td><input type="hidden" name="empno" size="45" value="1002" />1002</td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> 
		     <input type="submit" value="�e�X�s�W">
	</FORM>
	
		<%@ include file="/menu2.jsp" %> 
</body>

</html>
