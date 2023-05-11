package buy.svc;

import java.sql.Connection;

import dao.BuyDAO;

import static db.JdbcUtil.*;
public class BuyInfoDeleteService {

	public boolean deleteBuyInfo(int buy_num) {
		boolean isCancel=false;
		Connection conn = null;
		
		try {
			conn = getConnection();
			BuyDAO buyDAO= BuyDAO.getInstance();
			buyDAO.setConnection(conn);
			
			int deleteCount = buyDAO.deleteBuyInfo(buy_num);
			if(deleteCount>0) {
				commit(conn);
				isCancel = true;
			}else {
				rollback(conn);
			}
		}catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return isCancel;
		
	}
	
}
