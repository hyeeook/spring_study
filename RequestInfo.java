package com.naver.spring_study;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestInfo
{
	@RequestMapping("/requestMethod")	//localhost:8080/spring_study/requestMethod
	public void main(HttpServletRequest request)
	{
		//http://52.78.79.190:8080/spring_study/findingDayOfWeek?year=2025&month=1&day=1
		//getScheme() : http
		//getServerName() : 52.78.79.190
		//getServerPort() : 8080
		//getContextPath() : /spring_study
		//getServletPath() : /findingDayOfWeek
		//getQueryString(): year=2025&month=1&day=1
		//getRequestURI() : /spring_study/findingDayOfWeek
		//getRequestURL() : http://52.78.79.190:8080/spring_study/findingDayOfWeek
		
		System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());	//요청 내용의 인코딩
		System.out.println("request.getContentLength() = " + request.getContentLength());			//요청 내용의 길이 / 알 수 없을 때는 -1
		System.out.println("request.getContentType() = " + request.getContentType());				//요청 내용의 타입 / 알 수 없을 때는 null
		
		System.out.println();
		
		System.out.println("request.getMethod() = " + request.getMethod());		//요청 방법 / GET
		System.out.println("request.getProtocol() = " + request.getProtocol());	//프로토콜의 종류와 버전 / HTTP/1.1
		System.out.println("request.getScheme() = " + request.getScheme());		//프로토콜 / http

		System.out.println();
		
		System.out.println("request.getServerName() = " + request.getServerName());	//서버 이름 또는 ip주소 / localhost
		System.out.println("request.getServerPort() = " + request.getServerPort());	//서버 포트 / 8080
		System.out.println("request.getRequestURL() = " + request.getRequestURL());	//요청 URL / http://localhost:8080/spring_study/requestMethod
		System.out.println("request.getRequestURI() = " + request.getRequestURI());	//요청 URI / /spring_study/requestMethod
		
		System.out.println();
		
		System.out.println("request.getContextPath() = " + request.getContextPath());	//context path / /spring_study
		System.out.println("request.getServletPath() = " + request.getServletPath());	//servlet path / /requestMethod
		System.out.println("request.getQueryString() = " + request.getQueryString());	//쿼리 스트링
		
		System.out.println();
		
		System.out.println("request.getLocalName() = " + request.getLocalName());	//로컬 이름 / 0:0:0:0:0:0:0:1
		System.out.println("request.getLocalPort() = " + request.getLocalPort());	//로컬 포트 / 8080
		
		System.out.println();
		
		System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());	//원격 ip주소 / 0:0:0:0:0:0:0:1
		System.out.println("request.getRemoteHost() = " + request.getRemoteHost());	//원격 호스트 또는 ip주소 / 0:0:0:0:0:0:0:1
		System.out.println("request.getRemotePort() = " + request.getRemotePort());	//원격 포트 / 57737
	}
}
