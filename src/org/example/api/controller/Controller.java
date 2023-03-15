package org.example.api.controller;

import org.example.api.server.HttpRequest;
import org.example.api.server.HttpResponse;

public interface Controller {
	String getPath();
	void call(HttpRequest request, HttpResponse response);

}
