package server;

import java.util.Date;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Jason Chen <difuchen@gmail.com>
 * 
 * <p>method to send email to users by getting the following parameters
 * @param session - session
 * @param toEmail - email you want to send to 
 * @param subject - email subject
 * @param body - email body
 */
public class EmailUtil {
	/**
	 * Utility method to send simple HTML email
	 * @param session - session
	 * @param toEmail - email you want to send to 
	 * @param subject - email subject
	 * @param body - email body
	 */
	public static void sendEmail(Session session, String toEmail, String subject, String body){
		try
	    {
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress("maaxhub@gmail.com", "NoReply-JD"));
	      msg.setReplyTo(InternetAddress.parse("maaxhub@gmail.com", false));
	      msg.setSubject(subject, "UTF-8");
	      msg.setText(body, "UTF-8");
	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      System.out.println("Message is ready");
    	  Transport.send(msg);  

	      System.out.println("Email Sent Successfully!!");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	}

}
