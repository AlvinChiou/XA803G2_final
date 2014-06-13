<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@page import="com.mem.model.MemVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* , com.productitem.model.ProdItemVO, com.product.model.*" %>
<%	
	MemVO mem =(MemVO)session.getAttribute("memVO");
	String amount = (String) session.getAttribute("amount");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>訂購完成</title>
<link href="style.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.myDiv {
  margin-top: 150px;
}
</style>
</head>

<body>
<div class="myDiv">
  <div align="center"><h2><b>訂購完成</b></h2></div>
  <table width="100%" border="1" class="table">
    <tr>
      <td colspan="3" bgcolor="#CCCCCC"><strong>收件人資料</strong></td>
    </tr>
    <tr>
      <td align="center" bgcolor="#FFFF99"><strong>姓名</strong></td>
      <td align="center" bgcolor="#FFFF99"><strong>地址</strong></td>
      <td align="center" bgcolor="#FFFF99"><strong>電話</strong></td>
    </tr>
    <tr>
      <td align="center"><%=mem.getMemname()%></td>
      <td align="center"><%=mem.getMemadd()%></td>
      <td align="center"><%=mem.getMemtel()%></td>
    </tr>
  </table>
  <ul>
    <li>請詳閱下列付款說明，選擇任一部銀行或郵局ATM自動提款機轉帳，輸入下方16碼的轉帳帳號及金額，就能輕鬆完成付款！</li>
    <li>本網站ATM轉帳不受單日3萬元限制。</li>
  </ul>
  <table width="100%" border="1" class="table">
    <tr>
      <td colspan="3" bgcolor="#CCCCCC"><strong>付款方式</strong></td>
    </tr>
    <tr>
      <td width="16%" align="center" ><strong>銀行代碼</strong></td>
      <td width="84%" align="left" >700 (台灣郵政)</td>
      
    </tr>
    <tr>
      <td align="center"><strong>轉帳帳號</strong></td>
      <td align="left">70000415890432512</td>
      
    </tr>
    <tr>
      <td align="center"><strong>應繳金額</strong></td>
      <td align="left"><font color="red"><b><%=amount%></b></font></td>
    </tr>
  </table>
  <ul>
    <li>請列印此頁或存檔作為紀錄。</li>
  </ul>
</div>
<%session.removeAttribute("shoppingcart"); %>
</body>
</html>
