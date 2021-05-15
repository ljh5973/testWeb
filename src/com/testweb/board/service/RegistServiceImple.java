package com.testweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.board.model.BoardDAO;

public class RegistServiceImple implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		String title=request.getParameter("title");
		
		
		
		BoardDAO dao=BoardDAO.getInstance();
		dao.regist(writer, title, content);
	}

}
