package com.naver.spring_study;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestMessage
{
	@SuppressWarnings("unchecked")
	@RequestMapping("requestMessage")
	public void main(HttpServletRequest request) throws IOException
	{
		String requestLine;
		String queryString;
		Enumeration<String> e;
		final int CONTENT_LENGTH;
		byte[] content;
		InputStream in;
		
		//request line
		requestLine = request.getMethod();				//GET or POST
		requestLine += (" " + request.getRequestURI());	///spring_study/requestMessage
		queryString = request.getQueryString();			//?year=2021&month=10&day=1
		requestLine += (queryString == null ? "" : "?" + queryString);
		requestLine += (" " + request.getProtocol());	//HTTP/1.1
		System.out.println(requestLine);
		
		//request header
		e = request.getHeaderNames();
		
		while(e.hasMoreElements())
		{
			String headerName = e.nextElement();
			String headerValue = request.getHeader(headerName);
			
			System.out.println(headerName + " : " + headerValue);
		}
		
		//request body
		//POST일 때만 해당, GET은 body가 없음(CONTENT_LENGTH=0)
		CONTENT_LENGTH = request.getContentLength();
		
		if(CONTENT_LENGTH > 0)
		{
			content = new byte[CONTENT_LENGTH];
			in = request.getInputStream();
			in.read(content, 0, CONTENT_LENGTH);
			
			System.out.println();
			System.out.println(new String(content, "utf-8"));
		}
	}
}

//[실행결과 - GET]
//GET /spring_study/requestMessage HTTP/1.1
//host : localhost:8080
//connection : keep-alive
//cache-control : max-age=0
//sec-ch-ua : "Not(A:Brand";v="99", "Microsoft Edge";v="133", "Chromium";v="133"
//sec-ch-ua-mobile : ?0
//sec-ch-ua-platform : "Windows"
//upgrade-insecure-requests : 1
//user-agent : Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36 Edg/133.0.0.0
//accept : text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
//sec-fetch-site : none
//sec-fetch-mode : navigate
//sec-fetch-user : ?1
//sec-fetch-dest : document
//accept-encoding : gzip, deflate, br, zstd
//accept-language : ko,en;q=0.9,en-US;q=0.8

//[실행결과 - POST]
//POST /ch2/requestMessage HTTP/1.1  <--- 요청 라인(request line)
//host:localhost:8080
//connection:keep-alive
//content-length:90
//sec-ch-ua:"Chromium";v="94", "Google Chrome";v="94", ";Not A Brand";v="99"
//cache-control:no-cache
//content-type:application/x-www-form-urlencoded
//sec-ch-ua-mobile:?0
//user-agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.61 Safari/537.36
//sec-ch-ua-platform:"macOS"
//accept:*/*
//origin:chrome-extension://coohjcphdfgbiolnekdpbcijmhambjff
//sec-fetch-site:none
//sec-fetch-mode:cors
//sec-fetch-dest:empty
//accept-encoding:gzip, deflate, br
//accept-language:ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
//                          <--- empty line
//year=2021&month=10&day=1  <--- request body
