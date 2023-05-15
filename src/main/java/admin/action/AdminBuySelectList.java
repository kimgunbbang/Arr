package admin.action;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.svc.AdminBuyAllListService;
import buy.svc.BuyService;
import vo.ActionForward;
import vo.BuyList;

public class AdminBuySelectList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String buy_state = request.getParameter("buy_state");
		
		AdminBuyAllListService adminBuyAllListService = new AdminBuyAllListService();
		ArrayList<BuyList> buyList = adminBuyAllListService.getBuySelectList(buy_state);
		
		BuyService buyService = new BuyService();
		ArrayList<Integer> buyNumList = buyService.getBuyNumSelectList(buy_state);
		
		Collections.reverse(buyNumList);//최근구매내역이 먼저 나오게 역순처리
		request.setAttribute("state", buy_state);
		request.setAttribute("buyNumList", buyNumList);
		request.setAttribute("buyList", buyList);
		request.setAttribute("pagefile", "/admin/adminBuyList.jsp");
		forward = new ActionForward("/index.jsp",false);
		return forward;
	}

}
