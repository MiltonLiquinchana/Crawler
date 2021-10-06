package com.mflq.crawler.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mflq.crawler.models.bean.CrawlerRequest;
import com.mflq.crawler.services.ThReadCrawlerService;

@RestController
@RequestMapping("crawler")
public class CrawlerController {

	@PostMapping("start")
	public ResponseEntity<CrawlerRequest> generarCrawler(@RequestBody CrawlerRequest crawlerRequest) throws Exception {

		/* Instancia el servicio que ejecuta el crawler */
		ThReadCrawlerService service = new ThReadCrawlerService();

		/* Inicia el proceso */
		service.startProcess(crawlerRequest);
		return new ResponseEntity<CrawlerRequest>(crawlerRequest, HttpStatus.OK);
	}

}
