package com.ict.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class UploadController {
	
	
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
			
			for(MultipartFile multipartFile : uploadFile) {
				log.info("--------이름과 사이즈 감지---------");
				log.info("Upload File Name : " + multipartFile.getOriginalFilename());
				log.info("Upload File Size : " + multipartFile.getSize());
				log.info("--------이름과 사이즈 감지---------");
				
				// ● 외부 자료형을 자바 내부에서 쓸 수 있도록 자바 자료형으로 바꿈 (어디에 어떤 이름으로 업로드할거다)
				File savaFile = new File(uploadFolder, multipartFile.getOriginalFilename());
				
				// ● try~catch를 이용해서 문제가 생긴 경우를 대비함
				try {
					// 원하는 폴더로 전송
					multipartFile.transferTo(savaFile);
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
			
			String uploadFolder = "C:\\upload_data\\temp";
			
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
				
				File saveFile = new File(uploadFolder, uploadFileName);
				
				try {
					multipartFile.transferTo(saveFile);
				}catch(Exception e) {
					log.error(e.getMessage());
				}
			}// end for
		}
	
	
	
	
	
	

}
