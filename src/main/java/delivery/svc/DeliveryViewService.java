package delivery.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.DeliveryDAO;
import vo.Delivery;

public class DeliveryViewService {

	public Delivery selectDelivery(String id) {
		Delivery delivery = new Delivery();
		Connection conn=getConnection();
		try {
			DeliveryDAO deliveryDAO = DeliveryDAO.getInstance();
			deliveryDAO.setConnection(conn);
			delivery = deliveryDAO.selectDelivery(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return delivery;
	}

}