<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%@ page import="com.actregister.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ActService actSvc = new ActService();
	List<ActVO> list = actSvc.getAll();
	pageContext.setAttribute("list", list);
	
// 	ActRegisterService actRegSvc = new ActRegisterService();
// 	List<ActRegisterVO> actReg = actRegSvc.getAllActRegistersByMemNo(7005);
// 	pageContext.setAttribute("actReg", actReg);

%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmployeeService"/>
<jsp:useBean id="actRegSvc1" scope="page" class="com.actregister.model.ActRegisterService" /> 

<%-- <% 	ActVO actVO = (ActVO) request.getAttribute("actVO"); %> --%>
<html>
<head>
<title>前端-所有活動 - listAllAct.jsp</title>
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

</head>

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
<%-- 	<%= ( (ActVO)request.getAttribute("actVO") ).getActNo() %> --%>

	<table border='1' bordercolor='#CCCCFF' width='950' align='center'>
<!-- 		<tr> -->
<!-- 			<th nowrap>活動編號</th> -->
<!-- 			<th nowrap>目前狀態</th> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<th nowrap>活動名稱</th> -->
<!-- 			<th nowrap>活動內容</th> -->
<!-- 			<th nowrap>開始時間</th> -->
<!-- 			<th nowrap>結束時間</th> -->
<!-- 			<th>活動照片</th> -->
<!-- 			<th>租借器材</th> -->
<!-- 			<th>押金費用</th> -->
<!-- 			<th>主辦方繳交費用</th> -->
<!-- 			<th nowrap>報名費用</th> -->
<!-- 			<th nowrap>主辦會員</th> -->
<!-- 			<th>審核職員編號</th> -->

<!-- 		</tr> -->
		 
		<c:forEach var="actVO1" items="${list}" >
<%-- 			<tr align='center' valign='middle' ${(actVO1.actNo==actVO.actNo) ? 'bgcolor=#CCCCFF':''}> --%>
				<c:if test="${actVO1.actStatus == 'Y' || ((actVO1.actStatus == 'N' || actVO1.actStatus == 'A' || actVO1.actStatus == 'B' ) && actVO1.memNo == memVO.memno)}">
				<tr valign='middle' ${(actVO1.actNo==actVO.actNo) ? 'bgcolor=#CCCCFF':''}>
					<th>活動編號</th><td>${actVO1.actNo}</td>
					
					<th>目前狀態</th><td colspan='3'><c:if test="${actVO1.actStatus == 'Y'}">報名中</c:if>
									  <c:if test="${actVO1.actStatus == 'N'}">審核中</c:if>
									  <c:if test="${actVO1.actStatus == 'A'}">私人活動<br>未審核</c:if>
									  <c:if test="${actVO1.actStatus == 'B'}">私人活動</c:if>
									  <c:if test="${actVO1.actStatus == 'E'}">活動結束</c:if>
								  </td>
				</tr>
				<tr>
					<th>活動名稱</th><td><a href="<%=request.getContextPath()%>/front/act/act.do?action=getOne_For_Display&actNo=${actVO1.actNo}">${actVO1.actName}</a></td>
					<th>開始時間</th><td>${actVO1.actStartTime}</td>
	  	 			<th>結束時間</th><td>${actVO1.actEndTime}</td>
				</tr>
				<tr>
					<th>報名費用</th><td>${actVO1.actRegFee}</td>
					<th>主辦會員</th><td>
											 <c:forEach var="mem" items="${memSvc.all}">
											 	<c:if test="${actVO1.memNo == mem.memno}">${pageScope.mem.memname} - ${actVO1.memNo}</c:if>
											 </c:forEach>
										 </td>
					
					<td colspan='2'>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/act/act.do" style="display:inline; float:left;">
							<c:if test="${actVO1.memNo == memVO.memno && (actVO1.actStatus == 'N' || actVO1.actStatus == 'A')}">
								<input type="submit" value="修改">
								<input type="hidden" name="actNo" value="${actVO1.actNo}">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<input type="hidden" name="action" value="getOne_For_Update">
							</c:if>
						</FORM>
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/act/act.do" style="display:inline;">	
						<c:set  var = "isRegister" value = "false" />
							<c:forEach var="actReg" items="${actRegSvc1.all}">
								<c:if test="${actVO1.actStatus == 'Y' && actReg.memNo == memVO.memno}">
<%-- 								<br/>index : ${s.index} +  ////  actReg :${actReg.actNo}   --%>
<!-- 								設置一個控制變數isRegister,來判斷是否已報名,預設是false	 -->
									
<%-- 								${isRegister} 121 --%>
<!-- 								如果在此迴圈內,發現此活動已報名,才改為true	 -->
									    <c:if test="${actReg.actNo == actVO1.actNo}">
									    	<c:set  var = "isRegister" value = "true" />	
<%-- 									    	<br/> 125 : ${isRegister} 125 --%>
									    </c:if>
											
										
								</c:if>
							</c:forEach>	
<%-- 							<%=pageContext.getAttribute("isRegister") + ":131"%> --%>
<!-- 						在迴圈外再來判斷該活動 是否 已報名過	,(不應在迴圈內印出) -->
							<c:if test="${isRegister == false && actVO1.memNo != memVO.memno && memVO != null}">									
								<input type="submit" value="報名" id="btn" >	
								<input type="hidden" name="actNo" value="${actVO1.actNo}">
<%-- 								<input type="hidden" name="actReg" value="${actReg.actNo}"> --%>
								<input type="hidden" name="action" value="getOne_For_ActRegister">
							
							</c:if>
							<c:if test="${isRegister == true && actVO1.actStatus == 'Y' }">	
						   		<input type="submit" value="已報名" disabled>
							</c:if>
							
						</FORM>

						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/actRegister/actRegister.do" style="display:inline;">
							  <c:if test="${actVO1.actStatus == 'Y' && actVO1.memNo == memVO.memno}">
							   		<input type="submit" value="查詢報名狀況" >
							    	<input type="hidden" name="actNo" value="${actVO1.actNo}">
							    	<input type="hidden" name="action"value="getAll_For_Display_By_ActNo">
							  </c:if>
						</FORM>
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/act/act.do" style="display:inline;">
								<c:if test="${actVO1.memNo == memVO.memno && (actVO1.actStatus == 'N' || actVO1.actStatus == 'A')}">
							    	<input type="submit" value="刪除" >
							    	<input type="hidden" name="actNo" value="${actVO1.actNo}">
							    	<input type="hidden" name="action"value="delete">
							    </c:if>
						</FORM>
					
<%-- 						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/actRegister/actRegister.do"> --%>
<%-- 								<c:forEach var="actReg" items="${actRegSvc.all}"> --%>
<%-- 									<c:if test="${actVO1.actStatus == 'Y' && actReg.memNo == '7005' && actReg.actNo == actVO1.actNo}"> --%>
<!-- 							    		<input type="submit" value="取消報名"> -->
<%-- 							    		<input type="hidden" name="actNo" value="${actVO1.actNo}"> --%>
<!-- 							    		<input type="hidden" name="memNo" value="7005"> -->
<!-- 							    		<input type="hidden" name="action"value="delete_By_MemNo_And_ActNo"> -->
<%-- 							    	</c:if> --%>
<%-- 								</c:forEach> --%>
<!-- 						</FORM> -->
					</td>
				</tr>
<%-- 					<td>${actVO1.actContent}</td> --%>
					
					
<%-- 					<td><img src="<%=request.getContextPath()%>/act/DBGifReader3?actNo=${actVO1.actNo}"></td> --%>
<%-- 					<td>${actVO1.actEquipment}</td> --%>
<%-- 					<td>${actVO1.actDeposit}</td> --%>
<%-- 					<td>${actVO1.actHostFee}</td> --%>
						
					
					
<!-- 					<td> -->
<%-- 						<c:forEach var="emp" items="${empSvc.all}"> --%>
<%-- 							<c:if test="${actVO1.empNo == emp.empNo }"> --%>
<%-- 								${emp.empName}-${actVO1.empNo} --%>
<%-- 							</c:if> --%>
<%-- 						</c:forEach> --%>
<!-- 					</td> -->
		
				</c:if>
<!-- 			</tr> -->
		</c:forEach>
	</table>
	
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>

	<!-- <br>本網頁的路徑:<br><b> -->
	<%--    <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br> --%>
	<%--    <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b> --%>

</body>
</html>
