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
<title>�q�ʧ���</title>
<link href="style.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.myDiv {
  margin-top: 150px;
}
</style>
</head>

<body>
<div class="myDiv">
  <div align="center"><h2><b>�q�ʧ���</b></h2></div>
  <table width="100%" border="1" class="table">
    <tr>
      <td colspan="3" bgcolor="#CCCCCC"><strong>����H���</strong></td>
    </tr>
    <tr>
      <td align="center" bgcolor="#FFFF99"><strong>�m�W</strong></td>
      <td align="center" bgcolor="#FFFF99"><strong>�a�}</strong></td>
      <td align="center" bgcolor="#FFFF99"><strong>�q��</strong></td>
    </tr>
    <tr>
      <td align="center"><%=mem.getMemname()%></td>
      <td align="center"><%=mem.getMemadd()%></td>
      <td align="center"><%=mem.getMemtel()%></td>
    </tr>
  </table>
  <ul>
    <li>�иԾ\�U�C�I�ڻ����A��ܥ��@���Ȧ�ζl��ATM�۰ʴ��ھ���b�A��J�U��16�X����b�b���Ϊ��B�A�N�໴�P�����I�ڡI</li>
    <li>������ATM��b�������3�U������C</li>
  </ul>
  <table width="100%" border="1" class="table">
    <tr>
      <td colspan="3" bgcolor="#CCCCCC"><strong>�I�ڤ覡</strong></td>
    </tr>
    <tr>
      <td width="16%" align="center" ><strong>�Ȧ�N�X</strong></td>
      <td width="84%" align="left" >700 (�x�W�l�F)</td>
      
    </tr>
    <tr>
      <td align="center"><strong>��b�b��</strong></td>
      <td align="left">70000415890432512</td>
      
    </tr>
    <tr>
      <td align="center"><strong>��ú���B</strong></td>
      <td align="left"><font color="red"><b><%=amount%></b></font></td>
    </tr>
  </table>
  <ul>
    <li>�ЦC�L�����Φs�ɧ@�������C</li>
  </ul>
</div>
<%session.removeAttribute("shoppingcart"); %>
</body>
</html>
