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

	
}
