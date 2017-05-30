package scrapper;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Vinesh Patel <vinesh.kumar.patel.2@gmail.com>
 * 
 *         <p>
 *         This class crawls through a given URL and obtains every URL which is
 *         related to to the Website.
 * 
 *         <p>
 *         Method call examples:
 *         <p>
 *         spider.search("http://www.sweetmotherskitchen.co.nz/",
 *         trendingWords);
 */

public class Crawler {
	private Set<String> pagesVisited = new HashSet<String>();
	private List<String> pagesToVisit = new LinkedList<String>();
	private String root;

	/**
	 * Crawl through a given website URL for all Website links
	 * 
	 * @param url
	 *            Root URL
	 * @param trendingWords
	 *            List of Keywords to focus on within the website
	 */
	public void search(String url, List<String> trendingWords) {
		do {
			String currentUrl;
			CrawlerLeg leg = new CrawlerLeg();

			if (this.pagesToVisit.isEmpty()) {
				root = url;
				currentUrl = root;
				this.pagesVisited.add(url);
			} else {
				currentUrl = this.nextUrl();
				if (currentUrl == null) {
					break;
				}
			}

			leg.crawl(root, currentUrl);
			boolean success = leg.searchForWord(trendingWords);
			// if(success)
			// {
			// System.out.println(String.format("**Success** Word %s found at
			// %s", searchWord, currentUrl));
			// //break;
			// }
			this.pagesToVisit.addAll(leg.getLinks());
		} while (this.pagesToVisit.size() > 0);

		System.out.println("\n**Done** Visited " + this.pagesVisited.size() + " web page(s)"); // finished
	}

	/*
	 * Method to change current Url - URLs are never crawled twice
	 */
	private String nextUrl() {
		String nextUrl = null;

		do {
			if (this.pagesToVisit.size() == 0)
				return null;
			nextUrl = this.pagesToVisit.remove(0);
		} while (this.pagesVisited.contains(nextUrl));

		this.pagesVisited.add(nextUrl);
		return nextUrl;
	}

	public List<String> getPagesToVisit() {
		return this.pagesToVisit;
	}

	public Set<String> getPagesVisited() {
		return this.pagesVisited;
	}
}