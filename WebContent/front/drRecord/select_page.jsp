<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<title>IBM DrRecord: Home</title>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM DrRecord: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM DrRecord: Home</p>

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
  <li><a href='listAllDrRecord.jsp'>List</a> all DrRecord. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="drRecord.do" >
        <b>輸入就診紀錄編號 (如1):</b>
        <input type="text" name="drRecNo">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

 
  <jsp:useBean id="drRecSvc" scope="page" class="com.drRecord.model.DrRecordService"/> 
  <li>
     <FORM METHOD="post" ACTION="drRecord.do" >
       <b>選擇就診紀錄編號:</b>
       <select size="1" name="drRecNo">
         <c:forEach var="drRecVO" items="${drRecSvc.all}" > 
          <option value="${drRecVO.drRecNo}">${drRecVO.drRecNo}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="drRecord.do" >
       <b>選擇就診日期:</b>
       <select size="1" name="drRecNo">
         <c:forEach var="drRecVO" items="${drRecSvc.all}" > 
          <option value="${drRecVO.drRecNo}">${drRecVO.drRecTime}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>活動管理</h3>

<ul>
  <li><a href='addDrRecord.jsp'>Add</a> a new DrRecord.</li>
</ul>

</body>

</html>
