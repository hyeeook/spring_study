package com.naver.spring_study;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	@RequestMapping("/dayCalculator") //http://localhost:8080/spring_study/dayCalculator?year=2025&month=2&day=5
	public ModelAndView main(int year, int month, int day) throws IOException
	{
		ModelAndView mv = new ModelAndView();
		
		char dayOfWeek = this.getDayOfWeek(year, month, day);
		
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", day);
		mv.addObject("dayOfWeek", dayOfWeek);
		mv.setViewName("dayOfWeek"); //view 지정
		
		return mv;
	}
	
//	@RequestMapping("/dayCalculator") //http://localhost:8080/spring_study/dayCalculator?year=2025&month=2&day=5
//	public void main(int year, int month, int day, Model model) throws IOException
//	{
//		char dayOfWeek = this.getDayOfWeek(year, month, day);
//		
//		model.addAttribute("year", year);
//		model.addAttribute("month", month);
//		model.addAttribute("day", day);
//		model.addAttribute("dayOfWeek", dayOfWeek);
//		
//		//자동으로 매핑된 URL의 끝단어 "dayCalculator"로 view를 view로 지정함 
//	}
	
//	@RequestMapping("/dayCalculator") //http://localhost:8080/spring_study/dayCalculator?year=2025&month=2&day=5
//	public String main(int year, int month, int day, Model model) throws IOException
//	{
//		if(!isValid(year, month, day))
//		{
//			return "dayOfWeekError";
//		}
//		
//		char dayOfWeek = this.getDayOfWeek(year, month, day);
//		
//		model.addAttribute("year", year);
//		model.addAttribute("month", month);
//		model.addAttribute("day", day);
//		model.addAttribute("dayOfWeek", dayOfWeek);
//		
//		return "dayOfWeek"; //출력 jsp 소스 위치 : /spring_study/src/main/webapp/WEB-INF/views/dayOfWeek.jsp
//		//view의 상세 경로를 설정하는 파일 : /spring_study/src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml
//	}
	
	private boolean isValid(int year, int month, int day)
	{
		final int START_DAY = 1;
		int endDay = 0;
		
		//년 유효성 체크
		if(year < 0)
		{
			return false;
		}
		
		//월 유효성 체크
		if(!(1<=month && month<=12))
		{
			return false;
		}
		
		//일 세팅
		switch(month)
		{
			case 1 :
			case 3 :
			case 5 :
			case 7 :
			case 8 :
			case 10 :
			case 12 : 
				endDay = 31;
				break;
			case 4 :
			case 6 :
			case 9 :
			case 11 :
				endDay = 30;
				break;
			default :
				endDay = 28;
				break;
		}
		
		//일 세팅(윤년)
		if(month == 2)
		{
			if(year%4 == 0)
			{
				if(year%100 != 0 || year%400 == 0)
				{
					endDay = 29;
				}
			}
		}
		
		//일 유효성 체크
		if(!(START_DAY<=day && day<=endDay))
		{
			return false;
		}
		
		return true;
	}
	
	private char getDayOfWeek(int year, int month, int day)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		return "일월화수목금토".charAt(cal.get(Calendar.DAY_OF_WEEK)-1);
	}
}
