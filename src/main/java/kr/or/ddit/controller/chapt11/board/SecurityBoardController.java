package kr.or.ddit.controller.chapt11.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/security/board")
public class SecurityBoardController {
	
	@Autowired
	private PasswordEncoder pe;
	
	@PostConstruct
	public void init() {
		log.info("########################## : " + pe.encode("1234") );
		log.info("########################## : " + pe.encode("1234") );
		log.info("########################## : " + pe.encode("1234") );
		log.info("########################## : " + pe.encode("1234") );
		log.info("########################## : " + pe.encode("1234") );
		log.info("########################## : " + pe.encode("1234") );
		log.info("########################## : " + pe.encode("1234") );
		log.info("########################## : " + pe.encode("1234") );
		log.info("########################## : " + pe.encode("1234") );
		log.info("########################## : " + pe.encode("1234") );
		log.info("########################## : " + pe.encode("1234") );
	}
	
	// board 게시판의 목록 화면
	@GetMapping("/list")
	public String boardList() {
		log.info("boardList()실행!");
		return "chapt11/board/list";
	}

	// board 게시판의 등록 화면
	@GetMapping("/register")
	public String boardRegister() {
		log.info("boardRegister()실행!");
		return "chapt11/board/register";
	}
}
