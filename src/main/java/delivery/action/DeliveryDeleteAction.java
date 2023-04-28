package delivery.action;

import java.io.PrintWriter; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import delivery.svc.DeliveryDeleteService;
import vo.ActionForward;

public class DeliveryDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		

		if(id==null) {
		    forward=new ActionForward("productAllList.p",true);
		} else {
		    id = request.getParameter("id");
		}
		
		
		DeliveryDeleteService deliveryDeleteService = new DeliveryDeleteService();
		boolean deleteResult = deliveryDeleteService.deleteDelivery(id);
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
