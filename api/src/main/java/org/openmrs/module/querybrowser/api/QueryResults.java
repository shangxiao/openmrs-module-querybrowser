package org.openmrs.module.querybrowser.api;

import java.util.List;

public class QueryResults {

	private List<String> headers;

	private List<List<Object>> results;

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(final List<String> headers) {
		this.headers = headers;
	}

	public List<List<Object>> getResults() {
		return results;
	}

	public void setResults(final List<List<Object>> results) {
		this.results = results;
	}
}
