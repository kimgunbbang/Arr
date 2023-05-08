package dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Cart;

import static db.JdbcUtil.*;

public class CartDAO {
	private static CartDAO cartDAO;
	private Connection conn;
	
	private CartDAO() {}
	
	public static CartDAO getInstance() {
		if(cartDAO == null) {
			cartDAO = new CartDAO();
		}
		return cartDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}


	public ArrayList<Cart> selectCartList(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Cart> cartList = new ArrayList<Cart>();

		try {
			pstmt = conn.prepareStatement("SELECT * FROM cart WHERE id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Cart cart = new Cart();
				cart.setCart_num(rs.getInt("cart_num"));
				cart.setId(rs.getString("id"));
				cart.setP_num(rs.getInt("p_num"));
				cart.setP_name(rs.getString("p_name"));
				cart.setP_image(rs.getString("p_image"));
				cart.setP_price(rs.getInt("p_price"));
				cart.setCart_qty(rs.getInt("cart_qty"));
				cartList.add(cart);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return cartList;
	}

	public int selectTotalMoney(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalMoney = 0;

		try {
			pstmt = conn.prepareStatement("SELECT SUM(p_price * cart_qty) FROM cart WHERE id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				totalMoney = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return totalMoney;
	}

	public boolean insertCart(Cart cart) {
		boolean isInsertSuccess = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Cart cart2 = new Cart();
		String sql = "INSERT INTO cart VALUES (?,?,?,?,?,?,1)";
		int num;
		try {
			//상품정보담기
			String sql1 = "select  * from product where p_num=?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, cart.getP_num());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cart2.setId(cart.getId());
				cart2.setP_image(rs.getString("p_image"));
				cart2.setP_name(rs.getString("p_name"));
				cart2.setP_price(rs.getInt("p_price"));
				cart2.setP_num(cart.getP_num());
			}
			close(rs);
			close(pstmt);
			//insert하기
			pstmt = conn.prepareStatement("select max(cart_num) from cart");
	        rs = pstmt.executeQuery();
	        if(!rs.next()) {
	           num=1;
	        }else {
	           num=rs.getInt(1)+1;
	        }
	        close(rs);
	        close(pstmt);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,num);
			pstmt.setInt(2, cart2.getP_num());
			pstmt.setInt(3, cart2.getP_price());
			pstmt.setString(4, cart2.getP_name());
			pstmt.setString(5, cart2.getP_image());
			pstmt.setString(6, cart2.getId());
			
			int insertCount = pstmt.executeUpdate();

			if (insertCount > 0) {
				isInsertSuccess = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return isInsertSuccess;
	}


}
	
