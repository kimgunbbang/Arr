package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import vo.Cart;
import vo.Noncart;

public class NonCartDAO {
	private static NonCartDAO noncartDAO;
	private Connection conn;
	
	private NonCartDAO() {}
	
	public static NonCartDAO getInstance() {
		if(noncartDAO == null) {
			noncartDAO = new NonCartDAO();
		}
		return noncartDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public boolean isNonCartExist(String id, int p_num) {
	    boolean isExist = false;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        String sql = "SELECT * FROM noncart WHERE id=? AND p_num=?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, id);
	        pstmt.setInt(2, p_num);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            isExist = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(rs);
	        close(pstmt);
	    }

	    return isExist;
	}

	public boolean insertNonCart(Noncart noncart) {
		boolean isInsertSuccess = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Noncart noncart2 = new Noncart();
		String sql = "INSERT INTO noncart VALUES (?,?,?,?,?,?,1,?,?)";
		int num;
		try {
			//상품정보담기
			String sql1 = "select  * from product where p_num=?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, noncart.getP_num());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				noncart2.setId(noncart.getId());
				noncart2.setP_image(rs.getString("p_image"));
				noncart2.setP_name(rs.getString("p_name"));
				noncart2.setP_price(rs.getInt("p_price"));
				noncart2.setP_num(noncart.getP_num());
			}
			close(rs);
			close(pstmt);
			//insert하기
			pstmt = conn.prepareStatement("select max(cart_num) from noncart");
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
			pstmt.setInt(2, noncart2.getP_num());
			pstmt.setInt(3, noncart2.getP_price());
			pstmt.setString(4, noncart2.getP_name());
			pstmt.setString(5, noncart2.getP_image());
			pstmt.setString(6, noncart2.getId());
			
			Timestamp cre_date = new Timestamp(System.currentTimeMillis());
	        Timestamp exp_date = new Timestamp(System.currentTimeMillis() + (3 * 24 * 60 * 60 * 1000)); // 임의로 하루 설정
	        pstmt.setTimestamp(7, cre_date);
	        pstmt.setTimestamp(8, exp_date);
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

	public ArrayList<Noncart> selectNonCartList(String uuid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Noncart> cartList = new ArrayList<Noncart>();

		try {
			pstmt = conn.prepareStatement("SELECT * FROM noncart WHERE id=?");
			pstmt.setString(1, uuid);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Noncart noncart = new Noncart();
				noncart.setCart_num(rs.getInt("cart_num"));
				noncart.setId(rs.getString("id"));
				noncart.setP_num(rs.getInt("p_num"));
				noncart.setP_name(rs.getString("p_name"));
				noncart.setP_image(rs.getString("p_image"));
				noncart.setP_price(rs.getInt("p_price"));
				noncart.setCart_qty(rs.getInt("cart_qty"));
				cartList.add(noncart);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return cartList;
	}

	public int selectTotalMoney(String uuid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalMoney = 0;

		try {
			pstmt = conn.prepareStatement("SELECT SUM(p_price * cart_qty) FROM noncart WHERE id=?");
			pstmt.setString(1, uuid);
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

	public int downQty(Noncart noncart) {
		int downQty = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE noncart SET cart_qty = cart_qty - 1 WHERE cart_num = ?";
		 try {
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, noncart.getCart_num());


		        downQty = pstmt.executeUpdate();
		        
		    } catch(Exception e) {
		        e.printStackTrace();
		    } finally {
		        close(pstmt);
		    }
		    
		return downQty;
	}

	public int upQty(Noncart noncart) {
		int upQty = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE noncart SET cart_qty = cart_qty + 1 WHERE cart_num = ?";
		 try {
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, noncart.getCart_num());


		        upQty = pstmt.executeUpdate();
		        
		    } catch(Exception e) {
		        e.printStackTrace();
		    } finally {
		        close(pstmt);
		    }
		    
		return upQty;
	}

	public int removeCart(String[] cartList) {
		int removeCount = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from noncart where cart_num = ?";
		try {
			for(int i=0;i<cartList.length;i++) {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, cartList[i]);
				removeCount = pstmt.executeUpdate();
				if(!(removeCount>0)) {
					return 0;
				}
			}
		}catch(Exception e) {
			System.out.println("buyDAO cartDelete메서드 에러임");
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return removeCount;
	}
}
