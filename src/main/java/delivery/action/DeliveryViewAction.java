package delivery.action;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import delivery.svc.DeliveryViewService;
import user.svc.UserViewService;
import vo.ActionForward;
import vo.Delivery;

public class DeliveryViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ActionForward forward = null;
	    HttpSession session = request.getSession();
	    String id = (String) session.getAttribute("id");
	    int deli_num = Integer.parseInt(request.getParameter("deli_num"));
	    if (id == null) {
	        forward = new ActionForward("productAllList.p", true);
	    } else {
	        DeliveryViewService deliveryViewService = new DeliveryViewService();
	        Delivery delivery = deliveryViewService.selectDelivery(id,deli_num);
	        session.setAttribute("delivery", delivery);
	        request.setAttribute("pagefile", "/delivery/deliveryInfo.jsp");
	        forward = new ActionForward("/index.jsp", false);
	    }
	    return forward;
	}
}