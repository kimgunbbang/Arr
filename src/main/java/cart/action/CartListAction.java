package cart.action;

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
		HttpSession session =request.getSession();
		String id=(String)session.getAttribute("id");
		
		if(id==null){
			forward = new ActionForward("productAllList.p",true);
		}else {
			session.setAttribute("id", session.getAttribute("id"));
			CartListService cartListService = new CartListService();
			ArrayList<Cart> cartList = cartListService.getCartList(request);
			
			request.setAttribute("cartList", cartList);
			
			int totalMoney=0;//전체금액
			int money=0;//상품한개가격
			if(cartList != null) {
				for(int i=0;i<cartList.size();i++) {
					money=cartList.get(i).getP_price()*cartList.get(i).getCart_qty();
					totalMoney+=money;
				}
			}
			System.out.println(totalMoney);
			request.setAttribute("totalMoney", totalMoney);
			request.setAttribute("pagefile", "/cart/cartList.jsp");
			forward=new ActionForward("/index.jsp",false);
		}
		return forward;
	}

}
