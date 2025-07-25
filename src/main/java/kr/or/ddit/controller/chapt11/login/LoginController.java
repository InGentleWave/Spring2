package kr.or.ddit.controller.chapt11.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@GetMapping("/login")
	public String loginForm(String error,String logout, Model model) {
		log.info("error : "+error);
		log.info("logout : "+logout);
		
		if(error != null) {
			model.addAttribute("error",error);
		}
		if(logout != null) {
			model.addAttribute("logout",logout);
		}
		
		return "chapt11/loginForm";
	}
	
	@GetMapping("/logout")
	public String logoutForm() {
		return "chapt11/logoutForm";
	}
}
