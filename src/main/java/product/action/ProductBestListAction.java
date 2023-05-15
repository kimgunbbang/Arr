package product.action;

import java.util.ArrayList; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import product.svc.ProductListService;
import vo.ActionForward;
import vo.Product;

public class ProductBestListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String id = request.getSession().getId();
		
		ProductListService productListService = new ProductListService();
		ArrayList<Product> productReadList = productListService.getProductBestReadList(); //조회수순
		ArrayList<Product> productSaleList = productListService.getProductBestSaleList(); //판매량순

		
		request.setAttribute("productSaleList", productSaleList);
		request.setAttribute("productReadList", productReadList);
	    request.setAttribute("pagefile", "/product/productBestList.jsp");
		forward = new ActionForward("/index.jsp",false);//보여주는경로
		forward.setPath("/index.jsp");
		return forward;
	}

}
