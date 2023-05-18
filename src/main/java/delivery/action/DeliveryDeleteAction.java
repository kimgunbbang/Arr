package delivery.action;

import java.io.PrintWriter; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import delivery.svc.DeliveryDeleteService;
import vo.ActionForward;
import vo.Delivery;

public class DeliveryDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String deli_num = request.getParameter("deli_num");
		
		Delivery delivery = new Delivery();
		
		delivery.setId(id);
		delivery.setDeli_num(deli_num);
		System.out.println(delivery.getId());
		System.out.println(delivery.getDeli_num());
		
		DeliveryDeleteService deliveryDeleteService = new DeliveryDeleteService();
		boolean deleteResult = deliveryDeleteService.deleteDelivery(delivery);
		if(deleteResult){
			forward = new ActionForward("deliveryListAction.del",true);
			
			
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
