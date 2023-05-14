package function;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class App {
	
//	protected static String message;
	
	protected static String subject = "Password : Library";
	protected static String from = "mishrapwwan123@gmail.com";
  	  
	  
	//this is responsible to send email..
	public static void sendEmail(String message, String subject, String to, String from) {
		
		//Variable for mail
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES "+properties);
		
	
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		
		//Step 1: to get the session object..
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {				
				return new PasswordAuthentication("mishrapwwan123@gmail.com", "hwbpkstddvubugud");
			}
			
			
			
		});
		
		session.setDebug(true);
		
		//Step 2 : compose the message [text, multimedia]
		MimeMessage m = new MimeMessage(session);
		
		try {
		
		//from email
		m.setFrom(from);
		
		//adding recipient to message
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		//adding subject to message
		m.setSubject(subject);
	
		
		//adding text to message
		m.setText(message);
		
		//send 
		
		//Step 3 : send the message using Transport class
		Transport.send(m);
		
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
			
	}


	public static String getFrom() {
		return from;
	}


	public static void setFrom(String from) {
		App.from = from;
	}


	public static String getSubject() {
		return subject;
	}


	public static void setSubject(String subject) {
		App.subject = subject;
	}

}