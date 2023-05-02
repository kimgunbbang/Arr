package buy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;
import vo.Buy;

public class BuyActionForm implements Action {

	@Override//액션에서는
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;//널처리해주고
		String id = request.getParameter("id");//파라미터처리해주고 //아이디
		int p_num = Integer.parseInt(request.getParameter("p_num"));//상품번호
		int buy_qty = Integer.parseInt(request.getParameter("buy_qty"));//구매수량
		int p_price = Integer.parseInt(request.getParameter("p_price"));//상품금액
		int buy_totalmoney=buy_qty*p_price;
		
		Buy buy = new Buy();
		buy.setId(id);
		buy.setP_num(p_num);
		buy.setBuy_qty(buy_qty);
		buy.setBuy_totalmoney(buy_totalmoney);
		
		request.setAttribute("p_price", p_price);
		request.setAttribute("buy", buy);
		request.setAttribute("pagefile","/buy/buyForm.jsp");
		forward = new ActionForward("index.jsp",false);
		//
		return forward;
	}

}
