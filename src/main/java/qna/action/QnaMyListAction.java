package qna.action;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import product.svc.ProductListService;
import qna.svc.QanMyListService;
import vo.ActionForward;
import vo.Product;
import vo.Qna;

public class QnaMyListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		QanMyListService qanMyListService = new QanMyListService();
		ArrayList<Qna> qnaMyList = qanMyListService.getQnaMyList(id);
		ProductListService productListService = new ProductListService();
		ArrayList<Product> imgList = productListService.getProductAllList();
		
		request.setAttribute("imgList",imgList);
		request.setAttribute("qnaMyList", qnaMyList);
		request.setAttribute("pagefile", "/qna/qnaMyList.jsp");
		forward = new ActionForward("/index.jsp",false);
		return forward;
	}

}
