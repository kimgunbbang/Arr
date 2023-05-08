package buy.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BuyDAO;

import static db.JdbcUtil.*;
import vo.Buy;

public class BuyService {

	public boolean insertBuyInfo(ArrayList<Buy> inventoryCheck) {
		boolean success = false;
		Connection conn = null;
		try {
			conn = getConnection();
			BuyDAO buyDAO = BuyDAO.getInstance();
			buyDAO.setConnection(conn);
			
			int insertcount = buyDAO.insertBuyInfo(inventoryCheck);
			if(insertcount>0) {//insertcount가 0초과하면
				commit(conn);//commit하고
				success=true;//success 트루만들기
			}else {
				rollback(conn);
			}
		}catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return success;
	}

	public int getBuyNum(String id) {
		int buynum=0;
		Connection conn=null;
		try {
			conn=getConnection();
			BuyDAO buyDAO = BuyDAO.getInstance();
			buyDAO.setConnection(conn);
			
			buynum=buyDAO.getBuyNum(id);
			
		}catch(Exception e) {
			
		}finally {
			close(conn);
		}
		return buynum;
	}

	public ArrayList<Buy> getbuyList(String id) {
		ArrayList<Buy> buyList = new ArrayList<Buy>();
		Connection conn = null;
		try {
			conn=getConnection();
			BuyDAO buyDAO = BuyDAO.getInstance();
			buyDAO.setConnection(conn);
			
			buyList = buyDAO.getBuyList(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return buyList;
	}

}
