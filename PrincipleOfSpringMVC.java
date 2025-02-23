package com.naver.spring_study;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class PrincipleOfSpringMVC
{
	//Spring MVC의 DispatcherServlet 역할
	public static void main(String[] args) throws FileNotFoundException
	{
		HashMap<String, String> hashMap = new HashMap<>();
		
		System.out.println("before : " + hashMap);
		
		ModelController modelController = new ModelController();
		String viewName = modelController.main(hashMap);
		
		System.out.println("after : " + hashMap);
		
		render(hashMap, viewName);
	}
	
	//Spring MVC에서 view로 전달된 값을 적용하여 출력하는 역할
	static void render(HashMap<String, String> hashMap, String viewName)
	{
		String result = "";
		Scanner scanner = null;
		
		try
		{
			scanner = new Scanner(new File(viewName + ".txt"));	//텍스트 파일을 한 줄씩 읽어서 하나의 문자열로 만들기
			
			while(scanner.hasNextLine())
			{
				result += scanner.nextLine() + System.lineSeparator();	//파일에서 현재 라인의 문자열 + 현재 운영 체제에 맞는 개행문자
			}
			
			Iterator<String> iterator = hashMap.keySet().iterator();
			
			while(iterator.hasNext())
			{
				String key = iterator.next();						//지네릭 클래스를 사용했으므로 형변환 불필요
				result = result.replace("${" + key + "}", hashMap.get(key));	//지네릭 클래스를 사용했으므로 형변환 불필요
			}
			
			System.out.println(result);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("파일을 찾을 수 없습니다.");
		}
	}
}

class ModelController
{
	//Spring MVC의 Controller 역할
	public String main(HashMap<String, String> hashMap)
	{
		hashMap.put("ID", "91326146");
		hashMap.put("PW", "New1234!");
		
		return "txtView1";
	}
}

//프로젝트 바로 밑에 텍스트 파일 만들기

//[txtView1.txt]
//ID=${ID}, PW=${PW}

//[txtView2.txt]
//ID:${ID}
//PW:${PW}
