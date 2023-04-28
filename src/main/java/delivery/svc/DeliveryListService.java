package delivery.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.DeliveryDAO;
import vo.Delivery;

public class DeliveryListService {

	public ArrayList<Delivery> getDeliveryList(String id) {
		ArrayList<Delivery> deliveryList = null;
		Connection conn = null;
		DeliveryDAO deliveryDAO = null;
		
		try {
			conn=getConnection();
			deliveryDAO = DeliveryDAO.getInstance();
			deliveryDAO.setConnection(conn);
			deliveryList = deliveryDAO.selectDeliveryList(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		
		return deliveryList;
	}

}
