package kr.or.ddit.controller.chapt05;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chapt05/ajax")
public class Chapter05AjaxFileController {

	/*
	 * 10. 파일업로드 Ajax 방식 요청 처리
	 * 
	 */
	// 비동기 파일 처리 페이지
	@GetMapping("registerFileForm")
	public String ajaxRegisterFileForm() {
		return "chapt05/ajaxRegisterFile";
	}
	
	// 파일을 비동기로 받기위한 목적지
	@PostMapping("/uploadFile")
	public ResponseEntity<String> uploadFile(
			MultipartFile file){
		log.info("uploadFile()실행!");
		String originalName = file.getOriginalFilename();
		log.info("파일명 : {}",originalName);
		return new ResponseEntity<String>("UPLOAD SUCCESS",HttpStatus.OK);
	}
}
