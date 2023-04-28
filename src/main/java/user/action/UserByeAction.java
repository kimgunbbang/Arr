package user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import user.svc.UserByeService;
import user.svc.UserDeleteService;
import vo.ActionForward;

public class UserByeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null) {
			forward = new ActionForward("userLogin.u",true);
		}else {//아니면
			UserByeService userByeService = new UserByeService();
			boolean userBye= userByeService.byeUser(id);
			
			if(userBye) {
				session.invalidate();
				forward=new ActionForward("index.jsp",true);
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('탈퇴실패')");
				out.println("history.back()");
				out.println("</script>");
			}
			
		}
		return forward;
	}

}