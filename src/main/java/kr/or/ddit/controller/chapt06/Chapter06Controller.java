package kr.or.ddit.controller.chapt06;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chapt06")
public class Chapter06Controller {

	/*
		[ 6장. 데이터 전달자 모델 ]
		
			1. 모델 객체
			
				- Model 객체는 컨트롤러(Controller)에서 뷰(View)로 데이터를 전달하는데 사용되는 인터페이스며,
					MVC 패턴의 'Model'을 담당한다.
				- Model을 통해 웹 애플리케이션 화면에 보여줄 데이터를 담는 역할을 한다.
				- Model은 Map의 형태와 비슷하며, key/value로 값을 설정할 수 있다.
					값을 설정하기 위해서는 addAttribute() 메소드를 활용한다.
			
			2. 모델을 통한 데이터 전달
				 
				- Model 객체를 통해서 다양한 데이터를 뷰(View)에 전달할 수 있다.
	 */
	
	// 1) 매개변수에 선언된 Model 객체의 addAttribute() 메서드를 호출하여 데이터를 뷰(View)에 전달한다.
	@GetMapping("/read01")
	public String read01(Model model) {
		log.info("read01()실행!");
		
		model.addAttribute("userId","hongkd");
		model.addAttribute("password","1234");
		model.addAttribute("email","aaa@ccc.com");
		model.addAttribute("userName","홍길동");
		model.addAttribute("birthDay","2025-07-09");
		return "chapt06/read01";
	}
	
	// 2) Model 객체에 자바빈즈 클래스 객체를 값으로만 전달할 때는 뷰에서 참조할 이름을 클래스명의 앞글자를 소문자로 변환하여 처리한다.
	@GetMapping("/read02")
	public String read02(Model model) {
		log.info("read02()실행!");
		
		Member member = new Member();
		member.setUserId("hongkd");
		member.setPassword("1122");
		member.setEmail("aa@cc.com");
		member.setUserName("홍길동");
		member.setBirthDay("2025-07-09");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,2025);
		cal.set(Calendar.MONTH,7);
		cal.set(Calendar.DAY_OF_MONTH,9);
		member.setDateOfBirth(cal.getTime());
		
		model.addAttribute(member);
		return "chapt06/read02";
	}
	
	// 3) Model 객체에 자바빈즈 클래스 객체를 특정한 이름을 지정하여 전달할 수 있다.
	@GetMapping("/read03")
	public String read03(Model model) {
		log.info("read03()실행!");
		
		Member member = new Member();
		member.setUserId("hongkd");
		member.setPassword("1122");
		member.setEmail("aa@cc.com");
		member.setUserName("홍길동");
		member.setBirthDay("2025-07-09");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,2025);
		cal.set(Calendar.MONTH,7);
		cal.set(Calendar.DAY_OF_MONTH,9);
		member.setDateOfBirth(cal.getTime());
		
		model.addAttribute("user",member);
		return "chapt06/read03";
	}
	
	// 4) Model 객체를 통해 다양한 타입의 값을 전달할 수 있다.
	@GetMapping("/read04")
	public String read04(Model model) {
		log.info("read04()실행!");
		
		Member member = new Member();
		member.setUserId("hongkd");
		member.setPassword("1122");
		member.setEmail("aa@cc.com");
		member.setUserName("홍길동");
		member.setBirthDay("2025-07-09");
		member.setGender("남자");
		member.setDeveloper("Y");
		member.setForeigner(true);
		member.setNationality("korea");
		member.setCars("BMW");
		
		String[] carArray = {"jeep","audi"};
		member.setCarArray(carArray);
		
		List<String> carList = new ArrayList<>();
		carList.add("bmw");
		carList.add("audi");
		member.setCarList(carList);
		
		member.setHobby("Movie, Music");
		
		String[] hobbyArray = {"Music","Movie"};
		member.setHobbyArray(hobbyArray);
		
		List<String> hobbyList = new ArrayList<>();
		hobbyList.add("Music");
		hobbyList.add("Movie");
		member.setHobbyList(hobbyList);
		
		List<Card> cardList = new ArrayList<>();
		Card card1 = new Card();
		card1.setNo("12345");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,2025);
		cal.set(Calendar.MONTH,7);
		cal.set(Calendar.DAY_OF_MONTH,9);
		card1.setValidMonth(cal.getTime());
		
		Card card2 = new Card();
		card2.setNo("12345");
		cal.set(Calendar.YEAR,2025);
		cal.set(Calendar.MONTH,6);
		cal.set(Calendar.DAY_OF_MONTH,10);
		card2.setValidMonth(cal.getTime());
		
		cardList.add(card1);
		cardList.add(card2);
		member.setCardList(cardList);
		
		Address address = new Address();
		address.setPostCode("0800098");
		address.setLocation("Daejeon");
		member.setAddress(address);
		
		member.setDateOfBirth(cal.getTime());
		member.setIntroduction("안녕하세요! 반갑습니다!");
		
		model.addAttribute("user",member);
		return "chapt06/read04";
	}
	
}
