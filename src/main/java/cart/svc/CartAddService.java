package cart.svc;

import static db.JdbcUtil.*; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.CartDAO;
import vo.Cart;
import vo.Product;

public class CartAddService {


	public boolean addCart(Cart cart) {
		boolean isAddSuccess = false;
		Connection conn = null;

		try {
			conn = getConnection();
			CartDAO cartDAO = CartDAO.getInstance();
			cartDAO.setConnection(conn);
			
			boolean isExist = cartDAO.isCartExist(cart.getId(), cart.getP_num());
	        if (isExist) {
	            return false;
	        }else {
	        	isAddSuccess = cartDAO.insertCart(cart);
	        }
			
		
			if (isAddSuccess) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				close(conn);
			}
		}
		return isAddSuccess;
	}



	public ArrayList<Cart> getCartProduct(HttpServletRequest request, Product product) {
		HttpSession session = request.getSession();
		ArrayList<Cart> cartList = (ArrayList)session.getAttribute("cartList");
		if(cartList == null) {
			cartList = new ArrayList<Cart>();
			session.setAttribute("cartList", cartList);
		}
		
		boolean isNewCart = true;
		
		for(int i = 0 ; i < cartList.size() ; i++) {
			if(product.getP_num() == cartList.get(i).getP_num()) {
				isNewCart = false;
				cartList.get(i).setCart_qty(cartList.get(i).getCart_qty()+1);
				break;
			}
		}
		
		if(isNewCart) {
			Cart cart = new Cart();
			
			
		}
		
		return cartList;
	}



}