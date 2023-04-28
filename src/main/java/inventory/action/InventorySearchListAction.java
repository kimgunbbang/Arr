package inventory.action;

import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import inventory.svc.InventoryListService;
import product.svc.ProductListService;
import vo.ActionForward;
import vo.Inventory;
import vo.Product;

public class InventorySearchListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;//널처리해주고
		//날짜값이 아무것도 안들어 가 있을때,
		if(request.getParameter("invenSearchValueStartDate").equals("")&&request.getParameter("invenSearchValueEndDate").equals("")) {
			if(request.getParameter("invenSearchValue").trim().equals("")) {//일반검색값이 아무것도없을때
				//전체리스트보여주기
				InventoryListService inventoryListService = new InventoryListService();
				ArrayList<Inventory> inventoryList = inventoryListService.inventoryAllList();
				
				ProductListService productListService = new ProductListService();
				ArrayList<Product> productList = productListService.getProductAllList();
				
				request.setAttribute("inventoryList", inventoryList);
				request.setAttribute("productList", productList);
				request.setAttribute("pagefile", "/inventory/inventoryList.jsp");
				forward = new ActionForward("/index.jsp",false);
			}else {//일반검색값이 있을때
				String invenSearchValue = request.getParameter("invenSearchValue").trim();//검색값저장해주고
				String invenSearchOption = request.getParameter("invenSearchOption");//검색옵션도 저장해주고
				
				InventoryListService inventoryListService = new InventoryListService();
				ArrayList<Inventory> inventoryList = inventoryListService.inventorySearchList(invenSearchOption,invenSearchValue);
				
				ProductListService productListService = new ProductListService();
				ArrayList<Product> productList = productListService.getProductAllList();
				
				request.setAttribute("inventoryList", inventoryList);
				request.setAttribute("productList", productList);
				request.setAttribute("pagefile", "/inventory/inventoryList.jsp");
				forward = new ActionForward("/index.jsp",false);
			}
		}else if(!request.getParameter("invenSearchValueStartDate").equals("")||
				!request.getParameter("invenSearchValueEndDate").equals("")) {//시작날짜랑 끝날짜 어느하나라도 널값아닐때
			String invenSearchOption = request.getParameter("invenSearchOption");//검색옵션 저장해주고
			String invenStartDate = request.getParameter("invenSearchValueStartDate");//시작날짜 저장해주고,
			String invenEndDate = request.getParameter("invenSearchValueEndDate");//끝날짜 저장해주고,
			
			
			InventoryListService inventoryListService = new InventoryListService();
			ArrayList<Inventory> inventoryList = 
					inventoryListService.inventorySearchList(invenSearchOption,invenStartDate,invenEndDate);
			
			ProductListService productListService = new ProductListService();
			ArrayList<Product> productList = productListService.getProductAllList();
			
			request.setAttribute("inventoryList", inventoryList);
			request.setAttribute("productList", productList);
			request.setAttribute("pagefile", "/inventory/inventoryList.jsp");
			forward = new ActionForward("/index.jsp",false);
		}
		
		//이밑에 날짜로 받았을때 처리도해야함 날짜 두개 다 넣어야함
		
		
		
		return forward;
	}

}
