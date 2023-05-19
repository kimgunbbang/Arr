package inventory.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.InventoryDAO;

import static db.JdbcUtil.*;
import vo.Inventory;

public class InventoryListService {

	public ArrayList<Inventory> inventoryAllList(int page, int limit) {
		ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();
		Connection conn = null;
		try {
			conn=getConnection();
			InventoryDAO inventoryDAO = InventoryDAO.getInstance();
			inventoryDAO.setConnection(conn);
			
			inventoryList=inventoryDAO.inventoryAllList(page,limit);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			close(conn);
		}
		return inventoryList;
	}

	public ArrayList<Inventory> inventorySearchList(String invenSearchOption, String invenSearchValue, int page, int limit) {
		ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();
		Connection conn = null;
		try {
			conn=getConnection();
			InventoryDAO inventoryDAO = InventoryDAO.getInstance();
			inventoryDAO.setConnection(conn);
			
			inventoryList=inventoryDAO.inventorySearchList(invenSearchOption,invenSearchValue,page,limit);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			close(conn);
		}
		return inventoryList;
	}

	public ArrayList<Inventory> inventorySearchList(String invenSearchOption, String invenStartDate,
			String invenEndDate, int page, int limit) {
		ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();
		Connection conn = null;
		try {
			conn=getConnection();
			InventoryDAO inventoryDAO = InventoryDAO.getInstance();
			inventoryDAO.setConnection(conn);
			
			inventoryList=inventoryDAO.inventorySearchList(invenSearchOption,invenStartDate,invenEndDate,page,limit);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			close(conn);
		}
		return inventoryList;
	}

	public ArrayList<Inventory> inventoryProductList() {
		ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();
		Connection conn = null;
		try {
			conn=getConnection();
			InventoryDAO inventoryDAO = InventoryDAO.getInstance();
			inventoryDAO.setConnection(conn);
			
			inventoryList=inventoryDAO.inventoryProductList();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			close(conn);
		}
		return inventoryList;
	}

	public int getListCount() {
		int listcount=0;
		Connection conn =null;
		try {
			conn=getConnection();
			InventoryDAO inventoryDAO = InventoryDAO.getInstance();
			inventoryDAO.setConnection(conn);
			listcount = inventoryDAO.selectListCount();
		}catch(Exception e) {
			System.out.println("getListCount 에러"+e);
		}finally {
			close(conn);
		}
		
		return listcount;
	}
	
	public int getListCount(String invenSearchOption, String invenSearchValue) {
		int listcount=0;
		Connection conn =null;
		try {
			conn=getConnection();
			InventoryDAO inventoryDAO = InventoryDAO.getInstance();
			inventoryDAO.setConnection(conn);
			listcount = inventoryDAO.selectListCount(invenSearchOption,invenSearchValue);
		}catch(Exception e) {
			System.out.println("getListCount 에러"+e);
		}finally {
			close(conn);
		}
		
		return listcount;
	}

	public int getListCount(String invenSearchOption, String invenStartDate, String invenEndDate) {
		int listcount=0;
		Connection conn =null;
		try {
			conn=getConnection();
			InventoryDAO inventoryDAO = InventoryDAO.getInstance();
			inventoryDAO.setConnection(conn);
			listcount = inventoryDAO.selectListCount(invenSearchOption,invenStartDate,invenEndDate);
		}catch(Exception e) {
			System.out.println("getListCount 에러"+e);
		}finally {
			close(conn);
		}
		
		return listcount;
	}


}
