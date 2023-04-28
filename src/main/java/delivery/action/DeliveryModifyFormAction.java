package delivery.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import delivery.svc.DeliveryViewService;
import user.svc.UserViewService;
import vo.ActionForward;
import vo.Delivery;
import vo.User;

public class DeliveryModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null) {
			forward = new ActionForward("productAllList.p",true);
		}else {
			DeliveryViewService deliveryViewService = new DeliveryViewService();
			Delivery delivery = deliveryViewService.selectDelivery(request.getParameter("id"));
			request.setAttribute("delivery", delivery);
			request.setAttribute("pagefile", "/delivery/deliveryModifyForm.jsp");
			forward=new ActionForward("/index.jsp",false);
			
		}
		return forward;
	}

}