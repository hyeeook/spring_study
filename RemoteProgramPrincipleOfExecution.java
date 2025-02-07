package com.naver.spring_study;

import java.lang.reflect.Method;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //원격 호출 가능한 프로그램으로 등록
public class RemoteProgramPrincipleOfExecution //원격 프로그램의 실행 원리
{
	@RequestMapping("/hello") //URL과 메서드 연결
	private void main()
	{
		/* 1) 스태틱 메서드가 아닌데 어떻게 호출될 수 있는가?
		 * 		> WAS 내부에서 객체를 생성함.
		 * 2) 스태틱 메서드가 아닌 인스턴스 메서드를 사용하는 이유?
		 * 		> 인스턴스 메서드는 인스턴스 변수와 스태틱 변수를 모두 사용 가능.
		 * 		> 스태틱 메서드는 스태틱 변수만 사용 가능.
		 * 		> 가능하면 인스턴스 메서드로 사용하는 것이 유리함.
		 * 		> 스태틱 메서드여도 객체 생성은 된 상태에서 호출되는 것.
		 * 3) 접근제어자가 'public'이 아닌 'private'이어도 호출이 가능한가?
		 * 		> '@RequestMapping'으로 URL이 연결된 메서드라는 것은 외부에서 호출 가능하게 하겠다는 것이므로 접근제어자와 관계 없이 호출할 수 있음.
		 * 		> 그러나 프로그램 내부에서는 'private'의 속성(타 클래스에서 호출 불가 등)을 가지게 됨.
		 * 4) 1, 3번이 가능한 이유?
		 * 		> 'Reflection API'를 사용하기 때문에 가능함.
		 * 		> java.lang.reflect 패키지(클래스 정보를 얻고 다룰 수 있는 강력한 기능) 제공.
		 **/
		System.out.println("Hello");
	}
}

class UsingReflectionAPI //'Reflection API'를 사용하여 'private'인 인스턴스 메서드 호출
{
	void main() throws Exception
	{
		Class<?> remoteProgramPrincipleOfExecutionClass = Class.forName("com.naver.spring_study.RemoteProgramPrincipleOfExecution"); //클래스 정보 가져오기
		RemoteProgramPrincipleOfExecution remoteProgramPrincipleOfExecution = (RemoteProgramPrincipleOfExecution)remoteProgramPrincipleOfExecutionClass.newInstance(); //가져온 클래스 정보로 객체 생성
		Method main = remoteProgramPrincipleOfExecutionClass.getDeclaredMethod("main"); //가져온 클래스 정보로 'main'이라는 이름의 메서드 정보 가져오기
		main.setAccessible(true); //'private'인 main() 메서드를 호출 가능하게 설정
		
		main.invoke(remoteProgramPrincipleOfExecution); //RemoteProgramPrincipleOfExecution.main()
	}
}
