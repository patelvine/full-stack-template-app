package test;

import static org.junit.Assert.*;

import org.junit.Test;

import scrapper.Crawler;

public class CrawlerTest {
	private Crawler crawler;
	
	public CrawlerTest(){
		this.crawler = new Crawler();
	}
	
	@Test // Pass - check if the crawler throws any errors upon normal call
	public void test1() {
		crawler.search("http://www.sweetmotherskitchen.co.nz/", null);
		assertTrue(crawler.getPagesToVisit().size() == 0 && crawler.getPagesVisited().size() > 0);
	}
}