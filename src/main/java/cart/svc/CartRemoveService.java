package cart.svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class CartRemoveService {

	public void cartRemove(HttpServletRequest request, String[] cartArray) {
		HttpSession session = request.getSession();//세션에 남아있는 것 처리
		ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");
		
		if(cartArray != null) {
			for(int i=0;i<cartArray.length;i++) {
				for(int j=0;j<cartList.size();j++) {
					if(cartArray[i].equals(cartList.get(j).getP_num())) {
						cartList.remove(j);
					}
				}
			}
		}
		
	}

}