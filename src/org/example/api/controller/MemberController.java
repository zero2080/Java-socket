package org.example.api.controller;

import org.example.api.dto.Member;
import org.example.api.server.HttpMethod;
import org.example.api.server.HttpRequest;
import org.example.api.server.HttpResponse;
import org.example.api.server.HttpStatus;

public class MemberController extends AbstractController {

	public MemberController(){
		super("member");
	}

	@Override
	public void call(HttpRequest request, HttpResponse response){
		String subPath = request.getUrlPath().replace(String.format("/%s/",getPath()),"");

		switch(subPath){
			case "signup":
				signup(request,response);
				break;
			default:
				notFoundError(response);
		}
	}
	private void signup(HttpRequest request, HttpResponse response){
		if(request.getMethod() != HttpMethod.POST){
			notFoundError(response);
			return;
		}
		// 바디 파싱 및 서비스 호출


		response.setStatus(HttpStatus.OK);
		response.send();

	}
}
