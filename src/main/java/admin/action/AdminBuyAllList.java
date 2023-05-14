package admin.action;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.svc.AdminBuyAllListService;
import buy.svc.BuyService;
import vo.ActionForward;
import vo.Buy;
import vo.BuyList;

public class AdminBuyAllList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		AdminBuyAllListService adminBuyAllListService = new AdminBuyAllListService();
		ArrayList<BuyList> buyList = adminBuyAllListService.getBuyAllList();
		
		BuyService buyService = new BuyService();
		ArrayList<Integer> buyNumList = buyService.getBuyNumAllList();
		
		Collections.reverse(buyNumList);//최근구매내역이 먼저 나오게 역순처리
		request.setAttribute("buyNumList", buyNumList);
		request.setAttribute("buyList", buyList);
		request.setAttribute("pagefile", "/admin/adminBuyList.jsp");
		forward = new ActionForward("/index.jsp",false);
		return forward;
	}

}
