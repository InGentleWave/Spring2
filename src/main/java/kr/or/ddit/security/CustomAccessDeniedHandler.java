package kr.or.ddit.security;

import java.io.IOException;
import java.security.Principal;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class CustomAccessDeniedHandler implements AccessDeniedHandler{@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		log.info("## CustomAccessDeniedHandler()실행!");
		log.info("## AccessDeniedException info --------------");
		log.info(accessDeniedException.getMessage());
		
		Principal principal = request.getUserPrincipal();
		if(request.isUserInRole("ROLE_MEMBER")) {
			log.info("## 회원 권한을 가진 사용자 id : {}",principal.getName());
		} else {
			log.info("## 관리자 권한을 가진 사용자 id : {}",principal.getName());
		}
		response.sendRedirect("/accessError");
	}

}
