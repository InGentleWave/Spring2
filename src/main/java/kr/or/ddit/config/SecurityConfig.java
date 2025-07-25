package kr.or.ddit.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import jakarta.servlet.DispatcherType;
import kr.or.ddit.security.CustomAccessDeniedHandler;
import kr.or.ddit.security.CustomLoginFailureHandler;
import kr.or.ddit.security.CustomLoginSuccessHandler;
import kr.or.ddit.security.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;

// @Configuration 어노테이션 스프링 설정 클래스,
// 해당 클래스를 싱글톤을 적용하여 하나의 인스턴스로 관리하면서 bean 등록을 위해 설정한다.
@Slf4j
@Configuration
public class SecurityConfig {
	
	/*
		Spring Security 6.1.0 이후부터 람다식의 형태로 바뀌면서 deprecated된 메소드가 상당히 많아졌다.
		하여, 대부분 람다식의 표현으로 사용하지 않으면 메소드의 밑줄이 그어지는 deprecated의 형태로 warning을 알려준다.
		그렇기 때문에, 상위 버전에서는 deprecated된 메소드를 업데이트 된 메소드의 형태로 사용한다.
	 */
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// # CSRF 설정
		// CSRF 공격에 방어하기 위한 방안으로 csrf를 설정하는데 REST에서는 csrf를 disable 설정이 기본 설정
//		http.csrf((csrf) -> csrf.disable());
		
		http.authorizeHttpRequests(
				(authorize) ->
					// forward는 모두 접근 가능
					authorize.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ASYNC).permitAll()
						// 서버쪽에서 정적 자원을 관리한다면 Static 하위 정적 파일들을 permitAll로 설정한다.
						.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
						.requestMatchers("/resources/**").permitAll()
						// 시큐리티 일반게시판 목록, 시큐리티 공지사항 게시판 목록 모두 접근 가능
						.requestMatchers("/security/board/list", "/security/notice/list").permitAll()
						// 시큐리티 일반 게시판 등록은 회원과 관리자만 접근 가능
						// 'ROLE_'를 제외한 권한명을 설정한다.
						.requestMatchers("/security/board/register").hasAnyRole("MEMBER","ADMIN")
						// 시큐리타 공지사항 게시판 등록은 관리자만 접근 가능
						.requestMatchers("/security/notice/register").hasRole("ADMIN")
						.requestMatchers("/").permitAll()
						// 다른 요청은 인증된 사용자가 아닌 모든 사용자 접근 가능
						.anyRequest().permitAll()
		);
		
		// 로그인 인증 방식은 'BASIC'에 해당하는 기본 인증 방식으로 요청
//		http.httpBasic(Customizer.withDefaults());
		
		
		// 5. 접근 거부 처리
//		http.exceptionHandling((exception)->exception.accessDeniedPage("/accessError"));
		
		// 6. 사용자 정의 접근 거부 처리자
		http.exceptionHandling((exception)->exception.accessDeniedHandler(new CustomAccessDeniedHandler()));
		
		// 7. 사용자 정의 로그인 페이지
		http.httpBasic((basic) -> basic.disable());
//		http.formLogin((login) -> login.loginPage("/login"));
		
		// 8. 로그인 성공 처리
		http.formLogin((login) -> login.loginPage("/login")
									.successHandler(new CustomLoginSuccessHandler())
									.failureHandler(new CustomLoginFailureHandler()));
		
		// 9. 로그아웃 처리
		http.logout(
				(logout) -> logout.logoutUrl("/logout")				// 로그아웃 처리 URL
									.invalidateHttpSession(true)	// 로그아웃 처리 시, session 삭제
									.deleteCookies("JSESSION_ID", "remember-me")	// 로그아웃 시, 쿠키 삭제
				);
		
		// 12. 자동 로그인 처리
		http.rememberMe(
				(config) -> config.tokenValiditySeconds(86400)
									.tokenRepository(persistentTokenRepository()));
		return http.build();
	}

	// 괄호 안의 데이터(여기서는 UserDetailsService)가 Bean 등록이 되어있으면 @ConditionalOnMissingBean() 어노테이션이 붙은 메서드는 Bean 등록되지 않음
	@ConditionalOnMissingBean(UserDetailsService.class)
	@Bean
	protected InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		// 'ROLE_MEMBER' 권한을 가진 회원 정보와 'ROLE_ADMIN', 'ROLE_MEMBER'권한을 가진 관리자 정보를 등록
		// 회원 권한을 가진 계정 생성
		UserDetails member = User.withUsername("m001")		// 사용자 id 설정
								.password("{noop}1234") 	// 사용자 pw 설정
								.authorities("ROLE_MEMBER")	// 권한 설정(회원)
								.build();
		UserDetails admin = User.withUsername("a001")		// 관리자 id 설정
				.password("{noop}1234") 					// 관리자 pw 설정
				.authorities("ROLE_MEMBER","ROLE_ADMIN")	// 권한 설정(회원, 관리자)
				.build();
		
		return new InMemoryUserDetailsManager(member,admin);
	}
	
	// 10. UserDetailsService 재정의
	// 인증 관리자 관련 설정
	// 사용자 정보를 가져올 서비스를 재정의하거나, 인증방법, LDAP/JDBC 기반 인증 등을 설정할 때 사용한다.
	@Bean
	protected AuthenticationManager authenticationManager(
			HttpSecurity http,
			BCryptPasswordEncoder bCryptPasswordEncoder,
			UserDetailsService userDetailsService
			) {
		// 인증 제공자 인증 처리
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		// # userDetailsService()
		// - 사용자 정보를 가져올 서비스를 설정한다. 이때 설정하는 클래스는 UserDetailsService를 상속받는 클래스여야 한다.
		// # passwordEncoder()
		// - 비밀번호 암호화하기 위한 인코더를 설정한다.
		authProvider.setUserDetailsService(customUserDetailsService);
		authProvider.setPasswordEncoder(bCryptPasswordEncoder);
		return new ProviderManager(authProvider);
	}
	
	// 비밀번호 암호화를 위한 PasswordEncoder 재정의
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 12. 자동 로그인 설정
	// remember-me 데이터베이스 연결
	@Bean
	protected PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		
		try {
			tokenRepository.getJdbcTemplate().execute(JdbcTokenRepositoryImpl.CREATE_TABLE_SQL);
		} catch (BadSqlGrammarException e) {
			log.error("persistent_logins 테이블이 이미 존재합니다!");
		} catch (Exception e) {
			log.error("자동 로그인 테이블 생성 중, 에러 발생");
			e.printStackTrace();
		}
		
		return tokenRepository;
	}
}
