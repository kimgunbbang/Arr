package cart.svc;

import static db.JdbcUtil.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;


import dao.CartDAO;
import vo.Cart;

public class CartAddService {

	public boolean insertCart(String id, int p_num) {
		boolean addResult = false;
		CartDAO cartDAO = CartDAO.getInstance();
		Connection conn = null;
		
		try {
			conn = getConnection();
			cartDAO.setConnection(conn);
			int addCount = cartDAO.addCart(cart);
			
			if(addCount > 0) {
				addResult = true;
				commit(conn);
			}else {
				rollback(conn);
			}
			
		}catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return addResult;
	}


}
