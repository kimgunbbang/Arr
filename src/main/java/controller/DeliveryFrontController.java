package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import delivery.action.DeliveryAddAction;
import delivery.action.DeliveryDeleteAction;
import delivery.action.DeliveryViewAction;
import delivery.action.DeliveryModifyAction;
import delivery.action.DeliveryModifyFormAction;
import delivery.action.DeliveryListAction;
import user.action.UserDeleteAction;
import user.action.UserJoinAction;
import user.action.UserListAction;
import user.action.UserModifyAction;
import user.action.UserModifyFormAction;
import user.action.UserViewAction;
import vo.ActionForward;

@WebServlet("*.del")
public class DeliveryFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeliveryFrontController() {
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
		
		if(command.equals("/deliveryAdd.del")) {
			request.setAttribute("pagefile", "/delivery/deliveryAddForm.jsp");
			forward = new ActionForward("/index.jsp",false);
		}else if(command.equals("/deliveryAddAction.del")) {
			action = new DeliveryAddAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/deliveryListAction.del")) {
			action = new DeliveryListAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/deliveryViewAction.del")) {
			action = new DeliveryViewAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/deliveryDeleteAction.del")) {
			action = new DeliveryDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/deliveryModifyAction.del")) {
			action = new DeliveryModifyAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/deliveryModifyForm.del")) {
			action = new DeliveryModifyFormAction();
			try {
				forward=action.execute(request, response);
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

