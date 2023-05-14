package product.svc;

import java.sql.Connection;

import dao.ProductDAO;

import static db.JdbcUtil.*;
public class ProductDeleteService {

	public boolean productDelete(int p_num) {
		boolean isdelete = false;
		Connection conn = null;
		
		try {
			conn=getConnection();
			ProductDAO productDAO = ProductDAO.getInstance();
			productDAO.setConnection(conn);
			int deleteCount = productDAO.productDelete(p_num);
			if(deleteCount>0) {
				isdelete = true;
				commit(conn);
			}else {
				rollback(conn);
			}
		}catch(Exception e) {
			rollback(conn);
		}finally {
			close(conn);
		}
		return isdelete;
	}

}
