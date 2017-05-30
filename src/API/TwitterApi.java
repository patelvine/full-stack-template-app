package API;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;

import database.DBconnector;
import objects.PlaceWoeid;
import twitter4j.Location;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.UploadedMedia;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;


/**
 * This class will handle the call to twitter Api using Twitter4j.java 
 * @author cheavleat
 * need to change key 
 */
public class TwitterApi {
	private Twitter twitter ; 
	private ArrayList<PlaceWoeid> placeWoeidList ;
	private ArrayList<Trend> trendArrayList ; 
	
	
	/**
	 * This is the constructor for already exist Twitter object (already authenticate 
	 * from twitter through Oauth)
	 * @param t
	 */
	public TwitterApi(Twitter t){
		this.twitter = t ;
	}
	
	/**
	 * This is the constructor for non exist Twitter object that need to do authentication
	 * through Oauth after
	 */
	public TwitterApi() {} 
	
	public ArrayList<PlaceWoeid> getPlaceWoeidArrayList() {
		return this.placeWoeidList;
	}
	
	
	
	public Twitter getTwitter(){
		if(this.twitter != null ){
			return this.twitter ; 
		}
		return null ; 
	}
		
	/**
	 * This authentication method will sent request to get oAuth Access token from Twitter
	 * by providing the token key and token secret (store in twitter4j.properties), The twitter
	 * will redirect to the authentication page (ask user for permission) than it will provide
	 * the pin which use later to provide to the method to get access token key and access token 
	 * secret. 
	 * @return Twitter object 
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public Twitter authentication() throws IOException, URISyntaxException{
		Twitter twitter = new TwitterFactory().getInstance();		 
		 try {
		   RequestToken requestToken = twitter.getOAuthRequestToken();
		   //System.out.println("Got request token.");
           //System.out.println("Request token: " + requestToken.getToken());
           //System.out.println("Request token secret: " + requestToken.getTokenSecret());
           AccessToken accessToken = null;
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           while (null == accessToken) {
               System.out.println("Open the following URL and grant access to your account:");
               System.out.println(requestToken.getAuthorizationURL());
               Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));               
               System.out.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");
               String pin = br.readLine();
               try {
                   if (pin.length() > 0) {
                       accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                   } else {
                       accessToken = twitter.getOAuthAccessToken(requestToken);
                   }
                   this.twitter = twitter ; 
               } catch (TwitterException te) {
                   if (401 == te.getStatusCode()) {
                       System.out.println("Unable to get the access token, System exit now.");
                       System.exit(-1);
                   } else {
                       te.printStackTrace();
                   }
               }
           }
           //System.out.println("Got access token.");
           //System.out.println("Access token: " + accessToken.getToken());
           //System.out.println("Access token secret: " + accessToken.getTokenSecret());          
		} catch (TwitterException e) {
			e.printStackTrace();
		}		 
		return twitter;
	}
	
	/**
	 * TWITTER OBJECT MUST ALREADY BE AUTHENTICATED THROUGH OAUTH TO CALL THIS METHOD
	 * Update status with the given text 
	 * @param text
	 * @throws TwitterException
	 */
	public void updateStatus(String text) throws TwitterException{
		if (this.twitter == null){
			throw new TwitterException("MUST AUTHENTICATION BEFORE CALLING THIS METHOD") ;
		}else {
			this.twitter.updateStatus(text);	
		}
	}	
	
	
	
	
	/**
	 * TWITTER OBJECT MUST ALREADY BE AUTHENTICATED THROUGH OAUTH TO CALL THIS METHOD
	 * Get user's timeline by clarify what timeline in parameter 
	 * @param timeline (if 1 get user timeline (all the status in profile), 2 get the home timeline)
	 * @return list of status from timeline 
	 */
	public List<Status> getTimeline(int timeline){
		List<Status> statuses = new ArrayList<Status>() ;
		try {
			
			switch(timeline){
			case 1: statuses = this.twitter.getUserTimeline(); break ;
			case 2: statuses = this.twitter.getHomeTimeline(); break ;
			default: break ;	
			}			
			for (Status s : statuses){
				System.out.println(s);
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return statuses ; 
	}
	
	
	/**NOT WORKING
	 * TWITTER OBJECT MUST ALREADY BE AUTHENTICATED THROUGH OAUTH TO CALL THIS METHOD
	 * Upload multiple Media file by providing all the name of the files
	 * @param text 
	 * @param file
	 */
	public void uploadMedias(String text , String ...files) {
		try {
			long[] mediaIds = new long[files.length];
			for (int i = 0 ; i < files.length ; i++){
				UploadedMedia media;
				media = twitter.uploadMedia(new File(files[i]));
				mediaIds[i]= media.getMediaId();
			}
			StatusUpdate statusUpdate = new StatusUpdate(text);
			statusUpdate.setMediaIds(mediaIds);
			Status status = twitter.updateStatus(statusUpdate);
			System.exit(0);
		} catch (TwitterException e) {
			e.printStackTrace();
			System.exit(-1);
		}		
	}
	
	/**
	 * TWITTER OBJECT MUST ALREADY BE AUTHENTICATED THROUGH OAUTH TO CALL THIS METHOD
	 * Search tweet by mention the keyword
	 * @param text (keyword)
	 * @return list of status from search 
	 */
	public List<Status> searchTweet(String text){
		List<Status> tweets = new ArrayList<Status>() ; 
		try {
			Query query = new Query(text);
	        QueryResult result;
	        do {
	        	result = twitter.search(query);				
	        	tweets = result.getTweets();
	            for (Status tweet : tweets) {
	                System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
	            }
	        } while ((query = result.nextQuery()) != null);        
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tweets;
		
	}
	
	
	/**
	 * TWITTER OBJECT MUST ALREADY BE AUTHENTICATED THROUGH OAUTH TO CALL THIS METHOD
	 * Get favourite status
	 * @return list of status from favourites  
	 */
	public List<Status> getFavouriteStatus(){
		List<Status> fav = new ArrayList<Status>() ; 
		try {
			fav = this.twitter.getFavorites();
			for(Status status: fav){
				System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
			}			
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fav ; 
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	//////////////////////////Trending/////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////

	/**
	 * TWITTER OBJECT MUST ALREADY BE AUTHENTICATED THROUGH OAUTH TO CALL THIS METHOD
	 * This method will get all the place.woeid  
	 */
	public void getPlacesWoeid(){
		ResponseList<Location> locations;
		Properties prop = new Properties();
		placeWoeidList = new ArrayList<PlaceWoeid>();
	    OutputStream os = null;
        try {
			locations = this.twitter.getAvailableTrends();
			System.out.println("Showing available trends");
			int id = 0 ; 
			try {
				os = new FileOutputStream("placeWoeid.properties");				
				for (Location location : locations) {  
					String loc = location.getName().toLowerCase() ;
					 loc = loc.replace(" ", "_");
			         prop.setProperty(loc, Integer.toString(location.getWoeid()));
			         this.placeWoeidList.add(new PlaceWoeid(loc, location.getWoeid()));
			         id++;			            
			    }
				prop.store(os, "placeTrend.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) { // for storing 
				e.printStackTrace();
			}	       
		} catch (TwitterException e) {
			e.printStackTrace();
		}
        
	}
	
	/**
	 * This method is set to read file (properties file) and try to find the string given (location)
	 * If found the method will return the woeid of the location, otherwise it will return -1.
	 * @param location: 
	 * @param f: properties file 
	 * @return woeid : woeid of location , -1 if not found
	 */
	public int findWoeidFromFile(String location , String f){
        File file = new File(f);
        Properties prop = new Properties();        
        InputStream is = null;
        location = location.toLowerCase();
        location = location.replace(" ", "_");
       System.out.println("Trying to find woeid for: " +location  );
       
        int woeid = -1 ; 
        	try {
        		if(file.exists()){
					is = new FileInputStream(file);
					prop.load(is);
					if(prop.getProperty(location)!= null ){
						woeid = Integer.parseInt(prop.getProperty(location));
						System.out.println(woeid);
						return woeid ; 
					}else {
						System.out.println(woeid + " Can not find the location" );
						return -1 ; 
					}	
        		}else {
        			System.out.println("file not exist");
        			return -1 ;
        		}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) { // for loading inputstream to prop
				e.printStackTrace();
			}
        
        return -1 ; 
        		
	}
	
	
	/**NOTE: trendArrayList get renew everytime this method call.( No need to clear array )  
	 * TWITTER OBJECT MUST ALREADY BE AUTHENTICATED THROUGH OAUTH TO CALL THIS METHOD
	 * Get the popular trends(hashtag) at the place 
	 * @param woeid : place's woeid
	 * @return Array of Trend
	 */
	public Trend[] getPlaceTrends(int woeid){
		
		try {
			trendArrayList = new ArrayList<Trend>() ;
			Trends trends = this.twitter.getPlaceTrends(woeid);
			System.out.print("Trends "+this.twitter.getPlaceTrends(woeid));
			Trend[] trendArray = trends.getTrends();
			for (Trend trend: trendArray ){
				this.trendArrayList.add(trend);
				System.out.println("trend : "+trend.getName() +"---"+trend);
			}			
			return trendArray ; 
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return null ; 
	}
	
	/**
	 * Upload all the trending topic of the location to the database 
	 * @param location
	 */
	public void uploadTrendingToDB(String location ){
		try {
			Connection con = DBconnector.getConnection();
			PreparedStatement ps;	
			location = location.replace(" ", "_");
			java.sql.Timestamp date = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
			//Date date = new Date(Calendar.getInstance().getTime().getTime()) ;
			for (Trend t : this.trendArrayList){				
				String topic = t.getName() ;
				String link = t.getURL();			
				ps = con.prepareStatement("INSERT INTO trendings (location,topic,link,date) "+ "VALUES(?,?,?,?)"); 
				ps.setString(1, location);
				ps.setString(2,topic); 
	            ps.setString(3,link); 
	            ps.setTimestamp(4 ,date); // not sure if it work
	            ps.execute(); 
			}   
			con.close();  
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * This method will set to find the woeid of the location given in parameter from the 
	 * placeWoeid.properties, then if found it will process to get all the trending topic 
	 * in the location , finally it will upload to the database .
	 * In case the woeid not found the program will be call on exit  
	 * @param location
	 */
	public void getTrendNUploadToDB(String location ){
		int woeid = this.findWoeidFromFile(location, "placeWoeid.properties");
		
		if (woeid == -1 ){
			System.err.println("Can not find the location: make sure the spelling is correct: "+ location);
			System.exit(-1);
		}else {
			this.getPlaceTrends(woeid);
			this.uploadTrendingToDB(location);
		}	
	}
	
	
	/**
	 * The method will get the trending on more than one location 
	 * and upload all those data that retrieve from trending into
	 * Trending table in database 
	 * @param locations: array of loations
	 */
	public void getTrendingOnManyLocation ( String[] locations){
		for ( String loc : locations){
			this.getTrendNUploadToDB(loc);
		}
	}
	
	
	/**
	 * Convert any object to Json String
	 * @param o
	 * @return
	 */
	public String objectToJson(Object o){
		String json = new Gson().toJson(o);
		return json ;
	}
	
	
	
	/** 
	 * Delete the outdate Trendings topic from database 
	 * @param d
	 */
	public void deleteOutdatedTrending(String location ){
		try {
			Connection con = DBconnector.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement("DELETE FROM trendings WHERE location = ? ");
			ps.setString(1, location);
			ps.execute();
			con.close();
						
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	
	public void cachRequestUploadDB(String location){
		location = location.replace(" ", "_");
		try {			
			Connection con = DBconnector.getConnection();
			PreparedStatement selectps;
			Date todayDate = new Date() ;
			selectps = con.prepareStatement("SELECT * FROM trendings where location =? "); 
			selectps.setString(1,location);
			ResultSet rs = selectps.executeQuery();
			if(!rs.next()){
				System.out.println("There is no data in database - request from twitter and upload to DB");
				getTrendNUploadToDB(location);			
			}else{
				rs.next();
				Date date = rs.getTimestamp("date");
				double hoursDiff = getDateDiffInHours(date, todayDate);
				if(hoursDiff >= 24){
					System.out.println("Data in DB is outdated - request from twitter and upload to DB "+ hoursDiff);
					deleteOutdatedTrending(location);
					getTrendNUploadToDB(location);
				}else {
					System.out.println("Use Cached data");
				}
			}
			con.close();
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public static long getDateDiffInHours(Date date1, Date date2) {
		TimeUnit timeUnit = TimeUnit.HOURS ; 
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
	 public static void main(String[] args) throws IOException, URISyntaxException, TwitterException {
			TwitterApi tpt = new TwitterApi();
			tpt.authentication();
			tpt.cachRequestUploadDB("new zealand");
			//tpt.updateStatus("Today is "+ new Date());
			//tpt.getPlacesWoeid();
			//tpt.getPlaceTrends(23424977); // united state  //2348079 auckland 
			//tpt.uploadTrendingToDB("united states");
			//tpt.getTrendNUploadToDB("worldwide");
			//tpt.getTimeline(2);
			//tpt.uploadMedias("Hello it me", "placeTrend.properties"); not working due to limit
			//tpt.searchTweet("auckland");
			//tpt.getFavouriteStatus();
			//	tpt.findWoeidFromFile("united states", "placeWoeid.properties");
			//String [] locations = { "united states", "australia", "new zealand", "japan"};
			//tpt.getTrendingOnManyLocation(locations);
		 }
	
	
}
