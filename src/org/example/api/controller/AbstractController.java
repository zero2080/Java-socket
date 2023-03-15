package org.example.api.controller;

import org.example.api.server.HttpResponse;
import org.example.api.server.HttpStatus;

public abstract class AbstractController implements Controller {
	private final String path;

	public AbstractController(String path) {
		this.path = path;
	}

	@Override
	public String getPath() {
		return path;
	}

	public void sendServerError(HttpResponse response) {
		response.setStatus(HttpStatus.SERVER_ERROR);
		response.send();
	}

	public void notFoundError(HttpResponse response) {
		response.setStatus(HttpStatus.NOT_FOUND);
		response.send();
	}
}
