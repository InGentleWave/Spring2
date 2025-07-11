package kr.or.ddit.controller.chapt06;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chapt06/redirectattribute")
public class Chapter06RedirectAttributeController {

	/*
		4. RedirectAttribute 타입
		
			- RedirectAttribute는 일회성으로 데이터를 전달하는 용도로 사용한다.
	 */
	
	// 데이터를 전달하기 위한 페이지
	@GetMapping("/registerForm")
	public String registerForm() {
		log.info("registerForm()실행");
		return "chapt06/redirectAttributeForm";
	}
	
	@PostMapping("/register")
	public String registerForm(Member member, RedirectAttributes ra) {
		log.info("registerForm()실행!");
		ra.addFlashAttribute("msg", "success");
		
		return "redirect:/chapt06/redirectattribute/result"; 
	}
	
	@GetMapping("/result")
	public String result() {
		log.info("result()실행!");
		return "chapt06/result";
	}
	
	
}
