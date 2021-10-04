package com.mflq.crawler.services;

import com.mflq.crawler.models.bean.CrawlerRequest;
import com.mflq.crawler.thread.ThReadCrawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class ThReadCrawlerService {
	public void startProcess(CrawlerRequest crawlerRequest) throws Exception {
		String crawlStorageFolder = "temporal";

		int numberOfCrawlers = 7;

		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);

		config.setIncludeBinaryContentInCrawling(true);

		// Instantiate the controller for this crawl.
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

		// Para cada rastreo, debe agregar algunas URL de inicio. Estos son los primeros
		// URL que se obtienen y luego el rastreador comienza a seguir los enlaces
		// que se encuentran en estas páginas
		/* Con un for recorremos la url */
		for (String url : crawlerRequest.getUrls()) {
			controller.addSeed(url);
		}

		// La fábrica que crea instancias de rastreadores.
		CrawlController.WebCrawlerFactory<ThReadCrawler> factory = () -> new ThReadCrawler(crawlerRequest);

		// Inicie el rastreo. Esta es una operación de bloqueo, lo que significa que su
		// código
		// llegará a la línea después de esto solo cuando finalice el rastreo.
		controller.start(factory, numberOfCrawlers);
	}
}
