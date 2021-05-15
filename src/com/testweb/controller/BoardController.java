package com.testweb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.board.service.GetContentServiceImpl;
import com.testweb.board.service.GetListServiceImpl;
import com.testweb.board.service.IBoardService;
import com.testweb.board.service.RegistServiceImple;
import com.testweb.board.service.UpdateServiceImpl;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
		
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");
		String uri=request.getRequestURI();
		String path=request.getContextPath();
		String command=uri.substring(path.length());
		
		
		IBoardService service = null;
		
		if(command.equals("/board/write.board")) {
			
			
			request.getRequestDispatcher("board_write.jsp").forward(request, response);
		}else if(command.equals("/board/regist.board")) {
			//글 등록 요청
			service=new RegistServiceImple();
			service.execute(request, response);
			
			request.getRequestDispatcher("/board/list.board").forward(request, response);
			
		}else if(command.equals("/board/list.board")) {
			service= new GetListServiceImpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("bbs.jsp").forward(request, response);
		}else if(command.equals("/board/getContent.board")) {
			
			
			service= new GetContentServiceImpl();
			service.execute(request, response);
			request.getRequestDispatcher("board_content.jsp").forward(request, response);
		}else if(command.equals("/board/modify.board")) {
			service= new GetContentServiceImpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("board_modify.jsp").forward(request, response);
		}else if(command.equals("/board/update.board")) {
			service= new UpdateServiceImpl();
			service.execute(request, response);
			
			response.sendRedirect("getContent.board?bno" + request.getParameter("bno"));
		}
		
	}
	
	
	

}
