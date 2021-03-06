package com.spring.reply.dao;

import java.util.List;
import com.spring.reply.vo.ReplyVO;

public interface ReplyDao {
	
	public List<ReplyVO> replyList(String rnum); // 조회
	public ReplyVO replySeq(ReplyVO rvo); // 채번
	public int replyInsert(ReplyVO rvo); // 작성
	public int replyUpdate(ReplyVO rvo); // 수정
	public int replyDelete(ReplyVO rvo); // 삭제

}
