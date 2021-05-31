package com.spring.board.service;

import java.util.List;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.board.dao.BoardDao;
import com.spring.board.vo.BoardVO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	
//	Logger logger = Logger.getLogger(BoardServiceImpl.class);
	
	@Autowired
	private BoardDao boardDao;

	@Override
	public List<BoardVO> boardList(BoardVO bvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.boardList 시작");
		List<BoardVO> bList = null;
		bList = boardDao.boardList(bvo);
		System.out.println("[log] BoardServiceImpl.boardList 끝");
		return bList;
	} // end of boardList

	@Override
	public BoardVO boardDetail(BoardVO bvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.boardDetail 시작");
		BoardVO boardDetail = null;
		boardDetail = boardDao.boardDetail(bvo);
		System.out.println("[log] BoardServiceImpl.boardDetail 끝");
		return boardDetail;
	} // end of boardDetail

	@Override
	public int boardInsert(BoardVO bvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.boardInsert 시작");
		int result = 0;
		result = boardDao.boardInsert(bvo);
		System.out.println("[log] BoardServiceImpl.boardInsert 끝");
		return result;
	} // end of boardInsert

	@Override
	public int boardUpdate(BoardVO bvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.boardUpdate 시작");
		int result = 0;
		result = boardDao.boardUpdate(bvo);
		System.out.println("[log] BoardServiceImpl.boardUpdate 끝");
		return result;
	} // end of boardUpdate

	@Override
	public int boardDelete(BoardVO bvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.boardDelete 시작");
		int result = 0;
		result = boardDao.boardDelete(bvo);
		System.out.println("[log] BoardServiceImpl.boardDelete 끝");
		return result;
	}

	@Override
	public BoardVO boardSeq(BoardVO _bvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.boardSeq 시작");
		BoardVO bvo = null;
		bvo = boardDao.boardSeq(bvo);
		System.out.println("[log] BoardServiceImpl.boardSeq 끝");
		return bvo;
	} // end of sequenceB

	@Override
	public int pwdConfirm(BoardVO bvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.pwdConfirm 시작");
		int result = 0;
		result = boardDao.pwdConfirm(bvo);
		System.out.println("[log] BoardServiceImpl.pwdConfirm 끝");
		return result;
	} // end of pwdCofirm

	@Override
	public int boardListCnt(BoardVO _bvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.boardListCnt 시작");
		int bvo = 0;
		bvo = boardDao.boardListCnt(_bvo);
		System.out.println("[log] BoardServiceImpl.boardListCnt 끝");
		return bvo;
	} // end of boardListCnt

}
