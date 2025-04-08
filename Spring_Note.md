## STS에서 매개변수를 받을 수 있도록 콘솔로 Java 소스 실행하는 방법
1) 프로젝트 폴더 하위에 'target' 폴더(여기에 컴파일 된 class 파일이 생성됨) 우클릭
2) 위에서 네번째 'Show In' 클릭
3) 맨 위의 'Terminal' 클릭
4) 'cd classes' 입력
5) 'java com.naver.spring_study.DayCalculator 2025 01 01' 입력

ex)
```
public class DayCalculator
{
	public static void main(String[] args)
	{
		String sYear = args[0];
		String sMonth = args[1];
		String sDay = args[2];

		int iYear = Integer.parseInt(sYear);
		int iMonth = Integer.parseInt(sMonth);
		int iDay = Integer.parseInt(sDay);

		java.util.Calendar cCal = java.util.Calendar.getInstance();
		cCal.set(iYear, iMonth-1, iDay);

		int iDayOfWeek = cCal.get(java.util.Calendar.DAY_OF_WEEK);
		char cDayOfWeek = "일월화수목금토".charAt(iDayOfWeek-1);

		System.out.println(sYear + "년 " + sMonth + "월 " + sDay + "일은 " + cDayOfWeek + "요일 입니다.");
	}
}
```

