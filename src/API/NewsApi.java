package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import twitter4j.JSONException;
import twitter4j.JSONObject;

/**
 * 
 * @author Vinesh Patel <vinesh.kumar.patel.2@gmail.com>
 *
 */

public class NewsApi {
	private String key;
	private final String articlesUrl;
	private final String sourcesUrl;

	/**
	 * Constructs NewsApi Object
	 */
	public NewsApi() {
		this.key = "8b50cb8a6c49461c9b9fed87590551c1";
		this.articlesUrl = "https://newsapi.org/v1/articles?";
		this.sourcesUrl = "https://newsapi.org/v1/sources?";
	}

	/**
	 * 
	 * @param source
	 *            News source e.g. bbc-news
	 * @param sortBy
	 *            Retrieve news articles based on popular, top or latest
	 * @return JSONObject containing response
	 */
	public JSONObject getArticles(String source, String sortBy) {
		String url = this.articlesUrl + "source=" + source + "&sortBy=" + sortBy + "&apiKey=" + this.key;
		try {
			return readJsonFromUrl(url);
			// got the doc now i need to find key words with NLP and make a list
			// of them
		} catch (IOException e) {
			System.out.println("\n" + "** FAILURE ** source invalid OR sortBy not provided for source"
					+ "\n** RECALL ** recalling API request with bbc-news source param ONLY" + "\n");
			return getArticles("bbc-news", "");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param lang
	 *            Language (e.g. en)
	 * @param category
	 *            Category (e.g. business)
	 * @param country
	 *            Country (e.g. au)
	 * @return JSONObject containing response
	 */
	public JSONObject getSources(String lang, String category, String country) {
		String url = this.sourcesUrl + "lang=" + lang + "&category=" + category + "&country=" + country;
		System.out.println(url);
		try {
			return readJsonFromUrl(url);
			// got the doc now i need to find key words with NLP and make a list
			// of them
		} catch (IOException e) {
			System.out.println("\n" + "** FAILURE ** There are no sources with the params you have specified"
					+ "\n** RECALL ** Recalling API request with only lang param" + "\n");
			return getSources(lang, "", "");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Creates InputStream for URL and passes results to readAll method Results
	 * from readAll are parsed into a JSONObject
	 */
	private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	/*
	 * Forms string from URL response
	 */
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}