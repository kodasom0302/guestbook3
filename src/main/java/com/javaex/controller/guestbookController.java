package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestbookVo;

@WebServlet("/gbc")
public class guestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("guestbookController.goGet()");
    	
    	String action=request.getParameter("action");
    	System.out.println(action);
    	
    	if("insert".equals(action)) {
    		System.out.println("insert:등록");
    		
    		String name=request.getParameter("name");
    		String pw=request.getParameter("password");
    		String content=request.getParameter("content");
    		
    		var now=java.time.LocalDate.now();
    		
    		var stringDate=now.toString();
    		
    		System.out.println(stringDate);
    		
    		GuestbookVo guestVo=new GuestbookVo(name, pw, content, stringDate);
    		System.out.println(guestVo.toString());
    		
    		GuestDao guestDao=new GuestDao();
    		
    		guestDao.guestInsert(guestVo);
    		
    		response.sendRedirect("http://localhost:8080//guestbook3/gbc?action=list");
    	} else if("delete".equals(action)) {
    		System.out.println("delete:삭제");
    		
    		int no=Integer.parseInt(request.getParameter("no"));
    		System.out.println(no);
    		
    		GuestDao guestDao=new GuestDao();
    		
    		guestDao.guestDelete(no);
    		
    		response.sendRedirect("http://localhost:8080//guestbook3/gbc?action=list");
    	} else {
			System.out.println("list:리스트");
			
			GuestDao guestDao=new GuestDao();
			
			List<GuestbookVo> guestList=guestDao.guestSelect();
			
			request.setAttribute("guestList", guestList);

			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);
		}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
