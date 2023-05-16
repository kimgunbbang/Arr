package product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import inventory.action.InventoryListAction;
import inventory.svc.InventoryListService;
import product.svc.ProductDetailViewService;
import review.action.ReviewListAction;
import review.svc.ReviewListService;
import vo.ActionForward;
import vo.Inventory;
import vo.Product;
import vo.Review;

public class ProductDetailViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ActionForward forward = null;
	    int p_num = Integer.parseInt(request.getParameter("p_num"));

	    ProductDetailViewService productDetailViewService = new ProductDetailViewService();
	    Product product = productDetailViewService.getProductView(p_num);

	    HttpSession session = request.getSession();
	    ArrayList<Product> recentViewProduct = (ArrayList<Product>) session.getAttribute("recentViewProduct");

	    if (recentViewProduct == null) {
	        recentViewProduct = new ArrayList<>();
	    }

	    // 최근 본 상품 유지 기간 (분 단위)
	    int recentViewExpiration = 30;

	    // 세션에 최근 본 상품을 저장할 때 현재 시간도 함께 저장
	    long currentTime = System.currentTimeMillis();

	    // 최근 본 상품 목록을 순회하며 유효한 상품만 남기고 나머지는 제거
	    for (int i = recentViewProduct.size() - 1; i >= 0; i--) {
	        Product recentProduct = recentViewProduct.get(i);
	        long viewTime = recentProduct.getViewTime();
	        long elapsedTime = currentTime - viewTime;
	        if (elapsedTime > recentViewExpiration * 60 * 1000) {
	            recentViewProduct.remove(i);
	        }else if (recentProduct.getP_num() == product.getP_num()) {
	            // 상품이 이미 최근 본 상품 목록에 있는 경우 중복 추가 방지
	            recentViewProduct.remove(i);
	        }
	    }
	    
	    ReviewListAction reviewListAction = new ReviewListAction();
	    
		ReviewListService reviewListService = new ReviewListService();
		ArrayList<Review> reviewList = reviewListService.reviewAllList();
		

		request.setAttribute("reviewList", reviewList);

	    // 최근 본 상품에 현재 상품을 추가하고 시간을 설정
	    product.setViewTime(currentTime);
	    recentViewProduct.add(0, product);

	    // 최근 본 상품 목록의 길이를 제한하여 유지 기간 이후의 상품을 제거
	    if (recentViewProduct.size() > 10) {
	        recentViewProduct.subList(10, recentViewProduct.size()).clear();
	    }

	    // 최신 최근 본 상품 목록을 세션에 저장
	    session.setAttribute("recentViewProduct", recentViewProduct);

	    request.setAttribute("product", product);
	    request.setAttribute("pagefile", "/product/productDetailView.jsp");
	    forward = new ActionForward("/index.jsp", false);
	    return forward;
	}
}