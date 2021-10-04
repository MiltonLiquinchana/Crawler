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
		/*
		 * CrawlerRequest crawlerRequest = new CrawlerRequest(); List<String> lista =
		 * new ArrayList<>(); lista.add("milton"); lista.add("felipe");
		 * System.out.println(lista); crawlerRequest.setDomainsFilter(lista);
		 */
		 ThReadCrawlerService service = new ThReadCrawlerService();
		 service.startProcess(crawlerRequest);
//		String filtro = ".*(\\.(";
//		int contador = 1;
//		String separador = "|";
//		for (String extencion : crawlerRequest.getExtencionExclucion()) {
//
//			/* Valida si es el elemento final de la lista */
//			if (crawlerRequest.getExtencionExclucion().size() == contador) {
//				separador = "))$";
//
//			}
//			filtro += extencion + separador;
//
//			System.out.println(contador);
//			contador++;
//			System.out.println(extencion);
//		}
//		System.out.println(": " + filtro);
//		System.out.println(".*(\\\\.(css|js|mp3|mp4|zip|gz))$");

		return new ResponseEntity<CrawlerRequest>(crawlerRequest, HttpStatus.OK);
	}

}
