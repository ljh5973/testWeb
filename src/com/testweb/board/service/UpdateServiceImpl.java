package com.testweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.board.model.BoardDAO;

public class UpdateServiceImpl implements IBoardService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		
		String bno=request.getParameter("bno");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		int num=Integer.parseInt(bno);
		BoardDAO dao=BoardDAO.getInstance();
		
		dao.Update(title, content, num);
	}

}
