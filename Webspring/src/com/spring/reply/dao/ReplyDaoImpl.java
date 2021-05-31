package com.spring.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.reply.vo.ReplyVO;

@Repository
public class ReplyDaoImpl implements ReplyDao {
	
	@Autowired
	private SqlSession session;

	@Override
	public List<ReplyVO> replyList(String rnum) {
		// TODO Auto-generated method stub
		System.out.println("[log] ReplyDaoImpl.replyList 시작");
		List<ReplyVO> replyList = null;
		replyList = session.selectList("replyList", rnum);
		System.out.println("[log] ReplyDaoImpl.replyList 끝");
		return replyList;
	} // end of replyList
	
	@Override
	public ReplyVO replySeq(ReplyVO rvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] ReplyDaoImpl.replySeq 시작");
		ReplyVO replySeq = null;
		replySeq = session.selectOne("replySeq");
		System.out.println("[log] ReplyDaoImpl.replySeq 끝");
		return replySeq;
	} // end of replySeq

	@Override
	public int replyInsert(ReplyVO rvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] ReplyDaoImpl.replyInsert 시작");
		int replyInsert = 0;
		replyInsert = session.insert("replyInsert");
		System.out.println("[log] ReplyDaoImpl.replyInsert 끝");
		return replyInsert;
	} // end of replyInsert

	@Override
	public int replyUpdate(ReplyVO rvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] ReplyDaoImpl.replyUpdate 시작");
		int replyUpdate = 0;
		replyUpdate = session.update("replyUpdate");
		System.out.println("[log] ReplyDaoImpl.replyUpdate 끝");
		return replyUpdate;
	} // end of replyUpdate

	@Override
	public int replyDelete(ReplyVO rvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] ReplyDaoImpl.replyDelete 시작");
		int replyDelete = 0;
		replyDelete = session.delete("replyDelete");
		System.out.println("[log] ReplyDaoImpl.replyDelete 끝");
		return replyDelete;
	} // end of replyDelete

} // end of ReplyDaoImpl
