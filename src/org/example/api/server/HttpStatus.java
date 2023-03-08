package org.example.api.server;

public enum HttpStatus {
	OK(200);
	final int code;

	HttpStatus(int code) {
		this.code = code;
	}

	public int getCode(){
		return code;
	}
}
