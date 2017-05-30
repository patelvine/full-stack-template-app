package scrapper;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scrapper {

	public List<String[][]> scrape(Document htmlDocument) {
		// process request here for desired htmlDocument/Link
		return null;
	}

	private List<String[][]> tableScrape(Document htmlDocument) {
		List<String[][]> processedTables = new ArrayList<String[][]>();
		Elements tables = htmlDocument.select("table");
		for (Element table : tables) {
			Elements cap = table.select("caption");// name
			Elements trs = table.select("tr");
			String[][] trtd = new String[trs.size()][];
			for (int i = 0; i < trs.size(); i++) {
				Elements tds = trs.get(i).select("td");
				trtd[i] = new String[tds.size()];
				for (int j = 0; j < tds.size(); j++) {
					trtd[i][j] = tds.get(j).text();
				}
			}
			processedTables.add(trtd);
		}
		return processedTables;
	}
}
