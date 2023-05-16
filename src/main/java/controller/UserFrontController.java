package controller;
 
import java.io.IOException;   

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import user.action.UserByeAction;
import user.action.UserDeleteAction;
import user.action.UserJoinAction;
import user.action.UserListAction;
import user.action.UserLoginAction;
import user.action.UserModifyAction;
import user.action.UserModifyFormAction;
import user.action.UserViewAction;
import vo.ActionForward;

/**
 * Servlet implementation class UserFrontController
 */
@WebServlet("*.u")
public class UserFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserFrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		
		String contextPath = request.getContextPath();
		
		String command = uri.substring(contextPath.length());
		
		System.out.println(command);
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/userLogin.u")) {
			request.setAttribute("pagefile", "/user/userLoginForm.jsp");
			forward = new ActionForward("/index.jsp",false);
		}else if(command.equals("/userJoinForm.u")) {
			request.setAttribute("pagefile", "/user/userJoinForm.jsp");
			forward = new ActionForward("/index.jsp",false);
		}else if(command.equals("/userJoinAction.u")) {
			action = new UserJoinAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/userLoginAction.u")) {
			action = new UserLoginAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/userListAction.u")) {
			action = new UserListAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/userViewAction.u")) {
			action = new UserViewAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/userDeleteAction.u")) {
			action = new UserDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/userLogout.u")) {
			request.getSession().invalidate();
	
			
			forward=new ActionForward("main.p",true);
		}else if(command.equals("/userModifyAction.u")) {
			action = new UserModifyAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/userModifyForm.u")) {
			action = new UserModifyFormAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/userByeAction.u")) {
		    action = new UserByeAction();
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
