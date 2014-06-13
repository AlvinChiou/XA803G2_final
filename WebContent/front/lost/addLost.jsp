<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.lost.model.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<%  LostVO lostVO = (LostVO) request.getAttribute("lostVO");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>失蹤文章新增 - addLost.jsp</title>
<link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" />
<title>iPET Cares</title>
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

<script>
function miracle() {
	document.getElementsByName("losttitle")[0].value = "【台南市】台南市安南區同安路走失非洲灰鸚鵡";
	document.getElementsByName("lostcontent")[0].value
	= "我家湯姆貓昨天一時沒發現牠掙脫鏈子，結果跑走了。徵是：全身灰，尾巴紅色，左腳有藍色腳環（如圖），右腳有個８字鐵環為鎖練子用大概２歲，還不大會學說什麼會，幾乎只會亂叫 但是叫自己的名字[喵喵]這詞還滿清楚的 還會吹口哨名曲－桂河大橋的第一句，在中壢市中央大學這邊跑走的 希望撿到的好心人能盡快連絡我，讓喵喵可以回家我的電話：0919053489 吳先生感激不盡";

}
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

<%-- 錯誤表列  --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<form method="post" action="<%= request.getContextPath()%>/front/lost/lost.do" name="form1" enctype="multipart/form-data">
<table border="0" align='center'>
	<tr>
		<td><b>失蹤協尋:</b></td>
	</tr>
	<tr>
		<td>文章標題:</td>
		<td><input type="text" name="losttitle" size="60" value="<%= (lostVO==null)? "" : lostVO.getLosttitle()%>" /></td>
	</tr>
	<tr>
		<td>照片:</td>
		<td><input type="file" name="lostpic" size="60" /></td>
	</tr>
	<tr>
		<td>文章內容:</td>
		<td><textarea name="lostcontent" rows="30" cols="55" ><%= (lostVO==null)? "" : lostVO.getLostcontent() %></textarea></td>
	</tr>
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>登載時間:</td>
		<td>
		    <input type="hidden" id="datepicker3" name="losttime" value="<%= date_SQL%>" /><%= date_SQL%>
		</td>
	</tr>
	<tr>
		<td>找尋狀態:</td>
		<td>
			<input type="radio" name="loststate" size="2" value="<%= (lostVO==null)? "0" : lostVO.getLoststate()%>" checked='' />失蹤
			<input type="radio" name="loststate" size="2" value="<%= (lostVO==null)? "1" : lostVO.getLoststate()%>" />拾獲</td>
	</tr>
	<tr>
		<td>會員編號</td>
		<td><input type="hidden" name="memno" size="10" value="${memVO.memno}"/>${memVO.memno}</td>
	</tr>
</table>
	
	<div align='right' style="float:right; margin-right:240px;">
		<input type="submit" name="送出">
		<input type = "button" id = "btn" value = "click me" onclick = "miracle()">
		<input type="hidden" name="action" value="insert">
	</div>
<br>
</form>
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
</body>
</html>