<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-tw">
<meta http-equiv="Content-Type" content="text/html; charset=big5">
<TITLE>��ͦ�ƾ� , �����t��</TITLE>
<link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" />
<title>iPET Cares</title>
<style type="text/css">
</style>
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
</HEAD>

<BODY bgcolor='white'>

	<div id="wrapper">
  		<div id="main_bg">
    	<div id="main_bg01">
        	<div id="apDiv13"></div>
			<c:if test="${memVO == null}"><%@ include file="/header.jsp" %></c:if>
        	<c:if test="${memVO != null}"><%@ include file="/loginHeader.jsp" %></c:if>
			<%@ include file="/ad.jsp" %>   
  		</div>
		</div>
	<a id="titleimg"><img src="<%= request.getContextPath()%>/images/main_tit04.png"></a>
<p style="text-align:right; margin-right:120px; color:orange;">�ثe��m�G<a href="<%=request.getContextPath()%>/iPETCares.jsp">����</a>>><a href="<%=request.getContextPath()%>/front/shift/listAllShiftInCalendar.jsp">�ڭn����</a></p>
		
	<a href = "<%=request.getContextPath()%>/front/shift/listAllShiftInCalendar.jsp" style="margin-left:105px;">���s��z</a>
	<table border=0 cellpadding=0 cellspacing=0 width="80%" align="center">  
	  <tr>
	    <td>  
<!--          <font color=blue>��</font><font><b>�w��i�J: ���|�����t�Ρi�� <img src="images/foot.gif" border="0" height="22">�O���� �j , <font color=blue>��</font>�t��</b></font>              -->
       </td>               
	  </tr>               
    </table> 

<p>
<SCRIPT LANGUAGE="JavaScript">

var mo = "";
var child = "";
var year = "";
var today = new Date();
var dt = today.getDate();
var dy = today.getDay();
var currentmonth = today.getMonth();
var currentyear = today.getFullYear();
var monthlen = new Array(31,checkLeapYear(year),31,30,31,30,31,31,30,31,30,31);
var days = new Array("��", "�@", "�G", "�T", "�|", "��", "��")
var cmon = new Array("1.", "2.", "3.", "4.", "5.", "6.", "7.", "8.", "9.", "10.", "11.", "12.");

if (document.cookie == "") {
  mo = today.getMonth();
  year = today.getFullYear();
} else {
  mo = getCookie('whichmonth');
  year = getCookie('whichyear');
  if (mo == null) {
    mo = today.getMonth();
    year = today.getFullYear();
    alertReminders();
  }
}
setCookie('whichmonth',mo);
setCookie('whichyear', year);

var count = 1;
firstday = new Date(year, mo, 1);
startday = firstday.getDay();
var factor = startday - 1;
var endday = parseInt(monthlen[mo]) + factor;

var calendar = "";
calendar =  "<table bgcolor='white' width='80%' border=1 bordercolor=black cellpadding=0 cellspacing=0>\n";

calendar += "<tr align='center'><td>\n";
calendar += "<table width='100%' border='0' cellpadding='0' cellspacing='0'>\n";
calendar += "<th bgcolor='#000000'><a href='javascript:backup();'><font color='#ffffff'><<�W�Ӥ�</font></a> </th>\n";
calendar += "<th bgcolor='black'>   <a href='javascript:open_all(\"noteListMonth.jsp\");'><font color='lightblue'>" + year + "-" + cmon[mo] + "��Ҧ��ݶE�ɶ�</font></a></th>\n";
calendar += "<th bgcolor='#000000'><a href='javascript:stepup();'><font color='#ffffff'>�U�Ӥ�>></font></a> </th>\n";
calendar += "</table></td></tr>\n";

calendar += "<tr align='center'><td>\n";
calendar += "<table width='100%' border='0' cellpadding='0' cellspacing='0' bgcolor='#eeeeee'>\n";
for (i = 0; i < 7; i++) {
 if (i==0) 
  calendar += "<th width='14%'><font size=2 color='#ff0000'>�g" + days[i] + "</font></th>\n";
 else 
  calendar += "<th width='14%'><font size=2 color='#000000'>�g" + days[i] + "</font></th>\n";
}
calendar += "</table></td></tr>\n";

calendar += "<tr align='center'><td>\n";
calendar += "<table width='100%' border='0' cellpadding='0' cellspacing='0'>\n";
calendar += "<tr align='center' >";
if (startday > 0) {
  for (empty = 0; empty < startday; empty++) {
    calendar += "<td width='14%' height=30 >&nbsp;</td>";
  }
}
for (i = startday; i <= endday;i++) {
  if ( (i % 7) == 0) { calendar += "</tr>\n<tr align='center'>"; }
  

  
calendar += "<td width='14%' height='30'";  

var count2; 
if(count<10) count2='0'+count;
else count2=count;

var mo1= (new Number(mo))+1;
var mo2;
if(mo1<10) mo2='0'+mo1;
else mo2=mo1;

  if ( (i - (startday - 1)) == dt && currentmonth == mo && currentyear == year) {
    calendar += " bgcolor='lightblue'>"+"<b>";
    calendar += "<iframe src=\"JSTL01_query4_Index_forEach_param2.jsp?shiftDate=" + year + "-" + mo2 + "-" + count2 +  "\" width=100% height=110 scrolling=no frameborder=1 ></iframe>";
    calendar += "<a href='javascript:open_window(\"JSTL01_query4_Index_forEach_param2.jsp?shiftDate=" + year + "-" + mo2 + "-" + count2 + "\");'><font color='#ff0000'>" + count + "</font>";  
    //��Ӧ�ƾ䪺�s�����M�i��
    //calendar += "<a href='javascript:open_window(\"noteList.jsp?" + mo + "&" + count + "&" + year + "\");'><font color='#ff0000'>" + count + "</font>"; 
    if (currentmonth == mo && currentyear == year && document.cookie) {
      var isremind = getCookie(cmon[mo] + count);
      if (isremind) { calendar += "<img src='shift/images/foot.gif' border=0 height='500'>"; }
    }
<%--     calendar += "<br/><a href = '<%=request.getContextPath()%>/backend/shift/addShift.jsp?shiftDate="+ year + '-' + mo2 + '-' + count2 + " '>�s�W</a>"; --%>

  }else {
    calendar += " bgcolor='lightblue'>";    
      calendar += "<iframe src=\"JSTL01_query4_Index_forEach_param2.jsp?shiftDate=" + year + "-" + mo2 + "-" + count2 +  "\" width=100% height=110 scrolling=no frameborder=1 ></iframe>";
      calendar += "<a href='javascript:open_window(\"JSTL01_query4_Index_forEach_param2.jsp?shiftDate=" + year + "-" + mo2 + "-" + count2 + "\");'><font color='#000000'>" + count + "</font>"; 
    //��Ӧ�ƾ䪺�s�����M�i��
      //calendar += "<a href='javascript:open_window(\"noteList.jsp?" + mo + "&" + count + "&" + year +  "\");'><font color='#000000'>" + count + "</font>";
    //if (currentmonth == mo && currentyear == year && document.cookie) {
    if (currentyear == year && document.cookie) {
      var isremind = getCookie(cmon[mo] + count);
      if (isremind) { calendar += "<img src='shift/images/foot.gif' border=0 height='22'>"; }
    }
<%--     calendar += "<br/><a href = '<%=request.getContextPath()%>/backend/shift/addShift.jsp?shiftDate="+ year + '-' + mo2 + '-' + count2 + " '>�s�W</a>"; --%>
  }

calendar += "</td>";
  count++;
}
calendar += "</tr></table>\n";
calendar += "</td></tr></table>\n";

function open_all(url) {
  child = window.open(url,"allreminders", "width=430,height=400,resizable=0,scrollbars=1");
}

function checkLeapYear(theyear) {
  if ( ((theyear % 4 == 0) && (theyear % 100 != 0)) || (theyear % 400 == 0) ) {
    return("29");
  } else { return("28"); }
}

function open_window(url) {
  child = window.open(url,"newwindow", "width=350,height=350,resizable=0,scrollbars=1");
}

function alertReminders() {
  var alertit = getCookie(cmon[today.getMonth()] + dt);
  if (alertit != null) {
    alertit = alertit.split("!!");
    textit = "  1. " + alertit[0];
    for (var i = 1; i < alertit.length; i++) {
      textit += "\n  " + (i + 1) + ". " + alertit[i];
    }
    alert(cmon[today.getMonth()] + "�� " + dt + " �馳 " + alertit.length + " �h��ưO��" + "\n\n" + textit);
  }
}

function backup() {
  if (mo > 0) { mo--; }
  else { mo = 11; year--; }
  setCookie('whichmonth',mo);
  setCookie('whichyear', year);
  if (child && !child.closed) { child.close(); }
  document.location="listAllShiftInCalendar.jsp";
}

function stepup() {
  if (mo <  11) { mo++; }
  else { mo = 0; year++; }
  setCookie('whichmonth',mo);
  setCookie('whichyear', year);
  if (child && !child.closed) {	child.close(); }
  document.location="listAllShiftInCalendar.jsp";
}

function getCookie(name) {
  var cname = name + "=";
  var dc = document.cookie;
  if (dc.length > 0) {
    begin = dc.indexOf(cname);
    if (begin != -1) {
      begin += cname.length;
      end = dc.indexOf(";", begin);
      if (end == -1) end = dc.length;
      return unescape(dc.substring(begin, end));
    }
  }
  return null;
}

function setCookie(name, value, expires, path, domain, secure) {
  document.cookie = name + "=" + escape(value) + 
  ((expires == null) ? "" : "; expires=" + expires.toGMTString()) +
  ((path == null) ? "" : "; path=" + path) +
  ((domain == null) ? "" : "; domain=" + domain) +
  ((secure == null) ? "" : "; secure");
}

</SCRIPT>
<P>

<CENTER>
<SCRIPT LANGUAGE="JavaScript">
<!--
document.write(calendar);
if (child && !child.closed) { child.focus(); }
//-->
</SCRIPT>
</CENTER>	 

	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
             
</body> 
</html>    

