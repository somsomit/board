package com.spring.reply.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import com.spring.board.common.util.Sequence;
import com.spring.reply.service.ReplyService;
import com.spring.reply.vo.ReplyVO;

@Controller
@RequestMapping(value="/replies")
public class ReplyController {
	
	Logger logger = Logger.getLogger(ReplyController.class);
	
	@Autowired
	private ReplyService replyService;
	
	/*
	 * @PathVariable은 URI 경로에서 원하는 데이터 추출하는 용도로 사용
	 * ResponseEntity 타입은 개발자가 직접 결과 데이터와 HTTP 상태 코드를 직접 제어할 수 있는 클래스이다.
	 * 404나 500 같은 상태코드를 전송하고 싶은 데이터와 함께 전송할 수 있어서 세밀한 제어 가능
	 */
	
	@RequestMapping(value="/all/{b_num}.som", method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("b_num") String b_num){
		logger.info("[log] ReplyController.replyList 시작");
		ResponseEntity<List<ReplyVO>> entity = null;
		try{
			entity = new ResponseEntity<>(replyService.replyList(b_num), HttpStatus.OK);
		}catch(Exception e){
			System.out.println("[log] 에러 : " + e);
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		logger.info("[log] ReplyController.replyList 끝");
		return entity;
	} // end of list
	
	@RequestMapping(value="/replyInsert")
	public ResponseEntity<String> replyInsert(@RequestBody ReplyVO rvo){
		logger.info("[log] ReplyController.replyInsert 시작");
		
		ResponseEntity<String> entity = null;
		int result = 0;
		ReplyVO numbering = null;
		
		try{
			numbering = replyService.replySeq(numbering);
			String number = numbering.getR_num();
			rvo.setR_num(Sequence.num_R(number));
			
			result = replyService.replyInsert(rvo);
			
			if(result == 1){
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
		}catch(Exception e){
			System.out.println("[log] 에러 : " + e);
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		logger.info("[log] ReplyController.replyInsert 끝");
		return entity;
	} // end of replyInsert
	
	@RequestMapping(value="/{r_num}.som", method={RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> replyUpdate(@PathVariable("r_num")String r_num, 
			  								  @RequestBody ReplyVO rvo){
		System.out.println("[log] ReplyController.replyUpdate 시작");
		ResponseEntity<String> entity = null;
		try{
			rvo.setR_num(r_num);			
			replyService.replyUpdate(rvo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			System.out.println("[log] 에러 : " + e);
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		System.out.println("[log] ReplyController.replyUpdate 끝");
		return entity;
	} // end of replyUpdate
	
	@RequestMapping(value="/{r_num}.som", method=RequestMethod.DELETE)
	public ResponseEntity<String> replyDelete(@PathVariable("r_num")String r_num){
		System.out.println("[log] ReplyController.replyDelete 시작");
		ResponseEntity<String> entity = null;
		ReplyVO rvo = null;
		rvo = new ReplyVO();
		
		try{
			rvo.setR_num(r_num);
			replyService.replyDelete(rvo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			System.out.println("[log] 에러 : " + e);
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		System.out.println("[log] ReplyController.replyDelete 끝");
		return entity;
	} // end of replyDelete
	
} // end of ReplyController
