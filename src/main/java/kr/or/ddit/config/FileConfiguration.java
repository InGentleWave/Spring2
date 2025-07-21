package kr.or.ddit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 파일 업로드 시, local 경로에 파일을 업로드 하기 위한 설정
@Configuration
public class FileConfiguration implements WebMvcConfigurer{

	// 웹 주소와 파일의 위치를 매핑한다.
	// 주소 입력창에 /localhost/upload/** 로 입력하면 시스템은 C:/upload/ 경로로 들어가
	// 주소에 입력된 파일명과 일치하는 파일을 찾으려 할 것이다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 웹 브라우저에서 http://localhost/upload/** 로 요청 시,
		// local 경로 내, C:/upload/에서 파일을 찾는다.
		registry.addResourceHandler("/upload/**")
				.addResourceLocations("file:///c:/upload/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}

	
}
