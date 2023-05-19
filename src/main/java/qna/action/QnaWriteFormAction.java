package qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class QnaWriteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	ActionForward forward = null;
		
		String p_num = request.getParameter("p_num");

		
		request.setAttribute("p_num", p_num);

		request.setAttribute("pagefile", "/qna/qnaWriteForm.jsp");
		
		forward = new ActionForward("index.jsp",false);
		
		
		return forward;
	}

}
