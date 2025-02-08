package com.naver.spring_study;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TwoDice
{
	@RequestMapping("/rollDice") //http://localhost:8080/spring_study/rollDice
	public void main(HttpServletResponse response) throws IOException
	{
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		int index1 = (int)(Math.random()*6)+1;
		int index2 = (int)(Math.random()*6)+1;
		
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<img src='resources/img/dices/dice" + index1 + ".jpg'>");
		out.println("<img src='resources/img/dices/dice" + index2 + ".jpg'>");
		out.println("</body>");
		out.println("</html>");
	}
	
	//주사위 이미지 위치 : /src/main/webapp/resources/
}
