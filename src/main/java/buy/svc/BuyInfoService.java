package buy.svc;

import java.sql.Connection;

import dao.BuyDAO;
import vo.BuyInfo;

import static db.JdbcUtil.*;
public class BuyInfoService {

	public boolean insertBuy(BuyInfo buyInfo) {
		boolean success = false;
		Connection conn = null;
		
		try {
			conn = getConnection();
			BuyDAO buyDAO = BuyDAO.getInstance();
			buyDAO.setConnection(conn);
			
			int insert = buyDAO.insertBuyInfo(buyInfo);
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
