<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.doctor.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>
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
<title>�Ҧ���v��� - listAllDr.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>		

</head>
<body bgcolor='white'>
<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>"> 
<!--     <h2>�ʪ��d���޲z</h2> -->
<!--     <div id="search_bar"> -->
<!--       <select name="xxx"> -->
<!--         <option value="111">111</option> -->
<!--         <option value="222">222</option> -->
<!--         <option value="333">333</option> -->
<!--       </select> -->
<!--       <input type="text" name="zzz" id="zzz" /> -->
<!--       <a href="#" class="btn">�j�M</a> </div> -->
    <!-- end #search_bar -->
<!-- jsp�}�l -->
	
	<table border='1' cellpadding='5' cellspacing='0' width='500'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>�Ҧ���v���</h3> 
				<a href="<%=request.getContextPath()%>/backend/doctor-mo.jsp">
				<img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
			</td>
		</tr>
	</table>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>�Эץ��H�U���~:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>

	<table border='1' bordercolor='#CCCCFF' width='500'>
<!-- 		<tr> -->
<!-- 			<th nowrap>��v�s��</th> -->
<!-- 			<th nowrap>��v�m�W</th> -->
<!-- 			<th nowrap>�Ǹg��</th> -->
<!-- 			<th nowrap>�ʧO</th> -->
<!-- 			<th nowrap>�Ӥ�</th> -->
<!-- 			<th nowrap>�X�ͦ~���</th> -->
<!-- 			<th nowrap>��}</th> -->
<!-- 			<th nowrap>�q��</th> -->
<!-- 			<th nowrap>�ק�</th> -->
<!-- 			<th nowrap>�R��</th> -->
<!-- 		</tr> -->

		<%@ include file="pages/page1.file"%>
		<c:forEach var="doctorVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr ${(doctorVO.drNo==param.drNo) ? 'bgcolor=#CCCCFF':''}>
				<th nowrap>��v�s��</th><td>${doctorVO.drNo}</td>
			</tr>
			<tr>
				<th nowrap>��v�m�W</th><td>${doctorVO.drName}</td>
			</tr>
			<tr>
				<th nowrap>�Ǹg��</th><td>${doctorVO.drExp}</td>
			</tr>
			<tr>
				<th nowrap>�ʧO</th>
				<td>
					<c:if test="${doctorVO.drSex == 'M'}">�k</c:if>
					<c:if test="${doctorVO.drSex == 'F'}">�k</c:if>
				</td>
			</tr>
			<tr>
				<th nowrap>�Ӥ�</th>
				<td>
					<c:if test="${doctorVO.drPic!=null}"><img src="<%= request.getContextPath()%>/dr/DBGifReader2?drNo=${doctorVO.drNo}"></c:if>
					<c:if test="${doctorVO.drPic==null}"><img src="images/nopic.jpg"></c:if>
				</td>
			</tr>
			<tr>
				<th nowrap>�X�ͦ~���</th><td>${doctorVO.drBirth}</td>
			</tr>
			<tr>
				<th nowrap>��}</th><td>${doctorVO.drAdd}</td>
			</tr>
			<tr>
				<th nowrap>�q��</th><td>${doctorVO.drTel}</td>
			</tr>
			<tr>
				<th nowrap>�ק�</th>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dr/dr.do">
						<input type="submit" value="�ק�">
						<input type="hidden" name="drNo" value="${doctorVO.drNo}"> 
						<input type="hidden" name="requestURL"value="<%=request.getServletPath()%>">
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<th nowrap>�R��</th> -->
<!-- 				<td> -->
<%-- 					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dr/dr.do"> --%>
<!-- 						 <input type="submit" value="�R��">  -->
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

<!-- 	<br>�����������|: -->
<!-- 	<br> -->
<%-- 	<b> <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br> --%>
<%-- 		<font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> --%>
<!-- 	</b> -->
<!-- jsp���� -->
    
  </c:if>  
<%@ include file="/menu2.jsp" %> 



</body>
</html>
