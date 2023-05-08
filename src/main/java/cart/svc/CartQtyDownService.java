package cart.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.CartDAO;
import vo.Cart;

public class CartQtyDownService {

	public boolean downCartQty(Cart cart) {
		boolean qtyUpdate = false;
		Connection conn = getConnection();
		
	try {
		CartDAO cartDAO = CartDAO.getInstance();
		cartDAO.setConnection(conn);
		int downResult = cartDAO.downQty(cart);
		if(downResult>0) {
			qtyUpdate=true;
			commit(conn);
		}else {
			rollback(conn);
		}
	}catch(Exception e) {
		rollback(conn);
		e.printStackTrace();
	}finally {
		close(conn);
	}
		return qtyUpdate;
	}

}