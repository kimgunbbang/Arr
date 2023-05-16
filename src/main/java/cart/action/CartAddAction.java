package cart.action;

import java.io.PrintWriter; 
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import cart.svc.CartAddService;
import vo.ActionForward;
import vo.Cart;
import vo.Noncart;


public class CartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		//String uuid = (String) session.getAttribute("uuid"); // 세션에서 UUID 가져오기
		
		String numParam = request.getParameter("p_num");
		int num = numParam == null ? 0 : Integer.parseInt(numParam);
		if(id == null ) { //로그인 안했음
			// 요청에 포함된 쿠키 배열 가져오기
			Cookie[] cookies = request.getCookies();
			String uuid = null;
			// UUID 쿠키 찾기
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			        if (cookie.getName().equals("uuid")) {
			            uuid = cookie.getValue();
			            break;
			        }
			    }
			}else {
				// UUID 생성
				uuid = UUID.randomUUID().toString();

				// 생성된 UUID를 문자열로 변환하여 쿠키에 저장
				Cookie uuidCookie = new Cookie("uuid", uuid.toString());
				uuidCookie.setMaxAge(24 * 60 * 60); // 쿠키 유효기간 1일 설정
				response.addCookie(uuidCookie);	
			}

			// UUID가 존재하지 않는 경우 예외 처리 또는 기본값 설정
			if (uuid == null) {
				// UUID 생성
				uuid = UUID.randomUUID().toString();

				// 생성된 UUID를 문자열로 변환하여 쿠키에 저장
				Cookie uuidCookie = new Cookie("uuid", uuid.toString());
				uuidCookie.setMaxAge(24 * 60 * 60); // 쿠키 유효기간 1일 설정
				response.addCookie(uuidCookie);
			}

			Noncart noncart = new Noncart();
			
			//id = UUID.randomUUID().toString(); // UUID 생성
			//session.setAttribute("id", id); // 생성한 UUID를 세션에 저장
		

			
			noncart.setP_num(num);
			noncart.setId(uuid);
			
			CartAddService cartAddService = new CartAddService();
			boolean isAddSuccess = cartAddService.NonAddCart(noncart);

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

		}
		else {

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
	
		return forward;
	}

}