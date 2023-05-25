package review.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import qna.svc.QnaListService;
import review.svc.ReviewListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.Review;

public class ReviewAllListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String id=(String)request.getSession().getAttribute("id");
		
				//페이지 들어가는부분 그냥 갖다붙여쓰면됨-페이지계산
				ReviewListService reviewListService = new ReviewListService();
				int page = 1;//첫페이지
				int limit = 5;//한페이지에 목록수
				int limitpage = 10;//한페이지에서 보이는 페이지수
				if(request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
				}
				int listcount =  reviewListService.getListCount();
				int maxpage = (int)((double)listcount/limit+0.98);
				int startpage=((int)((double)page/limitpage+0.9)-1)*limitpage+1;
				int endpage = startpage+limitpage-1;
				if(endpage> maxpage) endpage = maxpage;
				PageInfo pageinfo = new PageInfo();
				pageinfo.setEndpage(endpage);
				pageinfo.setListcount(listcount);
				pageinfo.setMaxpage(maxpage);
				pageinfo.setPage(page);
				pageinfo.setStartpage(startpage);
		
		
		
		
		
		reviewListService = new ReviewListService();
		ArrayList<Review> reviewList = reviewListService.reviewAllList(page,limit,id);
		System.out.println(reviewList.toString());
		
		request.setAttribute("pageinfo", pageinfo);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("pagefile", "/review/reviewAllList.jsp");
	    forward = new ActionForward("/index.jsp", false);
		
		return forward;
	}

}
