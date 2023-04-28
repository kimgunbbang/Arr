package user.action;

import java.io.PrintWriter; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import user.svc.UserDeleteService;
import vo.ActionForward;

public class UserDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		if(id==null) {
			forward=new ActionForward("userLogin.u",true);
		}else if(id.equals("admin")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();	
			out.println("<script>");
			out.println("alert('관리자가 아닙니다.')");
			out.println("location.href='userLogin.u'");
			out.println("</script>");
		}
		
		id = request.getParameter("id");
		
		
		UserDeleteService userDeleteService = new UserDeleteService();
		boolean deleteResult = userDeleteService.deleteUser(id);
		if(deleteResult){
			forward = new ActionForward("userListAction.u",true);
			
			
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
