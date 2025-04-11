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
- STS에서 컴파일 옵션을 추가하는 방법
  > 1) Window > Preferences > Java > Compiler 오픈
  > 2) Compiler compliance level을 1.8 이상으로 변경
  > 3) Store information about method parameters(usable via reflection) 체크
- 프로젝트의 자바 버전 변경하는 방법
  > 1) pom.xml 오픈 > properties에서 java-version을 원하는 버전으로 수정
  > 2) plugins에서 maven-compiler-plugin의 source, target도 동일한 버전으로 수정
  > 3) 해당 프로젝트 우클릭 > Maven > Update Project...(pom.xml 수정 시에 반드시 수행해야 변경사항이 적용됨)

---

### Maven Dependencies에서 특정 라이브러리의 부재로 인한 에러 발생 시, 해결 방법
- 방법(1) pom.xml 파일 변경
- 방법(2) Tomcat의 라이브러리 변경
  > 1) Package Explorer에서 프로젝트 우클릭
  > 2) Build Path > Configure Build Path... 클릭
  > 3) Libraries 탭 클릭
  > 4) Class Path > Add Library... 클릭
  > 5) Server Runtime > 추가할 라이브러리 선택
  > 6) Package Explorer에서 선택한 라이브러리가 표시됨(선택한 라이브러리가 import 된 상태)

---

### 소스 파일 한글 깨짐 현상 조치
1) Window > Preferences > Web > HTML Files > Encoding : ISO 10646/Unicode(UTF-8) 변경
2) HTML 파일 외에도 한글이 깨지는 현상이 발생하면 해당 파일의 Encoding을 UTF-8로 변경

---

### 의존 모듈 충돌로 오류 발생 시 조치
1) 'C:\Users\hyeeo\.m2' 이동
2) repository 폴더 삭제('.m2' 폴더는 절대 삭제하면 안 됨)
3) STS 실행
4) 해당 프로젝트 우클릭 > Maven > Update Project...
5) 'C:\Users\hyeeo\.m2' 이동하면 삭제했던 repository 폴더가 다시 생성되어 있음

'C:\Users\hyeeo\.m2' 경로는 Maven이 저장소로 사용. 스프링 개발 시에 알 수 없는 오류들이 발생하는데, 대부분 의존 모듈의 충돌로 발생하므로 본 조치를 수행해 볼 필요가 있음.<br>
spring-web-5.0.7.RELEASE.jar : 클래스 파일<br>
spring-web-5.0.7.RELEASE-sources.jar : 소스 파일

---
