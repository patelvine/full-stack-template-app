package server;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@WebServlet("/signin")
public class SigninServlet extends HttpServlet {
    private static final long serialVersionUID = -6205814293093350242L;
    File file = new File("twitter4j.properties");
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // Twitter twitter = new TwitterFactory().getInstance();
    	if(file.exists()){
    		System.out.println("Trueeeeee");
    	}else {
    		System.out.println("Fallseee");
    	}
       
    	ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("oFfQL0Zq9WXLk4rdh3DaHjMDb")
                .setOAuthConsumerSecret("1apEQnk80e0TWBENbHWzr4I20gTBMhUux23n0J0qO3j0WZrde9");
        //Do this because the twitter4j.properties does not work properly in web 
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
       Date d = new Date () ;  
       
        request.getSession().setAttribute("twitter", twitter);
        try {
            StringBuffer callbackURL = request.getRequestURL();
            int index = callbackURL.lastIndexOf("/");
            callbackURL.replace(index, callbackURL.length(), "").append("/callback");
            System.out.println("cb "+ callbackURL.toString());
            /////////////////////////////////////////////////////////////////////////////
            //HAVE TO COPY THE CALLBACK URL AND PASTE IN DEV APP'S SETTING CALLBACK URL//
            /////////////////////////////////////////////////////////////////////////////
            RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
            request.getSession().setAttribute("requestToken", requestToken);
            System.out.print("siginin requestToken"+requestToken);
           // 
            response.sendRedirect(requestToken.getAuthenticationURL());
           // twitter.updateStatus("hello112 :"+d);
        } catch (TwitterException e) {
            throw new ServletException(e);
        }

    }
}