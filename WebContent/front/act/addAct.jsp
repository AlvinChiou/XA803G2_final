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
<title>活動資料新增 - addAct.jsp</title>
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
		  alert("請選擇開始時間!");
		  return false;
	  }
	  document.getElementById("action").value=actionVal;
	  document.getElementById("form1").submit();
  }
</script>

<script>
function miracle() {
	document.getElementsByName("actName")[0].value = "認養活動";
	document.getElementsByName("actContent")[0].value = "[認養須知]請妥善細心照顧送養人託付給您每一隻幼小的生命，不要辜負我們對牠所付出的愛心。領養前，謹慎考慮、慎重所選；領養後，珍惜所愛、愛你所選。(認養前可參考下方'認養問卷')請攜帶身分證作認養登記，認養人需年滿二十歲，並不限定於台中市民。認養資料將檢送(台中市動物防疫所)列管追蹤，須遵守(動物保護法)，落實寵物登記管理辦法之規定。領養後覺得不合適，請一星期內連同認養承諾書帶回，告知退回原因。若超過一星期者，有義務為寵物另找主人。負起(養牠愛牠，不傷害不拋棄)的責任。需為牠結紮、植入晶片，定期做好各項預防注射。不任意將寵物轉手他人，善盡疾病醫療、餵養照顧之責任。認養費用請看此頁最下方之一覽表。";
	document.getElementsByName("actEquipment")[0].value = "無";
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
<p style="text-align:right; margin-right:50px; color:orange;">目前位置：<a href="<%=request.getContextPath()%>/iPETCares.jsp">首頁</a>>><a href="<%=request.getContextPath()%>/front/act/listAllAct.jsp">所有活動資訊</a></p>
		
	<div style="margin-left:30px">
	<h3>活動資料:<font color=red><b>*為必填欄位</b></font></h3>
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

	<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
	
	<FORM METHOD="post" ACTION="act.do" name="form2">
		<table>
		<tr>
			<td><b>選擇活動日期:</b></td>
			<td>
				<input type="text" id="datepicker3" name="actStartDate" value="<%=(sn.getAttribute("actStartDate") == null) ? date_SQL : sn.getAttribute("actStartDate")%>" />
				
				<input type="submit" value="確定"> 
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
				<td>日期:</td>
				<td><input type=text name="actStartDate" value="<%= (sn.getAttribute("actStartDate") == null) ? date_SQL : sn.getAttribute("actStartDate") %>" readonly></td>
			</tr>
			<tr>
				<td>時間:</td>
				<td>開始時間: 
				<select id="startHour" name="startHour" class="cal-TextBox" onFocus="this.blur()" onchange="sendFrom('getAll_Acts_By_Date_For_endTime')" size="1">
						<option value="">請選擇</option>
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
				結束時間: <select name="endHour" class="cal-TextBox" onFocus="this.blur()" size="1" readonly>

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
				<td>活動名稱:<font color=red><b>*</b></td>
				<td><input type="TEXT" name="actName" size="55"
					value="<%=(actVO == null) ? "" : actVO.getActName()%>" /></td>
			</tr>
			<tr>
				<td>活動內容:<font color=red><b>*</b></td>
				<td><textarea name="actContent" rows="10" cols="50" value="<%=(actVO == null) ? "" : actVO.getActContent()%>" /><%=(actVO == null) ? "" : actVO.getActContent()%></textarea></td>
			</tr>
			
			<tr>
				<td>活動照片:</td>
				<td><input type="file" name="actPic" size="20"
					value="<%=(actVO == null) ? "pic" : actVO.getActPic()%>" /></td>
			</tr>

			<tr>
				<td>租借器材:</td>
				<td><textarea name="actEquipment" rows="3" cols="50"
						value="<%=(actVO == null) ? "無" : actVO.getActEquipment()%>" /></textarea></td>
			</tr>
			<tr>
				<td>押金費用:</td>
				<td><input type="hidden" name="actDeposit" size="20" value="<%=(actVO == null) ? "1500" : actVO.getActDeposit()%>" />1500</td>
			</tr>
			<tr>
				<td>租借場地費用:</td>
				<td><input type="hidden" name="actHostFee" size="20" value="<%=(actVO == null) ? "1000" : actVO.getActHostFee()%>" />1000</td>
			</tr>
			<tr>
				<td>活動報名費用:</td>
				<td><input type="TEXT" name="actRegFee" size="20"
					value="<%=(actVO == null) ? "0" : actVO.getActRegFee()%>" /></td>
			</tr>
			<tr><td>活動類型:</td>
				<td>
					<%if(actVO != null){ %>
					<input type="radio" name="actStatus" value="A" <%=(actVO.getActStatus().equals("A")) ? "checked" : ""%>/>私人
					<input type="radio" name="actStatus" value="N" <%=(actVO.getActStatus().equals("N")) ? "checked" : ""%>/>公開
					<%} else { %>
					<input type="radio" name="actStatus" value="A"/>私人
					<input type="radio" name="actStatus" value="N" checked/>公開
					<%} %>
				</td>
			</tr>
			<tr>
				<td>主辦方會員帳號:</td>
				<td><input type="hidden" name="memNo" size="10"
					value="<%=memVO.getMemno()%>" /><%=memVO.getMemname()%></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>審核職員編號:</td> -->
<!-- 				<td> -->
				<input type="hidden" name="empNo" size="10" value="<%=(actVO == null) ? "1003" : actVO.getEmpNo()%>" />
<!-- 					</td> -->
<!-- 			</tr> -->
		</table>
		<div align='right' style="margin-right:30px">
			<input type="hidden" id="action" name="action" value=""> <br><!--getAll_Acts_By_Date_For_endTime -->
			<input type="button" value="送出新增" onclick="sendFrom('insert')">
<input type = "button" id = "btn" value = "click me" onclick = "miracle()">
		</div>
	</FORM>
	<br>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
</body>

</html>
