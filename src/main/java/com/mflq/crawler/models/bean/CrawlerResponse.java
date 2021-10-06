package com.mflq.crawler.models.bean;

import java.util.List;

public class CrawlerResponse {
	private List<CrawlerResponseBody> urls;

	public List<CrawlerResponseBody> getUrls() {
		return urls;
	}

	public void setUrls(List<CrawlerResponseBody> urls) {
		this.urls = urls;
	}
}
