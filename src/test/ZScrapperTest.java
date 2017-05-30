package test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import objects.Fuel;

import scrapper.ZScapper;

public class ZScrapperTest {
	private ZScapper scraper;

	public ZScrapperTest(){
		this.scraper = new ZScapper();
	}

	@Test // Pass - check if scrapper obtains 3 fuel prices (Premium, 91, Diesel)
	public void test1() {
		List<Fuel> results = scraper.getZFuelPrices();
		assertTrue(results.size() == 3);
	}
	
	@Test // Pass - check if scrapper obtains 3 fuel prices (Premium, 91, Diesel)
	public void test2() {
		List<Fuel> results = scraper.getZFuelPrices();
		Fuel premium = results.get(0);
		assertTrue(premium.getType().equals("ZX Premium"));
	}
}
