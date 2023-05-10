package buy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import buy.svc.BuyInfoService;
import vo.ActionForward;
import vo.BuyInfo;

public class BuyInfoFormAction implements Action {

	@Override//액션에서는
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;//포워드 널처리하고
		String buy_num = (String)request.getParameter("buy_num");//파라미터처리하고
		BuyInfoService buyInfoService = new BuyInfoService();//서비스만들어서
		BuyInfo buyInfo = buyInfoService.buyInfoView(buy_num);
		
		request.setAttribute("buyInfo", buyInfo);//리퀘스트에 담아서
		request.setAttribute("pagefile", "/buy/buyInfoForm.jsp");//보내기
		forward = new ActionForward("/index.jsp",false);
		
		
		return forward;
	}

}
