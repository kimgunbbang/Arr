package cart.action;

import java.io.PrintWriter; 

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
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		String numParam = request.getParameter("p_num");
		int num = numParam == null ? 0 : Integer.parseInt(numParam);


		Cart cart = new Cart();
		cart.setP_num(num);
		cart.setId(id);

		CartAddService cartAddService = new CartAddService();
		boolean isAddSuccess = cartAddService.addCart(cart);

		if (!isAddSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('장바구니 추가 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}

		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("productDetail.p?p_num=" + num); 
		return forward;
	}

}
