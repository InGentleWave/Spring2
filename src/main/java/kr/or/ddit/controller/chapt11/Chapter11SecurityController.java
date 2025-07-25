package kr.or.ddit.controller.chapt11;

public class Chapter11SecurityController {
	/*
 		[ 11장 : 스프링 시큐리티 ] 
 		
 		1. 스프링 시큐리티 소개
 		
 			- 애플리케이션에서 보안 기능을 구현하는데 사용되는 프레임워크이다.
 			- 스프링 시큐리티는 필터 기반으로 동작하기 때문에 스프링 MVC와 분리되어 동작한다.
 			- 스프링 기반의 애플리케이션 보안(인증, 인가)을 담당하는 스프링 하위 프레임워크이다.
 			
 			# 인증과 인가
 			인증(Authentication)은 사용자의 신원을 입증하는 과정입니다.
 			예를 들어 사용자가 사이트에 로그인을 할 때 누구인지 확인하는 과정을 인증이라고 합니다.
 			인가(Authorization)은 인증과는 다릅니다.
 			인가는 사이트의 특정 부분에 접근할 수 있는지 권한을 확인하는 작업입니다.
 			예를 들어, 관리자는 관리자 페이지에 들어갈 수 있지만 일반 사용자는 관리자 페이지에 들어갈 수 없습니다.
 			이런 권하을 확인하는 과정을 인가라고 합니다.
 			
 			# 스프링 시큐리티
 			
 			슾링 시큐리티는 스프링 기반 애플리케이션의 보안을 담당하는 스프링 하위 프레임워크입니다.
 			다양한 보안 옵션을 제공하고 어노테이션을 이용한 설정도 간편하게 설정할 수 있습니다.
 			CSRF 공격, 세션 고정(Session Fixation)공격을 방어해주고 요청 헤더도 보안 처리를 해주므로 
 			개발자가 보안 관련 개발을 해야 하는 부담을 크게 줄일 수 있습니다.
 			
 				*** CSRF 공격
 				- 크로스 사이트 요청 위조는 웹 사이트 취약점 공격의 하나로,
 					사용자가 자신의 의지와는 무관하게 공격자가 의도한 행위(수정,삭제,등록 등)를
 					특정 웹 사이트에 요청하게 하는 공격을 말합니다.
				*** 세션 고정 공격
				- 사용자의 인증 정보를 탈취하거나 변조하는 공격을 말합니다.
			
			# 시큐리티 동작 방식
			
			스프링 시큐리티는 필터 기반으로 동작합니다.
			시큐리티는 SecurityContextPersistenceFilter부터 시작해서
			FilterSecurityInterceptor에 이르기까지 순서대로 많은 필터를 거칩니다.
			많은 필터들 중, UsernamePasswordAuthenticationFilter와
			FilterSecurityInterceptor는 시큐리티에 있어서 중요한 역할을 담당하는 필터입니다.
			UsernamePasswordAuthenticationFilter는 아이디와 비밀번호가 넘어오면
			인증 요청을 위임하는 인증 관리자 역할이고,
			FilterSecurityInterceptor는 권한 부여 처리를 위임해 접근 제어 결정을 쉽게 하는 접근 결정 관리자 역할을 합니다.
			
			# 각 필터 설명
			
			SecurityContextPersistenceFilter
			- SecurityContextRepository에서 SecurityContext (접근 주체와 인증에 대한 정보를 담고 있는 객체)를
				가져오거나 저장하는 역할을 합니다.
			
			LogoutFilter
			- 설정된 로그아웃 URL로 오는 요청을 확인해 해당 사용자르 로그아우 처리합니다.
			
			UsernamePasswordAuthenticationFilter
			- 인증 관리자입니다. 폼 기반 로그인을 할 때 사용되는 필터로 아이디, 비밀번호 데이터를 파싱해 인증 요청을 위임합니다.
				인증이 성공하면 AuthenticationSuccessHandler를,
				인증에 실패하면 AuthenticationFailureHandler를 실행합니다.
			
			DefaultLoginPageGeneratingFilter
			- 사용자가 로그인 페이지를 따로 지정하지 않았을 때 기본적으로 설정하는 로그인 페이지 관련 필터입니다.
			
			BasicAuthenticatioFilter
			- 요청 헤더에 있는 아이디와 비밀번호를 파싱해서 인증 요청을 위임합니다.
				인증이 성공하면 AuthenticationSuccessHandler를,
				인증에 실패하면 AuthenticationFailureHandler를 실행합니다.
			
			RequestCacheAwareFilter
			- 로그인 성공 후, 관련 있는 캐시 요청이 있는지 확인하고 캐시 요청을 처리해줍니다.
			예를 들어 로그인하지 않은 상태로 방문했던 페이지를 기억해두었다가 로그인 이후에 그 페이지로 이동 시켜줍니다.
			
			SecurityContextHolderAwareRequestFilter
			- HttpServletRequest 정보를 감쌉니다. 필터 체인 상의 다음 필터들에게 부가 정보를 제공하기 위해 사용
			
			AnnonymousAuthenticationFilter
			- 필터가 호출되는 시점까지 인증되지 않았다면
				익명 사용자 전용 객체인 AnnonymousAuthentication을 만들어 SecurityContext에 넣어줍니다.
				
			SessionManagementFilter
			- 인증된 사용자와 관련된 세션 관련 작업을 진행합니다.
				세션 변조 방지 전략을 설정하고, 유효하지 않은 세션에 대한 처리를 하고, 세션 생성 전략을 세우는 등의 작업을 처리합니다.
			
			ExceptionTranslationFilter
			- 요청을 처리하는 중에 발생할 수 있는 예외를 위임하거나 전달합니다.
			
			FilterSecurityInterceptor
			- 접근 결정 관리자입니다. AccessDecisionManager로 권한 부여 처리를 위임함으로써 접근 제어 결정을 쉽게 해줌.
				이 과정에서는 이미 사용자가 인증되어 있으므로 유효한 사용자인지도 알 수 있습니다.
				즉 인가 관련 설정을 할 수 있음.
				
			------------------------------------------------------------------------------------------
			
			가장 많이 사용되어지는 시큐리티 인증 방식은 로그인 폼을 이용한 폼 기반 방식입니다.
			사용자가 폼에 아이디와 패스워드를 입력하면, HttpServletRequest에 아이디와 비밀번호 정보가 전달됩니다.
			이때, AuthenticationFilter가 넘어온 아이디와 비밀번호의 유효성 검사를 합니다.
			유효성 검사가 끝나면 실제 구현체인 UsernamePasswordAuthenticationToken을 만들어 넘겨줍니다.
			전달받은 인증용 객체인 UsernamePasswordAuthenticationToken을 AuthenticationManager에게 보냅니다.
			그리고 전달받은 해당 UsernamePasswordAuthenticationToken을 AuthenticationProvider에게 보냅니다.
			사용자 아이디를 UserDetailService에 보내고 UserDetailService는 사용자 아이디로 조회한 사용자의 정보를
			UserDetails 객체로 만들어 AuthenticationProvider에게 전달합니다.
			이때, Database에 있는 사용자 정보를 가져와 입력 정보와 UserDetails의 정보를 비교해 실제 인증 처리를 합니다.
			인증이 완료되면 SecurityContextHolder에 Authentication을 저장합니다.
			인증 성공 여부에 따라 성공하면 AuthenticationSuccessHandler를,
			인증에 실패하면 AuthenticationFailureHandler를 실행합니다.
		
		2. 스프링 시큐리티 설정
		
			# 환경 설정
			
				- 의존 라이브러리 설정(pom.xml)
				> spring-boot-starter-security
				> spring-security-taglibs
				
				- application.properties 설정
				> logging.level.org.springframework.security=debug
				
				- 시큐리터 Config 설정
				> config 패키지 내, SecurityConfig 클래스 생성
				
			# 웹 화면 접근 정책
			
				- 웹 화면 접근 정책으 ㄹ정한다. (테스트를 위한 각 화면당 접근 정책을 설정한다.)
				> 	[일반게시판] 	목록 확면 - 모두가 접근 가능합니다.
								등록 화면 - 로그인한 회원, 관리자만 접근이 가능합니다.
					[공지사항]		목록 화면 - 모두가 접근 가능
								등록 화면 - 로그인한 관리자만 접근 가능
			
			# 화면 설정
			
				- 컨트롤러
				> board.SecurityBoardController
				> notice.SecurityNoticeController
				
				- 화면
				> board/list.jsp
				> board/register.jsp
				> notice/list.jsp
				> notice/register.jsp
		
		3. 접근 제한 설정
			
			- 시큐리티 설정을 통해서 특정 URI에 대한 접근을 제한할 수 있다.
			
			# 환경 설정
			
				- 시큐리티 Config 설정
				> URI 패턴으로 접근 제한을 설정한다.
				> protected SecurityFilterChain filterChain(HttpSecurity http)
			
			# 화면 설명
			
				- 일반 게시판 목록 화면 (모두 접근 가능하도록 되어 있다 : permitAll)
				- 일반 게시판 등록 화면 (회원권한을 가진 사용자, 관리자만 접근 가능 : hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')
					> 접근 제한에 걸려 스프링 시큐리티가 기본적으로 제공하는 로그인 페이지로 이동합니다.
					
				- 공지사항 게시판 목록 화면 (모두 접근 가능하도록 되어 있다 : permitAll)
				- 공지사항 게시판 등록 화면 (관리자만 접근 가능 : hasRole('ROLE_ADMIN')
					> 접근 제한에 걸려 스프링 시큐리티가 기본적으로 제공하는 로그인 페이지로 이동합니다.
		
		4. 로그인 처리
		
			- 메모리 상에 아이디와 패스워드를 지정하여 로그인을 처리한다.
			- 스프링 시큐리티 5버전부터는 패스워드 암호화 처리기를 반드시 이용하도록 변경이 되었다.
			- 암호화 처리기를 사용하지 않도록 "{noop}" 문자열을 비밀번호 앞에 사용한다.
			
			# 환경 설정
			
				- 시큐리티 Config 설정
					> protected InMemoryUserDetailsManager inMemoryUserDetailsManager()
				
				- 비밀번호 암호화 설정
					> 암호화 처리기를 사용하지 않도록 설정하기 위해서는 비밀번호 문자열 앞에 '{noop}'을 붙인다.
					
			# 화면 설명
			
				- 일반 게시판 등록 화면
					> 접근 제한에 걸려 스프링 시큐ㅣㄹ티가 기본적으로 제공하는 로그인 페이지가 연결되고, 
						일반 회원 등급인 ROLE_MEMBER 권한을 가진 member 계정으로 로그인 후 해당 페이지로 접근 가능
					
					> 관리자 등급인 admin 계정은 ROLE_MEMBER도 가지고 있는 계정이므로 해당 페이지로 접근 가능
				- 공지사항 게시판 등록 화면
					> 접근 제한에 걸려 스프링 시큐리티가 기본적으로 제공하는 로그인 페이지와 연결되고,
						관리자 등급인 ROLE_ADMIN 권한을 가진 admin 계정으로 로그인 후 해당 페이지로 접근 가능
				
		5. 접근 거부 처리
		
			- 접근 거부가 발생한 상황을 처리하는 접근 거부 처리자의 URI를 지정할 수 있다.
			
			# 환경 설정
			
				- 시큐리티 Config 설정
					> protected SecurityFilterChain filterChain(HttpSecurity http)
					> filterChain 메소드 안에서 accessDenied시에 처리할 내용 추가
					> http.exceptionHandling((exception) -> exception.accessDeniedPage());
			
			# 접근 거부 처리
			
				- 접근 거부 처리 컨트롤러 설정
					> security.CommonController
				- 접근 거부 페이지
					> chapt11/accessError.jsp
					
			# 화면 설명
				
				- 일반 게시판 등록 페이지
					> 접근 제한에 걸려 스프링 시큐리티가 제공하는 로그인 페이지가 나타나고,
						회원 권한을 가진 계정으로 접근 시 접근 가능
				- 공지사항 게시판 등록 페이지
					> 접근 제한에 걸려 스프링 시큐리티가 제공하는 로그인 페이지가 나타나고,
						회원 권한을 가진 계정으로 접근 시에 공지사항 게시판 등록 페이지는
						관리자 권한으로만 접근 가능하므로 접근이 거부된다.
						이때, access-denied0handler로 설정되어 있는 URI로 이동하고 해당 페이지에서
						접근이 거부되었을 때 보여질 페이지의 정보가 나타난다.
						
		6. 사용자 정의 접근 거부 처리자
		
			- 접근 거부가 발생한 상황에 단순 메세지 처리 이상의 다양한 처리를 하고 싶다면
				AccessDeniedHandler를 직접 구현하여야 한다.
				
			# 환경 설정
			
				- 시큐리티 Config 설정
					> protected SecurityFilterChain filterChain(HttpSecurity http)
					> filterChain 메소드 안에서 accessDenied시에 처리할 내용 추가
					> http.exceptionHandling((exception) -> exception.accessDeniedHandler());
			
			# 접근 거부 처리
			
				- 접근 거부 처리 컨트롤러 작성
					> security.CustomAccessDeniedHandler
					
			# 접근 거부 처리자 클래스 정의
			
				- 사용자가 정의한 접근 거부 처리자
					> CustomAccessDeniedHandler 클래스 정의
					> AccessDeniedHandler 인터페이스를 참조 받아 handle() 메소드를 재정의한다.
					> 해당 요청에 의해 접근이 거부되었을 때,
						CustomAccessDeniedHandler 클래스가 exception Handling에 의해 동작하여
						response 내장객체를 활용하여 '/accessError' URL로 이동하여
						접근 거부시 보여질 페이지로 이동한다.
			
			# 화면 설명
			
				- 일반 게시판 등록 페이지
					> 접근 제한에 걸려 스프링 시큐리티가 제공하는 로그인 페이지가 나타나고,
						회원 권한을 가진 계정으로 접근 시 접근 가능
						
				- 공지사항 게시판 등록 페이지
					> 접근 제한에 걸려 스프링 시큐리티가 제공하는 로그인 페이지가 나타나고,
						회원 권한을 가진 계정으로 접근 시에 
						공지사항 게시판 등록 페이지는 관리자 권한만 접근 가능하므로 접근이 거부된다.
						이때, access-denied-handler로 설정되어 있는 사용자 정의 클래스의 요청으로 이동하고
						해당 페이지에서 접근이 거부되었을 때 보여질 페이지로 이동한다.
		
		7. 사용자 정의 로그인 페이지
		
			- 기본 로그인 페이지가 아닌 사용자가 직접 정의한 로그인 페이지를 사용한다.
			
			# 환경 설정
				
				- 시큐리티 Config 설정
					> protected SecurityFilterChain filterChain(HttpSecurity http)
					> filterChain 메소드 안에서 .httpBasic() 메소드를 .formLogin()으로 변경
					> .formLogin((login) -> login.loginPage("/login");
					
			# 로그인 페이지 정의
			
				- 사용자가 정의한 로그인 컨트롤러
					> chapt11.login.LoginController 메소드 생성
				- 사용자 정의 로그인 페이지
					> chapt11/loginForm.jsp
					
		8. 로그인 성공 처리
		
			- 로그인을 성공한 후에 로그인 이력 로그를 기록하는 등의 동작을 하고 싶은 경우가 있다.
				이런 경우에 AuthenticationSuccessHandler라는 인터페이스를 구현해서 로그인 성공 처리자로 지정할 수 있다.
				
			# 환경 설정
			
				- 시큐리티 Config 실행
					> protected SecurityFilterChain filterChain(HttpSecurity http)
					> filterChain 메소드 안에서 .successHandler(new CustomLoginSuccessHandler());
					> .formLogin((login) -> login.loginPage("/login").successHandler());
				
			# 로그인 성공 처리자 클래스 정의
			
				- 로그인 성공 처리자
					> AuthenticationSuccessHandler 인터페이스를 직접 구현하여 인증 전에 접근을 시도한 URL로 리다이렉트 한다.
					
			# 화면 설정
			
				- 일반 게시판 등록 화면
					> 사용자가 정의한 로그인 페이지에서 회원 권한에 해당하는 계정으로 로그인 시, 
						성공했다면 성공 처리자인 CustomLoginSuccessHandler 클래스로 넘어가
						넘겨받은 파라미터들 중 authentication안에 principal로 User 정보를 받아서
						username과 password를 출력한다.
				
		9. 로그아웃 처리
		
			- 로그아웃을 위한 URI를 지정하고, 로그아웃 처리 후에 별도의 작업을 하기 위해서 사ㅛㅇㅇ자가 직접 구현한 처리자를 등록할 수 있다.
			
			# 환경 설정
				
				- 시큐리티 Config 설정
					> protected SecurityFilterChain filterChain(HttpSecurity http)
					> filterChain 메소드 안에서 .logout();
					> .logout(
					>			(logout) -> logout.logoutUrl("/logout")
					>							.invalidateHttpSession(true).deleteCookies("JSESSION)ID")
					> );
					> 로그아웃을 처리하기 위한 요청 URL을 등록하고(POST),
					> 로그아웃 시 Session 무효화 및 세션과 관련된 쿠키를 삭제한다.
					> 로그아웃을 실행하기 위한 URL은 기본값 "/logout"이다.
						CSRF 보호가 활성화된 경우(기본값) 요청도 POST여야 합니다.
						즉, 로그아웃을 트리거하면 기본적으로 POST "/logout"이 필요합니다.
						그런데 CSRF 보호가 비활성화된 경우 모든 HTTP 메서드가 허용됩니다.
						CSRF 보호가 비활성화인 경우, GET 방식의 "/logout"으로도 로그아웃 처리가 가능합니다.
			
			# 로그아웃 페이지 정의
			
				- 사용자가 정의한 로그아웃 컨트롤러
					> chapt11.login.LoginController 메소드 생성
				- 사용자 정의 로그아웃 페이지
					> chapt11/logoutForm.jsp
					
			# 로그아웃 처리
			
				- POST 방식의 '/logout'이 실행되면 아래의 내용이 삭제된다.
					> 연결된 세션 초기화(session)
					> 인증 토큰 삭제(CSRF Token)
					> SecurityContext 삭제
					> Remember me Cookie 정보 삭제(자동 로그인을 위한 쿠키 정보)
					> 이후 자동 리다이렉트('/login' 페이지로)
				
		10. UserDetailsService 재정의
			
			- 스프링 시큐리티의 UserDetailsService를 구현하여 사용자 상세 정보를 얻어오는 메소드를 재정의한다.
			
			# 환경 설정
			
				- pom.xml 설정
					> 데이터베이스 의존 관계 라이브러리
					> spring-boot-starter-jdbc
					> mybatis-spring-boot-starter
					> ojbdc
				
				- 데이터베이스 준비
					> member, member_auth
					
				- 시큐리티 config 설정
					> protected AuthenticationManager authenticationManager() 인증 매니저 등록
					> authenticationManager 안에 AuthenticationProvider 인증 제공자 등록
					> authenticationManager 안에 UserDetailsService 사용자 정의 UserDetailsService 등록
					> authenticationManager 안에 PasswordEncoder passwordEncoder() 비밀번호 암호화 등록
				
				- 비밀번호 암호화 설정
					> MemberController 내, init() 메소드 정의
					> init() 메소드르 통해 암호화된 비밀번호 확인 후, DB 설정
					
				*** 비밀번호 암호화 처리기 클래스 정의
				- 비밀번호 암호화 처리기
					> 스프링 시큐리티 5버전부터는 기본적으로 PasswordEncoder를 지정해야 하는데, 제대호 하려면 생성된 사용자 테이블(member)
						에 비밀번호를 암호화하여 지정해야 한다.
						암호화를 지정하기 위해서는 SHA-2방식의 8바이트 Hash 암호를 생성하는
						BCryptPasswordEncoder를 이용하여 암호화 설정을 진행한다.
						
				*** BCryptPasswordEncoder 클래스를 활용한 단방향 비밀번호 암호화
				- encode() 메서드를 통해서 SHA-2 방식의 8바이트 Hash 암호를 매번 랜덤하게 생성한다.
				- 똑같은 비밀번호를 입력하더라도 암호화되는 문자열은 매번 다른 문자열을 반홚ㄴ다.
				- 비밀번호를 입력하면 암호화된 비밀번호로 인코딩하는데, 암호화된 비밀번호와 테이블에 있는 암호화된 비밀번호가 일치하는지를 파악 후,
					일치하면 로그인 성공으로 다음 스탭을 진행한다.
				- BCryptPasswordEncoder 클래스의 encode() 메소드를 통해 만들어지는 암호화된 hash 다이제스트들은
					입력한 비밀번호 문자에 해당하는 수십억개의 다이제스트들 중에서
					일치하는 다이제스트가 존재할 경우 비밀번호의 일치로 보고 인증을 성공시켜 준다.
				
		11. 스프링 시큐리티 표현식
		
			- 스프링 시큐리티 표현식을 이용하면 인증 및 권한 정보에 따라 화면을 동적으로 구성할 수 있고 로그인 한 사용자 정보를 보여줄 수도 있다.
			
			# 공통 표현식
			
				- hasRole([role]) : 해당 롤이 있으면 true
				- hasAnyRole([role1,role2]) : 여러 롤들 중에서 하나라도 해당하는 롤이 있으면 true
				- principal : 인증된 사용자의 정보(UserDetails 인터페이스를 구현한 클래스의 객체)를 의미
				- authentication : 인증 사용자의 인증 정보(Authentication 인터페이스를 구현한 클래스의 객체)를 의미
				- permitAll : 모든 사용자에게 허용
				- denyAll : 모든 사용자에게 거부
				- isAnonymous() : 익명의 사용자의 경우(로그인을 하지 않은 경우도 해당)
				- isAuthenticated() : 인증된 사용자면 true
				- isFullyAuthenticated() : Remember-me로 인증된 것이 아닌 일반적인 방법으로 인증된 사용자인 경우 true
			
		 	# 표현식 사용
		 	
		 		- 표현식을 이용하여 동적 화면 구성
		 			> home.jsp 수정
	 			- 로그인 한 사용자 정보 보여주기
	 				> board/register.jsp 수정
	 				> notice/register.jsp 수정
	 				
 				- 표현식 사용해보기
 					> <sec:authentication property="">
 					> <sec:authorize access="">
				
		12. 자동 로그인
		
			- 로그인하면 특정 시간 동안 다시 로그인 할 필요가 없는 기능이다.
			- 스프링 시큐리티는 메모리나 데이터베이스를 사용하여 처리한다.
			
			# 환경 설정
			
				- 데이터베이스 준비
					> persistent_logins 테이블
				
				- 로그인 페이지 수정
					> loginForm.jsp 수정
					> remember-me 체크박스 추가
				
				- 시큐리티 Config 설정
					> protected PersistentTokenRepository persistentTokenRepository() 등록
					> protected SecurityFilterChain filterChain(HttpSecurity http)
					> filterChain 메소드 안에서 .rememberMe()
					> .rememberMe(
					>				(config) -> config.tokenValiditySeconds(86400)
					>									.tokenRepository(persistentTokenRepository())
					> );
					> .deleteCookies("remeber-me") 추가
				
				
				
				
				
				
				
				
				
				
	 */
}
