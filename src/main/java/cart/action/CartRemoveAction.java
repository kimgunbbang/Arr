package cart.action;

import java.io.PrintWriter; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import cart.svc.CartRemoveService;
import vo.ActionForward;

public class CartRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String[] cartList = null;
		cartList = request.getParameterValues("remove");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if(id == null) {
			CartRemoveService cartRemoveService = new CartRemoveService();
			boolean removeResult = cartRemoveService.removeNonCart(cartList);
			if(removeResult){
	            forward = new ActionForward("cartList.ct",true);
				
				
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		}
		else {
			CartRemoveService cartRemoveService = new CartRemoveService();
			boolean removeResult = cartRemoveService.removeCart(cartList);
			if(removeResult){
	            forward = new ActionForward("cartList.ct",true);
				
				
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		}
	
		return forward;
	}

}