package delivery.svc;

import java.sql.Connection; 

import dao.DeliveryDAO;
import vo.Delivery;
import vo.User;

import static db.JdbcUtil.*;

public class DeliveryAddService {

	public boolean addDeli(Delivery delivery) {
		boolean addResult = false;
		DeliveryDAO deliveryDAO = DeliveryDAO.getInstance();
		Connection conn = null;
		
		try {
			conn = getConnection();
			deliveryDAO.setConnection(conn);
			int addCount = deliveryDAO.addDelivery(delivery);
			
			if(addCount > 0) {
				addResult = true;
				commit(conn);
			}else {
				rollback(conn);
			}
			
		}catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return addResult;
	}

	public boolean addDeli(User user) {
		boolean addResult = false;
		DeliveryDAO deliveryDAO = DeliveryDAO.getInstance();
		Connection conn = null;
		
		try {
			conn = getConnection();
			deliveryDAO.setConnection(conn);
			int addCount = deliveryDAO.addDelivery(user);
			
			if(addCount > 0) {
				addResult = true;
				commit(conn);
			}else {
				rollback(conn);
			}
			
		}catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return addResult;
	}

}
