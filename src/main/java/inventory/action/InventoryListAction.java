package inventory.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import inventory.svc.InventoryListService;
import product.svc.ProductListService;
import vo.ActionForward;
import vo.Inventory;
import vo.PageInfo;
import vo.Product;

public class InventoryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;//널처리해주고
		
		//페이지 들어가는부분 그냥 갖다붙여쓰면됨-페이지계산
		InventoryListService inventoryListService = new InventoryListService();
		int page = 1;//첫페이지
		int limit = 10;//한페이지에 목록수
		int limitpage = 10;//한페이지에서 보이는 페이지수
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int listcount =  inventoryListService.getListCount();
		int maxpage = (int)((double)listcount/limit+0.98);
		int startpage=((int)((double)page/limitpage+0.9)-1)*limitpage+1;
		int endpage = startpage+limitpage-1;
		if(endpage> maxpage) endpage = maxpage;
		PageInfo pageinfo = new PageInfo();
		pageinfo.setEndpage(endpage);
		pageinfo.setListcount(listcount);
		pageinfo.setMaxpage(maxpage);
		pageinfo.setPage(page);
		pageinfo.setStartpage(startpage);
		
		
		
		
		inventoryListService = new InventoryListService();
		ArrayList<Inventory> inventoryList = inventoryListService.inventoryAllList(page,limit);
		
		ProductListService productListService = new ProductListService();
		ArrayList<Product> productList = productListService.getProductAllList();
		
		request.setAttribute("pageinfo", pageinfo);
		request.setAttribute("inventoryList", inventoryList);
		request.setAttribute("productList", productList);
		request.setAttribute("pagefile", "/inventory/inventoryList.jsp");
		forward = new ActionForward("/index.jsp",false);
		
		return forward;
	}


}
