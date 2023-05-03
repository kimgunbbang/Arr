package user.action;

import java.io.PrintWriter; 
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import user.svc.UserListService;
import vo.ActionForward;
import vo.User;

public class UserListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("id");
		
		
		if(id !=null && id.equals("admin")) {//관리자일때
			UserListService userListService = new UserListService();
			ArrayList<User> userList = userListService.getUserList();
			request.setAttribute("userList", userList);
			request.setAttribute("pagefile", "/user/userList.jsp");
			forward = new ActionForward("/index.jsp",false);
		}else if(id !=null && !id.equals("admin")){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자만 접근할 수 있습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			forward= new ActionForward("userLogin.u",true);
		}
		
		return forward;
	}

}

