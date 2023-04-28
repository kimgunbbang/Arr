package user.action;

import java.io.PrintWriter; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import user.svc.UserJoinService;
import vo.ActionForward;
import vo.User;

public class UserJoinAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		User user = new User();
		boolean joinResult = false;
		
		user.setId(request.getParameter("id"));
		user.setUser_pass(request.getParameter("user_pass"));
		user.setUser_name(request.getParameter("user_name"));
		user.setUser_zipcode(request.getParameter("user_zipcode"));
		user.setUser_addr(request.getParameter("user_addr"));
		user.setUser_addr2(request.getParameter("user_addr2"));
		user.setUser_phone(request.getParameter("user_phone"));
		
		UserJoinService userJoinService = new UserJoinService();
		joinResult = userJoinService.joinUser(user);
		
		if(joinResult) {
			forward = new ActionForward("productAllList.p",true);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}
}
