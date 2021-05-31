package com.spring.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.common.file.FileUploadUtil;
import com.spring.board.common.util.Sequence;
import com.spring.board.common.util.Util;
import com.spring.board.service.BoardService;
import com.spring.board.vo.BoardVO;
import com.spring.common.page.Paging;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	Logger logger = Logger.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/boardList", method = RequestMethod.GET)
	public String boardList(@ModelAttribute BoardVO bvo, Model model){
		logger.info("[log] BoardController.boardList 시작");
		
		if(bvo.getOrder_by()==null) bvo.setOrder_by("B_NUM");
		if(bvo.getOrder_sc()==null) bvo.setOrder_sc("DESC");
		
		logger.info("order_by : " + bvo.getOrder_by());
		logger.info("order_sc : " + bvo.getOrder_sc());
		logger.info("keyword : " + bvo.getKeyword());
		logger.info("search : " + bvo.getSearch());
		
		// 페이지 세팅
		Paging.setPage(bvo);
		
		// 전체 레코드 수 구현
		int total = boardService.boardListCnt(bvo);
		logger.info("total : " + total);
		
		// 글번호 재설정
		int count = total - (Util.nvl(bvo.getPage()) - 1) * Util.nvl(bvo.getPageSize());
		logger.info("count : " + count);
		
		List<BoardVO> boardList = null;
		boardList = boardService.boardList(bvo);
		model.addAttribute("boardList", boardList);
		model.addAttribute("count", count);
		model.addAttribute("total", total);
		model.addAttribute("data", bvo);
		
		return "board/boardList";
	} // end of boardList

	@RequestMapping(value="/boardDetail", method=RequestMethod.GET)
	public String boardDetail(@ModelAttribute BoardVO bvo, Model model){
		logger.info("[log] BoardController.boardDetail 시작");
		logger.info("[log] b_num : " + bvo.getB_num());
		
		BoardVO boardDetail = new BoardVO();
		boardDetail = boardService.boardDetail(bvo);
		
		if(boardDetail != null && (!boardDetail.equals(""))){
			boardDetail.setB_content(boardDetail.getB_content().toString().replaceAll("\n", "<br>"));
			logger.info("[log] file : " + boardDetail.getB_file());
		}
		
		model.addAttribute("boardDetail", boardDetail);
		logger.info("[log] BoardController.boardDetail 끝");
		return "board/boardDetail";
	} // end of boardDetail
	
	@ResponseBody
	@RequestMapping(value="/pwdConfirm", method=RequestMethod.POST)
	public String pwdCofirm(@ModelAttribute BoardVO bvo){
		logger.info("[log] BoardController.pwdCofirm 시작");
		
		int result = 0;
		result = boardService.pwdConfirm(bvo);
		logger.info("[log] result : " + result);
		
		logger.info("[log] BoardController.pwdCofirm 끝");
		return result + "";
	} // end of pwdCofirm
	
	// 글 쓰기 페이지로 이동
	@RequestMapping(value="/writeForm", method=RequestMethod.GET)
	public String writeForm(){
		logger.info("[log] BoardController.writeForm 시작");
		logger.info("[log] BoardController.writeForm 끝");
		return "board/writeForm";		
	} // end of writeForm
	
	@RequestMapping(value="/boardInsert", method=RequestMethod.POST)
	public String boardInsert(@ModelAttribute BoardVO bvo, 
							  HttpServletRequest request){
		logger.info("[log] BoardController.boardInsert 시작");
		logger.info("[log] fileName : " + bvo.getFile().getOriginalFilename());
		logger.info("[log] b_file : " + bvo.getB_file());
		
		BoardVO numbering = null;
		int result = 0;
		String url = "";
		try{
			numbering = boardService.boardSeq(numbering);
			
			String number = numbering.getB_num();
			logger.info("채번 확인 : " + number);
			bvo.setB_num(Sequence.num_B(number));
			
			String b_file = FileUploadUtil.fileUpload(bvo.getFile(), request);
			bvo.setB_file(b_file);
			logger.info("[log] 파일 클래스로 변경된 파일 : " + bvo.getB_file());
			result = boardService.boardInsert(bvo);
			
			if(result == 1){
				url = "/board/boardList.som";
			}
		}catch(Exception e){
			System.out.println("[log] 에러 : " + e);
		}
		
		logger.info("[log] BoardController.boardInsert 끝");
		return "redirect:" + url;
	} // end of boardInsert
	
	@RequestMapping(value="/UpdateForm", method=RequestMethod.POST)
	public String UpdateForm(@ModelAttribute BoardVO bvo, Model model){
		logger.info("[log] BoardController.UpdateForm 시작");
		
		BoardVO updateData = new BoardVO();
		updateData = boardService.boardDetail(bvo);
		
		model.addAttribute("updateData", updateData);
		logger.info("[log] BoardController.UpdateForm 끝");
		return "board/updateForm";
	}
	
	@RequestMapping(value="boardUpdate", method=RequestMethod.POST)
	public String boardUpdate(@ModelAttribute BoardVO bvo, HttpServletRequest request){
		logger.info("[log] BoardController.boardUpdate 시작");
		
		int result = 0;
		String url = "";
		String b_file = "";
		
		try{
			if(!bvo.getFile().isEmpty()){
				FileUploadUtil.fileDelete(bvo.getB_file(), request);
				b_file = FileUploadUtil.fileUpload(bvo.getFile(), request);
				bvo.setB_file(b_file);
			}else{
				logger.info("첨부파일이 없습니다.");
				bvo.setB_file("");
			}
			logger.info("[log] b_file : " + bvo.getB_file());
			result = boardService.boardUpdate(bvo);
			
			if(result == 1){
				url = "/board/boardList.som";
			}
		}catch(Exception e){
			logger.info("[log] 에러 : " + e);
		}
		logger.info("[log] BoardController.boardUpdate 끝");
		
		return "redirect:" + url;
	} // end of boardUpdate
	
	/* 글 수정 폼 */
	@RequestMapping(value="/updateForm", method=RequestMethod.POST)
	public String updateForm(@ModelAttribute BoardVO bvo, Model model){
		logger.info("[log] BoardController.updateForm 시작");
		logger.info("[log] num : " + bvo.getB_num());
		BoardVO updateData = boardService.boardDetail(bvo);
		
		model.addAttribute("updateData", updateData);
		logger.info("[log] BoardController.updateForm 끝");
		
		return "board/updateForm";
	} // end of updateForm
	
	@RequestMapping(value="/boardDelete")
	public String boardDelete(@ModelAttribute BoardVO bvo, HttpServletRequest request){
		logger.info("[log] BoardController.boardDelete 시작");
		
		int result = 0;
		String url = "";
		
		try{
			FileUploadUtil.fileDelete(bvo.getB_file(), request);
			result = boardService.boardDelete(bvo);
			if(result == 1){
				url = "/board/boardList.som";
			}
		}catch(Exception e){
			logger.info("[log] 에러 : " + e);
		}
		logger.info("[log] BoardController.boardDelete 끝");
		
		return "redirect:" + url;
	}
}
