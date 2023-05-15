package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import static db.JdbcUtil.*;
import vo.Buy;
import vo.BuyInfo;
import vo.BuyList;
import vo.Product;

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

	public ArrayList<BuyList> getBuyList(String id) {
		ArrayList<BuyList> buyList = new ArrayList<BuyList>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from buy join product using (p_num) where id=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					BuyList buy = new BuyList();
					buy.setBuy_date(rs.getDate("buy_date"));
					buy.setBuy_num(rs.getInt("buy_num"));
					buy.setP_num(rs.getInt("p_num"));
					buy.setBuy_qty(rs.getInt("buy_qty"));
					buy.setBuy_totalmoney(rs.getInt("buy_totalmoney"));
					
					buy.setId(rs.getString("id"));
					buy.setP_name(rs.getString("product.p_name"));
					buy.setBuy_state(rs.getString("buy_state"));
					buy.setP_image(rs.getString("p_image"));
					
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
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					buyNumList.add(rs.getInt("buy_num"));
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return buyNumList;
	}

	public BuyInfo getBuyInfoView(String buy_num) {
		BuyInfo buyInfo = new BuyInfo();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from buyinfo where buy_num=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buy_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				buyInfo.setBuy_addr(rs.getString("buy_addr"));
				buyInfo.setBuy_addr2(rs.getString("buy_addr2"));
				buyInfo.setBuy_date(rs.getDate("buy_date"));
				buyInfo.setBuy_name(rs.getString("buy_name"));
				buyInfo.setBuy_num(rs.getInt("buy_num"));
				buyInfo.setBuy_phone(rs.getString("buy_phone"));
				buyInfo.setBuy_zipcode(rs.getString("buy_zipcode"));
				buyInfo.setBuyinfo_num(rs.getInt("buyinfo_num"));
				buyInfo.setDeli_memo(rs.getString("deli_memo"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return buyInfo;
	}

	public int nonCartDelete(String[] cartList) {
		int success = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from noncart where cart_num=?";
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
			System.out.println("buyDAO noncartDelete메서드 에러임");
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return success;
	}

	public int deleteBuyInfo(int buy_num) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from buyinfo where buy_num=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, buy_num);
			deleteCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}

	public int buyChangeState(int buy_num) {
		int changeCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update buy set buy_state = 'cancel' where buy_num=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, buy_num);
			changeCount = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return changeCount;
	}

	public ArrayList<Buy> selectCancelProduct(int buy_num) {
		ArrayList<Buy> productList = new ArrayList<Buy>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from buy where buy_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, buy_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					Buy buy = new Buy();
					buy.setP_num(rs.getInt("p_num"));
					buy.setBuy_qty(rs.getInt("buy_qty"));
					productList.add(buy);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return productList;
	}

	public ArrayList<BuyList> getBuyAllList() {
		ArrayList<BuyList> buyList = new ArrayList<BuyList>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from buy join product using (p_num)";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					BuyList buy = new BuyList();
					buy.setBuy_date(rs.getDate("buy_date"));
					buy.setBuy_num(rs.getInt("buy_num"));
					buy.setBuy_qty(rs.getInt("buy_qty"));
					buy.setBuy_state(rs.getString("buy_state"));
					buy.setBuy_totalmoney(rs.getInt("buy_totalmoney"));
					buy.setId(rs.getString("id"));
					buy.setP_name(rs.getString("product.p_name"));
					buy.setP_num(rs.getInt("p_num"));
					buyList.add(buy);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return buyList;
	}

	public ArrayList<Integer> getBuyNumAllList() {
		ArrayList<Integer> buyNumList = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select distinct buy_num from buy";
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					buyNumList.add(rs.getInt("buy_num"));
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return buyNumList;
	}

	public int stateChange(int buy_num, String buy_state) {
		int statechange = 0;
		PreparedStatement pstmt = null;
		String sql = "update buy set buy_state=? where buy_num=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, buy_state);
			pstmt.setInt(2, buy_num);
			statechange=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return statechange;
	}

	public ArrayList<BuyList> getBuySelectList(String buy_state) {
		ArrayList<BuyList> buyList = new ArrayList<BuyList>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from buy join product using (p_num) where buy_state=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, buy_state);
			System.out.println(pstmt);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					BuyList buy = new BuyList();
					buy.setBuy_date(rs.getDate("buy_date"));
					buy.setBuy_num(rs.getInt("buy_num"));
					buy.setBuy_qty(rs.getInt("buy_qty"));
					buy.setBuy_state(rs.getString("buy_state"));
					buy.setBuy_totalmoney(rs.getInt("buy_totalmoney"));
					buy.setId(rs.getString("id"));
					buy.setP_name(rs.getString("product.p_name"));
					buy.setP_num(rs.getInt("p_num"));
					buyList.add(buy);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return buyList;
	}

	public ArrayList<Integer> getBuyNumSelectList(String buy_state) {
		ArrayList<Integer> buyNumList = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select distinct buy_num from buy where buy_state=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buy_state);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					buyNumList.add(rs.getInt("buy_num"));
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return buyNumList;
	}

	public ArrayList<Buy> getBuyList(int buy_num) {
		ArrayList<Buy> buyList = new ArrayList<Buy>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from buy join product using (p_num) where buy_num=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, buy_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					Buy buy = new Buy();
					buy.setBuy_date(rs.getDate("buy_date"));
					buy.setBuy_num(rs.getInt("buy_num"));
					buy.setP_num(rs.getInt("p_num"));
					buy.setBuy_qty(rs.getInt("buy_qty"));
					buy.setBuy_totalmoney(rs.getInt("buy_totalmoney"));
					
					buy.setId(rs.getString("id"));
					buy.setP_name(rs.getString("product.p_name"));
					buy.setBuy_state(rs.getString("buy_state"));
					buy.setP_image(rs.getString("p_image"));
					
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
	
}//BuyDAO클래스끝
