package cart.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;


import dao.CartDAO;
import dao.NonCartDAO;

public class CartRemoveService {

	public boolean removeCart(String[] cartList) {
		boolean removeCart = false;
		Connection conn = null;
		int removeCount = 0;
		
		
		try {
			conn = getConnection();
			CartDAO cartDAO = CartDAO.getInstance();
			cartDAO.setConnection(conn);
			removeCount = cartDAO.removeCart(cartList);
			
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

	public boolean removeNonCart(String[] cartList) {
		boolean removeCart = false;
		Connection conn = null;
		int removeCount = 0;
		
		
		try {
			conn = getConnection();
			NonCartDAO noncartDAO = NonCartDAO.getInstance();
			noncartDAO.setConnection(conn);
			removeCount = noncartDAO.removeCart(cartList);
			
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
