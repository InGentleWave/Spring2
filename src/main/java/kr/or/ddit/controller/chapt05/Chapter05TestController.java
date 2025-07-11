package kr.or.ddit.controller.chapt05;

import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/chapt05/test02")
public class Chapter05TestController {

	// 비동기 이벤트로 파일 데이터 전송 후, 결과 출력을 위한 테스트 페이지
	@GetMapping("/ajaxForm")
	public String testAjaxForm() {
		return "chapt05/test02/ajaxForm";
	}
	
	// 위 테스트 페이지에서 비동기 요청 시, 목적지로 사용할 메서드
	// 리턴 반환타입, 요청을 받을 메서드 방식은 자유롭게 설정
	// 요청 URL : /chapt05/test02/upload 로 목적지 설정
	@PostMapping("/upload")
	public ResponseEntity<List<String>> testUpload(
			MultipartFile file) {
		log.info("testUpload()실행!");
//		전송한 파일명, 크기, ContentType 출력
		String originalName = file.getOriginalFilename();
		String fileSize = file.getSize()+"";
		String fileContentType = file.getContentType();
		log.info("파일명 : "+originalName);
		log.info("파일 크기: "+fileSize);
		log.info("ContentType : "+fileContentType);
		List<String> result = new ArrayList<>();
		result.add("UPLOAD SUCCESS");
		result.add(originalName);
		result.add(fileSize);
		result.add(fileContentType);
		return new ResponseEntity<List<String>>(result,HttpStatus.OK);
	}
}
