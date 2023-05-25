package admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import inventory.svc.InventoryListService;
import product.svc.ProductListService;
import qna.svc.QnaListService;
import review.svc.ReviewListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.Product;
import vo.Qna;
import vo.Review;

public class AdminQnaList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//페이지 들어가는부분 그냥 갖다붙여쓰면됨-페이지계산
		QnaListService qnaListService = new QnaListService();
		int page = 1;//첫페이지
		int limit = 10;//한페이지에 목록수
		int limitpage = 10;//한페이지에서 보이는 페이지수
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int listcount =  qnaListService.getListCount();
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
		
		qnaListService = new QnaListService();
		ArrayList<Qna> qnaList = qnaListService.qnaAllList(page,limit);
		
		ProductListService productListService = new ProductListService();
		ArrayList<Product> imgList = productListService.getProductAllList();
		
		request.setAttribute("imgList",imgList);
		request.setAttribute("pageinfo", pageinfo);
		request.setAttribute("qnaList", qnaList);
		request.setAttribute("pagefile", "/admin/adminQnaList.jsp");
	    forward = new ActionForward("/index.jsp", false);
		return forward;
	}

}
