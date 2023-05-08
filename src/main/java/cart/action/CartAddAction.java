package cart.action;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import cart.svc.CartAddService;
import cart.svc.CartQtyUpService;
import dao.CartDAO;
import vo.ActionForward;
import vo.Cart;
import static db.JdbcUtil.*;

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
		CartDAO cartDAO = CartDAO.getInstance();
		Connection conn = getConnection();
		cartDAO.setConnection(conn);
		

		
		boolean isAddSuccess = cartAddService.addCart(cart);

		if (!isAddSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('이미 장바구니에 담긴 상품입니다!')");
			out.println("history.back()");
			out.println("</script>");
		}

		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("cartList.ct?p_num=" + num); 
		return forward;
	}

}
