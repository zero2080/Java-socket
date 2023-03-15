package org.example.api.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryStrings {
	private Map<String, Object> queryStrings = new HashMap<>();

	public QueryStrings(String queryStringLine) {
		String[] queryStringTokens = queryStringLine.split("&");
		Arrays.stream(queryStringTokens)
				.forEach(this::parseValue);
	}

	private void parseValue(String queryString) {
		String[] values = queryString.split("=");
		if (values.length < 2) {
			return;
		}

		if (queryStrings.get(values[0]) == null) {
			queryStrings.put(values[0], values[1]);
		} else if (queryStrings.get(values[0]) instanceof List) {
			List<String> value = (List<String>) queryStrings.get(values[0]);
			value.add(values[1]);
			queryStrings.put(values[0], value);
		} else {
			List<String> value = new ArrayList<>();
			value.add((String) queryStrings.get(values[0]));
			value.add(values[1]);
			queryStrings.put(values[0], value);
		}
	}

	public Object getValue(String key) {
		return this.queryStrings.keySet().stream()
				.filter(key::equals)
				.map(queryStrings::get)
				.findFirst()
				.orElse(null);
	}
}
