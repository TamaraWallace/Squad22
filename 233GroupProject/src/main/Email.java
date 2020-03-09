package main;



import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class Email {

	private final String senderEmail = "taskillabusiness@gmail.com";
	private final String senderPassword = "Taskilla123";
	private final String emailSMTPserver = "smtp.gmail.com";
	private final String emailServerPort = "465";
	private String receiverEmail = "taskillabusiness@gmail.com";
	private static String emailSubject = "Test Mail";
	private static String emailBody = ":)";
	
	
	public Email(String receiverEmail,String Subject,
			String Body){

		// Receiver Email Address
		this.receiverEmail=receiverEmail; 
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
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
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

		
	
	}
	public String getReceiverEmail() {
		return receiverEmail;
	}
	public void setReceiverEmailID(String receiverEmail) {
		this.receiverEmail = receiverEmail;
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
		@Override
		public PasswordAuthentication getPasswordAuthentication()
		{
			return new PasswordAuthentication(senderEmail, senderPassword);
		}
	}
}