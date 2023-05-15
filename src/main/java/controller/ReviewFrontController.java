package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

/**
 * Servlet implementation class ReviewFrontController
 */
@WebServlet("*.r")
public class ReviewFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReviewFrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		String uri=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command = uri.substring(contextPath.length());
		
		
		System.out.println(command);
		
		Action action=null;//액션초기화
		ActionForward forward=null;
		
		
		
		
	}
}
