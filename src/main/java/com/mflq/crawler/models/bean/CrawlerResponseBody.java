package com.mflq.crawler.models.bean;

public class CrawlerResponseBody {
	private String url;
	private CrawlerContent content;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public CrawlerContent getContent() {
		return content;
	}
	public void setContent(CrawlerContent content) {
		this.content = content;
	}
}
