package product.action;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;
import vo.Product;

public class productRecentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   HttpSession session = request.getSession();

	        // 세션에서 최근 본 상품 목록 가져오기
	        ArrayList<Product> recentViewProduct = (ArrayList<Product>) session.getAttribute("recentViewProduct");

	        // 최근 본 상품 목록이 없을 경우 새로운 목록 생성
	        if (recentViewProduct == null) {
	        	recentViewProduct = new ArrayList<>();
	        }
	
	        // 상품 정보를 받아오는 로직 (예시)
	        int p_num = Integer.parseInt(request.getParameter("p_num"));
	        String p_name = request.getParameter("p_name");
	        int p_price = Integer.parseInt(request.getParameter("p_price"));;
	        String p_image = request.getParameter("p_image");

	        // 상품 객체 생성
	        Product product = new Product();
	        product.setP_num(p_num);
	        product.setP_name(p_name);
	        product.setP_price(p_price);
	        product.setP_image(p_image);

	        // 최근 본 상품 목록에 상품 추가 (상품이 중복되지 않도록 제거 후 맨 앞에 추가)
	        recentViewProduct.remove(product);
	        recentViewProduct.add(0, product);

	        // 최근 본 상품 목록을 세션에 저장
	        session.setAttribute("recentViewProduct", recentViewProduct);

	        // 이동할 페이지 정보 설정
	        ActionForward forward = new ActionForward();
	        forward = new ActionForward("productRecent.p",true);

	        return forward;
	    }
	}