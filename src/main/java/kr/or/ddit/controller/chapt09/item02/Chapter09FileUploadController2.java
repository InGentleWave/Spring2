package kr.or.ddit.controller.chapt09.item02;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.controller.chapt09.item02.service.IItemService2;
import kr.or.ddit.vo.Item2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/item2")
public class Chapter09FileUploadController2 {

	/*
		2. 비동기 방식 업로드
		
			- 비동기 방식으로 여러 개의 이미지를 업로드 하는 파일 업로드 기능을 구현합니다.
				
				#  환경 설정
				
				- 의존 관계 정의 (pom.xml)
					> commons 라이브러리(io, fileupload) : 파일을 처리하기 위한 의존 라이브러리
					> imgscalr-lib : 이미지 변환을 처리하기 위한 의존 라이브러리
					
				# 파일 업로드 구현 설명
					
					- 파일 업로드 등록 화면 컨트롤러 만들기 (Chapter09FileUploadController2)
					- 파일 업로드 등록 화면 컨트롤러 메서드 만들기 (item2RegisterForm:get)
					- 파일 업로드 등록 화면 만들기 (item2/register.jsp)
					- 여기까지 확인
	 */
	
	@Value("${kr.or.ddit.upload.path}")
	private String uploadPath;
	
	@Autowired
	private IItemService2 itemService;
	
	@GetMapping("/register")
	public String item2RegisterForm() {
		
		return "chapt09/item2/register";
	}
	
	@GetMapping("/list")
	public String item2List(Model model) {
		List<Item2> itemList = itemService.list();
		model.addAttribute("itemList",itemList);
		return "chapt09/item2/list";
	}
	
	@GetMapping("modify")
	public String item2ModifyForm(int itemId,Model model) {
		Item2 item = itemService.read(itemId);
		model.addAttribute("item",item);
		return "chapt09/item2/modify";
	}
	
	@ResponseBody
	@GetMapping("/getAttach/{itemId}")
	public List<String> getAttach(@PathVariable int itemId){
		return itemService.getAttach(itemId);
	}
	
	@PostMapping("/modify")
	public String item2Modify(Item2 item,Model model) {
		String[] files = item.getFiles();
		
		if(files != null && files.length >0 ) {
			for(String fileName : files) {
				log.info("file(modify) : "+fileName);
			}
		}
		
		itemService.modify(item);
		model.addAttribute("msg","수정이 완료되었습니다.");
		return "chapt09/item2/success";
	}
	
	@GetMapping("/remove")
	public String removeForm(int itemId,Model model) {
		Item2 item = itemService.read(itemId);
		model.addAttribute("item",item);
		return "chapt09/item2/remove";
	}
	
	@PostMapping("/remove")
	public String remove(int itemId,Model model) {
		itemService.remove(itemId);
		model.addAttribute("msg","삭제가 완료되었습니다.");
		return "chapt09/item2/success";
	}
	
	// uploadFile 메소드는 브라우저에서 넘겨받은 파일을 업로드하는 기능을 담당합니다.
	// produces 속성은 클라이언트에서 보내오는 비동기 요청에 대한 응답 타입을 설정할 때 사용합니다.
	// 헤더 정보 중, Accept에 설정된 MimeType들 중 하나가 응답으로 나가고,
	// 클라이언트 요청에 속성들 중,
	// dataType 속성에 명시한 타입과 produces 속성에 명시한 타입을 일치시켜야
	// 해당 요청과 목적지가 연동될 수 있다.
	// 물론, 해당 메서드의 응답 타입에 명시된 제네릭타입으로도 예측은 할 수 있지만,
	// 무수히 많은 목적지(메서드)들 중 하나를 명확하게 설정하기 위해서는
	// 여러 옵션에 해당하는 속성정보를 맞출 필요는 있다.
	@PostMapping(value="/uploadFile", produces="text/plain; charset=utf-8")
	public ResponseEntity<String> uploadFile(MultipartFile file) throws Exception{
		log.info("file.originalFileName : " + file.getOriginalFilename());
		
		String savedName = UploadFileUtils.uploadFile(
				uploadPath, file.getOriginalFilename(), file.getBytes());
		
		return new ResponseEntity<String>(savedName,HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName){
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			MediaType mType = MediaUtils.getMediaType(formatName); 
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(uploadPath + "/" + fileName);
			
			if(mType != null) {
				headers.setContentType(mType);
			} else {
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment;filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return entity;
	}
	
	@PostMapping("/register")
	public String item2Register(Item2 item,Model model) {
		String[] files = item.getFiles();
		
		if(files != null && files.length > 0) {
			for(int i=0;i<files.length;i++) {
				log.info("files["+i+"] : "+files[i]);
			}
		}
		
		itemService.register(item);
		model.addAttribute("msg","등록이 완료되었습니다!");
		return "chapt09/item2/success";
	}
	
	
}
