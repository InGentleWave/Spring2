package kr.or.ddit.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{

	private RequestCache requestCache = new HttpSessionRequestCache();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("# CustomLoginSuccessHandler.onAuthenticationSuccess()실행!");
		User user = (User) authentication.getPrincipal();
		
		// 시큐리티 활성화된 사용자인지 체크
		if(user.isEnabled()) {
			log.info("# username : "+user.getUsername());
			log.info("# password : {}",user.getPassword());
			Collection<GrantedAuthority> grantedAuthority = user.getAuthorities();
			Iterator<GrantedAuthority> ite = grantedAuthority.iterator();
			while(ite.hasNext()) {
				GrantedAuthority authority = ite.next();
				log.info("# auth : {}", authority.getAuthority());	// 인증된 사용자 권한
			}
		}
		
		// SPRING_SECURITY_EXCEPTION으로 등록된 에러 정보를 삭제
		clearAuthenticationAttribute(request);
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		String targetUrl = "/";
		if(savedRequest != null) {
			targetUrl = savedRequest.getRedirectUrl();
		}
		
		log.info("# Login Success Target URL : {}",targetUrl);
		response.sendRedirect(targetUrl);
	}

	private void clearAuthenticationAttribute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session == null) {
			return;
		}
		
		// SPRING_SECURITY_LAST_EXCEPTION 값
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}
