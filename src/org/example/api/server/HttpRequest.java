package org.example.api.server;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {

	private final RequestLine requestLine;
	public HttpRequest(BufferedReader br) throws IOException {
		this.requestLine = new RequestLine(br.readLine());
	}

	public HttpMethod getMethod(){
		return requestLine.getMethod();
	}

	public String getUrlPath() {
		return requestLine.getUrlPath();
	}

	public QueryStrings getQueryStrings() {
		return requestLine.getQueryStrings();
	}

}
