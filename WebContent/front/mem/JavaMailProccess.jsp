<%@ page language="java" import="java.util.*,java.io.*" contentType="text/html;charset=UTF-8" pageEncoding="BIG5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.mail.*" %>
<%@ page import="javax.mail.internet.*" %>
<%@ page import="javax.activation.*" %>
<%@ page import="com.mem.model.*"%>

<%!
	InternetAddress[] address = null ;
%>

<%
	MemVO memVO = (MemVO)request.getAttribute("memVO");
	String memName = memVO.getMemname();
	String memPassword = memVO.getMempassword();
	String memEmail = memVO.getMememail();
	String memid = memVO.getMemid();
%>

<%
	String ch_name = memName;
	String passRandom = memPassword;

	//String ch_name = "peter1";   //String ch_name = request.getParameter("ch_name");
	//String passRandom = "111";   //String passRandom = request.getParameter("passRandom");
	
	
	String mailserver   = "140.115.236.9";
	String From         = "test@pchome.com.tw";
	String to           = memEmail;
	//String to         = "ixlogic@pchome.com.tw";  //String to = request.getParameter("email");
	String Subject      = "您的密碼";
 	String messageText  = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom +" (已經啟用)" + "\n" + "\n" + "\n" 
						+ "http://localhost:8081/XA803G2/front/mem/ChangeMemState?memid=" + memid + "\n" + "請連結此網址啟用帳戶";        

 	boolean sessionDebug = false;

try {

  // 設定所要用的Mail 伺服器和所使用的傳送協定
  java.util.Properties props = System.getProperties();
  props.put("mail.host",mailserver);
  props.put("mail.transport.protocol","smtp");
  
  // 產生新的Session 服務
  javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props,null);
  mailSession.setDebug(sessionDebug);
	
  Message msg = new MimeMessage(mailSession);
  
  // 設定傳送郵件的發信人
  msg.setFrom(new InternetAddress(From));
  
  // 設定傳送郵件至收信人的信箱
  address = InternetAddress.parse(to,false);
  msg.setRecipients(Message.RecipientType.TO, address);
  
  // 設定信中的主題 
  msg.setSubject(Subject);
  // 設定送信的時間
  msg.setSentDate(new Date());
  
  // 設定傳送信的MIME Type
  msg.setText(messageText);
  
  // 送信
  Transport.send(msg);

      //response.sendRedirect("emp_select.jsp?msg=Y");
    System.out.println("傳送成功!");
    out.println("<script >document.open('mail_ok.jsp', '' ,'height=110,width=390,left=200,top=120,resizable=no')</script>");	
}
    catch (MessagingException mex) {
      //response.sendRedirect("emp_select.jsp?msg=N");
    System.out.println("傳送失敗!");
    out.println("<script >document.open('mail_error.jsp', '' ,'height=110,width=390,left=200,top=120,resizable=no')</script>");  
      //mex.printStackTrace();
    }
 	
%>