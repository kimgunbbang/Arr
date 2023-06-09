package cart.action;

import java.io.PrintWriter; 
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import cart.svc.CartListService;
import vo.ActionForward;
import vo.Cart;
import vo.Noncart;

public class CartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("id");
		System.out.println(id);
	
		if(id == null) {
			Cookie[] cookies = request.getCookies();
			String uuid = null;
			// UUID 쿠키 찾기
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			        if (cookie.getName().equals("uuid")) {
			            uuid = cookie.getValue();
			            break;
			        }
			    }
			}
			
			
			
			ArrayList<Noncart> cartList = new ArrayList<Noncart>();
			CartListService cartListService = new CartListService();
			int totalMoney = 0;
			

			cartList = cartListService.getNonCartList(uuid);
			System.out.println(cartList);
			totalMoney = cartListService.getTotalMoney2(uuid);
			request.setAttribute("cartList", cartList);
			request.setAttribute("totalMoney", totalMoney);
			request.setAttribute("pagefile", "/cart/cartListForm.jsp");
			forward = new ActionForward("/index.jsp", false);
		}else {
			
		
		
		ArrayList<Cart> cartList = new ArrayList<Cart>();
		CartListService cartListService = new CartListService();
		int totalMoney = 0;
		

		cartList = cartListService.getCartList(id);
		System.out.println(cartList);
		totalMoney = cartListService.getTotalMoney(id);
		request.setAttribute("cartList", cartList);
		request.setAttribute("totalMoney", totalMoney);
		request.setAttribute("pagefile", "/cart/cartListForm.jsp");
		forward = new ActionForward("/index.jsp", false);
	
		}
		return forward;
	}

}