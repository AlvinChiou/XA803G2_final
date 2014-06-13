<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-tw">
<meta http-equiv="Content-Type" content="text/html; charset=big5">

<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script> --%>
<%-- <script src="<%=request.getContextPath()%>/menu.js"></script>		 --%>
</HEAD>

<BODY bgcolor=#dbeaf5 vLink=#0033cc background="images/wall_4.gif">
<%-- <%@ include file="/menu1.jsp" %>  --%>
	<a href = "<%=request.getContextPath()%>/backend/shift/listAllShiftInCalendar.jsp">重新整理</a>
<!-- 	<table border=0 cellpadding=0 cellspacing=0 width="80%" align="center">   -->
<!-- 	  <tr> -->
<!-- 	    <td>   -->
<!--          <font color=blue>●</font><font><b>歡迎進入: 員工行事曆系統【有 <img src="images/foot.gif" border="0" height="22">記號者 】 , <font color=blue>休假</font>系統</b></font>              -->
<!--        </td>                -->
<!-- 	  </tr>                -->
<!--     </table>  -->

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
var days = new Array("日", "一", "二", "三", "四", "五", "六")
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
calendar =  "<table bgcolor='#666666' width='80%' border=1 bordercolor=blue cellpadding=1 cellspacing=0>\n";

calendar += "<tr align='center'><td>\n";
calendar += "<table width='100%' border='0' cellpadding='3' cellspacing='0'>\n";
calendar += "<th bgcolor='#000000'><a href='javascript:backup();'><font color='#ffffff'><<上個月</font></a> </th>\n";
calendar += "<th bgcolor='navy'>   <a href='javascript:open_all(\"noteListMonth.jsp\");'><font color='#ffffff'>" + year + "-" + cmon[mo] + "月所有記事</font></a></th>\n";
calendar += "<th bgcolor='#000000'><a href='javascript:stepup();'><font color='#ffffff'>下個月>></font></a> </th>\n";
calendar += "</table></td></tr>\n";

calendar += "<tr align='center'><td>\n";
calendar += "<table width='100%' border='0' cellpadding='3' cellspacing='0' bgcolor='#eeeeee'>\n";
for (i = 0; i < 7; i++) {
 if (i==0) 
  calendar += "<th width='14%'><font size=2 color='#ff0000'>週" + days[i] + "</font></th>\n";
 else 
  calendar += "<th width='14%'><font size=2 color='#000000'>週" + days[i] + "</font></th>\n";
}
calendar += "</table></td></tr>\n";

calendar += "<tr align='center'><td>\n";
calendar += "<table width='100%' border='0' cellpadding='2' cellspacing='1'>\n";
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
    calendar += " bgcolor='#eeeeee'>"+"<b>";
    calendar += "<iframe src=\"<%=request.getContextPath()%>/backend/shift/JSTL01_query4_Index_forEach_param2.jsp?shiftDate=" + year + "-" + mo2 + "-" + count2 +  "\" width=100% height=50 scrolling=no frameborder=1 ></iframe>";
    calendar += "<a href='javascript:open_window(\"<%=request.getContextPath()%>/backend/shift/JSTL01_query4_Index_forEach_param2.jsp?shiftDate=" + year + "-" + mo2 + "-" + count2 + "\");'><font color='#ff0000'>" + count + "</font>";  
    //原來行事曆的連結仍然可用
    //calendar += "<a href='javascript:open_window(\"noteList.jsp?" + mo + "&" + count + "&" + year + "\");'><font color='#ff0000'>" + count + "</font>"; 
    if (currentmonth == mo && currentyear == year && document.cookie) {
      var isremind = getCookie(cmon[mo] + count);
      if (isremind) { calendar += "<img src='<%=request.getContextPath()%>/backend/shift/images/foot.gif' border=0 height='22'>"; }
    }
    calendar += "<br/><a href = '<%=request.getContextPath()%>/backend/shift/addShift.jsp?shiftDate="+ year + '-' + mo2 + '-' + count2 + " '>新增</a>";

  }else {
    calendar += " bgcolor='#eeeeee'>";    
      calendar += "<iframe src=\"<%=request.getContextPath()%>/backend/shift/JSTL01_query4_Index_forEach_param2.jsp?shiftDate=" + year + "-" + mo2 + "-" + count2 +  "\" width=100% height=50 scrolling=no frameborder=1 ></iframe>";
      calendar += "<a href='javascript:open_window(\"<%=request.getContextPath()%>/backend/shift/JSTL01_query4_Index_forEach_param2.jsp?shiftDate=" + year + "-" + mo2 + "-" + count2 + "\");'><font color='#000000'>" + count + "</font>"; 
    //原來行事曆的連結仍然可用
      //calendar += "<a href='javascript:open_window(\"noteList.jsp?" + mo + "&" + count + "&" + year +  "\");'><font color='#000000'>" + count + "</font>";
    //if (currentmonth == mo && currentyear == year && document.cookie) {
    if (currentyear == year && document.cookie) {
      var isremind = getCookie(cmon[mo] + count);
      if (isremind) { calendar += "<img src='<%=request.getContextPath()%>/backend/shift/images/foot.gif' border=0 height='22'>"; }
    }
    calendar += "<br/><a href = '<%=request.getContextPath()%>/backend/shift/addShift.jsp?shiftDate="+ year + '-' + mo2 + '-' + count2 + " '>新增</a>";
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
    alert(cmon[today.getMonth()] + "月 " + dt + " 日有 " + alertit.length + " 則行事記錄" + "\n\n" + textit);
  }
}

function backup() {
  if (mo > 0) { mo--; }
  else { mo = 11; year--; }
  setCookie('whichmonth',mo);
  setCookie('whichyear', year);
  if (child && !child.closed) { child.close(); }
  document.location="<%=request.getContextPath()%>/backend/shift/listAllShiftInCalendar.jsp";
}

function stepup() {
  if (mo <  11) { mo++; }
  else { mo = 0; year++; }
  setCookie('whichmonth',mo);
  setCookie('whichyear', year);
  if (child && !child.closed) {	child.close(); }
  document.location="<%=request.getContextPath()%>/backend/shift/listAllShiftInCalendar.jsp";
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

<%-- <%@ include file="/menu2.jsp" %>  --%>
             
</body> 
</html>    

