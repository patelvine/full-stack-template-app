package server;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jason Chen <difuchen@gmail.com>
 * 
 * <p> get user data from web page and create an email ready to send to user 
 */
@WebServlet("/mail")
public class mailInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * get user data from web page and create an email ready to send to user 
	 * @param request - request for user input from web page
	 * @param response - response with true if successful stored user else response with false
	 * @throws ServletException, IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
        String subject = request.getParameter("subject");
        String email =  request.getParameter("email");
        String company = request.getParameter("company");
        String message = request.getParameter("message");
        boolean feedback = Boolean.parseBoolean(request.getParameter("feedback"));
        String checker = "false";
        String body = null;
        String toEmail = null;
          
        if(feedback){
        	int phone = Integer.parseInt(request.getParameter("phone"));
        	body = "\nFeedback user: "+name+"\n\nEmail: "+email+"\n\nPhone: "+phone+"\n\nCompany: "+company+"\n\nComments: "+message;
        	toEmail = "maaxhub@gmail.com"; // can be any email id 
        }
        else{
        	subject = "MAX Tech";
        	body = "Thank you for choosing MAX, We will be in touch soon!!!";
        	toEmail =email;
        }
        
        if(subject !=null && body !=null){
        	final String fromEmail = "maaxhub@gmail.com"; //requires valid gmail id
    		final String password = "maxtech999"; // correct password for gmail id
    		
    		
    		System.out.println("SSLEmail Start");
    		Properties props = new Properties();
    		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
    		props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
    		props.put("mail.smtp.socketFactory.class",
    				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
    		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
    		props.put("mail.smtp.port", "465"); //SMTP Port
    		
    		Authenticator auth = new Authenticator() {
    			//override the getPasswordAuthentication method
    			protected PasswordAuthentication getPasswordAuthentication() {
    				return new PasswordAuthentication(fromEmail, password);
    			}
    		};
    		
    		Session session = Session.getDefaultInstance(props, auth);
    		System.out.println("Session created");
    		checker ="true";
    	    EmailUtil.sendEmail(session, toEmail,subject, body);
    	   
	    }
        
        response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(checker);
	}

}
