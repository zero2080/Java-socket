package org.example.api.server;

import org.example.api.controller.Controller;
import org.example.api.controller.MemberController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientRequestHandler implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(ClientRequestHandler.class);

	private final Socket clientSocket;

	public ClientRequestHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {

		HttpResponse response = null;
		try (InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()) {

			// request : 요청
			BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
			// 숙제 : 컨트롤러 구현하기!!!
			HttpRequest httpRequest = new HttpRequest(br);
			DataOutputStream dos = new DataOutputStream(out);
			HttpResponse httpResponse = new HttpResponse(dos);

			/*
			  1. 라인별로 파싱하여 리퀘스트 객체 생성
			    1-1. METHOD
			    1-2. PATH
			    1-3. BODY
			    1-4. QueryString 등
			 */

			// Path 파싱
			String urlPath = httpRequest.getUrlPath();

			if(urlPath.indexOf("/member")==0){
				Controller memberController = new MemberController();
				memberController.call(httpRequest,httpResponse);

			}else if(urlPath.indexOf("/board")==0){

			}

			//response : 응답
//				response = new HttpResponse(dos);
//				Map<String, String> headers = new HashMap<>();
//				ResponseBody responseBody = new ResponseBody();
//				responseBody.add("title", "글제목");
//				responseBody.add("content", "본문");
//				responseBody.add("writer", "개구리");
//				responseBody.add("createdAt", "2023-03-10T10:00:00+09:00");
//
//				headers.put("Content-Type", "application/json;charset=UTF-8");
//				headers.put("Content-Length", String.valueOf(responseBody.length()));
//
//				response.setStatus(HttpStatus.OK);
//				log.info("[CustomServer] set status.");
//				response.setHeaders(headers);
//				log.info("[CustomServer] set headers.");
//				response.setBody(responseBody);
//				log.info("[CustomServer] set body : {}", responseBody.getJson());
//				response.send();
		} catch (IOException e) {
			log.error(e.getMessage());
			if (response != null) {
				response.setStatus(HttpStatus.SERVER_ERROR);
				response.send();
			}
		}

	}
}
