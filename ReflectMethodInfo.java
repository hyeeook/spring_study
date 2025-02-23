package com.naver.spring_study;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReflectMethodInfo
{
	@RequestMapping("/reflectMethodInfo")
	public void main() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		Class<?> dayCalculatorClass = Class.forName("com.naver.spring_study.DayCalculator");		//클래스 정보 가져오기
		Constructor<?>[] dayCalculatorConstructors = dayCalculatorClass.getDeclaredConstructors();	//생성자 정보 가져오기
		
		//반복문 돌려가며 필요한 생성자 찾기
		for(Constructor<?> dayCalculatorConstructor : dayCalculatorConstructors)
		{
			Class<?>[] constructorParameterTypes = dayCalculatorConstructor.getParameterTypes();	//생성자의 파라미터 타입을 배열로 가져오기
			
			if(constructorParameterTypes.length == 0)	//기본 생성자인 경우
			{
				Object dayCalculatorObject = dayCalculatorConstructor.newInstance();	//기본 생성자로 객체 생성하기
			}
		}
		
		//Method dayCalculatorMainMethod = dayCalculatorClass.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);	//메서드 이름과 파라미터 타입으로 메서드 정보 가져오기
		Method[] dayCalculatorMethods  = dayCalculatorClass.getDeclaredMethods();	//선언된 메서드 정보를 배열로 가져오기
		
		//반복문 돌려가며 필요한 메서드 찾기
		for(Method dayCalculatorMethod : dayCalculatorMethods)
		{
			String methodName = dayCalculatorMethod.getName();							//메서드의 이름 가져오기
			Parameter[] methodParameters = dayCalculatorMethod.getParameters();			//메서드의 파라미터 목록 정보 배열로 가져오기
			Class<?> methodReturnType = dayCalculatorMethod.getReturnType();			//메서드의 리턴타입 가져오기
			//Class<?>[] methodParameterTypes = dayCalculatorMethod.getParameterTypes();	//메서드의 파라미터 타입을 배열로 가져오기
			
			StringJoiner methodParameterList = new StringJoiner(", ", "(", ")");		//메서드의 파라미터 목록을 저장할 변수
			
			//반복문 돌려가며 파라미터 정보 가져오기
			for(Parameter methodParameter : methodParameters)
			{
				String parameterName = methodParameter.getName();	//파라미터의 이름 가져오기
				Class<?> parameterType = methodParameter.getType();	//파라미터의 타입 가져오기
				
				methodParameterList.add(parameterType.getName() + " " + parameterName);	//"String param" 형식으로 파라미터 정보 저장
			}
			
			System.out.printf("%s %s%s%n", methodReturnType.getName(), methodName, methodParameterList);	//"String method(String param)" 형식으로 메서드 정보 출력
		}
	}
}

//[실행결과]
//java.lang.String main(int arg0, int arg1, int arg2, org.springframework.ui.Model arg3)
//boolean isValid(int arg0, int arg1, int arg2)
//char getDayOfWeek(int arg0, int arg1, int arg2)

//[매개변수 이름 출력]
//매개변수의 이름이 arg0으로 나오는데, 이유는 매개변수의 타입은 중요하지만 이름은 중요하지 않기 때문.
//매개변수의 이름까지 나오게 하려면 컴파일 옵션에 -parameters 옵션(javac -parameters)을 추가해야 함(JDK 1.8부터 추가).
//STS에서 컴파일 옵션을 추가하는 방법 :
//						Window > Preferences > Java > Compiler
//						> Compiler compliance level을 1.8 이상으로 변경
//						> Store information about method parameters(usable via reflection) 체크
//프로젝트의 자바 버전 변경하는 방법 :
//						pom.xml 오픈
//						> properties에서 java-version을 원하는 버전으로 수정
//						> plugins에서 maven-compiler-plugin의 source, target도 동일한 버전으로 수정
//						> 해당 프로젝트 우클릭 > Maven > Update Project...(pom.xml 수정 시에 반드시 수행해야 변경사항이 적용됨)
