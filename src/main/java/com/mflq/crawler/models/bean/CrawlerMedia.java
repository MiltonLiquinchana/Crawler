package com.mflq.crawler.models.bean;

import java.util.List;

public class CrawlerMedia {
	private List<CrawlerMediaFile> images;
	private List<CrawlerMediaFile> videos;
	private List<CrawlerMediaFile> webFiles;
	public List<CrawlerMediaFile> getImages() {
		return images;
	}
	public void setImages(List<CrawlerMediaFile> images) {
		this.images = images;
	}
	public List<CrawlerMediaFile> getVideos() {
		return videos;
	}
	public void setVideos(List<CrawlerMediaFile> videos) {
		this.videos = videos;
	}
	public List<CrawlerMediaFile> getWebFiles() {
		return webFiles;
	}
	public void setWebFiles(List<CrawlerMediaFile> webFiles) {
		this.webFiles = webFiles;
	}
}
