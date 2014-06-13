<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title></title>
<script language="JavaScript">
	$(document).ready(function(){
		$('#jmenu').jmenu();
});
</script>
<link rel="stylesheet" href="<%= request.getContextPath()%>/css/jmenu.css">
<script language="JavaScript" src="<%= request.getContextPath()%>/js/jmenu.js"></script>

</head>
<body>

<div id="top_bg01">
	<div class="top_member_box"> 
		<table width="160" border="0" cellspacing="0" cellpadding="0">
		<tbody>
		<tr>
			<td width="90" align="left" class="p11_5a8b7c">
			${memVO.memid},�A�n
			</td>
			
			<td width="60" align="left" class="p11_5a8b7c">
			<a href="<%= request.getContextPath()%>/memLogoutHandler.do">�|���n�X</a><br>
			</td>
		</tr>
		<tr>	
			<td width="60" align="left" class="p11_5a8b7c"></td>
			<td width="60" align="left" class="p11_5a8b7c">
			<a href="<%= request.getContextPath()%>/front/select_page.jsp">�|���M��</a><br>
			</td>
		</tr>
		</tbody>
		</table>
	</div>
	<div class="top_logo">
		<a href="<%= request.getContextPath()%>/iPETCares.jsp"><img src="<%= request.getContextPath()%>/images/logobannerG2.png" width="840" height="93" border="0"></a>
	</div>
</div>

<!---Begin--->

<ul id="jmenu">

    <li style="width: 228px;text-align: center;"><a href="#" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image10','','<%= request.getContextPath()%>/images/top_icon01_2.jpg',1)"><img src="<%= request.getContextPath()%>/images/top_icon01-1.jpg" name="Image10" border="0" id="Image10"></a>
		<ul style="top: 133px; left: 456px; display: none;">
			<li><a href="<%= request.getContextPath()%>/front/news/listAllNews.jsp">�Ҧ�����</a></li>
			<li><a href="<%= request.getContextPath()%>/front/news/news.do?action=listNews_Bytype&newstype=2">�̷s���i</a></li>
			<li><a href="<%= request.getContextPath()%>/front/news/news.do?action=listNews_Bytype&newstype=3">�̷s�ӫ~</a></li>
			<li><a href="<%= request.getContextPath()%>/front/news/news.do?action=listNews_Bytype&newstype=1">�̷s����</a></li>
        </ul>	
	</li>
	
<li style="width: 10px;text-align: center;"><div class="top_icon_line"><img src="<%= request.getContextPath()%>/images/top_icon_line.jpg" width="10" height="47"></div></li>
	
	<li style="width: 174px;text-align: center;"><a href="#" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image13','','<%= request.getContextPath()%>/images/top_icon02_2.jpg',1)"><img src="<%= request.getContextPath()%>/images/top_icon02-1.jpg" name="Image13" border="0" id="Image13"></a>
		<ul style="top: 133px; left: 694px; display: none;">
            <li><a href="<%= request.getContextPath()%>/front/dr/hospital.jsp">��|²��</a></li>
			<li><a href="<%= request.getContextPath()%>/front/dr/listAllDr.jsp">��v��T</a></li>
			<li><a href="<%= request.getContextPath()%>/front/shift/listAllShiftInCalendar.jsp">�ڭn����</a></li>
			<li><a href="<%= request.getContextPath()%>/front/apt/showApt.jsp">�����i��</a></li>			
        </ul>
	</li>
	
<li style="width: 10px;text-align: center;"><div class="top_icon_line"><img src="<%= request.getContextPath()%>/images/top_icon_line.jpg" width="10" height="47"></div></li>
	
	<li style="width: 172px;text-align: center;"><a href="#" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image14','','<%= request.getContextPath()%>/images/top_icon03_2.jpg',1)"><img src="<%= request.getContextPath()%>/images/top_icon03-1.jpg" name="Image14" border="0" id="Image14"></a>
		<ul style="top: 133px; left: 878px; display: none;">
            <li><a href="<%= request.getContextPath()%>/frontend/ORDERITEM/market.jsp">�ڭn�ʪ�</a></li>
<!--             <li><a href="#">��g�N����</a></li> -->
<!-- 			<li><a href="#">�p���ȪA</a></li> -->
        </ul>
	</li>
	
<li style="width: 10px;text-align: center;"><div class="top_icon_line"><img src="<%= request.getContextPath()%>/images/top_icon_line.jpg" width="10" height="47"></div></li>
	
	<li style="width: 173px;text-align: center;"><a href="#" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image15','','<%= request.getContextPath()%>/images/top_icon04_2.jpg',1)"><img src="<%= request.getContextPath()%>/images/top_icon04-1.jpg" name="Image15" border="0" id="Image15"></a>
		<ul style="top: 133px; left: 1060px; display: none;">
            <li><a href="<%= request.getContextPath()%>/front/act/listAllAct.jsp">���ʬd��</a></li>
			<li><a href="<%= request.getContextPath()%>/front/act/addAct.jsp">���ʥӽ�</a></li>
<!-- 			<li><a href="#">�p���ȪA</a></li> -->
        </ul>
	</li>
	
<li style="width: 10px;text-align: center;"><div class="top_icon_line"><img src="<%= request.getContextPath()%>/images/top_icon_line.jpg" width="10" height="47"></div></li>
	
	<li style="width: 204px;text-align: center;"><a href="#" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image156','','<%= request.getContextPath()%>/images/top_icon05_2.jpg',1)"><img src="<%= request.getContextPath()%>/images/top_icon05-1.jpg" name="Image156" border="0" id="Image156"></a>
		<ul style="top: 133px; left: 1060px; display: none;">
            <li><a href="<%= request.getContextPath()%>/front/lost/addLost.jsp">�ڭn�Z�n</a></li>
            <li><a href="<%= request.getContextPath()%>/front/lost/listAllLost.jsp">�Ҧ��峹</a></li>
            <li><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=listLost_ByState&loststate=0">����</a></li>
			<li><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=listLost_ByState&loststate=1">�B��</a></li>			
        </ul>
	</li>
	
</ul>
<!---End--->

</body>
</html>