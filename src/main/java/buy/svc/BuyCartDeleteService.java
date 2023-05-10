package buy.svc;

import java.sql.Connection;

import dao.BuyDAO;

import static db.JdbcUtil.*;
public class BuyCartDeleteService {

	public void cartDelete(String[] cartList) {
		int success = 0;
		Connection conn = null;
		
		try {
			conn=getConnection();
			BuyDAO buyDAO = BuyDAO.getInstance();
			buyDAO.setConnection(conn);
			success = buyDAO.cartDelete(cartList);
			
			if(success>0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			close(conn);
		}
		
	}

	public void nonCartDelete(String[] cartList) {
		int success = 0;
		Connection conn = null;
		
		try {
			conn=getConnection();
			BuyDAO buyDAO = BuyDAO.getInstance();
			buyDAO.setConnection(conn);
			success = buyDAO.nonCartDelete(cartList);
			
			if(success>0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			close(conn);
		}
	}

}
