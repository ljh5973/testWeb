package com.testweb.util;

public class PageVO {
	
	/*
	 * 화면에 그려질 pageNation을 계산하는 클래스(pageNum, amount값을 가지고 다님)
	 */

	
	private int startPage;	//게시글 화면에 보여질 첫번째 번호
	private int endPage;	//게시글 화면에 보여질 마지막 번호
	private boolean prev, next; 	//이전버튼 다음버튼
	
	private int pageNum;	//현재 조회하는 페이지 번호
	private int amount =10;	//화면에 그려질 데이터
	private int total;		//전체 게시글 수
	
	//생성자에서는 객체가 생성될 때 계산을 처리
	
	public PageVO(int pageNum, int amount, int total) {
		this.pageNum=pageNum;
		this.amount=amount;
		this.total=total;
		
		
		//1. endPage결정
		//ex) 조회하는 페이지1 -> 끝 번호는 10
			//조회하는 페이지9 -> 끝 번호는 10
			//조회하는 페이지11-> 끝 번호는 20
		
		this.endPage=(int)Math.ceil(pageNum/10.0)*10;
		
		//2. startPage결정
		//공식 = 끝페이지 - 페이지네이션 개수 +1
		
		this.startPage=this.endPage-10+1;
		
		//만약 게시글이 52개라면 ->진짜 끝번호는 60;
		//만약 게시글이 105개라면 -> 진짜 끝번호는 110
		//공식= (int)Math.ceil(전체게시글/화면에 보여질 데이터 개수)
		
		int realEnd =(int)Math.ceil(this.total/(double)this.amount);
		//3. realEnd(진짜 끝번호)를 구해서 endPage값을 다시 결정
		
		//마지막 페이지 도달했을 때 보여져야하는 끝 번호가 달라집니다.
		//ex: 131개 게시물
		//1번페이지 클릭시 -> endPage=10, realEnd=14
		
		if(this.endPage>realEnd) {
			this.endPage=realEnd;
		}
		
		//4. prev결정(startPage의 번호가 1, 11 ,21...)
		this.prev=this.startPage>1;
		
		//5.next결정
		//ex 131개 게시물
		//1~10클릭시 endPage =10,  realEnd= 14 ->다음버튼true
		//11클릭시 endPage=14, realEnd=14 ->다음버튼 false
		this.next=this.endPage < realEnd;
		
		
		
		
		
		
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
