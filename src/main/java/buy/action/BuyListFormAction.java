package buy.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import buy.svc.BuyService;
import vo.ActionForward;
import vo.Buy;
import vo.BuyList;

public class BuyListFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");//세션에 아이디 받아와서
		
		
		ArrayList<BuyList> buyList = new ArrayList<BuyList>();//구매목록담을 배열
		BuyService buyService = new BuyService();
		buyList = buyService.getbuyList(id);
		ArrayList<Integer> buyNumList = buyService.getBuyNumList(id);
		
		request.setAttribute("buyNumList", buyNumList);//17,18,19,20,21,22
		request.setAttribute("buyList", buyList);
		request.setAttribute("pagefile", "/buy/buyListForm.jsp");
		forward = new ActionForward("/index.jsp",false);
		return forward;
	}

}
