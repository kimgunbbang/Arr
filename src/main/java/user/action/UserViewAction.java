package user.action;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import user.svc.UserViewService;
import vo.ActionForward;
import vo.User;

public class UserViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ActionForward forward = null;
	    HttpSession session = request.getSession();
	    String id = (String) session.getAttribute("id");
	    if (id == null) {
	        forward = new ActionForward("userLogin.u", true);
	    } else {
	    	String userId = request.getParameter("id");
	        UserViewService userViewService = new UserViewService();
	        User user = userViewService.selectUser(userId);
	        session.setAttribute("user", user);
	        request.setAttribute("pagefile", "/user/userInfo.jsp");
	        forward = new ActionForward("/index.jsp", false);
	    }
	    return forward;
	}
}