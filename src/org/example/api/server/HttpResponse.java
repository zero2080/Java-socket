package org.example.api.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

public class HttpResponse {
	private final DataOutputStream dos;

	public HttpResponse(DataOutputStream dos) {
		this.dos = dos;
	}

	public void setStatus(HttpStatus status) {
		try {
			dos.writeBytes(String.format("HTTP/1.1 %d %s \r\n", status.getCode(), status.name()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void setHeaders(Map<String, String> headers) {
		try {
			for (String key : headers.keySet()) {
				dos.writeBytes(String.format("%s: %s \r\n", key, headers.get(key)));
			}
			dos.writeBytes("\r\n");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void setBody(ResponseBody body){
		try {
			dos.write(body.getJson().getBytes("UTF-8"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void send(){
		try {
			dos.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
