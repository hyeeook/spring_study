## STS 툴 사용법

### STS에서 매개변수를 받을 수 있도록 콘솔로 Java 소스 실행하는 방법
1) 프로젝트 폴더 하위에 'target' 폴더(여기에 컴파일 된 class 파일이 생성됨) 우클릭
2) 위에서 네번째 'Show In' 클릭
3) 맨 위의 'Terminal' 클릭
4) 'cd classes' 입력
5) 'java com.naver.spring_study.DayCalculator 2025 01 01' 입력

```java
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

---

### Reflection API 사용 시에 매개변수의 이름까지 가져오는 방법
- 매개변수의 이름이 arg0으로 나오는데, 이유는 매개변수의 타입은 중요하지만 이름은 중요하지 않기 때문
- 매개변수의 이름까지 나오게 하려면 컴파일 옵션에 -parameters 옵션(javac -parameters)을 추가해야 함(JDK 1.8부터 추가)
- STS에서 컴파일 옵션을 추가하는 방법 :
  > 1) Window > Preferences > Java > Compiler 오픈
  > 2) Compiler compliance level을 1.8 이상으로 변경
  > 3) Store information about method parameters(usable via reflection) 체크
- 프로젝트의 자바 버전 변경하는 방법 :
  > 1) pom.xml 오픈 > properties에서 java-version을 원하는 버전으로 수정
  > 2) plugins에서 maven-compiler-plugin의 source, target도 동일한 버전으로 수정
  > 3) 해당 프로젝트 우클릭 > Maven > Update Project...(pom.xml 수정 시에 반드시 수행해야 변경사항이 적용됨)
