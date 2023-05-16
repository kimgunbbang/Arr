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

	public BuyInfo buyInfoView(String buy_num) {
		BuyInfo buyInfo = new BuyInfo();
		Connection conn = null;
		
		try {
			conn=getConnection();
			BuyDAO buyDAO = BuyDAO.getInstance();
			buyDAO.setConnection(conn);
			
			buyInfo = buyDAO.getBuyInfoView(buy_num);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return buyInfo;
	}

	public boolean isBuyer(int buy_num, String buy_phone) {
		boolean isBuyer = false;
		Connection conn = null;
		
		try {
			conn=getConnection();
			BuyDAO buyDAO = BuyDAO.getInstance();
			buyDAO.setConnection(conn);
			isBuyer = buyDAO.isBuyer(buy_num,buy_phone);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return isBuyer;
	}

}
