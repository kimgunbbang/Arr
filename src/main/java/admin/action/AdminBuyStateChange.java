package admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import admin.svc.BuyStateChangeService;
import vo.ActionForward;

public class AdminBuyStateChange implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String buy_state = request.getParameter("buy_state");
		int buy_num = Integer.parseInt(request.getParameter("buy_num"));
		
		BuyStateChangeService buyStateChangeService = new BuyStateChangeService();
		boolean isChange = buyStateChangeService.stateChange(buy_num,buy_state);
		if(isChange) {
			forward = new ActionForward("adminBuyList.ad",true);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('주문상태 수정실패.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
