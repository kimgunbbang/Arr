package product.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;

import static db.JdbcUtil.*;
import vo.Product;

public class ProductListService {

	public ArrayList<Product> getProductAllList() {
		ArrayList<Product> productList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ProductDAO productDAO = ProductDAO.getInstance();
			productDAO.setConnection(conn);
			
			productList = productDAO.selectProductAllList();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return productList;
	}

	public ArrayList<Product> getProductSelectList(String category_name) {
		ArrayList<Product> productList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ProductDAO productDAO = ProductDAO.getInstance();
			productDAO.setConnection(conn);
			
			productList = productDAO.selectProductSelectList(category_name);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return productList;
	}

	

}
