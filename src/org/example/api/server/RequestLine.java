package org.example.api.server;

import java.util.Objects;

public class RequestLine {
	private final HttpMethod method; // GET

	private final String urlPath; // /calculate

	private QueryStrings queryStrings; // operand1=11&operator=*&operand2=55

	public RequestLine(String requestLine) {
		String[] tokens = requestLine.split(" ");
		this.method = parseMethod(tokens[0]);
		String[] urlPathTokens = tokens[1].split("\\?");
		this.urlPath = urlPathTokens[0];

		if (urlPathTokens.length == 2) {
			this.queryStrings = new QueryStrings(urlPathTokens[1]);
		}
	}

	public HttpMethod getMethod(){
		return method;
	}
	public QueryStrings getQueryStrings() {
		return this.queryStrings;
	}

	private HttpMethod parseMethod(String method){
		switch(method){
			case "PUT":
				return HttpMethod.PUT;
			case "POST" :
				return HttpMethod.POST;
			case "PATCH" :
				return HttpMethod.PATCH;
			case "DELETE" :
				return HttpMethod.DELETE;
			case "OPTION" :
				return HttpMethod.OPTION;
			case "HEADER" :
				return HttpMethod.HEADER;
			case "GET" :
			default:
				return HttpMethod.GET;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RequestLine that = (RequestLine) o;
		return Objects.equals(method, that.method) && Objects.equals(urlPath, that.urlPath) && Objects.equals(queryStrings, that.queryStrings);
	}

	@Override
	public int hashCode() {
		return Objects.hash(method, urlPath, queryStrings);
	}

	public String getUrlPath() {
		return urlPath;
	}
}
