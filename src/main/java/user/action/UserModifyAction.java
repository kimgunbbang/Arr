package user.action;

import java.io.PrintWriter; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import user.svc.UserModifyService;
import vo.ActionForward;
import vo.User;

public class UserModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null) {
			forward = new ActionForward("userLogin.u",true);
		}else {//아니면
			User user = new User();
			user.setId(request.getParameter("id"));
			if(request.getParameter("user_pwch")!=null && !request.getParameter("user_pass").equals("")) {
				user.setUser_pass(request.getParameter("user_pass"));
			}

			
			user.setUser_name(request.getParameter("user_name"));
			user.setUser_zipcode(request.getParameter("user_zipcode"));
			user.setUser_addr(request.getParameter("user_addr"));
			user.setUser_addr2(request.getParameter("user_addr2"));
			user.setUser_phone(request.getParameter("user_phone"));
			
			UserModifyService userModifyService = new UserModifyService();
			boolean userUpdate= userModifyService.updateUser(user);
			
			if(userUpdate) {
				forward=new ActionForward("userViewAction.u?id="+request.getParameter("id"),true);
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('정보수정에 실패하였습니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
			
		}
		return forward;
	}

}
