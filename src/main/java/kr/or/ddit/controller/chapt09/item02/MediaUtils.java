package kr.or.ddit.controller.chapt09.item02;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MediaUtils {
	private static Map<String,MediaType> mediaMap;
	
	// static 그룹 선언
	static {
		mediaMap = new HashMap<>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
	}
	
	public static MediaType getMediaType(String formatName) {
		return mediaMap.get(formatName.toUpperCase());
	}

}
