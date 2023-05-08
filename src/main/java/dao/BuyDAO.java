package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import static db.JdbcUtil.*;
import vo.Buy;

public class BuyDAO {
	private static BuyDAO buyDAO;//싱글톤1 : 선언
	private Connection conn; //★★★★★DAO에서 사용할 커넥트 무조건만들어야함!!!!
	private BuyDAO() {}//싱글톤2 : 객채생성막기
	
	public static BuyDAO getInstance() { //싱글톤3 : 메서드로 작성
		if(buyDAO == null) {
			buyDAO = new BuyDAO();
		}
		return buyDAO;
	}

	public void setConnection(Connection conn) {//연결메서드
		this.conn = conn;
		
	}

	public int insertBuyInfo(ArrayList<Buy> inventoryCheck) {
		int insertcount=0;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "insert into buy values (?,?,now(),?,'ready',?,?,?)";//1구매순번, 2id, (구매날짜), 3구매시메모
																//(구매상태), 4구매상품의금액, 5상품번호, 6구매수량
		int num;
		try {
			//순번구하기
			pstmt = conn.prepareStatement("select max(buy_num) from buy");
	        rs = pstmt.executeQuery();
	        if(!rs.next()) {
	           num=1;
	        }else {
	           num=rs.getInt(1)+1;
	        }
	        close(rs);
	        close(pstmt);
			for(Buy buy : inventoryCheck) {
				
		        
		        //insert하기
		        pstmt=conn.prepareStatement(sql);
		        pstmt.setInt(1, num);
		        pstmt.setString(2, buy.getId());
		        pstmt.setString(3, buy.getBuy_memo());
		        pstmt.setInt(4, buy.getBuy_totalmoney());
		        pstmt.setInt(5, buy.getP_num());
		        pstmt.setInt(6, buy.getBuy_qty());
		        insertcount = pstmt.executeUpdate();
		        if(!(insertcount>0)) {
		        	return 0;
		        }
		        
			}
			
		}catch(Exception e) {
			System.out.println("BuyDAO insertBuyInfo에러임");
			e.printStackTrace();
		}finally {
			close(rs);
	        close(pstmt);
		}
        
		
		return insertcount;
	}

	public int getBuyNum(String id) {//구매상세테이블에 넣을 구매번호얻음
		int buynum=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select max(buy_num) from buy where id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				buynum=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return buynum;
	}

	public int insertBuy(int buy_num, String string) {
		int insertbuy=0;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String sql = "insert into buyinfo values(?,?,?)";
		int num;
		try {
	         pstmt = conn.prepareStatement("select max(buyinfo_num) from buyinfo");
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
			pstmt.setInt(2, buy_num);
			pstmt.setInt(3, Integer.parseInt(string));
			insertbuy=pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return insertbuy;
	}

	public int cartDelete(String[] cartList) {
		int success = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from cart where cart_num=?";
		try {
			for(int i=0;i<cartList.length;i++) {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, cartList[i]);
				success = pstmt.executeUpdate();
				if(!(success>0)) {
					return 0;
				}
			}
		}catch(Exception e) {
			System.out.println("buyDAO cartDelete메서드 에러임");
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return success;
	}
	
}//BuyDAO클래스끝
