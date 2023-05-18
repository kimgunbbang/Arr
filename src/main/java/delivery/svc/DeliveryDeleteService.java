package delivery.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.DeliveryDAO;
import vo.Delivery;

public class DeliveryDeleteService {

	public boolean deleteDelivery(Delivery delivery) {
		boolean deleteDelivery = false;
		Connection conn = null;
		int deleteCount = 0;
		
		
		try {
			conn = getConnection();
			DeliveryDAO deliveryDAO = DeliveryDAO.getInstance();
			deliveryDAO.setConnection(conn);
			deleteCount = deliveryDAO.deleteDelivery(delivery);
			
			if(deleteCount>0) {
				commit(conn);
				deleteDelivery = true;
			}else {
				rollback(conn);
			}
		}catch(Exception e ) {
			rollback(conn);
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return deleteDelivery;
		
	}
}
