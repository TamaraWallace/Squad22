package main;


import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;

public class  Email {

	private static final String senderEmail = "taskillabusiness@gmail.com";
	private static final String senderPassword = "Taskilla123";
	private static final String emailSMTPserver = "smtp.gmail.com";
	private static final String emailServerPort = "465";
	private static String receiverEmail = "taskillabusiness@gmail.com";
	private static String emailSubject = "Test Mail";
	private static String emailBody = ":)";
	private static Authenticator auth;
	
	public Email() {
		
	}
	
	public void sendEmail(String receiverMail,String Subject,String Body){

		if (validateEmaill(receiverMail)) {
			// Receiver Email Address
			receiverEmail=receiverMail; 
			// Subject
			emailSubject=Subject;
			// Body
			emailBody=Body;
			Properties props = new Properties();
			props.put("mail.smtp.user",senderEmail);
			props.put("mail.smtp.host", emailSMTPserver);
			props.put("mail.smtp.port", emailServerPort);
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.socketFactory.port", emailServerPort);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			
			SecurityManager security = System.getSecurityManager();
			
			try{  
				//Authenticator auth = new SMTPAuthenticator();
				auth = new SMTPAuthenticator();
				Session session = Session.getInstance(props);
				MimeMessage msg = new MimeMessage(session);
				msg.setText(emailBody);
				msg.setSubject(emailSubject);
				msg.setFrom(new InternetAddress(senderEmail));
				msg.addRecipient(Message.RecipientType.TO,
						new InternetAddress(receiverEmail));
				Transport.send(msg);
				System.out.println("Message send Successfully:)"); }

			catch (Exception mex){
				mex.printStackTrace();}

		}else {
			System.out.println("Invalid Email; "+receiverMail);
		}
		
	
	
	}
	
	public static boolean validateEmaill(String email){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(email);
        
        if(m.find() && m.group().equals(email)){
            return true;
        }else{
            return false;            
        }
	}
	
	public String getReceiverEmail() {
		return receiverEmail;
	}
	public void setReceiverEmailID(String receiverMail) {
		receiverEmail = receiverMail;
	}
	public static String getEmailSubject() {
		return emailSubject;
	}
	public static void setEmailSubject(String emailSubject) {
		Email.emailSubject = emailSubject;
	}
	public static String getEmailBody() {
		return emailBody;
	}
	public static void setEmailBody(String emailBody) {
		Email.emailBody = emailBody;
	}
	public class SMTPAuthenticator extends javax.mail.Authenticator
	{
		public PasswordAuthentication getPasswordAuthentication()
		{
			return new PasswordAuthentication(senderEmail, senderPassword);
		}
	}
}