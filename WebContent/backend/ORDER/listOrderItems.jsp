<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* , com.product.model.*, com.order.model.*" %>
<jsp:useBean id="listOrderItemsByOrdNo" scope="request" type="java.util.Set"/>
<jsp:useBean id="prodItemSvc" scope="page" class="com.productitem.model.ProdItemService"/>

<%@ page import="com.pow.model.*"%>
<%@ page import="com.doctor.model.*"%>
<jsp:useBean id="powSvc" scope="page" class="com.pow.model.PowService" />
<%
	ProductService proSvc = new ProductService();
	List<ProductVO> list = proSvc.getAll();
	pageContext.setAttribute("list", list);
	
	PowVO powVO = powSvc.getOnePowByPKs((Integer)session.getAttribute("empNo"), 4006);
	List<PowVO> listPower = (List<PowVO>)session.getAttribute("list");
 %>
<%
	OrderVO orderVO = (OrderVO) request.getAttribute("orderVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jqueryUI/js/jquery-ui-1.10.4.min.js"></script>
<script src="<%=request.getContextPath()%>/menu.js"></script>	
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/themes/hot-sneaks/jquery-ui.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/js/Timepicker/src/jquery-ui-timepicker-addon.css" rel="stylesheet"></link>
<script src="<%=request.getContextPath()%>/js/Timepicker/src/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/Timepicker/src/jquery-ui-sliderAccess.js" type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>
<%@ include file="/menu1.jsp" %> 
<c:if test="<%=listPower.contains(powVO)%>"> 

<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li>${message}</li>
			</c:forEach>
		</ul>
	</font>
</c:if>
<b><h1>訂單明細(編號:${param.ordno})</h1></b>
<table>
	<tr>
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>數量</th>
		<th>單價</th>
		<th>單筆項目加總</th>
	</tr>
<c:forEach var="proItemVO" items="${listOrderItemsByOrdNo}">
	<tr align='center' valign='middle'> 
		<td>${proItemVO.prono}</td>
		<td>
			<c:forEach	var="productVO" items="${list}">
				<c:if test="${proItemVO.prono == productVO.prono}">
					${productVO.productname}
				</c:if>
			 </c:forEach>
		</td>
		<td>${proItemVO.itemqty}</td>
		<td>${proItemVO.price}</td>
		<td>${proItemVO.price*proItemVO.itemqty}</td>
	</tr>
	
</c:forEach>	
	<tr>
		<td colspan="3"></td>		
		<td>總價:</td>
		<td>
		${amount}
		</td>
	</tr>
</table>
<h2>配送資料</h2>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	<form method="post" action="<%=request.getContextPath()%>/backend/ORDER/order.do">
		<table border='0' cellpadding='5'>
			<tr>
				<th>訂單編號</th>
				<th>成立時間</th>
				<th>配送地址</th>
				<th>買受人電話</th>
				
				
				
			</tr>
			<tr>
				<td><%=orderVO.getOrdno()%><input type="hidden" name="ordno"
					size="45" value="<%=orderVO.getOrdno()%>"></td>
				<td><%=orderVO.getOrdtime()%><input type="hidden"
					name="ordtime" size="45" value="<%=orderVO.getOrdtime()%>"></td>
				<td><%=orderVO.getOrdaddr()%><input type="hidden"
					name="ordaddr" size="45" value="<%=orderVO.getOrdaddr()%>"></td>
				<td><%=orderVO.getOrdtel()%><input type="hidden" name="ordtel"
					size="45" value="<%=orderVO.getOrdtel()%>"></td>
				
				
		</table>
		<h2>訂單狀態</h2>
		<table border='0' cellpadding='5'>
			<tr>
				<th>發貨時間</th>
				<th>到貨時間</th>
				<th>銷單時間</th>
				<th>訂單狀態</th>
				<th>買方帳號</th>				
			</tr>
			<tr>
			<td>
				<input type="text" name="ordgotime" size="25"
					value="<%=(orderVO.getOrdgotime()!=null)?orderVO.getOrdgotime():""%>" id="ordgotime">
						 <script language="JavaScript">
    						$(document).ready(function(){ 
      							var opt_ordgotime={
      								//以下為日期選擇器部分
      								   dayNames:["星期日","星期一","星期二","星期三","星期四","星期五","星期六"],
      								   dayNamesMin:["日","一","二","三","四","五","六"],
      								   monthNames:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
      								   monthNamesShort:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
      								   prevText:"上月",
      								   nextText:"次月",
      								   weekHeader:"週",
      								   showMonthAfterYear:true,
      								   dateFormat:"yy-mm-dd",
      								   //以下為時間選擇器部分
      								   timeOnlyTitle:"選擇時分秒",
      								   timeText:"時間",
      								   hourText:"時",
      								   minuteText:"分",
      								   secondText:"秒",
      								   millisecText:"毫秒",
      								   timezoneText:"時區",
      								   currentText:"現在時間",
      								   closeText:"確定",
      								   amNames:["上午","AM","A"],
      								   pmNames:["下午","PM","P"],
      								   timeFormat:"HH:mm:ss",
               						   controlType:"select" 
              						};
      							$('#ordgotime').datetimepicker(opt_ordgotime);
      						});
  						</script>
					</td>
				<td><input type="text" name="ordarrtime" size="25"
					value="<%=(orderVO.getOrdarrtime()!=null?orderVO.getOrdarrtime():"未發貨此欄位不得存取")%>" ${(orderVO.ordgotime==null)?'disabled':''} id="ordarrtime">					
						<script language="JavaScript">
    						$(document).ready(function(){ 
      							var opt_ordarrtime={
      								//以下為日期選擇器部分
      								   dayNames:["星期日","星期一","星期二","星期三","星期四","星期五","星期六"],
      								   dayNamesMin:["日","一","二","三","四","五","六"],
      								   monthNames:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
      								   monthNamesShort:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
      								   prevText:"上月",
      								   nextText:"次月",
      								   weekHeader:"週",
      								   showMonthAfterYear:true,
      								   dateFormat:"yy-mm-dd",
      								   //以下為時間選擇器部分
      								   timeOnlyTitle:"選擇時分秒",
      								   timeText:"時間",
      								   hourText:"時",
      								   minuteText:"分",
      								   secondText:"秒",
      								   millisecText:"毫秒",
      								   timezoneText:"時區",
      								   currentText:"現在時間",
      								   closeText:"確定",
      								   amNames:["上午","AM","A"],
      								   pmNames:["下午","PM","P"],
      								   timeFormat:"HH:mm:ss",
               						   controlType:"select" 
              						};
      							$('#ordarrtime').datetimepicker(opt_ordarrtime);
      						});
  						</script>
  						<input type="hidden" name="ordarrtime" value="" ${(orderVO.ordgotime==null)?'readonly':'disabled'}>
					</td>
				<td><input type="text" name="orddeltime" size="25"
					value="<%=(orderVO.getOrddeltime()!=null?orderVO.getOrddeltime():"未送達此欄位不得存取")%>" ${(orderVO.ordarrtime==null)?'disabled':''} id="orddeltime">					
					<script language="JavaScript">
    						$(document).ready(function(){ 
      							var opt_orddeltime={
      								//以下為日期選擇器部分
      								   dayNames:["星期日","星期一","星期二","星期三","星期四","星期五","星期六"],
      								   dayNamesMin:["日","一","二","三","四","五","六"],
      								   monthNames:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
      								   monthNamesShort:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
      								   prevText:"上月",
      								   nextText:"次月",
      								   weekHeader:"週",
      								   showMonthAfterYear:true,
      								   dateFormat:"yy-mm-dd",
      								   //以下為時間選擇器部分
      								   timeOnlyTitle:"選擇時分秒",
      								   timeText:"時間",
      								   hourText:"時",
      								   minuteText:"分",
      								   secondText:"秒",
      								   millisecText:"毫秒",
      								   timezoneText:"時區",
      								   currentText:"現在時間",
      								   closeText:"確定",
      								   amNames:["上午","AM","A"],
      								   pmNames:["下午","PM","P"],
      								   timeFormat:"HH:mm:ss",
               						   controlType:"select" 
              						};
      							$('#orddeltime').datetimepicker(opt_orddeltime);
      						});
  						</script>
					<input type="hidden" name="orddeltime" value="" ${(orderVO.ordarrtime==null)?'readonly':'disabled'}>
					</td>
				<td><input type="radio" name="ordstate" value="0" ${(orderVO.ordgotime==null)?'checked':''}>未出貨
					<input type="radio" name="ordstate" value="1" ${(orderVO.ordgotime!=null)?'checked':''}>已出貨</td>
				<td><%=orderVO.getMemno()%><input type="hidden" name="memno"
					size="25" value="<%=orderVO.getMemno()%>"></td>
				
			</tr>
			<tr>
				<td colspan="9" align="right">
					<input type="hidden" name="action" value="update"> <input
			type="hidden" name="ordno" value="<%=orderVO.getOrdno()%>"> <input
			type="submit" value="更新訂單資料">
				</td>

			</tr>
		</table>
	</form>
	</c:if>
	<%@ include file="/menu2.jsp" %> 
	
</body>
</html>