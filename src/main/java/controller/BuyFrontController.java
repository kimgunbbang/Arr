package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import buy.action.BuyAction;
import buy.action.BuyActionForm;
import buy.action.BuyCancelAction;
import buy.action.BuyInfoDetailAction;
import buy.action.BuyInfoFormAction;
import buy.action.BuyListFormAction;
import buy.action.BuyNonUserListAction;
import vo.ActionForward;

/**
 * Servlet implementation class AdminFrontController
 */
@WebServlet("*.buy")
public class BuyFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyFrontController() {
        super();
        // TODO Auto-generated constructor stub
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
		if(command.equals("/buyActionForm.buy")) {
			action = new BuyActionForm();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/buyAction.buy")) {
			action = new BuyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/buyListForm.buy")) {
			action = new BuyListFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/buyInfoForm.buy")) {
			action = new BuyInfoFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/buyCancel.buy")) {
			action = new BuyCancelAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/buyInfoDetail.buy")) {
			action = new BuyInfoDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/nonUserBuyListForm.buy")) {
			request.setAttribute("pagefile", "/buy/buyNonUserListForm.jsp");//보일경로만적어주고
			forward=new ActionForward("/index.jsp",false);//포워드는 요로케하기
		}else if(command.equals("/nonUserBuyListAction.buy")) {
			action = new BuyNonUserListAction();
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
