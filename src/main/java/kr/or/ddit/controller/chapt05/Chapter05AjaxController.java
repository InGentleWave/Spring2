package kr.or.ddit.controller.chapt05;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chapt05/ajax")
public class Chapter05AjaxController {
	
	/*
	 * 	9. Ajax 방식 요청 처리
	 */
	
	// Ajax 방식 요청 처리 페이지
	@GetMapping("/registerForm")
	public String ajaxRegisterForm() {
		log.info("ajaxRegisterForm()실행!");
		return "chapt05/ajaxRegisterForm";
	}
	
	// 1) 객체 타입의 JSON 요청 데이터 @RequetBody 어노테이션을 지정하여 자바빈즈 매개변수로 처리한다.
	@ResponseBody
	@PostMapping("/register01")
	public ResponseEntity<String> ajaxRegister01(@RequestBody Member member){
		log.info("ajaxRegister01()실행!");
		log.info("member.userId : "+member.getUserId());
		log.info("member.password : "+member.getPassword());
		return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		
	}
	
	@ResponseBody
	@PostMapping("/register02")
	public ResponseEntity<String> ajaxRegister02(
			@RequestBody Map<String, String> param){
		// 요청 본문에서 데이터가 바인딩 되지 않아 userId 및 password가 출력되지 않는다.
		// 비동기 요청인 경우, 서버에서 기본 데이터 타입으로는 데이터를 받을 수 없다. (물론 보낼때 key 없이 데이터만 보내면 받을 수 있지만
		// 보내는 형식이 다르기 때문에 권장하지 않음)
		// 기본적으로 기본 데이터 타입으로 데이터를 받을 시, userId : {"userId" : "a001", "password" : "1234"} 형태의 문자열이
		// userId 변수에 저장되어 들어온다. 그러헥 되면 객체안에 있는 해당 각각의 key와 일치하는 값의 정보를 추출할 수 없으므로 사용할 수 없다.
		// 이때, 단일 요청 파라미터를 받으려면 Collection Map의 형태로 하되, @RequestBody 어노테이션을 붙여 사용합니다.
		// 또는 객체로 데이터를 받을 수있습니다.
		log.info("ajaxRegister02()실행!");
		log.info("userId : "+param.get("userId"));
		log.info("password : "+param.get("password"));
		return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		
	}
	@ResponseBody
	@PostMapping("/register03")
	public ResponseEntity<String> ajaxRegister03(
			String userId,@RequestBody Map<String,String> param){
		// 요청에 포함된 header 영역에 설정된 파라미터는 기본 데이터 타입으로 데이터를 바인딩할 수 있다.
		// 동기 방식의 요청처럼 기본 데이터 타입으로 데이터 바인딩 가능
		// 하지만, password는 요청 본문에 설정되서 들어오면서 비동기 요청에 의해서 들어오므로
		// 기본 데이터 타입 또는 @RequestBody 어노테이션이 설정된 기본 데이터타입이라 할지라도
		// 데이터 바인딩이 이뤄지지 않는다.
		// 해당 데이터를 받기 위해서는 2번째에서 배웠던 내용처럼 Map 또는 객체형태로 데이터를 받을 수 있다. (@RequestBody 어노테이션 필요)
		log.info("ajaxRegister03()실행!");
		log.info("userId : "+userId);
		log.info("password : "+param.get("password"));
		return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		
	}
	@ResponseBody
	@PostMapping("/register04")
	public ResponseEntity<String> ajaxRegister04(
			@RequestBody List<Member> memberList){
		// 요청에 포함된 header 영역에 설정된 파라미터는 기본 데이터 타입으로 데이터를 바인딩할 수 있다.
		// 동기 방식의 요청처럼 기본 데이터 타입으로 데이터 바인딩 가능
		// 하지만, password는 요청 본문에 설정되서 들어오면서 비동기 요청에 의해서 들어오므로
		// 기본 데이터 타입 또는 @RequestBody 어노테이션이 설정된 기본 데이터타입이라 할지라도
		// 데이터 바인딩이 이뤄지지 않는다.
		// 해당 데이터를 받기 위해서는 2번째에서 배웠던 내용처럼 Map 또는 객체형태로 데이터를 받을 수 있다. (@RequestBody 어노테이션 필요)
		log.info("ajaxRegister04()실행!");
		for(Member member:memberList) {
			log.info("member.userId : "+member.getUserId());
			log.info("member.password : "+member.getPassword());
		}
		return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		
	}
	@ResponseBody
	@PostMapping("/register05")
	public ResponseEntity<String> ajaxRegister05(
			@RequestBody Member member){
		log.info("ajaxRegister05()실행!");
		log.info("member.userId : "+member.getUserId());
		log.info("member.password : "+member.getPassword());
		Address address = member.getAddress();
		if( address != null ) {
			log.info("postCode : "+address.getPostCode());
			log.info("location : "+address.getLocation());
		}
		
		return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		
	}
	@ResponseBody
	@PostMapping("/register06")
	public ResponseEntity<String> ajaxRegister06(
			@RequestBody Member member){
		// Member객체 안에 있는 Card 객체의 validMonth 필드와 같은 Date 타입의 데이터를 직접적으로 받아내는거 자체가 제한이 있으므로,
		// 날짜와 같은 데이터는 String과 같은 문자열로 받고 DB나 서비스로직에서 날짜 데이터로 치환해서 사용한다.
		log.info("ajaxRegister06()실행!");
		log.info("member.userId : "+member.getUserId());
		log.info("member.password : "+member.getPassword());
		List<Card> cardList = member.getCardList();
		if( cardList != null ) {
			for(Card card : cardList) {
				log.info("no : "+card.getNo());
				log.info("validMonth : "+card.getValidMonth());
			}
		}
		
		return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		
	}
}
