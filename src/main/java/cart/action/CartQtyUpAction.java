package cart.action;

import java.io.PrintWriter; 

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import cart.svc.CartQtyUpService;
import vo.ActionForward;
import vo.Cart;
import vo.Noncart;

public class CartQtyUpAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if(id == null) {
			int cart_num = Integer.parseInt(request.getParameter("cart_num"));
			
			if(cart_num > 0) {
				Noncart noncart = new Noncart();
				
				noncart.setCart_num(Integer.parseInt(request.getParameter("cart_num")));
			
				CartQtyUpService cartQtyUpService = new CartQtyUpService();
				boolean qtyUpdate = cartQtyUpService.upCartQty(noncart);
				
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
		}
		else {
			int cart_num = Integer.parseInt(request.getParameter("cart_num"));
			
			if(cart_num > 0) {
				Cart cart = new Cart();
				
				cart.setCart_num(Integer.parseInt(request.getParameter("cart_num")));
			
				CartQtyUpService cartQtyUpService = new CartQtyUpService();
				boolean qtyUpdate = cartQtyUpService.upCartQty(cart);
				
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
		}
		int cart_num = Integer.parseInt(request.getParameter("cart_num"));
		
		if(cart_num > 0) {
			Cart cart = new Cart();
			
			cart.setCart_num(Integer.parseInt(request.getParameter("cart_num")));
		
			CartQtyUpService cartQtyUpService = new CartQtyUpService();
			boolean qtyUpdate = cartQtyUpService.upCartQty(cart);
			
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