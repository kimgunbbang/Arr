package cart.svc;

import java.sql.Connection; 


import dao.CartDAO;
import dao.NonCartDAO;
import vo.Cart;
import vo.Noncart;

import static db.JdbcUtil.*;
public class CartQtyUpService {

	public boolean upCartQty(Cart cart) {
		boolean qtyUpdate = false;
		Connection conn = getConnection();
		
		try {
			CartDAO cartDAO = CartDAO.getInstance();
			cartDAO.setConnection(conn);
			int upResult = cartDAO.upQty(cart);
			if(upResult>0) {
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

	public boolean upCartQty(Noncart noncart) {
		boolean qtyUpdate = false;
		Connection conn = getConnection();
		
		try {
			NonCartDAO noncartDAO = NonCartDAO.getInstance();
			noncartDAO.setConnection(conn);
			int upResult = noncartDAO.upQty(noncart);
			if(upResult>0) {
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