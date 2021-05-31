package com.spring.board.common.file;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	
	static Logger logger = Logger.getLogger(FileUploadUtil.class);
	private static final String FILE_PATH = "C://Users//chiri//Documents//web//Webspring//WebContent//uploadStorage";
	
	public static String fileUpload(MultipartFile file, HttpServletRequest request){
		logger.info("FileUploadUtil.fileUpload 시작");
		String real_name = null;
		try{
			// MultipartFile 클래스 getFile() 메소드로 클라이언트가 업로드한 파일명
			String org_name = file.getOriginalFilename();
			logger.info("[log] org_name : " + org_name);
			
			// 파일명 변경(중복 방지)
			if(org_name != null && (!org_name.equals(""))){
				real_name = "board_" + System.currentTimeMillis() + "_" + org_name;
				
				File fileDir = new File(FILE_PATH);
				if(!fileDir.exists()){
					fileDir.mkdir();
				}
				
				File fileAdd = new File(FILE_PATH + "//" + real_name);
				logger.info("[log] 파일 경로 : " + fileAdd);
				
				file.transferTo(fileAdd);
			}
		}catch(Exception e){
			System.out.println("[log] 에러 : " + e);
		}
		logger.info("FileUploadUtil.fileUpload 끝");
		return real_name;
		
	} // end of fileUpload
	
	public static void fileDelete(String fileName, HttpServletRequest request) throws IOException{
		logger.info("FileUploadUtil.fileDelete 시작");
		
		boolean result = false;
		String docRoot = request.getSession().getServletContext().getRealPath("/uploadStorage");
		
		File fileDelete = new File(docRoot + "/" + fileName);
		logger.info("[log] 삭제할 파일 : " + fileDelete);
		if(!fileDelete.exists() && fileDelete.isFile()){
			result = fileDelete.delete();
		}
		logger.info("파일 삭제 여부 : " + result);
		logger.info("FileUploadUtil.fileDelete 끝");
	} // end of fileDelete

}
