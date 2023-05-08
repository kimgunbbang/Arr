package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import static db.JdbcUtil.*;
import vo.Buy;
import vo.BuyInfo;

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

	public ArrayList<Buy> getBuyList(String id) {
		ArrayList<Buy> buyList = new ArrayList<Buy>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from buy where id=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					Buy buy = new Buy();
					buy.setBuy_date(rs.getDate("buy_date"));
					buy.setBuy_num(rs.getInt("buy_num"));
					buy.setBuy_memo(rs.getString("buy_memo"));
					buy.setBuy_state(rs.getString("buy_state"));
					buy.setP_num(rs.getInt("p_num"));
					buy.setBuy_qty(rs.getInt("buy_qty"));
					buy.setBuy_totalmoney(rs.getInt("buy_totalmoney"));
					buy.setId(rs.getString("id"));
					buyList.add(buy);
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("buyDAO getBuyList메서드 에러임");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return buyList;
	}

	public int insertBuyInfo(BuyInfo buyInfo) {
		int insertcount=0;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String sql = "insert into buyinfo values(?,?,?,?,?,?,?,?,now())";
		int num;
		try {
			//순번구하기
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
	        pstmt.setInt(2, buyInfo.getBuy_num());
	        pstmt.setString(3, buyInfo.getBuy_name());
	        pstmt.setString(4, buyInfo.getBuy_phone());
	        pstmt.setString(5, buyInfo.getBuy_zipcode());
	        pstmt.setString(6, buyInfo.getBuy_addr());
	        pstmt.setString(7, buyInfo.getBuy_addr2());
	        pstmt.setString(8, buyInfo.getDeli_memo());
	        
	        insertcount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
	        close(pstmt);
		}
		
		return insertcount;
	}

	public ArrayList<Integer> getBuyNumList(String id) {
		ArrayList<Integer> buyNumList = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select distinct buy_num from buy where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				buyNumList.add(rs.getInt("buy_num"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return buyNumList;
	}
	
}//BuyDAO클래스끝
