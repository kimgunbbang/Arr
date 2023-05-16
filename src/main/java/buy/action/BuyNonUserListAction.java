package buy.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import buy.svc.BuyInfoService;
import buy.svc.BuyService;
import vo.ActionForward;
import vo.Buy;
import vo.BuyInfo;

public class BuyNonUserListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int buy_num = Integer.parseInt(request.getParameter("buy_num"));
		String buy_phone = request.getParameter("buy_phone");
		
		BuyInfoService buyInfoService = new BuyInfoService();
		boolean isBuyer = buyInfoService.isBuyer(buy_num,buy_phone);//구매번호와 폰번호가 맞는지 확인하기
		
		if(isBuyer) {//구매자가 맞다면
			ArrayList<Buy> buyList = new ArrayList<Buy>();
			BuyService buyService = new BuyService();//구매내역가져오기
			buyList = buyService.getbuyList(buy_num);
			BuyInfo buyInfo = new BuyInfo();//구매정보가져오기
			buyInfoService = new BuyInfoService();
			buyInfo = buyInfoService.buyInfoView(Integer.toString(buy_num));
			
			request.setAttribute("buyList", buyList);
			request.setAttribute("buyInfo", buyInfo);
			request.setAttribute("pagefile", "/buy/buyNonUserList.jsp");
			forward = new ActionForward("index.jsp",false);
		}else {//구매자가 아니라면
			response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('주문자가 아닙니다.')");
            out.println("history.back()");
            out.println("</script>");
		}
		return forward;
	}

}
