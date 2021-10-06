package com.mflq.crawler.models.bean;

import java.util.List;

public class CrawlerContent {
	private List<String> links;
	private CrawlerMedia media;
	public List<String> getLinks() {
		return links;
	}
	public void setLinks(List<String> links) {
		this.links = links;
	}
	public CrawlerMedia getMedia() {
		return media;
	}
	public void setMedia(CrawlerMedia media) {
		this.media = media;
	}
}
