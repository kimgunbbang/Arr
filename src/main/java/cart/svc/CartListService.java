package cart.svc;

import static db.JdbcUtil.*;

import java.sql.Connection; 
import java.util.ArrayList;

import dao.CartDAO;
import db.JdbcUtil;
import vo.Cart;

public class CartListService {

	public ArrayList<Cart> getCartList(String id) {
		ArrayList<Cart> cartList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			CartDAO cartDAO = CartDAO.getInstance();
			cartDAO.setConnection(conn);
			cartList = cartDAO.selectCartList(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				close(conn);
			}
		}
		return cartList;
	}

	public int getTotalMoney(String id) {
		int totalMoney = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			CartDAO cartDAO = CartDAO.getInstance();
			cartDAO.setConnection(conn);
			totalMoney = cartDAO.selectTotalMoney(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				close(conn);
			}
		}
		return totalMoney;
	}

}