package com.spring.common.page;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class PagingTag extends TagSupport{

	private static final long serialVersionUID = 1L;
	
	/*
	 * @param page		현재 페이지 번호
	 * @param total		전체 조회된 ROW 수
	 * @param list_size	페이지에 보여주는 레코드 수
	 * @param page_size	페이지 네비게이터에 표시되는 페이지 버튼 개수
	 */
	
	private int page = 1;
	private int total = 1;
	private int list_size = 5;
	private int page_size = 10;
	
	@Override
	public int doStartTag() throws JspException{
		try{
			pageContext.getOut().println(getPaging());
		}catch(Exception e){
			System.out.println("[log] 에러 : " + e);
		}
		return super.doStartTag();
	} // end of doStartTag
	
	public void setPage(int page) {
		this.page = page;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setList_size(int list_size) {
		this.list_size = list_size;
	}

	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}
	
	public String getPaging(){
		String ret = "";
		
		if(page < 1)
				page = 1;
		if(total < 1)
				return "";
		
		/* page가 1페이지고 page_size가 2일 때 */
		/* crruntFirst는 1 */
		int currentFirst = ((page-1)/page_size)*page_size+1;
		
		/* currentLast는 2 */
		int currentLast = ((page-1)/page_size)*page_size+page_size;
		
		/* nextFirst는 3 */
		int nextFirst = (((page+1)/page_size)+1)*page_size+1;
		
		/* prevLast는 0 */
		int prevLast = (((page+1)/page_size)-1)*page_size+1;
		
		int lastPage = 1;
		lastPage = total/list_size;
		
		/*
		 * lastPage(총 페이지 수)는 total이 11이고 list_size가 10이면 1을 가진다.
		 * 총 페이지 수가 나누어 떨어지지 않으면 나머지 레코드 출력할 페이지가 필요
		 */
		
		if(total%list_size!=0)lastPage = lastPage+1;
		
		System.out.println("[log] page : " + page);
		System.out.println("[log] prevLast : " + prevLast);
		System.out.println("[log] nextFirst : " + nextFirst);
		System.out.println("[log] lastPage : " + lastPage);
		
		/* currentLast가 lastPage 보다 크면 마지막 페이지로 수정 */
		currentLast = (currentLast>lastPage)?lastPage:currentLast;
		nextFirst = (page + 1) > lastPage ? lastPage : (page + 1);
		prevLast = (page - 1) < 1 ? 1 : (page - 1);
		
		ret += "<div class='paginate'>";
		
		if (page > 1){
			ret += "<a href=\"javascript:goPage('1')\"><span><img src='../img/3.png' alt='처음' /></span></a>";
		}else{
			ret += "<span><img src='../img/3.png' alt='처음'/></span>";
		}
		
		if(prevLast > 0){
			ret += "<a href=\"javascript:goPage('" + prevLast + "')\"><span><img src='../img/1.png' alt='이전' /></span></a>";
		}else{
			ret += "<span><img src='../img/1.png' alt='이전'/></span>";
		}
		
		for(int j = currentFirst; j < currentFirst + page_size && j <= lastPage; j++){
			
			if(j <= currentLast){
				
				if(j == page){
					ret += "<a href='#' class='on textAn'>" + j + "</a>";
				}else{
					ret += " <a href=\"javascript:goPage('" + j + "');\" class='textAn'>" + j + "</a>";
				} // end of else
			} // end of if
		} // end of for
		
		if(nextFirst <= lastPage){
			ret += "<a href=\"javascript:goPage('" + nextFirst + "')\"><span><img src='../img/2.png' alt='다음' /></span></a>";
		}else{
			ret += "<span><img src='../img/2.png' alt='다음'/></span>";
		}
		
		if(page < lastPage){
			ret += "<a href=\"javascript:goPage('" + lastPage + "')\"><span><img src='../img/4.png' alt='마지막' /></span></a>";
		}else{
			ret += "<span><img src='../img/4.png' alt='마지막'/></span>";
		}
		
		ret += " </div>";
		
		return ret;
	} // end of getPaging
}
