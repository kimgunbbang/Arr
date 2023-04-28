package product.svc;

import java.sql.Connection;

import dao.ProductDAO;

import static db.JdbcUtil.*;
import vo.Product;

public class ProductModifyService {

	public boolean productModify(Product product) {
		boolean modifySuccess = false;
		Connection conn = null;
		
		try {
			conn=getConnection();
			ProductDAO productDAO = ProductDAO.getInstance();
			productDAO.setConnection(conn);
			
			int result = productDAO.modifyProduct(product);
			if(result>0) {
				modifySuccess=true;
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
		return modifySuccess;
	}

}
