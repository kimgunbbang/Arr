package cart.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import cart.svc.CartAddService;
import vo.ActionForward;
import vo.Cart;

public class CartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		
		CartAddService cartAddService = new CartAddService();
		ArrayList<Cart> cartList = new ArrayList<Cart>();
		cartList = cartAddService.insertCart(id,p_num);
		session.setAttribute("cartList", cartList);
		session.setAttribute("id", session.getAttribute("id"));
		forward = new ActionForward("cartList.ct",true);
		
		return forward;
	}

}
