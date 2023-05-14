package product.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import product.svc.ProductDeleteService;
import vo.ActionForward;

public class ProductDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		
		ProductDeleteService productDeleteService = new ProductDeleteService();
		boolean isDelete = productDeleteService.productDelete(p_num);//실제로 삭제하는게 아니라 p_hide를 바꿀거임
		if(isDelete) {//등록이 됬으면
			forward = new ActionForward("adminProductList.ad",true);
			
		}else{
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert(상품삭제 실패')");
			out.println("historyback()");
			out.println("</script>");
		}
		return forward;
	}

}
