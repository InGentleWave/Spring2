package kr.or.ddit.controller.chapt09.item01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.controller.chapt09.item01.service.IItemService;
import kr.or.ddit.vo.Item;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/item")
public class Chapter09FileUploadController {

	/*
		[ 9장 . 파일 업로드 ]
		
		1. 파일 업로드 설명
		- 서블릿 3.0에서 지원하는 파일 업로드 기능과 스프링 웹에서 제공하는 컴포넌트를 사용하여 파일을 업로드한다.
		
			# 파일 업로드 설정
				
				1) 서블릿 3.0 이상 지원
				2) 서블릿 표준 파일 업로드 기능을 활성화
				3) 스프링 MVC와 연계
				4) 업로드 된 파일 저장 위치 설정
			
			# 환경 설정
			
				1) 의존 관계 정의
				- 파일을 처리하기 위해서 의존 라이브러리를 추가한다.
				- 스프링 부트 프로젝트 초기 설정 시, 해당 dependency에 포함
					> pom.xml commons-io 추가
					> pom.xml commons-fileupload 추가
				
				2) application.properties 설정
				- spring.servlet.multipart.max-file-size=10MB
				- spring.servlet.multipart.max-request-size=12MB
				- spring.servlet.multipart.file-size-threshold=12MB
				
			# 파일업로드 경로 설정
			
				1) 파일 업로드 외부 local 경로 설정
				- config 패키지 내, FileConfiguration 클래스 생성
					> 파일 업로드 외부 local 경로와 웹 경로를 매핑하기 위한 설정
				
			# 데이터베이스 준비
			
				- item 테이블 생성 (item, item2, item2_attach)
				
		2. 이미지 업로드
			- 1개의 이미지를 업로드하는 기본 파일 업로드 기능을 구현합니다.
			
				# 파일 업로드 구현 설명
				
					- 파일업로드 등록 화면 컨트롤러 만들기 (Chapter09FileUploadController)
					- 파일업로드 등록 화면 컨트롤러 메소드 만들기 (itemRegisterForm:get)
					- 파일업로드 등록 화면 만들기 (item/register.jsp)
					- 여기까지 확인
					
					- 파일업로드 등록 기능 컨트롤러 메소드 만들기 (itemRegister:post)
					- 파일업로드 등록 기능 서비스 인터페이스 메소드 만들기
					- 파일업로드 등록 기능 서비스 클래스 메소드 만들기
					- 파일업로드 등록 기능 Mapper 인터페이스 메소드 만들기
					- 파일업로드 등록 기능 Mapper xml 쿼리 만들기
					- 파일업로드 등록 완료 페이지 만들기 (item/success.jsp)
					- 여기까지 확인
					
					- 파일업로드 목록 화면 컨트롤러 메소드 만들기 (itemList:get)
					- 파일업로드 목록 화면 서비스 인터페이스 메소드 만들기
					- 파일업로드 목록 화면 서비스 클래스 메소드 만들기
					- 파일업로드 목록 화면 Mapper 인터페이스 메소드 만들기
					- 파일업로드 목록 화면 Mapper xml 쿼리 만들기
					- 파일업로드 목록 화면 페이지 만들기 (item/list.jsp)
					- 여기까지 확인
					
					- 파일업로드 수정 화면 컨트롤러 메소드 만들기 (itemModifyForm:get)
					- 파일업로드 수정 화면 서비스 인터페이스 메소드 만들기
					- 파일업로드 수정 화면 서비스 클래스 메소드 만들기
					- 파일업로드 수정 화면 Mapper 인터페이스 메소드 만들기
					- 파일업로드 수정 화면 Mapper xml 쿼리 만들기
					- 파일업로드 수정 화면 페이지 만들기 (item/modify.jsp)
					- 여기까지 확인
					
					- 파일업로드 수정 기능 컨트롤러 메소드 만들기 (itemModify:post)
					- 파일업로드 수정 기능 서비스 인터페이스 메소드 만들기
					- 파일업로드 수정 기능 서비스 클래스 메소드 만들기
					- 파일업로드 수정 기능 Mapper 인터페이스 메소드 만들기
					- 파일업로드 수정 기능 Mapper xml 쿼리 만들기
					- 파일업로드 수정 완료 페이지 만들기 (item/success.jsp)
					- 여기까지 확인
					
					- 파일업로드 삭제 화면 컨트롤러 메소드 만들기 (itemRemoveForm:get)
					- 파일업로드 삭제 화면 서비스 인터페이스 메소드 만들기
					- 파일업로드 삭제 화면 서비스 클래스 메소드 만들기
					- 파일업로드 삭제 화면 Mapper 인터페이스 메소드 만들기
					- 파일업로드 삭제 화면 Mapper xml 쿼리 만들기
					- 파일업로드 삭제 화면 페이지 만들기 (item/remove.jsp)
					- 여기까지 확인
					
					- 파일업로드 삭제 기능 컨트롤러 메소드 만들기 (itemRemove:post)
					- 파일업로드 삭제 기능 서비스 인터페이스 메소드 만들기
					- 파일업로드 삭제 기능 서비스 클래스 메소드 만들기
					- 파일업로드 삭제 기능 Mapper 인터페이스 메소드 만들기
					- 파일업로드 삭제 기능 Mapper xml 쿼리 만들기
					- 파일업로드 삭제 완료 페이지 만들기 (item/success.jsp)
					- 여기까지 확인
	 */
	
	// 업로드 하기 위한 경로 설정
	// [방법 1] application.properties 파일에 내장된 경로를 설정할 수 있다.
	@Value("${kr.or.ddit.upload.path}")
	private String localPath;
	
	// [방법 2] 파일 업로드 설정 경로
//	private String localPath = "C:/upload";
	
	@Autowired
	private IItemService itemService;
	
	@GetMapping("/register")
	public String itemRegisterForm() {
		log.info("itemRegisterForm()실행!");
		return "chapt09/item/register";
	}
	
	@PostMapping("/register")
	public String itemRegister(Item item,Model model) throws Exception {
		log.info("itemRegister()실행!");
		MultipartFile file = item.getPicture();
		log.info("originalName : "+ file.getOriginalFilename());
		log.info("size : "+ file.getSize());
		log.info("contentType : "+ file.getContentType());
		
		// 넘겨받은 파일을 이용하여 파일 업로드(복사)를 진행한다.
		// return : UUID + '_' + 원본파일명
		String createdFileName = uploadFile(file.getOriginalFilename(),file.getBytes());
		item.setPictureUrl(createdFileName);
		itemService.register(item);
		model.addAttribute("msg","등록이 완료되었습니다.");
		return "chapt09/item/success";
	}

	
	@GetMapping("/list")
	public String itemList(Model model) {
		log.info("itemList()실행!");
		List<Item> itemList = itemService.list();
		model.addAttribute("itemList",itemList);
		return "chapt09/item/list";
	}
	
	@GetMapping("/modify")
	public String itemModifyForm(int itemId,Model model) {
		log.info("itemModifyForm()실행!");
		Item item = itemService.read(itemId);
		model.addAttribute("item",item);
		return "chapt09/item/modify";
	}
	@PostMapping("/modify")
	public String itemModify(Item item, Model model) throws Exception {
		log.info("itemModify()실행!");
		MultipartFile file = item.getPicture();
		if(file != null && file.getSize()>0) {
			log.info("fileName : " + file.getOriginalFilename());
			
			String createdFileName = uploadFile(file.getOriginalFilename(),file.getBytes());
			
			item.setPictureUrl(createdFileName);
		}
		itemService.modify(item);
		model.addAttribute("msg","수정이 완료되었습니다.");
		return "chapt09/item/success";
	}
	
	@GetMapping("/remove")
	public String itemRemoveForm(int itemId,Model model) {
		log.info("itemRemoveForm()실행!");
		Item item = itemService.read(itemId);
		model.addAttribute("item",item);
		return "chapt09/item/remove";
	}
	@PostMapping("/remove")
	public String itemRemove(int itemId, Model model) throws Exception {
		log.info("itemRemove()실행!");
		itemService.remove(itemId);
		model.addAttribute("msg","삭제가 완료되었습니다.");
		
		return "chapt09/item/success";
	}
	
	
	@ResponseBody
	@GetMapping("/display")
	public ResponseEntity<byte[]> displayFile(int itemId) {
		log.info("displayFile()실행!");
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		// itemId에 해당하는 이미지 파일명을 가져온다.
		String fileName = itemService.getPicture(itemId);
		log.info("File_Name : "+fileName);
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);	// 확장자 추출
			MediaType mType = getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(localPath + File.separator + fileName);
			if(mType != null) {
				headers.setContentType(mType);
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entity;
	}
	
	
	// 확장자에 알맞는 미디어타입 가져오기
	private MediaType getMediaType(String formatName) {
		if(formatName != null) {
			if(formatName.toUpperCase().equals("JPG")) {
				return MediaType.IMAGE_JPEG;
			}
			if(formatName.toUpperCase().equals("PNG")) {
				return MediaType.IMAGE_PNG;
			}
			if(formatName.toUpperCase().equals("GIF")) {
				return MediaType.IMAGE_GIF;
			}
		}
		return null;
	}

	// 파일 업로드 함수
	private String uploadFile(String originalFilename, byte[] bytes) throws Exception {
		UUID uuid = UUID.randomUUID();	// UUID로 파일명 생성
		String createdFileName = uuid.toString() + "_" + originalFilename;
		
		File file = new File(localPath);
		if(!file.exists()) file.mkdirs();
		
		File target = new File(localPath, createdFileName);
		FileCopyUtils.copy(bytes, target); // 파일 복사 진행
		return createdFileName;
	}
}
