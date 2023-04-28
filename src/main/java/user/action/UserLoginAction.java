package user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import user.svc.UserLoginService;
import vo.ActionForward;
import vo.User;

public class UserLoginAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String id = request.getParameter("id");
		String user_pass = request.getParameter("user_pass");
		
		UserLoginService userLoginService = new UserLoginService();
		User user = userLoginService.getUser(id); 
		boolean loginSuccess = userLoginService.login(id, user_pass); 
		
		if (loginSuccess && user != null && user.isUser_bye() == false) { 
		    HttpSession session = request.getSession();
		    session.setAttribute("id", user.getId());
		    forward = new ActionForward();
		    request.setAttribute("pagefile", "main.jsp");
		    forward.setPath("/index.jsp");
		} else if(user.isUser_bye() == true){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('이미 탈퇴한 회원입니다. 회원가입을 해주세요.')");
			out.println("history.back()");
			out.println("</script>");
		}else if(!loginSuccess){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원정보가 없습니다. 회원가입을 해주세요.')");
			out.println("history.back()");
			out.println("</script>");
		}
		else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원정보가 일치하지 않거나 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}
}
