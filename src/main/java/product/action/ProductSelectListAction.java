package product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import product.svc.ProductListService;
import vo.ActionForward;
import vo.Product;

public class ProductSelectListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String category_name = request.getParameter("category_name");
		
		ProductListService productListService = new ProductListService();
		ArrayList<Product> productList = productListService.getProductSelectList(category_name);
		System.out.println(productList.get(0).getP_qty());
		request.setAttribute("productList", productList);
		request.setAttribute("pagefile", "/product/productList.jsp");
		
		forward = new ActionForward("/index.jsp",false);//보여주는경로
		
		return forward;
	}

}
