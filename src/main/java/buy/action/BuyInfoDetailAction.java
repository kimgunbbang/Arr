package buy.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import buy.svc.BuyInfoService;
import buy.svc.BuyService;
import vo.ActionForward;
import vo.Buy;
import vo.BuyInfo;

public class BuyInfoDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String buy_num = request.getParameter("buy_num");
		ArrayList<Buy> buyList = new ArrayList<Buy>();
		
		BuyService buyService = new BuyService();//구매내역가져오기
		buyList = buyService.getbuyList(Integer.parseInt(buy_num));
		
		BuyInfo buyInfo = new BuyInfo();//구매정보가져오기
		BuyInfoService buyInfoService = new BuyInfoService();
		buyInfo = buyInfoService.buyInfoView(buy_num);
		
		request.setAttribute("buyList", buyList);
		request.setAttribute("buyInfo", buyInfo);
		forward = new ActionForward("/buy/buyInfoAllForm.jsp",false);
		return forward;
	}

}
