<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<html>
<head>
<title>�e��-���ʭ���</title>
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
</head>

<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
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

<ul>
  <li><a href='listAllAct.jsp'>List</a> all Acts. </li> <br>
 
<h3>�������W:</h3>  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actRegister/actRegister.do" >
        <b>�|���s��:</b>
        <input type="hidden" name="memNo" value="${memVO.memno}">${memVO.memno}
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="getAll_For_Display_By_MemNo_For_Delete">
    </FORM>
  </li>

  <jsp:useBean id="actSvc" scope="page" class="com.act.model.ActService" />
   
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="act.do" > -->
<!--        <b>��ܬ��ʽs��:</b> -->
<!--        <select size="1" name="actNo"> -->
<%--          <c:forEach var="actVO" items="${actSvc.all}" >  --%>
<%--           <option value="${actVO.actNo}">${actVO.actNo} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="submit" value="�e�X"> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--     </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="act.do" > -->
<!--        <b>��ܬ��ʦW��:</b> -->
<!--        <select size="1" name="actNo"> -->
<%--          <c:forEach var="actVO" items="${actSvc.all}" >  --%>
<%--           <option value="${actVO.actNo}">${actVO.actName} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="submit" value="�e�X"> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--      </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="act.do" name="form2"> -->
<!--        <b>��ܬ��ʤ��:</b> -->
<!--        <input class="cal-TextBox" -->
<!-- 			onFocus="this.blur()" size="9" readonly type="text" name="actStartDate" value="2014-01-01"> -->
<!-- 			<a class="so-BtnLink" -->
<!-- 			href="javascript:calClick();return false;" -->
<!-- 			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);" -->
<!-- 			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);" -->
<!-- 			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form2','actStartDate','BTN_date');return false;"> -->
<!-- 		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="���ʶ}�l���"></a> -->
<!--        <input type="submit" value="�e�X"> -->
<!--        <input type="hidden" name="action" value="getAll_Acts_By_Date_For_Time"> -->
<!--      </FORM> -->
     
<!--   </li> -->
</ul>


<h3>���ʺ޲z</h3>
<ul>
  <li><a href='addAct.jsp'>Add</a> a new Act.</li>
</ul>
<h3>�ƦX�d��</h3>
<ul>  

  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do" name="form1">
        <b><font color=blue>�U�νƦX�d��:</font></b> <br>
        <b>��J�|���s��:</b>
        <input type="text" name="memNo" value="7001"><br>
           
<!--        <b>��J¾���s��:</b> -->
<!--        <input type="text" name="empNo" value="1003"><br> -->
       
<!--        <b>��J���u¾��:</b> -->
<!--        <input type="text" name="job" value="PRESIDENT"><br> -->
    
       <b>��ܬ��ʪ��A:</b>
       <select size="1" name="actStatus" >
          <option value="">
          <option value="Y">���W��
          <option value="N">�f�֤�
          <option value="A">���f��(�p�H����)
          <option value="B">�p�H����
          
<%--          <c:forEach var="actVO" items="${actSvc.all}" >  --%>
<%--           <option value="${actVO.actStatus}">${actVO.actStatus} --%>
<%--          </c:forEach>    --%>
       </select><br>
           
<!--        <b>���ʶ}�l���:</b> -->
<!-- 		    <input class="cal-TextBox" -->
<!-- 			onFocus="this.blur()" size="9" readonly type="text" name="actStartTime" value="1981-11-17"> -->
<!-- 			<a class="so-BtnLink" -->
<!-- 			href="javascript:calClick();return false;" -->
<!-- 			onmouseover="calSwapImg('BTN_date1', 'img_Date_OVER',true);" -->
<!-- 			onmouseout="calSwapImg('BTN_date1', 'img_Date_UP',true);" -->
<!-- 			onclick="calSwapImg('BTN_date1', 'img_Date_DOWN');showCalendar('form1','actStartTime','BTN_date1');return false;"> -->
<!-- 		    <img align="middle" border="0" name="BTN_date1"	src="images/btn_date_up.gif" width="22" height="17" alt="�}�l���"></a> -->
		        
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="listActs_ByCompositeQuery">
     </FORM>
  </li>
</ul>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
</body>

</html>
