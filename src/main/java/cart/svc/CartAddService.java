package cart.svc;

import static db.JdbcUtil.*; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.CartDAO;
import dao.NonCartDAO;
import vo.Cart;
import vo.Noncart;
import vo.Product;

public class CartAddService {


	public boolean addCart(Cart cart) {
		boolean isAddSuccess = false;
		Connection conn = null;

		try {
			conn = getConnection();
			CartDAO cartDAO = CartDAO.getInstance();
			cartDAO.setConnection(conn);
			
			boolean isExist = cartDAO.isCartExist(cart.getId(), cart.getP_num());
	        if (isExist) {
	            return false;
	        }else {
	        	isAddSuccess = cartDAO.insertCart(cart);
	        }
			
		
			if (isAddSuccess) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				close(conn);
			}
		}
		return isAddSuccess;
	}

	public boolean NonAddCart(Noncart noncart) {
		boolean isAddSuccess = false;
		Connection conn = null;

		try {
			conn = getConnection();
			NonCartDAO noncartDAO = NonCartDAO.getInstance();
			noncartDAO.setConnection(conn);
			
			boolean isExist = noncartDAO.isNonCartExist(noncart.getId(), noncart.getP_num());
	        if (isExist) {
	            return false;
	        }else {
	        	isAddSuccess = noncartDAO.insertNonCart(noncart);
	        }
			
		
			if (isAddSuccess) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				close(conn);
			}
		}
		return isAddSuccess;
	}

}