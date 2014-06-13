<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.pow.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4001);
List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");

%>
<html>
<head><title>後端-活動查詢</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<title>前端-活動新增 - addAct.jsp</title>

</head>
			<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>"> 

<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>Back Act: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM Act: Home</p>

<h3>資料查詢:</h3>
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

<ul>
<!--   <li><a href='listAllAct.jsp'>List</a> all Acts. </li> <br><br> -->
  
<!--   <li> -->
<!--     <FORM METHOD="post" ACTION="act.do" > -->
<!--         <b>輸入會員編號 (如1):</b> -->
<!--         <input type="text" name="memNo"> -->
<!--         <input type="submit" value="送出"> -->
<!--         <input type="hidden" name="action" value="getAll_Acts_By_MemNo"> -->
<!--     </FORM> -->
<!--   </li> -->

  <jsp:useBean id="actSvc" scope="page" class="com.act.model.ActService" />
  
<!--   <h3>刪除活動報名:</h3>  -->
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="act.do" > -->
<!--        <b>選擇活動編號:</b> -->
<!--        <select size="1" name="actNo"> -->
<%--          <c:forEach var="actVO" items="${actSvc.all}" >  --%>
<%--           <option value="${actVO.actNo}">${actVO.actNo} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="submit" value="送出"> -->
<!--        <input type="hidden" name="action" value="delete_back"> -->
<!--     </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="act.do" > -->
<!--        <b>選擇活動名稱:</b> -->
<!--        <select size="1" name="actNo"> -->
<%--          <c:forEach var="actVO" items="${actSvc.all}" >  --%>
<%--           <option value="${actVO.actNo}">${actVO.actName} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="submit" value="送出"> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--      </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="act.do" name="form2"> -->
<!--        <b>選擇活動日期:</b> -->
<!--        <input class="cal-TextBox" -->
<!-- 			onFocus="this.blur()" size="9" readonly type="text" name="actStartDate" value="2014-01-01"> -->
<!-- 			<a class="so-BtnLink" -->
<!-- 			href="javascript:calClick();return false;" -->
<!-- 			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);" -->
<!-- 			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);" -->
<!-- 			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form2','actStartDate','BTN_date');return false;"> -->
<!-- 		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="活動開始日期"></a> -->
<!--        <input type="submit" value="送出"> -->
<!--        <input type="hidden" name="action" value="getAll_Acts_By_Date_For_Time"> -->
<!--      </FORM> -->
     
<!--   </li> -->
</ul>


<!-- <h3>活動管理</h3> -->
<!-- <ul> -->
<!--   <li><a href='addAct.jsp'>Add</a> a new Act.</li> -->
<!-- </ul> -->
<h3>複合查詢</h3>
<ul>  

  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do" name="form1">
        <b><font color=blue>萬用複合查詢:</font></b> <br>
        <b>輸入會員帳號:</b>
        <input type="text" name="memNo" value=""><br>
           
<!--        <b>輸入職員編號:</b> -->
<!--        <input type="text" name="empNo" value="1003"><br> -->
       
<!--        <b>輸入員工職位:</b> -->
<!--        <input type="text" name="job" value="PRESIDENT"><br> -->
    
       <b>選擇活動狀態:</b>
       <select size="1" name="actStatus" >
          <option value="">請選擇活動狀態
          <option value="Y">開放中
          <option value="N">審核中
          <option value="E">活動結束
          <option value="A">審核中(私人活動)
          <option value="B">開放中(私人活動
          
<%--          <c:forEach var="actVO" items="${actSvc.all}" >  --%>
<%--           <option value="${actVO.actStatus}">${actVO.actStatus} --%>
<%--          </c:forEach>    --%>
       </select><br>
           
<!--        <b>活動開始日期:</b> -->
<!-- 		    <input class="cal-TextBox" -->
<!-- 			onFocus="this.blur()" size="9" readonly type="text" name="actStartTime" value="1981-11-17"> -->
<!-- 			<a class="so-BtnLink" -->
<!-- 			href="javascript:calClick();return false;" -->
<!-- 			onmouseover="calSwapImg('BTN_date1', 'img_Date_OVER',true);" -->
<!-- 			onmouseout="calSwapImg('BTN_date1', 'img_Date_UP',true);" -->
<!-- 			onclick="calSwapImg('BTN_date1', 'img_Date_DOWN');showCalendar('form1','actStartTime','BTN_date1');return false;"> -->
<!-- 		    <img align="middle" border="0" name="BTN_date1"	src="images/btn_date_up.gif" width="22" height="17" alt="開始日期"></a> -->
		        
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listActs_ByCompositeQueryForBack">
     </FORM>
  </li>
</ul>
</c:if>
			<%@ include file="/menu2.jsp" %> 

</body>

</html>
