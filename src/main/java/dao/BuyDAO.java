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
			for(Buy buy : inventoryCheck) {
				//순번구하기
				pstmt = conn.prepareStatement("select max(p_num) from product");
		        rs = pstmt.executeQuery();
		        if(!rs.next()) {
		           num=1;
		        }else {
		           num=rs.getInt(1)+1;
		        }
		        close(rs);
		        close(pstmt);
		        
		        //insert하기
		        pstmt=conn.prepareStatement(sql);
		        pstmt.setInt(1, num);
		        pstmt.setString(2, buy.getId());
		        pstmt.setString(2, buy.getBuy_memo());
		        pstmt.setString(2, buy.getBuy_memo());
			}
			
		}catch(Exception e) {
			System.out.println("BuyDAO insertBuyInfo에러임");
			e.printStackTrace();
		}finally {
			
		}
        
		
		return insertcount;
	}
	
}//BuyDAO클래스끝
