package inventory.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import inventory.svc.InventoryListService;
import product.svc.ProductListService;
import vo.ActionForward;
import vo.Inventory;
import vo.PageInfo;
import vo.Product;

public class InventorySearchListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;//널처리해주고
		String[] searchinfo = {request.getParameter("invenSearchOption"),
								request.getParameter("invenSearchValue"),
								request.getParameter("invenSearchValueStartDate"),
								request.getParameter("invenSearchValueEndDate")};//검색정보 담을거임
		
		System.out.println(Arrays.toString(searchinfo));
		
		//날짜값이 아무것도 안들어 가 있을때,
		if(request.getParameter("invenSearchValueStartDate").equals("")&&request.getParameter("invenSearchValueEndDate").equals("")) {
			if(request.getParameter("invenSearchValue").trim().equals("")) {//일반검색값이 아무것도없을때
				forward = new ActionForward("inventoryList.in",true);
//				//페이지 들어가는부분 그냥 갖다붙여쓰면됨-페이지계산
//				InventoryListService inventoryListService = new InventoryListService();
//				int page = 1;//첫페이지
//				int limit = 10;//한페이지에 목록수
//				int limitpage = 10;//한페이지에서 보이는 페이지수
//				if(request.getParameter("page") != null) {
//					page = Integer.parseInt(request.getParameter("page"));
//				}
//				int listcount =  inventoryListService.getListCount();
//				int maxpage = (int)((double)listcount/limit+0.98);
//				int startpage=((int)((double)page/limitpage+0.9)-1)*limitpage+1;
//				int endpage = startpage+limitpage-1;
//				if(endpage> maxpage) endpage = maxpage;
//				PageInfo pageinfo = new PageInfo();
//				pageinfo.setEndpage(endpage);
//				pageinfo.setListcount(listcount);
//				pageinfo.setMaxpage(maxpage);
//				pageinfo.setPage(page);
//				pageinfo.setStartpage(startpage);
//				//전체리스트보여주기
//				inventoryListService = new InventoryListService();
//				ArrayList<Inventory> inventoryList = inventoryListService.inventoryAllList(page,limit);
//				
//				ProductListService productListService = new ProductListService();
//				ArrayList<Product> productList = productListService.getProductAllList();
//				
//				request.setAttribute("searchinfo", null);
//				request.setAttribute("pageinfo", pageinfo);
//				request.setAttribute("inventoryList", inventoryList);
//				request.setAttribute("productList", productList);
//				request.setAttribute("pagefile", "/inventory/inventoryList.jsp");
//				forward = new ActionForward("/index.jsp",false);
			}else {//일반검색값이 있을때
				String invenSearchValue = request.getParameter("invenSearchValue").trim();//검색값저장해주고
				String invenSearchOption = request.getParameter("invenSearchOption");//검색옵션도 저장해주고
				
				//페이지 들어가는부분 그냥 갖다붙여쓰면됨-페이지계산
				InventoryListService inventoryListService = new InventoryListService();
				int page = 1;//첫페이지
				int limit = 10;//한페이지에 목록수
				int limitpage = 10;//한페이지에서 보이는 페이지수
				if(request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
				}
				int listcount =  inventoryListService.getListCount(invenSearchOption,invenSearchValue);
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
				ArrayList<Inventory> inventoryList = inventoryListService.inventorySearchList(invenSearchOption,invenSearchValue,page,limit);
				
				ProductListService productListService = new ProductListService();
				ArrayList<Product> productList = productListService.getProductAllList();
				
				request.setAttribute("searchinfo", searchinfo);
				request.setAttribute("pageinfo", pageinfo);
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
			
			//페이지 들어가는부분 그냥 갖다붙여쓰면됨-페이지계산
			InventoryListService inventoryListService = new InventoryListService();
			int page = 1;//첫페이지
			int limit = 10;//한페이지에 목록수
			int limitpage = 10;//한페이지에서 보이는 페이지수
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			int listcount =  inventoryListService.getListCount(invenSearchOption,invenStartDate,invenEndDate);
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
			ArrayList<Inventory> inventoryList = 
					inventoryListService.inventorySearchList(invenSearchOption,invenStartDate,invenEndDate,page,limit);
			
			ProductListService productListService = new ProductListService();
			ArrayList<Product> productList = productListService.getProductAllList();
			
			request.setAttribute("searchinfo", searchinfo);
			request.setAttribute("pageinfo", pageinfo);
			request.setAttribute("inventoryList", inventoryList);
			request.setAttribute("productList", productList);
			request.setAttribute("pagefile", "/inventory/inventoryList.jsp");
			forward = new ActionForward("/index.jsp",false);
		}
		
		return forward;
	}

}
