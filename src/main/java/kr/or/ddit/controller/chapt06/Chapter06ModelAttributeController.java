package kr.or.ddit.controller.chapt06;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chapt06/modelattribute")
public class Chapter06ModelAttributeController {
	
	/*
		3. @ModelAttribute 어노테이션
		
			- @ModelAttribute는 전달받은 매개변수를 강제로 Model에 담아서 전달하도록 할 때 필요한 어노테이션이다.
	 */
	@GetMapping("/form")
	public String registerForm() {
		log.info("registerForm()실행!");
		return "chapt06/modelAttributeForm";
	}
	
	// 1) 기본 자료형인 매개변수에 @ModelAttribute 어노테이션을 지정하여 데이터를 전달한다.
	@PostMapping("/register01")
	public String register01(
			@ModelAttribute("userId") String userId,
			@ModelAttribute("password") String password
			) {
		log.info("register01()실행!");
		log.info("userId : "+userId);
		log.info("password : "+password);
		
		return "chapt06/result";
		
	}
	
	// 2) 자바빈즈 규칙에 맞는 객체는 매개변수로 선언하면 기본적으로 전달한다.
	@PostMapping("/register02")
	public String register02(Member member) {
		log.info("register02()실행!");
		log.info("member.userId : "+member.getUserId());
		log.info("member.password : "+member.getPassword());
		
		return "chapt06/result";
		
	}
}
