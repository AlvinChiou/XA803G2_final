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
			// �]�w�ҭn�Ϊ�Mail ���A���M�ҨϥΪ��ǰe��w
			java.util.Properties props = System.getProperties();
			props.put("mail.host", mailserver);
			props.put("mail.transport.protocol", "smtp");

			// ���ͷs��Session �A��
			javax.mail.Session mailSession = javax.mail.Session
					.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);

			Message msg = new MimeMessage(mailSession);

			// �]�w�ǰe�l�󪺵o�H�H
			msg.setFrom(new InternetAddress(From));

			// �]�w�ǰe�l��ܦ��H�H���H�c
			address = InternetAddress.parse(to, false);
			msg.setRecipients(Message.RecipientType.TO, address);

			// �]�w�H�����D�D
			msg.setSubject(Subject);
			// �]�w�e�H���ɶ�
			msg.setSentDate(new Date());

			// �]�w�ǰe�H��MIME Type
			msg.setText(messageText);

			// �e�H
			Transport.send(msg);
													
		} catch (MessagingException mex) {

			System.out.println("�ǰe����!");
		}
	}
}
