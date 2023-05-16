package review.action;

import java.util.ArrayList; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import product.svc.ProductListService;
import review.svc.ReviewListService;
import vo.ActionForward;
import vo.Product;
import vo.Review;

public class ReviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		
		ReviewListService reviewListService = new ReviewListService();
		ArrayList<Review> reviewList = reviewListService.reviewAllList();
		

		request.setAttribute("reviewList", reviewList);
		request.setAttribute("pagefile", "/product/productDetailView.jsp");
		forward = new ActionForward("/index.jsp",false);
		forward.setPath("/index.jsp");
		return forward;
	}

}
