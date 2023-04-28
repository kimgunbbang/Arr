package cart.svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class CartListService {

	public ArrayList<Cart> getCartList(HttpServletRequest request) {
		ArrayList<Cart> cartList = null;
		
		HttpSession session = request.getSession();
		cartList = (ArrayList<Cart>)session.getAttribute("cartList");
		
		return cartList;
	}

}