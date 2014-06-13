package com.utilities;
public class GmailSenderMain {
    public static void main(String[] args) throws ClassNotFoundException{
        String fromMail = "tokyo110/@gmail.com";
        String toMail = "hawk1108@gmail.com";
        String password = "J250Su6B";
        String subject = "´ú¸Õ¶l¥ó";
        String content = "https://java.net/projects/javamail/pages/Home";
        GmailSender gmailSender = new GmailSender();
        gmailSender.GmailAuth(fromMail,toMail, password, subject, content);
    }
}
