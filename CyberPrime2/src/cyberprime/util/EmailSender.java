package cyberprime.util;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cyberprime.entities.Clients;


public class EmailSender {
		
	
	final String jdaySend = "jday.sg@gmail.com";
	final String jdayPW = "jdayjday";

	Session session = null;
	Clients c;
	
		public EmailSender(Clients c) {

			this.c = c;
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			 
			session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(jdaySend, jdayPW);
				}
			  });
			
	}
		
		
		public void sendActivationLink(String token){
			
			String subject="Account activation";

			
			try {
	 
				Message message = new MimeMessage(session);

				message.setFrom(new InternetAddress(jdaySend));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(c.getEmail()));
				message.setSubject(subject);
				message.setContent("<h1>Dear client,</h1><p>Please activate your account by clicking " +
						"<a rel='nofollow' target='_blank' href='http://localhost/CyberPrime2/ActivateAccount?token="+token+"&userId="+c.getUserId()+"'>here</a></p>",
                        "text/html" );
				Transport.send(message);
				System.out.print("send");
	 
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			
		}
}

