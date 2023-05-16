package review.action;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import review.svc.ReviewWriteService;
import vo.ActionForward;
import vo.Review;

public class ReviewWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Review review = new Review();
		boolean writeResult = false;
		
		String id = (String) session.getAttribute("id");
		
		String numParam = request.getParameter("p_num");
		int num = numParam == null ? 0 : Integer.parseInt(numParam);
		

		
		review.setId(id);
		review.setP_num(request.getParameter("p_num"));
		review.setR_num(request.getParameter("r_num"));
		review.setR_rating(request.getParameter("r_rating"));
		review.setR_title(request.getParameter("r_title"));
		review.setR_detail(request.getParameter("r_detail"));
		review.setR_image(request.getParameter("r_image"));
		review.setR_date(request.getParameter("r_date"));
		
		ReviewWriteService reviewWriteService = new ReviewWriteService();
		writeResult = reviewWriteService.writeReview(review);
		
		if(writeResult) {
			forward = new ActionForward("productAllList.p",true);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('리뷰등록실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
