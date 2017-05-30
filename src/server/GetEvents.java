package server;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


import API.EventFindaApi;

import API.OpenWeatherMapApi;
import objects.Event;


@WebServlet("/getEvents")
public class GetEvents extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		
		Date d = new Date() ;
		/*
		 Event e = new Event("hello-w",d,"hello-w.com");
		
		Event e1 = new Event("hello-w1",d,"hello-w1.com");
		Event e2 = new Event("hello-w2",d,"hello-w2.com");
		Event[] es = new Event[3];
		es[0]= e;
		es[1]=e1;
		es[2]=e2;
		String json = new Gson().toJson(es);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		EventFindaApi eventfinda = new EventFindaApi("");
	
		*/
		///*
		try {
			EventFindaApi eventfinda = new EventFindaApi("");
	
			eventfinda.getEventBy(10,"wellington", "");
			ArrayList<Event> es = eventfinda.getEventsArrayList();
			String json = new Gson().toJson(es);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//*/
		
		
	}

}
