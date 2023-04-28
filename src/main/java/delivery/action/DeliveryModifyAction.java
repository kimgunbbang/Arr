package delivery.action;

import java.io.PrintWriter; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import delivery.svc.DeliveryrModifyService;
import vo.ActionForward;
import vo.Delivery;

public class DeliveryModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null) {
			forward = new ActionForward("productAllList.p",true);
		}else {//아니면
			Delivery delivery = new Delivery();
			delivery.setId(request.getParameter("id"));

			delivery.setDeli_name(request.getParameter("deli_name"));
			delivery.setDeli_zipcode(request.getParameter("deli_zipcode"));
			delivery.setDeli_addr(request.getParameter("deli_addr"));
			delivery.setDeli_addr2(request.getParameter("deli_addr2"));
			delivery.setDeli_username(request.getParameter("deli_username"));
			delivery.setDeli_phone(request.getParameter("deli_phone"));
			
			DeliveryrModifyService deliveryModifyService = new DeliveryrModifyService();
			boolean deliveryUpdate= deliveryModifyService.updateDelivery(delivery);
			
			if(deliveryUpdate) {
				forward=new ActionForward("deliveryViewAction.del",true);
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('배송지수정에 실패하였습니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
			
		}
		return forward;
	}

}