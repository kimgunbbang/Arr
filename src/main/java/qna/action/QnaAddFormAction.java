package qna.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import product.svc.ProductListService;
import vo.ActionForward;
import vo.Product;

public class QnaAddFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		ProductListService productListService = new ProductListService();
		ArrayList<Product> productList = productListService.getProductAllList();
		
		request.setAttribute("productList", productList);
		request.setAttribute("pagefile", "/qna/qnaAddForm.jsp");
		forward = new ActionForward("/index.jsp",false);
		return forward;
	}

}
