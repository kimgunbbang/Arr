package delivery.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.DeliveryDAO;
import vo.Delivery;

public class DeliveryViewService {

	public Delivery selectDelivery(String id, String deli_num) {
		Delivery delivery = new Delivery();
		Connection conn=getConnection();
		try {
			DeliveryDAO deliveryDAO = DeliveryDAO.getInstance();
			deliveryDAO.setConnection(conn);
			delivery = deliveryDAO.selectDelivery(id,deli_num);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return delivery;
	}

	public Delivery selectDelivery(Delivery delivery) {
		
		Connection conn=getConnection();
		try {
			DeliveryDAO deliveryDAO = DeliveryDAO.getInstance();
			deliveryDAO.setConnection(conn);
			delivery = deliveryDAO.selectDelivery(delivery);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return delivery;
	}

}