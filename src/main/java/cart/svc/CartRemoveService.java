package cart.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;


import dao.CartDAO;

public class CartRemoveService {

	public boolean removeCart(int cart_num) {
		boolean removeCart = false;
		Connection conn = null;
		int removeCount = 0;
		
		
		try {
			conn = getConnection();
			CartDAO cartDAO = CartDAO.getInstance();
			cartDAO.setConnection(conn);
			removeCount = cartDAO.removeCart(cart_num);
			
			if(removeCount>0) {
				commit(conn);
				removeCart = true;
			}else {
				rollback(conn);
			}
		}catch(Exception e ) {
			rollback(conn);
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return removeCart;
		
	}
}
