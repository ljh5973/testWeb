package com.testweb.board.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.board.model.BoardDAO;
import com.testweb.board.model.BoardVO;
import com.testweb.util.PageVO;

public class GetListServiceImpl implements IBoardService{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		
		
		// 1.화면 전환시에 조회하는 페이지번호 and 화면에 그려질 데이터 개수
		//첫 페이지 경우
		int pageNum=1;
		int amount=10;
		
		
		//페이지번호를 클릭하는경우
		if(request.getParameter("pageNum")!=null && request.getParameter("amount")!=null) {
			pageNum=Integer.parseInt(request.getParameter("pageNum"));
			amount=Integer.parseInt(request.getParameter("amount"));
		}
		//DAO 생성
		
		BoardDAO dao= BoardDAO.getInstance();
		
		ArrayList<BoardVO> list=dao.getList(pageNum, amount);
		
		int total=dao.getTotal();
		//2.pageVO생성
		PageVO pageVO=new PageVO(pageNum, amount, total) ;
		//3.페이지네이션을 화면에 전달
			request.setAttribute("pageVO", pageVO);
		//화면에 가지고 나갈 list를 request에 저장
		request.setAttribute("list",list);
			
		
	}
	

}
