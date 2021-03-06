package com.spring.reply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.reply.dao.ReplyDao;
import com.spring.reply.vo.ReplyVO;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyDao replyDao;

	@Override
	public List<ReplyVO> replyList(String rnum) {
		// TODO Auto-generated method stub
		System.out.println("[log] ReplyServiceImpl.replyList 시작");
		List<ReplyVO> replyList = null;
		replyList = replyDao.replyList(rnum);
		System.out.println("[log] ReplyServiceImpl.replyList 끝");
		return replyList;
	} // end of replyList

	@Override
	public ReplyVO replySeq(ReplyVO rvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] ReplyServiceImpl.replySeq 시작");
		ReplyVO replySeq = null;
		replySeq = replyDao.replySeq(rvo);
		System.out.println("[log] ReplyServiceImpl.replySeq 끝");
		return replySeq;
	} // end of replySeq

	@Override
	public int replyInsert(ReplyVO rvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] ReplyServiceImpl.replyInsert 시작");
		int replyInsert = 0;
		replyInsert = replyDao.replyInsert(rvo);
		System.out.println("[log] ReplyServiceImpl.replyInsert 끝");
		return replyInsert;
	} // end of replyInsert

	@Override
	public int replyUpdate(ReplyVO rvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] ReplyServiceImpl.replyUpdate 시작");
		int replyUpdate = 0;
		replyUpdate = replyDao.replyUpdate(rvo);
		System.out.println("[log] ReplyServiceImpl.replyUpdate 끝");
		return replyUpdate;
	} // end of replyUpdate

	@Override
	public int replyDelete(ReplyVO rvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] ReplyServiceImpl.replyDelete 시작");
		int replyDelete = 0;
		replyDelete = replyDao.replyDelete(rvo);
		System.out.println("[log] ReplyServiceImpl.replyDelete 끝");
		return replyDelete;
	} // end of replyDelete

}
