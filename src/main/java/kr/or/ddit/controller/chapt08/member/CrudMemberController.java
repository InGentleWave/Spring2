package kr.or.ddit.controller.chapt08.member;

import java.io.IOException;
import java.util.List;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.annotation.PostConstruct;
import kr.or.ddit.controller.chapt08.member.service.IMemberService;
import kr.or.ddit.vo.crud.CrudMember;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/crud/member")
public class CrudMemberController {
	
	@Autowired
	private IMemberService service;
	
	// @PostConstruct 어노테이션은 런타임 단계에서 초기화를 수행할 수 있도록 해줍니다.
	// 런타임 후 빈이 등록되고 초기화하는 과정에서 생성자를 초기화하거나 값을 확인하고자 할 때 사용합니다.
	@PostConstruct
	public void init() {
		// aopProxy는 인터페이스 기반의 프록시를 생성한 Dynamic Proxy를 사용하여 위빙을 지정합니다.
		// - 인터페이스를 구현하고 해당 인터페이스를 참조해서 구현한 클래스를 사용하는 형태이어야 한다(service, impl)
		// aopProxy 상태 확인(인터페이스 기반의 프록시인지 검증)
		log.info("# aopProxy 상태(interface 기반) : "+AopUtils.isAopProxy(service));
		// 인터페이스 기반이 아닌 클래스 기반의 프록시를 생성한 CGLib Proxy를 사용하여 위빙을 지정합니다.
		// - 인터페이스 구현 없이 클래스로만 사용되는 형태
		// aopProxy 상태 확인(클래스 기반의 프록시인지 검증)
		log.info("# aopProxy 상태(클래스 기반) : "+AopUtils.isCglibProxy(service));
		
	}

	@GetMapping("/register")
	public String crudMemberRegisterForm() {
		log.info("crudMembSerRegisterForm()실행!");
		 return "chapt08/member/register";
	}
	
	@PostMapping("/register")
	public String crudMemberRegister(CrudMember member,Model model) throws IOException {
		log.info("crudMemberRegister()실행!");
		service.register(member);
		model.addAttribute("msg","등록이 완료되었습니다.");
		return "chapt08/member/success";
	}
	
	@GetMapping("/list")
	public String crudMemberList(Model model) {
		log.info("crudMemberList()실행!");
		List<CrudMember> memberList = service.list();
		model.addAttribute("memberList",memberList);
		 return "chapt08/member/list";
	}
	
	@GetMapping("/read")
	public String crudMemberRead(int userNo,Model model) {
		log.info("crudMemberRead()실행!");
		CrudMember member= service.read(userNo);
		model.addAttribute("member",member);
		return "chapt08/member/read";
	}
	
	@GetMapping("/modify")
	public String crudMemberModifyForm(int userNo,Model model) {
		log.info("crudMemberModifyForm()실행!");
		CrudMember member= service.read(userNo);
		model.addAttribute("member",member);
		return "chapt08/member/modify";
	}
	@PostMapping("/modify")
	public String crudMemberModify(CrudMember member,Model model) {
		log.info("crudMemberModify()실행!");
		service.modify(member);
		model.addAttribute("msg","수정이 완료되었습니다.");
		return "chapt08/member/success";
	}
	@PostMapping("/remove")
	public String crudMemberDelete(int userNo,Model model) {
		log.info("crudMemberDelete()실행!");
		service.remove(userNo);
		model.addAttribute("msg","삭제가 완료되었습니다.");
		return "chapt08/member/success";
	}
}
