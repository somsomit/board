package com.spring.common.page;

import com.spring.board.common.util.Util;
import com.spring.board.common.vo.CommonVO;

public class Paging {
	
	public static void setPage(CommonVO cvo){
		int page = Util.nvl(cvo.getPage(), 1);
		int pageSize = Util.nvl(cvo.getPageSize(), 10);
		
		if(cvo.getPage()==null) cvo.setPage(page+"");
		if(cvo.getPageSize()==null) cvo.setPageSize(pageSize+"");
		
		int start_row = (page-1)*pageSize+1;
		int end_row = (page-1)*pageSize+pageSize;
		
		cvo.setStart_row(start_row+"");
		cvo.setEnd_row(end_row+"");
	}

}
