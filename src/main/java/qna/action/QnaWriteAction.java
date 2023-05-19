package qna.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import qna.svc.QnaWriteService;
import vo.ActionForward;
import vo.Qna;

public class QnaWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		Qna qna = new Qna();
		boolean writeResult = false;
		
		String id = (String) session.getAttribute("id");
		
		qna.setId(id);
		qna.setP_num(request.getParameter("p_num"));
		qna.setQna_num(request.getParameter("qna_num"));
		qna.setQna_subject(request.getParameter("qna_subject"));
		qna.setQna_content(request.getParameter("qna_content"));
		qna.setQna_answer(request.getParameter("qna_answer"));
		qna.setQna_reply(request.getParameter("qna_reply"));
		qna.setQna_date(request.getParameter("qna_date"));
		
		QnaWriteService qnaWriteService = new QnaWriteService();
		writeResult = qnaWriteService.writeQna(qna);
				
		if(writeResult) {
			request.setAttribute("p_num", request.getParameter("p_num"));
			forward = new ActionForward("productDetailView.p?p_num="+request.getParameter("p_num")+"#qnaSection",true);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('문의등록실패')");
			out.println("history.back()");
			out.println("</script>");
		}		
				
		return forward;
	}

}
