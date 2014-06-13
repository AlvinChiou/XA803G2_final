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
	<font color='red'>�Эץ��H�U���~:
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li>${message}</li>
			</c:forEach>
		</ul>
	</font>
</c:if>
<b><h1>�q�����(�s��:${param.ordno})</h1></b>
<table>
	<tr>
		<th>�ӫ~�s��</th>
		<th>�ӫ~�W��</th>
		<th>�ƶq</th>
		<th>���</th>
		<th>�浧���إ[�`</th>
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
		<td>�`��:</td>
		<td>
		${amount}
		</td>
	</tr>
</table>
<h2>�t�e���</h2>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>�Эץ��H�U���~
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
				<th>�q��s��</th>
				<th>���߮ɶ�</th>
				<th>�t�e�a�}</th>
				<th>�R���H�q��</th>
				
				
				
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
		<h2>�q�檬�A</h2>
		<table border='0' cellpadding='5'>
			<tr>
				<th>�o�f�ɶ�</th>
				<th>��f�ɶ�</th>
				<th>�P��ɶ�</th>
				<th>�q�檬�A</th>
				<th>�R��b��</th>				
			</tr>
			<tr>
			<td>
				<input type="text" name="ordgotime" size="25"
					value="<%=(orderVO.getOrdgotime()!=null)?orderVO.getOrdgotime():""%>" id="ordgotime">
						 <script language="JavaScript">
    						$(document).ready(function(){ 
      							var opt_ordgotime={
      								//�H�U�������ܾ�����
      								   dayNames:["�P����","�P���@","�P���G","�P���T","�P���|","�P����","�P����"],
      								   dayNamesMin:["��","�@","�G","�T","�|","��","��"],
      								   monthNames:["�@��","�G��","�T��","�|��","����","����","�C��","�K��","�E��","�Q��","�Q�@��","�Q�G��"],
      								   monthNamesShort:["�@��","�G��","�T��","�|��","����","����","�C��","�K��","�E��","�Q��","�Q�@��","�Q�G��"],
      								   prevText:"�W��",
      								   nextText:"����",
      								   weekHeader:"�g",
      								   showMonthAfterYear:true,
      								   dateFormat:"yy-mm-dd",
      								   //�H�U���ɶ���ܾ�����
      								   timeOnlyTitle:"��ܮɤ���",
      								   timeText:"�ɶ�",
      								   hourText:"��",
      								   minuteText:"��",
      								   secondText:"��",
      								   millisecText:"�@��",
      								   timezoneText:"�ɰ�",
      								   currentText:"�{�b�ɶ�",
      								   closeText:"�T�w",
      								   amNames:["�W��","AM","A"],
      								   pmNames:["�U��","PM","P"],
      								   timeFormat:"HH:mm:ss",
               						   controlType:"select" 
              						};
      							$('#ordgotime').datetimepicker(opt_ordgotime);
      						});
  						</script>
					</td>
				<td><input type="text" name="ordarrtime" size="25"
					value="<%=(orderVO.getOrdarrtime()!=null?orderVO.getOrdarrtime():"���o�f����줣�o�s��")%>" ${(orderVO.ordgotime==null)?'disabled':''} id="ordarrtime">					
						<script language="JavaScript">
    						$(document).ready(function(){ 
      							var opt_ordarrtime={
      								//�H�U�������ܾ�����
      								   dayNames:["�P����","�P���@","�P���G","�P���T","�P���|","�P����","�P����"],
      								   dayNamesMin:["��","�@","�G","�T","�|","��","��"],
      								   monthNames:["�@��","�G��","�T��","�|��","����","����","�C��","�K��","�E��","�Q��","�Q�@��","�Q�G��"],
      								   monthNamesShort:["�@��","�G��","�T��","�|��","����","����","�C��","�K��","�E��","�Q��","�Q�@��","�Q�G��"],
      								   prevText:"�W��",
      								   nextText:"����",
      								   weekHeader:"�g",
      								   showMonthAfterYear:true,
      								   dateFormat:"yy-mm-dd",
      								   //�H�U���ɶ���ܾ�����
      								   timeOnlyTitle:"��ܮɤ���",
      								   timeText:"�ɶ�",
      								   hourText:"��",
      								   minuteText:"��",
      								   secondText:"��",
      								   millisecText:"�@��",
      								   timezoneText:"�ɰ�",
      								   currentText:"�{�b�ɶ�",
      								   closeText:"�T�w",
      								   amNames:["�W��","AM","A"],
      								   pmNames:["�U��","PM","P"],
      								   timeFormat:"HH:mm:ss",
               						   controlType:"select" 
              						};
      							$('#ordarrtime').datetimepicker(opt_ordarrtime);
      						});
  						</script>
  						<input type="hidden" name="ordarrtime" value="" ${(orderVO.ordgotime==null)?'readonly':'disabled'}>
					</td>
				<td><input type="text" name="orddeltime" size="25"
					value="<%=(orderVO.getOrddeltime()!=null?orderVO.getOrddeltime():"���e�F����줣�o�s��")%>" ${(orderVO.ordarrtime==null)?'disabled':''} id="orddeltime">					
					<script language="JavaScript">
    						$(document).ready(function(){ 
      							var opt_orddeltime={
      								//�H�U�������ܾ�����
      								   dayNames:["�P����","�P���@","�P���G","�P���T","�P���|","�P����","�P����"],
      								   dayNamesMin:["��","�@","�G","�T","�|","��","��"],
      								   monthNames:["�@��","�G��","�T��","�|��","����","����","�C��","�K��","�E��","�Q��","�Q�@��","�Q�G��"],
      								   monthNamesShort:["�@��","�G��","�T��","�|��","����","����","�C��","�K��","�E��","�Q��","�Q�@��","�Q�G��"],
      								   prevText:"�W��",
      								   nextText:"����",
      								   weekHeader:"�g",
      								   showMonthAfterYear:true,
      								   dateFormat:"yy-mm-dd",
      								   //�H�U���ɶ���ܾ�����
      								   timeOnlyTitle:"��ܮɤ���",
      								   timeText:"�ɶ�",
      								   hourText:"��",
      								   minuteText:"��",
      								   secondText:"��",
      								   millisecText:"�@��",
      								   timezoneText:"�ɰ�",
      								   currentText:"�{�b�ɶ�",
      								   closeText:"�T�w",
      								   amNames:["�W��","AM","A"],
      								   pmNames:["�U��","PM","P"],
      								   timeFormat:"HH:mm:ss",
               						   controlType:"select" 
              						};
      							$('#orddeltime').datetimepicker(opt_orddeltime);
      						});
  						</script>
					<input type="hidden" name="orddeltime" value="" ${(orderVO.ordarrtime==null)?'readonly':'disabled'}>
					</td>
				<td><input type="radio" name="ordstate" value="0" ${(orderVO.ordgotime==null)?'checked':''}>���X�f
					<input type="radio" name="ordstate" value="1" ${(orderVO.ordgotime!=null)?'checked':''}>�w�X�f</td>
				<td><%=orderVO.getMemno()%><input type="hidden" name="memno"
					size="25" value="<%=orderVO.getMemno()%>"></td>
				
			</tr>
			<tr>
				<td colspan="9" align="right">
					<input type="hidden" name="action" value="update"> <input
			type="hidden" name="ordno" value="<%=orderVO.getOrdno()%>"> <input
			type="submit" value="��s�q����">
				</td>

			</tr>
		</table>
	</form>
	</c:if>
	<%@ include file="/menu2.jsp" %> 
	
</body>
</html>