package cart.svc;

import static db.JdbcUtil.*; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;


import dao.CartDAO;
import vo.Cart;

public class CartAddService {


	public boolean addCart(Cart cart) {
		boolean isAddSuccess = false;
		Connection conn = null;

		try {
			conn = getConnection();
			CartDAO cartDAO = CartDAO.getInstance();
			cartDAO.setConnection(conn);
			isAddSuccess = cartDAO.insertCart(cart);
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

}
