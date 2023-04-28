package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import cart.action.CartListAction;
import delivery.action.DeliveryAddAction;
import product.action.ProductAllListAction;
import vo.ActionForward;

@WebServlet("*.ct")
public class CartFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartFrontController() {
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
		
		if(command.equals("/cartList.ct")) {
			request.setAttribute("pagefile", "/cart/cartListForm.jsp");
			forward = new ActionForward("/index.jsp",false);
		}else if(command.equals("/cartListAction.ct")) {
			action = new CartListAction();
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
