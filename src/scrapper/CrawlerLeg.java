package scrapper;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Vinesh Patel <vinesh.kumar.patel.2@gmail.com>
 * 
 *         <p>
 * 		This class crawls through a single URl in search for links and also
 *         begins the Web Scraper once the web page has been processed
 * 
 */

public class CrawlerLeg {
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private List<String> links = new LinkedList<String>();
	private Document htmlDocument;
	private String root;

	/**
	 * Crawls URL to obtain all possible links associated with it.
	 * 
	 * @param root
	 *            Root URL to ensure crawler does not branch off to other
	 *            websites
	 * @param url
	 *            URL to crawl
	 * @return Boolean value signifying success or failure
	 */
	public boolean crawl(String root, String url) {
		System.out.println("crawl " + url);
		this.root = root;
		try {
			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
			Document htmlDocument = connection.get();
			this.htmlDocument = htmlDocument;

			if (connection.response().statusCode() == 200) {
				System.out.println("\n**Visiting** Received web page at " + url);
			}
			if (!connection.response().contentType().contains("text/html")) {
				System.out.println("**Failure** Retrieved something other than HTML");
				return false;
			}

			Elements linksOnPage = htmlDocument.select("a[href]");

			for (Element link : linksOnPage) {
				if (link.absUrl("href").toLowerCase().contains(this.root.toLowerCase())) {
					this.links.add(link.absUrl("href"));
				}
			}
			return true;
		} catch (IOException ioe) {
			System.out.println("failed");
			return false;
		}
	}

	/**
	 * Searches page for values relating to the words provided
	 * 
	 * @param searchWord
	 *            List of Strings of interest
	 * @return Boolean value signifying success or failure
	 */
	public boolean searchForWord(List<String> searchWord) {
		if (this.htmlDocument == null) {
			System.out.println(
					"#######################################- ERROR! Call crawl() before performing analysis on the document -#######################################");
			return false;
		}
		String bodyText = this.htmlDocument.body().text();

		// -- this is where the scrapper will be placed

		return false;// bodyText.toLowerCase().contains(searchWord.toLowerCase());
	}

	/**
	 * Returns all Links for the URL used to create this CrawlerLeg
	 * 
	 * @return Returns all Links associated with this URL (Leg)
	 */
	public List<String> getLinks() {
		return this.links;
	}
}