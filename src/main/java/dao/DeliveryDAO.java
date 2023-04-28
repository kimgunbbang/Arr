package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static db.JdbcUtil.*;

import vo.Delivery;
import vo.User;

public class DeliveryDAO {
	private static DeliveryDAO deliveryDAO;
	private Connection conn;
	
	private DeliveryDAO() {}
	
	public static DeliveryDAO getInstance() {
		if(deliveryDAO == null) {
			deliveryDAO = new DeliveryDAO();
		}
		return deliveryDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public int addDelivery(Delivery delivery) {
		int addCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "insert into delivery values(?,?,?,?,?,?,?,?)";
		int num;
        
		try {
			pstmt = conn.prepareStatement("select max(deli_num) from delivery");
	        rs = pstmt.executeQuery();
	        if(!rs.next()) {
	           num=1;
	        }else {
	           num=rs.getInt(1)+1;
	        }
	        close(rs);
	        close(pstmt);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, delivery.getId());
			pstmt.setString(3, delivery.getDeli_name());
			pstmt.setString(4, delivery.getDeli_zipcode());
			pstmt.setString(5, delivery.getDeli_addr());
			pstmt.setString(6, delivery.getDeli_addr2());
			pstmt.setString(7, delivery.getDeli_username());
			pstmt.setString(8, delivery.getDeli_phone());
			
			addCount = pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("deliveryAdd에러"+e);
		}finally {
			close(pstmt);
		}
		
		return addCount;
	}

	public ArrayList<Delivery> selectDeliveryList() {
		ArrayList<Delivery> deliveryList = new ArrayList<Delivery>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from delivery";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					Delivery delivery = new Delivery();
					delivery.setDeli_name("deli_name");
					delivery.setDeli_zipcode("deli_zipcode");
					delivery.setDeli_addr("deli_addr");
					delivery.setDeli_addr2("deli_addr2");
					delivery.setDeli_username("deli_username");
					delivery.setDeli_phone("deli_phone");
					deliveryList.add(delivery);
				}while(rs.next());
			}
		}catch (Exception e) {
			System.out.println("selectDeliveryList에러 "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return deliveryList;
	}

	public Delivery selectDelivery(String id) {
		Delivery delivery = new Delivery();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from delivery where id = ?";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {//결과가 있으면
				delivery.setDeli_name(rs.getString("deli_name"));
				delivery.setDeli_zipcode(rs.getString("deli_zipcode"));
				delivery.setDeli_addr(rs.getString("deli_addr"));
				delivery.setDeli_addr2(rs.getString("deli_addr2"));
				delivery.setDeli_username(rs.getString("deli_username"));
				delivery.setDeli_phone(rs.getString("deli_phone"));
			}
		}catch(Exception e) {
			System.out.println("selectDelivery에러 :"+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return delivery;
	}

	public int deleteDelivery(String id) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from delivery where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("deleteDelivery에러 : "+e);
		}finally {
			close(pstmt);
		}
		
		return deleteCount;
	}

	public int updateDelivery(Delivery delivery) {
		  int updateResult = 0;
		    PreparedStatement pstmt = null;
		    
		    String sql = "UPDATE delivery SET deli_name=?, deli_zipcode=?, deli_addr=?, deli_addr2=?, deli_username=?, deli_phone=? WHERE id=?";
		    
		    try {
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1, delivery.getDeli_name());
		        pstmt.setString(2, delivery.getDeli_zipcode());
		        pstmt.setString(3, delivery.getDeli_addr());
		        pstmt.setString(4, delivery.getDeli_addr2());
		        pstmt.setString(5, delivery.getDeli_username());
		        pstmt.setString(6, delivery.getDeli_phone());
		        pstmt.setString(7, delivery.getId());
		        
		        updateResult = pstmt.executeUpdate();
		        
		    } catch(Exception e) {
		        e.printStackTrace();
		    } finally {
		        close(pstmt);
		    }
		    
		    return updateResult;
		}
}
