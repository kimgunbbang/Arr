package product.svc;

import java.sql.Connection;

import dao.ProductDAO;

import static db.JdbcUtil.*;
import vo.Product;

public class ProductDetailViewService {

	public Product getProductView(int p_num) {
		Product product = null;
		Connection conn = null;
		try {
			conn=getConnection();
			ProductDAO productDAO = ProductDAO.getInstance();
			productDAO.setConnection(conn);
			
			product = productDAO.getProduct(p_num);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		
		return product;
	}

}
