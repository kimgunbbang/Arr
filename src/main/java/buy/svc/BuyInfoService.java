package buy.svc;

import java.sql.Connection;

import dao.BuyDAO;

import static db.JdbcUtil.*;
public class BuyInfoService {

	public boolean insertBuy(int buy_num, String string) {
		boolean success = false;
		Connection conn = null;
		
		try {
			conn = getConnection();
			BuyDAO buyDAO = BuyDAO.getInstance();
			buyDAO.setConnection(conn);
			
			int insert = buyDAO.insertBuy(buy_num,string);
			if(insert>0) {
				success = true;
				commit(conn);
			}else {
				rollback(conn);
			}
		}catch(Exception e) {
			rollback(conn);
		}finally {
			close(conn);
		}
		return success;
	}

}
