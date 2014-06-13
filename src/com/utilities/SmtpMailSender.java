package com.utilities;

import java.util.Date;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class SmtpMailSender {
	private String ch_name;
	private String mailserver;
	private String From;
	//private String password;
	private String to;
	private String Subject;
	private String messageText;
	
	public void Sendmail(String ch_name,  String mailserver, String From, String to, String Subject, String messageText){
		this.ch_name = ch_name;
		this.mailserver = mailserver;
		this.From = From;
		this.to = to;
		this.Subject = Subject;
		this.messageText = messageText;
		boolean sessionDebug = false;
		InternetAddress[] address = null;
		try {
			// 設定所要用的Mail 伺服器和所使用的傳送協定
			java.util.Properties props = System.getProperties();
			props.put("mail.host", mailserver);
			props.put("mail.transport.protocol", "smtp");

			// 產生新的Session 服務
			javax.mail.Session mailSession = javax.mail.Session
					.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);

			Message msg = new MimeMessage(mailSession);

			// 設定傳送郵件的發信人
			msg.setFrom(new InternetAddress(From));

			// 設定傳送郵件至收信人的信箱
			address = InternetAddress.parse(to, false);
			msg.setRecipients(Message.RecipientType.TO, address);

			// 設定信中的主題
			msg.setSubject(Subject);
			// 設定送信的時間
			msg.setSentDate(new Date());

			// 設定傳送信的MIME Type
			msg.setText(messageText);

			// 送信
			Transport.send(msg);
													
		} catch (MessagingException mex) {

			System.out.println("傳送失敗!");
		}
	}
}
