package cart.action;

import java.io.PrintWriter; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import cart.svc.CartQtyDownService;
import vo.ActionForward;
import vo.Cart;

public class CartQtyDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		
		int cart_num = Integer.parseInt(request.getParameter("cart_num"));
		
		if(cart_num > 0) {
			Cart cart = new Cart();
			
			cart.setCart_num(Integer.parseInt(request.getParameter("cart_num")));
		
			CartQtyDownService cartQtyDownService = new CartQtyDownService();
			boolean qtyUpdate = cartQtyDownService.downCartQty(cart);
			
			if(qtyUpdate) {
				forward = new ActionForward("cartList.ct", true);
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정에 실패하였습니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
		}


		
		
		return forward;
	}

}