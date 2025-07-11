package kr.or.ddit.controller.chapt07;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chapt07/jsp")
public class Chapter07JSPController {

	/*
		[ 7장. JSP ]
		
		1. 커스텀 태그 라이브러리
		
			- 스크립트 요소가 많아지면 많아질수록 JSP 코드는 복잡해집니다.
				이 문제를 해결하는 한 가지 방법은 커스텀 태그를 사용하는 것 입니다.
				커스텀 태그를 사용하면 뷰를 표시하기 위한 로직을 공통화하거나,
				표현하기 복잡한 로직을 캡슐화 할 수 있어서 JSP의 구현 코드를
				간결하게 만들 수 있습니다.
				그리고, 커스텀 태그를 모아 놓은 것을 커스텀 태그 라이브러리라고 합니다.
				
				# 대표적인 태그 라이브러리
				- JSTL(JavaServer Pages Standard Tag Library)
				- Spring-form JSP Tag Library
				- Spring JSP Tag Library
				- Spring Security JSP Tag Library
				...
		
		2. 표현언어(EL)
		
			- JSP는 EL(Expression Language)이라는 표현 언어를 사용해 값의 참조, 출력, 연산을 할 수 있다.
			- EL식은  ${...} 또는 #{...} 형식으로 작성합니다.
			
			# EL을 사용하여 객체를 조회하는 방법은 다음과 같습니다.
			- 자바빈즈 프로퍼티를 조회핳는 경우 "속성명.프로퍼티명"을 지정합니다. (실제 프로퍼티가 아닌 getter를 기반으로 설정됨)
			- List나 배열 요소를 조회하는 경우 "속성명[요소위치]"를 지정합니다.
			- Map 요소를 조회하는 경우 "속성명.프로퍼티명" 또는 "속성명[프로퍼티명]"을 지정합니다.
			
			# 사용 가능한 연산자
			- EL에서는 다음과 같은 연산자를 사용할 수 있다.
			
			# 산술 연산자
			+ 더하기, - 빼기, * 곱하기, /(div) 나누기, %(mod) 나머지
			
			# 비교 연산자
			==(eq) 같은 값인지 비교
			!=(eq) 다른 값인지 비교
			<=(le) 왼쪽이 작거나 같은 값인지 비교
			>=(ge) 왼쪽이 크거나 같은 값인지 비교
			<(lt) 왼쪽이 작은 값인지 비교
			>(gt) 왼쪽이 큰 값인지 비교
			
			# empty 연산자
			- null이거나 공백(문자열의 경우 공백 문자)인지 비교
			
			[true 조건 :::] null값, 빈 문자열(""), 길이가 0인 배열, 빈 Collection
			
			# 논리 연산자
			&&(and) 두 피연산자 모두 true이면 boolean true를 반환하고, 그렇지 않으면 false를 반환한다.
			||(or) 두 피연산자 중 하나 또는 모두 true이면 boolean true를 반환하고, 그렇지 않으면 false를 반환한다.
			!(not) 해당 피연산자의 의미를 반대로 바꾼다.
	 */
	// 1) 자바빈즈 프로퍼티를 조회하는 경우 '속성명.프로퍼티명' 또는 '속성명[키명]'을 지정한다
	@GetMapping("/home0101")
	public String home0101(Model model) {
		log.info("home0101()실행!");
		Map<String, String> memberMap = new HashMap();
		memberMap.put("userId", "hongkd");
		memberMap.put("password", "1122");
		memberMap.put("email", "aa123@cc.com");
		memberMap.put("userName", "홍길동");
		
		model.addAttribute("memberMap",memberMap);
		return "chapt07/home0101";
		
	}
	
	// 2) 산술 연산자 사용
	@GetMapping("/home0201")
	public String home0201(Model model) {
		log.info("home0201()실행!");
		int coin = 100;
		model.addAttribute("coin",coin);
		return "chapt07/home0201";
	}
	// 3) 비교 연산자 사용
	@GetMapping("/home0202")
	public String home0202(Model model) {
		log.info("home0202()실행!");
		int coin = 1000;
		String userId = "hongkd";
		model.addAttribute("coin",coin);
		model.addAttribute("userId",userId);
		return "chapt07/home0202";
	}
	// 4) empty 연산자 사용
	@GetMapping("/home0301")
	public String home0301(Model model) {
		log.info("home0301()실행!");
		Member member = new Member();
		model.addAttribute("member",member);
		return "chapt07/home0301";
	}
	// 5) 논리 연산자 사용
	@GetMapping("/home0401")
	public String home0401(Model model) {
		log.info("home0401()실행!");
		int coin = 1000;
		String userId = "hongkd";
		Member member = new Member();
		
		model.addAttribute("coin",coin);
		model.addAttribute("userId",userId);
		model.addAttribute("member",member);
		
		return "chapt07/home0401";
	}
}
