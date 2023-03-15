package org.example.api.server;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseBody {
	private final Map<String, String> body;
	private String json;

	public ResponseBody() {
		body = new LinkedHashMap<>();
	}

	public void add(String key, String value) {
		body.put(key, value);
		parseJson();
	}

	private void parseJson() {
		if (body.size() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("{");

			Iterator<String> keys = body.keySet().iterator();
			while(keys.hasNext()){
				String key = keys.next();
				sb.append("\"");
				sb.append(key);
				sb.append("\": ");
				sb.append("\"");
				sb.append(body.get(key));
				sb.append("\"");
				if(keys.hasNext()){
					sb.append(", ");
				}
			}
			sb.append("}");
			json = sb.toString();
		}
	}

	public int length() {
		try {
			return json.getBytes("UTF-8").length;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public String getJson(){
		return json;
	}
}
