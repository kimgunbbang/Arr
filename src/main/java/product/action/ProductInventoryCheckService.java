package product.action;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;

import static db.JdbcUtil.*;
import vo.Product;

public class ProductInventoryCheckService {

	public ArrayList<Product> productInventoryCheck(ArrayList<Product> productList) {
		ArrayList<Product> productListch = new ArrayList<Product>();
		Connection conn=null;
		int result=0;
		try {
			conn = getConnection();
			ProductDAO productDAO = ProductDAO.getInstance();
			productDAO.setConnection(conn);
			
			result = productDAO.inventoryCheck(productList);
			
			if(result>0) {
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
		return productListch;
	}

}
