package buy.action;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import buy.svc.BuyService;
import review.svc.ReviewListService;
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
		ArrayList<Boolean> reviewCheckList = new ArrayList<Boolean>();
		ReviewListService reviewListService = new ReviewListService();
		reviewCheckList = reviewListService.isReviewCheckList(buyNumList,buyList);
		//Collections.reverse(reviewCheckList);
		Collections.reverse(buyNumList);//최근구매내역이 먼저 나오게 역순처리 22/21/12
		System.out.println("buyList"+buyList.toString());
		
		System.out.println("불린배열"+reviewCheckList);
		
		
		request.setAttribute("reviewCheckList", reviewCheckList);
		request.setAttribute("buyNumList", buyNumList);//17,18,19,20,21,22
		request.setAttribute("buyList", buyList);
		request.setAttribute("pagefile", "/buy/buyListForm.jsp");
		forward = new ActionForward("/index.jsp",false);
		return forward;
	}

}
