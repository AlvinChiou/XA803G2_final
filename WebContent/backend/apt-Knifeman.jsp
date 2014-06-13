<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>

<%@ page import="com.pow.model.*"%>

<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmployeeService" />
<jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" />

<jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService" />
<%
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4002);
	List<PowVO> list = (List<PowVO>)session.getAttribute("list");
 %>
<html>
<head>
<!-- <title>IBM Emp: Home</title> -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">


<!-- 自己新增的 jquery DatePicker -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css"/> --%>
<%-- <script src="<%=request.getContextPath()%>/menu.js"></script>		 --%>

<script>
		$(function() {
		
			$("#datepicker5").datepicker({
				showOn : "button",
				buttonImage : "<%=request.getContextPath()%>/backend/apt/images/calendar.gif",
				buttonImageOnly : true,
				minDate: 0,
				maxDate: "+19D",
				dateFormat: "yy-mm-dd"
			});
		});
		
		

		$(function(){
			$('#menu').children('li').click(function(){
				$('#menu').find('ul').removeClass('active');
				$(this).find('ul').toggleClass('active');	
			});
		});

		
</script>
</head>
<body bgcolor='white'>

<%@ include file="/menu1.jsp" %> 
    
<!--     <table border='1' cellpadding='5' cellspacing='0' width='400'> -->
<!--   		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'> -->
<!--   		  <td><h3>iPETCares Apt: Home</h3><font color=red>( MVC )</font></td> -->
<!--  		</tr> -->
<!-- 	</table> -->
    
<!-- jsp主要開始 -->
<!-- <table border='1' cellpadding='5' cellspacing='0' width='400'> -->
<!--   <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'> -->
<!--     <td><h3>IBM Emp: Home</h3><font color=red>( MVC )</font></td> -->
<!--   </tr> -->
<!-- </table> -->

<!-- <p>This is the Home page for IBM Emp: Home</p> -->



<h3>掛號管理</h3>
<c:if test="<%=list.contains(powVO)%>">  
<ul>
<!--   <li><a href='apt/addApt.jsp'>Add</a> a new Appointment.</li> -->
<!--   <li><a href='apt/listAllApt.jsp'>Manage</a> appointments.</li> -->
  <li><a href='apt/showApt.jsp'>顯示出未來20天的掛號狀況</a></li>
</ul>
</c:if>


<br/>
<br/>

<c:if test="<%=list.contains(powVO)%>"> 
<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
<br/>
<ul>  
  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/apt/apt.do" name="form1">
        <b><font color=blue>萬用複合查詢:</font></b> <br>
        <b>輸入掛號編號:</b>
        <input type="text" name="aptNo" value="7001"><br>
           		
           <%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
           
       <b>選擇掛號日期:</b>
		   <td>
				<input type="text"  id="datepicker5"  name = "aptDate" />
		 </td>
       <br/><b>輸入掛號時段</b>
			<select name = "aptPeriod">
				<option value = "">請選擇欲掛號時段</option>
				<option value = "0910">9:00~10:00</option>
				<option value = "1011">10:00~11:00</option>
				<option value = "1112">11:00~12:00</option>
				<option value = "1314">13:00~14:00</option>
				<option value = "1415">14:00~15:00</option>
				<option value = "1516">15:00~16:00</option>
				<option value = "1617">16:00~17:00</option>
			</select>
		
  
    
        <br/><b>選擇寵物名:</b>
       <select size="1" name="petNo" >
          <option value="">
         <c:forEach var="petVO" items="${petSvc.all}" > 
          <option value="${petVO.petNo}">${petVO.petName}
         </c:forEach>   
       </select><br>
           
       
		        
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listApts_ByCompositeQuery">
     </FORM>
  </li>
</ul>

</c:if>

</body>

<!-- jsp主要結束-->
    
    
    
    
    
    
  <%@ include file="/menu2.jsp" %> 

</html>
