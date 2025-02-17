package com.naver.spring_study;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//로컬 실행.
//public class DayCalculator
//{
//	public static void main(String[] args)
//	{
//		String sYear = args[0];
//		String sMonth = args[1];
//		String sDay = args[2];
//		
//		int iYear = Integer.parseInt(sYear);
//		int iMonth = Integer.parseInt(sMonth);
//		int iDay = Integer.parseInt(sDay);
//		
//		Calendar cCal = Calendar.getInstance();
//		cCal.set(iYear, iMonth-1, iDay);
//		
//		int iDayOfWeek = cCal.get(Calendar.DAY_OF_WEEK);
//		char cDayOfWeek = "일월화수목금토".charAt(iDayOfWeek-1);
//		
//		System.out.println(sYear + "년 " + sMonth + "월 " + sDay + "일은 " + cDayOfWeek + "요일 입니다.");
//	}
//	//매개변수를 받기 위해 콘솔에서 실행하는 방법
//	//1) 프로젝트 폴더 하위에 'target' 폴더(여기에 컴파일 된 class 파일이 생성됨) 우클릭
//	//2) 위에서 네번째 'Show In' 클릭
//	//3) 맨 위의 'Terminal' 클릭
//	//4) 'cd classes' 입력
//	//5) 'java com.naver.spring_study.DayCalculator 2025 01 01' 입력
//}

//원격 실행, 콘솔 출력.
//@Controller
//public class DayCalculator
//{
//	@RequestMapping("/dayCalculator") //http://localhost:8080/spring_study/dayCalculator?year=2025&month=2&day=5
//	public void main(HttpServletRequest request)
//	{
//		//1) 입력
//		String sYear = request.getParameter("year");
//		String sMonth = request.getParameter("month");
//		String sDay = request.getParameter("day");
//		
//		//2) 작업
//		int iYear = Integer.parseInt(sYear);
//		int iMonth = Integer.parseInt(sMonth);
//		int iDay = Integer.parseInt(sDay);
//		
//		Calendar cCal = Calendar.getInstance();
//		cCal.set(iYear, iMonth-1, iDay);
//		
//		int iDayOfWeek = cCal.get(Calendar.DAY_OF_WEEK);
//		char cDayOfWeek = "일월화수목금토".charAt(iDayOfWeek-1);
//		
//		//3) 출력
//		System.out.println(sYear + "년 " + sMonth + "월 " + sDay + "일은 " + cDayOfWeek + "요일 입니다.");
//	}
//}

//원격 실행, 브라우저 출력.
//@Controller
//public class DayCalculator
//{
//	@RequestMapping("/dayCalculator") //http://localhost:8080/spring_study/dayCalculator?year=2025&month=2&day=5
//	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException
//	{
//		//1) 입력
//		String sYear = request.getParameter("year");
//		String sMonth = request.getParameter("month");
//		String sDay = request.getParameter("day");
//		
//		//2) 작업
//		int iYear = Integer.parseInt(sYear);
//		int iMonth = Integer.parseInt(sMonth);
//		int iDay = Integer.parseInt(sDay);
//		
//		Calendar cCal = Calendar.getInstance();
//		cCal.set(iYear, iMonth-1, iDay);
//		
//		int iDayOfWeek = cCal.get(Calendar.DAY_OF_WEEK);
//		char cDayOfWeek = "일월화수목금토".charAt(iDayOfWeek-1);
//		
//		//3) 출력
//		response.setContentType("text/html");
//		response.setCharacterEncoding("utf-8");	//없으면 한글 깨짐(2025? 2? 6?? ??? ???.)
//		PrintWriter out = response.getWriter();	//브라우저로의 출력 스트림
//		out.println(sYear + "년 " + sMonth + "월 " + sDay + "일은 " + cDayOfWeek + "요일 입니다.");
//	}
//}

//관심사 분리(MVC 패턴 적용)
@Controller
public class DayCalculator
{
	@RequestMapping("/dayCalculator") //http://localhost:8080/spring_study/dayCalculator?iYear=2025&iMonth=2&iDay=5
	public String main(int iYear, int iMonth, int iDay, HttpServletResponse response) throws IOException
	{
		Calendar cCal = Calendar.getInstance();
		cCal.set(iYear, iMonth-1, iDay);
		
		int iDayOfWeek = cCal.get(Calendar.DAY_OF_WEEK);
		char cDayOfWeek = "일월화수목금토".charAt(iDayOfWeek-1);
		
		//3) 출력
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");	//없으면 한글 깨짐(2025? 2? 6?? ??? ???.)
		PrintWriter out = response.getWriter();	//브라우저로의 출력 스트림
		out.println(iYear + "년 " + iMonth + "월 " + iDay + "일은 " + cDayOfWeek + "요일 입니다.");
		
		//출력 jsp 소스 위치 : /spring_study/src/main/webapp/WEB-INF/views/
		return "dayOfWeek"; //dayOfWeek.jsp
	}
}
