package product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import product.svc.ProductDetailViewService;
import vo.ActionForward;
import vo.Product;

public class ProductDetailViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		
		ProductDetailViewService productDetailViewService = new ProductDetailViewService();
		Product product = productDetailViewService.getProductView(p_num);//Product객체 하나가져올때
		
		request.setAttribute("product", product);
		request.setAttribute("pagefile", "/product/productDetailView.jsp");
		forward=new ActionForward("/index.jsp",false);
		return forward;
	}

}
