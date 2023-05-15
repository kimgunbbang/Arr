package admin.svc;

import java.sql.Connection;

import dao.BuyDAO;

import static db.JdbcUtil.*;
public class BuyStateChangeService {

	public boolean stateChange(int buy_num, String buy_state) {
		boolean ischange=false;
		Connection conn = null;
		
		try {
			conn=getConnection();
			BuyDAO buyDAO = BuyDAO.getInstance();
			buyDAO.setConnection(conn);
			int changecount = buyDAO.stateChange(buy_num,buy_state);
			if(changecount>0) {
				ischange = true;
				commit(conn);
			}else {
				rollback(conn);
			}
		}catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return ischange;
	}

}
