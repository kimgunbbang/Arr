package delivery.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.DeliveryDAO;
import vo.Delivery;

public class DeliveryrModifyService {

	public boolean updateDelivery(Delivery delivery) {
		boolean update = false;
		Connection conn= getConnection();

		try {
			DeliveryDAO deliveryDAO = DeliveryDAO.getInstance();
			deliveryDAO.setConnection(conn);
			int updateResult= deliveryDAO.updateDelivery(delivery);
			
			if(updateResult>0) {
				update=true;
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
		return update;
	}

}
