package qna.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import qna.svc.QnaAnswerService;
import vo.ActionForward;
import vo.Qna;

public class QnaAnswerAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		Qna qna = new Qna();
		boolean writeResult = false;
		
		qna.setP_num(request.getParameter("p_num"));
		qna.setQna_num(request.getParameter("qna_num"));
		qna.setQna_reply(request.getParameter("qna_reply"));
		
		System.out.println(request.getParameter("qna_num"));
		System.out.println(request.getParameter("qna_reply"));
		QnaAnswerService qnaAnswerService = new QnaAnswerService();
		writeResult = qnaAnswerService.writeAnswer(qna);
		
		if(writeResult) {
			request.setAttribute("p_num", request.getParameter("p_num"));
			forward = new ActionForward("productDetailView.p?p_num="+request.getParameter("p_num")+"#qnaSection",true);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('답변등록실패')");
			out.println("history.back()");
			out.println("</script>");
		}		
		
		return forward;
	}

}
