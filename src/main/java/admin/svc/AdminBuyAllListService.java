package admin.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BuyDAO;

import static db.JdbcUtil.*;
import vo.Buy;
import vo.BuyList;

public class AdminBuyAllListService {

	public ArrayList<BuyList> getBuyAllList() {
		ArrayList<BuyList> buyList = new ArrayList<BuyList>();
		Connection conn = null;
		try {
			conn=getConnection();
			BuyDAO buyDAO = BuyDAO.getInstance();
			buyDAO.setConnection(conn);
			buyList = buyDAO.getBuyAllList();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return buyList;
	}

}
