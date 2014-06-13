<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4002);
	List<PowVO> list = (List<PowVO>)session.getAttribute("list");
 %>
<jsp:useBean id="listApts_ByCompositeQuery" scope="request" type="java.util.List" />
<jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService" />
<html>
<head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">

<script>
	
		
		$(function(){
			$('#menu').children('li').click(function(){
				$('#menu').find('ul').removeClass('active');
				$(this).find('ul').toggleClass('active');	
			});
		});
		
</script>
<title>�ƦX�d�� - listApts_ByCompositeQuery.jsp</title>
</head>
<body bgcolor='white'>
<c:if test="<%=list.contains(powVO)%>">  
<%@ include file="/menu1.jsp" %> 
    
 <!--jsp�D�n�}�l -->
<b><font color=blue>
<!-- ���U�νƦX�d��-�i�ѫȤ���H�N�W�����Q�d�ߪ����<br> -->
<!-- �������@���ƦX�d�߮ɤ����G�m�ߡA</font> <font color=red>�w�W�[�����B�e�X�ק�B�R�����\��:</font></b> -->
<c:if test="<%=list.contains(powVO)%>">  
<!-- <table border='1' cellpadding='5' cellspacing='0' width='800'> -->
<!-- 	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'> -->
<!-- 		<td> -->
<!-- 		<h3><font color=red>�ƦX�d��</font>���u - listApts_ByCompositeQuery.jsp</h3> -->
<%-- 		<a href="<%=request.getContextPath()%>/backend/apt-Knifeman.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^�����޲z����</a> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- </table> -->

</c:if>
<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th nowrap>�����s��&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>�������&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>�����ɬq&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>���X�P�s��&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>����ɶ�&nbsp;&nbsp;&nbsp;</th>
		<th nowrap>�d���s��+�W�r&nbsp;&nbsp;&nbsp;</th>
	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file"%>
	<c:forEach var="aptVO" items="${listApts_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(aptVO.aptNo==param.aptNo) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
			<td nowrap>${aptVO.aptNo}</td>
			<td nowrap>${aptVO.aptDate}</td>
			<td nowrap>${aptVO.aptPeriod}</td>
			<td nowrap>${aptVO.aptNoSlip}</td>
			<td nowrap>${aptVO.aptRegTime}</td>		
			<td nowrap><c:forEach var="petVO" items="${petSvc.all}">
                    <c:if test="${petVO.petNo==aptVO.petNo}">
	                    ${aptVO.petNo}�i<font color=orange>${petVO.petName}</font> �j
	                    <td>
	                    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do">
			    				<input type="hidden" name="petNo" value="${petVO.petNo}"> 
			    				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			   					<input type="hidden" name="action" value="getOne_For_Display">
			    				<input type="submit" value="�d�ߦ��d��">
			    			</FORM>
	                    </td>
                    </c:if>
                </c:forEach>
			</td>
						<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/apt/apt.do">
			     <input type="submit" value="�ק�"> 
			     <input type="hidden" name="aptNo" value="${aptVO.aptNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/apt/apt.do">
			    <input type="submit" value="�R��">
			    <input type="hidden" name="aptNo" value="${aptVO.aptNo}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>

<!-- <br>�����������|:<br><b> -->
<%--    <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br> --%>
<%--    <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b> --%>

<!--jsp�D�n���� -->
    
    
    
    

    <%@ include file="/menu2.jsp" %> 
</c:if>
</body>

</html>
