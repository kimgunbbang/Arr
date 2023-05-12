package product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import product.svc.ProductListService;
import vo.ActionForward;
import vo.Product;

public class ProductBestSaleListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String id = request.getSession().getId();
		
		ProductListService productListService = new ProductListService();
		ArrayList<Product> productList = productListService.getProductBestSaleList();
		ProductInventoryCheckService productInventoryCheckService=new ProductInventoryCheckService();//전체상품가져와서 재고확인후 재고가 없으면 false처리
		productList = productInventoryCheckService.productInventoryCheck(productList);
		productList = productListService.getProductBestSaleList();
		
		
		request.setAttribute("productList", productList);
	    request.setAttribute("pagefile", "/product/productBestSaleList.jsp");
		forward = new ActionForward("/index.jsp",false);//보여주는경로
		forward.setPath("/index.jsp");
		return forward;
	}

}
