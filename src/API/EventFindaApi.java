package API;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import objects.Event;

public class EventFindaApi {
private ArrayList<Event> events = new ArrayList<Event>() ;
private String url ;
	public EventFindaApi(String url){
		this.url = "dsf";
	}
	
	/**
	 * read the response from api
	 * @param br : BufferedReader of the response from eventfinda api
	 * @throws Exception
	 */
	public void readResponse(BufferedReader br) throws Exception{
		 String line = "";
        String xml_string = "";
      
        while ((line = br.readLine()) != null) {
          xml_string += line;
        } 
  
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        DocumentBuilder b = f.newDocumentBuilder();
        Document doc = b.parse(new ByteArrayInputStream(xml_string.getBytes("UTF-8")));
        NodeList events = doc.getElementsByTagName("event");
        for (int i = 0; i < events.getLength(); i++) {
     	 System.out.println("<Event>");
          Element event = (Element) events.item(i);
          Node title = event.getElementsByTagName("name").item(0);
          System.out.println("\t Name: "+title.getTextContent());
          Element url = (Element) event.getElementsByTagName("url").item(0);
          System.out.println("\t Url: "+url.getTextContent());
          Element session = (Element)event.getElementsByTagName("session").item(0);
         // System.out.println("\t"+session.getTextContent());
          Element timeStart = (Element)session.getElementsByTagName("datetime_start").item(0);
          System.out.println("\t at:"+timeStart.getTextContent());
          Date d = new Date();
          Event e = new Event("city",title.getTextContent(), d, url.getTextContent());
          this.events.add(e);
          //Element all_img = (Element)event.getElementsByTagName("images").item(0);
          //NodeList images = all_img.getElementsByTagName("image");
    /*      for (int j = 0; j < images.getLength(); j++) {
            Element img = (Element) images.item(j);
            Node id = img.getElementsByTagName("id").item(0);
            System.out.println(id.getTextContent());
          
            NodeList trans = img.getElementsByTagName("transform");
            for (int k = 0; k < trans.getLength(); k++) {
              Element tran = (Element) trans.item(k);
              Node url = tran.getElementsByTagName("url").item(0);
              System.out.println(url.getTextContent());
            }
          }*/
          System.out.println("</Event>");
        }
	}
	
	/**
	 * This method will first setup the connection to eventfinda api then will
	 * do the get request to get the events specify by the url and call on readResponse()
	 * to read the response from the eventfinda api
	 * @throws Exception
	 * WILL FIX THE DEPRECATED CLASS AND METHOD AFTER
	 */
	public void findEvents() throws Exception  {

		
       HttpHost targetHost = new HttpHost("api.eventfinda.co.nz", 80, "http");
     //  DefaultHttpClient httpclient = new DefaultHttpClient();
       //HttpClient httpClient = HttpClientBuilder.create().build();
     
       //try {
       /*
         httpclient.getCredentialsProvider().setCredentials(
           new AuthScope(targetHost.getHostName(), targetHost.getPort()),
           new UsernamePasswordCredentials("maaxhub", "m42pfpqc4v4h")
         );
*/
       CloseableHttpClient httpClient1  ;
         CredentialsProvider provider = new BasicCredentialsProvider();
         UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("maaxhub", "m42pfpqc4v4h");	
         provider.setCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort()), credentials);
         CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
         
         AuthCache authCache = new BasicAuthCache();
         BasicScheme basicAuth = new BasicScheme();
         authCache.put(targetHost, basicAuth);

         BasicHttpContext localcontext = new BasicHttpContext();
         localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);
       
         HttpGet httpget = new HttpGet(url);
         HttpResponse response = httpClient.execute(targetHost, httpget, localcontext);
         HttpEntity entity = response.getEntity();
       
         BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
         readResponse(br);
         EntityUtils.consume(entity);

  //   } finally {
    //     httpclient.getConnectionManager().shutdown();
     //}
   
		
	}
	
	/**
	 * This method will build the url correspond to the arguments specify in parameters
	 * and call on findEvents() to get all the events.
	 * @param row : specific how many events needed
	 * @param location : name of the location, use "" if location is not need to specify
	 * @param category : name of category of the event , use "" if category is not need to specify
	 * @throws Exception
	 */
	public void getEventBy(int row, String location , String category) throws Exception{
		String baseUrl = "/v2/events.xml?rows=";
		if(row <=0 ){
			baseUrl = baseUrl+"10";
		}else {
			String r = String.valueOf(row);
			baseUrl = baseUrl+r ; 
		}
		baseUrl += "&fields=event:(url,name,sessions),session:(timezone,datetime_start)";
		if(location != null && location !=""){
			baseUrl = baseUrl +"&location_slug="+location;			
		}
		if(category!= null && category !=""){
			baseUrl += "&category_slug="+category ;
		}
		this.url = baseUrl ; 
		System.out.println(this.url);
		findEvents() ; 
		
	}
	
	public static void main(String[] args) throws Exception {
		EventFindaApi e = new EventFindaApi("");
		e.getEventBy(10,"wellington", "");
		System.out.println("------------------------------------------");
		System.out.println("------------------------------------------");
		System.out.println("------------------------------------------");
		e.getEventBy(10, "", "education");
		

    }
	
	public ArrayList<Event> getEventsArrayList(){
		return this.events;
	}


}