package cart.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import cart.svc.CartAddService;
import product.svc.ProductListService;
import vo.ActionForward;
import vo.Cart;
import vo.Product;

public class CartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();

		
		String id = (String) session.getAttribute("id");
		if(id == null) {
			Cart cart = new Cart();
			String p_num = request.getParameter("p_num");
			
			ProductListService productListService = new ProductListService();
			Product product = productListService.getProduct(p_num);
			
			CartAddService cartAddService = new CartAddService();
			ArrayList<Cart> cartList = new ArrayList<Cart>();
			cartList = cartAddService.getCartProduct(request,product);
			System.out.println("카트리스트="+cartList);
			session.setAttribute("cartList", cartList);
			
			// 장바구니 추가 후 팝업창 띄우기
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("if(confirm('장바구니에 상품을 담았습니다. 장바구니로 이동하시겠습니까?')){");
			out.println("location.href='cartList.ct';}");
			out.println("else { history.back(); }");
			out.println("</script>");
			out.flush(); // 버퍼 비우기

		}else {
			String numParam = request.getParameter("p_num");
			int num = numParam == null ? 0 : Integer.parseInt(numParam);

			Cart cart = new Cart();
			cart.setP_num(num);
			cart.setId(id);

			CartAddService cartAddService = new CartAddService();
			boolean isAddSuccess = cartAddService.addCart(cart);

			if (isAddSuccess == false) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('이미 장바구니에 담긴 상품입니다!')");
				out.println("history.back()");
				out.println("</script>");
				out.flush(); // 버퍼 비우기
				return forward;
			}

			// 장바구니 추가 후 팝업창 띄우기
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("if(confirm('장바구니에 상품을 담았습니다. 장바구니로 이동하시겠습니까?')){");
			out.println("location.href='cartList.ct';}");
			out.println("else { history.back(); }");
			out.println("</script>");
			out.flush(); // 버퍼 비우기

		}
	
		return forward;
	}

}