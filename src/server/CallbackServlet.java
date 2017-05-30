package server;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/callback")
public class CallbackServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private static final long serialVersionUID = 1657390011452788111L;
	Date d = new Date() ; 

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Here");
        Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
        RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
        String verifier = request.getParameter("oauth_verifier");
        System.out.println("Verifier: "+ verifier);
        try {
            AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
            System.out.println("cb Access Token: "+ accessToken);
            System.out.println("cb Request: "+request.getSession().getAttribute("requestToken"));
            request.getSession().removeAttribute("requestToken");
            // twitter.updateStatus("hello1121 "+d);
            System.out.println("cb After Request: "+request.getSession().getAttribute("requestToken"));
        } catch (TwitterException e) {
            throw new ServletException(e);
        }
        response.sendRedirect(request.getContextPath() + "/");
    }
}