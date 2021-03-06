package com.spring.board.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Sequence {
	
	public static final String Board_B = "B";
	public static final String Reply_R = "R";
	
	public static String ymdFormat(){
		Date d = null;
		d = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String date = sdf.format(d);
		
		return date;
	} // end of ymdFormat

	public static String numberFormat(String _no){
		String no = _no;
		
		if(no.length() == 1){
			no = "00" + no;
		}
		if(no.length() == 2){
			no = "0" + no;
		}
		
		return no;
	} // end of numberFormat
	
	public static String num_B(String _no){
		String num = _no;
		
		num = Board_B + Sequence.ymdFormat() + Sequence.numberFormat(num);
		
		System.out.println("날짜 : " + Sequence.ymdFormat());
		System.out.println("넘버링 : " + num);
		
		return num;
	} // end of num_B
	
	public static String num_R(String _no){
		String num = _no;
		
		num = Reply_R + Sequence.ymdFormat() + Sequence.numberFormat(num);
		
		System.out.println("날짜 : " + Sequence.ymdFormat());
		System.out.println("넘버링 : " + num);
		
		return num;
	}
	
	// 게시글 번호 시퀀스
	public static String seq_b(String _no){
		
		System.out.println("[log] Sequence.seq_b 시작");
		
		String num = _no;
		System.out.println("[log] num >>> " + num);
		num = Board_B + Sequence.ymdFormat() + Sequence.numberFormat(num);
		
		System.out.println("[log] 날짜 : " + Sequence.ymdFormat());
		System.out.println("[log] 넘버링 : " + num);
		
		System.out.println("[log] Sequence.seq_b 종료");
		return num;
	} // end of numberB
	
	// 댓글 번호 시퀀스
	public static String seq_r(String _no){
		System.out.println("[log] Sequence.seq_r 시작");
		
		String num = _no;
		System.out.println("[log] num >>> " + num);
		num = Reply_R + Sequence.ymdFormat() + Sequence.numberFormat(num);
		
		System.out.println("[log] 날짜 : " + Sequence.ymdFormat());
		System.out.println("[log] 넘버링 : " + num);
		
		System.out.println("[log] Sequence.seq_r 종료");
		return num;
	} // end of seq_r
	
} // end of Sequence
