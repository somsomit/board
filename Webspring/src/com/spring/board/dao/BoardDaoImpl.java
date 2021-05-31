package com.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.vo.BoardVO;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSession session;

	@Override
	public List<BoardVO> boardList(BoardVO bvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardDaoImpl.boardList 시작");
		List<BoardVO> bList = session.selectList("boardList");
		System.out.println("[log] BoardDaoImpl.boardList 끝");
		return bList;
	} // end of boardList

	@Override
	public BoardVO boardDetail(BoardVO bvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardDaoImpl.boardDetail 시작");
		BoardVO detail = null;
		detail = (BoardVO)session.selectOne("boardDetail");
		System.out.println("[log] BoardDaoImpl.boardDetail 끝");
		return detail;
	} // end of boardDetail

	@Override
	public int boardInsert(BoardVO bvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = session.insert("boardInsert");
		return result;
	} // end of boardInsert

	@Override
	public int boardUpdate(BoardVO bvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = session.update("boardUpdate");
		return result;
	} // end of boardUpdate

	@Override
	public int boardDelete(BoardVO bvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = session.update("boardDelete");
		return result;
	} // end of boardDelete

	@Override
	public int pwdConfirm(BoardVO bvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardDaoImpl.pwdConfirm 시작");
		int result = 0;
		result = (Integer)session.selectOne("pwdConfirm");
		System.out.println("[log] BoardDaoImpl.pwdConfirm 끝");
		return result;
	} // end of pwdCofirm
	
	@Override
	public BoardVO boardSeq(BoardVO _bvo){
		System.out.println("[log] BoardDaoImpl.boardSeq 시작");
		BoardVO bvo = null;
		bvo = session.selectOne("boardSeq");
		System.out.println("[log] BoardDaoImpl.boardSeq 끝");
		return bvo;
	} // end of numberB

	@Override
	public int boardListCnt(BoardVO _bvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardDaoImpl.boardListCnt 시작");
		int bvo = 0;
		bvo = (Integer)session.selectOne("boardListCnt");
		System.out.println("[log] BoardDaoImpl.boardListCnt 시작");
		return bvo;
	}

}
