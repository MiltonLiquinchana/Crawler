package com.mflq.crawler.models.bean;

import java.util.ArrayList;
import java.util.List;

public class CrawlerRequest {
	private List<String> urls;
	private List<String> domainsFilter;
	private List<String> extencionExclucion;

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public List<String> getDomainsFilter() {
		return domainsFilter;
	}

	public void setDomainsFilter(List<String> domainsFilter) {
		this.domainsFilter = domainsFilter;
	}

	public List<String> getExtencionExclucion() {
		return extencionExclucion;
	}

	public void setExtencionExclucion(List<String> extencionExclucion) {
		this.extencionExclucion = extencionExclucion;
	}

}
