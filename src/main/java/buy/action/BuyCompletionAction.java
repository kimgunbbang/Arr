package buy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import buy.svc.BuyService;
import vo.ActionForward;

public class BuyCompletionAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int buy_num = Integer.parseInt((String)request.getParameter("buy_num"));
		
		BuyService buyService = new BuyService();
		buyService.changeFinish(buy_num);
		forward = new ActionForward("buyListForm.buy",true);
		return forward;
	}

}
