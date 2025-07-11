package kr.or.ddit.controller.chapt05;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.FileMember;
import kr.or.ddit.vo.Member;
import kr.or.ddit.vo.MultiFileMember;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chapt05")
public class Chapter05Controller {
	/*
//	 * [ 5장. 컨트롤러 요청처리 ]
//	 * 
//	 * 1. 컨트롤러 메서드 매개변수
//	 * 
//	 * - Model : 이동 대상에 전달할 데이터를 가지고 있는 인터페이스 
//	 * - RedirectAttributes : 리다이렉트 대상에 전달할 데이터를 가지고 있는 인터페이스 
//	 * - 자바빈즈 클래스 : 요청 파라미터를 가지고 있는 자바빈즈 클래스 
//	 * - MultipartFile : 멀티파트 요청을 사용해 업로드 된 파일 정보를 가지고 있는 인터페이스 
//	 * - BindingResult : 도메인 클래스의 입력값 검증 결과를 가지고 있는 인터페이스 
//	 * - Locale : 클라이언트 Locale 
//	 * - Principal : 클라이언트 인증을 위한 사용자 정보를 가지고 있는 인터페이스
	 */
	public static Date strToDate(String dateStr) {
		DateTimeFormatter stdFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.parse(dateStr, stdFormatter);
		Date date = java.sql.Date.valueOf(localDate);
		return date;
	}
	public static String dateToStr(Date date) {
		LocalDate localDate = new java.sql.Date(date.getTime()).toLocalDate();
		DateTimeFormatter dtsFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		String dateStr = localDate.format(dtsFormatter);
		return dateStr;
	}
	
	// 요청 처리 페이지
	@GetMapping("/registerForm")
	public String registerForm() {
		log.info("registerForm()실행..!");
		return "chapt05/registerForm";
	}

	// 1) URL 경로 상의 쿼리 파라미터 정보로부터 요청 데이터를 취득할 수 있다.
	@GetMapping("/register")
	public String registerByParameter(String userId, String password) {
		log.info("registerByParameter()실행..!");
		log.debug("userId : {}, password : {}", userId, password);

		return "chapt05/success";
	}

	// 2) HTML Form 필드명과 컨트롤러 매개변수 명이 일치하면 요청 데이터를 취득할 수 있다.

	@PostMapping("/register01")
	public String register01(String userId, String password, int coin) {
		// 클라이언트에서 Form태그를 통해 전달바든 파라미터는 매개변수의 위치는 순서에 상관없고 오직 매개변수명이일치하면
		// 요청 데이터를 취득할 수 있다.
		// Form 필드 값 중, 받고 싶은 타입이 있다면 타입 선언 후 파라미터를 받되, 받는 타입과 일치하는 데이터인 경우 바인딩 할 수 있다.
		log.info("register01()실행..!");
		log.info("userId : " + userId);
		log.info("password : " + password);
		log.info("coin : " + coin);

		return "chapt05/success";
	}

	/*
//	  2. 요청 데이터 처리 어노테이션
//	  
//		  - @PathVariable : URL에서 경로 변수 값을 가져오기 위한 어노테이션 
//		  - @RequestParam : 요청 파라미터 값을 가져오기 위한 어노테이션 
//		  - @RequestHeader : 요청 헤더 값을 가져오기 위한 어노테이션 
//		  - @RequestBody : 요청 본문 내용을 가져오기 위한 어노테이션 
//		  - @CookieValue : 쿠키 값을 가져오기 위한 어노테이션
	 */

	// 1) URL 경로 상의 변수가 여러 개일 때, @PathVariable 어노테이션을 사용하여 특정한 경로 변수명을 지정해준다.
	@GetMapping("/register/{userId}/{coin}")
	public String registerByPath(@PathVariable String userId, @PathVariable int coin) {
		log.info("registerByPath()실행..!");
		log.info("userId : " + userId);
		log.info("coin : " + coin);

		return "chapt05/success";
	}

	// 2) @RequestParam 어노테이션을 사용하여 특정한 HTML Form의 필드명을 지정하여 요청을 처리한다.
	// 데이터를 받는 필드명은 동일하게 userId로 하되, 사용하는 변수명은 id로 달리 설정할 수 있다.
	// Spring1 Mybatis를 이용한 CRUD중, 페이징 처리 시 'page'를 서버에서 받을 때 아래와 같은 형식을 사용해 구현함.
	@PostMapping("/register0201")
	public String register0201(@RequestParam("userId") String id, @RequestParam("password") String pw, int coin) {
		log.info("register0201()실행..!");
		log.info("id : " + id);
		log.info("pw : " + pw);
		log.info("coin : " + coin);

		return "chapt05/success";
	}

	// 3) @RequestHeader 어노테이션을 사용하여 특정 header 정보의 값을 가져오거나 header 전체 정보를 가져올 수 있다.
	// 4) @CookieValue 어노테이션을 사용하여 Cookie에 저장된 값들 중 하나를 가져올 수 있다.
	@GetMapping("/register0202")
	public String register0202(@RequestHeader("Accept") String accept, @RequestHeader("User-Agent") String userAgent,
			@RequestHeader HttpHeaders headers, @CookieValue("JSESSIONID") String sessionId) {
		log.info("register0202실행..!");
		// 1. header안에 들어있는 특정 key로 구성된 헤더 값 출력
		log.info("-------------header 단일 데이터 출력----------------");
		log.info("accept : " + accept);
		log.info("userAgent : " + userAgent);

		log.info("-------------headers 정보 출력----------------");
		// 2. header 안에 담겨있는 모든 header 정보 출력
		Set<String> keys = headers.keySet();
		Iterator<String> ite = keys.iterator();
		while (ite.hasNext()) {
			String key = ite.next();
			log.info(key + " : " + headers.get(key));
		}

		log.info("-------------cookie 정보 출력----------------");
		log.info("sessionId : " + sessionId);

		return "chapt05/success";
	}

	/*
		3. 요청 처리 자바 빈즈
	*/
	// 1) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.

	@PostMapping("/beans/register01")
	public String registerJavaBeans01(Member member) {
		log.info("registerJavaBeans01()실행..!");
		log.info("member.getUserId() : " + member.getUserId());
		log.info("member.getPassword() : " + member.getPassword());
		log.info("member.getCoin() : " + member.getCoin());

		return "chapt05/success";
	}

	@PostMapping("/beans/register02")
	public String registerJavaBeans02(Member member, int coin) {
		log.info("registerJavaBeans02()실행..!");
		log.info("member.getUserId() : " + member.getUserId());
		log.info("member.getPassword() : " + member.getPassword());
		log.info("member.getCoin() : " + member.getCoin());
		log.info("coin : " + coin);

		return "chapt05/success";
	}

	/*
//	  4. Date 타입 처리
//	  
//	  		- 스프링 MVC는 Date 타입의 데이터를 처리하는 여러 방법을 제공합니다.
//	  
//	 		** 기본 데이터 타입 및 Member와 같은 자바빈즈 클래스 객체 안에 구성된 Date 타입 일지라도
//	 			'2025/07/04'와 같은 형식이 아니면 Date 타입으로 데이터를 바인딩 할 수 없다.
	 */
	// 1) 쿼리 파라미터(dateOfBirth=1234)로 전달받은 값은 Date 타입으로 데이터를 받을 수 있는가?</p>
	@GetMapping("/registerByGet01")
	public String registerByGet01(Date dateOfBirth) {
		log.info("registerByGet01()실행..!");
		log.info("dateOfBirth : " + dateOfBirth);
		return "chapt05/success";
	}

	// 2) 쿼리 파라미터(dateOfBirth=2025-07-04)로 전달받은 값은 Date 타입으로 데이터를 받을 수 있는가?</p>
	@PostMapping("/registerByGet02")
	public String registerByGet02(Member member) {
		log.info("registerByGet02()실행..!");
		log.info("member.dateOfBirth : " + member.getDateOfBirth());
		return "chapt05/success";
	}
	/*
//	  @DateTimeFormat 어노테이션
//	  	- @DateTimeFormat 어노테이션의 pattern 속성값에 원하는 날짜 형식을 지정할 수 있다.
	 */

	@PostMapping("/registerByGet03")
	public String registerByGet03(@DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth) {
		log.info("registerByGet03()실행..!");
		log.info("dateOfBirth : " + dateOfBirth);
		return "chapt05/success";
	}

	/*
//		6. 폼 방식 요청 처리
	 */
	// 1) 폼 텍스트 필드 요소값을 기본 자바빈즈 매개변수로 처리한다.
	@PostMapping("/registerMemberUserId")
	public String registerMemberUserId(Member member) {
		log.info("registerMemberUserId()실행..!");
		log.info("member.getUserId() : " + member.getUserId());
		return "chapt05/success";
	}

	// 2) 폼 비밀번호 필드 요소값을 기본 자바빈즈 매개변수로 처리한다.
	@PostMapping("/registerPassword")
	public String registerPassword(Member member) {
		log.info("registerPassword()실행..!");
		log.info("member.getPassword() : " + member.getPassword());
		return "chapt05/success";
	}

	// 3) 폼 라디오 필드 요소값을 기본 자바빈즈 매개변수로 처리한다.
	@PostMapping("/registerRadio")
	public String registerRadio(String gender) {
		log.info("registerRadio()실행..!");
		log.info("gender : " + gender);
		return "chapt05/success";
	}
	
	// 4) 폼 셀렉트 필드 요소값을 기본 자바빈즈 매개변수로 처리한다.
	@PostMapping("/registerSelect")
	public String registerSelect(String nationallity) {
		log.info("registerSelect()실행..!");
		log.info("nationallity : " + nationallity);
		return "chapt05/success";
	}
	
	// 5) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@PostMapping("/registerMultiSelect01")
	public String registerMultiSelect01(String cars) {
		// Multi 속성에 의해서 여러 데이터가 넘어올 때 기본 데이터 타입으로 데이터를 바인딩 시,
		// '값1,값2,값3...'의 형태처럼 ','를 기준으로 데이터가 하나의 문자열로 넘어온다.
		log.info("registerMultiSelect01()실행..!");
		log.info("cars : " + cars);
		return "chapt05/success";
	}
	// 6) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 문자열 배열 타입 매개변수로 처리한다.
	@PostMapping("/registerMultiSelect02")
	public String registerMultiSelect02(String[] carArray) {
		log.info("registerMultiSelect02()실행..!");
		if(carArray != null) {
			log.info("carArray.length : " + carArray.length);
			for(int i = 0;i<carArray.length;i++) {
				log.info("carArray["+i+"] : " + carArray[i]);
			}
		} else {
			log.info("carArray is null");
		}
		return "chapt05/success";
	}
	// 7) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 문자열 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.</p>
	@PostMapping("/registerMultiSelect03")
	public String registerMultiSelect03(@RequestParam ArrayList<String> carList) {
		// 일반적으로 Collection 타입의 데이터는 일반적인 선언으로는 값을 가져올 수 없다.
		// Collection 타입의 데이터를 받아오기 위해서는 @RequestParam 어노테이션을 설정한다.
		log.info("registerMultiSelect03()실행..!");
		if(carList != null) {
			log.info("carList.size() : " + carList.size());
			for(int i = 0;i<carList.size();i++) {
				log.info("carList.get("+i+") : " + carList.get(i));
			}
		} else {
			log.info("carList is null");
		}
		return "chapt05/success";
	}
	
	// 8) 폼 체크박스 요소값을 문자열 타입 매개변수로 처리한다.
	@PostMapping("/registerCheckbox01")
	public String registerCheckbox01(String hobby) {
		// Multi 속성에 의해서 여러 데이터가 넘어올 때 기본 데이터 타입으로 데이터를 바인딩 시,
		// '값1,값2,값3...'의 형태처럼 ','를 기준으로 데이터가 하나의 문자열로 넘어온다.
		log.info("registerCheckbox01()실행..!");
		log.info("hobby : " + hobby);
		return "chapt05/success";
	}
	
	// 9) 폼 체크박스 요소값을 문자열 배열 타입 매개변수로 처리한다.
	@PostMapping("/registerCheckbox02")
	public String registerCheckbox02(String[] hobbyArray) {
		log.info("registerCheckbox02()실행..!");
		if(hobbyArray != null) {
			log.info("hobbyArray.length : " + hobbyArray.length);
			for(int i = 0;i<hobbyArray.length;i++) {
				log.info("hobbyArray["+i+"] : " + hobbyArray[i]);
			}
		} else {
			log.info("hobbyArray is null");
		}
		return "chapt05/success";
	}
	// 10) 폼 체크박스 요소값을 문자열 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.
	@PostMapping("/registerCheckbox03")
	public String registerCheckbox03(@RequestParam ArrayList<String> hobbyList) {
		// 일반적으로 Collection 타입의 데이터는 일반적인 선언으로는 값을 가져올 수 없다.
		// Collection 타입의 데이터를 받아오기 위해서는 @RequestParam 어노테이션을 설정한다.
		log.info("registerCheckbox03()실행..!");
		if(hobbyList != null) {
			log.info("hobbyList.size() : " + hobbyList.size());
			for(int i = 0;i<hobbyList.size();i++) {
				log.info("hobbyList.get("+i+") : " + hobbyList.get(i));
			}
		} else {
			log.info("hobbyList is null");
		}
		return "chapt05/success";
	}
	// 11) 폼 체크박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@PostMapping("/registerCheckbox04")
	public String registerCheckbox04(String developer) {
		// developer 체크박스를 체크 시, 'Y'가 파라미터로 넘어옵니다.
		// 이때, 'Y'의 반대가 'N'으로 처리되는게 아니라, 'Y'를 받는 타입의 default 값인 'null'이 파라미터의 결과로
		// 들어온다. (String의 default는 null 이므로)
		log.info("registerCheckbox04()실행..!");
		log.info("developer : " + developer);
		return "chapt05/success";
	}
	// 12) 폼 체크박스 요소값을 기본 데이터 타입인 불리언 타입 매개변수로 처리한다.
	@PostMapping("/registerCheckbox05")
	public String registerCheckbox05(String foreigner) {
		// foreigner 체크박스를 체크 시, 'true'가 파라미터로 넘어옵니다.
		// 이때, 'true'의 반대는 'false'로 boolean 타입의 default 값도 'false'입니다.
		// 그렇기 때문에 체크 시 true, 미체크 시 false가 들어옵니다.
		log.info("registerCheckbox05()실행..!");
		log.info("foreigner : " + foreigner);
		return "chapt05/success";
	}
	// 13) 폼 텍스트 필드 요소값을 중첩된 자바 빈즈 매개변수로 처리한다.
	@PostMapping("/registerUserAddress")
	public String registerUserAddress(Member member) {
		log.info("registerUserAddress()실행..!");
		Address address = member.getAddress();
		if(address != null) {
			log.info("address.getPostCode() : " + address.getPostCode());
			log.info("address.getLocation() : " + address.getLocation());
		} else {
			log.info("address is null");
		}
		return "chapt05/success";
	}
	// 14) 폼 텍스트 필드 요소값을 중첩된 자바 빈즈 매개변수로 처리한다.
	@PostMapping("/registerUserCardList")
	public String registerUserCardList(Member member) {
		log.info("registerUserCardList()실행..!");
		List<Card> cardList = member.getCardList();
		if(cardList != null) {
			log.info("cardList.size() : " + cardList.size());
			for(int i = 0;i<cardList.size();i++) {
				Card card = cardList.get(i);
				log.info("card.getNo() : "+card.getNo());
				log.info("card.getValidMonth() : "+card.getValidMonth());
			}
		} else {
			log.info("cardList is null");
		}
		return "chapt05/success";
	}
	
	// 회원가입에 필요한 전체 폼 페이지 (문제)
	@GetMapping("/test/allForm")
	public String testAllForm() {
		return "chapt05/test/allForm";
	}
	
	@PostMapping("/test/result")
	public String registerUser(
			Member member,
			@RequestParam(required = false) List<String> carList,
			String dateOfBirthStr,
			String no1, String validMonth1,
			String no2, String validMonth2,
			Model model) {
		log.info("--- 회원 정보 출력 ---");
		log.info("유저ID				"+member.getUserId());
		log.info("패스워드				"+member.getPassword());
		log.info("이름				"+member.getUserName());
		log.info("E-MAIL			"+member.getEmail());
		Date dateOfBirth = Chapter05Controller.strToDate(dateOfBirthStr);
		member.setDateOfBirth(dateOfBirth);
//		SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
		log.info("생년월일				"+Chapter05Controller.dateToStr(member.getDateOfBirth()));
		if(member.getGender() == "male") {
			log.info("성별				"+"남성");
		} else if(member.getGender() == "female") {
			log.info("성별				"+"여성");
		} else {
			log.info("성별				"+"기타");
		}
		if(member.getDeveloper() == null) {
			log.info("개발자여부			"+"비개발자");
		} else {
			log.info("개발자여부			"+"개발자");
		}
		if(member.isForeigner()) {
			log.info("외국인여부			"+"외국인");
		} else {
			log.info("외국인여부			"+"내국인");
		}
		log.info("국적				"+member.getNationality());
		if(carList != null) {
			member.setCarList(carList);
			log.info("소유차량				"+member.getCarList());
		} else {
			log.info("소유차량				"+"소유차량 없음");
		}
		if(member.getHobbyList()!=null) {
			log.info("취미				"+member.getHobbyList());
		} else {
			log.info("취미				"+"취미 없음");
		}
//		member.setAddress(address);
		log.info("우편번호				"+member.getAddress().getPostCode());
		log.info("주소				"+member.getAddress().getLocation());
		if(no1 != null && validMonth1 != null && !validMonth1.equals("")) {
//			log.debug("no1 : "+no1);
//			log.debug("validMonth1 : "+validMonth1);
			Card card = new Card();
			card.setNo(no1);
			Date validMonth = Chapter05Controller.strToDate(validMonth1);
			card.setValidMonth(validMonth);
//				log.debug("card : "+card);
			if(member.getCardList() != null && member.getCardList().size()>0) {
				member.getCardList().add(card);
			} else {
				List<Card> cardList = new ArrayList<>();
				cardList.add(card);
				member.setCardList(cardList);
			}
		}
		if(no2 != null && validMonth2 != null && !validMonth2.equals("")) {
			Card card = new Card();
			card.setNo(no2);
			Date validMonth = Chapter05Controller.strToDate(validMonth2);
			card.setValidMonth(validMonth);
			if(member.getCardList() != null && member.getCardList().size()>0) {
				member.getCardList().add(card);
			} else {
				List<Card> cardList = new ArrayList<>();
				cardList.add(card);
				member.setCardList(cardList);
			}
		}
		
		
		if(member.getCardList() != null) {
			for(int i=0;i<member.getCardList().size();i++) {
				log.info("카드"+(i+1)+"(번호)			"+member.getCardList().get(i).getNo());
				log.info("카드"+(i+1)+"(유효년월)		"+Chapter05Controller.dateToStr(member.getCardList().get(i).getValidMonth()));
			}
		}
		log.info("소개				"+member.getIntroduction());
		model.addAttribute("member",member);
		return "chapt05/test/result";
	}
	
	/*
		8. 파일 업로드 폼 방식 요청 처리
		
			- 파일 업로드 설정
			> application.properties 파일 내, 파일 설정
			- IOUtils 사용과 파일 핸들링을 위한, pom.xml 내 의존 라이브러리 추가
			> commons.io, commons-fileupload 라이브러리 의존관계 등록
			
			*** 파일 업로드 설정
			- max-file-size : 업로드 가능한 최대 파일 크기(기본값은 1MB)
			- max-request-size : 요청 전체의 최대 크기 (파일 + 파라미터 포함, 기본값은 10MB)
			- file-size-threshold : 파일이 디스크에 기록되는 크기 임계값을 지정합니다. (기본값은 0)
			- location : 업로드 된 파일이 저장될 디렉토리를 지정합니다. 지정하지 않으면 임시 디렉토리가 사용됩니다.
	 */
	@PostMapping("/registerFile01")
	public String registerFile01(Member member, MultipartFile picture) {
		log.info("registerFile01()실행..!");
		log.info("member.getUserId() : "+member.getUserId());
		log.info("member.getPassword() : "+member.getPassword());
		
		log.info("originalFileName : "+ picture.getOriginalFilename());		// 파일명 출력
		log.info("size : " + picture.getSize());							// 파일크기 출력
		log.info("contentType : "+picture.getContentType());				// 파일 Mimetype 출력
		return "chapt05/success";
	}
	@PostMapping("/registerFile02")
	public String registerFile02(FileMember fileMember) {
		log.info("registerFile02()실행..!");
		log.info("member.getUserId() : "+fileMember.getUserId());
		log.info("member.getPassword() : "+fileMember.getPassword());
		
		MultipartFile picture = fileMember.getPicture();
		
		log.info("originalFileName : "+ picture.getOriginalFilename());		// 파일명 출력
		log.info("size : " + picture.getSize());							// 파일크기 출력
		log.info("contentType : "+picture.getContentType());			// 파일 Mimetype 출력
		return "chapt05/success";
	}
	@PostMapping("/registerFile03")
	public String registerFile03(
			@RequestParam ArrayList<MultipartFile> pictureList,
			FileMember fileMember) {
		log.info("registerFile03()실행..!");
		
		if(pictureList !=null && pictureList.size()>0) {
			for (int i=0;i<pictureList.size();i++) {
				MultipartFile picture = pictureList.get(i);
				log.info("originalFileName : "+ picture.getOriginalFilename());		// 파일명 출력
				log.info("size : " + picture.getSize());							// 파일크기 출력
				log.info("contentType : "+picture.getContentType());
			}
		}
		log.info("member.getUserId() : "+fileMember.getUserId());
		log.info("member.getPassword() : "+fileMember.getPassword());
		
		return "chapt05/success";
	}
	
	@PostMapping("/registerFile04")
	public String registerFile04(MultiFileMember multiFileMember) {
		// 일반적인 데이터를 받아내는 방식과 방법은 다양하게 존재합니다.
		// 다양한 방법이 존재하기 때문에 각 방법마다 데이터를 바인딩 하기 위한 간단한 규칙들은 존재합니다.
		// 우리는 그런 모든 규칙들을 알면 물론 좋겠지만, 다양한 방법에 대해서 모든 걸 알 필요는 없습니다.
		// 그렇기 때문에 가장 간편하고 많은 개발자가 사용하는 객체(자바빈즈 클래스 객체 타입)타입으로 데이터를 받는게 용이하다.
		// 클래스 내 명시된 필드명과 input 요소의 name을 맞춰준다면 데이터가 자동 바인딩된다.
		log.info("registerFile04()실행..!");
		List<MultipartFile> pictureList = multiFileMember.getPictureList();
		if(pictureList !=null && pictureList.size()>0) {
			for (int i=0;i<pictureList.size();i++) {
				MultipartFile picture = pictureList.get(i);
				log.info("originalFileName : "+ picture.getOriginalFilename());		// 파일명 출력
				log.info("size : " + picture.getSize());							// 파일크기 출력
				log.info("contentType : "+picture.getContentType());
			}
		}
		log.info("member.getUserId() : "+multiFileMember.getUserId());
		log.info("member.getPassword() : "+multiFileMember.getPassword());
		
		return "chapt05/success";
	}
}
