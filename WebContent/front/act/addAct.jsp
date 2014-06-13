<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.act.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%	ActVO actVO = (ActVO) request.getAttribute("ActVO");%>
<%	String startHour = (String)request.getAttribute("startHour");%>

<% HttpSession sn = request.getSession(); %>

<html>
<head>
<title>���ʸ�Ʒs�W - addAct.jsp</title>
<link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" />
<link href="<%= request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css" />
<link href="<%= request.getContextPath()%>/css/box_css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%= request.getContextPath()%>/js/jquery-1.4.2.js"></script>  
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/jquery.fancybox-1.3.4.css" media="screen" />
	
<script type="text/javascript">
	$(document).ready(function() {
		/*
		*   Examples - images
		*/

		$("a#example1").fancybox();
		$("#various3").fancybox({
			'width'				: '75%',
			'height'			: '75%',
			'autoScale'			: false,
			'transitionIn'		: 'none',
			'transitionOut'		: 'none',
			'type'				: 'iframe'
		});
	});
</script>
<script type="text/javascript">
  function sendFrom(actionVal){
	  if(document.getElementById("startHour").value==""){
		  alert("�п�ܶ}�l�ɶ�!");
		  return false;
	  }
	  document.getElementById("action").value=actionVal;
	  document.getElementById("form1").submit();
  }
</script>

<script>
function miracle() {
	document.getElementsByName("actName")[0].value = "�{�i����";
	document.getElementsByName("actContent")[0].value = "[�{�i����]�Ч����Ӥ߷��U�e�i�H�U�I���z�C�@�����p���ͩR�A���n�d�t�ڭ̹�e�ҥI�X���R�ߡC��i�e�A�ԷV�Ҽ{�B�V���ҿ�F��i��A�ñ��ҷR�B�R�A�ҿ�C(�{�i�e�i�ѦҤU��'�{�i�ݨ�')����a�����ҧ@�{�i�n�O�A�{�i�H�ݦ~���G�Q���A�ä����w��x�������C�{�i��ƱN�˰e(�x�����ʪ����̩�)�C�ްl�ܡA����u(�ʪ��O�@�k)�A�����d���n�O�޲z��k���W�w�C��i��ı�o���X�A�A�Ф@�P�����s�P�{�i�ӿծѱa�^�A�i���h�^��]�C�Y�W�L�@�P���̡A���q�Ȭ��d���t��D�H�C�t�_(�i�e�R�e�A���ˮ`���߱�)���d���C�ݬ��e���ϡB�ӤJ�����A�w�����n�U���w���`�g�C�����N�N�d�����L�H�A���ɯe�f�����B���i���U���d���C�{�i�O�νЬݦ����̤U�褧�@����C";
	document.getElementsByName("actEquipment")[0].value = "�L";
	document.getElementsByName("actRegFee")[0].value = "200";

}
</script>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />

<script>
	$(function() {
		$("#datepicker3").datepicker({
			defaultDate:(new Date()),
			dateFormat:"yy-mm-dd",
			showOn : "button",
			buttonImage : "<%= request.getContextPath()%>/front/mem/images/calendar.gif",
			buttonImageOnly : true,
			yearRange:"-90:+0",
			changeMonth: true,
			changeYear: true
		});
	});
</script>

</head>

<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<div id="wrapper">
  		<div id="main_bg">
    	<div id="main_bg01">
        	<div id="apDiv13"></div>
			<c:if test="${memVO == null}"><%@ include file="/header.jsp" %></c:if>
        	<c:if test="${memVO != null}"><%@ include file="/loginHeader.jsp" %></c:if>
			<%@ include file="/ad.jsp" %>   
  		</div>
		</div>
	<a id="titleimg"><img src="<%= request.getContextPath()%>/images/main_tit03.png"></a>
<br>
<p style="text-align:right; margin-right:50px; color:orange;">�ثe��m�G<a href="<%=request.getContextPath()%>/iPETCares.jsp">����</a>>><a href="<%=request.getContextPath()%>/front/act/listAllAct.jsp">�Ҧ����ʸ�T</a></p>
		
	<div style="margin-left:30px">
	<h3>���ʸ��:<font color=red><b>*���������</b></font></h3>
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

	<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
	
	<FORM METHOD="post" ACTION="act.do" name="form2">
		<table>
		<tr>
			<td><b>��ܬ��ʤ��:</b></td>
			<td>
				<input type="text" id="datepicker3" name="actStartDate" value="<%=(sn.getAttribute("actStartDate") == null) ? date_SQL : sn.getAttribute("actStartDate")%>" />
				
				<input type="submit" value="�T�w"> 
				<input type="hidden" name="action" value="getAll_Acts_By_Date_For_startTime">
				<input type="hidden" name="requestURL" value="<%= request.getServletPath() %>">
			</td>
		</tr>
		</table>
	</FORM>
	</div>

	<FORM METHOD="post" ACTION="act.do" id="form1" name="form1" enctype="multipart/form-data">
		<table border='1' bordercolor='#CCCCFF' width='950' align='center'>

			<tr>
				<td>���:</td>
				<td><input type=text name="actStartDate" value="<%= (sn.getAttribute("actStartDate") == null) ? date_SQL : sn.getAttribute("actStartDate") %>" readonly></td>
			</tr>
			<tr>
				<td>�ɶ�:</td>
				<td>�}�l�ɶ�: 
				<select id="startHour" name="startHour" class="cal-TextBox" onFocus="this.blur()" onchange="sendFrom('getAll_Acts_By_Date_For_endTime')" size="1">
						<option value="">�п��</option>
						<%
							if ((TreeSet<Integer>) request.getAttribute("occupied") != null) {
								for (int i = 10; i < 23; i++) {
									boolean isOccupied = false;
									String checkMark = "";
									for (Integer j : (TreeSet<Integer>) request.getAttribute("occupied")) {
										if (i == j.intValue()) {
											isOccupied = true;
										}
									}
									if (!isOccupied) {
										if(startHour != null){
											if(i == Integer.parseInt(startHour.substring(1, 3))){
												checkMark = "selected";
											}
										}				
						%>
						<option value=" <%=i%>:00:00" <%=checkMark %>><%=i%>:00</option>
						<%
							}
								}
							} else {
						%>
						<option value="10:00:00">10:00</option>
						<option value="11:00:00">11:00</option>
						<option value="12:00:00">12:00</option>
						<option value="13:00:00">13:00</option>
						<option value="14:00:00">14:00</option>
						<option value="15:00:00">15:00</option>
						<option value="16:00:00">16:00</option>
						<option value="17:00:00">17:00</option>
						<option value="18:00:00">18:00</option>
						<option value="19:00:00">19:00</option>
						<option value="20:00:00">20:00</option>
						<option value="21:00:00">21:00</option>
						<%
							}
						%>
				</select>
				�����ɶ�: <select name="endHour" class="cal-TextBox" onFocus="this.blur()" size="1" readonly>

						<%
							if ((TreeSet<Integer>) request.getAttribute("endTime") != null) {
								for (int i = 10; i < 23; i++) {
									if(((TreeSet<Integer>) request.getAttribute("endTime")).contains(i) ){
										%>
										<option value=" <%=i%>:00:00"><%=i%>:00</option>				
									<%}
								}
							}
						 %>
				</select></td>
			</tr>
			
			<tr>
				<td>���ʦW��:<font color=red><b>*</b></td>
				<td><input type="TEXT" name="actName" size="55"
					value="<%=(actVO == null) ? "" : actVO.getActName()%>" /></td>
			</tr>
			<tr>
				<td>���ʤ��e:<font color=red><b>*</b></td>
				<td><textarea name="actContent" rows="10" cols="50" value="<%=(actVO == null) ? "" : actVO.getActContent()%>" /><%=(actVO == null) ? "" : actVO.getActContent()%></textarea></td>
			</tr>
			
			<tr>
				<td>���ʷӤ�:</td>
				<td><input type="file" name="actPic" size="20"
					value="<%=(actVO == null) ? "pic" : actVO.getActPic()%>" /></td>
			</tr>

			<tr>
				<td>���ɾ���:</td>
				<td><textarea name="actEquipment" rows="3" cols="50"
						value="<%=(actVO == null) ? "�L" : actVO.getActEquipment()%>" /></textarea></td>
			</tr>
			<tr>
				<td>����O��:</td>
				<td><input type="hidden" name="actDeposit" size="20" value="<%=(actVO == null) ? "1500" : actVO.getActDeposit()%>" />1500</td>
			</tr>
			<tr>
				<td>���ɳ��a�O��:</td>
				<td><input type="hidden" name="actHostFee" size="20" value="<%=(actVO == null) ? "1000" : actVO.getActHostFee()%>" />1000</td>
			</tr>
			<tr>
				<td>���ʳ��W�O��:</td>
				<td><input type="TEXT" name="actRegFee" size="20"
					value="<%=(actVO == null) ? "0" : actVO.getActRegFee()%>" /></td>
			</tr>
			<tr><td>��������:</td>
				<td>
					<%if(actVO != null){ %>
					<input type="radio" name="actStatus" value="A" <%=(actVO.getActStatus().equals("A")) ? "checked" : ""%>/>�p�H
					<input type="radio" name="actStatus" value="N" <%=(actVO.getActStatus().equals("N")) ? "checked" : ""%>/>���}
					<%} else { %>
					<input type="radio" name="actStatus" value="A"/>�p�H
					<input type="radio" name="actStatus" value="N" checked/>���}
					<%} %>
				</td>
			</tr>
			<tr>
				<td>�D���|���b��:</td>
				<td><input type="hidden" name="memNo" size="10"
					value="<%=memVO.getMemno()%>" /><%=memVO.getMemname()%></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>�f��¾���s��:</td> -->
<!-- 				<td> -->
				<input type="hidden" name="empNo" size="10" value="<%=(actVO == null) ? "1003" : actVO.getEmpNo()%>" />
<!-- 					</td> -->
<!-- 			</tr> -->
		</table>
		<div align='right' style="margin-right:30px">
			<input type="hidden" id="action" name="action" value=""> <br><!--getAll_Acts_By_Date_For_endTime -->
			<input type="button" value="�e�X�s�W" onclick="sendFrom('insert')">
<input type = "button" id = "btn" value = "click me" onclick = "miracle()">
		</div>
	</FORM>
	<br>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
</body>

</html>
