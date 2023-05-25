package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import qna.action.QnaAddFormAction;
import qna.action.QnaAllListAction;
import qna.action.QnaAnswerAction;
import qna.action.QnaListAction;
import qna.action.QnaMyListAction;
import qna.action.QnaWriteAction;
import qna.action.QnaWriteFormAction;
import review.action.ReviewWriteAction;
import review.action.ReviewWriteFormAction;
import vo.ActionForward;

@WebServlet("*.q")
public class QnaFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QnaFrontController() {
        super();
        // TODO Auto-generated constructor stub
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
		
		if(command.equals("/qnaWriteForm.q")) {
			action = new QnaWriteFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/qnaWriteAction.q")) {
			action = new QnaWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/qnaAnswerAction.q")) {
			action = new QnaAnswerAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/qnaMyListAction.q")) {
			action = new QnaMyListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/qnaList.q")) {
			action = new QnaAllListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/qnaAddForm.q")) {
			action = new QnaAddFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
}
