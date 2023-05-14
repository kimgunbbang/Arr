package inventory.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.InventoryDAO;
import vo.Buy;
import vo.Inventory;

import static db.JdbcUtil.*;
public class InventoryInOutService {

	public boolean inOutQty(Inventory inventory) {
		boolean success = false;
		Connection conn=null;
		
		try {
			conn = getConnection();
			InventoryDAO inventoryDAO = InventoryDAO.getInstance();
			inventoryDAO.setConnection(conn);
			int result = inventoryDAO.inserInOutQty(inventory);
			
			if(result>0) {
				success = true;
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
		
		return success;
	}

	public boolean inOutQty(ArrayList<Buy> inventoryCheck) {
		boolean success = false;
		Connection conn=null;
		
		try {
			conn = getConnection();
			InventoryDAO inventoryDAO = InventoryDAO.getInstance();
			inventoryDAO.setConnection(conn);
			int result = inventoryDAO.inserInOutQty(inventoryCheck);
			
			if(result>0) {
				success = true;
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
		
		return success;
	}

	public boolean cancelinOutQty(ArrayList<Buy> productList) {
		boolean success = false;
		Connection conn=null;
		
		try {
			conn = getConnection();
			InventoryDAO inventoryDAO = InventoryDAO.getInstance();
			inventoryDAO.setConnection(conn);
			int result = inventoryDAO.inserCancelInOutQty(productList);
			
			if(result>0) {
				success = true;
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
		
		return success;
	}

	public boolean inOutQty(int productNum) {
		boolean success = false;
		Connection conn=null;
		
		try {
			conn = getConnection();
			InventoryDAO inventoryDAO = InventoryDAO.getInstance();
			inventoryDAO.setConnection(conn);
			int result = inventoryDAO.inserInOutQty(productNum);
			
			if(result>0) {
				success = true;
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
		
		return success;
	}

}
