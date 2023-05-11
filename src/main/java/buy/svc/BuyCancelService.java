package buy.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BuyDAO;
import vo.Buy;
import vo.Product;

import static db.JdbcUtil.*;
public class BuyCancelService {

	public boolean buyChangeState(int buy_num) {
		boolean isChange = false;
		Connection conn = null;
		
		try {
			conn=getConnection();
			BuyDAO buyDAO = BuyDAO.getInstance();
			buyDAO.setConnection(conn);
			int changeCount = buyDAO.buyChangeState(buy_num);
			if(changeCount>0) {
				commit(conn);
				isChange = true;
			}else {
				rollback(conn);
			}
		}catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return isChange;
	}

	public ArrayList<Buy> selectCancelProduct(int buy_num) {
		ArrayList<Buy> productList = new ArrayList<Buy>();
		Connection conn = null;
		try {
			conn=getConnection();
			BuyDAO buyDAO = BuyDAO.getInstance();
			buyDAO.setConnection(conn);
			productList = buyDAO.selectCancelProduct(buy_num);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return productList;
	}

}
