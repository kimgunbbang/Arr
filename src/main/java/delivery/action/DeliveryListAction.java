package delivery.action;

import java.io.PrintWriter; 
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import delivery.svc.DeliveryListService;
import user.svc.UserListService;
import vo.ActionForward;
import vo.Delivery;
import vo.User;

public class DeliveryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("id");
		
		
		if(id !=null ) {
			DeliveryListService deliveryListService = new DeliveryListService();
			ArrayList<Delivery> deliveryList = deliveryListService.getDeliveryList();
			request.setAttribute("deliveryList", deliveryList);
			request.setAttribute("pagefile", "/delivery/deliveryList.jsp");
			forward = new ActionForward("/index.jsp",false);
		}else if(id == null ){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인하세요.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
