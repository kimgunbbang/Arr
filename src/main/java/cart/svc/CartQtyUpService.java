package cart.svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class CartQtyUpService {

	public void upCartQty(String p_num, HttpServletRequest request) {
		HttpSession session = request.getSession();
		ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");
		
		if(cartList !=null) {
			for(int i=0;i<cartList.size();i++) {
				if(cartList.get(i).getP_num().equals(p_num)) {
					cartList.get(i).setCart_qty(cartList.get(i).getCart_qty()+1);
					if(cartList.get(i).getCart_qty()>10) cartList.get(i).setCart_qty(10);
				}
			}
		}
		
		
	}

}
