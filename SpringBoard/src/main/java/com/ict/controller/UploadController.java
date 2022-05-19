package com.ict.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;	// sql과 관련된 것이 아니기 때문에 util로 해야함.
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ict.domain.BoardAttachVO;

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
		
		// ※ 05.17 일반 컨트롤러에서 Rest호출을 처리하고 싶다면? (@ResponseBody 사용). 코드 수정(교안13의 31)
			// ● post 폼에서 파일을 첨부해서 uploadFoler로 보내기
			@PostMapping(value="/uploadFormAction",							// uploadFile에 파일들이 줄줄이 들어옴
						 produces = MediaType.APPLICATION_JSON_UTF8_VALUE)	// 리턴할 내용 : jason		
			@ResponseBody
			public ResponseEntity<List<BoardAttachVO>> uploadFormPost(MultipartFile[] uploadFile) {
				
				// ● 05.17 BoardAttachVO는 파일 한개, 현재 파일 업로드는 여러 파일을 동시에 업로드 해야하므로 List 사용
				List<BoardAttachVO> list = new ArrayList<>();
				
				// ● 05.17 폴더 경로 받아오기
				String uploadFolderPath = getFolder();
				
				// ● 어떤 폴더에 저장할 것인지 위치 지정하기
				String uploadFolder = "C:\\upload_data\\temp";
				
				// ● 05.16 추가) 해당 폴더가 존재하는지 체크해서 -> 존재하지 않는다면 자동으로 생성하는 로직
				File uploadPath = new File(uploadFolder, uploadFolderPath);
				log.info("upload path: "+ uploadPath);
				
				if(uploadPath.exists() == false) {
					uploadPath.mkdirs(); // make directories
				}
				
				for(MultipartFile multipartFile : uploadFile) {
					
					// ● 05.17 파일 정보를 저장할 DTO 생성(밑에서 setter 이용해서 파일 이름 넣어야 함)
						// 05.19 DTO -> VO로 변경해야하지만 귀찮아서 변수명은 그냥 씀
					BoardAttachVO attachDTO = new BoardAttachVO();
					
					
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
					
					
					// ● 05.17 setter 이용해서 파일 이름 넣기 (상단에 만든 DTO)
					attachDTO.setFileName(uploadFileName);
					
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
						
						// 05.17 uuid와 저장할 폴더 경로를 setter로 입력받는다.
						attachDTO.setUuid(uuid.toString());
						attachDTO.setUploadPath(uploadFolderPath);
						
						// 2) 05.16 이미지인지 아닌지 판단해서 썸네일을 만들어서 업로드
							// 업로드 후, 폴더를 확인해보면 썸네일 이미지 이름 앞에는 "s_어쩌구" 처럼 s_가 추가 되어있고,
							// 썸네일 이미지는 해당도가 많이 낮은 것을 확인할 수 있다.
						if(checkImageType(saveFile)) {
							
							// 05.17 if문에서 이미지가 맞는 경우 진입. setImage = true는 DTO에서 이미지가 맞는지 여부를 의미
							attachDTO.setFileType(true); // setImage(true)							
							
							FileOutputStream thumbnail = 
									new FileOutputStream(
											new File(uploadPath, "s_" + uploadFileName));
							
							Thumbnailator.createThumbnail(
									multipartFile.getInputStream(), thumbnail, 100, 100);
							thumbnail.close();
						}
						// 05.17 ArrayList에 개별 DTO를 집어넣어주어야 출력이 됨
						list.add(attachDTO);
							
					}catch(Exception e) {
						e.printStackTrace();
						log.error(e.getMessage());
					}
				}// end for
				
				// 05.17
				return new ResponseEntity<>(list, HttpStatus.OK);
				
			}// /uploadFormAction 종료
	
	
	
	// ■ ajax로 파일 업로드하기
	
		// get
		@GetMapping("/uploadAjax")
		public void uploadAjax() {
			log.info("upload ajax");
		}
		
		// post
		@PostMapping(value = "/uploadAjaxAction",
					 produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public ResponseEntity<List<BoardAttachVO>> uploadAjaxPost(MultipartFile[] uploadFile) {
			
			log.info("ajax post update!");
			
			// ● 05.17 AttachFileDTO는 파일 한개, 현재 파일 업로드는 여러 파일을 동시에 업로드 해야하므로 List 사용
			List<BoardAttachVO> list = new ArrayList<>();
			
			// ● 05.17 폴더 경로 받아오기
			String uploadFolderPath = getFolder();
			
			// 어떤 폴더에 저장할 것인지 위치 지정
			String uploadFolder = "C:\\upload_data\\temp";
			
			// ● 05.16 추가) 해당 폴더가 존재하는지 체크해서 -> 존재하지 않는다면 자동으로 생성하는 로직
			File uploadPath = new File(uploadFolder, uploadFolderPath);
			log.info("upload path: "+ uploadPath);
			
			if(uploadPath.exists() == false) {
				uploadPath.mkdirs(); // make directories
			}
			
			
			for(MultipartFile multipartFile : uploadFile) {
				
				// ● 05.17 파일 정보를 저장할 DTO 생성(밑에서 setter 이용해서 파일 이름 넣어야 함)
				BoardAttachVO attachDTO = new BoardAttachVO();
				
				log.info("------------▼-----------");
				log.info("단순 로그) Upload File Name : " + multipartFile.getOriginalFilename());
				log.info("단순 로그) Upload File Size : " + multipartFile.getSize());
				
				
				String uploadFileName = multipartFile.getOriginalFilename();
				log.info("변수) uploadFileName -> " + uploadFileName);
				
				// ● 파일 경로에서 이름만 떼오기 (근데 동일해서 이 코드가 없어도 될 것 같긴 함. 추후에 경로가 이상하게 들어오는 경우에 사용하면 됨)
				uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
				log.info("변수) uploadFileName(.substring 적용ver.) -> " + uploadFileName);
				log.info("------------------------");
				
				// ● 05.17 uploadFileName에서 얻어온 파일 이름을, setter 이용해서 상단에 만든 DTO에 넣기
				attachDTO.setFileName(uploadFileName);
				
				
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
					
					// 05.17 uuid와 저장할 폴더 경로를 setter로 입력받는다.
					attachDTO.setUuid(uuid.toString());
					attachDTO.setUploadPath(uploadFolderPath);
					
					// 05.16 썸네일) 이미지인지 아닌지 판단해서 썸네일을 만들어서 업로드 
					if(checkImageType(saveFile)) {
						
						// 05.17 if문에서 이미지가 맞는 경우 진입. 
						//       .setImage(true)라는 것은 DTO에서 이미지가 맞는지 여부 중 yes를 의미
						//attachDTO.setImage(true);	
						attachDTO.setFileType(true);	
						
						FileOutputStream thumbnail = 
								new FileOutputStream(
										new File(uploadPath, "s_" + uploadFileName));
						
						Thumbnailator.createThumbnail(
								multipartFile.getInputStream(), thumbnail, 100, 100);
						thumbnail.close();
					}
					// 05.17
					list.add(attachDTO);
					
				}catch(Exception e) {
					e.printStackTrace();
					log.error(e.getMessage());
				}
			}// end for
			
			// 05.17
			return new ResponseEntity<>(list, HttpStatus.OK);
			
		}// /uploadAjaxAction 종료
		
		
		
		
		// ■ 05.18 파일 이름을 적으면 그 파일의 경로나 나오게 하는 컨트롤러
			// 이미지 파일의 썸네일 경로를 컨트롤러가 직접 배정
			// /display 경로에 파일 정보를 입력하면, header에 파일 경로 정보를 실어서 response 하도록 처리하는 코드
		@GetMapping("/display")
		@ResponseBody
		public ResponseEntity<byte[]> getFile(String fileName){		
			log.info("/display) file name -> " + fileName);				// 여기서 파일 이름은 단순 파일 이름이 (X)
																		// 몇월 며칠 + uuid + 찐 파일 이름 => 중복 걱정이 없음
			
			// 단순하게 제이슨 데이터가 아니라 '파일'을 뿌려줘야하기 때문에 1) ~ 4) 까지 하는 것
			
			// ● 1. 파일 생성
				//  위의 경로의 파일을 얻어와서 자바 내부로 갖고 오는 코드
			File file = new File("c:\\upload_data\\temp\\" + fileName);	
			
			log.info("/display) file-> " + file);
			
			ResponseEntity<byte[]> result = null;
			
			try {
				// 2. 제이슨 대신에 파일을 줘야하는데 http방식 통신이므로 헤더를 생성
					// 파일이라서 헤더가 필요함
				HttpHeaders header = new HttpHeaders();	
				
				// 3. 컨텐츠 타입이 제이슨이 아닌 파일임을 헤더에 명시
					// 자료의 종류가 파일이라는 것을 명시(파일 경로를 같이 줌)
			        // 제이슨이면 이 코드가 필요 없음
				header.add("Content-Type", Files.probeContentType(file.toPath()));	
																					
				// 4. 리스폰스엔티티에 파일을 포함시켜 전달
				result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK); 
				
			}catch(IOException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		
		// ■ 05.18 업로드한 파일을 다운 받게 하는 기능 구현
		@GetMapping(value="/download",
					produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)	
					// 다운받을 수 있도록 스트림을 해줘야함(옥텟 = 네트워크에서 사용하는 단위. 검색 키워드 : http네트워크 옥텟)
		@ResponseBody
		public ResponseEntity<Resource> downloadFile(String fileName){
			
			log.info("/download) 파일이름 ->" + fileName);
			
			// 경로
			Resource resource = new FileSystemResource("C:\\upload_data\\temp\\" + fileName);
			
			log.info("/download) resource -> " + resource);
			
			// resource에서 파일 이름 뽑아주기
			String resourceName = resource.getFilename();
			
			// 다운로드시 파일 이름을 처리하도록 HttpHeaders객체를 이용
			HttpHeaders headers = new HttpHeaders();
			
			try {
				// 다운로드시 저장되는 이름은 Content-Disposition을 이용해 저장한다.
				headers.add("Content-Disposition", 
							"attachment; filename=" + new String(resourceName.getBytes("UTF-8"), "ISO-8859-1"));
				
			}catch(UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
		}
		
		
		// ■ 05.18 파일 첨부 후, X를 누르면 삭제되는 로직
		@PostMapping("/deleteFile")
		@ResponseBody
		public ResponseEntity<String> deleteFile(String fileName, String type){
			
			log.info("/deleteFile) 파일 이름 -> " + fileName);
			
			File file = null;
			
			try {
				// ● 일반 파일 삭제
					// 원본 파일의 경로를 얻어와서 자바에 인식을 시킴
					file = new File("c:\\upload_data\\temp\\" + URLDecoder.decode(fileName, "UTF-8"));
					
					// 파일을 지움
					file.delete();
				
				// ● 이미지 파일 삭제
				if(type.equals("image")) {
					
					// 먼저 s_를 경로에서 지움 (썸네일, 일반 이미지 둘 다 삭제해야 하므로)
					String largeFileName = file.getAbsolutePath().replace("s_", "");
					
					log.info("/deleteFile) largeFileName -> " + largeFileName);
					
					file = new File(largeFileName);
					
					// 이미지 파일을 삭제
					file.delete();
				}
			}catch(UnsupportedEncodingException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<String>("deleted", HttpStatus.OK);
			
		}
		
	
	
	
	
	
	

}
