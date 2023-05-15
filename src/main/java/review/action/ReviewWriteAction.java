package review.action;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;
import vo.Review;

public class ReviewWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		Review review = new Review();
		boolean addResult = false;
		
		review.setId(request.getParameter("id"));
		review.setP_num(Integer.parseInt(request.getParameter("p_num")));
		review.setR_num(Integer.parseInt(request.getParameter("r_num")));
		review.setR_rating(Integer.parseInt(request.getParameter("r_rating")));
		review.setR_title(request.getParameter("r_title"));
		review.setR_detail(request.getParameter("r_detail"));
		review.setR_image(request.getParameter("r_image"));
		review.setR_date(request.getParameter("r_date"));
		
		return forward;
	}

}
