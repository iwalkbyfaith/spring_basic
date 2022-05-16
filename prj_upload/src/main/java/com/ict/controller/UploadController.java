package com.ict.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;	// sql과 관련된 것이 아니기 때문에 util로 해야함.
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Log4j
@Controller
public class UploadController {
	
	// ■ 05.16 썸네일) 이미지만 썸네일을 만들 수 있으므로(문서,pdf는 노노), 이미지 파일이 맞는지 확인.
		// image = true, 아니면 false
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			return contentType.startsWith("image");
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	// ■ 05.16 파일 업로드) 폴더를 일자 별로 관리 (교안 13의 23)
	private String getFolder() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);	// 날짜를 얻어와서 폴더명으로 처리
		
	}
	
	
	
	// ■ 폼으로 파일 업로드하기
	
		// ● get 파일 업로드 폼으로 이동
		@GetMapping("/uploadForm")
		public void uploadForm() {
			log.info("upload form");
		}
		    
		// ● post 폼에서 파일을 첨부해서 uploadFoler로 보내기
		@PostMapping("/uploadFormAction")			// uploadFile에 파일들이 줄줄이 들어옴
		public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
			
			// ● 어떤 폴더에 저장할 것인지 위치 지정하기
			String uploadFolder = "C:\\upload_data\\temp";
			
			// ● 05.16 추가) 해당 폴더가 존재하는지 체크해서 -> 존재하지 않는다면 자동으로 생성하는 로직
			File uploadPath = new File(uploadFolder, getFolder());
			log.info("upload path: "+ uploadPath);
			
			if(uploadPath.exists() == false) {
				uploadPath.mkdirs(); // make directories
			}
			
			for(MultipartFile multipartFile : uploadFile) {
				log.info("--------이름과 사이즈 감지---------");
				log.info("Upload File Name : " + multipartFile.getOriginalFilename());
				log.info("Upload File Size : " + multipartFile.getSize());
				log.info("--------이름과 사이즈 감지---------");
				
				
				String uploadFileName = multipartFile.getOriginalFilename();
				log.info("변수) uploadFileName -> " + uploadFileName);
				
				// ● 파일 경로에서 이름만 떼오기 (근데 동일해서 이 코드가 없어도 될 것 같긴 함. 추후에 경로가 이상하게 들어오는 경우에 사용하면 됨)
				uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
				log.info("변수) uploadFileName(.substring 적용ver.) -> " + uploadFileName);
				log.info("------------------------");
				

				// ● 외부 자료형을 자바 내부에서 쓸 수 있도록 자바 자료형으로 바꿈 (어디에 어떤 이름으로 업로드할거다)
					//File savaFile = new File(uploadFolder, multipartFile.getOriginalFilename());
				
					// uuid 발급 부분
					UUID uuid = UUID.randomUUID();
					uploadFileName = uuid.toString() + "_" + uploadFileName;
					
				//File saveFile = new File(uploadPath, uploadFileName);			// 05.16 코드 위치 변경(↓)
				
				// ● try~catch를 이용해서 문제가 생긴 경우를 대비함
				try {
					// 1)그림이건 아니건 일단 업로드(파일 업로드할 떄 쓰는 로직)
					File saveFile = new File(uploadPath, uploadFileName); 		// 05.16 코드 위치 변경
					multipartFile.transferTo(saveFile);
					
					// 2) 05.16 이미지인지 아닌지 판단해서 썸네일을 만들어서 업로드
						// 업로드 후, 폴더를 확인해보면 썸네일 이미지 이름 앞에는 "s_어쩌구" 처럼 s_가 추가 되어있고,
						// 썸네일 이미지는 해당도가 많이 낮은 것을 확인할 수 있다.
					if(checkImageType(saveFile)) {
						FileOutputStream thumbnail = 
								new FileOutputStream(
										new File(uploadPath, "s_" + uploadFileName));
						
						Thumbnailator.createThumbnail(
								multipartFile.getInputStream(), thumbnail, 100, 100);
						thumbnail.close();
					}
						
				}catch(Exception e) {
					log.error(e.getMessage());
				}
			}// end for
			
		}
	
	
	
	// ■ ajax로 파일 업로드하기
	
		// get
		@GetMapping("/uploadAjax")
		public void uploadAjax() {
			log.info("upload ajax");
		}
		
		// post
		@PostMapping("/uploadAjaxAction")
		public void uploadAjaxPost(MultipartFile[] uploadFile) {
			
			log.info("ajax post update!");
			
			// 어떤 폴더에 저장할 것인지 위치 지정
			String uploadFolder = "C:\\upload_data\\temp";
			
			// ● 05.16 추가) 해당 폴더가 존재하는지 체크해서 -> 존재하지 않는다면 자동으로 생성하는 로직
			File uploadPath = new File(uploadFolder, getFolder());
			log.info("upload path: "+ uploadPath);
			
			if(uploadPath.exists() == false) {
				uploadPath.mkdirs(); // make directories
			}
			
			
			for(MultipartFile multipartFile : uploadFile) {
				
				log.info("------------▼-----------");
				log.info("단순 로그) Upload File Name : " + multipartFile.getOriginalFilename());
				log.info("단순 로그) Upload File Size : " + multipartFile.getSize());
				
				
				String uploadFileName = multipartFile.getOriginalFilename();
				log.info("변수) uploadFileName -> " + uploadFileName);
				
				// ● 파일 경로에서 이름만 떼오기 (근데 동일해서 이 코드가 없어도 될 것 같긴 함. 추후에 경로가 이상하게 들어오는 경우에 사용하면 됨)
				uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
				log.info("변수) uploadFileName(.substring 적용ver.) -> " + uploadFileName);
				log.info("------------------------");
				
				
				
				// ● 05.16 날짜별로 폴더를 생성하는 로직으로 바뀌면서 경로도 바꾸어줌.
					//File saveFile = new File(uploadFolder, uploadFileName);
				
					// uuid 발급 부분
					UUID uuid = UUID.randomUUID();
					uploadFileName = uuid.toString() + "_" + uploadFileName; // 파일 이름에 uuid를 붙여서 보냄
				
					//File saveFile = new File(uploadPath, uploadFileName);
				
				try {
					// 그림이건 아니건 일단 업로드(파일 업로드할 떄 쓰는 로직)
					File saveFile = new File(uploadPath, uploadFileName); 		// 05.16 코드 위치 변경
					multipartFile.transferTo(saveFile);
					
					// 05.16 썸네일) 이미지인지 아닌지 판단해서 썸네일을 만들어서 업로드 
					if(checkImageType(saveFile)) {
						FileOutputStream thumbnail = 
								new FileOutputStream(
										new File(uploadPath, "s_" + uploadFileName));
						
						Thumbnailator.createThumbnail(
								multipartFile.getInputStream(), thumbnail, 100, 100);
						thumbnail.close();
						
					}
				}catch(Exception e) {
					log.error(e.getMessage());
				}
			}// end for
		}
		
		
		

		
	
	
	
	
	
	

}
