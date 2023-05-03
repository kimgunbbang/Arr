package cart.action;

import java.io.PrintWriter; 
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import cart.svc.CartListService;
import vo.ActionForward;
import vo.Cart;

public class CartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ArrayList<Cart> cartList = new ArrayList<Cart>();
		CartListService cartListService = new CartListService();
		int totalMoney = 0;
		

		cartList = cartListService.getCartList(id);
		totalMoney = cartListService.getTotalMoney(id);
		request.setAttribute("cartList", cartList);
		request.setAttribute("totalMoney", totalMoney);
		forward = new ActionForward("cartList.ct", false);

		
		return forward;
	}

}