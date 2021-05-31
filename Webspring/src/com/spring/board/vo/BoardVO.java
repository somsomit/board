package com.spring.board.vo;

import org.springframework.web.multipart.MultipartFile;
import com.spring.board.common.vo.CommonVO;

public class BoardVO extends CommonVO {
	
	// B_NUM, B_NAME, B_TITLE, B_CONTENT, B_FILE, B_PWD, B_INSERTDATE, B_UPDATEDATE, B_DELETEYN
	private String b_num;
	private String b_name;
	private String b_title;
	private String b_content;
	private String b_file;
	private String b_pwd;
	private String b_insertdate;
	private String b_updatedate;
	private String b_deleteYN;
	
	private MultipartFile file;
	
	public String getB_num() {
		return b_num;
	}
	public void setB_num(String b_num) {
		this.b_num = b_num;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public String getB_file() {
		return b_file;
	}
	public void setB_file(String b_file) {
		this.b_file = b_file;
	}
	public String getB_pwd() {
		return b_pwd;
	}
	public void setB_pwd(String b_pwd) {
		this.b_pwd = b_pwd;
	}
	public String getB_insertdate() {
		return b_insertdate;
	}
	public void setB_insertdate(String b_insertdate) {
		this.b_insertdate = b_insertdate;
	}
	public String getB_updatedate() {
		return b_updatedate;
	}
	public void setB_updatedate(String b_updatedate) {
		this.b_updatedate = b_updatedate;
	}
	public String getB_deleteYN() {
		return b_deleteYN;
	}
	public void setB_deleteYN(String b_deleteYN) {
		this.b_deleteYN = b_deleteYN;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
