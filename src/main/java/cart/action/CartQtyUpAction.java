package cart.action;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import cart.svc.CartQtyUpService;
import vo.ActionForward;

public class CartQtyUpAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		String p_num = request.getParameter("p_num");
		CartQtyUpService cartQtyUpService=new CartQtyUpService();
		cartQtyUpService.upCartQty(p_num,request);
		HttpSession session =request.getSession();
		session.setAttribute("id", session.getAttribute("id"));
		forward=new ActionForward("cartList.ct",true);
		return forward;
	}

}