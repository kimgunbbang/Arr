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

	public Product getProduct(String p_num) {
		Product product = new Product();
		Connection conn = null;
		try {
			conn = getConnection();
			ProductDAO productDAO = ProductDAO.getInstance();
			productDAO.setConnection(conn);
			
			product = productDAO.selectProduct(p_num);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return product;
	}

	public ArrayList<Product> getProductBestReadList() {
		ArrayList<Product> productList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ProductDAO productDAO = ProductDAO.getInstance();
			productDAO.setConnection(conn);
			
			productList = productDAO.selectProductBestReadList();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return productList;
	}

	public ArrayList<Product> getProductBestSaleList() {
		ArrayList<Product> productList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ProductDAO productDAO = ProductDAO.getInstance();
			productDAO.setConnection(conn);
			
			productList = productDAO.selectProductBestSaleList();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return productList;
	}

	public int getProductMaxP_num() {
		int maxP_num=0;
		Connection conn = null;
		try {
			conn=getConnection();
			ProductDAO productDAO = ProductDAO.getInstance();
			productDAO.setConnection(conn);
			maxP_num = productDAO.getProductMaxP_num();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return maxP_num;
	}

	public ArrayList<Product> getProductSearchList(String pSearch) {
		ArrayList<Product> productList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ProductDAO productDAO = ProductDAO.getInstance();
			productDAO.setConnection(conn);
			
			productList = productDAO.selectProductSearchList(pSearch);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return productList;
	}
	

}
