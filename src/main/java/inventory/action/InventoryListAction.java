package inventory.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import inventory.svc.InventoryListService;
import product.svc.ProductListService;
import vo.ActionForward;
import vo.Inventory;
import vo.Product;

public class InventoryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;//널처리해주고
		
		InventoryListService inventoryListService = new InventoryListService();
		ArrayList<Inventory> inventoryList = inventoryListService.inventoryAllList();
		
		ProductListService productListService = new ProductListService();
		ArrayList<Product> productList = productListService.getProductAllList();
		
		request.setAttribute("inventoryList", inventoryList);
		request.setAttribute("productList", productList);
		request.setAttribute("pagefile", "/inventory/inventoryList.jsp");
		forward = new ActionForward("/index.jsp",false);
		
		return forward;
	}

}
