package delivery.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import delivery.svc.DeliveryAddService;
import vo.ActionForward;
import vo.Delivery;
import vo.User;

public class DeliveryAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		Delivery delivery = new Delivery();
		boolean addResult = false;
		
		delivery.setId(request.getParameter("id"));
		delivery.setDeli_name(request.getParameter("deli_name"));
		delivery.setDeli_zipcode(request.getParameter("deli_zipcode"));
		delivery.setDeli_addr(request.getParameter("deli_addr"));
		delivery.setDeli_addr2(request.getParameter("deli_addr2"));
		delivery.setDeli_username(request.getParameter("deli_username"));
		delivery.setDeli_phone(request.getParameter("deli_phone"));
		
		DeliveryAddService deliveryAddService = new DeliveryAddService();
		addResult = deliveryAddService.addDeli(delivery);
		
		if(addResult) {
			forward = new ActionForward("productAllList.p",true);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('배송지등록실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}
}