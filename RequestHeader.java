package com.naver.spring_study;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestHeader
{
	@RequestMapping("/requestHeader")
	public void main(HttpServletRequest request)
	{
		@SuppressWarnings("unchecked")
		Enumeration<String> e = request.getHeaderNames();
		
		while(e.hasMoreElements())
		{
			String headerName = e.nextElement();
			String headerValue = request.getHeader(headerName);
			
			System.out.println(headerName + " : " + headerValue);
		}
	}
}

//[실행결과]
//host : localhost:8080
//connection : keep-alive
//cache-control : max-age=0
//sec-ch-ua : "Not A(Brand";v="8", "Chromium";v="132", "Microsoft Edge";v="132"
//sec-ch-ua-mobile : ?0
//sec-ch-ua-platform : "Windows"
//upgrade-insecure-requests : 1
//user-agent : Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 Edg/132.0.0.0
//accept : text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
//sec-fetch-site : none
//sec-fetch-mode : navigate
//sec-fetch-user : ?1
//sec-fetch-dest : document
//accept-encoding : gzip, deflate, br, zstd
//accept-language : ko,en;q=0.9,en-US;q=0.8
