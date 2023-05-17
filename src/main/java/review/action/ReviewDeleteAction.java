package review.action;

import java.io.PrintWriter; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import review.svc.ReviewDeleteService;
import vo.ActionForward;
import vo.Review;

public class ReviewDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Review review = new Review();

	
		ReviewDeleteService reviewDeleteService = new ReviewDeleteService();
		boolean deleteResult = reviewDeleteService.deleteReview(review);
		if(deleteResult){
			forward = new ActionForward("productDetailViewAction.p",true);
			
			
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
