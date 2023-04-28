package user.action;

import java.io.PrintWriter; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import user.svc.UserViewService;
import vo.ActionForward;
import vo.User;

public class UserModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null) {
			forward = new ActionForward("userLogin.u",true);
		}else {
			UserViewService userViewService = new UserViewService();
			User user = userViewService.selectUser(request.getParameter("id"));
			request.setAttribute("user", user);
			request.setAttribute("pagefile", "/user/userModifyForm.jsp");
			forward=new ActionForward("/index.jsp",false);
			
		}
		return forward;
	}

}
