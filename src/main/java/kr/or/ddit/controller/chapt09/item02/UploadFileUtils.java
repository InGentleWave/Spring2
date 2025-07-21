package kr.or.ddit.controller.chapt09.item02;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadFileUtils {
	/*
		1. '/년/월/일/' 폴더 경로를 만들고, 해당 경로를 리턴받는다.
		2. 서버 업로드 경로 + "/년/월/일/" + UUID_원본파일명 형태로 파일 복사를 진행한다.
		3. 업로드 한 파일이 이미지라면 's_'가 붙은 썸네일 이미지를 만든다.
	 */
	
	public static String uploadFile(String uploadPath, String originalFilename, byte[] bytes) throws Exception {
		// UUID 생성
		UUID uuid = UUID.randomUUID();		// 중복되지 않는 UUID 생성
		String savedName = uuid + "_" + originalFilename;
		
		// 2025/07/16 폴더 경로를 만들고, /2025/07/16/ 폴더 경로를 리턴한다.
		String savedPath = calcPath(uploadPath);
		
		// 배포된 서버 업로드 경로 + '/2025/07/16' + UUID_원본파일명으로 File target을 하나 만듬
		File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(bytes, target);	// 위에서 만들어진 경로와 파일명을 가지고 파일 복사를 진행한다.
		
		// 확장자 추출
		String formatName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
		
		// \2025\07\16 경로를 /경로로 변경 후 원본 파일명을 붙인다.
		// File.separatorChar는 시스템에 따라 달라지는 기본 이름 구분 기호입니다.
		// UNIX 시스템에서는 해당 필드의 값이 '/'이고, Microsoft Windows 시스템에서는 '\\'입니다.
		String uploadedFileName = savedPath.replace(File.separatorChar, '/') + "/" + savedName;
		
		// uploadedFileName을 이용한 썸네일 이미지 제작
		// 확장자가 이미지 파일이면 's_'가 붙은 파일의 썸네일 이미지 파일을 생성한다.
		if(MediaUtils.getMediaType(formatName) != null) {
			makeThumbnail(uploadPath, savedPath, savedName);
		}
		
		return uploadedFileName;
	}

	// 썸네일 이미지 만들기
	private static void makeThumbnail(String uploadPath, String savedPath, String savedName) throws Exception {
		// 썸네일 이미지를 만들기 위해 원본 이미지를 읽는다.
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + savedPath +File.separator+ savedName));
		// 썸네일 이미지를 만들기 위한 설정을 진행
		// Method.AUTOMATIC : 최소 시간 내에 가장 잘 보이는 이미지를 얻기 위한 사용 방식
		// Mode.FIT_TO_HEIGHT : 이미지 방향과 상관없이 주어진 높이 내에서 가장 잘 맞는 이미지로 계산
		// targetSize : 값 100, 정사각형 사이즈로 100X10의 이미지를 만들어줌
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
		String thumbnailName = uploadPath + savedPath + File.separator + "s_" + savedName;
		File newFile = new File(thumbnailName);
		
		// 확장자 추출
		String formatName = savedName.substring(savedName.lastIndexOf(".")+1);
		
		// 's_'가 붙은 썸네일 이미지를 만든다
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
	}

	// /년/월/일 폴더 경로 생성 후 경로 문자열 가져오기
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator + cal.get(Calendar.YEAR);	// /2025
		// new DecimalFormat("00") :: 두 자리에서 빈자리는 0으로 채움
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);	// /2025/07
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));	// /2025/07/16
		
		// 년월일 폴더 구조에 의한 폴더 생성
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}
	
	// 가변인자
	// 키워드 '...'을 사용한다.
	// [사용법] 타입...변수명 형태로 사용
	// 순서대로 yearPath, monthPath, datePath가 배열로 들어가 처리
	private static void makeDir(String uploadPath, String...paths) {
		// /2025/07/16 폴더 구조가 존재한다면 return
		// 만들려던 폴더 구조가 이미 만들어져 있는거니까 return
		if(new File(uploadPath + paths[paths.length - 1]).exists()) {
			log.info("경로 존재함");
			return;
		}
		for(String path : paths) {
			log.info("경로 존재함");
			File dirPath = new File(uploadPath + path);
			if(!dirPath.exists()) {
				dirPath.mkdirs();
			}
		}
	
	}
	

}
