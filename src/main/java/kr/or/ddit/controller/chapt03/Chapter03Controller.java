package kr.or.ddit.controller.chapt03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/chapt03")
public class Chapter03Controller {

	/*
		[ 3장. 컨트로러 요청 매핑 ]
		
		1. 요청 경로 매핑
		
			- @RequestMapping의 value 속성에 요청 경로를 설정한다.
			- 요청 경로는 반드시 설정해야 하는 필수 정보이다.
			- 속성이 하나일 때는 속성명을 생략할 수 있다. (default는 value)
			- 컨트롤러의 클래스 레벨과 메소드 레벨로 지정할 수 있다.
			- 클래스 레벨로 요청 경로를 지정하면 메소드 레벨에서 지정한 경로의 기본 경로로 취급된다.
			- 클래스 레벨의 요청 경로에 메소드 레벨의 요청 경로를 덧붙인 형태가 최종 경로가 된다.
	 */
	private static final Logger log = LoggerFactory.getLogger(Chapter03Controller.class);
	
	// 요청 경로에 포함된 URI의 값을 필터로 폴더 하위에 jsp를 찾아 결과 화면을 만든다.
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void registerForm() {
		log.info("registerForm() 실행...!");
		
	}
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void list() {
		log.info("list() 실행...!");
		
	}
	/*
		2. 요청 경로 매핑
		
			- 요청 경로를 동적으로 표현이 가능한 경로 패턴을 지정할 수 있다.
			- URL 경로 상의 변하는 값을 경로 변수로 취급한다.
			- 경로 변수에 해당하는 값을 파라미터 변수에 설정할 수 있다.
	 */
	
	// @PathVariable 어노테이션을 활용하여 요청 경로 상의 포함된 파라미터를 받을 수 있다.
	@RequestMapping(value="/read/{boardNo}", method=RequestMethod.GET)
	public String read(@PathVariable int boardNo) {
		log.info("read() 실행..!");
		log.info("경로 상의 포함된 boardNo : " + boardNo);
		return "chapt03/read";
	}
	
	/*
		3. HTTP 메소드 매핑
		
			- method 속성을 사용하여 HTTP 메소드 매핑 조건으로 지정할 수 있다.
			- 화면으로 응답하는 경우에는 HTTP 메소드로 GET 방식과 POST 방식 두 가지를 사용할 수 있다.
	 */
	
	@RequestMapping(value="/formHome", method=RequestMethod.GET)
	public String formHome() {
		log.info("formHome()실행..!");
		return "formHome";
	}
	
	// register 경로에 GET 방식
	@RequestMapping(value="/http/register", method=RequestMethod.GET)
	public String registerFormHttp() {
		log.info("registerFormHttp() 실행..!");
		return "success";
	}
	// register 경로에 POST 방식
	@RequestMapping(value="/http/register", method=RequestMethod.POST)
	public String registerHttp() {
		log.info("registerHttp() 실행..!");
		return "success";
	}
	// modify 경로에 GET 방식
	@RequestMapping(value="/http/modify", method=RequestMethod.GET)
	public String modifyFormHttp() {
		log.info("modifyFormHttp() 실행..!");
		return "success";
	}
	// modify 경로에 POST 방식
	@RequestMapping(value="/http/modify", method=RequestMethod.POST)
	public String modifyHttp() {
		log.info("modifyHttp() 실행..!");
		return "success";
	}
	// remove 경로에 POST 방식
	@RequestMapping(value="/http/remove", method=RequestMethod.POST)
	public String removeHttp() {
		log.info("removeHttp() 실행..!");
		return "success";
	}
	// list 경로에 GET 방식
	@RequestMapping(value="/http/list", method=RequestMethod.GET)
	public String listHttp() {
		log.info("listHttp() 실행..!");
		return "success";
	}
	
}
