package kr.or.ddit.controller.chapt07;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("chapt07/el")
public class Chapter07ELController {

	/*
		8. EL 함수
			- JSTL은 표현언어(EL)에서 사용할 수 있는 함수를 제공한다.
			
			1) EL 함수 목록
			
			fn:contains(str1, str2)
				- 지정한 문자열이 포함되어 있는지 판단
			fn:containsIgnoreCase(str1,str2)
				-  지정한 문자열이 대문자/소문자를 구분하지 않고 포함되어 있는지 판단
			fn:startsWith(str1, str2)
				- 지정한 문자열로 시작하는지 판단한다.
			fn:endsWith(str1, str2)
				- 지정한 문자열로 끝나는지 판단한다.
			fn:indexOf(str1, str2)
				- 지정한 문자열이 처음으로 나왔을 때의 인덱스를 구한다.
			fn:length(obj)
				- 컬렉션 또는 배열의 요소 개수, 문자열 길이를 구한다.
			fn:escapeXml(str)
				- 지정한 문자열을 XML 구문으로 해석되지 않도록 이스케이프한다.
			fn:replace(str, src, dest)
				- 문자열을 치환한다.
			fn:toLowerCase(str)
				- 문자열을 소문자로 변환한다.
			fn:toUpperCase(str)
				- 문자열을 대문자로 변환한다.
			fn:trim(str)
				- 문자열을 trim 한다.
			fn:substring(str,idx1,idx2)
				- 지정한 범위에 해당하는 문자열을 잘라낸다.
			fn:substringAfter(str1,str2)
				- 지정한 문자열에 일치하는 이후의 문자열을 잘라낸다.
			fn:substringBefore(str1,str2)
				- 지정한 문자열에 일치하는 이전의 문자열을 잘라낸다.
			fn:join(array, str)
				- 분할된 데이터를 구분자로 설정된 텍스트와 합쳐진 하나의 문자열로 만든다
			fn:split(str1, str2)
				- 문자열을 구분자로 분할해서 문자열 배열을 만든다.
			
	 */
	
	@GetMapping("/home0101")
	public String home0101(Model model) {
		String str = "<font>Hello World!</font>";
		model.addAttribute("str",str);
		return "chapt07/el/home0101";
	}
}
