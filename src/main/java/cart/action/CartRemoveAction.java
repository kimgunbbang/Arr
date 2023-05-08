package cart.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import cart.svc.CartRemoveService;
import dog.svc.DogCartRemoveService;
import vo.ActionForward;

public class CartRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;//포워드값을 널로 해주고
		String[] cartArray=request.getParameterValues("remove");
		//리퀘스트처리 옵션박스 이름이 같아서 배열로 넘어옴->배열로 처리
		HttpSession session =request.getSession();
		session.setAttribute("id", session.getAttribute("id"));
		CartRemoveService cartRemoveService = new CartRemoveService();
		cartRemoveService.cartRemove(request,cartArray);
		forward=new ActionForward("cartList.ct",true);
		return forward;
	}

}
