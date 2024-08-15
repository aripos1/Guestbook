package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestVo;

@WebServlet("/gbc")
public class GuestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// controller 접수 받는 일
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 접수
		String action = request.getParameter("action");
		System.out.println(action);

		if ("list".equals(action)) {

			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestVo> guestList = guestbookDao.getGuestList();

			// 화면그리기 -->포워드
			// request 에 리스트 주소 넣기
			request.setAttribute("guestList", guestList);

//		List<PersonVo> personList =(List<PersonVo>)request.getAttribute("personList");

			// 포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");
			rd.forward(request, response);

		}else if("insert".equals(action)) {
			
			System.out.println("등록 요청 데이터3개 저장해줘");
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			
			GuestVo guestVo = new GuestVo(name,password,content);
			
			GuestbookDao guestbookDao = new GuestbookDao();
			
			guestbookDao.insertGuest(guestVo);
			
			response.sendRedirect("/guestbook/gbc?action=list");
			
		}else if("deleteform".equals(action)) {
			
			System.out.println("삭제폼 요청");
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			GuestbookDao guestbookDao = new GuestbookDao();
			GuestVo guestVo = guestbookDao.getGuestOne(no);
			
			request.setAttribute("guestVo", guestVo);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
			rd.forward(request, response);
		
		}else if("delete".equals(action)) {
			System.out.println("삭제 요청");
			int no = Integer.parseInt(request.getParameter("no")) ;
		
			GuestVo guestVo = new GuestVo(no);
			GuestbookDao guestbookDao = new GuestbookDao();
			
			guestbookDao.deleteGuest(guestVo);
			
			response.sendRedirect("/guestbook/gbc?action=list");
		
		}
		
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}