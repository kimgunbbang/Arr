package qna.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import qna.svc.QnaListService;
import vo.ActionForward;
import vo.Qna;

public class QnaListAction implements Action  {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		
		QnaListService qnaListService = new QnaListService();
		ArrayList<Qna> qnaList = qnaListService.qnaAllList();
		
		System.out.println(qnaList);
		request.setAttribute("qnaList", qnaList);
		request.setAttribute("pagefile", "/product/productDetailView.jsp");
		forward = new ActionForward("/index.jsp",false);
		forward.setPath("/index.jsp");
		return forward;
	}

}
