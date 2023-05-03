package buy.svc;

import java.sql.Connection;
import java.util.ArrayList;
import static db.JdbcUtil.*;
import vo.Buy;

public class BuyService {

	public boolean insertBuyInfo(ArrayList<Buy> inventoryCheck) {
		boolean success = false;
		Connection conn = null;
		try {
			conn = getConnection();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return success;
	}

}
